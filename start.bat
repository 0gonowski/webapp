@echo off
echo Budowanie projektu...
call .\mvnw clean package -DskipTests

echo Uruchamianie aplikacji...
java -jar target\webapp-0.0.1-SNAPSHOT.jar

pause

