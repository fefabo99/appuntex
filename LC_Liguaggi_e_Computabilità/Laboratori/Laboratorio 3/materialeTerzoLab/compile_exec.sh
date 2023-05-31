#!/bin/bash
jflex simple.l
byaccj -J simple.y
javac Parser.java
