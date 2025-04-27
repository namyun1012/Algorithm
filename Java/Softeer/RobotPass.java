package Softeer;

import java.io.*;
import java.util.*;

// 같은 곳은 반복 안하므로 BFS 보다는 Greedy 형식으로 가는 것이 나을 듯하다.
// 틀린 경우가 존재함
public class RobotPass {
    public static int H, W;

    public static char[][] board;
    public static int[][] visited;

    public static List<Coord> starts;
    public static int[] dx= {0,1,0,-1};
    public static int[] dy = {-1,0,1,0};
    public static char[] directs = {'^', '>', 'v', '<'};

    public static class Coord {
        int y, x;
        int direct;
        String orders;
        public Coord(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Coord(int y, int x, int direct, String orders) {
            this.y = y;
            this.x = x;
            this.direct = direct;
            this.orders = orders;
        }


    }

    public static boolean OOP(int y, int x, int dir) {
        if((y + dy[dir] * 2 >= 0 && x + dx[dir] * 2 >= 0 && y + dy[dir] * 2 < H && x + dx[dir] * 2 < W))  {
            if(board[y + dy[dir]][x + dx[dir]] == '#' && board[y + dy[dir] * 2][x + dx[dir] * 2] == '#') {
                if (visited[y + dy[dir]][x + dx[dir]] == 0 && visited[y + dy[dir] * 2][x + dx[dir] * 2] == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean check() {
        for(Coord start : starts) {
            if (visited[start.y][start.x] == 0)
                return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        board = new char[H][W];
        visited = new int[H][W];
        starts = new ArrayList<Coord>();


        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();

            for (int j = 0; j < W; j++) {
                char cur = temp.charAt(j);
                board[i][j] = cur;

                // # 인 부분은 시작 가능성 있음 최대 625
                if (cur == '#') {
                    starts.add(new Coord(i, j, 0, ""));
                    starts.add(new Coord(i, j, 1, ""));
                    starts.add(new Coord(i, j, 2, ""));
                    starts.add(new Coord(i, j, 3, ""));
                }
            }
        }
        
        // 명령어 개수가 최소인 것
        int result = Integer.MAX_VALUE;

        int result_y = -1;
        int result_x = -1;
        int result_direct = 0;
        Coord result_coord = null;
        
        // 각 Start 지점마다 반복
        for (Coord start : starts) {

            visited = new int[H][W];
            visited[start.y][start.x] = 1;
            Queue<Coord> queue = new ArrayDeque<Coord>();
            queue.add(start);


            Coord final_coord = start;

            while (!queue.isEmpty()) {
                Coord cur = queue.remove();
                final_coord = cur;

                // 주위의 경로 확인
                // 북동남서 (시계 방향)
                for (int j = 0; j < 4; j++) {

                    // cur.direct 를 우선으로 해보게 진행?
                    int dir = (cur.direct + j) % 4;

                    if (!OOP(cur.y, cur.x, dir)) {
                        continue;
                    }

                    int rotate = cur.direct - dir;
                    
                    // 후진하면 바로 틀린 것으로
                    if (rotate == 2 || rotate == -2) break;

                    visited[cur.y + dy[dir]][cur.x + dx[dir]] = 1;
                    visited[cur.y + dy[dir] * 2][cur.x + dx[dir] * 2] = 1;

                    // 정방향임
                    if (rotate == 0) {
                        queue.add(new Coord(cur.y + dy[dir] * 2, cur.x + dx[dir] * 2, dir, cur.orders + "A"));
                    }
                    
                    // 시계 방향 한번
                    else if (rotate == 1 || rotate == -3) {
                        queue.add(new Coord(cur.y + dy[dir] * 2, cur.x + dx[dir] * 2, dir, cur.orders + "LA"));
                    }
                    
                    // 반시계 방향
                    else if(rotate == 3 || rotate == -1) {
                        queue.add(new Coord(cur.y + dy[dir] * 2, cur.x + dx[dir] * 2, dir, cur.orders + "RA"));
                    }
                    // 후진은 넣지 않는다. 후진 있으면 맞는 경우가 없는 듯
                    break;
                }
            }

            if (check() && result > final_coord.orders.length()) {
                result = final_coord.orders.length();
                result_coord = final_coord;
                result_y = start.y;
                result_x = start.x;
                result_direct = start.direct;
            }
        }

        System.out.println((result_y + 1) + " " + (result_x + 1));
        System.out.println(directs[result_direct]);
        System.out.println(result_coord.orders);
    }
}
