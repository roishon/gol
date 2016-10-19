package de.interhyp.cleancode.gol;

import java.util.List;

/**
 * Created by rshachor on 18.10.2016.
 */
public class GolHandler {

    private int rowNumber, colNumber;

    public GolHandler (List<String> lines)
    {
        rowNumber = getRowsNumber(lines);
        colNumber = getColsNumber(lines);
    }


    public String getNextLevel (List<String> lines)
    {
        char[][] currentMatrix = linesToMatrix(lines);
        char[][] nextMatrix = getNextLevelMatrix(currentMatrix);
        String nextTextMatrix = matrixToString(nextMatrix);
        return nextTextMatrix;
    }


    public char[][] linesToMatrix (List<String> lines)
    {
        char [][]matrix = new char[rowNumber][colNumber];

        for (int row = 0; row < rowNumber; row++)
            for (int col = 0; col < colNumber; col++)
                matrix[row][col] =  lines.get(row).charAt(col);

        return matrix;
    }


    public String matrixToString (char[][] matrix)
    {
        StringBuilder builder = new StringBuilder();

        for (int row = 0; row < rowNumber; row++)
        {
            for (int col = 0; col < colNumber; col++)
                builder.append(matrix[row][col]);
            builder.append("\n");
        }
        return builder.toString();
    }



    public char[][] getNextLevelMatrix (char[][] currentMatrix)
    {
        char[][] nextMatrix = new char[rowNumber][colNumber];
        for (int row = 0; row < rowNumber; row++)
            for (int col = 0; col < colNumber; col++)
                nextMatrix[row][col] = getNextStatus(currentMatrix, row, col);

        return nextMatrix;
    }


    public char getNextStatus (char[][] matrix, int row, int col)
    {
        char nextStatus = '?';
        char currentStatus = matrix[row][col];
        int livingNeighbors = countLivingNeighbors(matrix, row, col);

        if (currentStatus == 'X')
        {
            if (livingNeighbors < 2 || livingNeighbors > 3)
                nextStatus = '_';
            else
                nextStatus = 'X';
        }
        else
        {
            if (livingNeighbors == 3)
                nextStatus = 'X';
            else
                nextStatus = '_';
        }
        return nextStatus;
    }


    public int countLivingNeighbors(char[][] matrix, int row, int col)
    {
        int livingNeighbors = 0;
        for (int rowN = row - 1; rowN <= (row + 1); rowN++)
            for (int colN = col - 1; colN <= (col + 1); colN++)
                {
                if (validateCoordinateNeighbors (rowN, colN, row, col))
                    {
                        char c = matrix[rowN][colN];
                        livingNeighbors += isLiving(c);
                    }
                }

        return livingNeighbors;
    }


    public boolean validateCoordinateNeighbors (int rowN, int colN, int currentRow, int currentCol)
    {
        if (rowN < 0 || colN < 0 || rowN >= rowNumber || colN >= colNumber)
            return false;

        if (currentRow == rowN && currentCol == colN)
            return false;

        return true;
    }


    public int isLiving (char c)
    {
        if (c == 'X' || c =='x')
            return 1;
        else
            return 0;
    }


    public int getRowsNumber (List<String> lines)
    {
        return lines.size();
    }



    public int getColsNumber (List<String> lines)
    {
        return lines.get(0).length();
    }



}
