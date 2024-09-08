#!/bin/bash

export MAVEN_HOME=/usr/local/Cellar/maven/3.x.x/libexec
export JAVA_HOME=/usr/local/opt/openjdk

# Ensure Maven and JDK are installed
if [ ! -d "$MAVEN_HOME" ]; then
    echo "Maven is not installed. Please install Maven using Homebrew: 'brew install maven'."
    exit 1
fi

if [ ! -d "$JAVA_HOME" ]; then
    echo "JDK is not installed. Please install JDK using Homebrew: 'brew install openjdk'."
    exit 1
fi

$MAVEN_HOME/bin/mvn clean test

if [ $? -eq 0 ]; then
    echo "Tests completed successfully."
else
    echo "An error occurred during tests."
fi

if [ -d "src/reports" ]; then
    echo "Test reports are located in the src/reports directory."
else
    echo "Test reports directory not found."
fi

if [ -d "src/screenshots" ]; then
    echo "Screenshots are located in the src/screenshots directory."
else
    echo "Screenshots directory not found."
fi
