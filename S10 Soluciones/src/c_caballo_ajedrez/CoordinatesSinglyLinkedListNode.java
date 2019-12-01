package c_caballo_ajedrez;

public class CoordinatesSinglyLinkedListNode {
	public Coordinates						data;
	public CoordinatesSinglyLinkedListNode	next;

	public CoordinatesSinglyLinkedListNode(Coordinates nodeData) {
		data = nodeData;
	}

	public CoordinatesSinglyLinkedListNode(Coordinates nodeData,
			CoordinatesSinglyLinkedListNode path) {
		data = nodeData;
		next = path;
	}

	@Override
	public String toString() { // Para mejor uso del debugger.
		return "CoordinatesSinglyLinkedListNode [" + data + "]";
	}
}
