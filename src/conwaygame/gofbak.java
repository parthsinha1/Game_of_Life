package conwaygame;
import java.lang.reflect.Array;
import java.util.ArrayList;
/**
 * Conway's Game of Life Class holds various methods that will
 * progress the state of the game's board through it's many iterations/generations.
 *
 * Rules 
 * Alive cells with 0-1 neighbors die of loneliness.
 * Alive cells with >=4 neighbors die of overpopulation.
 * Alive cells with 2-3 neighbors survive.
 * Dead cells with exactly 3 neighbors become alive by reproduction.

 * @author Seth Kelley 
 * @author Maxwell Goldberg
 */
public class GameOfLife {

    // Instance variables
    private static final boolean ALIVE = true;
    private static final boolean  DEAD = false;

    private boolean[][] grid;    // The board has the current generation of cells
    private int totalAliveCells; // Total number of alive cells in the grid (board)

    /**
    * Default Constructor which creates a small 5x5 grid with five alive cells.
    * This variation does not exceed bounds and dies off after four iterations.
    */
    public GameOfLife() {
        grid = new boolean[5][5];
        totalAliveCells = 5;
        grid[1][1] = ALIVE;
        grid[1][3] = ALIVE;
        grid[2][2] = ALIVE;
        grid[3][2] = ALIVE;
        grid[3][3] = ALIVE;
    }

    /**
    * Constructor used that will take in values to create a grid with a given number
    * of alive cells
    * @param file is the input file with the initial game pattern formatted as follows:
    * An integer representing the number of grid rows, say r
    * An integer representing the number of grid columns, say c
    * Number of r lines, each containing c true or false values (true denotes an ALIVE cell)
    */
    public GameOfLife (String file) {

        StdIn.setFile(file);

        int r = StdIn.readInt();
        int c = StdIn.readInt();
        grid = new boolean[r][c];

        for (int i = 0; i < r; i++){
            for (int j = 0; j < c; j++){
                boolean a = StdIn.readBoolean();
                grid[i][j] = a;
            }
        }
        }

    /**
     * Returns grid
     * @return boolean[][] for current grid
     */
    public boolean[][] getGrid () {
        return grid;
    }
    
    /**
     * Returns totalAliveCells
     * @return int for total number of alive cells in grid
     */
    public int getTotalAliveCells () {
        return totalAliveCells;
    }

    /**
     * Returns the status of the cell at (row,col): ALIVE or DEAD
     * @param row row position of the cell
     * @param col column position of the cell
     * @return true or false value "ALIVE" or "DEAD" (state of the cell)
     */
    public boolean getCellState (int row, int col) {

        boolean p = grid[row][col];

        return p; // update this line, provided so that code compiles
    }

    /**
     * Returns true if there are any alive cells in the grid
     * @return true if there is at least one cell alive, otherwise returns false
     */
    public boolean isAlive () {

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if (grid[i][j]){
                    return true;
                }
                                   
                }                                
                } 
                
            
        

        return false; // update this line, provided so that code compiles
    }

    /**
     * Determines the number of alive cells around a given cell.
     * Each cell has 8 neighbor cells which are the cells that are 
     * horizontally, vertically, or diagonally adjacent.
     * 
     * @param col column position of the cell
     * @param row row position of the cell
     * @return neighboringCells, the number of alive cells (at most 8).
     */
    public int numOfAliveNeighbors (int row, int col) {
        int rs = grid.length;
        int cs = grid[0].length;
        int neighbors = 0;
        
        // horizontal 
        if (col - 1 >= 0) 
           if (grid[row][col-1]==true) 
           neighbors++;
        else 
           if(grid[row][cs-1]) 
           neighbors++;
        if (col+1 < cs)
          if (grid[row][col+1])
          neighbors++;
        else
          if (grid[row][0]) 
          neighbors++;
        
        //vertical 
        if (row - 1 >= 0)
            if(grid[row-1][col]) 
            neighbors++;
        else
            if(grid[rs-1][col]) 
            neighbors++;
        if (row+1 < rs)
            if(grid[row+1][col]) 
            neighbors++;
        else
            if (grid[0][col]) 
            neighbors++;
          
        //diagonal right 
         
        if (row-1 >= 0 && col-1 >= 0)
          if (grid[row-1][col-1]) 
          neighbors++;
        else if (row-1 >= 0 && col-1 < 0)
          if (grid[row-1][cs-1]) 
          neighbors++;
        else if (row-1 < 0 && col-1 >= 0)
          if (grid[0][col-1]) 
          neighbors++;
        else 
          if (grid[rs-1][cs-1]) 
          neighbors++;
        
        if (row+1<rs && col+1 < cs)
          if (grid[row+1][col+1]) 
          neighbors++;
        else if (row+1<rs && col+1 >= cs)
          if (grid[row+1][0]) 
          neighbors++;
        else if (row+1 >= rs  && col+1<cs)
          if (grid[0][col+1]) 
          neighbors++;
        else 
          if (grid[0][0]) 
          neighbors++;
        
        //diagonal left 
        if (row-1 >= 0 && col+1< cs)
           if (grid[row-1][col+1]) 
           neighbors++;
        else if (row-1 >=0 && col+1 >= cs)
            if (grid[row-1][cs]) 
            neighbors++;
        else if (row-1 == -1 && col+1 < cs)
             if (grid[0][col+1]) 
             neighbors++;
         if (grid[0][cs-1]) 
         neighbors++;
          

        if (row+1 < rs && col-1 >= 0)
           if (grid[row+1][col-1]) 
           neighbors++;
        else if (row+1 < rs && col-1 == -1)
            if (grid[row+1][0]) 
            neighbors++;
        else if (row+1 >= rs && col-1 >= 0)
            if (grid[rs-1][col-1]) 
            neighbors++;
        if (grid[rs-1][0]) 
        neighbors++;
        return neighbors;
    }
     

    

    /**
     * Creates a new grid with the next generation of the current grid using 
     * the rules for Conway's Game of Life.
     * 
     * @return boolean[][] of new grid (this is a new 2D array)
     */
    public boolean[][] computeNewGrid () {

        int rs = grid.length;
        int cs = grid[0].length;
        boolean[][] n = new boolean[rs][cs];
        
        for (int i = 0; i < rs; i++){
            for (int j = 0; j < cs; j++){
                n[i][j] = rules(i,j);
                


            }
        }
        return n;// update this line, provided so that code compiles
    }
    private boolean rules(int rows, int col){
        boolean a = grid[rows][col];
        int n = numOfAliveNeighbors(rows, col);
        
        if (a && n >= 4)
            return false;
        if (a && n >= 2)
            return true;
        if (a && n <= 1)
            return false;
        if (a){
            return false;
        }
        else{
            if(n == 3)
            return true;
        }
            
            
        return a;
    }

    /**
     * Updates the current grid (the grid instance variable) with the grid denoting
     * the next generation of cells computed by computeNewGrid().
     * 
     * Updates totalAliveCells instance variable
     */
    public void nextGeneration () {

        grid = computeNewGrid();
    }

    /**
     * Updates the current grid with the grid computed after multiple (n) generations. 
     * @param n number of iterations that the grid will go through to compute a new grid
     */
    public void nextGeneration (int n) {

        for (int i = 0; i <= n; i++){
            nextGeneration();
        }
    }

    /**
     * Determines the number of separate cell communities in the grid
     * @return the number of communities in the grid, communities can be formed from edges
     */
    public int numOfCommunities() {
        int row = grid.length;
        int col = grid[0].length;
        WeightedQuickUnionUF lol = new WeightedQuickUnionUF(row, col);
        for (int i =0; i<grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++)
            {
                int rs = grid.length;
                int cs = grid[0].length;
                   if (col - 1 >= 0) 
                    if (grid[row][col-1]==true) 
                        lol.union(i,j,row,col-1);           
                 else 
                    if(grid[row][cs-1]) 
                       lol.union(i,j,row,cs-1);
                 if (col+1 < cs)
                   if (grid[row][col+1])
                       lol.union(i,j,row,col+1);
                 else
                   if (grid[row][0]) 
                       lol.union(i,j,row,0);
                 
                 //vertical
                 if (row - 1 >= 0)
                    if (grid[row-1][col]) 
                        lol.union(i,j,row-1,col);
                 else
                    if (grid[rs-1][col]) 
                        lol.union(i,j,rs-1,col);
                 if (row+1 < rs)
                    if (grid[row+1][col]) 
                        lol.union(i,j,row+1,col);
                 else
                     if (grid[0][col]) 
                        lol.union(i,j,0,col);
                   
                 //diagonal right
                  
                 if (row-1 >= 0 && col-1 >= 0)
                   if (grid[row-1][col-1]) 
                        lol.union(i,j,row-1,col-1);
                 else if (row-1 >= 0 && col-1 < 0)
                   if (grid[row-1][cs-1]) 
                      lol.union(i,j,row-1,cs-1);
                 else if (row-1 < 0 && col-1 >= 0)
                   if (grid[0][col-1]) 
                      lol.union(i,j,0,col-1);
                 else 
                   if (grid[rs-1][cs-1]) 
                       lol.union(i,j,rs-1,cs-1);         
                 if (row+1<rs && col+1 < cs)
                   if (grid[row+1][col+1]) 
                       lol.union(i,j,row+1,col+1);
                 else if (row+1<rs && col+1 >= cs)
                   if (grid[row+1][0]) 
                       lol.union(i,j,row+1,0);
                 else if (row+1 >= rs  && col+1<cs)
                   if (grid[0][col+1]) 
                      lol.union(i,j,0,col+1);
                 else 
                   if (grid[0][0]) 
                     lol.union(i,j,0,0);
                 
                 //diagonal left
                 if (row-1 >= 0 && col+1< cs)
                    if (grid[row-1][col+1]) 
                        lol.union(i,j,row-1,col+1);
                 else if (row-1 >=0 && col+1 >= cs)
                     if (grid[row-1][cs]) 
                        lol.union(i,j,row-1,cs);
                 else if (row-1 == -1 && col+1 < cs)
                      if (grid[0][col+1]) 
                        lol.union(i,j,0,col+1);
                  if (grid[0][cs-1]) 
                    lol.union(i,j,0,cs-1);
                   
         
                 if (row+1 < rs && col-1 >= 0)
                    if (grid[row+1][col-1]) 
                        lol.union(i,j,row+1,col-1);
                 else if (row+1 < rs && col-1 == -1)
                     if (grid[row+1][0]) 
                        lol.union(i,j,row+1,0);
                 else if (row+1 >= rs && col-1 >= 0)
                     if (grid[rs-1][col-1]) 
                        lol.union(i,j,rs-1,col-1);
                 if (grid[rs-1][0]) 
                    lol.union(i,j,rs-1,0);
                }
      
                    }
                    int cnt=0;
                    ArrayList<Integer> lmao = new ArrayList<>();
                    for(int i = 0; i < row; i++){
                        for(int j = 0; j < col; j++){
                           int n = lol.find(i,j);
                           if(n != lol.find(i,j))
                            lmao.add(n);
                            cnt++;
                        }
                        
            }
                return cnt;
        
      

            



       // update this line, provided so that code compiles
    }
}











