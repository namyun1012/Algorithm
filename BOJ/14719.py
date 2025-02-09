H, W = map(int, input().split())

arr = list(map(int, input().split()))

result = 0
cur = 0

# left 쪽이 큰 경우 고려하기
for i in range(1, W):
    if arr[i - 1] < arr[i]:
        # right 쪽이 더 큰 경우
        if arr[cur] < arr[i]:
            for j in range(cur, i):
                temp = arr[cur] - arr[j]
                if temp > 0:
                    result += temp
                    arr[j] = arr[cur]
            cur = i
        
        # left 쪽이 더 큰 경우
        else:
            for j in range(cur, i):
                temp = arr[i] - arr[j]
                if temp > 0:
                    result += temp
                    arr[j] = arr[i]
                
print(result)
        
