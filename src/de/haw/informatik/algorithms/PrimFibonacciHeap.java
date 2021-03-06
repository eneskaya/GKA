package de.haw.informatik.algorithms;

import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.datatypes.EFWeightedEdge;
import org.jgrapht.Graph;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.*;

public class PrimFibonacciHeap {

    //FibonacciHeap
    public static FibonacciHeap<EFVertex> leFib = new FibonacciHeap<>();

    // Map with all Vertices and the shortest Edge from the spanningTree to them
    public static HashMap<EFVertex, Double> map = new HashMap<>();

    //Map with all Vertices and their Entry in the Heap
    public static HashMap<EFVertex, FibonacciHeap.Entry> entryMap = new HashMap<>();

    public static double totalWeight;
    public static int graphAccesses;

    @SuppressWarnings("unchecked")
    public static Graph computeGraph(Graph graph, EFVertex startEFVertex) {

        // put all Vertices with POSITIV_INFINITY in the map, to check that it will definitly be change
        for (EFVertex ef : (Set<EFVertex>) graph.vertexSet()) {

            graphAccesses++;
            if (ef.equals(startEFVertex)) {
                FibonacciHeap.Entry entry = leFib.enqueue(ef, 0.0);
                entryMap.put(ef, entry);
                map.put(ef, 0.0);
            } else {
                FibonacciHeap.Entry entry = leFib.enqueue(ef, Double.POSITIVE_INFINITY);
                entryMap.put(ef, entry);
                map.put(ef, Double.POSITIVE_INFINITY);
            }
        }

        // spanningTree-Graph which we will return
        WeightedPseudograph<EFVertex, EFWeightedEdge> spanningTree =
                new WeightedPseudograph<>(EFWeightedEdge.class);

        spanningTree.addVertex(startEFVertex);

        while (!leFib.isEmpty()) {

            EFVertex currentEFVertex = leFib.dequeueMin().getValue();
            Set<EFWeightedEdge> set = graph.edgesOf(currentEFVertex);


            if (Double.compare(map.get(currentEFVertex), Double.POSITIVE_INFINITY) == 0) {
                spanningTree.addVertex(currentEFVertex);
            }

            for (EFWeightedEdge e : set) {
                graphAccesses++;
                // refreshing the distances from the spanningTree to the Vertices
                if (graph.getEdgeWeight(e) < map.get(graph.getEdgeTarget(e)) &&
                        graph.getEdgeSource(e).equals(currentEFVertex) &&
                        !spanningTree.containsVertex((EFVertex) graph.getEdgeTarget(e))) {

                    leFib.delete(entryMap.get(graph.getEdgeTarget(e)));
                    EFVertex temp = (EFVertex) graph.getEdgeSource(e);
                    ((EFVertex) graph.getEdgeTarget(e))._predecessor = temp;
                    map.put((EFVertex) graph.getEdgeTarget(e), graph.getEdgeWeight(e));
                    FibonacciHeap.Entry entry = leFib.enqueue((EFVertex) graph.getEdgeTarget(e), graph.getEdgeWeight(e));
                    entryMap.put((EFVertex) graph.getEdgeTarget(e), entry);
                } else if (graph.getEdgeWeight(e) < map.get(graph.getEdgeSource(e)) &&
                        graph.getEdgeTarget(e).equals(currentEFVertex) &&
                        !spanningTree.containsVertex((EFVertex) graph.getEdgeSource(e))) {

                    leFib.delete(entryMap.get(graph.getEdgeSource(e)));
                    EFVertex temp = (EFVertex) graph.getEdgeTarget(e);
                    ((EFVertex) graph.getEdgeSource(e))._predecessor = temp;
                    map.put((EFVertex) graph.getEdgeSource(e), graph.getEdgeWeight(e));
                    FibonacciHeap.Entry entry = leFib.enqueue((EFVertex) graph.getEdgeSource(e), graph.getEdgeWeight(e));
                    entryMap.put((EFVertex) graph.getEdgeSource(e), entry);
                }

            }

            // add the clostest direct neighbour to the spanningTree
            // if the spanningTree doesnt contains it already
            if (!spanningTree.containsVertex(currentEFVertex)) {
                spanningTree.addVertex(currentEFVertex);
                EFWeightedEdge temporaryEdge = spanningTree.addEdge(currentEFVertex._predecessor, currentEFVertex);
                spanningTree.setEdgeWeight(temporaryEdge, map.get(currentEFVertex));
                totalWeight += map.get(currentEFVertex);
            }
        }

        System.out.println("total weight = " + totalWeight);
        System.out.println("total graph accesses = " + graphAccesses);
        return spanningTree;
    }
}