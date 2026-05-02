#include <bits/stdc++.h>

using namespace std;

int MOD = 20170805;
int dp[501][501][2]; // 0: 위 아래, 1: 왼쪽 -> 오른쪽

// DP 풀이..
int solution(int m, int n, vector<vector<int>> city_map) {
    for(int i=0; i<=m; i++) {
        for(int j=0; j<=n; j++) {
            dp[i][j][0] = dp[i][j][1] = 0;
        }
    }


    dp[1][1][0] = 1;
    dp[1][1][1] = 1;

    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            if (i == 1 && j == 1) continue; 

            int val = city_map[i-1][j-1];

            if (val == 0) {
                dp[i][j][0] = (dp[i-1][j][0] + dp[i][j-1][1]) % MOD;
                dp[i][j][1] = (dp[i-1][j][0] + dp[i][j-1][1]) % MOD;
            } 
            else if (val == 1) {
                dp[i][j][0] = 0;
                dp[i][j][1] = 0;
            } 
            else if (val == 2) {
                dp[i][j][0] = dp[i-1][j][0];
                dp[i][j][1] = dp[i][j-1][1];
            }
        }
    }

    return (dp[m-1][n][0] + dp[m][n-1][1]) % MOD;
}