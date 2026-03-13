package Programmers;
import java.io.*;
import java.util.*;
public class Q87694 {
// 갈 수 있는 곳을 어떻게 선정할 지가 제일 중요할 듯
// 그 이후에는 걍 BFS 돌리고 끝날 듯

// 외부, 내부 표현
// 외부 => 1
// 내부 => -1, => Board 에 Rectangle 넣을 때 기존이 내부라고 표기되어 있을 시 그부분 은 자동으로 내부화 시킴
// 이후 그냥 BFS
// 좌표가 선을 의미하도록?
// 선의 길이를 2배로 하기..? => 코너 부분 계산 용이 ... 어캐 생각함..?

    class Solution {

        static final int max = 104;
        static int[][] board;

        static int[] dx = {0,-1,1,0};
        static int[] dy = {1,0,0,-1};

        public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

            board = new int[max][max];

            for (int i = 0; i < rectangle.length; i++) {
                int left_x = rectangle[i][0] * 2;
                int down_y = rectangle[i][1] * 2;
                int right_x = rectangle[i][2] * 2;
                int up_y = rectangle[i][3] * 2;

                // 좌표가 아닌 선 단위로 채우도록 진행
                // 1 -> 1~2 선을 채움
                for (int y = down_y; y <= up_y; y++) {
                    for (int x = left_x; x <= right_x; x++) {

                        // 외부
                        if (x == left_x || x == right_x || y == down_y || y == up_y)
                            fill(y, x);

                            // 내부
                        else
                            board[y][x] = -1;
                    }
                }
            }

            // print();

            Queue<int[]> queue = new ArrayDeque<int[]>();
            int[][] visited = new int[max][max];

            queue.add(new int[]{characterY * 2, characterX * 2});
            visited[characterY * 2][characterX* 2] = 1;

            while(!queue.isEmpty()) {
                int[] cur = queue.remove();
                int cur_y = cur[0];
                int cur_x = cur[1];

                if (cur_y == itemY * 2 && cur_x == itemX * 2) return (visited[cur_y][cur_x] - 1) / 2;

                for (int dir = 0; dir < 4; dir++) {
                    int nxt_y = cur_y + dy[dir];
                    int nxt_x = cur_x + dx[dir];

                    if(!OOP(nxt_y, nxt_x)) continue;
                    if(board[nxt_y][nxt_x] != 1) continue;
                    if(visited[nxt_y][nxt_x] > 0) continue;


                    queue.add(new int[]{nxt_y, nxt_x});
                    visited[nxt_y][nxt_x] = visited[cur_y][cur_x] + 1;
                }
            }

            return -1;
        }

        public static void fill(int y, int x) {

            // 그냥 아무 곳도 없으면 그냥 채움
            if (board[y][x] == 0) {
                board[y][x] = 1;
            }

            // 이미 다른 곳의 내부 일 경우 -1 로 냅둠
            else if (board[y][x] == -1) {
                return ;
            }

            // 이미 외부 일시 그냥 외부라고 표기함
            else {
                return ;
            }
        }

        public static void print() {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }

            System.out.println();
        }

        public static boolean OOP(int y, int x) {
            if (y < 0 || y >= max || x < 0 || x >= max) return false;
            return true;

        }

    }
}
