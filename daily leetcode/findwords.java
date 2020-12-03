class Solution {

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        //build Trie based on words
        TrieNode root = buildTrie(words);
        //for every char in board, dfs backtrack
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }
    private void dfs(char[][] board, int i, int j, TrieNode p, List<String> res){
        //take the char at i,j, prepare for backTrack
        char c = board[i][j];
        //if this position has been visited or there are no node on Trie, return
        if(board[i][j] == '#' || p.next[c - 'a'] == null) return;
        //or go to this TrieNode,
        p = p.next[c - 'a'];
        //check this node if it is a word, if it is, add to the res
        if(p.word != null){
            res.add(p.word);
            //if we found this is the word, we use null to make sure there are no dupicate word
            p.word = null;
        }
        //we use board itself as the visited[][]
        board[i][j] = '#';
        //dfs to every directions
        if(i > 0) dfs(board, i - 1, j, p, res);
        if(j > 0) dfs(board, i, j - 1, p, res);
        if(i < board.length - 1) dfs(board, i + 1, j, p, res);
        if(j < board[0].length - 1) dfs(board, i, j + 1, p, res);
        board[i][j] = c;
        
        
    }
    private TrieNode buildTrie(String[] words){
        TrieNode root = new TrieNode();
        //for every word in words
        for(String str : words){
            //we prepare a node p
            TrieNode p = root;
            //for every chars in word,
            for(char c : str.toCharArray()){
                //we put it into the position
                int i = c - 'a';
                if(p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
            }
            p.word = str;
        }
        return root;
    }
    //TrieNode, we just need to know this node has 26 possible chars
    //if the position of char in this node is null, means no such word
    //for convienence, if this TrieNode could be a word, store this word
    class TrieNode{
        TrieNode[] next = new TrieNode[26];
        String word;
    }
}
