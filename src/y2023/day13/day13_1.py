from collections import defaultdict


def readRows(pattern):
    return pattern.splitlines()


def readColumns(pattern):
    res = []
    for i in range(len(pattern[0])):
        c = ""
        for j in range(len(pattern)):
            c += pattern[j][i]
        res.append(c)
    return res


def isSym(rows, i):
    diff = 1
    while 0 <= i + 1 + diff < len(rows) and 0 <= i - diff < len(rows):
        if not rows[i + 1 + diff] == rows[i - diff]:
            return False
        diff += 1

    return True


def findSym(rows):
    for i in range(len(rows) - 1):
        if rows[i] == rows[i + 1] and isSym(rows, i):
            return i
    return -1


def solution():
    with open('input.txt') as f:
        patterns = f.read().split("\n\n")

        res = 0
        for pattern in patterns:
            rows = readRows(pattern)
            columns = readColumns(rows)
            r1 = findSym(rows)
            c1 = findSym(columns)
            if c1 >= 0:
                res += c1 + 1
            if r1 >= 0:
                res += (r1 + 1) * 100

        return res


if __name__ == '__main__':
    print(solution())
