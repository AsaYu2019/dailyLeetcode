/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    //本题的题眼key：找到每个子树的length！
    /*
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        int N = pre.length;
        if(N == 0) return null;
        TreeNode root = new TreeNode(pre[0]);
        if(N == 1) return root;
        
        int L = 0;
        for(int i = 0; i < N; i++){
            if(post[i] == pre[1]){
                L = i + 1;
            }
        }
        root.left = constructFromPrePost(Arrays.copyOfRange(pre, 1, L + 1),Arrays.copyOfRange(post, 0, L));
        root.right = constructFromPrePost(Arrays.copyOfRange(pre, L + 1, N),Arrays.copyOfRange(post, L, N - 1));
        return root;
    }*/
    int[] pre, post;
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        this.pre = pre;
        this.post = post;
        return make(0,0,pre.length);
    }
    //i0本树root在pre中的起始位置，i1表示在post中的起始位置
    private TreeNode make(int i0, int i1, int N){
        if(N == 0) return null;
        TreeNode root = new TreeNode(pre[i0]);
        if(N == 1) return root;
        
        int L = 1;
        //排除第一个元素，因为我已经知道pre中该元素必为root，故此，我们需要找到post中与pre[1]位置相等的数，即为length
        for(; L < N; L++){
            if(post[i1 + L - 1] == pre[i0 + 1]) break;
        }
        //第一次make left时，pre的i0处已经给了root，但post的i1未变
        root.left = make(i0 + 1, i1, L );
        //make left后再make right时，pre中right的root需要跨越左子树的长度和root节点
        //post中的root也需要跨越左子树的长度，此时未make的部分只剩下总长 - left部分长度 - root节点
        root.right = make(i0 + L + 1, i1 + L, N - 1 - L);
        return root;
        
    }
}
