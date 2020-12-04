class Solution {
    int len;
    public boolean splitArraySameAverage(int[] A) {
        this.len = A.length;
        if(len == 1) return false;
        int sum = 0;
        for(int num : A){
            sum += num;
        }
        Arrays.sort(A);
        //why not use target = sum / len? because it probably couldn't be divide exactly,so we need to find a method to eliminate this kind of situation
        //but how? thik about that: sumB/lenB == sumC/lenC ==> Sum/len = sumC/lenC,
        //so that sumC== sumB == Sum * lenC / len,
        //and sumC and sumB must be a Integer, 
        //which means if Sum * lenC % len != 0, we don't use it as our target.
        //Now Sum is immutable, len is immutable, only lenC means how many elements we need to add to the subset, it could be 1 to len - 1, so we can do some pruning
        for(int lenB = 1; lenB < len - 1; lenB++){
            if(sum * lenB % len != 0) continue; //pruning, ensure sumB must be an integer;
            int sumB = sum * lenB / len;
            //if we can find a subset, return true
            if(dfs(A, sumB, lenB, 0)) return true;
        }
        //we couldn't find a subset
        return false;
    }
    private boolean dfs(int[] A, int sumB, int lenB, int index){
        //when we find one subset? based on this subset B, we make sumB and lenB as our targets to find all elements of B, so if sumB and lenB equal 0 at the same time, we find one
        if(sumB == 0 && lenB == 0) return true;
        //if only one arrived 0, return false
        if(sumB == 0 || lenB == 0) return false;
        //or after scaning all the elements, we couldn't find a right subset
        if(index == len) return false;
        
        //case 1: if the element of index can be choosen and can find a subset in the after
        if(dfs(A, sumB- A[index], lenB - 1, index + 1)) return true;
        //case 2, the element of index could not be choosen to subset B
        //tips, if A[index] == A[index + 1], means A[index + 1] couldn't be choosen too
        int i = index;
        while(i < len && A[i] == A[index]) i++;
        if(dfs(A, sumB, lenB, i))return true;
        return false;
    }
}
