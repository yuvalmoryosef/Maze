package Server;

import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Configurations {
    private static AMazeGenerator mazeGenerator;
    private static ASearchingAlgorithm searchingAlgorithm;
    private static int countTreads;

    public Configurations() {
        readFromConf();
    }

    private static void readFromConf() {
        try (InputStream input = Configurations.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                return;
            }
            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
            String mazeG=prop.getProperty("maze");

            if (mazeG.equals("EmptyMazeGenerator")) {
                mazeGenerator = new EmptyMazeGenerator();
            }

            if (mazeG.equals("SimpleMazeGenerator")) {
                mazeGenerator = new SimpleMazeGenerator();
            }
            if (mazeG.equals("MyMazeGenerator")) {
                mazeGenerator = new MyMazeGenerator();
            }

            String sol=prop.getProperty("sol");
            if (sol.equals("BFS")) {
                searchingAlgorithm = new BreadthFirstSearch();
            }

            if (sol.equals("DFS")) {
                searchingAlgorithm = new DepthFirstSearch();
            }

            if (sol.equals("BEST")) {
                searchingAlgorithm = new BestFirstSearch();
            }

            countTreads = Integer.parseInt(prop.getProperty("Tread"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AMazeGenerator getMazeGenerator() {
        return mazeGenerator; }

    public static ASearchingAlgorithm getSearchingAlgorithm() {
        return searchingAlgorithm;
    }

    public static int getCountTreads() {
        return countTreads;
    }
}
