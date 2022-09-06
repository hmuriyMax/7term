import ...

def count_chars(string):
    data = {}
    for ch in string:
        data[ch] = True
    return len(data)


n = int(input())
for i in range(n):
    tstr = input()
    data = tstr.split(',')
    chars = count_chars(data[0]+data[1]+data[2])
    ddmmsum = int(data[3]) + int(data[4])
    fletter = ord(data[0].lower()) - ord('a') + 1