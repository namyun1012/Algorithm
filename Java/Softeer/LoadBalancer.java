package Softeer;
import java.util.*;
import java.io.*;

// 현재 시간 초과 문제 발생 중
// 아래 같은 구현 방식 말고, 위상 정렬 방식으로 풀이
// 위상 정렬의 큰 변형, 다시 한번 확인
// O(N * R)
public class LoadBalancer {
    static int N;
    static long K;

    static Node[] nodes;
    static int[] indegrees;
    static class Node {
        int x = 1;
        boolean isBalancer;
        int[] arr;
        int r;
        int id;
        long result = 0;

        Node(int id, int[] arr) {
            this.id = id;
            this.arr = arr;
            isBalancer = true;
            r = arr.length - 1;
        }

        Node(int id) {
            this.id = id;
            isBalancer = false;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        nodes = new Node[N + 1];
        indegrees = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());

            // Work Node 인 경우
            if (r == 0) {
                nodes[i] = new Node(i);
            }

            // Load Balancer 인 경우
            else {
                int[] temp = new int[r + 1];
                for (int j = 1; j <= r; j++) {
                    temp[j] = Integer.parseInt(st.nextToken());
                    indegrees[temp[j]]++;
                }

                nodes[i] = new Node(i,temp);
            }
        }

        nodes[1].result = K;

        Queue<Integer> queue = new ArrayDeque<Integer>();
        queue.add(1);
        
        // 위상 정렬 진행
        while(!queue.isEmpty()) {
            Node cur_node = nodes[queue.remove()];

            if(cur_node.isBalancer) {
                long divide_val = cur_node.result / cur_node.r;
                long remain_val = cur_node.result % cur_node.r;

                for(int i = 1; i <= cur_node.r; i++) {
                    Node next_node = nodes[cur_node.arr[i]];

                    if (i <= remain_val) {
                        next_node.result += (divide_val + 1);
                    }

                    else {
                        next_node.result += divide_val;
                    }

                    if(--indegrees[next_node.id] == 0) {
                        queue.add(next_node.id);
                    }
                }
            }
        }


        for(int i = 1; i <= N; i++)
            bw.write(nodes[i].result + " ");

        bw.flush();
        bw.close();
        br.close();

    }
}
