import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class ControllableRobotGraphics extends JFrame implements KeyListener, ActionListener {
	
	private ImageIcon characterImage;
	private ImageIcon marioWall;
	private ImageIcon marioSpace;
    private JLabel character;
    private JLabel numMoves; 
    private JLabel counter;
    private Maze maze;
    private Robot r;
    private int count;
    private int currentX;
    private int currentY;
    private boolean [][] maze1; 
    
    public ControllableRobotGraphics(Maze m, ControllableRobot r) {
        maze = m;
        this.r = r;
        if (r != null) {
	        currentX = r.getRobotPos()[0];
	        currentY = r.getRobotPos()[1];
        }
        maze1 = maze.getMaze();
        setLayout(null);
        
        marioWall = new ImageIcon("images/MarioBlock.png");
        
        marioSpace = new ImageIcon("images/MarioOpenBlock.png");
        
        setTitle("Robot Maze");  
        setSize(1000, 600);  
        getContentPane().setBackground(Color.lightGray);
        addKeyListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        numMoves = new JLabel("Number of Moves");
        numMoves.setSize(new Dimension(150, 10));
        numMoves.setLocation(20+(maze1[0].length*17), 10);
    }
    
    public void move() {
	    	count = 0;
	    do {
	    		if (!(r instanceof ControllableRobot)) {
	    			r.move(maze);
	    			currentX = r.getRobotPos()[0];
	    			currentY = r.getRobotPos()[1];
	    			count++;
	    		} else {
	    			r.setPos(currentX, currentY);
	    		}
	        for (int row = 0; row<maze1.length; row++)
	        {
	            for (int col = 0; col<maze1[0].length; col++)
	            {
	                if (row == r.getRobotPos()[1] && col == r.getRobotPos()[0])
	                {
	                    character.setLocation(10+(col*17), 10+(row*17));
	                    add(character);
	                } else if (maze1[row][col] == false) {
	                		JLabel wall = new JLabel(marioWall);
	                		wall.setSize(new Dimension(16,16));
	                		wall.setLocation(10+(col*17), 10+(row*17));
	                		add(wall);
	                } else {
	                		JLabel space = new JLabel(marioSpace);
	                		space.setSize(new Dimension(16,16));
	                		space.setLocation(10+(col*17), 10+(row*17));
	                		add(space);
	                }
	            }
	        }
	        add(numMoves);
	        counter = new JLabel(count+"");
	        counter.setSize(new Dimension(100,10));
	        counter.setLocation(60+(maze1[0].length*17), 25);
	        add(counter);
	        
	        try {
	            repaint();
	            setVisible(true);
	            Thread.sleep(100);
	            getContentPane().removeAll();
	            revalidate();
	        } catch (InterruptedException e) {}
	    } while (!r.isDone(maze));
        
        JOptionPane.showMessageDialog(null, "Congrats you have made it through the maze in " + count + " moves!");
    }
    
    public void keyTyped(KeyEvent e) {
       
    }
    
    public void keyPressed(KeyEvent e) {
    		if (r.isDone(maze) || !(r instanceof ControllableRobot)) {
    			return;
    		}
        int code = e.getExtendedKeyCode();
        if (currentY-1 >= 0 && maze1[currentY-1][currentX] && code == KeyEvent.VK_UP)
            currentY--;
        else if (maze1[currentY][currentX+1] && code == KeyEvent.VK_RIGHT)
            currentX++;
        else if (maze1[currentY+1][currentX] && code == KeyEvent.VK_DOWN)
            currentY++;
        else if (currentX-1 >= 0 && maze1[currentY][currentX-1] && code == KeyEvent.VK_LEFT)
            currentX--;
        else
            return;
        count++;
    }
    
    public void keyReleased(KeyEvent e) {
       
    }
    
    public void actionPerformed(ActionEvent e) {
        r.setPos(r.getRobotStartPos()[0], r.getRobotStartPos()[1]);
        count = 0;
    }
    
    public void setCharacterImage(ImageIcon icon) {
    		character = new JLabel(icon);
    		character.setSize(16,16);
    }
    
    public void setRobotType(Robot r) {
    		this.r = r;
    		currentX = r.getRobotStartPos()[0];
    		currentY = r.getRobotStartPos()[1];
    	}
    
    public static void main(String []args) {      
        boolean done = false;
        do {
        		boolean[][] maze={{false, true, false, false, false, false, false, false, false},{false, true, true, true, true, true, false, true, false},{false, true, false, false, false, false, false, true, false},{false, true, false, true, false, true, true, true, false},{false, true, false, true, false, false, false, true, false},{false, true, true, true, false, true, true, true, false},{false, false, false, true, false, true, false, true, false},{false, true, true, true, true, true, false, true, false},{false, false, false, false, false, false, false, true, false}};	 
        		Maze testMaze = new Maze(maze, 1, 0);
        		ControllableRobotGraphics robot = new ControllableRobotGraphics(testMaze, null);
        		
        		boolean correctCharacterInput = false;
        		while (!correctCharacterInput) {
        			ImageIcon characterIcon;
        			String character = JOptionPane.showInputDialog("Enter 1 for Mario, 2 for Luigi.");
        			if (character == null) {
        				System.exit(-1);
        			} else if (character.equals("1")) {
        				characterIcon = new ImageIcon("images/Mario.png");
        				robot.setCharacterImage(characterIcon);
        				correctCharacterInput = true;
        			} else if (character.equals("2")) {
        				characterIcon = new ImageIcon("images/Luigi.png");
        				robot.setCharacterImage(characterIcon);
        				correctCharacterInput = true;
        			} else {
        				continue;
        			}
        		}
        		
        		boolean correctRobotInput = false;
        		Robot r = null;
        		while (!correctRobotInput) {
        			String robo = JOptionPane.showInputDialog("Enter 1 for Controllable Robot, 2 for Random Robot, "
        					+ "3 for Memory Robot, and 4 for Right Hand Robot.");
        			if (robo == null) {
        				System.exit(-1);
        			} else if (robo.equals("1")) {
        				r = new ControllableRobot(1, 0);
        			} else if (robo.equals("2")) {
        				r = new RandomRobot(1, 0);
        			} else if (robo.equals("3")) {
        				r = new MemoryRobot(1, 0);
        			} else if (robo.equals("4")) {
        				r = new RightHandRobot(1, 0, 3);
        			} else {
        				continue;
        			}
        			correctRobotInput = true;
        		}
        		robot.setRobotType(r);
        		
        		robot.move();
        		String playAgain = JOptionPane.showInputDialog("Press N to quit, anything else to play again.");
        		done = playAgain == null || playAgain.toLowerCase().equals("n");
        } while (!done); 
        System.exit(-1);
    }
}