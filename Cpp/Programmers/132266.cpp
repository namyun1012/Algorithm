#include <bits/stdc++.h>

// O(N) => 무린 듯. n 이 10만.. BFS..?
// 다익스트라는 안됨.. O(N^2) 임..


using namespace std;

vector<int> solution(int n, vector<vector<int>> roads, vector<int> sources, int destination) {
    vector<int> answer;
    
    
    vector<vector<int>> graphs(n + 1, vector<int>());
    
    for (int i = 0; i < roads.size(); i++) {
        int start = roads[i][0];
        int end = roads[i][1];
        
        graphs[start].push_back(end);
        graphs[end].push_back(start);
    }
    
    vector<int> visited(n + 1, 0);
    
    queue<int> q;
    visited[destination] = 1;
    q.push(destination);
    
    while(!q.empty()) {
        int cur = q.front();
        q.pop();
        
        
        for (int nxt : graphs[cur]) {
            if (visited[nxt]) continue;
            
            q.push(nxt);
            visited[nxt] = visited[cur] + 1;
        }
    }
    
    
    for (int ele : sources) {
        answer.push_back(visited[ele] - 1);
    }
    
    return answer;
}