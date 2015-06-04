package de.haw.informatik.tools;

import de.haw.informatik.datatypes.EFEdge;
import de.haw.informatik.datatypes.EFVertex;
import de.haw.informatik.datatypes.EFWeightedEdge;
import org.jgrapht.Graph;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ConnectedGraphRandomGenerator {

    private Set<EFVertex> _nodeSetA;
    private Set<EFVertex> _nodeSetB;
    private RandomNameGenerator _randomNameGenerator;
    private WeightedPseudograph _graph;

    public ConnectedGraphRandomGenerator() {

        this._randomNameGenerator = new RandomNameGenerator();
        this._nodeSetA = new HashSet<>();
        this._nodeSetB = new HashSet<>();
        this._graph = new WeightedPseudograph<EFVertex, EFWeightedEdge>(EFWeightedEdge.class);
    }

    private void populateSets(int countNodes) {

        while (_nodeSetA.size() < countNodes / 2) {

            _nodeSetA.add(new EFVertex(_randomNameGenerator.getName(), (int) (Math.random() * countNodes)));
        }

        while (_nodeSetB.size() < countNodes / 2) {

            _nodeSetB.add(new EFVertex(_randomNameGenerator.getName(), (int) (Math.random() * countNodes)));
        }
    }

    private void createEdgesBetweenVertices(int countEdges) {

        // Put the vertices in the graph
        for (EFVertex v : _nodeSetA) {
            _graph.addVertex(v);
        }

        for (EFVertex v : _nodeSetB) {
            _graph.addVertex(v);
        }

        Object[] verticesArrayA = _nodeSetA.toArray();
        Object[] verticesArrayB = _nodeSetB.toArray();

        // Get a random node from Set A and Set B
        // Then create an edge between them

        while (_graph.edgeSet().size() <= countEdges) {

            int a = (int) (Math.random() * verticesArrayA.length);
            int b = (int) (Math.random() * verticesArrayB.length);

            EFEdge edge = (EFEdge) _graph.addEdge(verticesArrayA[a], verticesArrayB[b]);

            int weight =
                    ((EFVertex) verticesArrayA[a]).getAttributeValue()
                            - ((EFVertex) verticesArrayB[b]).getAttributeValue();

            _graph.setEdgeWeight(edge, Math.abs(weight));
        }

        // Now remove all the vertices, that have no edge
        Set<EFVertex> vertexSet = _graph.vertexSet();

        Iterator<EFVertex> vertexSetIterator = vertexSet.iterator();

        while (vertexSetIterator.hasNext()) {
            EFVertex aktuellerVertex = vertexSetIterator.next();

            if (_graph.edgesOf(aktuellerVertex).isEmpty()) {
                _graph.removeVertex(aktuellerVertex);
            }
        }
    }

    public Graph getRandomConnectedGraph(int countVertices, int countEdges) {

        this.populateSets(countVertices);
        this.createEdgesBetweenVertices(countEdges);

        return _graph;
    }
}
