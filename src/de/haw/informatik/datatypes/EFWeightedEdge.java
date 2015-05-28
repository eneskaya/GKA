/**
 *
 */
package de.haw.informatik.datatypes;

import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * @author Enes Kaya (CTO),
 *         Finn Masurat (Senior Vice President Of Visual Represenational Algorithmic Calculations)
 */
@SuppressWarnings("serial")
public class EFWeightedEdge extends DefaultWeightedEdge implements EFEdge {

    @Override
    public String formatted() {
        return ((EFVertex) this.getSource()).toString() + "," + ((EFVertex) this.getTarget()).toString() + "::" + (int) this.getWeight();
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "" + this.getWeight();
    }
}
