package application.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import application.model.Player;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class PlayerController {

	public static int playerQueCounter = 0;

	private Player player;

	@FXML GridPane playerGrid;
	@FXML Label playerCashLabel;
	@FXML ProgressBar playerHpBar;
	@FXML Label playerHpLabel;
	@FXML ProgressBar playerEgBar;
	@FXML Label playerEgLabel;

	@FXML Button attackButton;
	@FXML Button defendButton;
	@FXML Button stealButton;
	@FXML Button insureButton;
	@FXML Button healButton;
	@FXML Button executeButton;
	
	@FXML Label currentAtkCostLabel;
	@FXML Label currentDefCostLabel;
	@FXML Label currentStlCostLabel;
	@FXML Label currentInsCostLabel;
	@FXML Label currentHelCostLabel;
	
	@FXML Label nextRoundAtkCostLabel;
	@FXML Label nextRoundDefCostLabel;
	@FXML Label nextRoundStlCostLabel;
	@FXML Label nextRoundInsCostLabel;
	@FXML Label nextRoundHelCostLabel;

	private List<Button> playerButtonList;
	
	private List<HBox> activePlayerHBoxes = new ArrayList<>();
	private EnemyController enemyController;

	public PlayerController(EnemyController enemyController) {
		this.enemyController = enemyController;
		player = new Player();
	}
	
	public void setPlayerComponents(GridPane playerGrid, ProgressBar playerHpBar, ProgressBar playerEgBar, Label playerHpLabel,
			Label playerEgLabel, Label playerCashLabel) {
		this.playerGrid = playerGrid;
		this.playerHpBar = playerHpBar;
		this.playerEgBar = playerEgBar;
		this.playerHpLabel = playerHpLabel;
		this.playerEgLabel = playerEgLabel;
		this.playerCashLabel = playerCashLabel;
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
	
	public void setPlayerCurrentCostLabels(Label currentAtkCostLabel, Label currentDefCostLabel, 
			Label currentStlCostLabel, Label currentInsCostLabel, Label currentHelCostLabel) {
		this.currentAtkCostLabel = currentAtkCostLabel;
		this.currentDefCostLabel = currentDefCostLabel;
		this.currentStlCostLabel = currentStlCostLabel;
		this.currentInsCostLabel = currentInsCostLabel;
		this.currentHelCostLabel = currentHelCostLabel;
	}
	
	public void setPlayerNextRoundCostLabels(Label nextRoundAtkCostLabel, Label nextRoundDefCostLabel, 
			Label nextRoundStlCostLabel, Label nextRoundInsCostLabel, Label nextRoundHelCostLabel) {
		this.nextRoundAtkCostLabel = nextRoundAtkCostLabel;
		this.nextRoundDefCostLabel = nextRoundDefCostLabel;
		this.nextRoundStlCostLabel = nextRoundStlCostLabel;
		this.nextRoundInsCostLabel = nextRoundInsCostLabel;
		this.nextRoundHelCostLabel = nextRoundHelCostLabel;
	}

	public void addPlayerMoveToQue(Event e) {
		createActivePlayerHBoxes(e);
		if (playerQueCounter >= enemyController.getActivePlayerQueIndexes().size()) {
			for (Button b : playerButtonList)
				b.setDisable(true);
			executeButton.setDisable(false);
			return;
		}
		disableCorrectButtons();
	}
	
	private void createActivePlayerHBoxes(Event e) {
		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.getChildren().add(new Label(((Button) e.getSource()).getText()));
		getPlayerGrid().add(box, 0, enemyController.getActivePlayerQueIndexes().get(playerQueCounter++));
		activePlayerHBoxes.add(box);
	}
	
	public void disableCorrectButtons() {
		List<Integer> currentIndexesToDisable = enemyController.getButtonIndexesToDisable().get(playerQueCounter);
		disableAllPlayerButtons(false);
		for (int index : currentIndexesToDisable) {
			playerButtonList.get(index).setDisable(true);
		}
	}
	
	private void disableAllPlayerButtons(boolean state) {
		for(Button b : playerButtonList) b.setDisable(state);
	}

	public void updateAllPlayerStats() {
		
		playerCashLabel.setText("Cash: " + player.getCash());
		playerHpBar.setProgress(player.getHp());
		playerHpLabel.setText(String.format("%,.2f", player.getHp()));
		playerEgBar.setProgress(player.getEg());
		playerEgLabel.setText(String.format("%,.2f", player.getEg()));
		
		currentAtkCostLabel.setText("ATK: " + player.getCurrentAtkCost());
		currentDefCostLabel.setText("DEF: " + player.getCurrentDefCost());
		currentStlCostLabel.setText("STL: " + player.getCurrentStlCost());
		currentInsCostLabel.setText("INS: " + player.getCurrentInsCost());
		currentHelCostLabel.setText("HEL: $" + player.getCurrentHelCost());
		
		nextRoundAtkCostLabel.setText("ATK: " + player.getNextRoundAtkCost());
		nextRoundDefCostLabel.setText("DEF: " + player.getNextRoundDefCost());
		nextRoundStlCostLabel.setText("STL: " + player.getNextRoundStlCost());
		nextRoundInsCostLabel.setText("INS: " + player.getNextRoundInsCost());
		nextRoundHelCostLabel.setText("HEL: $" + player.getNextRoundHelCost());
		
	}
	
	public List<Button> getPlayerButtonList() {
		return playerButtonList;
	}

	public Player getPlayer() {
		return player;
	}
	
	public void clearPlayerGrid() {
		Node node = playerGrid.getChildren().get(0);
		playerGrid.getChildren().clear();
		playerGrid.getChildren().add(0,node);
	}
	
	public GridPane getPlayerGrid() {
		return playerGrid;
	}
	
	public List<HBox> getActivePlayerHBoxes() {
		return activePlayerHBoxes;
	}
	
}
