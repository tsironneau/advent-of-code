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
    return list(zip(*pattern))


def nb_diffs(rows, i):
    diff = 0
    nb_diffs = 0
    while 0 <= i + 1 + diff < len(rows) and 0 <= i - diff < len(rows):
        r1 = rows[i + 1 + diff]
        r2 = rows[i - diff]
        for j in range(len(r1)):
            nb_diffs += 1 if r1[j] != r2[j] else 0
        diff += 1

    return nb_diffs


def findSym(rows):
    for i in range(len(rows) - 1):
        if nb_diffs(rows, i) == 1:
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
