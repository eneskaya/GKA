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

    public static List<EFVertex> getCircuit(UndirectedGraph<EFVertex, EFEdge> graph) {

        // Cancel, if no eulerian graph
        if (!EulerianCircuit.isEulerian(graph)) throw new IllegalArgumentException("Kein Eulergraph.");

        List<EFVertex> path = new LinkedList<>();

        // choose any vertex to start from
        EFVertex current = graph.vertexSet().iterator().next();

        // add the starting vertex as the first node
        path.add(current);

        // while there are still edges to be traversed...
        while (graph.edgeSet().size() != 0) {

            // ...select the edges of the the current vertex
            Set<EFEdge> currentEdgeSet = graph.edgesOf(current);

            EFEdge chosenEdge = null;

            // If there is only one choice, choose this edge...
            if (currentEdgeSet.size() == 1) {
                chosenEdge = currentEdgeSet.iterator().next();
            } else {
                // else, go through the edges and select one that isn't a bridge
                for (EFEdge e : currentEdgeSet) {
                    if (!isBridge(graph, e)) {
                        chosenEdge = e;
                        break;
                    }
                }
            }

            if (chosenEdge == null) chosenEdge = currentEdgeSet.iterator().next();

            // Now, from the chosen edge, "travel" to the target of the edge
            EFVertex travelTo = graph.getEdgeTarget(chosenEdge).equals(current) ?
                    graph.getEdgeSource(chosenEdge) : graph.getEdgeTarget(chosenEdge);

            // The new current node is the one we just travelled to
            current = travelTo;

            if (path.get(0).equals(travelTo)) continue;
            path.add(travelTo);

            // Remove the edge we have traversed
            graph.removeEdge(chosenEdge);
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
