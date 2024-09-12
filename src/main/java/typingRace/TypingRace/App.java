package typingRace.TypingRace;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import typingRace.TypingRace.object.CasualText;

public class App extends Application {
    GridPane grid = new GridPane();
	Button bClass= new Button("classification");
	Button bChangeStatus= new Button("START");
	Label writtenText = new Label("click start and type as fast as you can");
	GridPane keyboard = new GridPane();
	TextArea text = new TextArea();
	Label WPM = new Label("- WPM");
	
	int interfaccia;
	boolean status=false;
	String words="";
	
    @Override
    public void start(Stage window) {
    	grid.setGridLinesVisible(true);
    	grid.setAlignment(Pos.CENTER);
    	
        bChangeStatus.setOnAction(e->changeStatus());
        
        bClass.setFocusTraversable(false);
        bChangeStatus.setFocusTraversable(false);

		bClass.prefWidthProperty().bind(grid.widthProperty());
		bClass.setMaxWidth(300);
		bClass.setMinWidth(100);
		bChangeStatus.prefWidthProperty().bind(grid.widthProperty());
		bChangeStatus.setMaxWidth(300);
		bChangeStatus.setMinWidth(100);
		bClass.prefHeightProperty().bind(grid.heightProperty());
		bClass.setMaxHeight(300);
		bClass.setMaxHeight(100);
		bChangeStatus.prefHeightProperty().bind(grid.heightProperty());
		bChangeStatus.setMaxHeight(300);
		bChangeStatus.setMaxHeight(100);
        
        text.setWrapText(true);
        text.setEditable(false);
        
        Scene scene= new Scene(grid);
        window.setScene(scene);
        window.setTitle("Typing Race");
        scene.setOnKeyPressed(e->takeClickedKey(e));
        changeInterface(1);
        window.show();
    }
    
    public void takeClickedKey(KeyEvent key){
    	String letter=key.getCode().toString();
    	System.out.println(letter);
    	if(status) {
    		words+=letter;
    		writtenText.setText(words);
    	}
    }
    
    public void changeInterface(int interfaccia) {
    	grid.getChildren().clear();
    	if(interfaccia==1) {
    		this.interfaccia=1;
    		Label title = new Label("TypingRace");
    		
    		CasualText text=new CasualText(100, "C:\\Users\\belmi\\Desktop\\Archivio\\workspaces-JAVA\\TypingRaceWorkspace\\TypingRaceProject\\src\\main\\resources\\typingRace\\TypingRace\\italian.txt");
    		this.text.setText(text.generateText());
    		
    		grid.add(title, 0, 0, 2, 1);
    		grid.add(keyboard, 0, 1, 2, 1);
    		grid.add(this.text, 0, 2, 2, 1);
    		grid.add(writtenText, 0, 3, 2, 1);
    		grid.add(WPM, 0, 4, 2, 1);
    		grid.add(bClass, 0, 5);
    		grid.add(bChangeStatus, 1, 5);
    	}
    }
    
    public void changeStatus() {
    	if(interfaccia==1) {
    		if(bChangeStatus.getText().equals("START")) {
	    		bChangeStatus.setText("RESET");
	    		status=true;
	    	}else if(bChangeStatus.getText().equals("RESET")){
	    		changeInterface(1);
	    		bChangeStatus.setText("START");
	    		status=false;
	    	}
    	}
    }
    
    public static void main(String[] args) {
        launch();
    }
}