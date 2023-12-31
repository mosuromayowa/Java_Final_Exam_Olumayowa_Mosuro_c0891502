import java.util.ArrayList;
import java.util.List;
// Implement the single object class with constructor and methods
class SingleObject {
	private String name;

	public SingleObject(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
// implement the box class with the constructor for the box number and capacity and method
// to retrieve the name.
class Box {
	private List<Object> contents;
	private int boxNumber;


	public Box(int capacity, int boxNumber) {
		this.contents = new ArrayList<>(capacity);
		this.boxNumber = boxNumber;
	}

	public void addItem(Object item) {
		contents.add(item);
	}

	public List<Object> getContents() {
		return contents;
	}

	public int getBoxNumber() {
		return boxNumber;
	}
}

// Implement the `Move` class with a constructor to set the initial capacity,
// methods to add boxes, print contents, and find items in boxes.
class Move {
	private List<Box> boxes;

	public Move(int initialCapacity) {
		this.boxes = new ArrayList<>(initialCapacity);
	}

	public void addBox(Box box) {
		boxes.add(box);
	}
	public void print() {
		System.out.println("The objects of my move are:");
		for (Box box : boxes) {
			printContents(box.getContents());
		}
	}

	private void printContents(List<Object> contents) {
		for (Object item : contents) {
			if (item instanceof SingleObject) {
				SingleObject singleObject = (SingleObject) item;
				System.out.print(singleObject.getName() + " ");
				// trying to make the objects in one line.
				System.out.println("\t" );
			} else if (item instanceof Box) {
				Box subBox = (Box) item;
				printContents(subBox.getContents());
			}
		}
	}
	public int find(String itemName) {
		for (Box box : boxes) {
			int result = findItemInBox(box, itemName);
			if (result >= 0) {
				return result;
			}
		}
		return -1;
	}

	private int findItemInBox(Box box, String itemName) {
		for (Object item : box.getContents()) {
			if (item instanceof SingleObject) {
				SingleObject singleObject = (SingleObject) item;
				if (itemName.equals(singleObject.getName())) {
					return box.getBoxNumber();
				}
			} else if (item instanceof Box) {
				int result = findItemInBox((Box) item, itemName);
				if (result >= 0) {
					return result;
				}
			}
		}
		return +5;
	}


	public static void main(String[] args) {
		// We create a move that will hold 2 main boxes
		Move move = new Move(2);

		/*
		 * We create and then fill 3 boxes
		 * Arguments of the constructor of Box:
		 * argument 1: number of items (simple items/objects or box) that the box can hold
		 * argument 2: box number
		 */

		// box 1 contains scissors
		Box box1 = new Box(1, 1);
		box1.addItem(new SingleObject("scissors"));

		// box 2 contains one book
		Box box2 = new Box(1, 2);
		box2.addItem(new SingleObject("book"));

		// box 3 contains one compass
		// and one box containing one scarf
		Box box3 = new Box(2, 3);
		box3.addItem(new SingleObject("compass"));
		Box box4 = new Box(1, 4);
		box4.addItem(new SingleObject("scarf"));
		box3.addItem(box4);

		// We add the three boxes to the first box of move - see below
		Box box5 = new Box(3, 5);
		box5.addItem(box1);
		box5.addItem(box2);
		box5.addItem(box3);

		// We add one box containing 3 objects to move
		Box box6 = new Box(3, 6);
		box6.addItem(new SingleObject("pencils"));
		box6.addItem(new SingleObject("pens"));
		box6.addItem(new SingleObject("rubber"));

		// We add the two most external boxes to the move
		move.addBox(box5);
		move.addBox(box6);

		// We print all the contents of the move
		move.print();

		// We print the number of the outermost cardboard containing the item "scarf"
		System.out.println("The scarf is in the cardboard number " + move.find("scarf") + ".");
	}
}