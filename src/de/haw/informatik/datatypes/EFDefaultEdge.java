package de.haw.informatik.datatypes;

import org.jgrapht.graph.DefaultEdge;

public class EFDefaultEdge extends DefaultEdge  implements EFEdge {
	
	@Override
	public String formatted() {
		return ((EFVertex) this.getSource()).getName()
				+ ","
				+ ((EFVertex) this.getTarget()).getName();
	}

	@Override
	public String toString() {
		return "";
	}
}
