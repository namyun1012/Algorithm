#include <bits/stdc++.h>

using namespace std;

// 10만 * 25민
// Timeout 걸릴 듯함..?
// 누적합 사용 문제..
// 기록을 r2 - r1 - c2 - c1 을 돌지 말고. 기록만 해야 함..
// 어려움.

int solution(vector<vector<int>> board, vector<vector<int>> skill) {
    int N, M, T;
    N = board.size();
    M = board[0].size();
    T = skill.size();
    
    // 누적합은 0 을 비워놓는 것이 편함.
    // +k 영향 받는 지점, 안 받는 지점..
    vector<vector<int>> sum(N + 1, vector<int>(M + 1, 0));
    int answer = 0;
    
    for (int t = 0; t < T; t++) {
        int type = skill[t][0];
        
        int r1 = skill[t][1];
        int c1 = skill[t][2];
        int r2 = skill[t][3];
        int c2 = skill[t][4];
        int degree = type == 1 ? skill[t][5] * -1 : skill[t][5];
        
        // 적의 공격 받음. 
        sum[r2 + 1][c2 + 1] += degree; // 두번 뺏으니 한번 더해줌 그 이후로는.
        sum[r1][c1] += degree; // 이것부터 오른쪽 아래로 degree 만큼 영향 받음
        sum[r2 + 1][c1] -= degree; // 영향 안 받음
        sum[r1][c2 + 1] -= degree;    // 영향 안받음
    }
    
    for (int i = 0; i <= N; i++) {
        for (int j = 1; j <= M; j++) {
            sum[i][j] += sum[i][j - 1];
        }
    }

    for (int j = 0; j <= M; j++) {
        for (int i = 1; i <= N; i++) {
            sum[i][j] += sum[i - 1][j];
        }
    }
    
    
    
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (board[i][j] + sum[i][j] > 0) answer++;
        }
    }
    
    
    return answer;
}