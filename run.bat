@echo off
set JAVA_HOME=C:\Program Files\OpenJDK\jdk-21.0.8
set PATH=%JAVA_HOME%\bin;%PATH%
mvnw clean spring-boot:run --settings "C:\Users\Alexis\.m2\settings-personal.xml"
pause
