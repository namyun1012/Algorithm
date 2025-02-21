# Stack 을 사용하는 문제

N = int(input())

towers  = list(map(int, input().split()))
stack   = []
result  = [0] * (N + 1)

for i in range(N):
    
    cur = towers[i]
    
    while (stack) :
        receiver = stack.pop()
        
        if receiver[0] >= cur:
            result[i] = receiver[1]
            stack.append(receiver)
            break
        
    stack.append((cur, i + 1))
    
for i in range(N):
    print(result[i], end=" ")


