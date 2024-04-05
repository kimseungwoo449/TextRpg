package textRpg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
	private String id;
	private String password;

	private int cash;
	private int partyNumber;

	private Map<Integer, ArrayList<Unit>> parties;
	private ArrayList<Hero> myHero;
	private ArrayList<Item> inventory;

	public User(String id, String password) {
		this.id = id;
		this.password = password;
		this.parties = new HashMap<Integer, ArrayList<Unit>>();
		this.myHero = initialHeros();
		this.inventory = new ArrayList<Item>();
		this.cash += 10000;
		this.partyNumber = 1;
	}

	public String getId() {
		return this.id;
	}

	public int getCash() {
		return this.cash;
	}

	public int getMyHeroSize() {
		return this.myHero.size();
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private ArrayList<Hero> initialHeros() {
		ArrayList<Hero> temp = new ArrayList<Hero>();
		String[] className = { "HeroWarrior", "HeroWizard", "HeroPaladin", "HeroPrist" };
		Class<?>[] pram = new Class<?>[] { int.class };

		for (int i = 0; i < className.length; i++) {
			String path = "textRpg." + className[i];
			try {
				Class<?> clazz = Class.forName(path);
				Object obj = clazz.getDeclaredConstructor(pram).newInstance(1);
				Hero hero = (Hero) obj;
				temp.add(hero);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return temp;
	}

	public boolean buyHero(Hero hero) {
		if (cash < 500)
			return false;
		this.myHero.add(hero);
		cash -= 500;
		return true;
	}

	public boolean sellHero(int index) {
		if (index < 0 || index >= myHero.size())
			return false;

		Hero targetHero = this.myHero.get(index);
		int grade = targetHero.getGrade();

		cash += 300 * grade;
		this.myHero.remove(index);
		deleteHeroInParty(targetHero);
		return true;
	}

	private void deleteHeroInParty(Hero targetHero) {
		for (int i = 1; i < partyNumber; i++) {
			ArrayList<Unit> party = parties.get(i);

			for (int j = 0; j < party.size(); j++) {
				Unit heroInParty = party.get(j);
				if (heroInParty.equals(targetHero)) {
					party.remove(targetHero);
					break;
				}
			}
		}
	}

	public void showMyHero() {
		for (int i = 0; i < myHero.size(); i++) {
			Hero hero = myHero.get(i);

			if (hero instanceof HeroWarrior)
				Color.cyanPrintln((i + 1) + " : " + hero);
			else if (hero instanceof HeroWizard)
				Color.purplePrintln((i + 1) + " : " + hero);
			else if (hero instanceof HeroPaladin)
				Color.bluePrintln((i + 1) + " : " + hero);
			else if (hero instanceof HeroPrist)
				Color.yellowPrintln((i + 1) + " : " + hero);
		}
	}

	public void addParty(int[] indexArray) {
		ArrayList<Unit> party = new ArrayList<Unit>();

		for (int i = 0; i < indexArray.length; i++) {
			int heroIndex = indexArray[i];
			Hero targetHero = myHero.get(heroIndex);
			party.add(targetHero);
		}

		this.parties.put(partyNumber++, party);
	}

	public boolean deleteParty(int index) {
		if (index < 1 || index > parties.size())
			return false;
		for (int i = 1; i <= parties.size(); i++) {
			if (i > index) {
				parties.put(i - 1, parties.get(i));
			}
			if (i == parties.size())
				parties.remove(i);
		}
		return true;
	}

	public void showParties() {
		List keySet = new ArrayList(parties.keySet());
		Collections.sort(keySet);
		viewParty(keySet);
	}

	private void viewParty(List keySet) {
		for (int i = 0; i < parties.size(); i++) {
			int key = (int) keySet.get(i);
			ArrayList<Unit> party = parties.get(key);

			Color.greenPrintln("------------------ " + key + " ------------------");
			for (int j = 0; j < party.size(); j++) {
				Unit hero = party.get(j);

				if (hero instanceof HeroWarrior)
					Color.cyanPrintln(hero + "");
				else if (hero instanceof HeroWizard)
					Color.purplePrintln(hero + "");
				else if (hero instanceof HeroPaladin)
					Color.bluePrintln(hero + "");
				else if (hero instanceof HeroPrist)
					Color.yellowPrintln(hero + "");

			}
		}
	}

	public boolean buyItem(Item item) {
		int pay = item.getPrice();
		if (pay > cash)
			return false;

		cash -= pay;
		this.inventory.add(item);
		return true;
	}

	public void showInventory() {
		Color.greenPrintln("--------- 인벤토리 ----------");
		for (int i = 0; i < inventory.size(); i++) {
			Item item = inventory.get(i);
			Color.cyanPrintln((i + 1) + ". " +item.toString());
		}
	}

	public Item getItem(int itemIndex) {
		if(itemIndex<0||itemIndex>=inventory.size())
			return null;
		Item item = inventory.get(itemIndex);
		inventory.remove(itemIndex);
		return item;
	}
	
	public ArrayList<Unit> getParty(int index){
		if(index<1||index>=partyNumber)
			return null;
		
		return parties.get(index);
	}
	
}
