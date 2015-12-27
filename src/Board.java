import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Prayansh on 2015-12-25.
 */
public class Board extends ArrayList<Integer> {
    // A cell on the board is [0,9]
    // where 0 represents blank cell

    /**
     * ROW, COL, BOX contain units that have to be unique in order for the board to be valid
     */
    public static final int[][] ROW = {
            {0, 1, 2, 3, 4, 5, 6, 7, 8},
            {9, 10, 11, 12, 13, 14, 15, 16, 17},
            {18, 19, 20, 21, 22, 23, 24, 25, 26},
            {27, 28, 29, 30, 31, 32, 33, 34, 35},
            {36, 37, 38, 39, 40, 41, 42, 43, 44},
            {45, 46, 47, 48, 49, 50, 51, 52, 53},
            {54, 55, 56, 57, 58, 59, 60, 61, 62},
            {63, 64, 65, 66, 67, 68, 69, 70, 71},
            {72, 73, 74, 75, 76, 77, 78, 79, 80}};
    public static final int[][] COL = {
            {0, 9, 18, 27, 36, 45, 54, 63, 72},
            {1, 10, 19, 28, 37, 46, 55, 64, 73},
            {2, 11, 20, 29, 38, 47, 56, 65, 74},
            {3, 12, 21, 30, 39, 48, 57, 66, 75},
            {4, 13, 22, 31, 40, 49, 58, 67, 76},
            {5, 14, 23, 32, 41, 50, 59, 68, 77},
            {6, 15, 24, 33, 42, 51, 60, 69, 78},
            {7, 16, 25, 34, 43, 52, 61, 70, 79},
            {8, 17, 26, 35, 44, 53, 62, 71, 80}};
    public static final int[][] BOX = {
            {0, 1, 2, 9, 10, 11, 18, 19, 20},
            {3, 4, 5, 12, 13, 14, 21, 22, 23},
            {6, 7, 8, 15, 16, 17, 24, 25, 26},
            {27, 28, 29, 36, 37, 38, 45, 46, 47},
            {30, 31, 32, 39, 40, 41, 48, 49, 50},
            {33, 34, 35, 42, 43, 44, 51, 52, 53},
            {54, 55, 56, 63, 64, 65, 72, 73, 74},
            {57, 58, 59, 66, 67, 68, 75, 76, 77},
            {60, 61, 62, 69, 70, 71, 78, 79, 80}};


    public Board(int initialCapacity) {
        super(initialCapacity);
    }

    public Board() {
        super();
    }

    public Board(Collection<? extends Integer> c) {
        super(c);
    }


    /**
     * TODO
     * Function to check if board is valid or not
     */
    public boolean valid() {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i) < 0 || this.get(i) > 9)
                return false;
        }
        for (int i = 0; i < 9; i++) {
            if (duplicates(mapUnit(ROW[i])) || duplicates(mapUnit(COL[i])) || duplicates(mapUnit(BOX[i])))
                return false;
        }
        return true;
    }

    /**
     * Check if any duplicates in the given list
     * Can be made abstract for any definition by changing
     * @param integers
     * @return true if duplicates found else false
     */
    private boolean duplicates(ArrayList<Integer> integers) {
        while (!integers.isEmpty()) {
            int cur = integers.get(0);
            integers.remove(0);
            if (integers.contains(cur))
                return true;
        }
        return false;
    }

    /**
     * Create a list of Cell Values by mapping the indices of the current board
     * @param ints
     * @return
     */
    private ArrayList<Integer> mapUnit(int[] ints) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < ints.length; i++) {
            if (this.get(ints[i]) != 0)
                res.add(this.get(ints[i]));
        }

        return res;
    }

    /**
     * Function to check if board is solved or not
     * If board is filled and valid then it is solved
     *
     * @return
     */
    public boolean solved() {
        if (valid()) {
            return !this.contains(0);
        } else
            return false;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                s += (this.get(i * 9 + j) + " ");
                if (j == 2 || j == 5)
                    s += " | ";
            }
            s += "\n";
            if (i == 2 || i == 5)
                s += "------------------------\n";
        }
        return s;
    }
}