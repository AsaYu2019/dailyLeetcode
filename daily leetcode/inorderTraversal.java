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
    /* recursive
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        inorder(root, res);
        return res;
    }
    private void inorder(TreeNode root, List<Integer> res){
        if(root == null) return;
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    } */
    //interating method with stack
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> st = new Stack<>();
        List<Integer> res = new LinkedList<>();
        TreeNode cur = root;
        while(cur != null || !st.isEmpty()){
            while(cur != null){
                st.push(cur);
                cur = cur.left;
            }
            cur = st.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }
}
