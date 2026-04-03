import java.io.*;
import java.util.*;

// 시간 제한이 6초인 꽤나 긴 문제
// BFS 사용으로 해결

public class Q9019 {

    static int T;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            Map<Integer, String> map = new HashMap<Integer, String>();
            Queue<Integer> queue = new ArrayDeque<Integer>();

            queue.add(start);
            map.put(start, "");

            while (!queue.isEmpty()) {
                int cur = queue.remove();

                if(map.get(end) != null) continue;
                if(cur >= 10000 || cur < 0) continue;

                String cur_string = map.get(cur);
                int nxt;

                // D
                nxt = (cur * 2) % 10_000;
                if(map.get(nxt) == null) {
                    queue.add(nxt);
                    map.put(nxt, cur_string + "D");
                }

                // S
                nxt = cur - 1;
                if (nxt == -1) nxt = 9999;

                if(map.get(nxt) == null) {
                    queue.add(nxt);
                    map.put(nxt, cur_string + "S");
                }

                int d4 = cur % 10;
                int d3 = (cur % 100) / 10;
                int d2 = (cur % 1000) / 100;
                int d1 = cur / 1000;

                // L
                nxt = d2 * 1000 + d3 * 100 + d4 * 10 + d1;
                if(map.get(nxt) == null) {
                    queue.add(nxt);
                    map.put(nxt, cur_string + "L");
                }

                // R
                nxt = d4 * 1000 + d1 * 100 + d2 * 10 + d3;
                if(map.get(nxt) == null) {
                    queue.add(nxt);
                    map.put(nxt, cur_string + "R");
                }
            }

            System.out.println(map.get(end));
        }
        br.close();
    }
}
