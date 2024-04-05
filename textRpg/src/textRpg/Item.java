package textRpg;

interface Equipable {
	public void setNameAndPrice(int grade);
}

interface Consumable {

}

abstract public class Item {
	private int price;
	private boolean isEquiped;
	private String name;
	private Hero equipedHero;

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

	public boolean isEquiped() {
		return this.isEquiped;
	}

	public void setIsEquiped() {
		this.isEquiped = this.isEquiped == true ? false : true;
	}

	public void setEquipedHero(Hero hero) {
		this.equipedHero = this.equipedHero == null ? hero : null;
	}
	
	public Hero getEquipedHero() {
		return this.equipedHero;
	}
	
	@Override
	public String toString() {
		return "[" + this.name + "]";
	}
}
