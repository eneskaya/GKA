package de.haw.informatik.algorithms;

import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.datatypes.EFWeightedEdge;
import org.jgrapht.Graph;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.*;

public class Prim {

    // Map with all Vertices and the shortest Edge from the spanningTree to them
    public static Map<EFVertex, Double> map = new HashMap<>();

    public static double totalWeight;
    public static int graphAccesses;

    @SuppressWarnings("unchecked")
    public static Graph computeGraph(Graph graph, EFVertex startVertex) {


        PriorityQueue<EFVertex> leQueue = new PriorityQueue<>((o1, o2) -> {
            return Double.compare(map.get(o1), map.get(o2));
        });

        // put all Vertices with POSITIV_INFINITY in the map, to check that it will definitly be change
        for (EFVertex ef : (Set<EFVertex>) graph.vertexSet()) {
            graphAccesses++;
            if (ef.equals(startVertex)) {
                map.put(ef, 0.0);
            } else {
                map.put(ef, Double.POSITIVE_INFINITY);
            }
            leQueue.add(ef);
        }

        // spanningTree-Graph which we will return
        WeightedPseudograph<EFVertex, EFWeightedEdge> spanningTree =
                new WeightedPseudograph<>(EFWeightedEdge.class);

        spanningTree.addVertex(startVertex);

        while (!leQueue.isEmpty()) {

            EFVertex currentEFVertex = leQueue.poll();
            Set<EFWeightedEdge> set = graph.edgesOf(currentEFVertex);

            if (Double.compare(map.get(currentEFVertex), Double.POSITIVE_INFINITY) == 0) {
                spanningTree.addVertex(currentEFVertex);
            }


            for (EFWeightedEdge e : set) {
                graphAccesses++;
                // refreshing the distances from the spanningTree to the Vertices
                if (graph.getEdgeWeight(e) < map.get(graph.getEdgeTarget(e)) && graph.getEdgeSource(e).equals(currentEFVertex)) {
                    leQueue.remove(graph.getEdgeTarget(e));
                    EFVertex temp = (EFVertex) graph.getEdgeSource(e);
                    ((EFVertex) graph.getEdgeTarget(e))._predecessor = temp;
                    map.put((EFVertex) graph.getEdgeTarget(e), graph.getEdgeWeight(e));
                    leQueue.add((EFVertex) graph.getEdgeTarget(e));
                } else if (graph.getEdgeWeight(e) < map.get(graph.getEdgeSource(e)) && graph.getEdgeTarget(e).equals(currentEFVertex)) {
                    leQueue.remove(graph.getEdgeSource(e));
                    EFVertex temp = (EFVertex) graph.getEdgeTarget(e);
                    ((EFVertex) graph.getEdgeSource(e))._predecessor = temp;
                    map.put((EFVertex) graph.getEdgeSource(e), graph.getEdgeWeight(e));
                    leQueue.add((EFVertex) graph.getEdgeSource(e));
                }
            }

            // add the clostest direct neighbour to the spanningTree
            // if the spanningTree doesn't contain it already
            if (!spanningTree.containsVertex(currentEFVertex)) {
                spanningTree.addVertex(currentEFVertex);
                if (spanningTree.containsVertex(currentEFVertex._predecessor)) {
                    EFWeightedEdge temporaryEdge = spanningTree.addEdge(currentEFVertex._predecessor, currentEFVertex);
                    spanningTree.setEdgeWeight(temporaryEdge, map.get(currentEFVertex));
                    totalWeight += +map.get(currentEFVertex);
                }
            }

        }

        System.out.println("total weight = " + totalWeight);
        System.out.println("total graph accesses = " + graphAccesses);

        return spanningTree;
    }
}