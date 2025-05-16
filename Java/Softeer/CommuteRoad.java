package Softeer;
import java.util.*;
import java.io.*;

// S 에서 가능한 모든 곳, T 에서 가능한 모든 곳
// S 로 가능한 모든 곳, T 에서 가능한 모든 곳
// visited 처리도 잘 해줘야 했다.
// 쉬워보였는데 어려움


public class CommuteRoad {

    public static int N;
    public static int M;

    public static int S, T;
    public static int[] visited1;
    public static int[] visited2;

    public static int[] visited3;
    public static int[] visited4;
    public static ArrayList<Integer>[] graph;
    public static ArrayList<Integer>[] reversed;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // Initialize

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        // graph 초기화
        graph = new ArrayList[N + 1];
        reversed = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<Integer>();
            reversed[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start, end;
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
            reversed[end].add(start);
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        // BFS 진행

        visited1 = new int[N + 1];
        visited2 = new int[N + 1];
        visited3 = new int[N + 1];
        visited4 = new int[N + 1];

        visited1[T] = 1;
        visited2[S] = 1;
        dfs1(S, T);
        dfs1(T, S);

        dfs2(S, T);
        dfs2(T, S);

        int result = 0;

        for(int i = 1; i <= N; i++){
            if(visited1[i] == 1 && visited2[i] == 1 && visited3[i] == 1 && visited4[i] == 1) result++;
        }

        result -= 2; // S, T 제외

        System.out.println(result);

        br.close();

    }
    
    // S, T 에서 도달 가능한 모든 정점
    public static void dfs1(int cur, int end) {
        int[] visited;

        if(end == T) visited = visited1;
        else visited = visited2;

        visited[cur] = 1;
        for(int nxt : graph[cur]) {
            if(visited[nxt] == 1) continue;
            dfs1(nxt, end);
        }
    }

    // S, T 까지 도달 가능한 모든 정점들
    public static void dfs2(int cur, int end) {
        int[] visited;

        if(end == T) visited = visited3;
        else visited = visited4;

        visited[cur] = 1;
        for(int nxt : reversed[cur]) {
            if(visited[nxt] == 1) continue;
            dfs2(nxt, end);
        }
    }
}
