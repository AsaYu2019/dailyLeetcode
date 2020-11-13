class Solution {
    /*Think about dp[][] represent
      For row i, how many students in i are limited by i-1 row.
      The value of dp[i][j] should be the total max students can seat,
        -so the state function should be:
            --dp[i][j] = Math.max(dp[i][j], dp[i - 1][l] + students in i row)
            --because j students may have different location,so everytime we should max them
      Now we can check different situation one by one, from just 1 row.
        -but how could we represent the different situation of students' seats?
        -Actually we can use 1 represent this seat has a student, 0 for not 
        -so we can use binary bit mask to do that.
            --For i row, there are n seats, so 2^n - 1 means every seats are 1, 0 means no student
        -Now we can check the validation for each represets in the same row:
            --There couldn't be consencutive 1s.         ① 
            --Broken seat couldn't be taken by student   ②
        -But how?
            --Same with students, we can use binary bits represent seats situation
                --1 represents broken, 0 represents avaliable.
                --if binary seats & binary students != 0, means broken seats are taken, quit it
            --So how could we get the binary seats?
                --For every row seat represent by [][]seats, 
                --we can trave seats[i].length (that is n) from left to right
                --If seats[i][j] =='#', we set this position to 1, means 1<<k
                        --  k == n - j + 1 . eg. j = 0, 1 << n, got.  j = n - 1(max of j), 1<<0
                    --carry on, if there are another '#', we can use (seat |= 1<<k)
                        --1000 | 0010 = 1010, two broken seats.
            --For ②: if seat & student != 0, quit it.
            --For ①: if studetn & (student<<1) != 0 or student & (student>>1) != 0, quit
                -- eg: 0110==> 0110              0110
                            & 0110              & 0110
                              00100 != 0         00100 !=0
        -Now we can check the validation in two rows. prev represet prev row, cur for current row
            --cur needs to check the situation of prev, means leftprev and rightprev
                --For leftprev: if prev & (cur<<1) != 0, violate the rule, quit
                --For rightprev: if prev & (cur>>1) !=0, violate the rule, quit
                so it's (prev & (cur<<1) != 0)&&(prev & (cur>>1) !=0)
    Now we get from 0 row, 
        --which seats are broken,               (1)
        --and how could we organize students    (2)
        --and the relation between prev and cur (3)
        For the function:dp[i][j] = Math.max(dp[i][j], dp[i - 1][l] + students in i row),
        we need to know the "students in i row"
            --Using (1)(2)(3), we can iterate the situation get a valid students binary bit
            --Every bit placed by 1 represet a student.
            --So we can use "fast power" to get the number of '1' in student
                -- while(n !=0) n=n&(n - 1), count++; try use example to prove it
    Therefore, we can complete our dp method
    */
    public int maxStudents(char[][] seats) {
        int m = seats.length;
        int n = seats[0].length;
        //broken seats
        int[] seat = new int[m+1];//1 means 1st row
        for(int i = 1; i <= m; i++){
            for(int j = 0; j < n; j++){
                //in [][]seats, we need to caculate from 0 row, so i - 1;
                if(seats[i - 1][j] == '#'){
                    seat[i] |= (1<<n-j-1);
                }
            }
        }
        //we use bit represent students situation, so
        //  for n seats, 2^n - 1 means every seat has a student, (1111...)
        //  that is the number of student couldn't bigger than 2^n, say 1<<n
        int[][] dp = new int[m+1][1<<n];
        for(int i = 1; i <= m; i++){
            //for ith row, the situation of student (bit)
            for(int c = 0; c < (1 << n); c++){
                //check validation about ith row for itself
                //   || check the seat validation for ith row
                if(((c& (c>>1)) != 0) || ((c & seat[i])) != 0){
                    continue; //quit this two situation
                }
                //check every valid i - 1 th row's situation
                for(int prev = 0; prev < (1<<n); prev++){
                    //self check && broken seat && leftprev&&rightprev
                    if(((prev & (prev>>1)) == 0) && ((prev & seat[i - 1]) == 0)
                      && ((prev & (c<<1)) == 0) && ((prev & (c>>1)) == 0)){
                        dp[i][c] = Math.max(dp[i][c], dp[i - 1][prev] + countOnes(c));
                }
                }
            }
        }
        //at the last row, find the max one
        int res = 0;
        for(int i = 0; i < (1<<n); i++){
            res = Math.max(res, dp[m][i]);
        }
        return res;
        
    }
    private int countOnes(int x){
        int count = 0;
        while(x !=0){
            x = x&(x-1);
            count++;
        }
        return count;
        
    }
}
