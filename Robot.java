import java.util.*;
public abstract class Robot
{
    public Robot(int startX, int startY)
    {
       this.startX=startX;
       this.startY=startY;
       currentX=this.startX;
       currentY=this.startY;
    }
    
    public abstract void move(Maze m);
    
    public boolean isDone(Maze m)
    {
        if(currentX!=startX || currentY!=startY)
        {
            if(currentY==m.getMaze().length-1 || currentX==m.getMaze()[0].length-1 || currentY==0 || currentX ==0)
                return true;
        }
        return false;
    }
    
    public int[] getRobotPos()
    {
        int[] pos={currentX, currentY};
        return pos;
    }
    
    public int[] getRobotStartPos()
    {
        int []pos={startX, startY};
        return pos;
    }
    
    public void setPos(int newX, int newY)
    {
        currentX=newX;
        currentY=newY;
    }
    
    private int startX; 
    private int startY;
    private int currentX; 
    private int currentY;
}