package Programmers;

import java.io.*;
import java.util.*;


public class Q72410_String {



    class Solution {
        public String solution(String new_id) {

            String step1 = new_id.toLowerCase();

            String step2 = "";

            for (int i = 0; i < step1.length(); i++) {
                char cur = step1.charAt(i);

                if (cur >= '0' && cur <= '9' ||
                        cur >= 'a' && cur <= 'z'||
                        cur == '-' || cur == '_' || cur == '.')

                    step2 += cur;
            }

            while (step2.contains("..")) {
                step2 = step2.replace("..", ".");
            }

            // step4
            if(step2.length() > 0 && step2.charAt(0) == '.')
                step2 = step2.substring(1);
            if (step2.endsWith("."))
                step2 = step2.substring(0, step2.length() - 1);

            // step5
            if (step2.length() == 0) step2 = "a";

            // step6
            if (step2.length() >= 16) {
                step2 = step2.substring(0, 15);

                if (step2.charAt(step2.length() - 1) == '.')
                    step2  = step2.substring(0, step2.length() - 1);
            }

            while (step2.length() <= 2) {
                step2 += step2.charAt(step2.length() - 1);
            }

            return step2;
        }
    }
}
