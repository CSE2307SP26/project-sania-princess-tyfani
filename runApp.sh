#!/bin/bash
#put commands here to compile and run your app from command line
#just run ./runApp.sh in terminal

javac -d out src/main/*.java #compiles java files into class files, puts them in a folder called out
java -cp out main.MainMenu #runs the app, -cp out tells Java where to find the compiled files (out folder), main.MainMenu is the class to run
