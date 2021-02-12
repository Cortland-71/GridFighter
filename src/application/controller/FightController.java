package application.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
		enemyController.setAllEnemyStats();

		playerController = new PlayerController(enemyController);
		playerController.setPlayerComponents(playerGrid, playerHpBar, playerEgBar, playerHpLabel, playerEgLabel, playerCashLabel);
		playerController.setPlayerButtons(attackButton, defendButton, stealButton, insureButton, healButton,
				executeButton);
		playerController.setPlayerCostLabels(atkCostLabel, defCostLabel, stlCostLabel, insCostLabel, helCostLabel);
		playerController.disableCorrectButtons();
		playerController.setAllPlayerStats();

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
	
	private void getEnemyGridKeyFrame() {
		List<List<Integer>> allRedMoves = enemyController.getAllRedMoves();
		enemyGridKeyFrame = new KeyFrame(Duration.millis(250),
                new EventHandler<ActionEvent>() {
        			int row = 0;
                    public void handle(ActionEvent event) {
                    	for(int i = 0; i < allRedMoves.get(row).size(); i++) {
                    		enemyController.getAllEnemyHBox().get(row).get(allRedMoves.get(row).get(i))
                    		.setStyle("-fx-background-color: -borderGray;");
                    		((Label)enemyController.getAllEnemyHBox().get(row).get(allRedMoves.get(row).get(i))
                    				.getChildren().get(0)).setStyle("-fx-text-background-color: black;");
                    	}
                    	row++;
                    }
                });
	}
	
	private void getPlayerGridKeyFrame() {
		playerGridKeyFrame = new KeyFrame(Duration.millis(500),
                new EventHandler<ActionEvent>() {
        			int row = 0;
		            public void handle(ActionEvent event) {
		               playerController.getAllPlayerHBox().get(row)
		               .setStyle("-fx-background-color: -borderGray;");
		               row++;
		            }
        });
	}
	
	private void runPlayerAndEnemyKeyFrames() {
		gridTimeLine = new Timeline();
        gridTimeLine.setCycleCount(enemyController.getAllRedMoves().size());
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
				nextRoundButton.setDisable(false);
			}
        });
	}
	
	public void startNextRound() {
		nextRoundButton.setDisable(true);
		roundLabel.setText("Round " + ++roundNumber);
		enemyController.setEnemyMoveLabelToRed();
		
		
	}
	
	
}
