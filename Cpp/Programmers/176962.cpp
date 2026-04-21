#include <bits/stdc++.h>

using namespace std;

// 스케줄링 문제, 
// 멈춰둔 과제는 더 이상 start 신경 쓰지 말 것. 넣을 때 playTime 을 빼버리는 방식으로 구현
// 실행 결과 무한 반복은 없고, 좀 오류 사항좀 확인해야 할 듯함.
struct Plan {
    string name;
    int start;
    int playtime;
    
    const bool operator<(const Plan& o) {
        return start < o.start;
    }
};

vector<string> solution(vector<vector<string>> plans) {
    int N = plans.size();
    vector<Plan> subjects;
    
    // 정리 하기
    for (int i = 0; i < plans.size(); i++) {
        string name = plans[i][0];
        int start = stoi(plans[i][1].substr(0,2)) * 60 + stoi(plans[i][1].substr(3,2));
        int playtime = stoi(plans[i][2]);
        
        subjects.push_back({name, start, playtime});
    }
    
    sort(subjects.begin(), subjects.end());
    //      , [](Plan& a, Plan& b) {
    //     return a.start < b.start;
    // });
    
    // 가장 최신에서 한 것 부터 빼서 진행함.
    stack<Plan> s;
    vector<string> answer;

    for (int i = 0; i < N; i++) {
        
        Plan cur = subjects[i];
        int nxt_start = -1;
        
        // 새로운 거 존재 확인
        if (i < N - 1) {
            nxt_start = subjects[i + 1].start;
        }
        
        // 다 못 끝내는 경우 + 새로운 과제 받아옴
        if (nxt_start != -1 && nxt_start - cur.start < cur.playtime) {
            s.push({cur.name, cur.start, cur.playtime - (nxt_start - cur.start)});
            // 새로운 과제 진행은 다음 곳에서
        }
        
        // 마지막인 경우..
        else if (nxt_start == -1) {
            answer.push_back(cur.name);
            
            while (!s.empty()) {
                answer.push_back(s.top().name);
                s.pop();
            }
        }
        
        // 이번 꺼 다 끝냄,  + 마지막 아님
        else if (nxt_start != -1 && nxt_start - cur.start >= cur.playtime ) {
            answer.push_back(cur.name);
            
            int remain =  (nxt_start - cur.start) - cur.playtime;
            
            while(!s.empty()) {
                
                Plan more = s.top();
                s.pop();
                
                // 남은 시간이 남은 과제의 실행 시간보다 부족한 경우 그 만큼 빼고 다시 넣음
                if (remain < more.playtime) {
                    s.push({more.name, more.start, more.playtime - remain});
                    break;
                }
                
                else {
                    answer.push_back(more.name);
                    remain -= more.playtime;
                }
            }
        }
        
    }

    
    return answer;
}