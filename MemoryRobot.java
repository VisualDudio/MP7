import java.util.*;
public class MemoryRobot extends RandomRobot
{
   public MemoryRobot(int startX, int startY)
   {
       super(startX, startY);
   }
   
   public void move(Maze m)
   {
       int previousX = getRobotPos()[0];
       int previousY = getRobotPos()[1];
       ArrayList<String> moves = possibleMoves(m);
       super.move(m);
       if (moves.size() == 1) {
           m.getMaze()[previousY][previousX]=false;
       }
   }
}    