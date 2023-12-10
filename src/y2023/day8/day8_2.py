from collections import defaultdict
from math import gcd, prod


def lcm(steps):
    res = 1
    for step in steps:
        res = (step * res) // gcd(step, res)
    return res


def solution():
    with open('input.txt') as f:
        dirs, links = f.read().strip().split('\n\n')

        dict = {}
        starts = []
        for link in links.split('\n'):
            key, pair = link.strip().split(' = ')
            if key.endswith('A'):
                starts.append(key)
            dict[key] = pair[1:-1].split(', ')

        dirs = dirs.replace('L', '0').replace('R', '1')
        allSteps = []
        for current in starts:
            steps = 0
            i = 0
            while not current.endswith('Z'):
                index = i % len(dirs)
                new = dict[current][int(dirs[index])]
                current = new

                steps += 1
                i += 1
            allSteps.append(steps)

        return lcm(allSteps)


if __name__ == '__main__':
    print(solution())
