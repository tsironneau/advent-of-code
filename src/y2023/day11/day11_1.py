from collections import defaultdict


def emptyLines(lines):
    res = []
    for i in range(len(lines)):
        if len(set(lines[i])) == 1:
            res.append(i)
    return res


def findGalaxies(lines):
    res = []
    for i in range(len(lines)):
        for j in range(len(lines[0])):
            if lines[i][j] == '#':
                res.append((i, j))
    return res


def bonusSteps(mi, ma, empty):
    res = 0
    for e in empty:
        if mi <= e <= ma:
            res += 1
    return res


def pathLength(g1, g2, emptyL, emptyC):
    g1x = g1[0]
    g2x = g2[0]
    g1y = g1[1]
    g2y = g2[1]
    manh = abs(g1x - g2x) + abs(g1y - g2y)

    manh += bonusSteps(min(g1x, g2x), max(g1x, g2x), emptyL)
    manh += bonusSteps(min(g1y, g2y), max(g1y, g2y), emptyC)

    return manh


def solution():
    with open('input.txt') as f:
        lines = f.read().splitlines()

        emptyL = emptyLines(lines)
        emptyC = emptyLines(list(zip(*lines)))

        galaxies = findGalaxies(lines)

        res = 0
        for i in range(len(galaxies) - 1):
            for j in range(i + 1, len(galaxies)):
                g1 = galaxies[i]
                g2 = galaxies[j]
                res += pathLength(g1, g2, emptyL, emptyC)

        return res


if __name__ == '__main__':
    print(solution())
