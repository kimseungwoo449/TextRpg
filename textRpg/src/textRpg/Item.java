package textRpg;

interface Equipable {
	public void setNameAndPrice(int grade);
	public void setEquipedHeroIndex(int heroIndex);
	public int getEquipedHeroIndex();
}

interface Consumable {

}

abstract public class Item {
	private int price;
	private String name;

	public Item(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public Item() {

	}

	abstract public void fucntion(Unit target);

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "[" + this.name + "]";
	}
}
