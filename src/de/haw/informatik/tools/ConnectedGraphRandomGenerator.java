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
    public Graph getRandomConnectedGraph(int countVertices) {

        WeightedGraph graph = new WeightedPseudograph<EFVertex, EFWeightedEdge>(EFWeightedEdge.class);

        Set<EFVertex> s = new HashSet<>();
        Set<EFVertex> t = new HashSet<>();

        for (int i = 0; i < countVertices; i++) {

            EFVertex v = new EFVertex(rg.getName(), ((int) (Math.random() * countVertices)));

            if (s.contains(v)) {
                i--;
                continue;
            }

            s.add(v);
        }

        EFVertex current = this.randomVertexFromSet(s);

        for (EFVertex v : s) {
            graph.addVertex(v);
        }

        s.remove(current);
        t.add(current);

        while (s.size() > 0) {
            EFVertex neighbour = this.randomVertexFromSet(s);

            if (!t.contains(neighbour)) {
                EFEdge edge = (EFEdge) graph.addEdge(current, neighbour);

                int weight = current.getAttributeValue() - neighbour.getAttributeValue();
                graph.setEdgeWeight(edge, Math.abs(weight));

                s.remove(neighbour);
                t.add(neighbour);
            }

            current = neighbour;
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
