package trees;

public interface Position<E> {

	public E element();
	public E setElement(E elem);
	//public E SearchTreePosition(E elem);
	public int getWeight();
	public int setWeight(int weight);
}
