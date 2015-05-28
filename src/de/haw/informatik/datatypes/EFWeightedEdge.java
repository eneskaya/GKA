package de.haw.informatik.datatypes;

import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * @author Enes Kaya (CTO),
 *         Finn Masurat (Senior Vice President Of Visual Represenational Algorithmic Calculations)
 */
@SuppressWarnings("serial")
public class EFWeightedEdge extends DefaultWeightedEdge implements EFEdge, Comparable {

    @Override
    public String formatted() {
        return this.getSource().toString() + "," + this.getTarget().toString() + "::" + (int) this.getWeight();
    }

    @Override
    public String toString() {
        return "" + this.getWeight();
    }

    @Override
    public int compareTo(Object o) {
        return Double.compare(this.getWeight(), ((EFWeightedEdge) o).getWeight());
    }
}
