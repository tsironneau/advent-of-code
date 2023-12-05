def find_winning(line):
    numbers = line.split(': ')[1].split(' | ')
    left = numbers[0].split()
    right = numbers[1].split()
    inter = list(set(left).intersection(right))
    return inter


def solution():
    with open('input.txt') as f:
        lines = f.readlines()
        card = 1
        cards = {}
        for i in range(len(lines)):
            cards[i + 1] = 1
        for line in lines:
            wp = find_winning(line)
            for i in range(card + 1, card + len(wp) + 1):
                cards[i] = cards[i] + cards[card]
            card += 1
        return sum(cards.values())


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print(solution())
