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

	private Map<Integer, ArrayList<Hero>> parties;
	private ArrayList<Hero> myHero;
	private ArrayList<Item> inventory;

	private ColorPrint color = ColorPrint.getInstance();

	public User(String id, String password) {
		this.id = id;
		this.password = password;
		this.parties = new HashMap<Integer, ArrayList<Hero>>();
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
		
		cash+=300*grade;
		this.myHero.remove(index);
		return true;
	}

	public void showMyHero() {
		for (int i = 0; i < myHero.size(); i++) {
			Hero hero = myHero.get(i);

			if (hero instanceof HeroWarrior)
				color.cyanPrintln((i + 1) + " : " + hero);
			else if (hero instanceof HeroWizard)
				color.purplePrintln((i + 1) + " : " + hero);
			else if (hero instanceof HeroPaladin)
				color.bluePrintln((i + 1) + " : " + hero);
			else if (hero instanceof HeroPrist)
				color.yellowPrintln((i + 1) + " : " + hero);
		}
	}

	public void addParty(int[] indexArray) {
		ArrayList<Hero> party = new ArrayList<Hero>();
		
		for(int i =0;i<indexArray.length;i++) {
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
			ArrayList<Hero> party = parties.get(key);

			color.greenPrintln("------------------ " + key + " ------------------");
			for (int j = 0; i < party.size(); i++) {
				Hero hero = party.get(i);

				if (hero instanceof HeroWarrior)
					color.cyanPrintln(hero + "");
				else if (hero instanceof HeroWizard)
					color.purplePrintln(hero + "");
				else if (hero instanceof HeroPaladin)
					color.bluePrintln(hero + "");
				else if (hero instanceof HeroPrist)
					color.yellowPrintln(hero + "");

			}
		}
	}

	public boolean addItem(int pay, Item item) {
		if (pay > cash)
			return false;

		cash -= pay;
		this.inventory.add(item);
		return true;
	}

	public void showInventory() {
		for (int i = 0; i < inventory.size(); i++) {
			Item item = inventory.get(i);
			color.cyanPrintln((i + 1) + ". " + item.getName());
		}
	}

	public void useItem(Unit target, int ItemIndex) {
		Item item = inventory.get(ItemIndex);
		item.fucntion(target);
		inventory.remove(ItemIndex);
	}
}
