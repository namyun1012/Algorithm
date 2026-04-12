#include <bits/stdc++.h>

using namespace std;

int visited[1000001];

const int MAX = 1000001;

int solution(int x, int y, int n) { 
    queue<int> q;
    q.push(x);
    visited[x] = 1;
    
    while (!q.empty()) {
        int cur = q.front();
        q.pop();

        
        int nxt = cur + n;
        if (nxt >= 1 && nxt <= MAX && visited[nxt] == 0) {
            q.push(nxt);
            visited[nxt] = visited[cur] + 1;
        } 
    
        nxt = cur * 2;
        if (nxt >= 1 && nxt <= MAX && visited[nxt] == 0) {
            q.push(nxt);
            visited[nxt] = visited[cur] + 1;
        } 
        
        nxt = cur * 3;
        if (nxt >= 1 && nxt <= MAX && visited[nxt] == 0) {
            q.push(nxt);
            visited[nxt] = visited[cur] + 1;
        } 
        
    }
    
    return visited[y] - 1;
}