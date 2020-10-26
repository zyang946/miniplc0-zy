#!/bin/zsh

gradle fatjar --no-daemon
echo "input file is"
read input
echo "output file is"
read output
java -jar ./build/libs/miniplc0java.jar -l $input -o $output