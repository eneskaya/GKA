package de.haw.informatik.tests;

import de.haw.informatik.algorithms.Fleury;
import de.haw.informatik.datatypes.EFEdge;
import de.haw.informatik.tools.EulerGraphRandomGenerator;
import org.jgrapht.Graph;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FleuryTest {

    @Test
    public void testEqualCountEdges() {

        Graph graph = EulerGraphRandomGenerator.constructGraph(300, 500);

        List<EFEdge> fleuryPath = Fleury.getPath(graph);

        assertEquals(500, fleuryPath.size());
    }

    @Test
    public void testEqualEdgesSet() {
        Graph graph = EulerGraphRandomGenerator.constructGraph(100, 100);

        Set<EFEdge> edgeSet = graph.edgeSet();
        List<EFEdge> edgeList = Fleury.getPath(graph);

        Set<EFEdge> edgeSetAfterFleury = new HashSet<>();
        edgeSetAfterFleury.addAll(edgeList);

        edgeSet.containsAll(edgeSetAfterFleury);

        assertTrue(edgeSet.containsAll(edgeSetAfterFleury));
    }
}
