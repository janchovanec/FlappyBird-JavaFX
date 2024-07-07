# Overview
This project is a recreation of the popular Flappy Bird game, developed using Java with Maven and JavaFX. 
The objective of the game is to navigate the bird through the pipes without colliding with them. This project was 
created as a school assignment to demonstrate proficiency in Java programming.

# Installation
## Prerequisites
Before you can run the project, ensure you have the following installed on your system:
Java Development Kit (JDK) 11 or higher
Maven 3.6.0 or higher

## Steps to Install
Clone the repository:
```
git clone https://github.com/janchovanec/FlappyBird-JavaFX.git
cd flappy-bird-clone
```

Use Maven to build the project and resolve dependencies:
```
mvn clean install
```

After building the project, you can run the game using the following Maven command:
```
mvn javafx:run
```
# Usage
## Controls
Press the SPACE bar to make the bird flap its wings and rise.
The bird will automatically fall due to gravity when no input is provided.
Avoid the pipes to keep scoring points.
## Game Over
The game ends when the bird collides with a pipe or the ground.
The final score will be displayed on the screen.
