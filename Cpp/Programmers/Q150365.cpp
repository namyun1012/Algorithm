#include <bits/stdc++.h>

using namespace std;


// (x,y) -> (r, c) 로 이동, 거리는 K 여야만 함. 포함해서 같은 격자를 2번 이상 방문하는 것임.
// 완전 탐색? 거리 제한만 걸어둔 체로..? k 가 2500 이라서 사실상 불가능함..
// 방해물 없어서 찾을 필요도 없긴 한 듯..
// du 혹은 rl 을 어떻게 붙일치가 간건임..
// Greedy 라고 함

string solution(int n, int m, int x, int y, int r, int c, int k) {
    
    int dist = abs(x - r) + abs(y - c);
    if (dist > k || (k - dist) % 2 != 0) return "impossible";
    
    string answer = "";
    int curX = x, curY = y;

    for (int i = 0; i < k; i++) {
        // d -> l -> r -> u 순서로 시도
        if (curX < n && (abs(curX + 1 - r) + abs(curY - c) <= k - i - 1)) {
            answer += 'd'; curX++;
        }
        else if (curY > 1 && (abs(curX - r) + abs(curY - 1 - c) <= k - i - 1)) {
            answer += 'l'; curY--;
        }
        else if (curY < m && (abs(curX - r) + abs(curY + 1 - c) <= k - i - 1)) {
            answer += 'r'; curY++;
        }
        else {
            answer += 'u'; curX--;
        }
    }
    
    return answer;
}