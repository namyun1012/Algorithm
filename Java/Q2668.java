import java.io.*;
import java.util.*;
/*

    Cycle 형성시 처리?
    맞았음 Cycle 형성 되어 있는지 확인하는 문제
    풀이 떠올리는 것이 힘들었음
 */


public class Q2668 {

    static int N;
    static int[] arr;
    static int result = 0;
    static int[] visited;
    static int[] result_list;
    static List<Integer>[] graph;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        visited = new int[N + 1];
        result_list = new int[N + 1];

        graph = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            graph[i].add(arr[i]);
        }

        for (int i = 1; i <= N; i++) {
            if(result_list[i] == 1) continue;

            int[] visited1 = new int[N + 1];
            dfs(i, visited1);

            int[] visited2 = new int[N + 1];
            dfs(arr[i], visited2);

            boolean check = true;

            for(int j = 1; j <= N; j++) {
                if(visited1[j] != visited2[j]) {
                    check = false;
                    break;
                }
            }

            if(check) {
                for(int j = 1; j <= N; j++) {
                    result_list[j] = result_list[j] | visited1[j];
                }
            }

        }

        for(int i = 1; i <= N; i++) {
            if(result_list[i] == 1) result++;
        }

        System.out.println(result);

        for(int i = 1; i <= N; i++) {
            if(result_list[i] == 1) {
                System.out.println(i);
            }
        }

        br.close();
    }

    // 완전한 원형 Cycle 일 때만 가능하게 해야 함
    public static void dfs(int cur, int[] visited) {
        if(visited[cur] == 1) return;

        visited[cur] = 1;
        for (int nxt : graph[cur]) {
            if(visited[nxt] == 1) continue;
            dfs(nxt, visited);
        }
        
    }
}
