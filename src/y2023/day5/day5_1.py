redMax = 12
greenMax = 13
blueMax = 14


def locationNumber(seed, maps):
    current = int(seed)
    found = False
    for map in maps:
        if len(map) == 0:
            found = False
            continue
        if not map[0].isdigit():
            continue
        if found:
            continue

        coords = map.split(' ')
        dest, source, diff = [int(x) for x in coords]

        if source <= current < source + diff:
            found = True
            new_current = dest + (current - source)
            current = new_current

    return current


def solution():
    with open('input.txt') as f:
        lines = f.read().splitlines()
        seeds, *maps = lines
        seeds = seeds.split(": ")[1]
        locations = []
        for seed in seeds.split(' '):
            location = locationNumber(seed, maps)
            locations.append(location)

        return min(locations)


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print(solution())
