package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class PlayerController {

	private static int playerQueCounter = 0;

	private Player player;

	@FXML
	GridPane playerGrid;
	@FXML
	ProgressBar playerHpBar;
	@FXML
	Label playerHpLabel;
	@FXML
	ProgressBar playerEgBar;
	@FXML
	Label playerEgLabel;

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

	private List<Button> playerButtonList;
	private EnemyController enemyController;

	public PlayerController(EnemyController enemyController) {
		this.enemyController = enemyController;
		player = new Player();
	}
	
	public void setPlayerComponents(GridPane playerGrid, ProgressBar playerHpBar, ProgressBar playerEgBar, Label playerHpLabel,
			Label playerEgLabel) {
		this.playerGrid = playerGrid;
		this.playerHpBar = playerHpBar;
		this.playerEgBar = playerEgBar;
		this.playerHpLabel = playerHpLabel;
		this.playerEgLabel = playerEgLabel;
	}

	public void setPlayerButtons(Button attackButton, Button defendButton, Button stealButton, Button insureButton,
			Button healButton, Button executeButton) {
		this.attackButton = attackButton;
		this.defendButton = defendButton;
		this.stealButton = stealButton;
		this.insureButton = insureButton;
		this.healButton = healButton;
		this.executeButton = executeButton;
		playerButtonList = new ArrayList<>(
				Arrays.asList(attackButton, defendButton, stealButton, insureButton, healButton));

	}

	public Player getPlayer() {
		return player;
	}

	public void addPlayerMoveToQue(Event e) {
		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.getChildren().add(new Label(((Button) e.getSource()).getText()));
		playerGrid.add(box, 0, playerQueCounter++);
		
		if (playerQueCounter >= 10) {
			for (Button b : playerButtonList)
				b.setDisable(true);
			executeButton.setDisable(false);
			return;
		}
		
		List<Integer> currentIndexesToDisable = enemyController.getButtonIndexesToDisable().get(playerQueCounter);
		
		disableAllPlayerButtons(false);
		for (int index : currentIndexesToDisable) {
			playerButtonList.get(index).setDisable(true);
		}
		
		
	}
	
	private void disableAllPlayerButtons(boolean state) {
		for(Button b : playerButtonList) b.setDisable(state);
	}

	public void setAllPlayerStats() {
		playerHpBar.setProgress(player.getHp());
		playerHpLabel.setText(Double.toString(player.getHp()));
		playerEgBar.setProgress(player.getEg());
		playerEgLabel.setText(Double.toString(player.getEg()));
	}
	
	public List<Button> getPlayerButtonList() {
		return playerButtonList;
	}

}
