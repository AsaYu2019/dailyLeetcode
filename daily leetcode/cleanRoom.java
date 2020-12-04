
class Solution {
    //because we don't know where the robot is, so we use robot's current position as the original point of this coordinate, so that (x,y) represents its location, and evrey time we add 1 or -1 to (row,col), means robot move one step;
    //so (-1,0) means move one step to up;
    //(0,1) means move one step to right;
    //(1,0) means move one step to down;
    //(0,-1) means move one step to left;
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    //use hashSet of pairs (row, col) to check this room is visited or not
    Set<Pair<Integer, Integer>> visited = new HashSet<>();
    Robot robot;
    private void goback(){
        //if there is no room to get into, we need to goback
        //inorder to goback, we need to make robot turn 180 degree
        robot.turnRight();
        robot.turnRight();
        robot.move();
        //after we back to the last positon, we need to turn around to make robot at the last direction, and prepare to go to another next room
        robot.turnRight();
        robot.turnRight();
    }
    private void backtrack(int row, int col, int d){
        visited.add(new Pair(row, col));
        robot.clean();
        //we have 4 directions
        for(int i = 0; i < 4; i++){
            // %4 to make sure no overflow
            int newD = (d + i) % 4;
            //new rooms position
            int newRow = row + directions[newD][0];
            int newCol = col + directions[newD][1];
            //if this new room hasn't visited and robot moves into it
            if(!visited.contains(new Pair(newRow, newCol)) && robot.move()){
                //recursively do the backtrack based on this room
                backtrack(newRow, newCol, newD);
                //if every room around this room has been visited, go back 
                goback();
            }
            //back to last position, clockwised to search next room
            robot.turnRight();
        }
    }

    
    public void cleanRoom(Robot robot) {
        this.robot = robot;
        backtrack(0, 0, 0);
    }
}
