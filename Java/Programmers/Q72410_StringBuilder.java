package Programmers;

public class Q72410_StringBuilder {




    class Solution {
        public String solution(String new_id) {

            String step1 = new_id.toLowerCase();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < step1.length(); i++) {
                char ch = step1.charAt(i);

                // Step2
                if ((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9') ||
                        ch == '-' || ch == '_' || ch == '.') {

                    // step3
                    if (ch == '.') {
                        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '.')
                            continue;
                    }
                    sb.append(ch);
                }
            }

            if (sb.length() > 0 && sb.charAt(0) == '.') {
                sb.deleteCharAt(0);
            }

            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '.') {
                sb.deleteCharAt(sb.length() - 1);
            }

            // step5
            if (sb.length() == 0) {
                sb.append("a");
            }

            // step6
            if (sb.length() >= 16) {
                sb.setLength(15); // 간단하게 길이를 고정할 수 있음

                if (sb.charAt(sb.length() - 1) == '.') {
                    sb.deleteCharAt(sb.length() - 1);
                }
            }

            while (sb.length() < 3) {
                sb.append(sb.charAt(sb.length() - 1));
            }


            return sb.toString();
        }
    }
}
