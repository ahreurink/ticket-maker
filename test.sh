echo Building...
./build.sh

MODULE_PATHS=$(find out -mindepth 1 -maxdepth 1 -type d | tr '\n' ':' | sed 's/:$//')
echo Testing...
MODULE=$1
case $MODULE in
    main)
        echo "No test for main"
        ;;
    llm)
        echo "Building and running llm module..."
        ./build.sh
        java -cp $MODULE_PATHS responder.ResponderTest
        ;;
    posting)
        java -cp $MODULE_PATHS posting.GitHubTicketPosterTest
        java -cp $MODULE_PATHS posting.JsonBuilderTest ;;
    *)
        echo "Unknown module: $MODULE"
        echo "Available modules: $(find out -mindepth 1 -maxdepth 1 -type d | sed 's/out\///' | tr '\n' ' ')"
        exit 1
        ;;
esac
