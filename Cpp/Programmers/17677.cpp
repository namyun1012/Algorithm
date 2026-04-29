#include <bits/stdc++.h>

using namespace std;

// 두 문자열, 두 글자 씩 귾어서 원소로.
// 집합의 유사도 구하기..
// 교집합 + 합집합
// 집합 구현시 중복 존재 가능함.



int solution(string str1, string str2) {
    
    for (int i = 0; i < str1.size(); i++)
        str1[i] = tolower(str1[i]);
    
    for (int j = 0; j < str2.size(); j++)
        str2[j] = tolower(str2[j]);
    
    // 집합 만들기.
    vector<string> vec1;
    vector<string> vec2;
    
    for (int i = 0; i < str1.size() - 1; i++) {
        char c1 = str1[i];
        char c2 = str1[i + 1];
        string word = "";
        if (isalpha(c1) && isalpha(c2)) vec1.push_back(word + c1 + c2);
    }
    
    for (int i = 0; i < str2.size() - 1; i++) {
        char c1 = str2[i];
        char c2 = str2[i + 1];
        string word = "";
        if (isalpha(c1) && isalpha(c2)) vec2.push_back(word + c1 + c2);
    }
    
    // map 에 죄다 넣고 비교 돌리기.
    unordered_map<string, int> m1;
    unordered_map<string, int> m2;
    
    for (string word : vec1) m1[word]++;
    for (string word : vec2) m2[word]++;
    
    // inter / un * 65536
    int inter = 0;
    int un = 0;
    
    
    // inter 부터..
    for (auto p :  m1) {
        string word = p.first;
        if (m1[word] > 0 && m2[word] > 0) {
            inter += min(m1[word], m2[word]);
            un += max(m1[word], m2[word]);            
        }
        
        else un += m1[word];
    }
    
    for (auto p : m2) {
        string word = p.first;
        if (m1[word] == 0 && m2[word] > 0) un += m2[word];
    }
    

    cout << inter << " " << un << endl;
    if (un == 0) return 65536;
    int result = (int) (((double) inter / (double) un) * (double) 65536);

    return result;
}