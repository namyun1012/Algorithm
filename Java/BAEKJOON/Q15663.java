import java.io.*;
import java.util.*;
public class Q15663 {

    static int N, M;
    static List<Integer> arr;
    static HashMap<String, Boolean> check = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        arr = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());

            arr.add(temp);
        }

        Collections.sort(arr);
        backtracking(0, new ArrayList<Integer>(), new int[N + 1]);
    }

    public static void backtracking(int index, List<Integer> array, int[] visited) {
        if (index == M) {
            String print = "";

            for (Integer ele : array) {
                print += (ele.toString() + " ");
            }

            if (check.get(print) != null) return;


            check.put(print, true);
            System.out.println(print);
            return;
        }


        for (int i = 0; i < N; i++) {

            if(visited[i] == 1) continue;

            int temp = arr.get(i);

            array.add(temp);
            visited[i] = 1;
            backtracking(index + 1, array, visited);
            visited[i] = 0;
            array.remove(array.size() -1);

        }
    }


}
