#include <bits/stdc++.h>

// 문자열 최대 길이는 2500 => Brute Force?

// 앞뒤를 뒤집어도 똑같은 문자열;;
// 최소값은 1
// 답이 틀리긴 하는데..? 효율성은 ㄱㅊ은 듯..?

using namespace std;
int solution(string s)
{
    int answer=0;
    int N = s.size();
    
    for (int len = N; len >= 2; len--) {
        for (int start = 0; start + len <= N; start++) {
            
            string cur = s.substr(start, len);
            
            // cout << cur << endl;
            
            bool check = true;
            
            for (int i = 0; i <= len / 2 + 1; i++) {
                if (cur[i] != cur[len - 1 - i]) {
                    check = false;
                    break;
                }
            }
            
            if (check) return len;
        }
    }
    

    return 1;
}