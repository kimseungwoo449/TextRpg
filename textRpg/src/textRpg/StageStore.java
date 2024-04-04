package textRpg;

public class StageStore extends Stage{
	private static StageStore instance = new StageStore();
	
	private StageStore() {
		
	}
	
	public static StageStore getInstance() {
		return instance;
	}
	
	@Override
	public void printMenu() {
		Color.greenPrintln("---------- 상점 ----------");
		Color.greenPrintln("---------- 목록 ----------");
		Color.greenPrintln("[1] 폭탄 [700원]");
		Color.greenPrintln("[2] 포션 [200원]");
	}
	
	public Item buyItem() {
		Item item = null;
		
		printMenu();
		int itemNumber = GameManager.inputNumber("구매할 아이템");
		item = makeItem(itemNumber);
		return item;
	}
	
	private Item makeItem(int itemNumber) {
		Item item = null;
		if(itemNumber==1)
			item = new ItemBomb();
		else if(itemNumber==2)
			item = new ItemPotion();
		
		return item;
	}
	
}
