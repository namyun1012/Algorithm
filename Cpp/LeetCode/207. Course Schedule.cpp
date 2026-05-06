#include <bits/stdc++.h>

// start -> 0
// 음.. 위상 정렬 되서 전체 방문 가능한지. 확인 하는 것인 듯.
class Solution {
public:


    bool canFinish(int numCourses, vector<vector<int>>& prerequisites) {
        
        int N = numCourses;

        vector<int> ins(N, 0);
        vector<vector<int>> graphs(N, vector<int>());


        // [0, 1] => take 1 and take 0
        for (int i = 0; i < prerequisites.size(); i++) {
            auto pre = prerequisites[i];

            ins[pre[0]]++;
            graphs[pre[1]].push_back(pre[0]);
        }

        vector<int> visited(N, 0);
        
        queue<int> q;
        for (int i = 0; i < N; i++) {
            if (ins[i] == 0) {
                q.push(i);
                visited[i] = 1;
            }
        }


        while (!q.empty()) {
            int cur = q.front();
            q.pop();

            for (int nxt : graphs[cur]) {
                ins[nxt] -= 1;

                if (ins[nxt] == 0 && visited[nxt] == 0) {
                    q.push(nxt);
                    visited[nxt] = 1;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (visited[i] == 0) return false;
        }
        return true;
    }
};