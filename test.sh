echo Building...
./build.sh

echo Testing...
MODULE=$1
case $MODULE in
    core-loop)
        echo "No test for core-loop"
        ;;
    llm)
        echo "Building and running llm module..."
        ./build.sh
        java -cp out/llm responder.ResponderTest
        ;;
    posting)
        java -cp out/requests:out/posting posting.GitHubTicketPosterTest ;;
    *)
        echo "Unknown module: $MODULE"
        echo "Available modules: core-loop, llm, responder, request"
        exit 1
        ;;
esac
