package Softeer;
import java.io.*;
import java.util.*;

// Backtracking 압축 중요
// 압축 조건이 빡센 편이다.

public class MinArea {

    public static int N, K;
    public static List<Coord>[] coords;
    public static List<Coord>[] compressed_coords;


    public static int result = Integer.MAX_VALUE;
    public static int MAX_COORD = 1000;
    public static class Coord {
        int y, x;

        public Coord(int y, int x) {
            this.y= y;
            this.x = x;
        }
    }

    public static void backtracking(int index, int max_x, int max_y, int min_x, int min_y) {
        if (index > K) {
            result = Math.min(result, (max_x - min_x) * (max_y - min_y));
            return;
        }

        for (Coord cur_coord : coords[index]) {
            int new_max_x = Math.max(max_x, cur_coord.x);
            int new_max_y = Math.max(max_y, cur_coord.y);
            int new_min_x = Math.min(min_x, cur_coord.x);
            int new_min_y = Math.min(min_y, cur_coord.y);
            
            // 압축할 수 있도록
            if (result <= (new_max_x - new_min_x) * (new_max_y - new_min_y)) continue;
            backtracking(index + 1, new_max_x, new_max_y, new_min_x, new_min_y);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coords = new List[K + 1];

        for (int i = 0; i <= K; i++) {
            coords[i] = new ArrayList<Coord>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x, y, k;

            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            coords[k].add(new Coord(y, x));
        }

        backtracking(1, -MAX_COORD, -MAX_COORD, MAX_COORD, MAX_COORD);

        System.out.println(result);

        // Solution 각 Coords 에서 하나씩 뽑기
        br.close();
    }
}
