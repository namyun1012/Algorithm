import heapq
# 다익스트라 알고리즘 구현하기

INF = int(1e9)
N, M = map(int, input().split())

graph = [[] for _ in range(N + 1)]

for i in range(M):
    a, b, c = map(int, input().split())
    graph[b].append((a, c))
    graph[a].append((b, c))
    
distance = [INF] * (N + 1)

start = 1
end = N


def dijkstra(start):
    queue = []
    heapq.heappush(queue, (0, start))
    distance[start] = 0
    
    while queue:
        dist, now = heapq.heappop(queue)
        if distance[now] < dist:
            continue
        
        for i in graph[now]:
            cost = dist + i[1]
            if cost < distance[i[0]]:
                distance[i[0]] = cost
                heapq.heappush(queue, (cost, i[0]))


dijkstra(1)

print(distance[N])

    

