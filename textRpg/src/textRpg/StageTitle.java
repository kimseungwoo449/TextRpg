package textRpg;

public class StageTitle extends Stage {
	private final int JOIN = 1;
	private final int LEAVE = 2;
	private final int REFINE_PASSWORD = 3;
	private final int LOG_IN = 4;
	private final int LOG_OUT = 5;
	private final int LOBBY = 6;
	private final int EXIT = 0;

	private static StageTitle instance = new StageTitle();
	private UserManager userManager = UserManager.getInstance();
	private StageLobby stageLobby = StageLobby.getInstance();
	
	private StageTitle() {
		super("TEXT RPG");
	}

	public static StageTitle getInstance() {
		return instance;
	}

	@Override
	public void printMenu() {
		Color.greenPrintln("==== " + this.getStageName() + " ====");
		Color.greenPrintln("[1] 회원가입");
		Color.greenPrintln("[2] 회원탈퇴");
		Color.greenPrintln("[3] 회원정보수정");
		Color.greenPrintln("[4] 로그	인");
		Color.greenPrintln("[5] 로그아웃");
		Color.greenPrintln("[6] 로	비");
		Color.greenPrintln("[0] 종	료");
	}

	private boolean isPossible(int select) {
		if (UserManager.log == -1
				&& (select == LEAVE || select == LOBBY || select == LOG_OUT || select == REFINE_PASSWORD)) {
			Color.redPrintln("로그 인 후 이용가능합니다.");
			return false;
		}
		if (UserManager.log != -1 && (select == LOG_IN || select == JOIN)) {
			Color.redPrintln("로그 아웃 후 이용가능합니다.");
			return false;
		}

		return true;
	}

	private void runMenu(int select) {
		if (!isPossible(select))
			return;

		if (select == JOIN)
			join();
		else if (select == LEAVE)
			leave();
		else if (select == REFINE_PASSWORD)
			refinePassword();
		else if (select == LOG_IN)
			login();
		else if (select == LOG_OUT)
			logout();
		else if (select == LOBBY)
			lobby();
		else if (select == EXIT)
			GameManager.isRun = false;
	}

	private void join() {
		userManager.join();
	}

	private void leave() {
		userManager.leave();
	}

	private void refinePassword() {
		userManager.refinePassword();
	}
	
	private void login() {
		userManager.login();
	}

	private void logout() {
		userManager.logout();
	}

	private void lobby() {
		stageLobby.run();
	}

	public void run() {
		printMenu();
		int select = GameManager.inputNumber("Menu");
		runMenu(select);
	}
}
