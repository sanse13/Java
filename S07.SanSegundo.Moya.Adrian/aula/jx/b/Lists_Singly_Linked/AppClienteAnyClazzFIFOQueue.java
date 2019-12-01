package jx.b.Lists_Singly_Linked;

public class AppClienteAnyClazzFIFOQueue {

	public static void main(String[] args) {
		AnyClazzSinglyLinkedFIFOQueue<Integer> integers = new AnyClazzSinglyLinkedFIFOQueue<>();
		AnyClazzSinglyLinkedFIFOQueue<String> strings = new AnyClazzSinglyLinkedFIFOQueue<>();
		AnyClazzSinglyLinkedFIFOQueue<Object> objects = new AnyClazzSinglyLinkedFIFOQueue<>();

		integers.addTail(5);
		strings.addTail("Uno");

		Integer unInteger = integers.rmvHead();
		String unString = strings.rmvHead();

		System.out.println(unInteger);
		System.out.println(unString);
	}
}
