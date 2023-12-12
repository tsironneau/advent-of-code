from collections import defaultdict

coordinates = {
    'n': (0, -1, 's'),
    's': (0, 1, 'n'),
    'w': (-1, 0, 'e'),
    'e': (1, 0, 'w'),
}

dirs = {
    '-': {
        'e': 'w',
        'w': 'e'
    },
    '|': {
        'n': 's',
        's': 'n'
    },
    'L': {
        'n': 'e',
        'e': 'n'
    },
    'J': {
        'n': 'w',
        'w': 'n'
    },
    '7': {
        'w': 's',
        's': 'w'
    },
    'F': {
        'e': 's',
        's': 'e'
    }
}


def get_start(lines):
    for y in range(len(lines)):
        line = lines[y]
        for x in range(len(line)):
            if lines[y][x] == 'S':
                return x, y


def solution():
    with open('input.txt') as f:
        lines = f.read().splitlines()

        # currentSymbol = 'F'
        currentSymbol = 'J' #Done at hand
        goTo = list(dirs[currentSymbol].keys())[0]
        x, y = get_start(lines)

        path = []
        print("-" * 10)
        while True:
            path.append((x,y))
            diffs = coordinates[goTo]
            x, y = x + diffs[0], y + diffs[1]
            currentSymbol = lines[y][x]
            if currentSymbol == 'S':
                break

            comeFrom = diffs[2]
            goTo = dirs[currentSymbol][comeFrom]

        return len(path) // 2


if __name__ == '__main__':
    print(solution())
