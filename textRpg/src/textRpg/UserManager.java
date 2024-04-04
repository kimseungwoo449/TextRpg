package textRpg;

import java.util.ArrayList;

public class UserManager {
	private static UserManager instance = new UserManager();
	private ColorPrint color = ColorPrint.getInstance();
	
	private ArrayList<User> users;
	
	private UserManager() {
		users = new ArrayList<User>();
	}
	
	public static UserManager getInstance() {
		return instance;
	}
	
	public void join() {
		String id = GameManager.inputString("ID");
		String password = GameManager.inputString("PASSWORD");
		
		if(findUserIndexById(id)!=-1) {
			color.redPrintln("동일한 아이디가 존재합니다.");
			return;
		}
		
		User user = new User(id,password);
		users.add(user);
		String info = String.format("가입 완료. %s님 TEXT RPG에 오신걸 환영합니다.", id);
		color.greenPrintln(info);
	}
	
	private int findUserIndexById(String id) {
		int index = -1;
		for(int i =0;i<users.size();i++) {
			User user = users.get(i);
			if(user.getId().equals(id))
				index = i;
		}
		return index;
	}
	
	public void leave() {
		String id = GameManager.inputString("ID");
		String password = GameManager.inputString("PASSWORD");
		
		int userIndex = findUserIndexById(id);
		
		if(userIndex==-1||!users.get(userIndex).getPassword().equals(password)) {
			color.redPrintln("ID 혹은 PASSWORD를 재확인 해주세요.");
			return;
		}
		
		users.remove(userIndex);
		color.greenPrintln("탈퇴 완료.");
	}
	
	
}
