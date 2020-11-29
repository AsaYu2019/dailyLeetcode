class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> st = new Stack<>();
        HashMap<Integer, Integer> hm = new HashMap<>();
        int[] res = new int[nums1.length];
        
        for(int i = 0; i < nums2.length; i++){
            //for every element of nums1, find the next greater one ,store to the map as key-val
            while(!st.isEmpty() && nums2[i] > st.peek())
                hm.put(st.pop(), nums2[i]);
            //if this one is smaller, push it to stack to prepare next greater
            st.push(nums2[i]);
        }
        //when touch the tail, if the stack is not empty,all the elements of it have no next greater
        while(!st.isEmpty()) hm.put(st.pop(), -1);
        //store them to res;
        for(int i = 0; i < nums1.length; i++){
            res[i] = hm.get(nums1[i]);
        }
        return res;
    }
}
