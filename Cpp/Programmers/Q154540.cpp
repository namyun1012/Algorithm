C++ 에서 여기 visited, board 함수 처리하는 곳도

& 를 해줘야 전역 변수 처럼 쓰이냐;;

메모리 값 복사하는 거 아니었누



#include <bits/stdc++.h>



using namespace std;



// X -> 바다, 숫자는 무인도 

// 상, 하, 좌, 우 , 연결되는 칸에 숫자를 모두 합한 값 -> 며칠 간 머무른ㄴ 지



// 걍 전구역 BFS ㄱㄱ



int N;

int M;



int dx[] = {0,1,0,-1};

int dy[] = {1,0,-1,0};



int BFS(int y, int x, vector<vector<char>>& board, vector<vector<int>>& visited);

int OOP(int y, int x);



vector<int> solution(vector<string> maps) {

    vector<int> answer;

    

    N = maps.size();

    M = maps[0].size();

    

    vector<vector<char>> board(N, vector<char>(M, 0));

    vector<vector<int>> visited(N, vector<int>(M, 0));

    

    for (int i = 0; i < N; i++) {

        for (int j = 0; j < M; j++) {

            board[i][j] = maps[i][j];

        }

    }

    

    for (int i = 0; i < N; i++) {

        for (int j = 0; j < M; j++) {

            if (board[i][j] != 'X' && visited[i][j] == 0) {

                answer.push_back(BFS(i, j, board, visited));

            }

        }

    }

    

    if (answer.empty()) answer.push_back(-1);

    

    sort(answer.begin(), answer.end());

    

    return answer;

}





int BFS(int y, int x, vector<vector<char>>& board, vector<vector<int>>& visited) {

    

    int result = 0;

    

    // init

    queue<pair<int, int>> q;

    visited[y][x] = 1;

    q.push({y, x});

    result += board[y][x] - '0';

    

    while(!q.empty()) {

        int cur_y = q.front().first;

        int cur_x = q.front().second;

        q.pop();

        

        for (int dir = 0; dir < 4; dir++) {

            int nxt_y = cur_y + dy[dir];

            int nxt_x = cur_x + dx[dir];

            

            if (OOP(nxt_y, nxt_x)) continue;

            if (board[nxt_y][nxt_x] == 'X') continue;

            if (visited[nxt_y][nxt_x]) continue;

            

            q.push({nxt_y, nxt_x});

            visited[nxt_y][nxt_x] = 1;

            result += (board[nxt_y][nxt_x] - '0');

        }

    }

    

    return result;

}



int OOP(int y, int x) {

    if (y < 0 || y >= N || x < 0 || x >= M) return 1;

    return 0;

}