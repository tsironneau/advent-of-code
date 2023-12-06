def location_ranges(seed, mapping_lines):
    current_ranges = seed
    already_modified_ranges = []
    for mapping_line in mapping_lines:

        not_modified_ranges = []
        coords = mapping_line.split(' ')
        dest, src, diff = [int(x) for x in coords]
        src_end = src + diff
        while current_ranges:
            (start, end) = current_ranges.pop()
            before = (start, min(end, src))
            modified_range = (max(start, src), min(src_end, end))
            after = (max(src_end, start), end)
            if before[0] < before[1]:
                not_modified_ranges.append(before)
            if modified_range[0] < modified_range[1]:
                already_modified_ranges.append((modified_range[0] - src + dest, modified_range[1] - src + dest))
            if after[0] < after[1]:
                not_modified_ranges.append(after)
        current_ranges = not_modified_ranges
    return already_modified_ranges + current_ranges


def solution():
    with open('input.txt') as f:
        lines = f.read().split('\n\n')
        print(lines)
        seeds = lines[0].split(': ')[1]
        mappings = lines[1:]
        print(seeds)
        print(mappings)
        split = seeds.split(' ')
        mins = []
        for i in range(0, len(split) - 2, 2):
            start = int(split[i])
            diff = int(split[i + 1])
            ranges = [(start, start + diff)]
            for mapping in mappings:
                map_data = mapping.split('\n')[1:]
                ranges = location_ranges(ranges, map_data)
            mins.append(min(ranges)[0])

        return min(mins)


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print(solution())
