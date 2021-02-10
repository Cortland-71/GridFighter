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

public class FightController implements Initializable {

	@FXML
	GridPane enemyGrid;
	@FXML
	GridPane playerGrid;

	@FXML
	Button attackButton;
	@FXML
	Button defendButton;
	@FXML
	Button stealButton;
	@FXML
	Button insureButton;
	@FXML
	Button healButton;
	@FXML
	Button executeButton;

	@FXML
	ProgressBar playerHpBar;
	@FXML
	Label playerHpLabel;
	@FXML
	ProgressBar playerEgBar;
	@FXML
	Label playerEgLabel;

	private PlayerController playerController;
	private EnemyController enemyController;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		enemyController = new EnemyController(enemyGrid);
		enemyController.setAllEnemyStats();

		playerController = new PlayerController(enemyController);
		playerController.setPlayerComponents(playerGrid, playerHpBar, playerEgBar, playerHpLabel, playerEgLabel);
		playerController.setPlayerButtons(attackButton, defendButton, stealButton, insureButton, healButton,
				executeButton);
		playerController.setAllPlayerStats();

		executeButton.setDisable(true);
		
		System.out.println(enemyController.getButtonIndexesToDisable());
	}
	
	

	public void addPlayerMoveToQue(Event e) {
		playerController.addPlayerMoveToQue(e);
	}

	public void executeButtonPress() {

	}
}
