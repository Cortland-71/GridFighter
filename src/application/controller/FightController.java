package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

import application.Attack;
import application.Defend;
import application.Fireable;
import application.Heal;
import application.Insure;
import application.Steal;
import application.model.Person;
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
	
	private KeyFrame playerGridKeyFrame;
	private KeyFrame enemyGridKeyFrame;
	private Timeline gridTimeLine;
	
	public static short roundNumber = 1;

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

		executeButton.setDisable(true);
		nextRoundButton.setDisable(true);
		roundLabel.setText("Round " + roundNumber);
	}

	public void addPlayerMoveToQue(Event e) {
		playerController.addPlayerMoveToQue(e);
	}

	public void executeButtonPress() {
		executeButton.setDisable(true);
		runPlayerAndEnemyKeyFrames();
	}
	
	private List<Fireable> fireableList = new ArrayList<>(Arrays.asList(new Attack(), new Defend(), new Steal(), new Insure(), new Heal()));
	
	int redMoveListIndex = 0;
	private void enemyFire(Person personAttacking, Person personBeingAttacked) {
		while(enemyController.getAllRedMoveIndexes().get(redMoveListIndex).isEmpty()) redMoveListIndex++;
		System.out.println(enemyController.getAllRedMoveIndexes().get(redMoveListIndex));
		for(int index : enemyController.getAllRedMoveIndexes().get(redMoveListIndex)) {
			System.out.println("enemy: ****");
			fireableList.get(index).fire(enemyController.getEnemy(), playerController.getPlayer());
		}
		redMoveListIndex++;
	}
	
	int playerActiveIndex = 0;
	private void playerFire() {
		System.out.println("player: ****");
		String labelText = ((Label)playerController.getActivePlayerHBoxes().get(playerActiveIndex++).getChildren().get(0)).getText();
		System.out.println("Player box text: " + labelText);
		
		
	}
	
	private void getEnemyGridKeyFrame() {
		
		enemyGridKeyFrame = new KeyFrame(Duration.millis(250),
	        new EventHandler<ActionEvent>() {
				int enemyHBoxListIndex = 0;
	            public void handle(ActionEvent event) {
	            	List<HBox> activeEnemyHBoxes = enemyController.getActiveEnemyHBoxes().get(enemyHBoxListIndex);
	            	for(HBox box : activeEnemyHBoxes) {
	            		box.setStyle("-fx-background-color: -borderGray;");
	            		((Label)box.getChildren().get(0)).setStyle("-fx-text-background-color: black;");
	            	}
	            	enemyHBoxListIndex++;
	            	enemyFire(enemyController.getEnemy(), playerController.getPlayer());
	            	updateAllStats();
	            	enemyController.setAllEffects(enemyController.getActivePlayerQueIndexes().get(enemyHBoxListIndex-1));
	            }
	        });
	}
	
	private void getPlayerGridKeyFrame() {
		playerGridKeyFrame = new KeyFrame(Duration.millis(500),
                new EventHandler<ActionEvent>() {
        			int playerHBoxListIndex = 0;
		            public void handle(ActionEvent event) {
		               playerController.getActivePlayerHBoxes().get(playerHBoxListIndex)
		               .setStyle("-fx-background-color: -borderGray;");
		               playerHBoxListIndex++;
		               
		               playerFire();
	                   updateAllStats();
		            }
        });
	}
	
	private void updateAllStats() {
		enemyController.updateAllEnemyStats();
        playerController.updateAllPlayerStats();
	}
	
	private void runPlayerAndEnemyKeyFrames() {
		gridTimeLine = new Timeline();
        gridTimeLine.setCycleCount(enemyController.getActiveEnemyHBoxes().size());
        getEnemyGridKeyFrame();
        getPlayerGridKeyFrame();
        getAfterGridKeyFrames();
        gridTimeLine.getKeyFrames().addAll(enemyGridKeyFrame, playerGridKeyFrame);
        gridTimeLine.play();
	}
	
	private void getAfterGridKeyFrames() {
		gridTimeLine.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("-----------------------------");
				nextRoundButton.setDisable(false);
				redMoveListIndex=0;
				playerActiveIndex=0;
				if(roundNumber > 4) {
					nextRoundButton.setText("Finish");
				}
			}
        });
	}
	
	public void changeScene(ActionEvent event) {
		try {
			BorderPane root = FXMLLoader.load(getClass().getResource("test.fxml"));
			Scene scene = new Scene(root);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void startNextRound(ActionEvent e) {
		if(roundNumber > 4) {
			System.out.println("Done");
			changeScene(e);
			return;
		}
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
