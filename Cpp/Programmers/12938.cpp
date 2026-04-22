#include <bits/stdc++.h>

// 각 원소의 합이 S
// 위 조건을 만족하면서 각 원소의 곱이 최대.
// 원소의 개수 N
// S 가 1 억대라 Backtracking 으로 전부하는 건 아닌 듯함..
// Greedy 로 각 집합 내 원소 가 비슷할 수록 제일 높은 듯 함.
// ㅜ = 4 s = 9  2 2 2 3
// 우선 균등하게 분배 후 나머지를 .. Greedy 인가..?

using namespace std;

vector<int> solution(int n, int s) {
    
    vector<int> answer(n, 0);
    
    int q = s / n;
    int r = s % n;
    
    
    if (q <= 0) return vector<int>(1, -1);
    
    for (int i = 0; i < n; i++)
        answer[i] = q;
    
    for (int i = n - 1; i >= 0 && r > 0; i--, r--)
        answer[i]++;
    
    return answer;
}