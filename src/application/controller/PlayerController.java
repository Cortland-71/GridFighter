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
	
	@FXML Label atkCostLabel;
	@FXML Label defCostLabel;
	@FXML Label stlCostLabel;
	@FXML Label insCostLabel;
	@FXML Label helCostLabel;

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
	
	public void setPlayerCostLabels(Label atkCostLabel, Label defCostLabel, Label stlCostLabel, Label insCostLabel, Label helCostLabel) {
		this.atkCostLabel = atkCostLabel;
		this.defCostLabel = defCostLabel;
		this.stlCostLabel = stlCostLabel;
		this.insCostLabel = insCostLabel;
		this.helCostLabel = helCostLabel;
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

	public void setAllPlayerStats() {
		
		playerCashLabel.setText("Cash: " + player.getCash());
		playerHpBar.setProgress(player.getHp());
		playerHpLabel.setText(Double.toString(player.getHp()));
		playerEgBar.setProgress(player.getEg());
		playerEgLabel.setText(Double.toString(player.getEg()));
		
		atkCostLabel.setText("ATK: " + player.getAtkCost());
		defCostLabel.setText("DEF: " + player.getDefCost());
		stlCostLabel.setText("STL: " + player.getStlCost());
		insCostLabel.setText("INS: " + player.getInsCost());
		helCostLabel.setText("HEL: $" + player.getHelCost());
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
