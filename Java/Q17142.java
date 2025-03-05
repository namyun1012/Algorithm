// M 이 활성화 하는, 즉 Start 인 Virus
// 바이러스는 10개 이하
// 모든 곳에 바이러스를 퍼트리는 것이 목적이므로,
// 기존의 비활성 바이러스에도 퍼지면 됨
// 매번 check 하는 것은 시간 초과 가능성 높음
// 81% 에서 틀림
// 핵심은 빈 칸을 채우는 것이므로 빈 칸에서만 거리를 확인해야 한다.

import java.io.*;
import java.util.*;

public class Q17142 {
    static int N, M;
    static int board[][];
    static int visited[][];
    static int distance[][];
    static List<Coord> virus;
    static int result = Integer.MAX_VALUE;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};


    public static class Coord {
        int x;
        int y;

        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean OOP(Coord cur) {
        if(cur.x < 0 || cur.y < 0 || cur.x >= N || cur.y >= N || board[cur.x][cur.y] == 1)
            return true;
        return false;
    }

    public static void backtracking(int index, List<Integer> starts) {
        // M 개가 되면 Backtracking 실행

        if (starts.size() == M) {
            BFS(starts);
            return;
        }

        if(starts.size() > M) {
            return;
        }

        for (int i = index; i < virus.size(); i++) {
            List<Integer> new_starts = new ArrayList<Integer>(starts);
            new_starts.add(i);
            backtracking(i + 1, new_starts);
            
        }
    }

    public static void BFS(List<Integer> starts) {
        Queue<Coord> queue = new ArrayDeque<Coord>();
        int max_distance = 0;
        
        // BFS 할 때마다 초기화
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                visited[i][j] = 0;
                distance[i][j] = 0;
            }
        }

        for (int i : starts) {
            Coord cur = virus.get(i);

            queue.add(cur);
            visited[cur.x][cur.y] = 1;
            distance[cur.x][cur.y] = 0;
        }

        while (!queue.isEmpty()) {
            Coord cur = queue.remove();

            for (int dir = 0; dir < 4; dir++) {
                Coord nxt = new Coord(cur.x + dx[dir], cur.y + dy[dir]);

                if(OOP(nxt)) continue;
                if(visited[nxt.x][nxt.y] == 1) continue;

                queue.add(nxt);
                visited[nxt.x][nxt.y] = 1;

                distance[nxt.x][nxt.y] = distance[cur.x][cur.y] + 1;
            }
        }

        // 추후 검사
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {

                // 벽이나, 바이러스 면 거리 검사를 건너 뛰어야 한다.
                if(board[i][j] == 2) continue;

                if(visited[i][j] == 0 && board[i][j] == 0)
                    return;
                max_distance = Math.max(max_distance, distance[i][j]);
            }
        }

        result = Math.min(result, max_distance);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        visited = new int[N][N];
        distance = new int[N][N];

        virus = new ArrayList<Coord>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int cur = Integer.parseInt(st.nextToken());
                board[i][j] = cur;
                // 바이러스는 2
                if (cur == 2)
                    virus.add(new Coord(i, j));
            }
        }

        backtracking(0, new ArrayList<Integer>());

        if(result == Integer.MAX_VALUE)
            result = -1;

        System.out.println(result);
        br.close();
    }
}
