
import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'}, {' ', '|', ' ', '|', ' '}};
        printGameBoard(gameBoard);

        while (true) {
            //player moves
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter position between (1-9) : ");
            int playerPos = sc.nextInt();
            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Wrong position!! \n please insert valid position");
                playerPos = sc.nextInt();
            }
            playerPosition(gameBoard, playerPos, "player");
            String result = winningConditions();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            //cpu moves
            Random rn = new Random();
            int cpuPos = rn.nextInt(9) + 1;
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rn.nextInt(9) + 1;
            }
            playerPosition(gameBoard, cpuPos, "CPU");

            printGameBoard(gameBoard);

            result = winningConditions();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
        }
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char [] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void playerPosition(char[][] gameBoard, int pos, String player) {
        char symbol = ' ';
        if (player.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (player.equals("CPU")) {
            symbol = 'O';
            cpuPositions.add(pos);
        }
        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String winningConditions() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftcol = Arrays.asList(1, 4, 7);
        List midcol = Arrays.asList(2, 5, 8);
        List rightcol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftcol);
        winning.add(midcol);
        winning.add(rightcol);
        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning) {
            if (playerPositions.containsAll(l)) {
                return "Congratulations you won! :)";
            } else if (cpuPositions.containsAll(l)) {
                return "Sorry you lost it! :(";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "Game tie!!";
            }
        }
        return "";
    }

}
