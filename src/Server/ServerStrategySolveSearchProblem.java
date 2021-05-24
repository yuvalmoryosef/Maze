package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class ServerStrategySolveSearchProblem implements IServerStrategy {
    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();

            Maze maze = (Maze) fromClient.readObject();
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            Solution solution;
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            File folderMaze = new File(tempDirectoryPath + "/Mazes");
            folderMaze.mkdir();
            File[] listOfFileRowCol = folderMaze.listFiles();
            if (listOfFileRowCol.length == 0) {//empty dir, first entrance
                File rowCol = new File(folderMaze.getPath() + "/" + maze.getrowSize() + "." + maze.getcolSize());
                rowCol.mkdir();
                solution = (Solution)insertSolution(maze, rowCol, 0);
                //solution = solveProblem(searchableMaze, new DepthFirstSearch());

            } else {
                solution = findSol(listOfFileRowCol, maze);
            }
            try {
                toClient.writeObject(solution);
                toClient.flush();
                toClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Solution solveProblem(ISearchable domain, ISearchingAlgorithm searcher) {
        //Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);
        return solution;
    }

    private Solution findSol(File[] listOfFileRowCol, Maze maze) {
        for (File f : listOfFileRowCol) {//run on all folders of row.col
            String row = "" + maze.getrowSize();
            String col = "" + maze.getcolSize();
            String fName = f.getName();
            if (fName.equals(row + "." + col))//same row.col
            {
                File[] listOfFileCount = f.listFiles();
                for (File c : listOfFileCount) {//run on all folders of maze, solution
                    File[] listOfFileMazeAndSol = c.listFiles();
                    if (listOfFileMazeAndSol[0].getName().equals("maze.txt")) {
                        byte savedMazeBytes[] = new byte[0];
                        try {
                            //read maze from file
                            //InputStream in = new MyDecompressorInputStream(new FileInputStream(listOfFileMazeAndSol[0].getPath()));
                            InputStream in = new FileInputStream(listOfFileMazeAndSol[0].getPath());

                            savedMazeBytes = new byte[maze.toByteArray().length];

                            in.read(savedMazeBytes);
                            in.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Maze loadedMaze = new Maze(savedMazeBytes);
                        //System.out.println(Arrays.toString(loadedMaze.toByteArray()));
                        boolean areMazesEquals = Arrays.equals(loadedMaze.toByteArray(), maze.toByteArray());
                        if (areMazesEquals) {
                            //System.out.println("true!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            try {
                                ObjectInputStream fromMazeSol = new ObjectInputStream(new FileInputStream(listOfFileMazeAndSol[1].getPath()));
                                try {
                                    //System.out.println("same maze!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                                    Solution solution = (Solution) fromMazeSol.readObject();
                                    System.out.println(listOfFileCount[0].getParentFile().getName());
                                    return solution;
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                //found row.col but not same maze
                return insertSolution(maze, f, listOfFileCount.length);
            }
        }
        //not found row.col
        File rowCol = new File(listOfFileRowCol[0].getParentFile().getPath() + "/" + maze.getrowSize() + "." + maze.getcolSize());
        rowCol.mkdir();
        return insertSolution(maze, rowCol, 0);
    }

    private synchronized Solution insertSolution(Maze maze, File rowCol, int len) {
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        Configurations configurations=new Configurations();
        ASearchingAlgorithm searchingAlgorithm = configurations.getSearchingAlgorithm();
        Solution solution = solveProblem(searchableMaze,searchingAlgorithm);
        File countSol = new File(rowCol.getPath() + "/" + len);
        countSol.mkdir();
        try {//write solution to file
            ObjectOutputStream solToFolder = new ObjectOutputStream(new FileOutputStream(countSol.getPath() + "/sol.txt"));
            solToFolder.writeObject(solution);
            solToFolder.flush();
            solToFolder.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {//write maze to file
            byte[] mazeByte = maze.toByteArray();
            Files.write(Paths.get(countSol.getPath() + "/maze.txt"),mazeByte);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return solution;
    }
}
