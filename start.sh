#!/bin/sh

chmod +x ./gradlew
./graldew :woodo-homework-api:build

java -jar ./woodo-homework-api/build/libs/woodo-homework-api-0.0.1-SNAPSHOT.jar --spring.profiles.active=db-h2

