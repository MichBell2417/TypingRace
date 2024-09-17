package typingRace.TypingRace.object;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class ClassificationFile {
	
	private String filePath;
	
	public ClassificationFile(String filePath) {
		this.filePath=filePath;
	}
	
	public void saveData(int wpm) {
		String[] dati=getData();
		try (
			FileWriter file= new FileWriter(filePath);){
			for(int i=1; i<dati.length; i++) {
				file.write(dati[i]+"\n");
			}
			file.write(wpm + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String[] getData() {
		String riga;
		String[] output = new String[0];
		int[] outputInt = new int[0];
		try (
			FileReader file = new FileReader(filePath);
			BufferedReader input = new BufferedReader(file);
			){
			output = new String[CasualText.calcLength(filePath)];
			outputInt = new int[CasualText.calcLength(filePath)];
			int i=0;
			while((riga=input.readLine())!=null) {
				if(riga!=null) {
					outputInt[i]=Integer.parseInt(riga);
					i++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Arrays.sort(outputInt);
		for(int i=0; i<outputInt.length; i++) {
			output[i]=""+outputInt[i];
		}
		return output;
	}
	
	public String getRecord() {
		String[] data=getData();
		int num=Integer.parseInt(data[0]);
		int maggiore=num;
		for(int i=1; i<data.length; i++){
			num=Integer.parseInt(data[i]);
			if(num>maggiore) {
				maggiore=num;
			}
		}
		return ""+maggiore;
	}
	
	public String getAvarage() {
		String[] data=getData();
		int num=Integer.parseInt(data[0]);
		int media=num;
		for(int i=1; i<data.length; i++){
			num=Integer.parseInt(data[i]);
			media+=num;
		}
		return ""+(media/data.length);
	}
	
	public void resetFile() {
		try (
				FileWriter file = new FileWriter(filePath);
				){
			file.write("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
