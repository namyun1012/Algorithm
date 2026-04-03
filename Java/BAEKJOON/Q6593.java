import java.util.*;
import java.io.*;

// 맞는 것은 바로 맞긴 했는데 힘든 듯.

public class Q6593 {

    // 층, 행, 열
    public static char[][][] board;
    public static int[][][] visited;
    public  static int[][][] distance;

    public static int L,R,C;
    public static Coord start;
    public static Coord end;

    static int[] dx = {1,-1,0,0,0,0};
    static int[] dy = {0,0,1,-1,0,0};
    static int[] dz = {0,0,0,0,1,-1};

    public static class Coord {
        int l;
        int r;
        int c;

        public Coord(int l, int r, int c) {
            this.l = l;
            this.r = r;
            this.c = c;
        }
    }


    public static void bfs() {
        Queue<Coord> queue = new ArrayDeque<Coord>();

        queue.add(start);
        visited[start.l][start.r][start.c] = 1;
        distance[start.l][start.r][start.c] = 0;


        while (!queue.isEmpty()) {
            Coord cur = queue.remove();

            for(int i = 0; i < 6; i++) {
                Coord nxt = new Coord(cur.l + dx[i], cur.r + dy[i], cur.c + dz[i]);

                if(!OOP(nxt.l, nxt.r, nxt.c))
                    continue;
                if(visited[nxt.l][nxt.r][nxt.c] == 1)
                    continue;


                queue.add(nxt);
                visited[nxt.l][nxt.r][nxt.c] = 1;
                distance[nxt.l][nxt.r][nxt.c] = distance[cur.l][cur.r][cur.c] + 1;

            }
        }
    }

    public static boolean OOP(int l, int r, int c) {
        if (l < 0 || l >= L || r < 0 || r >= R || c < 0 || c >= C) {
            return false;
        }

        if(board[l][r][c] == '#') {
            return false;
        }


        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        while(true) {
            StringTokenizer st  = new StringTokenizer(br.readLine());

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if(L == 0 && R == 0 && C == 0) {
                break;
            }

            board = new char[L][R][C];
            visited = new int[L][R][C];
            distance = new int[L][R][C];

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String input = br.readLine();
                    for (int k = 0; k < C; k++) {
                        char cur = input.charAt(k);

                        if (cur == 'S') {
                            start = new Coord(i, j, k);
                        }

                        if (cur == 'E') {
                            end = new Coord(i, j, k);
                        }

                        board[i][j][k] = cur;
                        distance[i][j][k] = 0;
                        visited[i][j][k] = 0;
                    }
                }
                // readLine 주의 빈칸 있다.
                br.readLine();
            }

            bfs();

            if(visited[end.l][end.r][end.c] == 0) {
                bw.write("Trapped!\n");
            } else {
                bw.write("Escaped in " + distance[end.l][end.r][end.c] + " minute(s).\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
