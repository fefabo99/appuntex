#!/bin/bash
jflex calc.l
sleep 0.5
byaccj -J calc.y
sleep 0.1
javac Parser.java
java Parser
