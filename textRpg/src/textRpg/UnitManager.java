package textRpg;

import java.util.ArrayList;
import java.util.Random;


public class UnitManager {
	private static UnitManager instance = new UnitManager();
	private Random ran = new Random();

	private String heroClassName[];
	private String monsterClassName[];

	private final String PATH = "textRpg.";

	private UnitManager() {
		this.heroClassName = new String[] { "HeroWarrior", "HeroWizard", "HeroPaladin", "HeroPrist" };
		this.monsterClassName = new String[] { "MonsterOrc", "MonsterSlime", "MonsterBat", "MonsterWolf" };
	}

	public static UnitManager getInstance() {
		return instance;
	}

	public Hero buyHero() {
		Hero hero = null;
		int grade = randomGrade();
		String heroType = randomName(heroClassName);
		Class<?>[] params = new Class<?>[] { int.class };
		try {
			Class<?> clazz = Class.forName(PATH + heroType);
			Object obj = clazz.getDeclaredConstructor(params).newInstance(grade);
			hero = (Hero) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return hero;
	}

	private int randomGrade() {
		int grade = -1;
		int randomValue = ran.nextInt(100) + 1;

		if (randomValue >= 1 && randomValue <= 50)
			grade = 1;
		else if (randomValue >= 51 && randomValue <= 80)
			grade = 2;
		else if (randomValue >= 81 && randomValue <= 100)
			grade = 3;
		return grade;
	}

	private String randomName(String[] targetNameArray) {
		return targetNameArray[ran.nextInt(targetNameArray.length)];
	}
	
	public ArrayList<Unit> createMonsters() {
		ArrayList<Unit> monsters = new ArrayList<Unit>();
		Class<?> params[] = new Class<?>[] { int.class, int.class, int.class };
		int rNum = ran.nextInt(5)+1;
		for (int i = 0; i < rNum; i++) {
			String monsterType = randomName(monsterClassName);

			try {
				Class<?> clazz = Class.forName(PATH+monsterType);
				int hp = ran.nextInt(100) + 120;
				int power = ran.nextInt(10) + 15;
				int exp = ran.nextInt(15) + 10;
				Object obj = clazz.getDeclaredConstructor(params).newInstance(hp, power, exp);
				Monster temp = (Monster)obj;
				monsters.add(temp);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		return monsters;
	}
}
