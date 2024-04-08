package textRpg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map;

public class FileManager {
	private static FileManager instance = new FileManager();

	private File file;

	private FileReader fr;
	private BufferedReader br;
	private FileWriter fw;

	private final String FILE_NAME = "textRpg.txt";

	private FileManager() {
		file = new File(FILE_NAME);
	}

	public static FileManager getInstance() {
		return instance;
	}

	public void save(String info) {
		try {
			fw = new FileWriter(file);
			fw.write(info);
			fw.close();
			Color.whitePrintln("저장완료");
		} catch (Exception e) {
			Color.redPrintln("저장실패");
		}
	}
	
	public String load() {
		String info = "";
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			while(br.ready()) {
				info+=br.readLine()+"\n";
			}
			br.close();
			fr.close();
			Color.whitePrintln("로드완료");
		} catch (Exception e) {
			Color.redPrintln("로드실패");
		}
		if(info.length()>0)
			info=info.substring(0,info.length()-1);
		return info;	
	}
}
