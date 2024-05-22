package problems;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlexLaninTest {

    @Test
    void run()
    {
        long result = SolveMatrix();
        System.out.println("Count :: " + result);
        assertTrue(result > 340 && result < 400);
    }

    private static long COUNTER = 0;

    public static long SolveMatrix() {
        SolveMatrix(new int[letters.length][letters.length], 0, 0, 1);
        return COUNTER;
    }

    public static final int wordSize = 8;
    public static final int vowelCount = 2;

    public static final int[][] knightAllowableMoves = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};

    public static final int[][] letters = {
            { 1,  0,  0, -1,  1},
            {-1,  0,  0,  1,  0},
            { 0,  0,  0,  0,  1},
            { 0,  0,  0,  0,  0},
            { 1,  0, -1, -1,  1},
    };

    public static final char[][] actualLetters = {
            { 'A',  'B',  'C',  ' ',  'E'},
            { ' ',  'G',  'H',  'I',  'J'},
            { 'K',  'L',  'M',  'N',  'O'},
            { 'P',  'Q',  'R',  'S',  'T'},
            { 'U',  'V',  ' ',  ' ',  'Y'},
    };


    public static void SolveMatrix(int[][] moves, int x, int y, int movesCounter)
    {
        moves[x][y] += movesCounter;

        if (movesCounter == wordSize )
        {
            print(moves);
            moves[x][y] = 0;
            COUNTER++;
            return;
        }

        for (int k = 0; k < 8; k++)
        {
            var newPosition = knightAllowableMoves[k];
            int newX = x + newPosition[0];
            int newY = y + newPosition[1];

            if (canMove(newX, newY, moves)) {
                SolveMatrix(moves, newX, newY, movesCounter + 1);
            }
        }

        moves[x][y] = 0;
    }

    private static boolean canMove(int x, int y, int[][] moves)
    {
        if (x < 0 || y < 0) return false;
        else if(x >= letters.length || y >= letters.length) return false;
        else if(letters[x][y] < 0) return false;
        else if(moves[x][y] != 0) return false;
        else return canAddVowel(x, y, moves);
    }

    private static boolean canAddVowel(int x, int y, int[][] moves)
    {
        int sum = 0;
        for (int i = 0; i != moves.length; i++) {
            for (int j = 0; j != moves.length; j++) {
                if(moves[i][j] > 0) sum += letters[i][j];
            }
        }

        return sum <= vowelCount;
    }

    private static void print(int[][] moves)
    {
        var word = new StringBuffer();
        for (int i = 0; i != moves.length; i++) {
            for (int j = 0; j != moves.length; j++) {
                word.append(moves[i][j] == 0 ? "" : (char)actualLetters[i][j]);
                System.out.print(moves[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(word);
        System.out.println();
    }

}