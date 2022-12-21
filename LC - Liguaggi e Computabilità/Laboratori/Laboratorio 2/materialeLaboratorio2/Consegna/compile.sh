#!/bin/bash
jflex infDyck.l
byaccj -J infDyck.y
javac Parser.java
java Parser
