class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int row = maze.length, col = maze[0].length;
        //visited, no need to go back
        boolean[][] visited = new boolean[row][col];
        return dfs(maze, start, destination, visited);
    }
    private boolean dfs(int[][] maze, int[] start, int[] dest, boolean[][] visited){
        if(visited[start[0]][start[1]]) return false;
        if(start[0] == dest[0] && start[1] == dest[1]) return true;
        //only set the point where the ball stopped
        visited[start[0]][start[1]] = true;
        //four direction
        int r = start[1] + 1, l = start[1] - 1, up = start[0] - 1, down  = start[0] + 1;
        //move to right
        while(r < maze[0].length && maze[start[0]][r] == 0) r++;
        //where is the ball now? it's at start[0], r - 1 position, because r is the wall or obstacle now, so we need to move back for one step
        if(dfs(maze, new int[]{start[0], r - 1}, dest, visited)) return true;
        //move to left
        while(l >= 0 && maze[start[0]][l] == 0) l--;
        if(dfs(maze, new int[]{start[0], l + 1}, dest, visited)) return true;
        //move to up
        while(up >= 0 && maze[up][start[1]] == 0) up--;
        if(dfs(maze, new int[]{up+1, start[1]}, dest, visited)) return true;
        //move to down
        while(down < maze.length && maze[down][start[1]] == 0) down++;
        if(dfs(maze, new int[]{down-1, start[1]}, dest, visited)) return true;
        //or false;
        return false;
    }
}
