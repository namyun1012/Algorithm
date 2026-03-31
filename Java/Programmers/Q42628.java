package Programmers;
import java.io.*;
import java.util.*;
public class Q42628 {



// 그냥 관리하는 방식으로;;

    class Node implements Comparable<Node> {
        int idx;
        int value;
        boolean is_deleted;

        public Node(int idx, int value) {
            this.idx = idx;
            this.value = value;
            this.is_deleted = false;
        }

        public int compareTo(Node o) {
            return this.value - o.value;
        }

    }


    class Solution {
        public int[] solution(String[] operations) {
            int N = operations.length;
            StringTokenizer st;

            PriorityQueue<Node> min = new PriorityQueue<Node>(); // 최소 Heap
            PriorityQueue<Node> max = new PriorityQueue<Node>(Collections.reverseOrder());
            // 최대 Heap


            int size = 0;
            int idx = 0;
            ArrayList<Node> nodes = new ArrayList<Node>();

            for (int t = 0; t < N; t++) {
                st = new StringTokenizer(operations[t]);

                String order = st.nextToken();
                int number = Integer.parseInt(st.nextToken());

                if (order.equals("I")) {
                    Node node = new Node(idx, number);
                    nodes.add(node);
                    idx++;
                    max.add(node);
                    min.add(node);
                    size++;
                }

                else {
                    if (size == 0) continue;

                    // 최대 삭제
                    if (number == 1) {
                        while(true) {
                            Node node = max.poll();
                            if (!node.is_deleted) {
                                node.is_deleted = true;
                                break;
                            }
                        }
                    }

                    // 최소 삭제
                    else {
                        while(true) {
                            Node node = min.poll();
                            if (!node.is_deleted) {
                                node.is_deleted = true;
                                break;
                            }
                        }
                    }

                    size -= 1;
                }
            }

            if (size == 0) return new int[] {0, 0};

            if (size == 1) {
                int value = 0;

                while(true) {
                    Node node = min.poll();
                    if (!node.is_deleted) {
                        node.is_deleted = true;
                        value = node.value;
                        break;
                    }
                }

                return new int[] {value, value};
            }

            int max_value = 0;
            while(true) {
                Node node = max.poll();
                if (!node.is_deleted) {
                    node.is_deleted = true;
                    max_value = node.value;
                    break;
                }
            }

            int min_value = 0;
            while(true) {
                Node node = min.poll();
                if (!node.is_deleted) {
                    node.is_deleted = true;
                    min_value = node.value;
                    break;
                }
            }

            return new int[] {max_value, min_value};
        }


    }
}
