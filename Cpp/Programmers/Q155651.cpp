#include <bits/stdc++.h>
using namespace std;
// Greedy , 정렬 필요 초반에
int solution(vector<vector<string>> book_time) {

    sort(book_time.begin(), book_time.end());
    vector<int> rooms;

    for (auto& book : book_time) {
        int start = stoi(book[0].substr(0, 2)) * 60 + stoi(book[0].substr(3, 2));
        int end = stoi(book[1].substr(0, 2)) * 60 + stoi(book[1].substr(3, 2)) + 10;

        bool assigned = false;
        for (int i = 0; i < rooms.size(); i++) {
            // 기존 방의 청소가 끝난 시간이 현재 예약 시작 시간보다 작거나 같으면 사용 가능
            if (rooms[i] <= start) {
                rooms[i] = end; // 종료 시간 갱신
                assigned = true;
                break;
            }
        }

        // 들어갈 수 있는 방이 없으면 새 방 추가
        if (!assigned) {
            rooms.push_back(end);
        }
    }

    return rooms.size();
}