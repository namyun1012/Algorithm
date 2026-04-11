#include <bits/stdc++.h>

// k 번째 방법이 최대 20! 이라서. 전체 찾아보는 건 불가능 하다고 보아야 할 듯

using namespace std;

// 1 ~~  (n - 1)!
// 2 ~~  (n - 2)!
// 이런 식으로 k 에서 1개 씩 빼면서 결정할 수 있을 듯함.

// (n - 1)! 팩토리얼 부터 1개씩 뺌.. 남은 사람들 중 해당 되는 순서를 넣음..

vector<long long> fact;

vector<int> solution(int n, long long k) {
    vector<int> answer;
    
    // pq 준비
    vector<int> men;
    for (int i = 1; i <= n; i++) men.push_back(i);
    
    // 팩토리얼 미리 구해놓기..
    fact = vector<long long>(n + 1, 0);
    fact[1] = 1;
    for (int i = 2; i <= n; i++)
        fact[i] = fact[i-1] * i;
    
    // k 에서 i! 만큼 빼고 idx 구해서 진행함..
    // 그냥 pq 대신 n 이 20 밖에 안 되니 걍 매번 정렬해도 될 듯 함.
    for (int i = n - 1; i >= 1; i--) {
        
        long long cur = fact[i];
        
        int count = 0;
        while (k > cur) {
            k -= cur;
            count++;
        }
        
        int result = men[count];
        answer.push_back(result);
        men.erase(men.begin() + count);
        // 배열 재 정렬
        sort(men.begin(), men.end());
    }
    
    // 마지막 것 투입.
    answer.push_back(men[0]);
    
    return answer;
}