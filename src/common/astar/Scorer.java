package common.astar;

public interface Scorer<T extends Node<?>> {

    double computeScore(T from, T to);
}
