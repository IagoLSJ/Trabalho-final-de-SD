#!/bin/bash

# Linux Script
# Make the script executable chmod +x generate_protos.sh
# Run the script ./generate_protos.sh

# Compile Protocol Buffers to Python
protoc -I=../ --python_out=../greensteam-server greensteam.proto

# Compile Protocol Buffers to Java
protoc -I=../ --java_out=../greensteam_client/src/main/java greensteam.proto