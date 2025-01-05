from collections import deque

N, M ,R = map(int, input().split())

graph = [[]  for _ in range(N + 1)]
visited = [0] * (N +1)
order = 0

for _ in range(M):
    i, j = map(int, input().split())
    graph[i].append(j)
    graph[j].append(i)

for i in range(1, N+1):
    graph[i].sort()

def bfs(start):
    global order
    queue = deque()
    order += 1
    visited[start] = order
    queue.append(start)
    
    while queue:
        cur = queue.popleft()
        
        for nxt in graph[cur]:            
            if visited[nxt]:
                continue
            
            order += 1
            visited[nxt] = order
            queue.append(nxt)
    
    
bfs(R)

for i in range(1, N + 1):
    print(visited[i])