class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[][] distance = new int[maze.length][maze[0].length];
        for(int[] row : distance){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        distance[start[0]][start[1]] = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start[0], start[1]});
        while(!q.isEmpty()){
            int[] point = q.poll();
            for(int[] dir : dirs){
                int x = point[0] + dir[0];
                int y = point[1] + dir[1];
                int count = 0;
                while(x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0){
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                //if start to this point is smaller than this point's direct or prev distance(why we need to - dir0 and diry? because in while loop before we beyond the wall of maze) 
                if(distance[point[0]][point[1]] + count < distance[x - dir[0]][y - dir[1]]){
                    distance[x - dir[0]][y - dir[1]] = distance[point[0]][point[1]] + count;
                    q.add(new int[]{x - dir[0], y - dir[1]});
                }
            }
        }
        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distance[destination[0]][destination[1]];
    }
}
