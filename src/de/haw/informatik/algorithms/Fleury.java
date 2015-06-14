package de.haw.informatik.algorithms;

import de.haw.informatik.datatypes.EFEdge;
import de.haw.informatik.datatypes.EFVertex;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.alg.EulerianCircuit;
import org.jgrapht.graph.UndirectedSubgraph;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public class Fleury {

    public static List<EFVertex> getCircuit(UndirectedGraph graph) {

        // Abbrechen, wenn kein Eulergraph
        if (!EulerianCircuit.isEulerian(graph)) throw new IllegalArgumentException("Kein Eulergraph.");

        List<EFVertex> path = new LinkedList<>();

        UndirectedGraph otherGraph = new UndirectedSubgraph<>(graph, null, null);

        EFVertex start = (EFVertex) graph.vertexSet().iterator().next();

        path.add(start);

        while (graph.edgeSet().size() > 0) {

            Set<EFVertex> e = graph.edgesOf(start);


        }

        return path;
    }

    public static boolean isBridge(UndirectedGraph graph, EFEdge edge) {

        UndirectedGraph subgraph = new UndirectedSubgraph<>(graph, null, null);

        subgraph.removeEdge(edge);

        ConnectivityInspector connectivityInspector = new ConnectivityInspector(subgraph);
        boolean isConnected = connectivityInspector.isGraphConnected();

        return !isConnected;
    }
}
