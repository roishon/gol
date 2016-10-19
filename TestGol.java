package de.interhyp.cleancode.gol;


import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;



/**
 * Created by rshachor on 18.10.2016.
 */
public class TestGol {

    @Test
    public void testGetNextLevel ()
    {
        List<String> lines = Arrays.asList( "_XXX",
                                            "__XX",
                                            "____",
                                            "_XX_");
        String expected =   "_X_X\n" +
                            "_X_X\n" +
                            "_X_X\n" +
                            "____\n";

        GolHandler handler = new GolHandler(lines);

        String result = handler.getNextLevel(lines);
        assertEquals(result, expected);
    }


    @Test
    public void testLinesToMatrix ()
    {
        List<String> lines = Arrays.asList("abc", "def", "ghi");
        GolHandler handler = new GolHandler(lines);
        char[][] expected = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}};
        char[][] result = handler.linesToMatrix(lines);

        for (int row = 0; row < lines.size(); row++)
            assertArrayEquals(result[row], expected[row]);

    }


    @Test
    public void testMatrixToString ()
    {
        List<String> lines = Arrays.asList( "_XXX",
                                            "__XX",
                                            "____",
                                            "_XX_");


        char[][] matrix =  {{'_', 'X', 'X', 'X'},
                            {'_', '_', 'X', 'X'},
                            {'_', '_', '_', '_'},
                            {'_', 'X', 'X', '_'}};


        String expected =   "_XXX\n" +
                            "__XX\n" +
                            "____\n" +
                            "_XX_\n";


        GolHandler handler = new GolHandler(lines);
        String result = handler.matrixToString(matrix);
        assertEquals(result, expected);
    }



    @Test
    public void testGetNextLevelMatrix ()
    {
        List<String> lines = Arrays.asList( "_XXX",
                                            "__XX",
                                            "____",
                                            "_XX_");

        char[][] current =     {{'_', 'X', 'X', 'X'},
                                {'_', '_', 'X', 'X'},
                                {'_', '_', '_', '_'},
                                {'_', 'X', 'X', '_'}};

        char[][] expected =     {{'_', 'X', '_', 'X'},
                                 {'_', 'X', '_', 'X'},
                                 {'_', 'X', '_', 'X'},
                                 {'_', '_', '_', '_'}};


        GolHandler handler = new GolHandler(lines);

        char[][] result = handler.getNextLevelMatrix(current);

        for (int row = 0; row < result.length; row++)
            assertArrayEquals(result[row], expected[row]);

    }


    @Test
    public void testGetNextStatus ()
    {
        List<String> lines = Arrays.asList( "_XXX",
                                            "__XX",
                                            "____",
                                            "_XX_");

        char[][] matrix = {{'_', 'X', 'X', 'X'}, {'_', '_', 'X', 'X'}, {'_', '_', '_', '_'}, {'_', 'X', 'X', '_'}};

        GolHandler handler = new GolHandler(lines);

        char liveToDeadOneNeighbor = handler.getNextStatus(matrix, 0, 0);
        char liveStayLiveThreeNeighbors = handler.getNextStatus(matrix, 0, 3);
        char liveToDeadFourNeighbor = handler.getNextStatus(matrix, 0, 2);
        char deadToLive = handler.getNextStatus(matrix, 1, 1);
        char deadStaysDead = handler.getNextStatus(matrix, 3, 3);

        assertEquals('_', liveToDeadOneNeighbor);
        assertEquals('X', liveStayLiveThreeNeighbors);
        assertEquals('_', liveToDeadFourNeighbor);
        assertEquals('X', deadToLive);
        assertEquals('_', deadStaysDead);


    }


    @Test
    public void testCountLivingNeighbors ()
    {
        List<String> lines = Arrays.asList( "_XXX",
                                            "__XX",
                                            "____",
                                            "_XX_");

        char[][] matrix = {{'_', 'X', 'X', 'X'}, {'_', '_', 'X', 'X'}, {'_', '_', '_', '_'}, {'_', 'X', 'X', '_'}};

        GolHandler handler = new GolHandler(lines);

        int expectedX0Y3 = 3;
        int expectedX0Y2 = 4;
        int expectedX2Y0 = 1;

        int resultX0Y3 = handler.countLivingNeighbors(matrix, 0, 3);
        int resultX0Y2 = handler.countLivingNeighbors(matrix, 0, 2);
        int resultX2Y0 = handler.countLivingNeighbors(matrix, 2, 0);

        assertEquals(expectedX0Y3, resultX0Y3);
        assertEquals(expectedX0Y2, resultX0Y2);
        assertEquals(expectedX2Y0, resultX2Y0);
    }



    @Test
    public void testValidateCoordinateNeighbors ()
    {
        //3 rows, 7 cols
        List<String> lines = Arrays.asList("1234567", "1234567", "1234567");
        GolHandler handler = new GolHandler(lines);
        int row = 0;
        int col = 6;

        int rowUp = -1;
        int rowDown = 1;
        int colLeft = 5;
        int colRight = 7;

        //outside the matrix
        assertEquals(handler.validateCoordinateNeighbors(rowUp, colLeft, row, col), false);
        assertEquals(handler.validateCoordinateNeighbors(rowUp, col, row, col), false);
        assertEquals(handler.validateCoordinateNeighbors(rowUp, colRight, row, col), false);
        assertEquals(handler.validateCoordinateNeighbors(row, colRight, row, col), false);
        assertEquals(handler.validateCoordinateNeighbors(rowDown, colRight, row, col), false);
        //same cell, not a neighbor
        assertEquals(handler.validateCoordinateNeighbors(row, col, row, col), false);
        //inside the matrix
        assertEquals(handler.validateCoordinateNeighbors(rowDown, col, row, col), true);
        assertEquals(handler.validateCoordinateNeighbors(rowDown, colLeft, row, col), true);
        assertEquals(handler.validateCoordinateNeighbors(row, colLeft, row, col), true);
    }



    @Test
    public void testIsLiving ()
    {
        char live = 'X';
        char dead = '_';
        List<String> lines = Arrays.asList("1234567", "1234567");
        GolHandler handler = new GolHandler(lines);
        int isLive = handler.isLiving(live);
        int isDead = handler.isLiving(dead);
        assertEquals(isLive, 1);
        assertEquals(isDead, 0);
    }


    @Test
    public void testGetColsNumber ()
    {
        List<String> lines = Arrays.asList("1234567", "1234567");
        GolHandler handler = new GolHandler(lines);
        int expected = 7;
        int result = handler.getColsNumber(lines);
        assertEquals(result, expected);
    }


    @Test
    public void testGetRowsNumber ()
    {
        List<String> lines = Arrays.asList("1234567", "1234567");
        GolHandler handler = new GolHandler(lines);
        int expected = 2;
        int result = handler.getRowsNumber(lines);
        assertEquals(result, expected);
    }


}
