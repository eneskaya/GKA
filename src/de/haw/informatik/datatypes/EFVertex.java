package de.haw.informatik.datatypes;

public class EFVertex implements Comparable<EFVertex> {

    private String _name;
    private int _attributeValue;
    private boolean _attributed;
    public EFVertex _predecessor;

    /**
     * Creates an EFEFVertex which is attributed.
     *
     * @param name      Name of the EFVertex
     * @param attribute Attribute value of EFVertex
     */
    public EFVertex(String name, int attribute) {
        _name = name;
        _attributeValue = attribute;
        _attributed = true;
    }

    /**
     * Creates an EFEFVertex which is not attributed.
     *
     * @param name Name of the EFVertex
     */
    public EFVertex(String name) {
        _name = name;
        _attributed = false;
    }

    public String getName() {
        return _name;
    }

    public int getAttributeValue() {
        return _attributeValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof EFVertex)) return false;

        return ((EFVertex) obj).getName().equals(_name);
    }

    @Override
    public int hashCode() {

        return _name.hashCode();
    }

    @Override
    public int compareTo(EFVertex o) {

        return Double.compare(_attributeValue, o._attributeValue);
    }

    @Override
    public String toString() {

        if (_attributed) {
            return _name + ":" + _attributeValue;
        }

        return _name;
    }
}
