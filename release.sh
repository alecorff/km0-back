#!/bin/bash
set -e

TYPE=$1

if [[ "$TYPE" != "patch" && "$TYPE" != "minor" && "$TYPE" != "major" ]]; then
  echo "Usage: ./release.sh {patch|minor|major}"
  exit 1
fi

### ===== CONFIG JAVA =====
export JAVA_HOME="/c/Program Files/OpenJDK/jdk-21.0.8"
export PATH="$JAVA_HOME/bin:$PATH"

### ===== CONFIG GIT =====
GIT_REMOTE=origin

# Sync branches
git checkout master
git pull $GIT_REMOTE master
git checkout develop
git pull $GIT_REMOTE develop

# Get current version
CURRENT_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)

if [[ "$CURRENT_VERSION" != *-SNAPSHOT ]]; then
  echo "Current version must be a SNAPSHOT"
  exit 1
fi

BASE_VERSION=${CURRENT_VERSION%-SNAPSHOT}
IFS='.' read -r MAJOR MINOR PATCH <<< "$BASE_VERSION"

### ===== CALCULATE RELEASE VERSION =====
case $TYPE in
  patch)
    RELEASE_MAJOR=$MAJOR
    RELEASE_MINOR=$MINOR
    RELEASE_PATCH=$PATCH
    ;;
  minor)
    RELEASE_MAJOR=$MAJOR
    RELEASE_MINOR=$((MINOR + 1))
    RELEASE_PATCH=0
    ;;
  major)
    RELEASE_MAJOR=$((MAJOR + 1))
    RELEASE_MINOR=0
    RELEASE_PATCH=0
    ;;
esac

RELEASE_VERSION="$RELEASE_MAJOR.$RELEASE_MINOR.$RELEASE_PATCH"
echo "Releasing version $RELEASE_VERSION"

### ===== START RELEASE =====
git flow release start "$RELEASE_VERSION"

# Set release version
mvn versions:set \
  -DnewVersion="$RELEASE_VERSION" \
  -DgenerateBackupPoms=false

git commit -am "build(release): $RELEASE_VERSION"

mvn clean package -DskipTests

GIT_MERGE_AUTOEDIT=no git flow release finish \
  -m "Release $RELEASE_VERSION" \
  -T "$RELEASE_VERSION"

git push $GIT_REMOTE refs/tags/"$RELEASE_VERSION"

### ===== NEXT DEV VERSION =====
NEXT_PATCH=$((RELEASE_PATCH + 1))
NEXT_VERSION="$RELEASE_MAJOR.$RELEASE_MINOR.$NEXT_PATCH-SNAPSHOT"

mvn versions:set \
  -DnewVersion="$NEXT_VERSION" \
  -DgenerateBackupPoms=false

git commit -am "build(dev): prepare next iteration $NEXT_VERSION"

git push $GIT_REMOTE develop
git push $GIT_REMOTE master

echo "Release $RELEASE_VERSION done"
