public class RightHandRobot extends Robot
{
    private int orientation;
    
    public RightHandRobot(int startX, int startY, int orientation)
    {
        super(startX, startY);
        this.orientation = orientation; 
    }
    
    public void move(Maze maze)
    {
       int currentYTemp = getRobotPos()[1]; 
       int currentXTemp = getRobotPos()[0];
       switch (possibleMove(maze)) {
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
    
    public String possibleMove(Maze maze)
    {
        boolean[][] temp = maze.getMaze();
        int currentYTemp = getRobotPos()[1]; 
        int currentXTemp = getRobotPos()[0];
        
        for (int i = 0; i < 2; i++) {
            if (orientation == 3)//South
            {
                if(currentYTemp + 1 != temp.length && temp[currentYTemp + 1][currentXTemp] && !temp[currentYTemp][currentXTemp - 1])
                    return "Down";
                else if(currentXTemp + 1 != temp[0].length && !temp[currentYTemp][currentXTemp-1])
                    orientation++;
                else {
                    orientation--;
                    return "Left";
                }
            }
        
            if (orientation == 4)//East
            {
                if(currentXTemp + 1 != temp[0].length && temp[currentYTemp][currentXTemp + 1] && !temp[currentYTemp + 1][currentXTemp])
                    return "Right";
                else if(currentYTemp != 0 && !temp[currentYTemp + 1][currentXTemp])
                    orientation = 1;
                else {
                    orientation--;
                    return "Down";
                }
            }
        
            if (orientation == 1)//North
            {
                if (currentYTemp != 0 && temp[currentYTemp - 1][currentXTemp] && !temp[currentYTemp][currentXTemp + 1])
                	return "Up";
                else if(currentXTemp != 0 && !temp[currentYTemp][currentXTemp + 1])
                    orientation++;
                else {
                    orientation = 4; 
                    return "Right";
                }
            }
        
            if(orientation == 2)//West
            {
                if (currentXTemp != 0 && temp[currentYTemp][currentXTemp - 1] && !temp[currentYTemp - 1][currentXTemp])
                   return "Left";
                else if (currentYTemp + 1 != temp.length && !temp[currentYTemp - 1][currentXTemp])
                   orientation++;
                else {
                   orientation--;
                   return "Up";
                }
            }
        }
        return "no move";
    }
    //North=1
    //West=2
    //South=3
    //East=4
    
    public int getOrientation()
    {
        return orientation;
    }
}
