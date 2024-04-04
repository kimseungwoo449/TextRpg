package textRpg;

abstract public class Item {
	private int price;
	private String name;
	
	public Item(String name,int price) {
		this.name = name;
		this.price = price;
	}
	
	abstract public void fucntion(Unit target);
	
	public String getName() {
		return this.name;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	@Override
	public String toString() {
		return "["+this.name+"]";
	}
}
