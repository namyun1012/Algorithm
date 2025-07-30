package Programmers;
import java.io.*;
import java.util.*;
public class Q42892 {


// Integer.compare 임
// 그냥 root 에다가 Insert 하면 되는 것을 너무 어렵게 생각한 듯 이진트리로

    class Solution {

        static int[][] answer;
        static int index = 0;

        public int[][] solution(int[][] nodeinfo) {
            int n = nodeinfo.length;

            Node[] tree = new Node[n];

            Map<Integer, Integer> height = new HashMap<Integer, Integer>();


            for (int i = 0; i < n; i++) {
                tree[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
            }

            Arrays.sort(tree);
            Node root = tree[0];

            for (int i = 1; i < n; i++) {
                insert(root, tree[i]);
            }

            answer = new int[2][n];

            preOrder(root);
            index = 0;
            postOrder(root);

            return answer;
        }


        class Node implements Comparable<Node>{
            public int num;
            public int x, y; // x 는 value, y는 level

            public Node left;
            public Node right;

            Node(int x, int y, int num) {
                this.x = x;
                this.y = y;
                this.num = num;
            }

            public int compareTo(Node o) {
                return Integer.compare(o.y, this.y);
            }
        }

        public void insert(Node parent, Node cur) {
            if (cur.x < parent.x) {
                if (parent.left == null) {
                    parent.left = cur;
                }

                else {
                    insert(parent.left, cur);
                }
            }


            else {
                if (parent.right == null) {
                    parent.right = cur;
                }

                else {
                    insert(parent.right, cur);
                }
            }
        }


        public void preOrder(Node cur) {
            if (cur == null) return;

            answer[0][index++] = cur.num;
            preOrder(cur.left);
            preOrder(cur.right);
        }

        public void postOrder(Node cur) {
            if (cur == null) return ;

            postOrder(cur.left);
            postOrder(cur.right);
            answer[1][index++] = cur.num;

        }
    }
}
