N, K = map(int, input().split())

arr = [[0] * 4 for _ in range(N)]
ranking = dict()

for i in range(N):
    arr[i] = list(map(int, input().split()))
    ranking[arr[i][0]] = 1

for i in range(N):
    first = arr[i][0]
    for j in range(N):
        # 금메달 개수 차이
        if arr[i][1] < arr[j][1]:
            ranking[first] += 1

        elif arr[i][1] == arr[j][1] and arr[i][2] < arr[j][2]:
            ranking[first] += 1
         
        elif arr[i][1] == arr[j][1] and arr[i][2] == arr[j][2] and arr[i][3] < arr[j][3]:
            ranking[first] += 1
            
print(ranking[K])

             