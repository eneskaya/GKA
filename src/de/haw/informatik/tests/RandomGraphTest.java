package de.haw.informatik.tests;

import de.haw.informatik.tools.EulerGraphGenerator;
import org.jgrapht.alg.EulerianCircuit;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class RandomGraphTest {

    @Test
    public void testEulerGraph() {

        for (int i = 0; i < 100; i++) {
            assertTrue(EulerianCircuit.isEulerian(EulerGraphGenerator.getGraph(1000, 10000)));
        }
    }
}
