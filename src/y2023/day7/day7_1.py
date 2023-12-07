from functools import cmp_to_key
from collections import defaultdict

symbols = ['2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A']


def compareSym(sym1, sym2):
    i1 = symbols.index(sym1)
    i2 = symbols.index(sym2)
    return i1 - i2


def groupByCount(hand):
    res = defaultdict(set)
    for c in hand:
        count = hand.count(c)
        res[count].add(c)
    return res


def power(hand):
    group = groupByCount(hand)
    if len(group[5]) > 0:
        return 7
    if len(group[4]) > 0:
        return 6
    if len(group[3]) > 0 and len(group[2]) > 0:
        return 5
    if len(group[3]) > 0:
        return 4
    if len(group[2]) == 2:
        return 3
    if len(group[2]) == 1:
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
            res += bid * (i+1)
        return res


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print(solution())
