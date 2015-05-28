package de.haw.informatik.tools;

import de.haw.informatik.datatypes.EFEdge;
import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.datatypes.EFWeightedEdge;
import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.HashSet;
import java.util.Set;

public class GraphRandomGenerator {

    private static Graph _graph;

    private static RandomNameGenerator _randomNameGenerator;

    public static Graph getRandomGraph(int vertexCount, int edgeCount) {

        _randomNameGenerator = new RandomNameGenerator();

        _graph = new WeightedPseudograph<EFVertex, EFWeightedEdge>(EFWeightedEdge.class);

        Set<EFVertex> vertices = new HashSet<>();

        // Generate new EFVertex objects
        // and add them to the set
        while (vertices.size() < vertexCount) {
            String name = _randomNameGenerator.getName();
            vertices.add(new EFVertex(name, ((int) (Math.random() * vertexCount))));
        }

        // Add the vertices to the graph
        for (EFVertex v : vertices) {
            _graph.addVertex(v);
        }

        Object[] verticesArray = vertices.toArray();

        // Generate edges
        for (int i = 0; i < edgeCount; i++) {

            int a = (int) (Math.random() * vertexCount);
            int b = (int) (Math.random() * vertexCount);

            EFEdge edge = (EFEdge) _graph.addEdge(verticesArray[a], verticesArray[b]);

            int weight = ((EFVertex) verticesArray[a]).getAttributeValue() - ((EFVertex) verticesArray[b]).getAttributeValue();

            ((WeightedGraph) _graph).setEdgeWeight(edge, Math.abs(weight));
        }

        return _graph;
    }
}