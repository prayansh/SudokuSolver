import java.util.ArrayList;

/**
 * Created by Prayansh on 2015-12-25.
 */
public class Solver {
    private Board b;

    /**
     * Initialise the board with blank values
     */
    public Solver() {
        b = new Board(81);
        for (int i = 0; i < b.size(); i++) {
            b.set(i, 0);
        }
    }

    /**
     * Initialise the board as a ArrayList
     * @param board
     */
    public Solver(ArrayList<Integer> board) {
        b = new Board(board);
    }

    /**
     *
     * @param board
     */
    public Solver(Integer[][] board) {
        b = new Board();
        if ((board.length * board[0].length) == 81)
            for (int i = 0; i < board[i].length; i++)
                for (int j = 0; j < board.length; j++)
                    b.add(board[i][j]);
        else
            System.out.println("Wrong Input");
    }

    public Solver(int[][] board) {
        b = new Board();
        if ((board.length * board[0].length) == 81)
            for (int i = 0; i < board[i].length; i++)
                for (int j = 0; j < board.length; j++)
                    b.add(board[i][j]);
        else
            System.out.println("Wrong Input");
    }

    public Solver(int[] board) {
        b = new Board();
        if (board.length == 81)
            for (int i = 0; i < board.length; i++)
                b.add(board[i]);
        else
            System.out.println("Wrong Input");
    }

    /**
     * Solve the given board with generative tree algorithm
     * @return solved board
     */
    public Board solve() {
        ArrayList<Board> todo = new ArrayList<>();
        todo.add(b);
        while (!todo.isEmpty()) {
            Board cur = todo.get(0);
            todo.remove(cur);
//            System.out.println(cur);
            if (cur.solved()) {
                b = cur;
                return b;
            }
            todo = append(nextBoards(cur), todo);
        }
        System.out.println("No Solution Found");
        return b;
    }

    /**
     * Solve the given board with generative tree algorithm
     * @return all possible solutions
     */
    public ArrayList<Board> produceAllSolutions() {
        ArrayList<Board> todo = new ArrayList<>();
        ArrayList<Board> rsf = new ArrayList<>();
        todo.add(b);
        while (!todo.isEmpty()) {
            Board cur = todo.get(0);
            todo.remove(cur);
//            System.out.println(cur);
            if (cur.solved()) {
                rsf.add(cur);
                continue;
            }
            todo = append(nextBoards(cur), todo);
        }
        if (rsf.isEmpty()) {
            rsf.add(b);
            System.out.println("No Solution Found");
        }
        return rsf;
    }

    /**
     * Produce the next boards
     * Also filter out all invalid boards
     *
     * @param cur
     * @return
     */
    private ArrayList<Board> nextBoards(Board cur) {
        int index = firstBlank(cur);
        ArrayList<Board> result = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            //cur.set(index, i);// replace index value with i in new board
            result.add(newBoard(index, i, cur));
        }
        result.removeIf(board -> !board.valid());
        return result;
    }

    /**
     * Produce a new board by replacing the index with the given value for the board
     *
     * @param index
     * @param value
     * @param cur
     * @return
     */
    private Board newBoard(int index, int value, Board cur) {
        Board res = new Board();
        for (int i = 0; i < cur.size(); i++) {
            if (i == index)
                res.add(value);
            else
                res.add(cur.get(i));
        }
        return res;
    }

    /**
     * Produce the first blank cell in the board
     * @param cur - the board in context
     * @return the first blank cell
     */
    private int firstBlank(Board cur) {
        for (int i = 0; i < cur.size(); i++) {
            if (cur.get(i) == 0)
                return i;
        }
        return 0;
    }

    /**
     * Appends l1 to l2 with l1 being first
     * !!!! TESTED DO NOT CHANGE !!!!
     *
     * @param l1 - list 1
     * @param l2 - list 2
     * @return (append (list 1) (list 2)) -> (list 1 2)
     */
    public static ArrayList<Board> append(ArrayList<Board> l1, ArrayList<Board> l2) {
        for (Board a : l2) {
            l1.add(a);
        }
        l2.clear();
        return l1;
    }

    public Board getB() {
        return b;
    }
}
