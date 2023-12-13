from collections import defaultdict

cache = {}


def nb_valid_r(springs, hint):
    key = (springs, hint)
    if key in cache:#we can just do key in dict to check if a key is in the dict !
        return cache[key]
    if len(springs) == 0:
        return 1 if len(hint) == 0 else 0

    if len(hint) == 0:
        return 1 if '#' not in springs else 0

    first = springs[0]
    res = 0
    if first in '.?':
        res += nb_valid_r(springs[1:], hint)

    if first in '#?':
        c_hint = hint[0]
        if len(springs) >= c_hint and '.' not in springs[:c_hint] and (
                len(springs) == c_hint or springs[c_hint] != '#'):
            res += nb_valid_r(springs[c_hint + 1:], hint[1:])

    cache[key] = res
    return res


def nb_valid(line):
    springs, hints = line.split()
    springs = ((springs + '?') * 5)[:-1]#Also "?".join([springs] * 5)
    hints = ((hints + ',') * 5)[:-1]
    hints = tuple(map(int, hints.split(',')))
    return nb_valid_r(springs, hints)


def solution():
    with open('input.txt') as f:
        lines = f.readlines()

        res = 0
        for line in lines:
            res += nb_valid(line)

        return res


if __name__ == '__main__':
    print(solution())
