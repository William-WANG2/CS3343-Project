# Kunkun Loves English

This is an innovative game designed by a group of CS3343 students.  

## Description

This is a funny game to practice your vocabulary.

## Getting Started

### Dependencies

* Windows 7/8/10, MacOS
* Java Runtime Environment
* Java SE 1.8

### Installing

Download the source code from [Project page](https://github.com/William-WANG2/CS3343-Project) and Unzip
*or* download by git clone,
```
git clone https://github.com/William-WANG2/CS3343-Project
```

### Executing program

Go to application folder,

```
cd CS3343-Project/GameRepo
```

To create new bin folder, 
```
make new 
```

To compile the whole project,    
```
make build
```

To pack all classes into one JAR, 
```
make jar
```

To delete all compiled classes and JAR, 
```
make clean
```

To rebuild the whole project, 
```
make rebuild
```

To run the jar file
```
java -jar GREGame.jar
```

For Windows users, double click build.bat file to compile and generate jar file. Then double click Run.bat to run the application. (Notice that you should first add your jdk to the environment path on Windows)


## Version Control System

Git

## Version History
* 3.0
    * UI design and Scene design
* 2.0
    * Various bug fixes and optimizations
    * See [commit change](https://github.com/William-WANG2/CS3343-Project/graphs/commit-activity)
* 1.0
    * Initial Release

## Project Structure
├── src<br>
│   ├── algorithm<br>
│   │   └── ShortestPath.java<br>
│   ├── exception<br>
│   │   └── ExMapExceedWordfSize.java<br>
│   ├── fileReader<br>
│   │   ├── Crypto.java<br>
│   │   ├── ReaderApp.java<br>
│   │   ├── ReaderFactory.java<br>
│   │   ├── TxtReader.java<br>
│   │   └── XMLReader.java<br>
│   ├── game<br>
│   │   ├── EntryPoint.java<br>
│   │   └── GREGame.java<br>
│   ├── gameObject<br>
│   │   ├── Board.java<br>
│   │   ├── Character.java<br>
│   │   ├── GameButtton.java<br>
│   │   ├── GameResult.java<br>
│   │   ├── Map.java<br>
│   │   ├── MapNode.java<br>
│   │   ├── MapNodeInfo.java<br>
│   │   ├── MusicController.java<br>
│   │   ├── WordInfo.java<br>
│   │   └── WordType.java<br>
│   ├── junitTest<br>
│   │   ├── Dio_recomputeShorlestPath_test.java<br>
│   │   ├── ReaderApp_getList_test.java<br>
│   │   ├── Scene_enter_test.java<br>
│   │   ├── Scene_exit_test.java<br>
│   │   ├── Scene_render_test.java<br>
│   │   ├── Scene_update_test.java<br>
│   │   └── Main_test.java<br>
│   ├── scenes<br>
│   │   ├── LoginScene.java<br>
│   │   ├── PlayingScene.java<br>
│   │   ├── ResultScene.java<br>
│   │   ├── RuleScene.java<br>
│   │   └── Scene.java<br>
│   └── util<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── BoundingBox.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── BoundingCircle.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── GameApplication.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── GameTimer.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── Geometry.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── GlobalConstants.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── Key.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── Mouse.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── Music.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── ResourceLoader.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── Texture.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── Transform.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;├── Vector2d.java<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└── Vector2f.java<br>
