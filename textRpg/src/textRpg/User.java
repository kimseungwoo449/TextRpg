package textRpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
	private String id;
	private String password;

	private int cash;
	private int partyNumber;
	
	private Map<Integer, ArrayList<Hero>> party;
	private ArrayList<Hero> myHero;
	private ArrayList<Item> inventory;

	private ColorPrint color = ColorPrint.getInstance();

	public User(String id, String password) {
		this.id = id;
		this.password = password;
		this.party = new HashMap<Integer, ArrayList<Hero>>();
		this.myHero = initialHeros();
		this.inventory = new ArrayList<Item>();
		this.cash += 1000;
		this.partyNumber = 1;
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

	public void addHero(Hero hero) {
		this.myHero.add(hero);
	}

	public boolean deleteHero(int index) {
		if (index < 0 || index >= myHero.size())
			return false;

		this.myHero.remove(index);
		return true;
	}

	public void showMyHero() {
		for (int i = 0; i < myHero.size(); i++) {
			Hero hero = myHero.get(i);

			if (hero instanceof HeroWarrior)
				color.redPrint((i + 1) + " : " + hero);
			else if (hero instanceof HeroWizard)
				color.purplePrint((i + 1) + " : " + hero);
			else if (hero instanceof HeroPaladin)
				color.bluePrint((i + 1) + " : " + hero);
			else if (hero instanceof HeroPrist)
				color.yellowPrint((i + 1) + " : " + hero);
		}
	}
	
	public void addParty(ArrayList<Hero> party) {
		this.party.put(partyNumber++, party);
	}

}
