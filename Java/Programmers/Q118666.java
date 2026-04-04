package Programmers;
import java.io.*;
import java.util.*;

public class Q118666 {


    class Solution {
        public String solution(String[] survey, int[] choices) {

        /*
            R T C F J M A N
        */
            int[] types = new int[8];

            Character[] mapping = new Character[] {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};
            Map<Character, Integer> map = new HashMap<Character, Integer>();

            for (int i = 0; i < 8; i++)
                map.put(mapping[i], i);

            for (int i = 0; i < survey.length; i++) {
                String question = survey[i];
                int choice = choices[i];

                // 비동의
                if (choice < 4) {
                    types[map.get(question.charAt(0))] += 4 - choice;
                }

                // 동의
                else if (choice > 4) {
                    types[map.get(question.charAt(1))] += choice - 4;
                }

            }



            String answer = "";
            if (types[0] >= types[1]) answer += "R";
            else answer += "T";

            if (types[2] >= types[3]) answer += "C";
            else answer += "F";

            if (types[4] >= types[5]) answer += "J";
            else answer += "M";

            if (types[6] >= types[7]) answer += "A";
            else answer += "N";

            return answer;
        }


    }
}
