package textRpg;

import java.util.ArrayList;

public class StageLobby extends Stage {
	private final int BATTLE = 1;
	private final int BUY_HERO = 2;
	private final int SELL_HERO = 3;
	private final int ORGANIZE_PARTY = 4;
	private final int DISASSEMBLE_PARTY = 5;
	private final int STORE = 6;
	private final int VIEW_MY_HEROES = 7;
	private final int VIEW_MY_PARTIES = 8;
	private final int VIEW_MY_INVENTORY = 9;
	private final int TITLE = 0;

	private boolean isRun;

	private static UserManager userManager = UserManager.getInstance();
	private static StageLobby instance = new StageLobby();

	private StageLobby() {
		super("로비");
	}

	public static StageLobby getInstance() {
		return instance;
	}

	@Override
	public void printMenu() {
		Color.greenPrintln("=== " + getStageName() + " ===");
		Color.greenPrintln("[1] 전투");
		Color.greenPrintln("[2] 영웅 뽑기 [500원]");
		Color.greenPrintln("[3] 영웅 판매");
		Color.greenPrintln("[4] 파티 생성");
		Color.greenPrintln("[5] 파티 해체");
		Color.greenPrintln("[6] 상점");
		Color.greenPrintln("[7] 영웅 보기");
		Color.greenPrintln("[8] 파티 보기");
		Color.greenPrintln("[9] 인벤토리");
		Color.greenPrintln("[0] 타이틀");
	}

	private void runMenu(int select) {
		if (select == BATTLE)
			battle();
		else if (select == BUY_HERO)
			buyHero();
		else if (select == SELL_HERO)
			sellHero();
		else if (select == ORGANIZE_PARTY)
			organizeParty();
		else if (select == DISASSEMBLE_PARTY)
			disassembleParty();
		else if (select == STORE)
			store();
		else if (select == VIEW_MY_HEROES)
			viewAllHeroes();
		else if (select == VIEW_MY_PARTIES)
			viewAllParties();
		else if (select == VIEW_MY_INVENTORY)
			viewInventory();
		else if (select == TITLE)
			this.isRun = false;
	}

	private void battle() {
		userManager.battle();
	}

	private void buyHero() {
		userManager.buyHero();
	}

	private void sellHero() {
		userManager.sellHero();
	}

	private void organizeParty() {
		userManager.organizeParty();
	}

	private void disassembleParty() {
		userManager.disassembleParty();
	}

	private void store() {
		userManager.buyItem();
	}
	
	private void viewAllHeroes() {
		userManager.showMyHeroes();
	}
	
	private void viewAllParties() {
		userManager.showMyParties();
	}
	
	public void viewInventory() {
		userManager.showMyInventory();
	}
	
	public void run() {
		this.isRun = true;
		while(isRun) {
			printMenu();
			int select = GameManager.inputNumber("Menu");
			runMenu(select);
		}
	}

}
