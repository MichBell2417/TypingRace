package typingRace.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

public class ReadFileRow {

	public void main(String[] args){
		FileReader flussoCaratteri;
		String link="C:\\Users\\belmi\\Desktop\\Archivio\\workspaces-JAVA\\TypingRace\\TypingRace\\src\\main\\resources\\typingRace\\TypingRace\\italian.txt";
		int fileLength=-1;
		try {
			flussoCaratteri = new FileReader(link);
			fileLength= calcLength(link);
			BufferedReader lettoreDiRighe = new BufferedReader(flussoCaratteri);
			System.out.println(fileLength);
			int nRiga=(int)(Math.random()*fileLength);
			System.out.println(nRiga);
			String testo;
			do {
				testo = lettoreDiRighe.readLine();
				System.out.println(nRiga);
			}while(nRiga-->0);
			System.out.println(testo);
			System.out.println("eseguito");
			lettoreDiRighe.close();
			flussoCaratteri.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int calcLength(String path) throws IOException {
		FileReader file= new FileReader(path);
		LineNumberReader lineNumberReader = new LineNumberReader(file);
		lineNumberReader.skip(Long.MAX_VALUE);
		int numRow=lineNumberReader.getLineNumber()+1;
		lineNumberReader.close();
		file.close();
		return numRow;
	}
}
