#include <bits/stdc++.h>

// prime factors are limited to 2,3 and 5.

class Solution {
public:
    int nthUglyNumber(int n) {
        
        priority_queue<long long ,vector<long long>, greater<long long>> pq;

        int start = 1;
        pq.push(start);
        long long cur = -1;

        unordered_map<long long, int> m;
        m[start] = 1;


        for (int i = 0; i < n; i++) {
            cur = pq.top();
            pq.pop();

            long long nxt = cur * 2;

            if (!m[nxt]) {
                m[nxt] = 1;
                pq.push(nxt);
            }

            nxt = cur * 3;
            if (!m[nxt]) {
                m[nxt] = 1;
                pq.push(nxt);
            }

            nxt = cur * 5;

            if(!m[nxt]) {
                m[nxt] = 1;
                pq.push(nxt);
            }
        }

        return cur;
    }
};