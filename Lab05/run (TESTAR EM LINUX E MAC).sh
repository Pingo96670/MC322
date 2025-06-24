#!/bin/bash

# Define source and destination directories
SRC_DIR="./src"
BIN_DIR="./bin"

if [ -d "bin" ]; then
    rm -rf bin
fi


# Create the bin directory if it doesn't exist
mkdir -p "$BIN_DIR"

# Find all .java files in the src directory and subdirectories
find "$SRC_DIR" -name "*.java" > sources.txt

# Compile the Java files into the bin directory
javac -d "$BIN_DIR" -cp "$SRC_DIR" @sources.txt

# Clean up the temporary sources.txt file
rm sources.txt

echo "Compilation complete!"
read -n 1 -s -r -p "Press any key to continue"
echo

# Run Main class after compiling
java -cp bin lab/Main
