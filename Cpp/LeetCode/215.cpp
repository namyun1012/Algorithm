#include <bits/stdc++.h>

class Solution {
public:
    int findKthLargest(vector<int>& nums, int k) {
        
        priority_queue<int> pq;

        for (int ele : nums) pq.push(ele);

        int result = 1;
        while (k > 0) {
            result = pq.top();
            pq.pop();

            k -= 1;
        }


        return result;
    }
};