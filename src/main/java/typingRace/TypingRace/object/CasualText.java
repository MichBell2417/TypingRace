package typingRace.TypingRace.object;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * This class take casual word from a text file and create a meaningless text
 */
public class CasualText {
	
	private int length;
	private FileReader file;
	
	private String text="********";
	private String wordsSource;
	
	public CasualText(int length) {
		this.length=length;
	}
	public CasualText(int length, String positionFile) {
		this.length=length;
		wordsSource=positionFile;
		openFile();
	}
	
	//senza modificatore di visibilità il metodo è utilizzabile solo nella stessa classe e nel pacchetto
	static int calcLength(String path) throws IOException {
		FileReader file= new FileReader(path);
		LineNumberReader lineNumberReader = new LineNumberReader(file);
		lineNumberReader.skip(Long.MAX_VALUE);
		int numRow=lineNumberReader.getLineNumber()+1;
		lineNumberReader.close();
		file.close();
		return numRow;
	}
	
	private void openFile() {
		try {
			file= new FileReader(wordsSource);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String generateText() {
		text="";
		int fileLength=-1;
		try {
			fileLength= calcLength(wordsSource);
//			System.out.println(fileLength);
			BufferedReader lettoreDiRighe=null;
			for(int i=0; i<length; i++) {
				openFile();
				lettoreDiRighe = new BufferedReader(file);
				int nRiga=(int)(Math.random()*fileLength);
//				System.out.println(nRiga);
				String word;
				do {
					word = lettoreDiRighe.readLine();
				}while(nRiga-->0);
//				System.out.println(word);
				text+=word+" ";
			}
			lettoreDiRighe.close();
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}
}
