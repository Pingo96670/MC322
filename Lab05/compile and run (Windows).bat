@echo off
rem Check if the bin directory exists. If it does, delete it
if exist bin (
    rmdir /s /q bin
)

rem Creates a fresh bin directory
mkdir bin

rem Find all .java files in the src directory and its subdirectories
for /r src %%i in (*.java) do (
    rem Strip the src directory from the path and compile to the bin folder
    javac -d bin -cp src %%i
)

echo Compilation complete.
pause
echo.

rem Run Main class after compiling
java -cp bin lab/Main