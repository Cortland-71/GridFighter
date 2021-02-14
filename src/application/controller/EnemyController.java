package application.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import application.model.Enemy;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class EnemyController {

	private Enemy enemy;
	private List<List<Integer>> buttonIndexesToDisable = new ArrayList<>();
	private List<List<Integer>> allRedMoveIndexes = new ArrayList<>();
	private List<List<HBox>> allEnemyHBox = new ArrayList<>();
	private List<Integer> activePlayerQueIndexes = new ArrayList<>();
	
	@FXML GridPane enemyGrid;
	private PlayerController playerController;

	public EnemyController(GridPane enemyGrid) {
		this.enemyGrid = enemyGrid;
		
		enemy = new Enemy();
	}
	
	public void setPlayerController(PlayerController playerController) {
		this.playerController = playerController;
	}
	
	public List<List<HBox>> getAllEnemyHBox() {
		return allEnemyHBox;
	}

	public Enemy getEnemy() {
		return enemy;
	}
	
	public void populateEnemyGridWithHBoxsAndAddThemToList() {
		for (int i = 0; i < enemy.getEnemyMoveLists().size(); i++) {
			List<HBox> hboxRowList = new ArrayList<>();
			for (int j = 0; j < enemy.getEnemyMoveLists().get(i).size(); j++) {
				HBox box = new HBox();
				box.setAlignment(Pos.CENTER);
				enemyGrid.add(box, j, i);
				hboxRowList.add(box);
			}
			allEnemyHBox.add(hboxRowList);
		}
	}
	
	public void populateHBoxsWithEnemyMoveLabels() {
		for (int i = 0; i < allEnemyHBox.size(); i++) {
			for (int j = 0; j < allEnemyHBox.get(i).size(); j++) {
				int currentNum = enemy.getEnemyMoveLists().get(i).get(j);
				Label numLabel = new Label(Integer.toString(currentNum));
				allEnemyHBox.get(i).get(j).getChildren().add(numLabel);
			}
		}
	}
	
	public void setEnemyMoveLabelToRed() {
		allRedMoveIndexes.clear();
		activePlayerQueIndexes.clear();
		for(int i = 0; i < allEnemyHBox.size(); i++) {
			List<Integer> redMoves = new ArrayList<>();
			for(int j = 0; j < allEnemyHBox.get(i).size(); j++) {
				if(enemy.getSortedEnemyMoveLists().get(i).isEmpty()) continue;
				Label hboxLabel = ((Label)allEnemyHBox.get(i).get(j).getChildren().get(0));
				if(Integer.parseInt(hboxLabel.getText()) == enemy.getSortedEnemyMoveLists().get(i).get(0)) {
					hboxLabel.setStyle("-fx-text-fill: red;");
					redMoves.add(j);
					activePlayerQueIndexes.add(i);
					activePlayerQueIndexes = FightController.getNonDupList(activePlayerQueIndexes);
					//System.out.println("EnemyController: activePlayerQueIndexes: " + activePlayerQueIndexes);
				}
			}
			allRedMoveIndexes.add(redMoves);
		}
	}
	
	public void setButtonIndexesToBeDisabled() {
		buttonIndexesToDisable.clear();
		for(List<Integer> list : allRedMoveIndexes) {
			List<Integer> disableList = new ArrayList<>();
			for(int num : list) {
				if(num == 1 || num == 3) {
					disableList.add(num-1);
				}
			}
			buttonIndexesToDisable.add(disableList);
		}
	}
	
	public void removeHighestMoveFromSortedList() {
		for(List<Integer> list : enemy.getSortedEnemyMoveLists()) {
			if(!list.isEmpty()) list.remove(0);
		}
	}
	
	public void addPlayerMoveToQue(Event e) {
		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.getChildren().add(new Label(((Button) e.getSource()).getText()));
		playerController.getPlayerGrid().add(box, 0, activePlayerQueIndexes.get(PlayerController.playerQueCounter++));
		playerController.getAllPlayerHBox().add(box);
	}
	
	
	
	public List<List<Integer>> getButtonIndexesToDisable() {
		return buttonIndexesToDisable;
	}
	
	public List<List<Integer>> getAllRedMoves() {
		return allRedMoveIndexes;
	}
	
	public List<Integer> getActivePlayerQueIndexes() {
		return activePlayerQueIndexes;
	}
}
