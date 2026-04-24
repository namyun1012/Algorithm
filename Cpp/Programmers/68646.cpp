#include <bits/stdc++.h>

using namespace std;


// 번호가 더 작은 풍선을 터트리는 행위는 최대 1번.
// 그 이후에는 번호가 더 큰 풍선 
// 최후 까지 남기는 것이 가능한 풍선들..
// a 의 길이는 백만 -> O(N log N) 까지일 듯..
// 일단 풍선 별로 확인은 해 봐야 하나 O(N^2) 는 안되고, 이진 탐색이어야 할 듯..
// 제일 작은 거.. 그냥 남기기 가능
// 2번 째로 작은 거.. 1번 쓰고 그냥 남기기 가능
// 3번쨰로 작은 거 부터는 한 쪽에만 자기 자신 보다 작은 것이 몰려 있을 경우 가능함. 즉 양쪽에서의 최솟값이 현재 보다 작으면 안됨
// 왜 이리 어지럽게 풀었지..


int solution(vector<int> a) {
    int answer = 0;
    
    vector<int> sorted = a;
    
    sort(sorted.begin(), sorted.end(), [](int& a, int& b ) {
        return a < b;
    });
    
    // m 에 ranking 적어넣기..
    unordered_map<int, int> m;
    for (int i = 0; i < a.size(); i++) {
        m[sorted[i]] = (i + 1);
    }
    
    // 첫 번째 랑 마지막은 무조건 남기기 가능함.
    answer = 2;
    
    // 왼쪽에서 가장 작은 거. => 갱신으로 처리 가능.
    int left = a[0];
    
    // 오른 쪽에서 가장 작은 거. => 저장을 해놓아야 함..
    
    priority_queue<int, vector<int>, greater<int>> pq;
    unordered_map<int, int> visited;

    for (int i = 2; i < a.size(); i++) {
        pq.push(a[i]);
    }
    
    
    // i = 1번째 부터 마지막 전 까지 진행함.
    for (int i = 1; i < a.size() - 1; i++) {
        
        // 초기화 진행
        int cur = a[i];
        visited[cur] = 1;
        int right = pq.top();
        
        // right 갱신
        if (cur == right) {
            
            int new_right = pq.top();
            
            while (visited[new_right] != 0) {
                pq.pop();
                new_right = pq.top();
            }
            right = new_right;
        }  
        
        // cout << left << " " << cur << " " << right << endl;
        
        // 둘 다 작을 때 answer 늘려주기
        // 양쪽에서 둘 다 최솟값이 현재 보다 작으면 안됨
        if (!(cur > left && cur > right)) answer++;
        
        // left 갱신
        left = min(left, cur);
    }
    
    
    
    
    // 3 -> 3
    // 10 -> 6
    

    
    
    return answer;
}