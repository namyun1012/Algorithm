#include <bits/stdc++.h>

using namespace std;


// N 자리 이친수 를 구하는 프로그램
// 이친수는 1로 시작함. 
// 0 으로 끝나는 것, 1로 끝나는 것을 각자 운영하기

long long dp[91][2];

int main() {
    int N;
    cin >> N;

    dp[1][0] = 0;
    dp[1][1] = 1;
    dp[2][0] = 1;
    dp[2][1] = 0;

    for (int i = 3; i <= N; i++) {
        dp[i][0] = dp[i-1][0] + dp[i-1][1];
        dp[i][1] = dp[i-1][0];
    }


    cout << dp[N][0] + dp[N][1] << endl;

    return 0;
}