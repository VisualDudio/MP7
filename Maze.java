public class Maze {    
    private boolean[][] maze;
    private int startX;
    private int startY;
    
    public Maze(boolean[][] maze, int startX, int startY) {
       this.maze = maze; 
       this.startX = startX; 
       this.startY = startY;
    }
    
    public boolean[][] getMaze() {
       return maze; 
    }
    
    public int getStartX() {
        return startX;
    }
    
    public int getStartY() {
        return startY;
    }
}
