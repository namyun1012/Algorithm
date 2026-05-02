#include <bits/stdc++.h>


using namespace std;

// N 개의 마을 중 K 시간 이하로 배달이 가능한 마을에서만 주문 받기..
// 그냥 딱 다익스트라 인듯..?


int solution(int N, vector<vector<int>> road, int K) {
    int answer = 0;
    
    vector<vector<pair<int, int>>> graphs(N + 1, vector<pair<int, int>>());
    
    for (auto edge : road) {
        int start = edge[0];
        int end = edge[1];
        int cost = edge[2];
        
        graphs[start].push_back({end, cost});
        graphs[end].push_back({start, cost});
    }
    
    vector<int> distance(N + 1, 2e9);
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;
    
    distance[1] = 0;
    pq.push({0, 1}); // cost, start edge
    
    while (!pq.empty()) {
        
        // first - cost , second - node
        auto cur = pq.top();
        pq.pop();
        
        if (cur.first > distance[cur.second]) continue;
        
        // first - node , second - cost
        for (auto nxt : graphs[cur.second]) {
            if (cur.first + nxt.second < distance[nxt.first]) {
                distance[nxt.first] = cur.first + nxt.second;
                pq.push({distance[nxt.first], nxt.first});
            }
        }
    }
    
    for (int i = 1; i <= N; i++) {
        if (distance[i] <= K) answer++;
    }
    

    return answer;
}