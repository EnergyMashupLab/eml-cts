# EML-CTS Build/Run Script
# this is the macos run script similar to the linux run script
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
if [[ "${BUILD_ONLY,,}" == "b" ]]; then
    exit 0
fi

# Starting parity system in a separate terminal tab/window
echo "Starting parity system"
open -a Terminal.app --args -e "java -jar ./system-run/parity-system.jar ./system-run/etc/system0620.conf" &

# Starting parity client in a separate terminal tab/window
echo "Starting parity client"
open -a Terminal.app --args -e "java -jar ./client-run/parity-client.jar ./client-run/etc/client0620.conf" &

# Start the tomcat server
echo "Starting Tomcat Server"
java -jar ./target/eml-cts.jar

export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home && ./run.sh