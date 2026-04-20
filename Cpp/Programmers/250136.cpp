#include <bits/stdc++.h>

using namespace std;

// 가로 길이 m 최대 500. BFS 500번 정도는 가능할 듯 함. => 효율성 테스트에서 실패..? 25000 * 500 은 무리수 인듯..
// 석유는 1
// BFS 를 반복하는 것이 아니라.. 해당 값 이미 있을 경우에는 그거 쓰기..

int n, m;

int dx[] = {0,0,-1,1};
int dy[] = {1,-1,0,0};


unordered_map<int, int> values;
int idx;

bool OOP(int y, int x) {
    if (x < 0 || y < 0 || y >= n || x >= m) return false;
    return true;
}

// BFS는 각 영역 별 한번씩만 진행되도록 함..
int BFS(vector<vector<int>>& land, vector<vector<int>>& visited, int y, int x) {
    
    queue<pair<int, int>> q;
    
    q.push({y, x});
    visited[y][x] = idx;
    
    int result = 1;
    
    while (!q.empty()) {
        auto cur = q.front();
        q.pop();
        
        for (int dir = 0; dir < 4; dir++) {
            pair<int, int> nxt = {cur.first + dy[dir], cur.second + dx[dir]};
            
            if (!OOP(nxt.first, nxt.second)) continue;
            if (land[nxt.first][nxt.second] == 0) continue;
            if (visited[nxt.first][nxt.second]) continue;
            
            q.push(nxt);
            visited[nxt.first][nxt.second] = idx;
            result++;
            
        }
    }
    
    
    values[idx++] = result;

    return result;
    
}

int solution(vector<vector<int>> land) {
    n = land.size();
    m = land[0].size();
    idx = 1;
    
    vector<vector<int>> visited;
    visited = vector<vector<int>>(n, vector<int>(m, 0));
    int answer = 0;
    
    // 처음에 한 번 쭉 진행...
    for (int j = 0; j < m; j++) { 
        for (int i = 0; i < n; i++) {
            if (land[i][j] == 1 && visited[i][j] == 0) {
                BFS(land, visited, i, j);
            }
        } 
    }
    
    for (int j = 0; j < m; j++) {
        int cur = 0;
        vector<int> calculated(idx + 1, 0);
        
        for (int i = 0; i < n; i++) {
            if (land[i][j] == 0) continue;
            
            // cur_idx 구함
            int cur_idx = visited[i][j];
            if (calculated[cur_idx]) continue;
            
            cur += values[cur_idx];
            calculated[cur_idx] = 1;
        }
        
        answer = max(cur, answer);
    }
    
    
//     for (auto value : values)
//         cout << value.first << " " << value.second << endl;
    
//     for (int i = 0; i < n; i++) {
//         for (int j = 0; j < m; j++) {
//             cout << visited[i][j] 
//         }
//     }
    
    return answer;
}