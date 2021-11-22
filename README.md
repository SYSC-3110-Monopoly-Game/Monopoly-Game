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

# User Manual
    
This is a Simplify Monopoly Game.
The game interface consists of a ‘board game’ section and an ‘information’ section.

Players need to roll dices and move to next square depend on those dices. Different squares have different functions.
A player wins the game when all other players go bankrupt.


<details><summary>Board Game Section</summary>   
    <details><summary>Squares</summary>
        <details><summary>Property Square</summary>
    
- There are ‘street name’, ‘price’, ‘color tag’ and ‘buildings area’ in each Property Square.
- When players get to this kind of squares:
    - They can buy the square, if it does not have an owner.
    - They need to pay for the rent, if this square is owned by another player.
        - The rent price for that square depend on the number of buildings on it.
    - They can ‘sell’ or ‘build houses’ after they own that land.
</details> 
         <details><summary>Go Square</summary>
             
- The starting square when the game starts.
- When players pass or reach it again, they will get $50.
</details> 
         <details><summary>Income Tax Square</summary>
             
- Players need to pay the tax ($ 100) when they get to this square.
</details>
         <details><summary>Rail Road Square</summary>
             
- When players get to this kind of squares:
    - They can buy the square, if it does not have an owner.
    - They need to pay for the rent, if this square is owned by another player.
    - House cannot be built here!
</details> 
         <details><summary>Jail Square</summary>
             
- When player gets to the jail, he/she can only move out of the jail in two ways:
    1. Player rolls an even number.
    2. Player already stayed in the jail for 3 rounds.             
</details> 
         <details><summary>Utility Square</summary>
             
- When players get to this kind of squares:
    - They can buy the square, if it does not have an owner.
    - They need to pay for the rent, if this square is owned by another player.
    - House cannot be built here!               
</details>  
         <details><summary>Free Parking Square</summary>
             
- Players do not need to do anything with this square.             
</details> 
         <details><summary>Go To Jail Square</summary>
             
- Players will be directly sent to ‘Jail Square’ when they get to ‘Go To Jail Square’.             
</details>
</details> 
    <details><summary>Houses and Hotels</summary>   

- House: indicates by green square.
- Hotel: indicates by red square.
- Buildings can only be built on Property Square! 
- In this Monopoly Game: 
    - At most four houses and one hotel can be built on a Property Square.       
    - If player chooses to build a hotel before owning four houses on that Property Square, the number of houses of the square will increase to 4.    
        - (e.g., Player A already had a house on ‘Baltic Avenue’ and he wants to build a hotel there. He will have 4 houses and a hotel on ‘Baltic Avenue’ after he pressing the button for ‘build a hotel’.) 
    - Player can build houses or hotels, when he/she owns all Property Squares of the same color.       
        - (e.g., when player A owns ‘Baltic Avenue’ and ‘Mediterranean Avenue’, he/she can start to build houses or hotel on Property Square)         
</details>       
</details> 
    
<details><summary>Information Section</summary>    
- Information Section is made up by five sections. (‘Players’ information’, ‘Current Player’s Information’. ‘Current Location’, ‘Square Information’ and ‘Buttons’)
    <details><summary>Players' Information</summary>
            
- List of players who are not in current.
    - Showing their ‘name’ and ‘cash’.
</details> 
    <details><summary>Current Players' Information</summary>
        
-  Showing current player’s information.
    - ‘Name’, ‘cash’ and ‘player owned property’.
</details>
    <details><summary>Current Location</summary>
        
- Showing the location of current player.
</details>
    <details><summary>Square Information</summary>
        
- Showing current square’s information.
    - ‘Buy Price’, ‘Rent Price’, ‘House Price’ and ‘Hotel Price’.
</details>
    <details><summary>Buttons</summary>
 - Buttons Section including six buttons: ‘Buy’, ‘Sell’, ‘Roll Dice’, ‘Next Turn’, ‘Build On Property’ and ‘Sell Houses/Hotels’.       
        <details><summary>Buy Button</summary> 
            
- Click to buy the current square.
</details>
       <details><summary>Sell Button</summary>
           
- Click to sell the last bought square.
</details>
       <details><summary>Roll Dice Button</summary>
           
- Click to roll dices.
  </details>
       <details><summary>Next Turn Button</summary>
           
- Click to pass the turn to next player.
 </details>
       <details><summary>Build On Property Button</summary>
           
- Click to open a new window to choose the square you want to build houses/hotel.
    - After you clicking the square 
        - There is a new window for you to choose the building type (Houses/Hotel).
            - Now, click ‘House’ (Hotel) if you want to build a house (hotel).
</details>
       <details><summary>Sell Houses/Hotels Button</summary>
           
- Click to open a new window show the square(s) you have houses/hotel on. Then choose you want to sell houses/hotel there.
        - After you clicking the square 
            - There is a new window for you to choose the building type (Houses/Hotel) you can sell.
                - Now, click ‘House’ (Hotel) if you want to sell a house (hotel).       
</details>
</details>     
</details>      
    
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

