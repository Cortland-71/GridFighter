package application;

import java.util.Collections;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class EnemyController {

	private Enemy enemy;
	@FXML
	GridPane enemyGrid;

	public EnemyController(GridPane enemyGrid) {
		this.enemyGrid = enemyGrid;
		enemy = new Enemy();
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setAllEnemyStats() {
		populateGridWithEnemyMoves();
	}

	private void populateGridWithEnemyMoves() {
		for (int i = 0; i < enemy.getEnemyMoveLists().size(); i++) {
			for (int j = 0; j < enemy.getEnemyMoveLists().get(i).size(); j++) {
				int currentNum = enemy.getEnemyMoveLists().get(i).get(j);
				HBox box = new HBox();
				Label numLabel = new Label(Integer.toString(currentNum));
				box.setAlignment(Pos.CENTER);
				box.getChildren().add(numLabel);
				if (currentNum == Collections.max(enemy.getEnemyMoveLists().get(i))) {
					numLabel.setStyle("-fx-text-fill: red;");
				}
				enemyGrid.add(box, j, i);
			}
		}
	}

}
