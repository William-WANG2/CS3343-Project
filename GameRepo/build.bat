@ECHO OFF

IF EXIST "GREGame.jar" del GREGame.jar

cd src
javac algorithm\*.java exception\*.java fileReader\*.java game\*.java gameObject\*.java scenes\*.java util\*.java -d ..\bin 

cd ..
jar cfe GREGame.jar game.EntryPoint -C bin .


