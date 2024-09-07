package conwaygame;
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

        // WRITE YOUR CODE HERE
        StdIn.setFile(file);

        int r = StdIn.readInt();
        int c = StdIn.readInt();
        
        grid = new boolean[r][c];

        for (int m = 0; m<r; m++){
            for(int l = 0; l<c; l++){
                boolean a = StdIn.readBoolean();
                grid[m][l]=a;
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

        // WRITE YOUR CODE HERE
        boolean n = grid[row][col];
        return n; // update this line, provided so that code compiles
    }

    /**
     * Returns true if there are any alive cells in the grid
     * @return true if there is at least one cell alive, otherwise returns false
     */
    public boolean isAlive () {

        // WRITE YOUR CODE HERE
       
        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++)
                if(grid[i][j])
                   return true;
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
    public int numOfAliveNeighbors1 (int row, int col) {

        // WRITE YOUR CODE HERE
        int rowsize = grid.length;
        int colsize = grid[0].length;
        int totalAliveNeighbors = 0;
        
        // horizontal neighbors
        if (col - 1 >= 0) 
           if (grid[row][col-1]==true) totalAliveNeighbors++;
        else 
           if(grid[row][colsize-1]) totalAliveNeighbors++;
        if (col+1 < colsize)
          if (grid[row][col+1])totalAliveNeighbors++;
        else
          if (grid[row][0]) totalAliveNeighbors++;
        
        // check the vertical neighbors
        if (row - 1 >= 0)
            if(grid[row-1][col]) totalAliveNeighbors++;
        else
            if(grid[rowsize-1][col]) totalAliveNeighbors++;
        if (row+1 < rowsize)
            if(grid[row+1][col]) totalAliveNeighbors++;
        else
            if (grid[0][col]) totalAliveNeighbors++;
          
        // Check Diagonal 1
         
        if (row-1 >= 0 && col-1 >= 0)
          if (grid[row-1][col-1]) totalAliveNeighbors++;
        else if (row-1 >= 0 && col-1 < 0)
          if (grid[row-1][colsize-1]) totalAliveNeighbors++;
        else if (row-1 < 0 && col-1 >= 0)
          if (grid[0][col-1]) totalAliveNeighbors++;
        else 
          if (grid[rowsize-1][colsize-1]) totalAliveNeighbors++;
        
        if (row+1<rowsize && col+1 < colsize)
          if (grid[row+1][col+1]) totalAliveNeighbors++;
        else if (row+1<rowsize && col+1 >= colsize)
          if (grid[row+1][0]) totalAliveNeighbors++;
        else if (row+1 >= rowsize  && col+1<colsize)
          if (grid[0][col+1]) totalAliveNeighbors++;
        else 
          if (grid[0][0]) totalAliveNeighbors++;
        
        // Check diagonal 2
        if (row-1 >= 0 && col+1< colsize)
           if (grid[row-1][col+1]) totalAliveNeighbors++;
        else if (row-1 >=0 && col+1 >= colsize)
            if (grid[row-1][colsize]) totalAliveNeighbors++;
        else if (row-1 == -1 && col+1 < colsize)
             if (grid[0][col+1]) totalAliveNeighbors++;
         if (grid[0][colsize-1]) totalAliveNeighbors++;
          

        if (row+1 < rowsize && col-1 >= 0)
           if (grid[row+1][col-1]) totalAliveNeighbors++;
        else if (row+1 < rowsize && col-1 == -1)
            if (grid[row+1][0]) totalAliveNeighbors++;
        else if (row+1 >= rowsize && col-1 >= 0)
            if (grid[rowsize-1][col-1]) totalAliveNeighbors++;
        if (grid[rowsize-1][0]) totalAliveNeighbors++;
        return totalAliveNeighbors; // update this line, provided so that code compiles
    }
    
    private int indexWithInBoundry(int idx, int boundry)
    {
        if (idx == -1) return boundry-1;
        if (idx == boundry) return 0;
        return idx;
    }
    public int numOfAliveNeighbors (int row, int col) {
         return getAliveNeighbors(row, col).size();
    }
    private ArrayList<Integer[][]> getAliveNeighbors ( int row, int col) {

        // WRITE YOUR CODE HERE
        int rowsize = grid.length;
        int colsize = grid[0].length;
        ArrayList <Integer[][]> neighbors = new ArrayList<Integer[][]>();
        Integer[][] activeNeighbors = new Integer[1][2];
        int current = 0;
        //int idx = 0;
        int current2 = 0;
        // horizontal neighbors
        current = indexWithInBoundry(col-1, colsize);
        if (grid[row][current]) 
        {
            activeNeighbors[0][0] = row;
            activeNeighbors[0][1] = current;
            neighbors.add(activeNeighbors);
            activeNeighbors = new Integer[1][2];
            //idx++;
        }
        current = indexWithInBoundry(col+1, colsize);
        if (grid[row][current])
        {
            activeNeighbors[0][0] = row;
            activeNeighbors[0][1] = current;
            neighbors.add(activeNeighbors);
            activeNeighbors = new Integer[1][2];
        }

        // check the vertical neighbors
        current = indexWithInBoundry(row-1, rowsize);
        if (grid[current][col]) 
        {
            activeNeighbors[0][0] = current;
            activeNeighbors[0][1] = col;


            neighbors.add(activeNeighbors);
            activeNeighbors = new Integer[1][2];
        }
        current = indexWithInBoundry(row+1, rowsize);
        if (grid[current][col])
        {
            activeNeighbors[0][0] = current;
            activeNeighbors[0][1] = col;
            neighbors.add(activeNeighbors);
            activeNeighbors = new Integer[1][2];
        }

         // Check Diagonal 1
        current = indexWithInBoundry(row-1, rowsize);
        current2 = indexWithInBoundry(col-1, colsize);
        if (grid[current][current2])
        {
            activeNeighbors[0][0] = current;
            activeNeighbors[0][1] = current2;
            neighbors.add(activeNeighbors);
            activeNeighbors = new Integer[1][2];
         }
       
        current = indexWithInBoundry(row+1, rowsize);
        current2 = indexWithInBoundry(col+1, colsize);
        if (grid[current][current2])
        {
            activeNeighbors[0][0] = current;
            activeNeighbors[0][1] = current2;
            neighbors.add(activeNeighbors);
            activeNeighbors = new Integer[1][2];
            
        }
       
        current = indexWithInBoundry(row-1, rowsize);
        current2 = indexWithInBoundry(col+1, colsize);
        if (grid[current][current2])
        {
            activeNeighbors[0][0] = current;
            activeNeighbors[0][1] = current2;
            neighbors.add(activeNeighbors);
            activeNeighbors = new Integer[1][2];
            
        }
       
        current = indexWithInBoundry(row+1, rowsize);
        current2 = indexWithInBoundry(col-1, colsize);
        if (grid[current][current2])
        {
            activeNeighbors[0][0] = current;
            activeNeighbors[0][1] = current2;
            neighbors.add(activeNeighbors);
            activeNeighbors = new Integer[1][2];
        }
       
        return neighbors; // update this line, provided so that code compiles
    }


    /**
     * Creates a new grid with the next generation of the current grid using 
     * the rules for Conway's Game of Life.
     * 
     * @return boolean[][] of new grid (this is a new 2D array)
     */
    public boolean[][] computeNewGrid () {

        // WRITE YOUR CODE HERE
        int rsi = grid.length;
        int csi = grid[0].length;
        boolean[][] newgrid = new boolean[rsi][csi];
        for(int i = 0; i < rsi; i++){
            for(int t = 0; t < csi; t++){
                newgrid[i][t] = applyrules(i,t);
            }
        }
        return newgrid;
    }
    private boolean applyrules(int rows, int coll){
            boolean ali = grid[rows][coll];
            int aliveneigh = numOfAliveNeighbors(rows, coll);
            
            if (ali && aliveneigh >= 4)
                return false;
            if (ali && aliveneigh >= 2)
                return true;
            if (ali && aliveneigh <= 1)
                return false;
            if (ali){
                return false;
            }
            else{
                if(aliveneigh == 3)
                return true;
            }
                
                
            return ali;
        }


    /**
     * Updates the current grid (the grid instance variable) with the grid denoting
     * the next generation of cells computed by computeNewGrid().
     * 
     * Updates totalAliveCells instance variable
     */
    public void nextGeneration () {

        // WRITE YOUR CODE HERE
        grid = computeNewGrid();
    }

    /**
     * Updates the current grid with the grid computed after multiple (n) generations. 
     * @param n number of iterations that the grid will go through to compute a new grid
     */
    public void nextGeneration (int n) {

        for (int i = 0; i < n; i++)
        {
            nextGeneration();
        }
    }

    /**
     * Determines the number of separate cell communities in the grid
     * @return the number of communities in the grid, communities can be formed from edges
     */
    public int numOfCommunities() {

        // WRITE YOUR CODE HERE
        
        int totalAliveNeighbors =0;
        ArrayList<Integer> roots = new ArrayList<Integer>();

         
        //find two cells that requires to be connected
        WeightedQuickUnionUF unionUF = new WeightedQuickUnionUF(grid.length, grid[0].length);
        for (int i =0; i<grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++)
            {
                if(getCellState(i, j)) {
                   
                   ArrayList<Integer[][]> neighbors = getAliveNeighbors(i,j); 
                   for (int idx = 0; idx < neighbors.size(); idx++)
                   {
                    Integer[][] neighborItem = neighbors.get(idx);
                    unionUF.union(i,j,neighborItem[0][0].intValue(),neighborItem[0][1].intValue()  );
                   }
                   int l = unionUF.find(i,j);
                   if (!roots.contains(l)) roots.add(l);
                }

               
            }

        }
        return roots.size();
    }
                   
                       
              
        
}