@echo off


:: Navigate to system-run directory and start parity-system.jar in a new window
cd system-run\
start "Parity System" cmd /k "java -jar parity-system.jar etc\system0620.conf"
echo Started Parity System...
timeout /t 5

:: Check if the Parity System (Order Entry service on port 4000) is running
:check_port
echo Checking if Parity System is listening on port 4000...
netstat -an | findstr :4000
if errorlevel 1 (
    echo Parity System is not yet ready, waiting...
    timeout /t 5
    goto check_port
)
echo Parity System is ready.

:: Navigate to client-run directory and start parity-client.jar in a new window
cd ..\client-run
start "Parity Client" cmd /k "java -jar parity-client.jar etc\client0620.conf"
echo Started Parity Client...
timeout /t 5

:: Navigate to target directory and run eml-cts.jar in the current window
cd ..\target
echo Starting eml-cts.jar...
java -jar eml-cts.jar