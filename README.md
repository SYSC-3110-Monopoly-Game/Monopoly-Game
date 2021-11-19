# Monopoly Game

## Contents

- Project Description
- Set Up and Test Instructions
- Authors
- Milestones
    - Milestone 1
        - Folders/Files included
        - Team Responsibilities
        - Detailed Set Up
        
# Project Description

This project is a design and implementation of a simplified version of the classic boardgame Monopoly.
The game is simplified by eliminating the Chance/Community chest cards and by not allowing mortgages nor sales 
and trades between players.The cost of rent, houses and hotels will be simplified to represent fixed percentages of 
the initial cost of the property.

# Set Up and Test Instructions

1. Download jar file and unzip
2. Open MonopolyGame.java and run class

# Authors

- Liya Abebe
- Rebbeca Li
- Shizhong Shang
- Zirui Qiao

# Milestones

<details><summary>Milestone 1</summary>

# Milestone 1

## Folders/Files added

* src
    - Model
        + Dice.java
        + MonopolyBoard.java
        + MonopolyGame.java
        + Player.java
        + PropertySquare.java
        + Square.java
    - UML and Sequence Diagrams
        + ask if the player want to buy square.png
        + check if the player is bankrupt.png
        + let the player move to the next square.png
        + the game needs to add in a new player.png
        + M1-Console output.pdf
        + Milestone1UML.png

    


## Team Responsibilities

Liya Abebe

- Implemented Dice class
- Implemented MonopolyBoard class
- Refactored print statements
- README file documentation
 
Rebbeca Li
    
- Implemented Square class
- Implemented PropertySquare class
- Updated UML

Shizhong Shang
  
- Implemented Player class
- Sequence diagrams

Zirui Qiao

- Implementing MonopolyGame class
- Implementing ifWantToBuy method and selectWhichToSell method in class Player
- Fixed buyProperty method in class Player
- Fixed landOn method in class PropertySquare
- Fixed toString method in class Square


## Detailed Set Up

We created classes to represent the main entities of the game. The classes are
`Dice.java`, `MonopolyBoard.java`, `MonopolyGame.java`, `Player.java`, `PropertySquare.java` and `Square.java`. 
We designed `Square.java` to be an abstract class since there are several variety of square types
in the game. Then each square type will implement the `Square.java` class, just like how `PropertySquare.java`
has done in this milestone. `MonopolyBoard.java` creates all the squares in the game using the square classes.
Finally, the `MonopolyGame.java` brings everything together by creating the board and players and running the game.

</details>


