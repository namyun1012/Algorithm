package Softeer;
import java.util.*;
import java.io.*;

// Bellman Ford Algorithm 으로는 안될 듯하다.
// 매 Node 마다 BFS 진행하는 것 실패
// 잘 이해가 되지 않은 문제

public class DistanceSum {

    public static int N;


    public static List<int[]>[] graph;
    public static long[] subtree;
    public static long[] result;

    public static void dfs_subtree(int cur, int parent) {

        // 처음에는 1개
        subtree[cur] = 1;
        
        // subtree 의 개수 구하기
        for (int[] nxt : graph[cur]) {
            int nxt_node = nxt[0];
            int weight = nxt[1];

            // parent 가 아닌 경우에 진행
            if (nxt_node != parent) {
                dfs_subtree(nxt_node, cur);
                subtree[cur] += subtree[nxt_node];

                // 현재 weight * child의 subtree 개수 + child 의 distnace
                result[cur] += weight * subtree[nxt_node] + result[nxt_node];
            }
        }

    }

    public static void dfs_result(int cur, int parent) {
        for(int[] nxt : graph[cur]) {

            int nxt_node = nxt[0];
            int wegiht = nxt[1];


            if(nxt_node != parent) {

                // 자식 : 부모 + 자식의 자식 아닌거를 더하고 자식의 자식인 것을 빼줌 (weight 곱해서)
                result[nxt_node] = result[cur] + wegiht * (N - 2 * subtree[nxt_node]);
                dfs_result(nxt_node, cur);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new List[N + 1];
        subtree = new long[N + 1];
        result = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<int[]>();
        }


        // Graph Initialize
        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start, end, weight;
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            int[] temp = {end, weight};

            graph[start].add(temp);

            temp = new int[] {start, weight};

            graph[end].add(temp);
        }

        dfs_subtree(1, 0);
        dfs_result(1, 0);


        for(int i = 1; i <= N ; i++ ){
            System.out.println(result[i]);
        }

        br.close();
    }
}
