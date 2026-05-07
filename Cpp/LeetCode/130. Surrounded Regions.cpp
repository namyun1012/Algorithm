#include <bits/stdc++.h>

class Solution {
public:

    int N, M;

    int dx[4] = {0,0,-1,1};
    int dy[4] = {1,-1,0,0};

    bool OOP(int y, int x) {
        if (y < 0 || x < 0 || y >= N || x >= M) return false;
        return true;
    }

    void BFS(vector<vector<char>>& board, vector<vector<int>>& visited, int y, int x) {

        queue<pair<int, int>> q;
        vector<pair<int, int>> vec; // pair 저장용도.
        
        visited[y][x] = 1;
        q.push({y,x});
        vec.push_back({y,x});


        int min_x = 2e9;
        int min_y = 2e9;

        int max_x = -1;
        int max_y = -1;


        while (!q.empty()) {
            auto cur =q.front();
            q.pop();
            
            min_y = min(min_y, cur.first);
            min_x = min(min_x, cur.second);

            max_y = max(max_y, cur.first);
            max_x = max(max_x, cur.second);

            for (int dir = 0; dir <4 ;dir ++) {
                int nxt_x = cur.second + dx[dir];
                int nxt_y = cur.first + dy[dir];

                if (!OOP(nxt_y, nxt_x)) continue;
                if (board[nxt_y][nxt_x] == 'X') continue;
                if (visited[nxt_y][nxt_x]) continue;

                q.push({nxt_y, nxt_x});
                vec.push_back({nxt_y, nxt_x});
                visited[nxt_y][nxt_x] = 1;
            }
        }

        if (!(min_x == 0 || min_y == 0 || max_x == M - 1 || max_y == N - 1)) {
            for (auto& p : vec) {
                board[p.first][p.second] = 'X';
            }
        }

    }

    void solve(vector<vector<char>>& board) {
        
        N = board.size();
        M = board[0].size();

        vector<vector<int>> visited(N, vector<int>(M, 0));

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'O' && visited[i][j] == 0) {
                    BFS(board, visited, i, j);
                }
            }
        }
        return ;

    }
};