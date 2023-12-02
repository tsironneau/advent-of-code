redMax = 12
greenMax = 13
blueMax = 14


def solution():
    with open('input.txt') as f:
        lines = f.readlines()
        res = 0
        for i, line in enumerate(lines):
            bad = False
            for tirage in line.split(':')[1].split(';'):
                for colors in tirage.split(','):
                    split = colors.strip().split(' ')
                    if (split[1] == 'red' and int(split[0]) > redMax
                            or split[1] == 'green' and int(split[0]) > greenMax
                            or split[1] == 'blue' and int(split[0]) > blueMax):
                        bad = True
                        break
            if not bad:
                res += i + 1

        return res


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print(solution())
