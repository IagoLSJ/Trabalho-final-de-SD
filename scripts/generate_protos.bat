@echo off

rem Windows Script
rem Command to run the script generate_protos.bat

rem Compile Protocol Buffers to Python
protoc -I=../ --python_out=../greensteam-server greensteam.proto

rem Compile Protocol Buffers to Java
protoc -I=../ --java_out=../greensteam_client/src/main/java greensteam.proto
