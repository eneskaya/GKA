package de.haw.informatik.algorithms;

import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.datatypes.EFWeightedEdge;
import org.jgrapht.Graph;

import java.util.*;

/**
 * Created by finnmasurat on 10.06.15.
 */
public class Hierholzer {


    public static String computeEuler(Graph graph, EFVertex source) {

        ArrayList<EFVertex> oddCounter = new ArrayList<>();
        Map<EFVertex, Double> map = new HashMap<>();


        Set<EFVertex> vertexSet = graph.vertexSet();
        for (EFVertex ef : vertexSet) {

            if (graph.edgesOf(ef).isEmpty()) {
                throw new IllegalArgumentException("Isolated vertex");
            } else if (graph.edgesOf(ef).size() % 2 == 1) {
                oddCounter.add(ef);
            }
        }

        if (oddCounter.size() > 2) {
            throw new IllegalArgumentException("More than two odd vertices");
        }


        return "";
    }

}
