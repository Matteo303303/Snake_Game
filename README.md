# Snake_Game.java

Snake Game in Java

This repository contains a Snake Game implemented in Java using Swing for the GUI. The project simulates the classic Snake game where the player navigates the snake to eat apples and grow longer while avoiding collisions.

## Features

Classic Snake Gameplay: Control the snake to collect apples and increase its length.

Customizable Game Field: The game operates on a 600x600 grid with 25px units.

Dynamic Snake Movement: Smooth movement and collision detection.

Scoring System: Keeps track of the number of apples eaten.

Game Over Screen: Displays score upon game termination.

## How to Play

Start the Game: Launch the Snake game by running the provided Java program.

Control the Snake: Use the following arrow keys to move:

Up Arrow: Move Up

Down Arrow: Move Down

Left Arrow: Move Left

Right Arrow: Move Right

Objective: Eat the apples and avoid crashing into the walls or the snake's own body.

## Controls

Arrow Keys: Navigate the snake.

## How to Run the Game

Ensure you have Java installed on your system.

Compile the program:

Run the game:

Project Structure

Game Logic Overview

Initialization: Sets up the game window, initializes the snake, and places the first apple.

Movement: Updates the snake's position based on player input.

Collision Detection: Checks for collisions with the walls or itself.

Apple Consumption: If the snake eats an apple, it grows in length and updates the score.

Game Over: Displays a "Game Over" message and the player's score when the game ends.

## Customization

Screen Size: Modify SCREEN_WIDTH and SCREEN_HEIGHT in GamePanel.java.

Snake Speed: Adjust the DELAY constant to change game speed.

Snake Color: Customize snake body and apple colors in the draw method.

## Dependencies

Java SE Development Kit (JDK) 8 or newer

## Contribution

Feel free to fork the repository and submit pull requests for improvements and new features.

License

This project is licensed under the MIT License. See the LICENSE file for more details.

Enjoy the game and have fun coding!
