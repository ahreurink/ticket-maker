#javac -d out llm/src/main/java/llm/Responder.java core-loop/src/main/java/Main.java
javac -d out --module-source-path . $(find . -name "*.java")
