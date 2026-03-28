package Programmers;
import java.io.*;
import java.util.*;
public class Q84021 {



// Table 에 있는 조각 Gameboard 에 채워넣기
// BFS + Rotate
// 정확히 꽉 채워야 들어갈 수 있는 듯하다. => Backtracking 은 굳이 필요 없을 듯
// 회전만 가능함.

    class Solution {

        static int N;
        static int result = 0;

        static int[] dx = {0,1,0,-1};
        static int[] dy = {1,0,-1,0};

        static List<List<int[]>> pieces;
        static List<List<int[]>> emptys;

        public int solution(int[][] game_board, int[][] table) {

            N = game_board.length;

            pieces = new ArrayList<List<int[]>>();
            emptys = new ArrayList<List<int[]>>();

            // 1. BFS 2번을 진행하여, pieces, emptys 를 채움

            // Pieces

            int[][] visited = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (table[i][j] == 1 && visited[i][j] == 0) {
                        // Start BFS
                        // System.out.println(i + " " + j );
                        bfs(i, j, table, pieces, visited, 0);
                    }
                }
            }

            // emptys
            visited = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (game_board[i][j] == 0 && visited[i][j] == 0) {
                        // Start BFS

                        bfs(i, j, game_board, emptys, visited, 1);
                    }
                }
            }

            // 2. Emptys 와 Pieces 사이의 서로 비교 진행
            // 상대 좌표로 넣어져 있음.. Rotate 하면서 비교하기

            // 채워 놓은 것은 제외함
            int[] used_pieces = new int[pieces.size()];
            int[] used_emptys = new int[emptys.size()];


            for (int i = 0; i < pieces.size(); i++) {
                for (int j = 0; j < emptys.size(); j++) {
                    if(used_emptys[j] == 1) continue;

                    List<int[]> piece = pieces.get(i);
                    List<int[]> empty = emptys.get(j);

                    if (piece.size() != empty.size()) continue;

                    if(check(piece, empty)) {
                        result += piece.size();
                        used_emptys[j] = 1;
                        break;
                    }
                }
            }

            return result;
        }

        // List 에는 상대좌표 만 넣게 하기..
        // 최대 6조각 이므로 6,6?
        void bfs(int y, int x, int[][] board, List<List<int[]>> items, int[][] visited, int flag) {
            List<int[]> item = new ArrayList<int[]>();
            Queue<int[]> queue = new ArrayDeque<int[]>();

            int[] start = new int[]{y, x};

            item.add(new int[]{0, 0}); //
            queue.add(start);
            visited[y][x] = 1;

            while(!queue.isEmpty()) {
                int[] cur = queue.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int nxt_y = cur[0] + dy[dir];
                    int nxt_x = cur[1] + dx[dir];

                    if(OOP(nxt_x, nxt_y)) continue;
                    if(flag == 0 && board[nxt_y][nxt_x] == 0) continue;
                    if(flag == 1 && board[nxt_y][nxt_x] == 1) continue;
                    if(visited[nxt_y][nxt_x] == 1) continue;


                    int[] nxt = new int[] {nxt_y, nxt_x};
                    visited[nxt_y][nxt_x] = 1;
                    queue.add(nxt);
                    item.add(new int[]{nxt_y - y, nxt_x - x}); // nxt_y, nxt_x 에서 y, x 만큼 빼준 값으로 넣어보기
                }
            }
            items.add(item);
        }

        boolean OOP(int x, int y) {
            if (x < 0 || x >= N || y < 0 || y >= N) return true;
            return false;
        }

        void printArray(int[][] array) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(array[i][j] + " ");
                }
                System.out.println();
            }
        }

        // 상대 좌표임
        // 비교 14 으로..? 최대 6개 까지 연결 가능하므로
        boolean check(List<int[]> piece, List<int[]> empty) {
            int K = 8;
            int[][] board = new int[K * 2][K * 2];
            int size = piece.size();

            // empty도 normalize 후 배치
            int minY = 100, minX = 100;
            for (int[] temp : empty) {
                minY = Math.min(minY, temp[0]);
                minX = Math.min(minX, temp[1]);
            }
            for (int[] temp : empty) {
                board[temp[0] - minY + K][temp[1] - minX + K] = 1;
            }

            // Normal
            int[][] piece_board = new int[K * 2][K * 2];
            minY = 100; minX = 100;
            for (int i = 0; i < size; i++) {
                int[] temp = piece.get(i);
                minY = Math.min(minY, temp[0]);
                minX = Math.min(minX, temp[1]);
            }
            for (int i = 0; i < size; i++) {
                int[] temp = piece.get(i);
                piece_board[temp[0] - minY + K][temp[1] - minX + K] = 1;
            }
            if (CompareArray(piece_board, board)) return true;

            // 90도
            piece_board = new int[K * 2][K * 2];
            minY = 100; minX = 100;
            for (int i = 0; i < size; i++) {
                int[] temp = piece.get(i);
                minY = Math.min(minY, temp[1]);       // 회전 후 y = 원본 x
                minX = Math.min(minX, -temp[0]);      // 회전 후 x = 원본 -y
            }
            for (int i = 0; i < size; i++) {
                int[] temp = piece.get(i);
                piece_board[temp[1] - minY + K][-temp[0] - minX + K] = 1;
            }
            if (CompareArray(piece_board, board)) return true;

            // 180도
            piece_board = new int[K * 2][K * 2];
            minY = 100; minX = 100;
            for (int i = 0; i < size; i++) {
                int[] temp = piece.get(i);
                minY = Math.min(minY, -temp[0]);      // 회전 후 y = 원본 -y
                minX = Math.min(minX, -temp[1]);      // 회전 후 x = 원본 -x
            }
            for (int i = 0; i < size; i++) {
                int[] temp = piece.get(i);
                piece_board[-temp[0] - minY + K][-temp[1] - minX + K] = 1;
            }
            if (CompareArray(piece_board, board)) return true;

            // 270도
            piece_board = new int[K * 2][K * 2];
            minY = 100; minX = 100;
            for (int i = 0; i < size; i++) {
                int[] temp = piece.get(i);
                minY = Math.min(minY, -temp[1]);      // 회전 후 y = 원본 -x
                minX = Math.min(minX, temp[0]);       // 회전 후 x = 원본 y
            }
            for (int i = 0; i < size; i++) {
                int[] temp = piece.get(i);
                piece_board[-temp[1] - minY + K][temp[0] - minX + K] = 1;
            }
            if (CompareArray(piece_board, board)) return true;

            return false;
        }

        boolean CompareArray(int[][] arr1, int[][] arr2) {
            int size = arr1.length;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if(arr1[i][j] != arr2[i][j]) return false;
                }
            }


            return true;
        }
    }
}
