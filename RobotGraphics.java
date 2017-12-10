import javax.swing.*;
import java.awt.*;

public class RobotGraphics extends JFrame
{
    private JLabel character;
    private JLabel numMoves; 
    private JLabel counter;
    private Maze maze;
    private Robot r;
    
    public RobotGraphics(Maze m, Robot r)
    {
        maze=m;
        this.r=r;
        boolean [][] maze1=maze.getMaze();
        setLayout(null);
        
        ImageIcon marioWall=new ImageIcon("images/MarioBlock.png");
        
        ImageIcon marioSpace=new ImageIcon("images/MarioOpenBlock.png");
        
        ImageIcon player=new ImageIcon("images/MarioRunSouth1.png");
        character=new JLabel(player);
        character.setSize(16,16);
        
        setTitle("Robot Maze");  
        setSize(500, 300);  
        getContentPane().setBackground(Color.lightGray);
        
        int count=0;
        while(!r.isDone(m))
        {
            if(count!=0)
                r.move(m);
            for(int row=0; row<maze1.length; row++)
            {
                for(int col=0; col<maze1[0].length; col++)
                {
                    if(row==r.getRobotPos()[1] && col==r.getRobotPos()[0])
                    {
                        character.setLocation(10+(col*17), 10+(row*17));
                        add(character);
                    }
                      
                        else if(maze1[row][col]==false)
                        {
                             JLabel wall=new JLabel(marioWall);
                             wall.setSize(new Dimension(16, 16));
                             wall.setLocation(10+(col*17), 10+(row*17));
                             add(wall);
                        }
                        else
                        {
                            JLabel space=new JLabel(marioSpace);
                            space.setSize(new Dimension(16 ,16));
                            space.setLocation(10+(col*17), 10+(row*17));
                            add(space);
                        }
                    }
            }
            count++;
            numMoves=new JLabel("Number of Moves");
            numMoves.setSize(new Dimension(100, 10));
            numMoves.setLocation(20+(maze1[0].length*17), 10);
            add(numMoves);
            
            counter=new JLabel((count-1)+"");
            counter.setSize(new Dimension(100,10));
            counter.setLocation(60+(maze1[0].length*17), 25);
            add(counter);
            try
            {
                repaint();
                setVisible(true);
                Thread.sleep(100);
                getContentPane().removeAll();
                revalidate();
            }
            catch(InterruptedException e){}
        }
        
        //JPanel gameOver=new JPanel();
    }
    
    public static void main(String []args)
    {
        boolean [][]maze1={{false, false, false, false, false, false, false},{false, true, true, true, true, true, false},{false, false, false, true, false, true, false},{false, false, false ,true, false, true, false}};
        boolean[][] maze={{false, true, false, false, false, false, false, false, false},{false, true, true, true, true, true, false, true, false},{false, true, false, false, false, false, false, true, false},{false, true, false, true, false, true, true, true, false},{false, true, false, true, false, false, false, true, false},{false, true, true, true, false, true, true, true, false},{false, false, false, true, false, true, false, true, false},{false, true, true, true, true, true, false, true, false},{false, false, false, false, false, false, false, true, false}};
        Maze testMaze=new Maze(maze, 1, 0);
        Robot robo=new MemoryRobot(1, 0);
        RobotGraphics a=new RobotGraphics(testMaze, robo);
    }
    
    
    
    
    
}