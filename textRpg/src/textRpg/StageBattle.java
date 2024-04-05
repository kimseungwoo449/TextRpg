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

	private UserManager userManager = UserManager.getInstance();

	public StageBattle(ArrayList<Unit> party, ArrayList<Unit> monsters) {
		super("BATTLE");
		this.party = party;
		this.monsters = monsters;
	}

	@Override
	public void printMenu() {
		Color.greenPrintln("========[" + super.getStageName() + "]=======");
		Color.bluePrintln("========[PLAYER]=======");
		for (Unit hero : party)
			printHero(hero);
		Color.redPrintln("========[MONSTER]=======");
		for (Unit monster : monsters)
			Color.redPrintln(monster + "");
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

	private String selectNextAction() {
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

			String curHeroAction = actionOfHero(hero);
			if (curHeroAction == null) {
				i--;
				continue;
			}
			heroesAction += curHeroAction + "\n";
		}
		return heroesAction;
	}

	private String actionOfHero(Hero hero) {
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
		String info = String.format("[%s]가 [%s]를 %d만큼 공격했습니다!", hero.getName(), monster.getName(), attack);
		if (monster.isDead()) {
			info += String.format("\n[%s]가 죽었습니다!", monster.getName());
		}
		return info;
	}

	private String skill(Hero hero) {
		Unit monster = choiceRandomUnit(monsters);
		String info = "";
		if (hero instanceof HeroWizard) {
			int attack = hero.skill(monster);
			hero.attack(monster, attack);
			info += String.format("[파이어볼]!! [%s]가 [%s]를 %d만큼 공격했습니다!", hero.getName(), monster.getName(), attack);
			
			if (monster.isDead()) {
				info += String.format("\n[%s]가 죽었습니다!", monster.getName());
			}
			
		} else if (hero instanceof HeroPaladin) {
			int heal = hero.skill(hero);
			info += String.format("[%s]가 %d만큼 회복했습니다.", hero.getName(), heal);
		} else if (hero instanceof HeroWarrior) {
			int setSkilled = hero.skill(hero);
			if (setSkilled == 0) {
				Color.redPrintln("이미 강화된 상태입니다.");
				return null;
			}
			info += String.format("[%s]가 공격을 강화했습니다. 다음 턴엔 공격력의 3배로 공격합니다.", hero.getName());
		} else if (hero instanceof HeroPrist) {
			Hero target = (Hero) choiceRandomUnit(party);
			int heal = hero.skill(target);
			info += String.format("[%s]가 [%s]를 %d만큼 회복시켰습니다!", hero.getName(), target.getName(), heal);
		}

		return info;
	}

	private Unit choiceRandomUnit(ArrayList<Unit> group) {
		Unit target = null;
		while (true) {
			int randomUnit = ran.nextInt(group.size());
			target = group.get(randomUnit);

			if (!target.isDead())
				break;
		}
		return target;
	}

	private String useItem(Hero hero) {
		userManager.showMyInventory();
		int itemIndex = GameManager.inputNumber("사용할 아이템 번호") - 1;

		Item item = userManager.getItem(itemIndex);
		String info = null;
		if (item instanceof ItemBomb) {
			ItemBomb bomb = (ItemBomb) item;
			Unit monster = choiceRandomUnit(monsters);
			bomb.fucntion(monster);
			info = String.format("[%s]가 [%s]를 사용해 [%s]를 %d의 데미지로 공격합니다.", hero.getName(), bomb.getName(),
					monster.getName(), bomb.getDamage());
			
			if (monster.isDead()) {
				info += String.format("\n[%s]가 죽었습니다!", monster.getName());
			}
		} else if (item instanceof ItemPotion) {
			ItemPotion potion = (ItemPotion) item;
			potion.fucntion(hero);
			info = String.format("[%s]가 [%s]를 사용해 체력 %d를 회복합니다.", hero.getName(), potion.getName(),
					potion.getRecoveryAmount());
		}

		return info;
	}

	private String attackOfMonsters() {
		String monstersAction = "";
		for (Unit monster : monsters) {
			int randomHero = ran.nextInt(party.size());
			Unit hero = party.get(randomHero);
			if (!monster.isDead() && !hero.isDead()) {
				int randomSkill = ran.nextInt(4);
				int attack = 0;

				if (randomSkill == 0) {
					attack = monster.skill(hero);
				} else {
					attack = monster.getOffensivePower();
				}

				monstersAction += makeText(monster, hero, attack, randomSkill) + "\n";
				if (monster instanceof MonsterSlime && randomSkill == 0) {
					continue;
				}
				hero.takeDamage(attack);
				if (hero.isDead()) {
					monstersAction += String.format("[%s]에 의해 [%s]가 사망했습니다....\n", monster.getName(), hero.getName());
				}
			}
		}
		return monstersAction;
	}

	private String makeText(Unit monster, Unit hero, int value, int skilled) {
		String info = null;
		if (skilled == 0) {
			if (monster instanceof MonsterSlime) {
				info = String.format("[자가재생][%s]가 %s만큼 자가회복을 했습니다.", monster.getName(),value);
			} else if (monster instanceof MonsterBat) {
				info = String.format("[흡혈][%s]가 [%s]에게 %d만큼의 피해를 준 뒤, %d만큼 회복합니다.", monster.getName(), hero.getName(),
						value, value * 2);
			} else if (monster instanceof MonsterOrc) {
				info = String.format("[강타][%s]가 [%s]에게 %d만큼의 피해를 주었습니다.", monster.getName(), hero.getName(), value);
			} else if (monster instanceof MonsterWolf) {
				info = String.format("[물어뜯기][%s]가 [%s]에게 %d만큼의 피해를 주었습니다.", monster.getName(), hero.getName(), value);
			}
		} else {
			info = String.format("[%s]가 [%s]에게 %d만큼의 피해를 주었습니다.", monster.getName(), hero.getName(), value);
		}
		return info;
	}

	private void printTurnStatus(String heroesAction, String monstersAction) {
		Color.greenPrintln("----- Turn Status -----");
		Color.whitePrint(heroesAction);
		Color.redPrint(monstersAction);
	}

	public void run() {
		while (checkNext()) {
			printMenu();
			String heroesAction = selectNextAction();
			String monstersAction = attackOfMonsters();
			printTurnStatus(heroesAction, monstersAction);
		}
	}

}
