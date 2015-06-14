package de.haw.informatik.algorithms;

import de.haw.informatik.datatypes.EFEdge;
import de.haw.informatik.datatypes.EFVertex;
import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.*;

public class BreadthFirstSearch {

    private Graph _graph;

    private EFVertex _source, _target;

    /**
     * Computes the shortest path for two given vertices and a graph.
     *
     * @param graph
     * @param source
     * @param target
     */
    public BreadthFirstSearch(Graph graph, EFVertex source, EFVertex target) {
        _graph = graph;
        _source = source;
        _target = target;
    }

    public String doSearch() {

        if (_graph != null && _source != null && _target != null && !_source.equals(_target)
                && _graph.containsVertex(_source) && _graph.containsVertex(_target)) {
            Queue<EFVertex> queue = new LinkedList<>();
            Map<EFVertex, EFVertex> map = new HashMap<>();

            Set<EFEdge> edges;

            queue.add(_source);
            map.put(_source, null);
            EFVertex temp;

            while (!queue.isEmpty() && !queue.contains(_target)) {
                temp = queue.poll();

                edges = _graph.edgesOf(temp);

                for (EFEdge e : edges) {
                    EFVertex targetEFVertex = (EFVertex) _graph.getEdgeTarget(e);
                    if (_graph instanceof WeightedPseudograph || _graph instanceof Pseudograph) {
                        EFVertex targetEFVertex2;

                        if (!map.containsKey(targetEFVertex2 = (EFVertex) _graph.getEdgeSource(e))) {
                            queue.add(targetEFVertex2);
                            map.put(targetEFVertex2, temp);
                        }
                    }

                    if (!map.containsKey(targetEFVertex)) {
                        queue.add(targetEFVertex);
                        map.put(targetEFVertex, temp);
                    }
                }
            }

            if (queue.contains(_target)) {
                EFVertex current = _target;
                String path = "";

                int countEdges = 0;

                while (!current.equals(_source)) {
                    path = " --> " + current.toString() + path;
                    current = map.get(current);
                    countEdges++;
                }

                return "Der Kürzeste Weg von " + _source.toString()
                        + " nach " + _target.toString() + " ist:\n"
                        + _source.toString() + path + "\n"
                        + "über " + countEdges + " Kante(n).";
            }
        }

        return "Kein Ergebnis.";
    }
}
