package problems;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevenshteinDistanceTest
{
    @Test
    void computeEditDistance() {
        assertEquals(1, LevenshteinDistanceTest.computeEditDistance("table", "tables"));
        assertEquals(3, LevenshteinDistanceTest.computeEditDistance("a table", "tables"));
        assertEquals(2, LevenshteinDistanceTest.computeEditDistance("a table", "table"));
        assertEquals(2, LevenshteinDistanceTest.computeEditDistance("table", "a table"));
        assertEquals(2, LevenshteinDistanceTest.computeEditDistance("tbl", "table"));
        assertEquals(3, LevenshteinDistanceTest.computeEditDistance("tables", "tbl"));
        assertEquals(3, LevenshteinDistanceTest.computeEditDistance("", "tbl"));
        assertEquals(5, LevenshteinDistanceTest.computeEditDistance("table", ""));
        assertEquals(0, LevenshteinDistanceTest.computeEditDistance("table", "table"));
    }

    public static int computeEditDistance(String s1, String s2)
    {
        //if (s1.equals(s2))       return 0;             //Strings are equal, no edits needed
        //else
        if (s1.isEmpty())   return s2.length();   //str1 is empty, edits are simply insertions of the chars of str2
        //else if (s2.isEmpty())   return s1.length();   //str2 is empty, edits are simply the insertions of the chars of str1

        //Create arrays representing columns of the edit-distance matrix this method creates. The
        //cells will contain the edit distances between 0-based str2 substrings of increasing size and
        //the str1 substring bounded by its (str1's) previous and currently processing chars respectively.
        int[] ancestorMatrixCol = new int[s2.length() + 1];
        int[] previousMatrixCol = new int[s2.length() + 1];
        int[] currentMatrixCol  = new int[s2.length() + 1];

        //Calculate the edit distances for the first matrix column. The first COLUMN of the
        //matrix is associated with scenarios in which str1 is empty, in which case the
        //edit distance between it and a given str2 substring (str2.substring(0, index)) is simply "index"
        for (int i = 0; i < previousMatrixCol.length; i++) previousMatrixCol[i] = i;

        //Loop through the chars in str1, calculating the optimal edit distances between
        //the str1 substring bounded by each char, and all the 0-based str2 substrings.
        //In other words, loop through and fill the columns, top to bottom, of the edit distance matrix
        for (int i = 0; i < s1.length(); i++)
        {
            //The first ROW of the matrix is associated with scenarios in which str2 is empty, in which case
            //the edit distance is between it and str1.substring(0, i) is simply i. The value is offset by 1
            //to take into account the first COLUMN of the matrix which corresponds to empty str1 scenarios.
            currentMatrixCol[0] = i + 1;

            //Loop through the chars in str2, calculating the optimal edit distances between
            //the 0-based str2 substring bounded by each char and str1.substring(0, i).
            //In other words, fill from top to bottom, the matrix column indexed by i.
            for (int j = 0; j < s2.length(); j++)
            {
                //Take the edit distance (contained in the neighboring upper matrix cell) calculated between the previous str2
                //substring and the current str1 substring and increment it to represent a hypothetical necessary deletion in str2
                int deletionCost = currentMatrixCol[j] + 1;

                //Take the edit distance (contained in the neighboring left matrix cell) calculated between the current str2 substring
                //and the previous str1 substring and increment it to represent a hypothetical necessary insertion to str2
                int insertionCost = previousMatrixCol[j + 1] + 1;

                //Determine the edit distance between the currently processing chars in str1 and str2
                int curCharEditDistance = (s1.charAt(i) == s2.charAt(j) ? 0 : 1);

                //Take the edit distance calculated between the previous str2 and str1 substrings and
                //increment it only if their currently processing chars differ (hypothetical necessary substitution)
                int substitutionCost = previousMatrixCol[j] + curCharEditDistance;

                //Determine the smallest edit operation cost among those currently associated with a deletion, insertion and substitution
                int minEditOperationCost = Math.min(deletionCost, insertionCost);
                minEditOperationCost = Math.min(minEditOperationCost, substitutionCost);

                //If the previous and currently processing chars of both strings are transposed, determine the smallest
                //edit operation cost between minEditOperationCost and that associated with a hypothetical transposition
                if(i > 0 && j > 0 && s1.charAt(i) == s2.charAt(j - 1) && s1.charAt(i - 1) == s2.charAt(j)) {
                    minEditOperationCost = Math.min(minEditOperationCost, ancestorMatrixCol[j - 1] + curCharEditDistance);
                }

                currentMatrixCol[j + 1] = minEditOperationCost;
            }

            //Copy the elements of currentMatrixCol to previousMatrixCol, priming them for the next iteration
            for (int j = 0; j < previousMatrixCol.length; j++)
            {
                ancestorMatrixCol[j] = previousMatrixCol[j];
                previousMatrixCol[j] = currentMatrixCol[j];
            }

        }

        return currentMatrixCol[s2.length()];
    }

    /**
     * Determines if two Strings are within a specified edit distance of one another, using a dynamic programming approach.
     * @param maxEditDistance       an int denoting the maximum amount of edit operations allowed
     *                              to be used to (hypothetically) turn one String in to another
     * @param str1                  a String
     * @param str2                  a String
     * @return                      true if {@code str1} and {@code str2} are within {@code maxEditDistance}
     *                              edit operations of each other; false otherwise
     */
    public static boolean isWithinEditDistanceNonAutomaton(int maxEditDistance, String str1, String str2)
    {
        return (computeEditDistance(str1, str2) <= maxEditDistance);
    }
}