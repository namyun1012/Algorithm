import java.io.*;
import java.util.*;
// visited HashMap 으로 변경 + Long 사용으로 정답
public class Q16953 {

    static long A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        HashMap<Long, Integer> visited = new HashMap<>();

        Queue<Long> queue = new ArrayDeque<>();

        visited.put(A, 1);
        queue.add(A);

        while (!queue.isEmpty()) {
            long cur = queue.remove();

            for (int dir = 0; dir < 2; dir++) {
                long nxt;
                if (dir == 0) {
                    nxt =  cur * 2;
                }
                else {
                    nxt = cur * 10 + 1;
                }


                if (nxt < 1 || nxt > B) continue;
                if (visited.containsKey(nxt)) continue;

                visited.put(nxt, visited.get(cur) + 1);
                queue.add(nxt);
            }
        }

        if (visited.containsKey(B)) {
            System.out.println(visited.get(B));
        }
        else {
            System.out.println(-1);
        }
    }
}
