package Programmers;

import java.io.*;
import java.util.*;
public class Q42577 {
    // 단순 비교는 안 될 듯함
// 1 ~ 20 씩 전부 Map or Set 에 넣어가면서 진행하기.
    class Solution {
        public boolean solution(String[] phone_book) {

            int size = phone_book.length;
            
            // 정렬 필요 -> 긴 것 부터 체크하기 위함
            Arrays.sort(phone_book);

            Set<String> set = new HashSet<String>();

            for (int i = size - 1; i >= 0; i--) {
                String line = phone_book[i];

                // 전화번호 자체가 Set 에 있는 경우만 False
                if (set.contains(line)) return false;

                for (int j = 0; j < line.length(); j++) {
                    String sub_line = line.substring(0,j + 1);
                    // System.out.println(sub_line);
                    set.add(sub_line);
                }
            }

            return true;
        }
    }
}
