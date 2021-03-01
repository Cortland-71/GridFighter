package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

import application.model.Person;
import application.model.moves.Attack;
import application.model.moves.Defend;
import application.model.moves.Fireable;
import application.model.moves.Heal;
import application.model.moves.Insure;
import application.model.moves.Steal;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FightController implements Initializable {

	@FXML GridPane enemyGrid;
	@FXML GridPane playerGrid;

	@FXML Button attackButton;
	@FXML Button defendButton;
	@FXML Button stealButton;
	@FXML Button insureButton;
	@FXML Button healButton;
	@FXML Button executeButton;
	@FXML Button nextRoundButton;

	@FXML Label playerCashLabel;
	@FXML ProgressBar playerHpBar;
	@FXML Label playerHpLabel;
	@FXML ProgressBar playerEgBar;
	@FXML Label playerEgLabel;
	
	@FXML ProgressBar enemyHpBar;
	@FXML Label enemyHpLabel;
	
	@FXML Label atkCostLabel;
	@FXML Label defCostLabel;
	@FXML Label stlCostLabel;
	@FXML Label insCostLabel;
	@FXML Label helCostLabel;
	
	@FXML Label roundLabel;

	private PlayerController playerController;
	private EnemyController enemyController;
	
	private EndGameController endGameController;
	private AnimationController animationController;
	
	
	
	
	public static short roundNumber = 1;
	
	public Button getNextRoundButton() {
		return this.nextRoundButton;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		enemyController = new EnemyController(enemyGrid);
		enemyController.setEnemyComponents(enemyHpBar, enemyHpLabel);
		enemyController.populateEnemyGridWithHBoxsAndAddThemToList();
		enemyController.populateHBoxsWithEnemyMoveLabels();
		enemyController.setEnemyMoveLabelToRed();
		enemyController.setButtonIndexesToBeDisabled();
		enemyController.removeHighestMoveFromSortedList();
		enemyController.updateAllEnemyStats();
		enemyController.setAllEffects(enemyController.getActivePlayerQueIndexes().get(0));
		
		playerController = new PlayerController(enemyController);
		playerController.setPlayerComponents(playerGrid, playerHpBar, playerEgBar, playerHpLabel, playerEgLabel, playerCashLabel);
		playerController.setPlayerButtons(attackButton, defendButton, stealButton, insureButton, healButton,
				executeButton);
		playerController.setPlayerCostLabels(atkCostLabel, defCostLabel, stlCostLabel, insCostLabel, helCostLabel);
		playerController.disableCorrectButtons();
		playerController.updateAllPlayerStats();
		
		endGameController = new EndGameController(this);
		animationController = new AnimationController(this);

		executeButton.setDisable(true);
		nextRoundButton.setDisable(true);
		roundLabel.setText("Round " + roundNumber);
	}
	
	public PlayerController getPlayerController() {
		return this.playerController;
	}
	
	public EnemyController getEnemyController() {
		return this.enemyController;
	}

	public void addPlayerMoveToQue(Event e) {
		playerController.addPlayerMoveToQue(e);
	}

	public void executeButtonPress() {
		executeButton.setDisable(true);
		animationController.runPlayerAndEnemyKeyFrames();
	}
	
	private List<Fireable> fireableList = new ArrayList<>(Arrays.asList(new Attack(), new Defend(), new Steal(), new Insure(), new Heal()));
	
	private int redMoveListIndex = 0;
	
	public void setRedMoveListIndex(int redMoveListIndex) {
		this.redMoveListIndex = redMoveListIndex;
	}
	public void enemyFire(Person personAttacking, Person personBeingAttacked) {
		while(enemyController.getAllRedMoveIndexes().get(redMoveListIndex).isEmpty()) redMoveListIndex++;
		for(int index : enemyController.getAllRedMoveIndexes().get(redMoveListIndex)) {
			fireableList.get(index).fire(enemyController.getEnemy(), playerController.getPlayer());
		}
		redMoveListIndex++;
	}
	
	private int playerActiveIndex = 0;
	
	public void setPlayerActiveIndex(int playerActiveIndex) {
		this.playerActiveIndex = playerActiveIndex;
	}
	public void playerFire() {
		String labelText = ((Label)playerController.getActivePlayerHBoxes().get(playerActiveIndex++).getChildren().get(0)).getText();
		for(Fireable move : fireableList) {
			if(labelText.equals(move.getId())) {
				move.fire(playerController.getPlayer(), enemyController.getEnemy());
			}
		}
	}
	
	
	public void startNextRound(ActionEvent e) {
		endGameController.setFinishButton(e);
		nextRoundButton.setDisable(true);
		PlayerController.playerQueCounter = 0;
		playerController.getActivePlayerHBoxes().clear();
		roundLabel.setText("Round " + ++roundNumber);
		enemyController.setEnemyMoveLabelToRed();
		enemyController.setButtonIndexesToBeDisabled();
		enemyController.removeHighestMoveFromSortedList();
		playerController.disableCorrectButtons();
		playerController.clearPlayerGrid();
 	}
	
	public static <T> List<T> getNonDupList(List<T> list) {
		HashSet<T> set = new HashSet<>(list);
		return new ArrayList<>(set);
	}
}
