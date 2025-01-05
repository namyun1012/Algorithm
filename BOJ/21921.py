N, X = map(int, input().split())

visitor = list(map(int, input().split()))

count = 0
sum = 0
max = 0

#Start
for i in range(X):
    sum += visitor[i]

count = 1
max = sum


for i in range(N - X):
    sum += (visitor[i + X] - visitor[i])
    
    if sum > max :
        max = sum
        count = 1
    elif sum == max:
        count += 1
        
if(max == 0):
    print("SAD")
    
else:
    print(max)
    print(count)