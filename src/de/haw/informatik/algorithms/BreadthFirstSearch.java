package de.haw.informatik.algorithms;

import de.haw.informatik.datatypes.EFEdge;
import de.haw.informatik.datatypes.EFVertex;
import org.jgrapht.Graph;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.*;

public class BreadthFirstSearch {

    private Graph<EFVertex, EFEdge> _graph;

    private EFVertex _source, _target;

    private Set<EFVertex> _allVertices;

    public BreadthFirstSearch(Graph<EFVertex, EFEdge> graph, EFVertex source, EFVertex target) {
        _graph = graph;
        _source = source;
        _target = target;

        _allVertices = graph.vertexSet();
    }

    public String doSearch() {

        if (_graph != null && _source != null && _target != null && !_source.equals(_target)
                && _graph.containsVertex(_source) && _graph.containsVertex(_target))
        {
            Queue<EFVertex> queue = new LinkedList<EFVertex>();
            Map<EFVertex, EFVertex> map = new HashMap<EFVertex, EFVertex>();

            Set<EFEdge> edges;

            queue.add(_source);
            map.put(_source, null);
            EFVertex temp;

            while (!queue.isEmpty() && !queue.contains(_target)) {
                temp = queue.poll();

                edges = _graph.edgesOf(temp);

                for (EFEdge e : edges) {
                    EFVertex targetVertex = _graph.getEdgeTarget(e);
                    if (_graph instanceof WeightedPseudograph || _graph instanceof Pseudograph) {
                        EFVertex targetVertex2;

                        if (!map.containsKey(targetVertex2 = _graph.getEdgeSource(e))) {
                            queue.add(targetVertex2);
                            map.put(targetVertex2, temp);
                        }
                    }

                    if (!map.containsKey(targetVertex)) {
                        queue.add(targetVertex);
                        map.put(targetVertex, temp);
                    }
                }
            }

            if (queue.contains(_target)) {
                EFVertex current = _target;
                String path = "";

                int countEdges = 0;

                while (!current.equals(_target)) {
                    path = " --> " + current.toString() + path;
                    current = map.get(current);
                    countEdges++;
                }

                return "Der Kürzeste Weg von " + _source.toString()
                        + " nach " + _target.toString() + " ist:\n"
                        + _source.toString() + path + "\n"
                        + "Dies erfolgt über " + countEdges + " Kante(n).";
            }
        }

        return "Vallah, hat nix geklappt.";
    }
}
