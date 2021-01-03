class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //perpare an array of arraylist to build a directed graph which represent the prerequisites, which means for A -> B, we need to learn A first, then we can learn B
        ArrayList<Integer>[] Graph = new ArrayList[numCourses];
        //prepare an array of integer, to store the in-degree of every course, if the degree of a course is 0, we can learn it directly
        int[] degree = new int[numCourses];
        //prepare an arraylist to store the courses which have 0 in-degree;
        //if the size of bfs is equal to numCourses, we know all courses we can finish
        ArrayList<Integer> bfs = new ArrayList<>();
        
        //initialize Graph, the course num or the index is the nodes
        for(int i = 0; i < numCourses; i++) Graph[i] = new ArrayList<>();
        //we know pre[1] is the prereq, and pre[0] is the course we can learn after pre[1]
        //for every node, add edges to the next course which can be learned after we learn current course
        for(int[] pre : prerequisites){
            Graph[pre[1]].add(pre[0]);
            //correspondly, increase the in-dgree of pre[0]
            degree[pre[0]]++;
        }
        
        //first round, check the 0 in-degree courses, add them to the bfs set
        for(int i = 0; i < numCourses; i++){
            if(degree[i] == 0){
                bfs.add(i);
            }
        }
        //second round, remove the nodes which in-degree is 0 and then decrease the in-degree correspondly
        for(int i = 0; i < bfs.size();i++){
            //i is the prerequire of j, so when we learned i, j's in-degree should be decrease, and if degree[j] == 0, j could be learned now, add it to bfs;
            for(int j : Graph[bfs.get(i)]){
                degree[j]--;
                if(degree[j] == 0){
                    bfs.add(j);
                }
            }
        }
        return bfs.size() == numCourses;
    }
}
