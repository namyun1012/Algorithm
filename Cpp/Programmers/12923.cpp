#include <bits/stdc++.h>

using namespace std;

// 가장 큰 약수로 깔려진 배열임.. 자기 자신을 제외하고..
// begin - end 5천
// 시간 초과 + 실패 1건
// n 으로 나눌 때, 소수로만..?
// 3163 까지 약수 구해 놓기
// 정확성에서 왜 1개 실패..?
// 100000000
// 10000000
vector<int> solution(long long begin, long long end) {
    
    const long long MAX = 1000000000;
    const long long MAX_SQRT = (long long) sqrt(MAX) + 1;
    const long long LIMIT = 10000000;
    
    vector<int> answer;
    // 5000
    for (long long i = begin; i <= end; i++) {
        
        long long n = 2;
        
        bool check = false;
        
        if (i == 1) {
            answer.push_back(0);
            continue;
        }
        
        long long best = 1;
        while (n <= MAX_SQRT) {
            if (i % n == 0) {
                if (i / n <= LIMIT) {
                    best = i / n; // LIMIT 이하인 가장 큰 약수
                    break; 
                }

                // i / n이 너무 크다면, n이라도 정답 후보로 챙겨둠
                best = n; 
            }
            n++;
        }
        
        // Prime
        answer.push_back(best); 
    }

    
    return answer;
}