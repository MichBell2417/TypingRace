package typingRace.TypingRace;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import typingRace.TypingRace.object.CasualText;
import typingRace.TypingRace.object.ClassificationFile;

public class App extends Application {
    GridPane grid = new GridPane();
	Button bInterface= new Button("Classification");
	Button bChangeStatus= new Button("START");
	TextField writtenText = new TextField();
	GridPane keyboard = new GridPane();
	Label text = new Label();
	Label WPM = new Label("- WPM");
	
	Button bReset=new Button("Reset");
	
	ClassificationFile raccoltaPartite= new ClassificationFile("C:\\Users\\belmi\\Desktop\\Archivio\\workspaces-JAVA\\TypingRaceWorkspace\\TypingRaceProject\\src\\main\\resources\\typingRace\\TypingRace\\classification.txt");
	
	Timeline whileTyping = new Timeline(new KeyFrame(Duration.millis(50), e->execute()));
	
	int nInterface=1;
	boolean status=false;
	String words="";
	
    @Override
    public void start(Stage window) {
    	grid.setAlignment(Pos.CENTER);
    	
        bChangeStatus.setOnAction(e->changeStatus());
        bInterface.setOnAction(e->changeInterface());
        bReset.setOnAction(e->{
        	raccoltaPartite.resetFile();
        	changeInterface(2);
        	});
        
        bInterface.setFocusTraversable(false);
        bChangeStatus.setFocusTraversable(false);
        writtenText.setFocusTraversable(false);
		writtenText.setDisable(true);

		bInterface.prefWidthProperty().bind(grid.widthProperty());
		bInterface.setMaxWidth(300);
		bInterface.setMinWidth(100);
		bInterface.prefHeightProperty().bind(grid.heightProperty());
		bInterface.setMaxHeight(300);
		bInterface.setMaxHeight(100);
		
		bChangeStatus.prefWidthProperty().bind(grid.widthProperty());
		bChangeStatus.setMaxWidth(300);
		bChangeStatus.setMinWidth(100);
		bChangeStatus.prefHeightProperty().bind(grid.heightProperty());
		bChangeStatus.setMaxHeight(300);
		bChangeStatus.setMaxHeight(100);
		
		bReset.prefWidthProperty().bind(grid.widthProperty());
		bReset.setMaxWidth(300);
		bReset.setMinWidth(100);
		bReset.prefHeightProperty().bind(grid.heightProperty());
		bReset.setMaxHeight(300);
		bReset.setMaxHeight(100);
        
        text.setWrapText(true);
        text.setFont(new Font(15));
        
        writtenText.setPromptText("click start and type as fast as you can");
        
        Scene scene= new Scene(grid, 450, 275);
        window.setScene(scene);
        window.setTitle("Typing Race");
        changeInterface(1);
        window.show();
        scene.setOnKeyPressed(event -> takeClickedKey(event));
    }
    
    public void execute() {
    	
    }
    
    public void takeClickedKey(KeyEvent key){
    	String letter=key.getCode().toString();
    	System.out.println(letter);
    	if(status) {
    		
    		words+=letter;
    		writtenText.setText(words);
    	}
    }
    
    public void changeInterface() {
    	if(nInterface==2) {
    		changeInterface(1);
    	}else if(nInterface==1) {
    		changeInterface(2);
    	}
    }
    public void changeInterface(int interfaccia) {
    	grid.getChildren().clear();
    	if(interfaccia==1) {
    		nInterface=1;
    		Label title = new Label("TypingRace");
    		
    		CasualText text=new CasualText(10, "C:\\Users\\belmi\\Desktop\\Archivio\\workspaces-JAVA\\TypingRaceWorkspace\\TypingRaceProject\\src\\main\\resources\\typingRace\\TypingRace\\italian.txt");
    		this.text.setText(text.generateText());
    		
    		bInterface.setText("Classification");
    		
    		grid.add(title, 0, 0, 2, 1);
    		grid.add(keyboard, 0, 1, 2, 1);
    		grid.add(this.text, 0, 2, 2, 1);
    		grid.add(writtenText, 0, 3, 2, 1);
    		grid.add(WPM, 0, 4, 2, 1);
    		grid.add(bInterface, 0, 5);
    		grid.add(bChangeStatus, 1, 5);
    	}else if(interfaccia==2) {
    		nInterface=2;
    		String[] data = raccoltaPartite.getData();

    		TableView<MatchData> classificationTable = new TableView<>();
    		classificationTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    		classificationTable.setFocusTraversable(false);
    		
    		TableColumn<MatchData, String> colPosition = new TableColumn<>("Position");
            colPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
    		TableColumn<MatchData, String> colWPM = new TableColumn<>("WPM");
    		colWPM.setCellValueFactory(new PropertyValueFactory<>("wpm"));

    		classificationTable.getColumns().addAll(colPosition, colWPM);

    		ObservableList<MatchData> dataList = FXCollections.observableArrayList();
    		classificationTable.setItems(dataList);
    		for (int i = 1; i < data.length; i++) {
    		    dataList.add(new MatchData(""+(i), data[i]));
//    		    System.out.println(data[i]);
    		}
    		
    		Label lRecord=new Label("Record:");
    		Label record=new Label(raccoltaPartite.getRecord());
    		Label lAvarage=new Label("Avarage:");
    		Label avarage=new Label(raccoltaPartite.getAvarage());
    		
    		bInterface.setText("Play");
    		
    		grid.add(classificationTable, 0, 0, 1, 6);
    		grid.add(lRecord, 1, 0);
    		grid.add(record, 1, 1);
    		grid.add(lAvarage, 1, 2);
    		grid.add(avarage, 1, 3);
    		grid.add(bInterface, 1, 4);
    		grid.add(bReset, 1, 5);
    	
    	}
    		
    }
    
    public void changeStatus() {
    	if(nInterface==1) {
    		if(bChangeStatus.getText().equals("START")) {
	    		bChangeStatus.setText("RESET");
	    		writtenText.setDisable(false);
	    		status=true;
	    	}else if(bChangeStatus.getText().equals("RESET")){
	    		changeInterface(1);
	    		raccoltaPartite.saveData((int)(Math.random()*70));
	    		bChangeStatus.setText("START");
	    		writtenText.setDisable(true);
	    		status=false;
	    	}
    	}
    }
    
    public static void main(String[] args) {
        launch();
    }
}