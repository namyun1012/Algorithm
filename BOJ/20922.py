N, K = map(int, input().split())

arr = list(map(int, input().split()))
num_arr = [0] * (200001)

max_length = 0
length = 0

start = 0
end = 0

while end < N:
    num = arr[end]
     
    # 클 시 start 늘림
    if(num_arr[num] >=  K):
        num_arr[arr[start]] -= 1
        start += 1
        length -= 1
        
    # 그냥 작을 시 end 늘림
    else :
        end += 1
        num_arr[num] += 1
        length += 1
        max_length = max(length, max_length)
        
print(max_length)
