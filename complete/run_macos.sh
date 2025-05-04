# EML-CTS Build/Run Script for macos

#!/bin/bash

read -p "Do you want to build and run [br] or just build [b]? " BUILD_ONLY

# Clean the previous build
echo "Cleaning out all previous binaries"
mvn clean

if [[ $? -ne 0 ]]; then
    echo "ERROR: Project failed to clean"
    exit 1
fi

# Build the package
echo "Building the eml-cts"
mvn package

if [[ $? -ne 0 ]]; then
    echo "ERROR: Project failed to build"
    exit 1
fi

# We only wanted to build
if [[ ${BUILD_ONLY} = "b" ]]; then
    exit 0
fi

# Starting parity system in a separate terminal window
echo "Starting parity system"
open -a Terminal --args bash -c "java -jar ./system-run/parity-system.jar ./system-run/etc/system0620.conf; exec bash"

# Starting parity client in a separate terminal window
echo "Starting parity client"
open -a Terminal --args bash -c "java -jar ./client-run/parity-client.jar ./client-run/etc/client0620.conf; exec bash"

# Start the tomcat server
echo "Starting Tomcat Server"
java -jar ./target/eml-cts.jar
