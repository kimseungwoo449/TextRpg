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

		int attack = calculateHeroAttack(hero);

		hero.attack(monster, attack);
		String info = String.format("[%s]가 [%s]를 %d만큼 공격했습니다!", hero.getName(), monster.getName(), attack);

		info += whenMonsterDeadText(monster);
		return info;
	}

	private int calculateHeroAttack(Hero hero) {
		int attack = hero.getOffensivePower();
		attack += hero.getExtraPower();
		attack = checkWarriorSkill(hero, attack);
		return attack;
	}

	private int checkWarriorSkill(Hero hero, int attack) {
		if (hero instanceof HeroWarrior && ((HeroWarrior) hero).isSkilled()) {
			attack *= 3;
			((HeroWarrior) hero).setIsSkilled();
		}
		return attack;
	}

	private String whenMonsterDeadText(Unit monster) {
		String info = "";
		if (monster.isDead()) {
			info += String.format("\n[%s]가 죽었습니다!", monster.getName());
		}
		return info;
	}

	private String skill(Hero hero) {
		Unit monster = choiceRandomUnit(monsters);
		String info = null;
		if (hero instanceof HeroWizard)
			info = skillOfWizard(hero, monster);
		else if (hero instanceof HeroPaladin)
			info = skillOfPaladin(hero);
		else if (hero instanceof HeroWarrior)
			info = skillOfWarrior(hero);
		else if (hero instanceof HeroPrist)
			info = skillOfPrist(hero);

		return info;
	}

	private String skillOfWizard(Hero hero, Unit monster) {
		String info = "";
		int attack = hero.skill(monster);
		hero.attack(monster, attack);
		info += String.format("[파이어볼]!! [%s]가 [%s]를 %d만큼 공격했습니다!", hero.getName(), monster.getName(), attack);

		if (monster.isDead()) {
			info += String.format("\n[%s]가 죽었습니다!", monster.getName());
		}
		return info;
	}

	private String skillOfWarrior(Hero hero) {
		int setSkilled = hero.skill(hero);
		if (setSkilled == 0) {
			Color.redPrintln("이미 강화된 상태입니다.");
			return null;
		}
		return String.format("[%s]가 공격을 강화했습니다. 다음 턴엔 공격력의 3배로 공격합니다.", hero.getName());
	}

	private String skillOfPrist(Hero hero) {
		String info = "";
		Hero target = (Hero) choiceRandomUnit(party);
		int heal = hero.skill(target);
		info += String.format("[%s]가 [%s]를 %d만큼 회복시켰습니다!", hero.getName(), target.getName(), heal);
		return info;
	}

	private String skillOfPaladin(Hero hero) {
		String info = "";
		int heal = hero.skill(hero);
		info += String.format("[%s]가 %d만큼 회복했습니다.", hero.getName(), heal);
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
		String info = null;
		if(userManager.getConsumableItemSize()==0) {
			Color.redPrintln("소모 아이템이 존재하지 않습니다.");
			return info;
		}
			
		
		userManager.showConsumableInventory();
		int itemIndex = GameManager.inputNumber("사용할 아이템 번호") - 1;

		Item item = userManager.getItem(itemIndex);

		if (item instanceof ItemBomb)
			info = useItemOfBomb(item, hero);
		else if (item instanceof ItemPotion)
			info = userItemPotion(item, hero);
		return info;
	}

	private String useItemOfBomb(Item item, Hero hero) {

		ItemBomb bomb = (ItemBomb) item;
		Unit monster = choiceRandomUnit(monsters);
		bomb.fucntion(monster);
		String info = String.format("[%s]가 [%s]를 사용해 [%s]를 %d의 데미지로 공격합니다.", hero.getName(), bomb.getName(),
				monster.getName(), bomb.getDamage());

		if (monster.isDead()) {
			info += String.format("\n[%s]가 죽었습니다!", monster.getName());
		}
		return info;
	}

	private String userItemPotion(Item item, Hero hero) {
		ItemPotion potion = (ItemPotion) item;
		potion.fucntion(hero);
		String info = String.format("[%s]가 [%s]를 사용해 체력 %d를 회복합니다.", hero.getName(), potion.getName(),
				potion.getRecoveryAmount());
		return info;
	}

	private String attackOfMonsters() {
		String monstersAction = "";
		for (Unit monster : monsters) {
			int randomHero = ran.nextInt(party.size());
			Hero hero = (Hero) party.get(randomHero);

			if (!monster.isDead() && !hero.isDead())
				monstersAction += makeMonsterAttackStatus(monster, hero);
		}
		return monstersAction;
	}

	private String makeMonsterAttackStatus(Unit monster, Hero hero) {
		String monsterAction = "";
		int randomSkill = ran.nextInt(4);
		int attack = calculateMonsterAttack(monster, hero, randomSkill);

		if (monster instanceof MonsterSlime && randomSkill == 0) {
			monsterAction += makeText(monster, hero, attack, randomSkill) + "\n";
			return monsterAction;
		}

		attack = blockDamageCheck(hero, attack);
		monsterAction += makeText(monster, hero, attack, randomSkill) + "\n";

		hero.takeDamage(attack);

		monsterAction += checkHeroDead(monster, hero);
		return monsterAction;
	}

	private String makeText(Unit monster, Unit hero, int value, int skilled) {
		if (skilled == 0)
			return textOfMonsterSkill(monster, hero, value);
		else
			return textOfMonsterAttack(monster, hero, value);
	}

	private String checkHeroDead(Unit monster, Hero hero) {
		String monsterAction = "";
		if (hero.isDead()) {
			monsterAction += String.format("[%s]에 의해 [%s]가 사망했습니다....\n", monster.getName(), hero.getName());
		}
		return monsterAction;
	}

	private int blockDamageCheck(Hero hero, int attack) {
		double armorOfHero = (double) hero.getArmor();
		if (armorOfHero != 0) {
			double ratio = armorOfHero / 100;
			double blockDamage = attack * ratio;
			attack -= blockDamage;
		}
		return attack;
	}

	private int calculateMonsterAttack(Unit monster, Hero hero, int randomSkill) {
		int attack = 0;
		if (randomSkill == 0) {
			attack = monster.skill(hero);
		} else {
			attack = monster.getOffensivePower();
		}

		return attack;
	}

	private String textOfMonsterSkill(Unit monster, Unit hero, int value) {
		String info = null;
		if (monster instanceof MonsterSlime) {
			info = String.format("[자가재생][%s]가 %s만큼 자가회복을 했습니다.", monster.getName(), value);
		} else if (monster instanceof MonsterBat) {
			info = String.format("[흡혈][%s]가 [%s]에게 %d만큼의 피해를 준 뒤, %d만큼 회복합니다.", monster.getName(), hero.getName(),
					value, value * 2);
		} else if (monster instanceof MonsterOrc) {
			info = String.format("[강타][%s]가 [%s]에게 %d만큼의 피해를 주었습니다.", monster.getName(), hero.getName(), value);
		} else if (monster instanceof MonsterWolf) {
			info = String.format("[물어뜯기][%s]가 [%s]에게 %d만큼의 피해를 주었습니다.", monster.getName(), hero.getName(), value);
		}
		return info;
	}

	private String textOfMonsterAttack(Unit monster, Unit hero, int value) {
		return String.format("[%s]가 [%s]에게 %d만큼의 피해를 주었습니다.", monster.getName(), hero.getName(), value);
	}

	private void printTurnStatus(String heroesAction, String monstersAction) {
		Color.greenPrintln("----- Turn Status -----");
		Color.whitePrint(heroesAction);
		Color.redPrint(monstersAction);
	}

	private void result() {
		if (checkPlayersAllDie()) {
			Color.redPrintln("전투에 실패하였습니다...");
			return;
		}

		for (Unit monster : monsters) {
			int exp = monster.getExp();
			for (Unit hero : party) {
				Hero target = (Hero) hero;
				target.setExp(exp);
				String info = target.checkLevelUp();

				if (info != null)
					colorPrintByHeroValue(target, info);
			}
		}
		setWarriorSkill();
	}

	private void setWarriorSkill() {
		for (Unit hero : party) {
			if (hero instanceof HeroWarrior) {
				HeroWarrior temp = (HeroWarrior) hero;
				if (temp.isSkilled())
					temp.setIsSkilled();
			}
		}
	}

	private void colorPrintByHeroValue(Hero hero, String info) {
		if (hero instanceof HeroWarrior)
			Color.cyanPrintln(info);
		else if (hero instanceof HeroWizard)
			Color.purplePrintln(info);
		else if (hero instanceof HeroPaladin)
			Color.bluePrintln(info);
		else if (hero instanceof HeroPrist)
			Color.yellowPrintln(info);
	}

	public void run() {
		while (checkNext()) {
			printMenu();
			String heroesAction = selectNextAction();
			String monstersAction = attackOfMonsters();
			printTurnStatus(heroesAction, monstersAction);
		}
		result();
	}

}
