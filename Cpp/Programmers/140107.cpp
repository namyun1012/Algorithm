#include <bits/stdc++.h>

using namespace std;

// 점 찍기 놀이.
// d 는 1백만.
// O (N) 선에서 끝내야 할 듯함.
// d 가 k 의 배수가 아님..

long long solution(int k, int d) {
    long long answer = 0;
    
    long long j = (d / k) * k;
    long long d2 = (long long) d * d;
    

    for (long long i = 0; i <= d; i += k) {
        
        // 거리 확인 후 더 클 시 max_j 를 k 만큼 뺌 결국 for 문 전체 기간 동안 한번만 돌게 됨.
        long long cur_dis = (i * i) + (j * j);
        
        while(cur_dis > d2) {
            j -= k;
            cur_dis = (i * i) + (j * j);
        }
        
        answer += (j / k) + 1;
    }
    
    
    return answer;
}