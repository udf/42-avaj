#!/bin/sh
find -name '*.java' > sources.txt
javac -sourcepath . @sources.txt
java -classpath . za.co.wethinkcode.avaj.simulator.Simulator "$@"