package de.haw.informatik.algorithms;

import de.haw.informatik.datatypes.EFVertex;
import org.jgrapht.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BreadthFirstSearch {

    private Graph _graph;

    private EFVertex _source, _target;

    private Set<EFVertex> _allVertices;

    public BreadthFirstSearch(Graph graph, EFVertex source, EFVertex target) {
        _graph = graph;
        _source = source;
        _target = target;

        _allVertices = graph.vertexSet();
    }

    private boolean doSearch() {

        HashMap<Integer, EFVertex> marked = new HashMap<Integer, EFVertex>();

        // Man kennzeichne den Knoten s mit 0 und setze i = 0
        int i = 0;
        marked.put(i, _source);

        // Man ermittle alle nichtgekennzeichneten Knoten in G
    for(EFVertex v : marked.get(i)){
        marked.put(i+1,)
    }

        return false;
    }

    /**
     * Finds and returns all neighbours of a specified vertex in a graph.
     *
     * @param vertex
     *          The vertex for which the neighbours should be found.
     * @return
     *          A set of vertices.
     */
    public Set<EFVertex> getNeighbourVertices(EFVertex vertex) {

        Set<EFVertex> neighbours = new HashSet<>();

        for (EFVertex v : _allVertices) {

            if (_graph.containsEdge(vertex, v)) {
                neighbours.add(v);
            }
        }

        return neighbours;
    }
}
