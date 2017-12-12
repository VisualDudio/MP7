import java.util.*;

public class MemoryRobot extends RandomRobot {
   public MemoryRobot(int startX, int startY) {
       super(startX, startY);
   }
   
   public void move(Maze maze) {
       int previousX = getRobotPos()[0];
       int previousY = getRobotPos()[1];
       ArrayList<String> moves = possibleMoves(maze);
       super.move(maze);
       if (moves.size() == 1) {
           maze.getMaze()[previousY][previousX] = false;
       }
   }
}    
