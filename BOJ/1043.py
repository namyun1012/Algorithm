# Union Find 를 사용해야 하는 문제

N, M = map(int, input().split())

temp = list(map(int, input().split()))

parties = [[] for _ in range(M + 1)]

known = [0] * (N + 1)
parent = [0] * (N + 1)

result = 0

def union(x, y):
    x = find(x)
    y = find(y)
    
    if (known[x] == 1):
        parent[y] = x
    
    elif (known[y] == 1):
        parent[x] = y
    else:
        parent[y] = x
    
    
def find(x):
    if (parent[x] != x):    
        parent[x] = find(parent[x])    
    return parent[x]

    
for i in range(len(parent)):
    parent[i] = i

for i in range(1, len(temp)):
    known[temp[i]] = 1

for i in range(M):
    parties[i] = list(map(int, input().split()))

for i in range(M):
    party = parties[i]
    
    for j in range(2, len(party)):
        person_1 = party[1]
        person_2 = party[j]
        union(person_1, person_2)


for i in range(M):
    party = parties[i]
    check = 0
    for j in range(1, len(party)):
        person = party[j]
        
        if known[find(person)] == 1:
            check = 1
            break
    
    if (not check):
        result += 1

print(result)
