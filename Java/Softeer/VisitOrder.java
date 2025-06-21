package Softeer;

import java.io.*;
import java.util.*;

// m 은 16 이하, n 은 4 이하
// 복잡한 코드 만들어도 될 듯?
// backtracking 및 DFS 사용 해보기
// 배열 입력?
// O(4^(N^2))

public class VisitOrder {

    public static int n;
    public static int m;

    public static int[][] board;
    public static int[][] visited;
    public static int[][] essential_visited;

    public static int result = 0;
    public static int[] end;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1,-1, 0, 0 };


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n + 1][n + 1];
        essential_visited = new int[m][2];
        visited = new int[n + 1][n + 1];

        for (int i = 1; i <= n ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                board[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int x, y;
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            essential_visited[i] = new int[] {x, y};
        }

        int[] start = essential_visited[0];
        end = essential_visited[m - 1];


        visited[start[1]][start[0]] = 1;
        backtracking(1, start);
        
        System.out.println(result);
        br.close();
    }

    public static boolean OOP(int[] cur) {
        int x = cur[0];
        int y = cur[1];

        if (x < 1 || y < 1 || x > n || y > n)
            return false;
        if (board[y][x] == 1)
            return false;

        return true;
    }


    // index 는 최대 16 까지
    public static void backtracking(int index, int[] cur) {
        if(end[0] == cur[0] && end[1] == cur[1] && index == m) {
            result += 1;
            return;
        }

        // m 인데 끝이 아님?
        if(index == m) return;

        int[] finding = essential_visited[index];

        for (int dir = 0; dir < 4; dir++) {
            int[] nxt = {cur[0] + dx[dir], cur[1] + dy[dir]};
            if(!OOP(nxt)) continue;
            if(visited[nxt[1]][nxt[0]] == 1) continue;

            visited[nxt[1]][nxt[0]] = 1;

            // 찾음
            if(finding[0] == nxt[0] && finding[1] == nxt[1]) {
               backtracking(index + 1, nxt);
            }

            else {
                backtracking(index, nxt);
            }
            
            // 끝났을 시 비 처리 해줌
            visited[nxt[1]][nxt[0]] = 0;

        }
    }

}
