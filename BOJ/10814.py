# Python 문법 Test 용도

N = int (input())
arr = []
for i in range(N):
    age, name = input().split()
    age = int(age)
    arr.append((age, name))

arr.sort(key = lambda x:x[0])

for ele in arr:
    print(str(ele[0]) + " " + ele[1])
    
    