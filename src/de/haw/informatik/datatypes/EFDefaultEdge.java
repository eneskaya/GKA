package de.haw.informatik.datatypes;

import org.jgrapht.graph.DefaultEdge;

@SuppressWarnings("serial")
public class EFDefaultEdge extends DefaultEdge  implements EFEdge {
	
	@Override
	public String formatted() {
		return ((EFVertex) this.getSource()).getName() + "," + ((EFVertex) this.getTarget()).getName();
	}
}
