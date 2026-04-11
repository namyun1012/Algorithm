#include <bits/stdc++.h>

using namespace std;

// 하나 선택시 idx + 1 % N, idx - 1 선택 불가..
// DP..?

// 뜯음, 안 뜯음
int dp1[100001];
int dp2[100001];

int solution(vector<int> sticker)
{
    int N = sticker.size();
    
    if (N == 1) return sticker[0];
    
    dp1[0] = sticker[0];
    dp1[1] = sticker[0];
    
    // 첫 번째 스티커 포함
    for (int i = 2; i < N - 1; i++) {
        dp1[i] = max(dp1[i-1], dp1[i-2] + sticker[i]);
    }
    
    dp2[0] = 0;
    dp2[1] = sticker[1];
    
    // 미포함
    for (int i = 2; i < N; i++) {
        dp2[i] = max(dp2[i-1], dp2[i-2] + sticker[i]);
    }
   

    return max(dp1[N-2], dp2[N-1]);
}