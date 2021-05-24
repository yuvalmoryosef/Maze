package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ServerStrategyGenerateMaze implements  IServerStrategy{

    @Override
    public void serverStrategy(InputStream inFromClient, OutputStream outToClient) {
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            toClient.flush();

            int [] array=(int[])fromClient.readObject();//row,cols
          //  AMazeGenerator mazeGenerator = new MyMazeGenerator();
            Configurations configurations=new Configurations();
            AMazeGenerator mazeGenerator = configurations.getMazeGenerator();

            Maze maze = mazeGenerator.generate(array[0],array[1]); //Generate new maze
            byte[] mazeByte = maze.toByteArray();
            //new option:
            OutputStream outTempB = new ByteArrayOutputStream();
            MyCompressorOutputStream comp = new MyCompressorOutputStream(outTempB);
            outTempB.flush();
            outTempB.close();
            comp.write(mazeByte);

            byte [] savedMazeBytes = new byte[((ByteArrayOutputStream)outTempB).size()];
            InputStream inTempB = new ByteArrayInputStream(((ByteArrayOutputStream)outTempB).toByteArray());
            inTempB.read(savedMazeBytes);
            toClient.writeObject(savedMazeBytes);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
