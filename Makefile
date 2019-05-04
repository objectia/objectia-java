.PHONY: test build doc

test:
	mvn test -f ./pom.xml

build:
	mvn compile -f ./pom.xml
	
doc: 
	javadoc -public -doctitle "Java client for Objectia API" -d ./doc ./src/main/java/com/objectia/*.java ./src/main/java/com/objectia/exceptions/*.java ./src/main/java/com/objectia/models/*.java
