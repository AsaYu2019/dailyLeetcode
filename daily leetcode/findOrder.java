class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //perpare an array of arraylist to build a directed graph which represent the prerequisites, which means for A -> B, we need to learn A first, then we can learn B
        ArrayList<Integer>[] Graph = new ArrayList[numCourses];
        //prepare an array of integer, to store the in-degree of every course, if the degree of a course is 0, we can learn it directly
        int[] degree = new int[numCourses];
        //prepare an array of interger to store the result,
        int[] res = new int[numCourses];
        //prepare a pointer to know which index we need to use in the res[]
        int point = 0;
        //initialize the Graph and indegree[]
        for(int i = 0; i < numCourses; i++) Graph[i] = new ArrayList<>();
        for(int[] pre : prerequisites){
            Graph[pre[1]].add(pre[0]);
            degree[pre[0]]++;
        }
        //find the first course we can learn, add it to res
        for(int i = 0; i < numCourses; i++){
            if(degree[i] == 0) res[point++] = i;
        }
        //if there is no course we can learn at the beginning, return int[0]
        if(point == 0) return new int[0];
        //remove the nodes which in-degree is 0 and then decrease the in-degree correspondly
        for(int i = 0; i < point; i++){
            for(int j : Graph[res[i]]){
                degree[j]--;
                if(degree[j] == 0) res[point++] = j;
            }
        }
        if(point == numCourses){
            return res;
        }else{
            return new int[0];
        }
    }
}
