

def solution():
    with open('input.txt') as f:
        lines = f.readlines()
        res = 0
        for i, line in enumerate(lines):
            maxs = {"red": 0, "blue": 0, "green": 0}
            for game in line.split(':')[1].split(';'):
                for colors in game.split(','):
                    count, color = colors.strip().split(' ')
                    maxs[color] = max(maxs[color], int(count))

            score = 1
            for maxByColor in maxs.values():
                score *= maxByColor

            res += score

        return res


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print(solution())
