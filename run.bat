@echo off
set JAVA_HOME=C:\Program Files\OpenJDK\jdk-21.0.8
set PATH=%JAVA_HOME%\bin;%PATH%

:: Ajoute tes variables d'environnement ici
set DB_URL=jdbc:postgresql://localhost:5434/km0dev
set DB_USER=admin
set DB_PASSWORD=pB55v1Ks?AxB
set STRAVA_CLIENT_ID=179949
set STRAVA_CLIENT_SECRET=c60e34f786519935d4da649516af780ecec5b48a

mvnw clean spring-boot:run --settings "C:\Users\Alexis\.m2\settings-personal.xml"
pause
