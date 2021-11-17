@ECHO OFF

IF EXIST "GREGame.jar" (
	java -jar GREGame.jar
)ELSE (
	build.bat
	Run.bat
)