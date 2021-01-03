class Solution {
    public int findLatestStep(int[] arr, int m) {
        int res = -1, n = arr.length;
        //length[] represent each 1s group reflected by arr
        //length:[0,0,3,3,3,0,0] means from index 2 to 4, there is a 1s group length 3
        //count[] represent each counts which use the index as the length
        //count:[-2,1,0,0,0,0] means there are 1 group of 1s has the length of 1
        
        //why n + 2? because for each step, we need to know both of the right and left parts of the array's 1s group's length. In the begining we need n position to store every 1s. And for position 1, the left part's position is 0(now we need n+1 position to store 0), for position n, the right part's positon is n + 1(now we need n + 1 + 1 position to store them)
        int[] length = new int[n + 2];
        //why n + 1? because count[] represent the counts of each length's group. If the n is 5, count[5] represents the number of groups which has the length of 5. And if 5 is the biggest one, we need have 5 + 1 position to store them.
        int[] count = new int[n + 1];
        for(int i = 0; i < n; i++){
        //prepare a as the index for which one we will change to 1
        //so that left is a 1s group which at a-1 position and have the length of length[a - 1];
        // right is a 1s group which at a+1 position and have the length of length[a + 1];
            int a = arr[i], left = length[a - 1], right = length[a + 1];
            //after get the left and right part of 1s group, update the a position to 1
            //so the length of 1s group which inclde a position is left + right + 1;
            length[a] = left + right + 1;
            //up date the start position of this 1s group for next round
            length[a - left] = length[a];
            //up date the end position of this 1s group for next round
            length[a + right] = length[a];
            //corresponding to the left and right's merge, decrease the counts of original left and right
            count[left]--;
            count[right]--;
            //then update the length of group which include a position
            count[length[a]]++;
            //because we wanna find the last step, means when the count[m] changes from 0 to positive, we must have a answer;
            //when the count[m] changes from positive to 0 at the last time, we got the answer we want
            if(count[m] > 0) res = i + 1;
        }
        return res;
    }
}
