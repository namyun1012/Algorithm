package Softeer;
import java.io.*;
import java.util.*;

public class TrafficSystem {

    public static int N;
    public static int T;


    // i * N + j
    public static List<int[]> signals;
    public static int[][] board;

    public static class Coord {
        int x, y;
        int direct;
        int time;
        public Coord(int x, int y, int direct, int time) {
            this.x = x;
            this.y = y;
            this.direct = direct;
            this.time = time;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        signals = new ArrayList<int[]>();

        for (int i = 0; i < N * N ; i++) {
            st = new StringTokenizer(br.readLine());

            int[] traffic = new int[4];
            for (int j = 0; j < 4; j++) {
                traffic[j] = Integer.parseInt(st.nextToken());
            }

            signals.add(traffic);
        }

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = new int[N];
        }

        // 실행
        int start_y = 0;
        int start_x = 0;
        int result = 1;


        board[0][0] = 1;

        Queue<Coord> queue = new ArrayDeque<Coord>();
        queue.add(new Coord(start_x, start_y, 2, 0)); //

        // direct 0 : 북 -> 남, 1 : 동 -> 서 2: 남 -> 북
        while (!queue.isEmpty()) {
            Coord cur = queue.remove();

            if (cur.time > T) continue;
            if(cur.x < 0 || cur.y < 0 || cur.x >= N || cur.y >= N) continue;


            if (board[cur.y][cur.x] == 0) {
                board[cur.y][cur.x] = 1;
                result += 1;
            }
            
            int cur_traffic = signals.get(cur.y * N + cur.x)[cur.time % 4];
            // System.out.println(cur.x + " " + cur.y + " " + cur.direct + " " + cur.time + " " + cur_traffic);

            switch (cur.direct) {
                case 0:
                    // 
                    if (cur_traffic == 4) {
                        queue.add(new Coord(cur.x-1, cur.y, 1, cur.time + 1));
                        queue.add(new Coord(cur.x, cur.y + 1, 0, cur.time + 1));
                        queue.add(new Coord(cur.x + 1, cur.y, 3, cur.time + 1));
                    }

                    else if (cur_traffic == 8) {
                        queue.add(new Coord(cur.x, cur.y + 1, 0, cur.time + 1));
                        queue.add(new Coord(cur.x + 1, cur.y, 3, cur.time + 1));
                    }

                    else if (cur_traffic == 12) {
                        queue.add(new Coord(cur.x-1, cur.y, 1, cur.time + 1));
                        queue.add(new Coord(cur.x, cur.y + 1, 0, cur.time + 1));
                    }

                    break;

                case 1:
                    if (cur_traffic == 3) {
                        queue.add(new Coord(cur.x, cur.y - 1, 2, cur.time + 1));
                        queue.add(new Coord(cur.x-1, cur.y, 1, cur.time + 1));
                        queue.add(new Coord(cur.x, cur.y + 1, 0, cur.time + 1));
                    }

                    else if (cur_traffic == 7) {
                        queue.add(new Coord(cur.x-1, cur.y, 1, cur.time + 1));
                        queue.add(new Coord(cur.x, cur.y + 1, 0, cur.time + 1));
                    }

                    else if (cur_traffic == 11) {
                        queue.add(new Coord(cur.x, cur.y - 1, 2, cur.time + 1));
                        queue.add(new Coord(cur.x-1, cur.y, 1, cur.time + 1));
                    }

                    break;

                case 2:
                    if (cur_traffic == 2) {
                        queue.add(new Coord(cur.x-1, cur.y, 1, cur.time + 1));
                        queue.add(new Coord(cur.x, cur.y-1, 2, cur.time + 1));
                        queue.add(new Coord(cur.x+1, cur.y, 3, cur.time + 1));
                    }

                    else if (cur_traffic == 6) {
                        queue.add(new Coord(cur.x-1, cur.y, 1, cur.time + 1));
                        queue.add(new Coord(cur.x, cur.y-1, 2, cur.time + 1));
                    }

                    else if (cur_traffic == 10) {
                        queue.add(new Coord(cur.x, cur.y-1, 2, cur.time + 1));
                        queue.add(new Coord(cur.x+1, cur.y, 3, cur.time + 1));
                    }
                    break;

                case 3:
                    if (cur_traffic == 1) {
                        queue.add(new Coord(cur.x, cur.y - 1, 2, cur.time + 1));
                        queue.add(new Coord(cur.x+1, cur.y, 3, cur.time + 1));
                        queue.add(new Coord(cur.x, cur.y+1, 0, cur.time + 1));
                    }

                    else if (cur_traffic == 5) {
                        queue.add(new Coord(cur.x, cur.y - 1, 2, cur.time + 1));
                        queue.add(new Coord(cur.x+1, cur.y, 3, cur.time + 1));
                    }

                    else if (cur_traffic == 9) {
                        queue.add(new Coord(cur.x+1, cur.y, 3, cur.time + 1));
                        queue.add(new Coord(cur.x, cur.y+1, 0, cur.time + 1));
                    }

                    break;

                default:
                    break;

            }
        }


        System.out.println(result);

        br.close();


    }
}
