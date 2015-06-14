package de.haw.informatik.tools;

import de.haw.informatik.datatypes.EFEdge;
import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.datatypes.EFWeightedEdge;
import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ConnectedGraphRandomGenerator {

    private RandomNameGenerator rg;
    private Random random;

    public ConnectedGraphRandomGenerator() {
        rg = new RandomNameGenerator();
        random = new Random();
    }

    @SuppressWarnings("unchecked")
    public Graph getRandomConnectedGraph(int countVertices, int countEdges) {

        WeightedGraph graph = new WeightedPseudograph<EFVertex, EFWeightedEdge>(EFWeightedEdge.class);

        Set<EFVertex> unvisited = new HashSet<>();
        Set<EFVertex> visited = new HashSet<>();

        for (int i = 0; i < countVertices; i++) {

            EFVertex v = new EFVertex(rg.getName(), ((int) (Math.random() * countVertices)));

            if (unvisited.contains(v)) {
                i--;
                continue;
            }

            unvisited.add(v);
        }

        EFVertex current = this.randomVertexFromSet(unvisited);

        unvisited.forEach(graph::addVertex);

        unvisited.remove(current);
        visited.add(current);

        while (unvisited.size() > 0) {
            EFVertex neighbour = this.randomVertexFromSet(unvisited);

            if (!visited.contains(neighbour)) {
                EFEdge edge = (EFEdge) graph.addEdge(current, neighbour);

                int weight = current.getAttributeValue() - neighbour.getAttributeValue();
                graph.setEdgeWeight(edge, Math.abs(weight));

                unvisited.remove(neighbour);
                visited.add(neighbour);
            }

            current = neighbour;
        }

        if (graph.edgeSet().size() < countEdges) {

            while (graph.edgeSet().size() < countEdges) {
                // Get two random vertices
                EFVertex firstRandomVertex = this.randomVertexFromSet(graph.vertexSet());
                EFVertex secondRandomVertex = this.randomVertexFromSet(graph.vertexSet());

                // Create an edge between them
                EFEdge edge = (EFEdge) graph.addEdge(firstRandomVertex, secondRandomVertex);
                int weight = firstRandomVertex.getAttributeValue() - secondRandomVertex.getAttributeValue();

                graph.setEdgeWeight(edge, Math.abs(weight));

                // repeat until the edge count has been reached
            }
        }

        return graph;
    }

    /**
     * Takes a set of vertices and returns a random element
     * from this set.
     *
     * @param set
     * @return EFVertex
     */
    private EFVertex randomVertexFromSet(Set<EFVertex> set) {
        int size = set.size();
        int item = random.nextInt(size);
        int i = 0;

        for (EFVertex v : set) {
            if (item == i) {
                return v;
            }
            i++;
        }

        // never reached actually
        return (EFVertex) set.toArray()[0];
    }
}
