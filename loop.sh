#!/bin/bash

NO=1
while true
do
  echo "---  Build #$NO  ---"
  ./gradlew clean build run
  NO=$((NO+1))
done
