import java.io.*;
import java.util.*;

// MST 문제..?
class Solution {
    
    public class Edge implements Comparable<Edge> {
        int from, to;
        int distance;
        
        public Edge(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }
        
        public int compareTo(Edge o) {
            return this.distance - o.distance;
        }
    }
    
    
    public int solution(int n, int[][] costs) {
        
        int[][] boards = new int[n][n];
        
        for (int i = 0; i < costs.length; i++) {
            int first = costs[i][0];
            int second = costs[i][1];
            int cost = costs[i][2];
            
            boards[first][second] = cost;
            boards[second][first] = cost;
        }
        
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        
        // Initialize
        int total = 0;
        int[] visited = new int[n];
        
        // Start 0
        visited[0] = 1;
        for (int i = 1; i < n; i++) {
            if (boards[0][i] != 0) pq.add(new Edge(0, i, boards[0][i]));
        }
        
        
        // Repeat
        while(!pq.isEmpty()) {
            
            // distance 제일 작은 거
            Edge cur = pq.poll();
            
            if (visited[cur.to] == 1) continue;
            total += cur.distance;
            visited[cur.to] = 1;
            
            for (int i = 0; i < n; i++) {
                if (boards[cur.to][i] == 0) continue;
                if (visited[i] == 1) continue;
                
                pq.add(new Edge(cur.to, i, boards[cur.to][i]));
            }
        }

        return total;
    }
}