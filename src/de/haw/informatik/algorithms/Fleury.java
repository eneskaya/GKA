package de.haw.informatik.algorithms;

import de.haw.informatik.datatypes.EFEdge;
import de.haw.informatik.datatypes.EFVertex;
import org.jgrapht.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Fleury {

    @SuppressWarnings("all")
    public static List<EFEdge> getPath(Graph graph) {

        List<EFVertex> path = new ArrayList<>();

        // Choose the first element v0 of the graph...
        EFVertex vertex = (EFVertex) graph.vertexSet().toArray()[0];
        // ...and add to list
        path.add(vertex);

        return null;
    }

    /**
     * Takes a set of vertices and returns a random element
     * from this set.
     *
     * @param set
     * @return EFVertex
     */
    private static EFVertex randomVertexFromSet(Set<EFVertex> set) {
        int size = set.size();
        int item = new Random().nextInt(size);
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
