import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class SnakeAndLadder {
    static final int WINNING_POSITION = 100;
    static Map<Integer, Integer> snakes = new HashMap<>();
    static Map<Integer, Integer> ladders = new HashMap<>();
    
    static {
        // Define snakes
        snakes.put(98, 28);
        snakes.put(95, 56);
        snakes.put(92, 51);
        snakes.put(83, 19);
        snakes.put(73, 1);
        snakes.put(69, 33);
        snakes.put(64, 36);
        snakes.put(59, 17);
        snakes.put(55, 7);
        snakes.put(52, 11);
        
        // Define ladders
        ladders.put(2, 23);
        ladders.put(6, 45);
        ladders.put(20, 59);
        ladders.put(30, 88);
        ladders.put(52, 72);
        ladders.put(57, 96);
        ladders.put(71, 91);
        ladders.put(79, 99);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int player1Pos = 0, player2Pos = 0;
        boolean player1Turn = true;
        
        System.out.println("Welcome to Snake and Ladder Game!");
        
        while (true) {
            System.out.println((player1Turn ? "Player 1" : "Player 2") + "'s turn. Press enter to roll the dice.");
            scanner.nextLine();
            
            int diceRoll = random.nextInt(6) + 1;
            System.out.println("Rolled: " + diceRoll);
            
            if (player1Turn) {
                player1Pos = movePlayer(player1Pos, diceRoll);
                System.out.println("Player 1 is now at position: " + player1Pos);
                if (player1Pos == WINNING_POSITION) {
                    System.out.println("Player 1 wins!");
                    break;
                }
            } else {
                player2Pos = movePlayer(player2Pos, diceRoll);
                System.out.println("Player 2 is now at position: " + player2Pos);
                if (player2Pos == WINNING_POSITION) {
                    System.out.println("Player 2 wins!");
                    break;
                }
            }
            
            player1Turn = !player1Turn;
        }
        
        scanner.close();
    }
    
    private static int movePlayer(int position, int diceRoll) {
        if (position + diceRoll > WINNING_POSITION) {
            return position;  // Ignore move if it exceeds 100
        }
        
        position += diceRoll;
        
        if (snakes.containsKey(position)) {
            System.out.println("Oh no! A snake bites! Moving down from " + position + " to " + snakes.get(position));
            position = snakes.get(position);
        } else if (ladders.containsKey(position)) {
            System.out.println("Yay! A ladder! Climbing up from " + position + " to " + ladders.get(position));
            position = ladders.get(position);
        }
        
        return position;
    }
}
