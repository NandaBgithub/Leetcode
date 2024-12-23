/*
My intuition
------------
- We are given a matrix data structure. Like any matrix, it can be treated a a graph with adjacent nodes being adjacent cells.
- We need to traverse this graph, but we need to do so to detect cycles. So it involves some sort of connectivity.
- Anytime connectivity is involved, I tend to immediately want to utilise a depth searching algorithm.

Before searching I would also take note of which directions I should go.

Suppose a simulation,
I start at the top left corner, and I want to check as many directions needed to see whether there is a cell that I can move to. When I check adjacent cells I can mark them as visited. A loop should only occur when I reach a cell that contains not only the same letter as my current cell
but also has been visited before.
If for some reason, there is no cycle, I move onto searching depth wise for the next element in the row until I exhaust all the possible elements in the grid.


PseudoCode
----------
containsCycle(grid){
    row 
    col
    boolean visited[row][col]
    // [up, down, right, left]
    directions = [[0,1], [0, -1], [1, 0], [-1,0]]

    for (i = 0; i < row; i++){
        for(j = 0; j < col; j++){
            if visited then skip to next iteration

            otherwise, breadthFirstSearch(currentCell)

            if breadthFirstSearch returns true, then there is a cycle, so return true
        }
    }
}

breadthFirstSearch(){
    mark the current cell as visited

    for each possible direction move to it
        if this current direction is making the current coordinate go out of bounds, skip iteration

        otherwise, check whether the next cell has the same letter
            if it doesnt, then skip iteration
            if it does, then check whether the cell has been visited
                if it has been visited and it is same letter, return true, we found a cycle
                    if it hasnt been visited, then breadth first search on this cell and set the previous cell to this current one so we do not end up searching this direction

                    if this breadth first search succeeds, return true, otherwise, skip to next iteration.

    by this point we should have searched all directions depth wise, and still have not found a cycle, so return false.
}




*/
class Solution {
    public boolean containsCycle(char[][] grid) {
        int row = grid.length;   
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];

        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if (visited[i][j]){
                    continue;
                }

                // Start the previous coordinates at -1,-1 since there is
                // no previous at the time of invocation
                if (dfs(grid, i, j, -1, -1, grid[i][j], visited)){
                    return  true;
                }
            }
        }

        return false;
    }

    private static final int[][] direction = {{0, 1}, {1,0}, {0,-1}, {-1,0}};

    private boolean dfs(char[][] grid, int i, int j, int prevI, int prevJ, 
        char c, boolean[][] visited){

        visited[i][j] = true;

        // Scan through all directions
        for (int[] dir: direction){
            // Coordinates of the next square
            int x = i + dir[0];
            int y = j + dir[1];

            // Check if next coordinates are out of bounds
            if (x < 0 || x == grid.length || y < 0 || y == grid[0].length){
                continue;
            }

            // Check if the next cordinates was our previous coordinates
            if (x == prevI && y == prevJ){
                continue;
            }

            // Check if next coordinates is the same letter
            if (grid[x][y] != c){
                continue;
            }
       
            // If next coordinates is the same letter, and has been visited
            // then we have a loop
            if (visited[x][y] && grid[x][y] == c){
                return true;    
            }

            // Otherwise, keep searching the next square
            if (dfs(grid, x, y, i, j, c, visited)){
                return true;
            }
        }

        return false;
    }
}

/*
Time complexity
In this case, the worst case for this approach is in cases where we would end up searching the entire grid, so approximately m*n where m is the number of rows and n is the number of columns.
 */