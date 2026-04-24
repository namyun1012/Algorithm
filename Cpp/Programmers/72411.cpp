#include <bits/stdc++.h>

using namespace std;

// 코스요리
// 비트 연산 각인가..?
//
// orders 가 최대 20 => 대충 O(N^3) 가능..? , 알파벳은 최대 10개임
// 메뉴는 두 개 이상 , 2명 이상의 손님으로 부터 받아온 거.
// 가장 많이 함꼐 주문한 단품 메뉴들 .. 즉 최댓값과 동일한 코스 메뉴만 선정 가능
// 백트래킹 한 번하고 결과 넣으면 될 듯함.
// course 배열. 단품 메뉴들의 갯수.

// 왜 이렇게 backtracking 이상한 곳에서 못 풀지..?

unordered_map<string, int> m;

// 2^28 1024 * 1024 *256 * 20 가능할 듯..
void backtracking(vector<string>& orders, int course, int idx, string cur) {
    
    // 검증 실행
    if (cur.size() == course) {
        // cout << cur << endl;
        for (int i = 0; i < orders.size(); i++) {
            
            int cur_idx = 0;
            for (int j = 0; j < orders[i].size(); j++) {
                // 같을 시 cur_idx 추가
                if (cur_idx < cur.size() && cur[cur_idx] == orders[i][j]) {
                    cur_idx++;
                }
            }

            if (cur_idx == cur.size()) m[cur]++;

        }
        return ;
    }
    
    for (int i = idx; i <= (int)'Z'; i++) {   
        cur += char (i);
        backtracking(orders, course, i + 1, cur);
        cur.erase(cur.size() - 1);
    }    
}


vector<string> solution(vector<string> orders, vector<int> course) {
    vector<string> answer;
    
    int N = orders.size();
    
    // 우선 각 Menu 별 정렬 실행
    for (int i = 0; i < N; i++)
        sort(orders[i].begin(), orders[i].end());
    
    for (int ele : course) {
        m.clear();
        backtracking(orders, ele, (int)'A', "");
        
        int max_val = 0;
        
        // m 에서 최대값 인 것들 찾고 그것들만 넣음
        for (auto& values : m) {
            max_val = max(max_val, values.second);
        }
        
        if (max_val < 2) continue;
        
        for (auto& values : m) {
            if (values.second == max_val) answer.push_back(values.first);
        }
    }

    sort(answer.begin(), answer.end());
    
    return answer;
}