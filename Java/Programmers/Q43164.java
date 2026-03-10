package Programmers;
import java.io.*;
import java.util.*;
public class Q43164 {


    // Start is ICN
// Using Backtracking
    class Solution {

        static String[][] tickets;
        static int length;
        static int[] visited;
        static String[] result;

        public String[] solution(String[][] tickets) {

            this.tickets = tickets;
            length = tickets.length;
            visited = new int[length];

            Arrays.sort(this.tickets, Comparator.comparing(i -> i[1]));

            List<String> path = new ArrayList<String>();
            path.add("ICN");
            dfs("ICN", path);

            return result;
        }

        public void dfs(String cur, List<String> path) {
            if (result != null) return ;

            if (path.size() == length + 1) {
                result = path.toArray(new String[0]);
                return;
            }

            // 다음 거 찾기
            for (int i = 0; i <  length; i++) {
                String[] ticket = tickets[i];
                if (visited[i] == 1 || !cur.equals(ticket[0])) continue;

                path.add(ticket[1]);
                visited[i] = 1;

                dfs(ticket[1], path);

                path.remove(path.size() - 1);
                visited[i] = 0;
            }
        }
    }
}
