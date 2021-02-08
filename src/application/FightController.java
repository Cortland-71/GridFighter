package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class FightController implements Initializable{
	
	@FXML GridPane enemyGrid;
	@FXML GridPane playerGrid;
	
	@FXML Button attackButton;
	@FXML Button defendButton;
	@FXML Button stealButton;
	@FXML Button insureButton;
	@FXML Button healButton;
	
	private List<Button> playerButtonList;
	
	private static int playerQueCounter = 0;
	
	private List<List<Integer>> enemyMoveLists = new ArrayList<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		playerButtonList = new ArrayList<>(Arrays.asList(attackButton, defendButton, stealButton, insureButton, healButton));
		createEnemyMoveLists();
		System.out.println(enemyMoveLists);
		populateGridWithEnemyMoves();
	}
	
	private void populateGridWithEnemyMoves() {
		for(int i = 0; i < enemyMoveLists.size(); i++) {
			for(int j = 0; j < enemyMoveLists.get(i).size(); j++) {
				HBox box = new HBox();
				box.setAlignment(Pos.CENTER);
				box.getChildren().add(new Label(Integer.toString(enemyMoveLists.get(i).get(j))));
				enemyGrid.add(box, j, i);
			}
		}
	}
	
	private void createEnemyMoveLists() {
		for(int row = 0; row < 10; row++) {
			List<Integer> rowList = new ArrayList<>();
			for(int col = 0; col < 5; col++) {
				Random rand = new Random();
				rowList.add(rand.nextInt((20-0)+1)+0);
			}
			enemyMoveLists.add(rowList);
		}
	}
	
	public void addPlayerMoveToQue(Event e) {
		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.getChildren().add(new Label(((Button)e.getSource()).getText()));
		playerGrid.add(box, 0, playerQueCounter++);
		if(playerQueCounter >= 10) {
			for(Button b : playerButtonList) b.setDisable(true);
		}
	}
}
