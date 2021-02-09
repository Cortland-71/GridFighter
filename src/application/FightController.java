package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
	@FXML Button executeButton;
	
	private List<Button> playerButtonList;
	
	private static int playerQueCounter = 0;
	
	private List<List<Integer>> enemyMoveLists = new ArrayList<>();
	
	private Player player;
	private Enemy enemy;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		player = new Player();
		setAllPlayerStats();
		playerButtonList = new ArrayList<>(Arrays.asList(attackButton, defendButton, stealButton, insureButton, healButton));
		executeButton.setDisable(true);
		createEnemyMoveLists();
		populateGridWithEnemyMoves();
	}
	
	private void populateGridWithEnemyMoves() {
		for(int i = 0; i < enemyMoveLists.size(); i++) {
			for(int j = 0; j < enemyMoveLists.get(i).size(); j++) {
				int currentNum = enemyMoveLists.get(i).get(j);
				HBox box = new HBox();
				Label numLabel = new Label(Integer.toString(currentNum));
				box.setAlignment(Pos.CENTER);
				box.getChildren().add(numLabel);
				if(currentNum == Collections.max(enemyMoveLists.get(i))) {
					numLabel.setStyle("-fx-text-fill: red;");
				}
				enemyGrid.add(box, j, i);
			}
		}
	}
	
	private void createEnemyMoveLists() {
		for(int row = 0; row < 10; row++) {
			List<Integer> rowList = new ArrayList<>();
			for(int col = 0; col < 5; col++) {
				Random rand = new Random();
				rowList.add(rand.nextInt((20-5)+1)+5);
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
			executeButton.setDisable(false);
		}
	}
	
	@FXML ProgressBar playerHpBar;
	@FXML Label playerHpLabel;
	@FXML ProgressBar playerEgBar;
	@FXML Label playerEgLabel;
	private void setAllPlayerStats() {
		playerHpBar.setProgress(player.getHp());
		playerHpLabel.setText(Double.toString(player.getHp()));
		playerEgBar.setProgress(player.getEg());
		playerEgLabel.setText(Double.toString(player.getEg()));
	}
	
	public void executeButtonPress() {
		
	}
}
