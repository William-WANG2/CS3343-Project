# GRE GAME

This is an innovative game designed by a group of CS3343 students.  

## Role assignment

1. Code review & refactor: [WANG Xuezhen](https://github.com/William-WANG2), [ZHANG Yuyang](https://github.com/1319992808)
2. Develope Programmer: [LI Ruixin](https://github.com/Ruixin-LI), [LIU Mingyang](https://github.com/konolmyda)
3. Qulity Assurance Engineer: [HE Ruozhen](https://github.com/Catherine-R-He), [GUO Shangping](https://github.com/shangpguo2)

## Description

This is a funny game to practice your GRE vocabulary.

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

* 0.2
    * Various bug fixes and optimizations
    * See [commit change](https://github.com/William-WANG2/CS3343-Project/graphs/commit-activity)
* 0.1
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
│   │   ├── fileReaderTest.java<br>
│   │   └── nodeInfoTest.java<br>
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
