from collections import defaultdict


def solution():
    with open('input.txt') as f:
        dirs, links = f.read().strip().split('\n\n')

        dict = {}
        current = 'AAA'
        for link in links.split('\n'):
            key, pair = link.strip().split(' = ')
            if current == '':
                current = key
            dict[key] = pair[1:-1].split(', ')

        dirs = dirs.replace('L', '0').replace('R', '1')
        steps = 0
        i = 0
        while current != 'ZZZ':
            index = i % len(dirs)
            new = dict[current][int(dirs[index])]
            current = new

            steps += 1
            i += 1
        return steps


if __name__ == '__main__':
    print(solution())
