package application.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EndGameController {
	
	private EnemyController enemyController;
	private PlayerController playerController;
	
	public EndGameController(FightController fightController) {
		this.enemyController = fightController.getEnemyController();
		this.playerController = fightController.getPlayerController();
	}
	
	public void changeScene(ActionEvent event) {
		try {
			BorderPane root = FXMLLoader.load(getClass().getResource("ResultScene.fxml"));
			Scene scene = new Scene(root);
			Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
			window.setScene(scene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean someoneDied() {
		if(playerController.getPlayer().getHp() <= 0 || enemyController.getEnemy().getHp() <= 0) {
			return true;
		}
		return false;
	}
	
	public void changeSceneIfSomeoneDiedOrAllRoundsAreOver(ActionEvent e) {
		if(FightController.roundNumber > 4 || someoneDied()) {
			changeScene(e);
		}
	}

}
