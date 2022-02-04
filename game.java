// Run the game from the main method
public class Main {
    public static void main(String[] args) {

        // Creating an instance of Play2048 will start the game
        // Click run and read the instructions in the output before making any moves
        // NOTE: No other code is needed here!
        Play2048 game = new Play2048();

    }
}



/*
Create an instance of this class to play the game 2048
The game starts automatically once an instance is created
@author Raymond Wu
@author ICS3UG-d
@version 1.0
@since 1.0
 */
class Play2048 {
    // The 4x4 int board from which the game is played
    private int[][] board;
    // The Scanner that will read the user's input
    private java.util.Scanner input;
    // The user's current score;
    private int score;

    /*
    Class constructor
    Initializes the game board to be a 4x4 int grid with two starting pieces
    Initializes the scanner that will read in the user's input
    Initializes the score to 0
     */
    public Play2048(){
        board = new int[4][4];
        input = new java.util.Scanner(System.in);
        score = 0;

        // Place two starting pieces
        randomPiece();
        randomPiece();

        // Print the Welcome
        System.out.println("Welcome to 2048!" + "\n");
        // Call the method that is used to start the game
        start();

    }
    /*
    Prints the 4x4 game board to the output console using nested for loops
    Each row is printed on a new line in the console
    Squares with no pieces have the number 0 printed (SUBJECT TO CHANGE)
    Prints the user's score to the output console under the game board
     */
    private void display(){
        // Start with a new line for neat formatting
        System.out.println();
        // Find the length of the largest square's value for formatting
        int longest = 0;
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                longest = Math.max(longest, (board[i][j]+"").length());
            }
        }
        // Print each value in the board with formatting
        System.out.println("------------------------------");
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                // length of the current square's value
                int currLength = (board[i][j]+"").length();
                // output the current square's value
                System.out.print(board[i][j]);
                // output enough spaces
                for(int k = 0; k <= longest-currLength; k++){
                    System.out.print(" ");
                }
            }
            // new line to space out each line of output
            System.out.println();
        }
        // separate the grid and the score display
        // print the score
        System.out.println("------------------------------");
        System.out.println("SCORE: " + score);
    }

    /*
    Iterates through all 16 squares of the board to see if any squares have 2048
    A game is considered won if there is at least one square with the value 2048
    @return won either true or false depending on if 2048 is or is not on the board respectively
     */
    private boolean isWon(){
        // set won to false initially
        boolean won = false;
        // set won to true if any square is true
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(board[i][j] == 2048){
                    won = true;
                    break;
                }
            }
        }
        // return won
        return won;
    }

    /*
    Iterates through all 16 squares of the board and compares each square with all adjacent squares
    A game is considered lost if no moves exist that would alter the board state
    An alteration of the board state is either conjoinment of two or more pieces or movement of one or more pieces
    @return lost either true or false depending on if the board state can or cannot be altered respectively
     */
    private boolean isLost(){
        boolean lost = false;
        // test if no moves are possible using isValid()
        // if all four calls to isValid() with the different moves all return false, the game is lost
        if(!isValid("w")&&!isValid("a")&&!isValid("s")&&!isValid("d")){
            lost = true;
        }

        // return the value of lost, either true or false
        return lost;
    }

    /*
    Determines whether the specified move is a valid one or not
    A move is considered valid if it is either the string w, a, s or d and would alter the board state
    An alteration of the board state is either conjoinment of two or more pieces or movement of one or more pieces
    @param move a String representing the user's desired move
    @return valid either true or false depending on two criteria
    - if the move is not one of w, a, s, or d, isValid() returns false
    - if the move does not alter the board state, isValid() returns false
    - isValid() returns true otherwise
     */
    private boolean isValid(String move){
        boolean valid = false;

        // test condition 1: if the move is one of w, a ,s, or d
        if(!move.equals("w")&&!move.equals("a")&&!move.equals("s")&&!move.equals("d")){
            return valid;
        }

        // if condition 1 is passed, test condition 2
        // test if the move would alter the board state for each possible move
        if(move.equals("a")){

            for(int i = 0; i < 4; i++){

                // Test if the move is already valid
                if(valid){
                    break;
                }

                // Test if any squares are moved
                // A square will be moved if there is at least one open space in front of it in the movement direction
                for(int j = 3; j > 0; j--){
                    if(board[i][j]!=0 && board[i][j-1]==0){
                        valid = true;
                        break;
                    }
                }

                // Test if any squares are conjoined
                // Two squares will be conjoined if they have the same value and there are no intermediate non-open squares
                for(int j = 3; j > 0; j--){

                    // Test if the move is already valid
                    if(valid){
                        break;
                    }

                    // Iterate to find the next square in front of the current square to see if conjoinment can be made
                    for(int k = j-1; k >= 0; k--){
                        // Move to the next square if it is equal to 0
                        if(board[i][j] == 0){
                            break;
                        }
                        // Move to the next square if it is equal to 0
                        if(board[i][k] == 0){
                            continue;
                        }
                        // Test if the squares have equal values
                        if(board[i][k]==board[i][j]){
                            valid = true;
                            break;
                        }
                    }

                }

            }

        }

        if(move.equals("d")){

            for(int i = 0; i < 4; i++){

                // Test if the move is already valid
                if(valid){
                    break;
                }

                // Test if any squares are moved
                // A square will be moved if there is at least one open space in front of it in the movement direction
                for(int j = 0; j < 3; j++){
                    if(board[i][j]!=0 && board[i][j+1]==0){
                        valid = true;
                        break;
                    }
                }

                // Test if any squares are conjoined
                // Two squares will be conjoined if they have the same value and there are no intermediate non-open squares
                for(int j = 0; j < 3; j++){

                    // Test if the move is already valid
                    if(valid){
                        break;
                    }

                    // Iterate to find the next square in front of the current square to see if conjoinment can be made
                    for(int k = j+1; k < 4; k++){
                        // Move to the next square if it is equal to 0
                        if(board[i][j] == 0){
                            break;
                        }
                        // Move to the next square if it is equal to 0
                        if(board[i][k] == 0){
                            continue;
                        }
                        // Test if the squares have equal values
                        if(board[i][k]==board[i][j]){
                            valid = true;
                            break;
                        }
                    }

                }

            }

        }

        if(move.equals("w")){

            for(int i = 0; i < 4; i++){

                // Test if the move is already valid
                if(valid){
                    break;
                }

                // Test if any squares are moved
                // A square will be moved if there is at least one open space in front of it in the movement direction
                for(int j = 3; j > 0; j--){
                    if(board[j][i]!=0 && board[j-1][i]==0){
                        valid = true;
                        break;
                    }
                }

                // Test if any squares are conjoined
                // Two squares will be conjoined if they have the same value and there are no intermediate non-open squares
                for(int j = 3; j > 0; j--){

                    // Test if the move is already valid
                    if(valid){
                        break;
                    }

                    // Iterate to find the next square in front of the current square to see if conjoinment can be made
                    for(int k = j-1; k >= 0; k--){
                        // Move to the next square if it is equal to 0
                        if(board[j][i] == 0){
                            break;
                        }
                        // Move to the next square if it is equal to 0
                        if(board[k][i] == 0){
                            continue;
                        }
                        // Test if the squares have equal values
                        if(board[k][i]==board[j][i]){
                            valid = true;
                            break;
                        }
                    }

                }

            }

        }

        if(move.equals("s")){

            for(int i = 0; i < 4; i++){

                // Test if the move is already valid
                if(valid){
                    break;
                }

                // Test if any squares are moved
                // A square will be moved if there is at least one open space in front of it in the movement direction
                for(int j = 0; j < 3; j++){
                    if(board[j][i]!=0 && board[j+1][i]==0){
                        valid = true;
                        break;
                    }
                }

                // Test if any squares are conjoined
                // Two squares will be conjoined if they have the same value and there are no intermediate non-open squares
                for(int j = 0; j < 3; j++){

                    // Test if the move is already valid
                    if(valid){
                        break;
                    }

                    // Iterate to find the next square in front of the current square to see if conjoinment can be made
                    for(int k = j+1; k < 4; k++){
                        // Move to the next square if it is equal to 0
                        if(board[j][i] == 0){
                            break;
                        }
                        // Move to the next square if it is equal to 0
                        if(board[k][i] == 0){
                            continue;
                        }
                        // Test if the squares have equal values
                        if(board[k][i]==board[j][i]){
                            valid = true;
                            break;
                        }
                    }

                }

            }

        }

        // return the value of valid after testing
        return valid;
    }

    /*
    Tests if the specified move is a valid one or not by calling isValid()
    If the move is valid, the board state is altered accordingly
    The board state is altered by shifting all elements in the specified direction
    Then all adjacent elements with equal values are conjoined and the score is updated
    Then the board state is shifted again
    @param move a String representing the user's desired move
     */
    private void conductMove(String move){
        // Call isValid(move) to verify that the move works before proceeding
        if(!isValid(move)){
            System.out.println("That is not a valid move! Please try another one.");
            return;
        }
        // alter the board state based on the move
        if(move.equals("a")){

            for(int i = 0; i < 4; i++){

                // move any non-open squares in the specified direction
                // this is done to compress the non-open squares to make the conjoinment code easier
                for(int j = 0; j < 4; j++){
                    // move the current square to the furthest open square in the specified direction
                    for(int k = 0; k <= j; k++){
                        if(board[i][k] == 0){
                            board[i][k] = board[i][j];
                            board[i][j] = 0;
                            break;
                        }
                    }
                }

                // conjoin any possible squares
                int index = 0;
                while(index < 3){
                    if(board[i][index] == board[i][index+1]){
                        board[i][index] *= 2;
                        board[i][index+1] = 0;
                        // update the score
                        score+=board[i][index];
                        // increment index by 2 since index+1 cannot be used in a swap anymore
                        index+=2;
                    } else {
                        // increment index by 1 since index+1 may still be used in a swap
                        index++;
                    }
                }

                // move any non-open squares in the specified direction
                // this is done since conjoinment may create new open squares
                for(int j = 0; j < 4; j++){
                    // move the current square to the furthest open square in the specified direction
                    for(int k = 0; k <= j; k++){
                        if(board[i][k] == 0){
                            board[i][k] = board[i][j];
                            board[i][j] = 0;
                            break;
                        }
                    }
                }


            }

        }
        if(move.equals("d")){

            for(int i = 0; i < 4; i++){

                // move any non-open squares in the specified direction
                // this is done to compress the non-open squares to make the conjoinment code easier
                for(int j = 3; j >= 0; j--){
                    // move the current square to the furthest open square in the specified direction
                    for(int k = 3; k >= j; k--){
                        if(board[i][k] == 0){
                            board[i][k] = board[i][j];
                            board[i][j] = 0;
                            break;
                        }
                    }
                }

                // conjoin any possible squares
                int index = 3;
                while(index > 0){
                    if(board[i][index] == board[i][index-1]){
                        board[i][index] *= 2;
                        board[i][index-1] = 0;
                        // update the score
                        score+=board[i][index];
                        // decrement index by 2 since index-1 cannot be used in a swap anymore
                        index-=2;
                    } else {
                        // decrement index by 1 since index-1 may still be used in a swap
                        index--;
                    }
                }

                // move any non-open squares in the specified direction
                // this is done to compress the non-open squares to make the conjoinment code easier
                for(int j = 3; j >= 0; j--){
                    // move the current square to the furthest open square in the specified direction
                    for(int k = 3; k >= j; k--){
                        if(board[i][k] == 0){
                            board[i][k] = board[i][j];
                            board[i][j] = 0;
                            break;
                        }
                    }
                }


            }

        }
        if(move.equals("w")){

            for(int i = 0; i < 4; i++){

                // move any non-open squares in the specified direction
                // this is done to compress the non-open squares to make the conjoinment code easier
                for(int j = 0; j < 4; j++){
                    // move the current square to the furthest open square in the specified direction
                    for(int k = 0; k <= j; k++){
                        if(board[k][i] == 0){
                            board[k][i] = board[j][i];
                            board[j][i] = 0;
                            break;
                        }
                    }
                }

                // conjoin any possible squares
                int index = 0;
                while(index < 3){
                    if(board[index][i] == board[index+1][i]){
                        board[index][i] *= 2;
                        board[index+1][i] = 0;
                        // update the score
                        score+=board[index][i];
                        // increment index by 2 since index+1 cannot be used in a swap anymore
                        index+=2;
                    } else {
                        // increment index by 1 since index+1 may still be used in a swap
                        index++;
                    }
                }

                // move any non-open squares in the specified direction
                // this is done to compress the non-open squares to make the conjoinment code easier
                for(int j = 0; j < 4; j++){
                    // move the current square to the furthest open square in the specified direction
                    for(int k = 0; k <= j; k++){
                        if(board[k][i] == 0){
                            board[k][i] = board[j][i];
                            board[j][i] = 0;
                            break;
                        }
                    }
                }

            }

        }
        if(move.equals("s")){

            for(int i = 0; i < 4; i++){

                // move any non-open squares in the specified direction
                // this is done to compress the non-open squares to make the conjoinment code easier
                for(int j = 3; j >= 0; j--){
                    // move the current square to the furthest open square in the specified direction
                    for(int k = 3; k >= j; k--){
                        if(board[k][i] == 0){
                            board[k][i] = board[j][i];
                            board[j][i] = 0;
                            break;
                        }
                    }
                }

                // conjoin any possible squares
                int index = 3;
                while(index > 0){
                    if(board[index][i] == board[index-1][i]){
                        board[index][i] *= 2;
                        board[index-1][i] = 0;
                        // update the score
                        score+=board[index][i];
                        // decrement index by 2 since index-1 cannot be used in a swap anymore
                        index-=2;
                    } else {
                        // decrement index by 1 since index-1 may still be used in a swap
                        index--;
                    }
                }

                // move any non-open squares in the specified direction
                // this is done to compress the non-open squares to make the conjoinment code easier
                for(int j = 3; j >= 0; j--){
                    // move the current square to the furthest open square in the specified direction
                    for(int k = 3; k >= j; k--){
                        if(board[k][i] == 0){
                            board[k][i] = board[j][i];
                            board[j][i] = 0;
                            break;
                        }
                    }
                }


            }

        }


    }

    /*
    Places a piece of either value 2 or 4 on an unoccupied square of the board
    The value 2 or 4 is randomly generated with equal probability
    The target square is randomly generated, with each square having equal probability of being the target
     */
    private void randomPiece(){
        // randomly generate either 2 or 4 using Math.random()
        int value = 2*(int)(2*Math.random()+1);

        // randomly select a square using Math.random()
        // randomly select values from 0-3 inclusive for row and col
        int row = -1;
        int col = -1;
        row = (int)(Math.random()*4);
        col = (int)(Math.random()*4);
        // continue assign random values to row and col until an empty square is chosen
        while(board[row][col] != 0){
            row = (int)(Math.random()*4);
            col = (int)(Math.random()*4);
        }
        // assign the randomly chosen value to the chosen square
        board[row][col] = value;
    }

    /*
    Starts the game by first introducing the rules and prompting the user to continue
    Continues to prompt the user for their next move until the game is lost or the user quits
     */
    private void start(){
        // Print the Instructions
        System.out.println("HOW TO PLAY: ");
        System.out.println("Use WASD to move the squares.");
        System.out.println("Squares with the same number will be conjoined when they touch.");
        System.out.println("A random square will be generated after every valid move");
        System.out.println("Get a square with 2048 to win the game!");
        System.out.println("Try to win with as high a score as possible!");


        // Store the user's next move
        String move = "";

        // Continue prompting and retrieving the user's next move
        // As long as the game has not been lost or won, and the user did not ask to quit
        while(!isLost() && !isWon()){
            // Display the current board state
            display();
            // Prompt the user for their next move
            System.out.println("What is your next move?");
            System.out.println("Enter q to quit");
            System.out.println("Enter w to shift up");
            System.out.println("Enter a to shift left");
            System.out.println("Enter s to shift down");
            System.out.println("Enter d to shift right");
            System.out.println("------------------------------");

            // Try to retrieve the user's next move if it is valid
            // If the user enters exotic commands that would crash the program, state that the move is invalid
            try{
                // Retrieve the user's next move
                move = input.nextLine();
            } catch(Exception e){
                // Catch the exception and exit the game
                move = "q";
            }

            // set the move to lowercase so case doesn't matter
            move = move.toLowerCase();
            // See if the move is a request to quit
            if(move.equals("q")){
                break;
            }
            // See if the move is valid
            boolean works = isValid((move));
            // Conduct the move
            // Do not have to consider the boolean variable works since conductMove has a built-in validity test
            conductMove(move);
            // Place a new random square if the move was valid
            if(works){
                randomPiece();
            }
        }

        // Test if game was won, lost, or exited
        if(isWon()){
            System.out.println("Victory! Woo hoo!");
        } else if(isLost()){
            System.out.println("Defeat... Better luck next time!");
        } else {
            System.out.println("User asked to quit. Exiting game.");
        }

    }



}
