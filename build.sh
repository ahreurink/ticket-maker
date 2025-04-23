set -e

javac -d out --module-source-path . $(find . -name "*.java")

MODULE_PATHS=$(find out -mindepth 1 -maxdepth 1 -type d | tr '\n' ':' | sed 's/:$//')

if [ "$1" = "native" ] ; then
    echo "Building native image..."
    native-image -cp $MODULE_PATHS main.Main 
    #--no-fallback -H:Name=main -H:Class=main.Main -H:+ReportExceptionStackTraces -H:ReflectionConfigurationFiles=reflection-config.json -jar out/main.jar
fi