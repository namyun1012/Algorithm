#include <bits/stdc++.h>

using namespace std;

int N;

// A 팀 순서 고정
// B 팀의 i 번째 배열
// B 팀이 얻을 수 있는 최고 승점.
// 가장 적은 효율로 이겨야 함..
// 실패 1개 존재함.

int solution(vector<int> A, vector<int> B) {
    N = A.size();
    
    int answer = 0;
    
    // 우선 둘 다 정렬 진행함..
    sort(A.begin(), A.end());
    sort(B.begin(), B.end());
    
    // i 는 A의 idx, j 는 B의 idx
    
    int j = 0;
    for (int i = 0; i < N; i++) {
        
        while (j < N && B[j] <= A[i]) j++;
        
        if (B[j] > A[i]) {
            answer++;
            j++;
        }
    }
    
    return answer;
}