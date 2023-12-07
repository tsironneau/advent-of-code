from functools import cmp_to_key
from collections import defaultdict

symbols = ['J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A']


def compareSym(sym1, sym2):
    i1 = symbols.index(sym1)
    i2 = symbols.index(sym2)
    return i1 - i2


def groupByCount(hand):
    res = defaultdict(set)
    jcount = 0
    for c in hand:
        if c == 'J':
            jcount += 1
            continue
        count = hand.count(c)
        res[count].add(c)

    return res, jcount


def power(hand):
    group, jcount = groupByCount(hand)
    n_max = max(group.keys(), default=0)
    with_j = n_max + jcount
    if with_j == 5:
        return 7
    if with_j == 4:
        return 6
    if (len(group[3]) == 1 and len(group[2]) == 1) or (len(group[2]) == 2 and jcount == 1):
        return 5
    if with_j == 3:
        return 4
    if len(group[2]) == 2 or (len(group[2]) == 1 and jcount == 1):
        return 3
    if len(group[2]) == 1 or jcount == 1:
        return 2
    return 0


def compare(tuple1, tuple2):
    hand1 = tuple1[0]
    hand2 = tuple2[0]
    power1 = power(hand1)
    power2 = power(hand2)
    if power1 > power2:
        return 1
    if power1 < power2:
        return -1
    for i in range(len(hand1)):
        sym1 = hand1[i]
        sym2 = hand2[i]
        sym_cmp = compareSym(sym1, sym2)
        if sym_cmp != 0:
            return sym_cmp

    return 0


def solution():
    with open('input.txt') as f:
        lines = f.readlines()
        hands = []
        for line in lines:
            hand, bid = line.split()
            hands.append(tuple((hand, int(bid))))
        hands = sorted(hands, key=cmp_to_key(compare))

        res = 0
        for i in range(len(hands)):
            hand, bid = hands[i]
            res += bid * (i + 1)
        return res


if __name__ == '__main__':
    print(solution())
