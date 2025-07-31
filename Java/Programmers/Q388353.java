package Programmers;
import java.io.*;
import java.util.*;

public class Q388353 {

// 이게 BFS?
    class Solution {

        static char[][] board;
        static int n;
        static int m;

        static int[] dx = {0,0,1,-1};
        static int[] dy = {1,-1,0,0};

        public int solution(String[] storage, String[] requests) {

            n = storage.length;
            m = storage[0].length();

            board = new char[n][m];

            int number = n * m;


            for (int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    board[i][j] = storage[i].charAt(j);
                }
            }


            for (String request : requests) {
                char cur = request.charAt(0);

                // 겉에만 꺼내기
                // 저장해 두었다가 한 꺼번에 처리
                if (request.length() == 1) {
                    List<int[]> temp = new ArrayList<>();

                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < m; j++) {
                            if (board[i][j] != cur) continue;

                            if (isConnect(i, j)) temp.add(new int[] {i, j});
                        }
                    }

                    // 저장 후 삭제 처리
                    for (int i = 0; i < temp.size() ; i++) {
                        int y = temp.get(i)[0];
                        int x = temp.get(i)[1];
                        board[y][x] = ' ';
                        number -= 1;
                    }


                }

                // 전부 삭제 처리
                else {
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < m; j++) {
                            if (board[i][j] == cur)  {
                                board[i][j] = ' ';
                                number -= 1;
                            }
                        }
                    }
                }



            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }



            int answer = number;
            return answer;
        }

        // BFS 로?
        public boolean isConnect(int y, int x) {
            if(y == 0 || y == n - 1 || x == 0 || x == m - 1) {
                return true;
            }

            Queue<int[]> queue = new ArrayDeque<>();
            int[][] visited = new int[n][m];

            queue.add(new int[] {y, x});
            visited[y][x] = 1;

            while(!queue.isEmpty()) {
                int[] cur = queue.remove();
                int cur_y = cur[0];
                int cur_x = cur[1];

                for (int dir = 0; dir < 4; dir++) {
                    int nxt_y = cur_y + dy[dir];
                    int nxt_x = cur_x + dx[dir];

                    if(visited[nxt_y][nxt_x] == 1) continue;
                    if(board[nxt_y][nxt_x] != ' ') continue;
                    if(nxt_y == 0 || nxt_y == n - 1 || nxt_x == 0 || nxt_x == m - 1) return true;

                    visited[nxt_y][nxt_x] = 1;
                    queue.add(new int[] {nxt_y, nxt_x});
                }

            }

            return false;

        }
    }
}
