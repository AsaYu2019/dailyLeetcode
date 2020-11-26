class Solution {
    boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        if(row * col < word.length()) return false;
        boolean res = false;
        visited = new boolean[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                res = res | backTracking(board, word, i, j, 0);
                if(res) return true;
            }
        }
        return false;
    }
    private boolean backTracking(char[][] board, String word, int row, int col, int index){
        if(row < 0 || col < 0 || row > board.length - 1 || col > board[0].length - 1) return false;
        if(visited[row][col] || board[row][col] != word.charAt(index)) return false;
        if(index == word.length() - 1){
            if(board[row][col] == word.charAt(index)) return true;
            return false;
        }
        visited[row][col] = true;
        boolean res = backTracking(board, word, row - 1, col, index + 1) ||
                      backTracking(board, word, row + 1, col, index + 1) ||
                      backTracking(board, word, row, col - 1, index + 1) ||
                      backTracking(board, word, row, col + 1, index + 1);
        visited[row][col] = false;
        return res;
    }
}
