class Solution{
/*
for example: if stones include abcdef....
we will do ||a - b|-|c - d| - |e- f|.......-z|;
that meas |a+d+f  + (-b-c-e-z)|
so we got two sum of subset S1,and S2,
and S1 + S2 = sum_weight of the total stones weight
if S2 >= S1， when we do S2 - S1，
we will get S2 -S1 = sum_weight -S1 - S1
that is target = sum_weight -2*S1;
that is the samller sumset S1 should as close to half of the sum_weight as he can.
And S1 couldn't be greater than sum_weight。
From the contions, we got sum_weight <= 3000,

so our length of dp array will no bigger than 1501;

so let's do dp:
1,creat a boolean dp array with length of 1501;
2,declare dp[0] = true, base case
3,sum up all the element of stones, 
  when we add a new stone(stone_weight) to the set, we need to checke every weight between sum_weight and stone_weight, to determinate dp[weight] is true or false
4.That means, either one of the dp[weight] or dp[sum_weight - weight] is true, dp[weight] is true;
5. check dp[i] from i = sum_weight / 2 to left, if dp[i] is true, means we find the S1 we want, that is i;
6. return sum_weight - 2*i;
7,or return 0;
*/


	public int lastStoneWeightII(int[] stones){
		boolean[] dp =new boolean[1501];
		dp[0] = true;
		int sumWeight = 0;
		for(int a: stones){
			sumWeight += a;
			for(int i = Math.min(1500, sumWeight); i >= a; i--){
				dp[i] = dp[i] | dp[i - a];
				}
		}
		for(int i = sumWeight / 2; i >= 0; i--){
			if(dp[i]) return sumWeight - 2*i;
			}
		return 0;
	}
}
