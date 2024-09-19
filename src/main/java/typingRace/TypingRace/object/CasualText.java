package typingRace.TypingRace.object;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.charset.Charset;

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
		int numRow=lineNumberReader.getLineNumber();
		lineNumberReader.close();
		file.close();
		return numRow;
	}
	
	private void openFile() {
		try {
			file= new FileReader(wordsSource, Charset.forName("UTF-8"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String generateText() {
		text="";
		int fileLength=-1;
		try {
			fileLength = calcLength(wordsSource);
			System.out.println(fileLength);
			BufferedReader lettoreDiRighe=null;
			for(int i=0; i<length; i++) {
				openFile();
				lettoreDiRighe = new BufferedReader(file);
				int nRiga=(int)(Math.random()*fileLength)+1;
				System.out.println(nRiga);
				String word="!!ERRORE!!";
				while(nRiga-->0) {
					word = lettoreDiRighe.readLine();
				}
				System.out.println(word);
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
