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

<details><summary>Milestone 2</summary>
    
# Milestone 2

## Folders/Files added

* src
    - Model
        + main.java
        + FreeParkingSquare.java
        + GoSquare.java
        + GoTOJailSquare.java
        + IncomeTaxSquare.java
        + JailSquare.java
        + RailRoadSquare.java
        + UtilitySquare.java
    - view
        + DiceGUI.java
        + FreeParkingSquare.java
        + GoSquareGUI.java
        + GoToJailGUI.java
        + IncomeTaxSquareGUI.java
        + InfoDisplayGUI.java
        + JailSquareGUI.java
        + MonopolyGameGUI.java
        + playerGUI.java
        + PropertySquareGUI.java
        + RailRoadSquareGUI.java
        + SquareGridGUI.java
        + SquareGUI.java
        + UtilitySquareGUI.java
    - test
        + DiceTest.java
        + FreeParkingSquareTest.java
        + GoSquareTest.java
        + GoToJailSquareTest.java
        + incomeTaxSquareTest.java
        + JailSquareTest.java
        + MonopolyBoardTest.java
        + MonopolyGameTest.java
        + PlayerTest.java
        + PropertySquareTest.java
        + RailRoadSquareTest.java
        + UtilitySquareTest.java
    - Controller
        + MonopolyGameController.java
    - UML and Sequence Diagrams
        - Milestone2

   

## Team Responsibilities

Liya Abebe

- Implemented FreeParkingGUI class
- Implemented GoToJailGUI class
- Implemented GoSquareGUI class
- Implemented IncomeTaxSquareGUI class
- Implemented JailSquareGUI class
- Implemented PropertySquareGUI class
- Implemented RailRoadSquareGUI class
- Implemented UtilitySquareGUI class
- Implemented PlayerGUI class
- Implemented InfoDisplayGUI class
- Implemented MonopolyGameGUI class
- Implemented SquareGridGUI class
- Implemented DiceTest class
- Implemented MonopolyBoardTest class
- Implemented MonopolyGameController class
- Refactored MonopolyGame class
- Refactored main class
 
Rebbeca Li
    
- Refactored InfoDisplayGUI class
- Refactored DiceGUI class
- Draw the UML of the whole project

Shizhong Shang
  
- Refactored Player class
- Implemented PlayerTest class
- Implemented FreeParking class
- Implemented GoToJail class
- Implemented GoSquare class
- Implemented IncomeTaxSquare class
- Implemented JailSquare class
- Implemented RailRoadSquare class
- Implemented UtilitySquare class
- Sequence diagrams

Zirui Qiao

- Refactored MonopolyGame class
- Refactored JailSquare class
- Refactored all tests excepted for diceTest
- Implemented MonopolyGameTest class
- Implemented main class
- Implemented SquareGUI class
- Implemented buyProperty() and sellProperty() methods in MonopolyGame class
- Add sellProperty() method to Player class
- Refactored the landOn() method in PropertySquare class
- Refactored the landOn() method in GoToJailSquare class
- Refactored the makeSquares() method in MonopolyBoard class
- Provide a new method(no use after discussion) to SquareGridGUI class
- README file documentation


## Detailed Set Up
    
We separate the whole game to 5 packages. The packages are 'Model', 'view', 'tests', 'Controller' and 'images'.
In order to move game to GUI version, the game is designed to have a MVC pattern. Package 'Model' is the model
part in MVC pattern; package 'view' is the view part in MVC pattern; package 'Controller' is the controller
part in MVC pattern, package 'test' contains tests for all classes in 'Model' and package 'images' contains 
images will be used in 'view'.
    
Model:
    New classes which represents special types of squares are added. The classes are 'FreeParkingSqaure.java', 
    'GoSquare.java', 'GoToJailSquare.java', 'IncomeTaxSquare.java', 'JailSquare.java', 'RailRoadSquare.java' 
    and 'UtilitySquare.java'. 'RailRoadSquare.java' and 'UtilitySquare.java' are children of 'PropertySquare.java' 
    because all of them can be buy and sell. All of the rest new classes implement 'Square.java' class.
    A 'main' class is also added which helps to arrange MVC pattern. New rules are included in the game 
    compared to milstone1, rules about jail and selling properties when getting bankrupt are implemented 
    in game. In order to write the test easier and match with the MVC pattern, multiple methods in 'MonopolyGame' 
    are splited into new methods. All other classes remain their original functions.
    
view:
    New classes are created to do visual part of the game. The classes which represent the squares' view are 
    'FreeParkingSquareGUI.java', 'GoSquareGUI.java', 'GoToJailGUI.java', 'IncomeTaxSquareGUI.java', 
    'JailSquareGUI.java', 'PropertySquareGUI.java', 'RailRoadSquareGUI.java', 'UtilitySquareGUI.java' and 
    'SquareGUI.java'. We designed `SquareGUI.java` to be an abstract class just like in milestone1. Then, each
    class is responsible for a type of square in package 'Model'. 'DIceGUI.java' and 'PlayerGUI.java' are 
    also created in responsible for the visualization of 'Dice.java' and 'Player.java'. 'InfoDisplayGUI.java'
    is created to display the information of the player who is currently playing the round. 'SquareGridGUI.java'
    is created to draw the map of the game. Finally, 'MonopolyGameGUI.java' is created to bring all view part 
    together.
 
controller:
    New class 'MonopolyGameController.java' is created in responsible for actionListeners of buttons created in
    package 'view'.
    
test:
    For each class in package 'Model', a test class is created to test all testable methods in the class. 
    For example, 'DiceTest.java' to test all methods in 'Dice.java'.


## Known Issues/bugs

When a player that's not in jail rolls a double, the game automatically
rolls the dice again instead to waiting for an input from the 'Roll Dice' button.
It sends the player to jail if dice rolls a double 3 times. In the view, only the last dice roll is shown.
Ideally we would want to wait for user input to roll dice again when doubles are rolled and display
all the dice pairs, not just the last rolled pair.
</details>

<details><summary>Milestone 2</summary>
    
# Milestone 3

## Folders/Files added

* src
    - Model
        + AIPlayer.java
    - view
        + SimpleSquareGUI.java
    - test
        + AIPlayertest.java
    - UML and Sequence Diagrams
        - Milestone3

   

## Team Responsibilities

Liya Abebe

- 
 
Rebbeca Li
    
- 

Shizhong Shang
  
- Implemented AIPlayer class
- improved the UML diagram 
- Improve the sequence diagram PressButtonBuy_Action
- Improve the sequence diagram PressButtonSell_Action
- Improve the sequence diagram PressButtonRollDice_Action
- Improve the sequence diagram PressButtonSell_HouseOrHotel_Action
- Improve the sequence diagram PressButtonBuild_On_Property_Action
- Improve the sequence diagram PressButtonNextTurn_Action
- improved the Jail class
- Add AIPlayer in the MonopolyGame class

Zirui Qiao

- Implemented SimpleSquareGUI class
- Improved GUI code in all GUI square by merging repeated codes into SimpleSquareGUI class
- Improved the map of the game by using new method to generate map GUI
- refresh the GUI information more frequently by adding details in MonopolyGameGUI class
- Improved the code in MonopolyGameGUI class by packing redundant codes into methods
- Fixed the bug that when player trying to buy properties when lacking of money, wrong message
were shown.
- Included bankrupt feedback
- Write nearly all javadocs in package view
- Created getDecision() and HotelOrHouse() in MonopolyGameGUI class
- Packing redundant codes into setButtons(), setPriceInfo() and sellBuildBuilding() in
MonopolyGameGUI class
- Created inner classes houses and hotels in PropertySquare class
- Implemented buildHouse(), hasHouses(), getHousePrice(), sellHouse(), buildHotel(), getHotelPrice(), hasHotel(), 
sellHotel() in PropertySqaure class
- Implemented calculateRentFee() in PropertySquare class to get higher rent fee when the owner of this square 
owns all other squares with the same color(or more than 1 railroad and utility), the price also increase when 
there are houses or hotel built on this square.
- Refactoed AIPlayer so that it can run properly
- Implemented AIPlayertest class
- Updated all tests

In Player class:
- Implemented hasWholeSet() which return all properties in 1 or more whole sets owned by the player
- Implemented hasBuilding() which return all properties with houses or hotel
- Implemented countNumber() which gives the number the squares (owned by the player) with a given color
- Implemented getAvailableProperties() which remove all properties that the player does not has enough money to 
build at least a house on it from the given property list
- Implemented buildH() which builds a house or a hotel on the selected square according to parameter passed
- Implemented sellH() which sells a house or a hotel from the selected square according to parameter passed

In MonopolyGame class:
- Implemented checkAvailableBuild() and checkAvailableSell() which build or sell buildings on the selected square
- Implemented AIProcess() which runs the process of AI when it is its turn



## Detailed Set Up
    
In this Milestone, we focused on 3 parts: fixing existing bugs and features; implementing build/sell houses/hotel
and its GUI; implementing AI players. The existing bugs and features include: incorrect feedbacks; feature lost; 
Misunderstanding of the rules; few bugs and UML&sequence diagrams. Implementing build/sell houses/hotel and its GUI
include: implementing the logic of building/selling a house/hotel; getting decision from human players and showing 
houses/hotel changes on the GUI map. Implementing AI players include: implementing AI logic, fitting AI to the game
(how AI use original methods and how to fit the original loops) and showing AI on information tab and the GUI map.
Some of codes are be complicated because we want to fit MVC pattern as good as possible.


## Known Issues/bugs

If player rolls a double and lands on go to jail, the double lets them get out of jail.

</details>
