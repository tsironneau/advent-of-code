package common.astar;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class Graph<I, T extends Node<I>> {

    private final Map<I, T> nodes;
    private final Map<T, Set<T>> links;

    public Graph(final Map<I, T> nodes,
                 final Map<T, Set<T>> links) {
        this.nodes = Collections.unmodifiableMap(nodes);
        this.links = Collections.unmodifiableMap(links);
    }

    public T getNode(I id) {
        return nodes.get(id);
    }

    public Set<T> getLinks(T node) {
        return links.get(node);
    }
}
