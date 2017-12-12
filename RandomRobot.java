import java.util.*;
public class RandomRobot extends Robot {
   public RandomRobot(int startX, int startY) {
       super(startX, startY);
   }
   
   public void move(Maze m) {
       int currentYTemp = getRobotPos()[1]; 
       int currentXTemp = getRobotPos()[0];
       ArrayList<String> moves = possibleMoves(m);
       Random gen = new Random();
       switch (moves.get(gen.nextInt(moves.size()))) {
           case "Up":
        	   currentYTemp--;
        	   break;
           case "Down":
               currentYTemp++;
               break;
           case "Right":
               currentXTemp++;
               break;
           case "Left":
               currentXTemp--;
               break;
       }
       
       setPos(currentXTemp, currentYTemp);
   }
    
   public ArrayList<String> possibleMoves(Maze m) {
       boolean [][] temp = m.getMaze(); 
       ArrayList<String> moves = new ArrayList<String>();
       int currentYTemp = getRobotPos()[1]; 
       int currentXTemp = getRobotPos()[0];
       
       if (currentYTemp != temp.length - 1 && temp[currentYTemp + 1][currentXTemp])
    	   moves.add("Down");
       
       if (currentYTemp != 0 && temp[currentYTemp - 1][currentXTemp])
           moves.add("Up");
     
       if(currentXTemp != temp[0].length && temp[currentYTemp][currentXTemp + 1])
           moves.add("Right");
       
       if(currentXTemp != 0 && temp[currentYTemp][currentXTemp - 1])
           moves.add("Left");
       
       return moves;
   }
}    
