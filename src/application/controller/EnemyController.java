package application.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import application.model.Enemy;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class EnemyController {

	private Enemy enemy;
	private List<List<Integer>> buttonIndexesToDisable = new ArrayList<>();
	private List<List<Integer>> allRedMoves = new ArrayList<>();
	private List<List<HBox>> allEnemyHBox = new ArrayList<>();
	
	@FXML GridPane enemyGrid;

	public EnemyController(GridPane enemyGrid) {
		this.enemyGrid = enemyGrid;
		enemy = new Enemy();
	}
	
	public List<List<HBox>> getAllEnemyHBox() {
		return allEnemyHBox;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setAllEnemyStats() {
		populateGridWithEnemyMoves();
		setEnemyMoveLabelToRed();
		createButtonIndexesToBeDisabled();
	}

	private void populateGridWithEnemyMoves() {
		for (int i = 0; i < enemy.getEnemyMoveLists().size(); i++) {
			
			List<HBox> hboxRowList = new ArrayList<>();
			for (int j = 0; j < enemy.getEnemyMoveLists().get(i).size(); j++) {
				int currentNum = enemy.getEnemyMoveLists().get(i).get(j);
				HBox box = new HBox();
				Label numLabel = new Label(Integer.toString(currentNum));
				box.setAlignment(Pos.CENTER);
				box.getChildren().add(numLabel);

				enemyGrid.add(box, j, i);
				hboxRowList.add(box);
			}
			
			allEnemyHBox.add(hboxRowList);
		}
	}
	
	public void setEnemyMoveLabelToRed() {
		for(int i = 0; i < allEnemyHBox.size(); i++) {
			List<Integer> redMoves = new ArrayList<>();
			for(int j = 0; j < allEnemyHBox.get(i).size(); j++) {
				Label hboxLabel = ((Label)allEnemyHBox.get(i).get(j).getChildren().get(0));
				if(Integer.parseInt(hboxLabel.getText()) == enemy.getSortedEnemyMoveLists().get(i).get(FightController.roundNumber-1)) {
					hboxLabel.setStyle("-fx-text-fill: red;");
					redMoves.add(j);
				}
			}
			allRedMoves.add(redMoves);
			
		}
	}
	
	private void createButtonIndexesToBeDisabled() {
		for(List<Integer> list : allRedMoves) {
			List<Integer> disableList = new ArrayList<>();
			for(int num : list) {
				if(num == 1 || num == 3) {
					disableList.add(num-1);
				}
			}
			buttonIndexesToDisable.add(disableList);
		}
	}
	
	public List<List<Integer>> getButtonIndexesToDisable() {
		return buttonIndexesToDisable;
	}
	
	public List<List<Integer>> getAllRedMoves() {
		return allRedMoves;
	}
}
