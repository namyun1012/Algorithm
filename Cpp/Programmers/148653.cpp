#include <bits/stdc++.h>

using namespace std;

// 실패..
// 그 위에 있는 거 1번 쓰고 아래 거 는 안되나..?
// 큰 숫자 부터 해야 하나
// 0 보다 작을 시 엘리베이터는 안 움직임..
// 즉 Min 을 쓸 수 있는 범위가 정해져 있다는 말임.. >> 마지막은 Mini 르 못 쓰는 듯..? 10 미만 일 때..
// 1억이니까 O(N) 도 힘들 지도..
// 99 -> + 1 - 100 => 2번 이면 충분함..
// 각 자릿수를 0으로 맞추어야 하는데..
// 3개 실패 중임..
// 


int result = 1e9;


void dfs(int cur, int count) {
    
    if (count > result) return;
    
    if (cur == 0) {
        result = min(count, result);
        return ;
    }
    
    int remain = cur % 10;
        
    dfs(cur / 10, count + remain);
    dfs((cur / 10) + 1, 10 - remain + count);
}



int solution(int storey) {

    dfs(storey, 0);

    return result;
}vxcxc