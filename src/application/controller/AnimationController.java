package application.controller;

import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class AnimationController {
	
	private KeyFrame playerGridKeyFrame;
	private KeyFrame enemyGridKeyFrame;
	
	private EnemyController enemyController;
	private PlayerController playerController;
	
	private FightController fightController;
	
	private Timeline gridTimeLine;
	
	public AnimationController(FightController fightController) {
		this.fightController = fightController;
		this.enemyController = fightController.getEnemyController();
		this.playerController = fightController.getPlayerController();
	}
	
	public KeyFrame getPlayerGridKeyFrame() {
		return playerGridKeyFrame;
	}
	
	public KeyFrame getEnemyGridKeyFrame() {
		return enemyGridKeyFrame;
	}
	
	private void updateAllStats() {
		enemyController.updateAllEnemyStats();
        playerController.updateAllPlayerStats();
	}
	
	public void runPlayerAndEnemyKeyFrames() {
		gridTimeLine = new Timeline();
        gridTimeLine.setCycleCount(enemyController.getActiveEnemyHBoxes().size());
        getEnemyGridKeyFrameProcess();
        getPlayerGridKeyFrameProcess();
        getAfterGridKeyFrames();
        gridTimeLine.getKeyFrames().addAll(enemyGridKeyFrame, playerGridKeyFrame);
        gridTimeLine.play();
	}
	
	private void getEnemyGridKeyFrameProcess() {
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
	            	fightController.enemyFire(enemyController.getEnemy(), playerController.getPlayer());
	            	updateAllStats();
	            	enemyController.setAllEffects(enemyController.getActivePlayerQueIndexes().get(enemyHBoxListIndex-1));
	            	
	            }
	        });
	}
	
	private void getPlayerGridKeyFrameProcess() {
		playerGridKeyFrame = new KeyFrame(Duration.millis(500),
                new EventHandler<ActionEvent>() {
        			int playerHBoxListIndex = 0;
		            public void handle(ActionEvent event) {
		               playerController.getActivePlayerHBoxes().get(playerHBoxListIndex)
		               .setStyle("-fx-background-color: -borderGray;");
		               playerHBoxListIndex++;
		               
		               fightController.playerFire();
	                   updateAllStats();
		            }
        });
	}
	
	private void getAfterGridKeyFrames() {
		gridTimeLine.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				System.out.println("-----------------------------");
				fightController.getNextRoundButton().setDisable(false);
				fightController.setRedMoveListIndex(0);
				fightController.setPlayerActiveIndex(0);
				if(FightController.roundNumber > 4 || 
						fightController.getEndGameController().someoneDied()) {
					fightController.getNextRoundButton().setText("Finish");
				}
			}
        });
	}

}
