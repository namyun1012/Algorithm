#include <bits/stdc++.h>

using namespace std;

// 테두리만 회전함
// 그냥 무난한 구현 문제 였음

int result = 0;

void printBoard(vector<vector<int>>& board) {
    
    for (int i = 1; i < board.size(); i++) {
        for (int  j = 1; j < board[0].size(); j++) {
            cout << board[i][j] << " ";
        }
        cout << endl;
    }
}


// 가장 작은 값 주기 + 새로운 
int rotate(vector<vector<int>>& board, int r1, int c1, int r2, int c2) {
    
    int min_value = 2e9;
    
    int temp = board[r1][c1];
    
    min_value = min(temp, min_value);
    
    
    // 위로 올림
    for (int i = r1; i < r2; i++) {
        board[i][c1] = board[i + 1][c1];
        min_value = min(board[i][c1], min_value);
    }
    
    
    // 왼쪽으로 당김
    for (int j = c1; j < c2; j++) {
        board[r2][j] = board[r2][j + 1];
        min_value = min(board[r2][j], min_value);
    }
    
    // 아래 쪽으로 당김
    for (int i = r2; i > r1; i--) {
        board[i][c2] = board[i-1][c2];
        min_value = min(board[i][c2], min_value);
    }
    
    // 오른 쪽으로 당김
    for (int j = c2; j > c1 + 1; j--) {
        board[r1][j] = board[r1][j - 1];
        min_value = min(board[r1][j], min_value);
    }
    
    // 마무리 용도
    board[r1][c1 + 1] = temp;
    
    
   return min_value; 
}

vector<int> solution(int rows, int columns, vector<vector<int>> queries) {
    vector<vector<int>> board(rows + 1, vector<int>(columns + 1, 0));
    
    int idx = 1;
    
    for (int i = 1; i <= rows; i++) {
        for (int j = 1; j <= columns; j++) {
            board[i][j] = idx++;
        }
    }
    
    
    
    int T = queries.size();
    
    vector<int> answer;
    
    for (int t = 0; t < T; t++) {
        int r1, c1, r2, c2;
        
        r1 = queries[t][0];
        c1 = queries[t][1];
        r2 = queries[t][2];
        c2 = queries[t][3];
        
        answer.push_back(rotate(board, r1, c1, r2, c2));
    }
    
    
    
    
    return answer;
}


