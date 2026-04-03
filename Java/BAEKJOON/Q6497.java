import java.io.*;
import java.util.*;
// Test Case 여러 개 였던 것이 제일 확인하기 힘들었던 문제;;

public class Q6497 {

    static int m, n;
    static int[] parent;


    static class Edge implements Comparable<Edge> {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }


        public int compareTo(Edge e) {
            return Integer.compare(this.weight, e.weight);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            if(m == 0 && n == 0) break;

            parent = new int[m];

            Edge[] edges = new Edge[n];

            for (int i = 0; i < m; i++) {
                parent[i] = i;
            }

            long result = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                edges[i] = new Edge(start, end, weight);
                result += weight;
            }

            // weight 오름차순으로 정렬
            Arrays.sort(edges);

            long sum = 0;

            for (int i = 0; i < n; i++) {
                Edge cur = edges[i];

                int start = cur.start;
                int end = cur.end;
                int weight = cur.weight;

                int parent_start = get_parent(start);
                int parent_end = get_parent(end);

                if (parent_start != parent_end) {
                    union(parent_start, parent_end);
                    sum += weight;
                }
            }

            System.out.println(result - sum);
        }

        br.close();
    }

    public static void union(int x, int y) {
        parent[y] = x;
    }

    public static int get_parent(int x) {
        if(x == parent[x]) return x;

        return parent[x] = get_parent(parent[x]);
    }

}
