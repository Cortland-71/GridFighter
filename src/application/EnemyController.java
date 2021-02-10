package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class EnemyController {

	private Enemy enemy;
	private List<List<Integer>> buttonIndexesToDisable = new ArrayList<>();
	List<List<Integer>> allRedMoves = new ArrayList<>();
	@FXML GridPane enemyGrid;

	public EnemyController(GridPane enemyGrid) {
		this.enemyGrid = enemyGrid;
		enemy = new Enemy();
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setAllEnemyStats() {
		populateGridWithEnemyMoves();
		createButtonIndexesToBeDisabled();
	}

	private void populateGridWithEnemyMoves() {
		for (int i = 0; i < enemy.getEnemyMoveLists().size(); i++) {
			List<Integer> currentRedMoves = new ArrayList<>();
			for (int j = 0; j < enemy.getEnemyMoveLists().get(i).size(); j++) {
				int currentNum = enemy.getEnemyMoveLists().get(i).get(j);
				HBox box = new HBox();
				Label numLabel = new Label(Integer.toString(currentNum));
				box.setAlignment(Pos.CENTER);
				box.getChildren().add(numLabel);
				if (currentNum == Collections.max(enemy.getEnemyMoveLists().get(i))) {
					numLabel.setStyle("-fx-text-fill: red;");
					currentRedMoves.add(j);
				}
				enemyGrid.add(box, j, i);
			}
			allRedMoves.add(currentRedMoves);
		}
	}
	
	private void createButtonIndexesToBeDisabled() {
		for(List<Integer> list : allRedMoves) {
			List<Integer> disableList = new ArrayList<>();
			for(int num : list) {
				if(num == 1 || num == 3) {
					disableList = new ArrayList<>();
					disableList.add(num-1);
				}
			}
			buttonIndexesToDisable.add(disableList);
		}
	}
	
	public List<List<Integer>> getButtonIndexesToDisable() {
		return buttonIndexesToDisable;
	}
}
