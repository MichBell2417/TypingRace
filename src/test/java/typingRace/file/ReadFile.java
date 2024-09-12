package typingRace.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

	public static void main(String[] args){
		FileReader flussoCaratteri;
		try {
			flussoCaratteri = new FileReader("C:\\Users\\belmi\\Desktop\\Archivio\\workspaces-JAVA\\TypingRace\\TypingRace\\src\\main\\resources\\typingRace\\TypingRace\\italian.txt");
			BufferedReader lettoreDiRighe = new BufferedReader(flussoCaratteri);
			String testo;
			do {
				testo = lettoreDiRighe.readLine();
				if (testo!=null) {
					System.out.println(testo);
				}
			}while(testo!=null);
			System.out.println("eseguito");
			lettoreDiRighe.close();
			flussoCaratteri.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
