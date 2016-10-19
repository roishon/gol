package de.interhyp.cleancode.gol;

import de.interhyp.cleancode.Fileloader;
import de.interhyp.cleancode.IO;
import java.util.List;

/**
 * Created by rshachor on 18.10.2016.
 */
public class Gol {

    public static void main (String[] args)
    {
        new Gol().run (args[0]);
    }

    public void run (String fileName)
    {
        final String path = "C:/developOld/ber_start/src/";
        List<String> lines = Fileloader.loadFile(path + fileName);
        GolHandler handler = new GolHandler(lines);
        String nextLevel = handler.getNextLevel(lines);
        IO.print(nextLevel);
    }
}
