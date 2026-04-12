#include <bits/stdc++.h>

using namespace std;


// 그냥 이상한 수작 부리지 말고 Stack 써먹기..
int solution(vector<vector<int>> board, vector<int> moves) {
    int answer = 0;
    
    stack<int> s;
    int T = moves.size();
    
    int N = board.size();
    
    for (int t = 0; t < T; t++) {
        int move = moves[t];
        
        int cur = -1;
        for (int i = 0; i < N; i++) {
            if (board[i][move - 1] != 0) {
                cur = board[i][move - 1];
                board[i][move - 1] = 0;
                break;
            }
        }
        
        // 같을 시 => 폭파
        if (!s.empty() && s.top() == cur) {
            s.pop();
            answer += 2;
        }
        
        // 폭파 안 되는 경우
        else if (cur != -1) {
            s.push(cur);
        }
        
        
    }
    
    return answer;
}