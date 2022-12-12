package y2022.day11;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class Monkey {

    final List<Long> items;
    final Function<Long, Long> update;
    final Function<Long, Integer> evaluation;

    long inspections;

    public Monkey(List<? extends Number> items, Function<Long, Long> update, Function<Long, Integer> evaluation) {
        this.items = new ArrayList<>(items.stream().map(Number::longValue).collect(Collectors.toList()));
        this.update = update;
        this.evaluation = evaluation;
    }

    void incInspections() {
        inspections++;
    }
}
