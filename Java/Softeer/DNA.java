package Softeer;
import java.io.*;
import java.util.*;

// 모르겠다 하나도 이해 안감

public class DNA {
    public static int N, M;
    public static char[][] dnas;
    public static char[][] superDnas;
    public static int[] result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dnas = new char[N][M];

        for(int i = 0; i < N; i ++) {
            String temp = br.readLine();
            for (int j = 0; j < M; j++)
                dnas[i][j] = temp.charAt(j);
        }

        // long 까지는 안감 (N 이 15 이하)
        int total = 1 << N;
        superDnas = new char[total][];
        result = new int[total];

        Arrays.fill(result, N + 1);

        // 처음의 빈 집합은 모든 곳이 .
        superDnas[0] = new char[M];
        Arrays.fill(superDnas[0], '.');

        for (int index = 1; index < total; index++) {
            int lower = 0;
            int temp = index;
            while (temp % 2 == 0) {
                temp /= 2;
                lower++;
            }

            int prevMask = index - (1 << lower);
            superDnas[index] = merge_dna(dnas[lower], superDnas[prevMask]);
        }

        for (int index = 1; index < total; index++) {
            if (superDnas[index] != null) {
                result[index] = 1;
            }

            else {
                getAnswer(index);
            }
        }


        System.out.println(result[total - 1]);

    }

    public static char[] merge_dna(char[] dna1, char[] dna2) {
        if (dna1 == null || dna2 == null) return  null;
        char[] merged = new char[M];

        for(int i = 0; i < M; i++) {
            if(dna1[i] == '.' && dna2[i] == '.') merged[i] = '.';
            else if(dna1[i] == '.') merged[i] = dna2[i];
            
            else if(dna2[i] == '.') merged[i] = dna1[i];
            
            else if(dna1[i] == dna2[i]) merged[i] = dna1[i];
            
            // 합병 불가
            else return null;
        }

        return merged;
    }

    public static int getAnswer(int index) {
        if (result[index] < N + 1) return result[index];

        List<Integer> list = new ArrayList<Integer>();
        int temp = index;

        for (int i = 0; i < N; i++) {
            if (temp % 2 == 1) list.add(i);
            temp /= 2;
        }

        int subsetSize = list.size();
        int[] digit = new int[subsetSize];

        int n1 = 0;
        int n2 = index;

        for (int i = 1; i < (1 << (subsetSize - 1)); i++) {
            for (int j = 0; j < subsetSize; j++) {
                int bit = 1 << list.get(j);

                if (digit[j] == 1) {
                    digit[j] = 0;
                    n1 -= bit;
                    n2 += bit;
                }

                else {
                    digit[j] = 1;
                    n1 += bit;
                    n2 -= bit;
                    break;
                }
            }

            int sum = getAnswer(n1) + getAnswer(n2);
            if (result[index] > sum) {
                result[index] = sum;
            }
        }

        return result[index];
    }
}
