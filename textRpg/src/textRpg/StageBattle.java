package textRpg;

import java.util.ArrayList;
import java.util.Random;

public class StageBattle extends Stage {
	private Random ran = new Random();

	private final int ATTACK = 1;
	private final int SKILL = 2;
	private final int ITEM = 3;

	private ArrayList<Unit> party;

	private ArrayList<Unit> monsters;

	public StageBattle(ArrayList<Unit> party, ArrayList<Unit> monsters) {
		super("BATTLE");
		this.party = party;
		this.monsters = monsters;
	}

	@Override
	public void printMenu() {
		Color.redPrintln("========[" + super.getStageName() + "]=======");
		Color.bluePrintln("========[PLAYER]=======");
		for (Unit hero : party)
			printHero(hero);
		Color.redPrintln("========[MONSTER]=======");
		for (Unit monster : monsters)
			Color.redPrintln(monster + "");
		takeDamage();
		result();
	}

	private boolean checkNext() {
		if (!checkMonstersAllDie() && !checkPlayersAllDie()) {
			return true;
		}
		return false;
	}

	private void printHero(Unit hero) {
		if (hero instanceof HeroWarrior)
			Color.cyanPrintln(hero + "");
		else if (hero instanceof HeroWizard)
			Color.purplePrintln(hero + "");
		else if (hero instanceof HeroPaladin)
			Color.bluePrintln(hero + "");
		else if (hero instanceof HeroPrist)
			Color.yellowPrintln(hero + "");
	}

	private boolean checkMonstersAllDie() {
		int numberOfMonster = this.monsters.size();
		int monsterCount = 0;

		for (Unit monster : monsters) {
			if (monster.isDead())
				monsterCount++;
		}

		if (monsterCount != numberOfMonster) {
			return false;
		}
		return true;
	}

	private boolean checkPlayersAllDie() {
		int numberOfHero = 3;
		int heroCount = 0;

		for (Unit hero : party) {
			if (hero.isDead())
				heroCount++;
		}

		if (heroCount != numberOfHero)
			return false;
		return true;
	}

	private void selectNextAction() {
		String heroesAction = "";
		for (int i = 0; i < party.size(); i++) {
			Unit target = party.get(i);
			if (checkMonstersAllDie())
				break;

			Hero hero = (Hero) target;
			String info = String.format("[%s]", hero.getGrade() == 1 ? "★" : hero.getGrade() == 2 ? "★★" : "★★★");
			info += String.format("[LV : %d]", hero.getLv());
			info += String.format("[%s] [1] 어택 [2] 스킬 [3] 아이템", hero.getName());
			Color.greenPrintln(info);

			String curHeroAction = runMenu(hero);
			if (curHeroAction == null) {
				i--;
				continue;
			}
			heroesAction += curHeroAction;
		}
	}

	private String runMenu(Hero hero) {
		int choice = GameManager.inputNumber("Menu");
		if (choice == ATTACK)
			return attack(hero);
		else if (choice == SKILL)
			return skill(hero);
		else if (choice == ITEM)
			return useItem(hero);
		return null;
	}

	private String attack(Hero hero) {
		Unit monster = choiceRandomUnit(monsters);

		int attack = hero.getOffensivePower();

		if (hero instanceof HeroWarrior && ((HeroWarrior) hero).isSkilled()) {
			attack *= 3;
			((HeroWarrior) hero).setIsSkilled();
		}

		hero.attack(monster, attack);
		String info = String.format("[%s]가 [%s]를 %d만큼 공격했습니다.!", hero.getName(), monster.getName(), attack);
		return info;
	}

	private String skill(Hero hero) {
		Unit monster = choiceRandomUnit(monsters);
		String info = "";
		if (hero instanceof HeroWizard) {
			int attack = hero.skill(monster);
			hero.attack(monster, attack);
			info += "[파이어볼]!\n";
			info += String.format("[%s]가 [%s]를 %d만큼 공격했습니다!", hero.getName(), monster.getName(), attack);
		} else if (hero instanceof HeroPaladin) {
			int heal = hero.skill(hero);
			info+=String.format("[%s]가 %d만큼 회복했습니다.", hero.getName(),heal);
		}else if(hero instanceof HeroWarrior) {
			int setSkilled = hero.skill(hero);
			if(setSkilled==0) {
				Color.redPrintln("이미 강화된 상태입니다.");
				return null;
			}
			info+=String.format("[%s]가 공격을 강화했습니다. 다음 턴엔 공격력의 3배로 공격합니다.", hero.getName());
		}else if(hero instanceof HeroPrist) {
			Hero target = (Hero)choiceRandomUnit(party);
			int heal = hero.skill(target);
			info+=String.format("[%s]가 [%s]를 %d만큼 회복시켰습니다!", hero.getName(),target.getName(),heal);
		}

		return info;
	}

	private Unit choiceRandomUnit(ArrayList<Unit> group) {
		Unit target = null;
		while (true) {
			int randomUnit = ran.nextInt(monsters.size());
			target = group.get(randomUnit);

			if (!target.isDead())
				break;
		}
		return target;
	}

	public void run() {
		while (checkNext()) {
			printMenu();
			selectNextAction();
		}
	}

}
