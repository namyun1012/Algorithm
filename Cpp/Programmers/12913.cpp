#include <bits/stdc++.h>

using namespace std;

// DP 인 듯함. => 비교적 간단한 DP 문제

int solution(vector<vector<int>> land)
{
    int N = land.size();
    
    vector<vector<int>> dp(N, vector<int>(4, 0));
    
    for (int j = 0; j < 4; j++) dp[0][j] = land[0][j];
    
    for (int i = 1; i < N; i++) {
        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 4; k++) {
                if (k == j) continue;
                dp[i][j] = max(dp[i][j], dp[i-1][k] + land[i][j]);
            }   
        }
    }
    
    int answer = 0;
    for (int j = 0; j < 4; j++) answer = max(answer, dp[N-1][j]);
    
    return answer;
}