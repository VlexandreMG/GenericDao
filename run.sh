#!/bin/bash 

javac -cp ".:lib/*"  -d . *.java

# echo "Fin de la compilation"
 java -cp ".:lib/*" Main