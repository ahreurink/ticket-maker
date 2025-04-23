PROMPT="make the music get more intense as the forest grows more dense, \n liek you hear more birds and shit"
MODULE_PATHS=$(find out -mindepth 1 -maxdepth 1 -type d | tr '\n' ':' | sed 's/:$//')
echo  $PROMPT | java -cp $MODULE_PATHS main.Main
