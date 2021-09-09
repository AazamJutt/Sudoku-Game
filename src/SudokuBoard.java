import java.util.ArrayList;
import java.util.Random;

public class SudokuBoard {

    // the grid with the numbers
    private int[][] solution;
    public int[][] board;
    // a single row
    private int[] row;
    // random and aray list
    private Random ran;
    private ArrayList<Integer> al;
    // number of row and column
    private int size;
    private int K;

    // size of the Sudoku grid is received as parameter
    SudokuBoard(int size, int k) {
        this.size = size;
        this.K = k;
        // this this the grid that we will fill
        solution = new int[size][size];
        board = new int[size][size];
        // this is to store a row that we are trying to build
        row = new int[size];
        // random number generator
        ran = new Random();
        // arraylist that will contain the possible values for every case in the grid
        al = new ArrayList<Integer>();

        // now let's fill the grid row by row
        for (int i = 0; i < size; i++)
            fillRow(i);
        removeKDigits();
    }

    // will call genRow() to fill row
    // then will copy that row into the grid... the main reason for this method is to display debug info
    private void fillRow(int n) {
        genRow(n);
        for (int i = 0; i < size; i++) {
            solution[n][i] = row[i];
        }

    }

    // fill the instance variable row with a new row
    private void genRow(int n) {
        // will be used to flag which values are available or not
        boolean[] used = new boolean[size];
        // it might take more than one trial to fill a row
        // imagine the following case
        // 1 2 3 4
        // 2 3 4 1
        // now if I add for the third row
        // 3 then
        // 3 4 then
        // 3 4 2 then
        // the only possible case for the last column is 1 but 1 is invalid in the last column
        // so we'll have to try again
        boolean conflict = true; // assume we have conflict to enter the while loop
        while (conflict) {
            conflict = false; // assume it worked
            // loop for each column of the row
            for (int i = 0; i < size; i++) {
                // initialized that all number form 0 to size are not used and are available
                for (int j = 0; j < size; j++)
                    used[j] = false;
                // i cannot use any previous number used on that row so I set to true
                // all the already allocated numbers in that row
                for (int j = 0; j < i; j++)
                    used[row[j]] = true;
                // i cannot use neither the numbers used in the same column in the previous rows already filled
                for (int j = 0; j < n; j++)
                    used[solution[j][i]] = true;
                // fill the array list with the possible values
                al.clear(); // empty the arraylist
                for (int j = 0; j < size; j++) { // fill it with the permitted values
                    if (!used[j]) { // if number not used
                        al.add(j); // add it to arraylist
                    }
                }
                // now case explained in comment for variable conflict
                // in that case no number would have been entered in the arraylist so its size would be 0
                if (al.size() == 0) {
                    // if it is the case flag that there is a conflict
                    conflict = true;
                    break; // no need to continue the loop
                }
                // pickup a number randomly from the arraylist
                row[i] = al.remove(ran.nextInt(al.size()));
            }
        }
    }

    // to retreive the grid
    public int[][] getGrid() {
        return solution;
    }

    // Random generator
    int randomGenerator(int num)
    {
        return (int) Math.floor((Math.random()*num+1));
    }
    // Remove the K no. of digits to
    // complete game
    public void removeKDigits()
    {
        for (int i = 0; i< size; i++){
            for (int j = 0; j< size; j++){
                board[i][j] = solution[i][j];
            }
        }
        int count = K;
        while (count != 0)
        {
            int cellId = randomGenerator(size*size)-1;

            // System.out.println(cellId);
            // extract coordinates i  and j
            int i = (cellId/size);
            int j = cellId%9;
            if (j != 0)
                j = j - 1;

            // System.out.println(i+" "+j);
            if (board[i][j] != 0)
            {
                count--;
                board[i][j] = 0;
            }
        }
        for (int i = 0; i< size; i++){
            for (int j = 0; j< size; j++){
                System.out.print(board[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }

    // to print the grid (numbers are from 0 to size-1)
    // but for regular user we will display from 1 to size
    public String toString() {
        String str = "";
        for (int i = 0; i < size; i++) { // for every row
            for (int j = 0; j < size; j++) { // and column
                str += solution[i][j] + "\t"; // add value to String
            }
            str += "\n"; // end of line
        }
        return str;
    }


    public static void main(String[] arg) {
        SudokuBoard s = new SudokuBoard(9,20);
        System.out.print(s);
    }
}