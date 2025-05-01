# Java Snake Game

A classic Snake Game implementation in Java using Swing for GUI. This project demonstrates Object-Oriented Programming principles and basic game development concepts.

![Snake Game Screenshot](https://github.com/StreetOfCode/snake-java/blob/master/src/screenshots/game.jpg)

## Features

- Complete Snake game with intuitive controls
- Object-Oriented design with separate classes for game components
- Score tracking
- Game over screen with restart option
- Clean, readable code with comments

## Technologies Used

- Java
- Swing (for GUI)
- OOP principles

## How to Play

1. **Controls**:
    - Use the **arrow keys** (↑, ↓, ←, →) to control the snake's direction
    - Press **space** to restart the game after game over

2. **Game Rules**:
    - Eat the red food to grow and earn points
    - Avoid hitting the walls or your own tail
    - The game gets progressively faster as your score increases

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Any Java IDE (IntelliJ IDEA recommended)

### Installation and Running

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/snake-game.git
   ```

2. Open the project in your IDE

3. Run the main class `SnakeGame.java`

Alternatively, you can compile and run from the command line:
```bash
javac SnakeGame.java
java SnakeGame
```

## Project Structure

- `SnakeGame.java` - Main class with JFrame setup
- `GamePanel.java` - The game panel where the game is rendered
- `Snake.java` - Class representing the snake with its properties and behaviors
- `Food.java` - Class representing the food items
- `Direction.java` - Enum for snake's direction

## Learning Outcomes

This project demonstrates:
- Implementation of game loops using Swing Timer
- Handling user input in Java applications
- Collision detection algorithms
- Basic game state management
- Object-Oriented design principles

## Acknowledgments

- Based on the classic Snake game
- Created as an educational project to teach Java programming
- Special thanks to IntelliJ IDEA for their excellent Java development environment

---

Feel free to fork this project and make your own improvements.

## Connect With Us

[YouTube Channel](https://www.youtube.com/@streetofcode)