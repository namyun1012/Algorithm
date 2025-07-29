import java.io.*;
import java.util.*;



// 트리에서 가장 긴 거리
// 바로 정답, 2개 이상 leaf 구별해서 계산
public class Q1967 {

    static int N;
    static List<int[]>[] graph;
    static int result = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        graph = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<int[]>();
        }

        StringTokenizer st;

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[start].add(new int[] {end, weight});
        }

        dfs(1, 0);

        System.out.println(result);
    }

    // leaf, non-leaf 구별해서 진행해 보기?
    static int dfs(int cur, int distance) {

        result = Math.max(distance, result);
        List<int[]> childs = graph[cur];

        if (childs.size() == 0) return distance;

        if (childs.size() == 1) {
            int nxt = childs.get(0)[0];
            int cost = childs.get(0)[1];

            return dfs(nxt, distance + cost);
        }   

        
        // 2개 이상 존재하는 tree 일 때
        else {
            int[] temp_distances = new int[childs.size()];

            for (int i = 0; i < childs.size(); i++) {
                int nxt = childs.get(i)[0];
                int cost = childs.get(i)[1];

                temp_distances[i] = dfs(nxt, distance + cost);
            }
            
            // 정렬 후 마지막 꺼와 그 이전 꺼 result 로 계산해 보기
            Arrays.sort(temp_distances);
            result = Math.max(result, temp_distances[childs.size() - 1] + temp_distances[childs.size() - 2] - 2 * distance );

            return temp_distances[childs.size() - 1];
        }
    }
}
