package problems;

import lombok.Getter;
import org.junit.jupiter.api.Test;
import java.util.*;
import static java.util.stream.Collectors.joining;

public class KnightWordTest {

    @Test
    void run()
    {
        long result = SolveMatrix();
        System.out.println(result);
        System.out.println(Counter);

        //if(canReuseLetter) assertEquals(3847, result);
        //else assertEquals(375, result);
        // 37987
        // 180882
        // 34379
        // 180882
    }

    public static long SolveMatrix() {

        for (int i = 0; i != 5; i++) {
            for (int j = 0; j != 5; j++) {
                SolveMatrix(initMoves(), i, j, 1);
            }
        }

        return COUNTER.size();
    }

    private static void SolveMatrix(Move[][] moves, int x, int y, int movesCounter)
    {
        if(letters[x][y] < 0) return;

        moves[x][y].add(movesCounter);

        if (movesCounter == wordSize )
        {
            var word = print(moves);
            moves[x][y].remove(movesCounter);

            if(COUNTER.containsKey(word)) COUNTER.put(word, COUNTER.get(word) + 1);
            else COUNTER.put(word, 1);
            Counter++;

            return;
        }

        for (int k = 0; k < wordSize; k++)
        {
            var newPosition = knightAllowableMoves[k];
            int newX = x + newPosition[0];
            int newY = y + newPosition[1];

            if (canMove(newX, newY, moves)) {
                SolveMatrix(moves, newX, newY, movesCounter + 1);
            }
        }

        moves[x][y].remove(movesCounter);
    }


    private static final int wordSize = 8;
    private static final int vowelCount = 2;
    private static final boolean canReuseLetter = true; // can visit same letter more than 1
    private static final int[][] knightAllowableMoves = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
    private static final int[][] letters = {
            { 1,  0,  0, -1,  1},
            {-1,  0,  0,  1,  0},
            { 0,  0,  0,  0,  1},
            { 0,  0,  0,  0,  0},
            { 1,  0, -1, -1,  1},
    };

    private static final Map<String, Integer> COUNTER = new HashMap<>();
    private static int Counter = 0;
    private static final char[][] actualLetters = {
            { 'A',  'B',  'C',  ' ',  'E'},
            { ' ',  'G',  'H',  'I',  'J'},
            { 'K',  'L',  'M',  'N',  'O'},
            { 'P',  'Q',  'R',  'S',  'T'},
            { 'U',  'V',  ' ',  ' ',  'Y'},
    };

    @Getter
    public static class Move {
        private boolean used = false;
        private final List<Integer> moves = new ArrayList<>();

        public void remove(Integer position) {
            moves.remove(position);
            used = !moves.isEmpty();
        }

        public void add(int position) {
            used = true;
            moves.add(position);
        }

        @Override
        public String toString() {
            return !used ? "[ ] " : moves.stream().map(x -> "" + x).collect(joining(",", "[", "]")) + " ";
        }

    }

    private static Move[][] initMoves() {
        var moves = new Move[letters.length][letters.length];
        for (int i = 0; i != moves.length; i++) {
            for (int j = 0; j != moves.length; j++) {
                moves[i][j] = new Move();
            }
        }
        return moves;
    }

    private static boolean canMove(int x, int y, Move[][] moves)
    {
        if (x < 0 || y < 0) return false;
        else if(x >= letters.length || y >= letters.length) return false;
        else if(letters[x][y] < 0) return false;
        else if(!canReuseLetter && moves[x][y].used) return false;
        else return canAddVowel(x, y, moves);
    }

    private static boolean canAddVowel(int x, int y, Move[][] moves)
    {
        if (letters[x][y] < 1) return true;

        int sum = 0;
        for (int i = 0; i != moves.length; i++) {
            for (int j = 0; j != moves.length; j++) {
                if(moves[i][j].used) sum += letters[i][j] * moves[i][j].moves.size();
            }
        }

        return sum < vowelCount;
    }

    private static String print(Move[][] moves)
    {
        var array = new char[8];

        for (int i = 0; i != moves.length; i++) {
            for (int j = 0; j != moves.length; j++) {
                if(moves[i][j].used) {
                    var letter = actualLetters[i][j];
                    moves[i][j].moves.forEach( x -> array[x - 1] = letter);
                }
                System.out.print(moves[i][j]);
            }
            System.out.println();
        }


        var word = new String(array);
        System.out.println("WORD :: " + word);
        return word;
    }
}