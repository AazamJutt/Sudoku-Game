package model;

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
    int SRN; // square root of N
    private ArrayList<Integer> al;
    // number of row and column
    private int size;
    private int K;

    // size of the Sudoku grid is received as parameter
    public SudokuBoard(int size, int k) {
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
        al = new ArrayList<Integer>();// Compute square root of N
        Double SRNd = Math.sqrt(size);
        SRN = SRNd.intValue();
        fillValues();
    }

    // Sudoku Generator
    public void fillValues()
    {
        // Fill the diagonal of SRN x SRN matrices
        fillDiagonal();

        // Fill remaining blocks
        fillRemaining(0, SRN);

        // Remove Randomly K digits to make game
        removeKDigits();
    }

    // Fill the diagonal SRN number of SRN x SRN matrices
    void fillDiagonal()
    {

        for (int i = 0; i<size; i=i+SRN)

            // for diagonal box, start coordinates->i==j
            fillBox(i, i);
    }

    // Returns false if given 3 x 3 block contains num.
    boolean unUsedInBox(int rowStart, int colStart, int num)
    {
        for (int i = 0; i<SRN; i++)
            for (int j = 0; j<SRN; j++)
                if (solution[rowStart+i][colStart+j]==num)
                    return false;

        return true;
    }

    // Fill a 3 x 3 matrix.
    void fillBox(int row,int col)
    {
        int num;
        for (int i=0; i<SRN; i++)
        {
            for (int j=0; j<SRN; j++)
            {
                do
                {
                    num = randomGenerator(size);
                }
                while (!unUsedInBox(row, col, num));

                solution[row+i][col+j] = num;
            }
        }
    }

    // Random generator
    int randomGenerator(int num)
    {
        return (int) Math.floor((Math.random()*num+1));
    }

    // Check if safe to put in cell
    boolean CheckIfSafe(int i,int j,int num)
    {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i-i%SRN, j-j%SRN, num));
    }

    // check in the row for existence
    boolean unUsedInRow(int i,int num)
    {
        for (int j = 0; j<size; j++)
            if (solution[i][j] == num)
                return false;
        return true;
    }

    // check in the row for existence
    boolean unUsedInCol(int j,int num)
    {
        for (int i = 0; i<size; i++)
            if (solution[i][j] == num)
                return false;
        return true;
    }

    // A recursive function to fill remaining
    // matrix
    boolean fillRemaining(int i, int j)
    {
        //  System.out.println(i+" "+j);
        if (j>=size && i<size-1)
        {
            i = i + 1;
            j = 0;
        }
        if (i>=size && j>=size)
            return true;

        if (i < SRN)
        {
            if (j < SRN)
                j = SRN;
        }
        else if (i < size-SRN)
        {
            if (j==(int)(i/SRN)*SRN)
                j =  j + SRN;
        }
        else
        {
            if (j == size-SRN)
            {
                i = i + 1;
                j = 0;
                if (i>=size)
                    return true;
            }
        }

        for (int num = 1; num<=size; num++)
        {
            if (CheckIfSafe(i, j, num))
            {
                solution[i][j] = num;
                if (fillRemaining(i, j+1))
                    return true;

                solution[i][j] = 0;
            }
        }
        return false;
    }


    boolean isValid(int row, int col, int num) {

        for (int i = 0; i < 9; i++) {
            if (solution[row][i] == num) {
                return false;
            }
            if (solution[i][col] == num) {
                return false;
            }
        }

        int gridRow = row - (row % 3);
        int gridColumn = col - (col % 3);
        for (int p = gridRow; p < gridRow + 3; p++) {
            for (int q = gridColumn; q < gridColumn + 3; q++) {
                if (solution[p][q] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    // to retreive the grid
    public int[][] getGrid() {
        return solution;
    }
    // Remove the K no. of digits to
    // complete game
    public void removeKDigits() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = solution[i][j];
            }
        }
        int count = K;
        while (count != 0) {
            int cellId = randomGenerator(size * size) - 1;

            // System.out.println(cellId);
            // extract coordinates i  and j
            int i = (cellId / size);
            int j = cellId % 9;
            if (j != 0)
                j = j - 1;

            // System.out.println(i+" "+j);
            if (board[i][j] != 0) {
                count--;
                board[i][j] = 0;
            }
        }
    }

    public boolean isCorrectValue(int i, int j, int n) {
        if (solution[i][j] == n)
            return true;
        return false;
    }

    public int getCorrectValue(int i, int j) {
        return solution[i][j];
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
}