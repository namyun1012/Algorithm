package Programmers;
import java.util.*;
import java.io.*;
public class Q42747 {


    // 같은 값이 존재하는 경우 생각할 것.
// 생각보다 시간 복잡도 괜찮은 듯함
// 배열에 없는 숫자도 가능하다...
    class Solution {
        public int solution(int[] citations) {

            int N = citations.length;
            Arrays.sort(citations);

            // h 의 범위 1~N
            for (int i = 1; i <=  N; i++) {

                //
                int cur = citations[i - 1];

                // cur 이상의 것들 (최댓값 구해야 하므로)
                int h = N - i + 1;

                // cur 번 이상 인용된 논문이 h번 이상
                // => cur >= h 여야 함
                if (cur >= h) return h;
            }

            // [0,0,0] => 0
            return 0;
        }
    }
}
