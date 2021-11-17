JAVAC=javac

JAR_NAME=GREGame.jar

new: 
	mkdir -pv bin

clean: 
	rm -rf bin $(JAR_NAME) 

build: 
	$(JAVAC) -d bin src/*/*.java

rebuild: clean new build

jar: 
	jar cfe $(JAR_NAME) game.EntryPoint -C bin .