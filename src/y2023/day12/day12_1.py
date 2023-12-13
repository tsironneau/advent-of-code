from collections import defaultdict


def nb_valid_r(springs, hint):

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

    return res


def nb_valid(line):
    springs, hint = line.split()
    hint = tuple(map(int, hint.split(',')))#we can map all elements of a list with the map method
    return nb_valid_r(springs, hint)


def solution():
    with open('input.txt') as f:
        lines = f.readlines()

        res = 0
        for line in lines:
            res += nb_valid(line)

        return res


if __name__ == '__main__':
    print(solution())
