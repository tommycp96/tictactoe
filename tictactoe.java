package tictactoe;

import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

public static final int draw = 0;
public static final int enemy = 1;
public static final int player = 2;
public static final char player_char = 'X';
public static final char enemy_char = 'O';

public static int size;
public static String[][] board;
public static int score = 0;
public static Scanner scan = new Scanner(System.in);

/**
 * Creates base for the game.
 *
 * @param args the command line parameters. Not used.
 */
public static void main(String[] args) {

    while (true) {
        System.out.println("Select board size");
        System.out.print("[int]: ");
        try {
            size = Integer.parseInt(scan.nextLine());
        } catch (Exception e) {
            System.out.println("You can't do that.");
            continue;
        }

        break;
    }

    int[] move = {};
    board = new String[size][size];
    setupBoard();

    int i = 1;

    loop:

    while (true) {
        if (i % 2 == 1) {
            displayBoard();
            move = getMove();
        } 
        else {
            enemyTurn();
        }

        switch (isGameFinished(move)) {
            case player:
                System.err.println("YOU WIN!");
                break loop;
            case enemy:
                System.err.println("Enemy WINS!\nYOU LOSE!");
                break loop;
            case draw:
                System.err.println("IT'S A DRAW");
                break loop;
        }
        i++;
    }
}

private static int isGameFinished(int[] move) {
    if (isDraw()) {
        return draw;
    } 
    else if (playerHasWon(board, move, enemy_char)) {
        return enemy;
    } 
    else if (playerHasWon(board, move, player_char)) {
        return player;
    }
    return -1;
}

public static boolean playerHasWon(String[][] board, int[] move, String playerMark) { 
   for (int i = 0; i < size; i++) {
         if (board[i][0].equals(playerMark)) {
            int j;
            for (j = 1; j < size; j++) {
               if (!board[i][j].equals(playerMark)) {
                     break;
               }
            }

            if (j == size) {
               return true;
            }
         }
   }

  for (int i = 0; i < size; i++) {
      if (board[0][i].equals(playerMark)) {
          int j;
          for (j = 1; j < size; j++) {
              if (!board[j][i].equals(playerMark)) {
                  break;
              }
          }

          if (j == size) {
              return true;
          }
      }
  }

  int i;
  for (i = 0; i < size; i++) {
      if (!board[i][i].equals(playerMark)) {
          break;
      }
  }
  if (i == size) {
      return true;
  }

  for (i = 0; i < size; i++) {
      if (!board[i][(size - 1) - i].equals(playerMark)) {
          break;
      }
  }
  if (i == size) {
      return true;
  }

  return false;
}

public static boolean isDraw() {
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            if (board[i][j] == " ") {
                return false;
            }
        }
    }

    return true;
}

public static void displayBoard() {
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            System.out.printf("[%s]", board[i][j]);
        }

        System.out.println();
    }
}

public static void setupBoard() {
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            board[i][j] = " ";
        }
    }
}

public static int[] getMove() {

    Scanner sc = new Scanner(System.in);
    System.out.println("Your turn:");

    while (true) {
        try {
            System.out.printf("ROW: [0-%d]: ", size - 1);
            int x = Integer.parseInt(sc.nextLine());
            System.out.printf("COL: [0-%d]: ", size - 1);
            int y = Integer.parseInt(sc.nextLine());

            if (isValidPlay(x, y)) {
                board[x][y] = "" + player_char;
                return new int[]{x, y};
            }
        } catch (Exception e) {
            System.out.println("You can't do that.");
        }
        return null;
    }
}

public static void enemyTurn() {
    Random rgen = new Random();  
    while (true) {
        int x = (int) (Math.random() * size);
        int y = (int) (Math.random() * size);

        if (isValidPlay(x, y)) {
            board[x][y] = "" + enemy_char;
            break;
        }
    }
}

public static boolean isValidPlay(int inX, int inY) {
    if ((inX >= size) || (inY >= size)) {
        return false;
    }
    return (board[inX][inY] == " ");
    }
}