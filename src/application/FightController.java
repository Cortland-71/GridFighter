package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class FightController implements Initializable{
	
	@FXML GridPane enemyGrid;
	
	

	private List<List<Integer>> enemyMoveLists = new ArrayList<>();
	
	private void createEnemyMoveLists() {
		for(int i = 0; i < 10; i++) { //Row
			List<Integer> rowList = new ArrayList<>();
			for(int j = 0; j < 5; j++) { //Column
				
				Random rand = new Random();
				int num = rand.nextInt((20-0)+1)+0;
				rowList.add(num);
			}
			enemyMoveLists.add(rowList);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		createEnemyMoveLists();
		System.out.println(enemyMoveLists);
		for(int i = 0; i < enemyMoveLists.size(); i++) {
			for(int j = 0; j < enemyMoveLists.get(i).size(); j++) {
				HBox box = new HBox();
				box.setAlignment(Pos.CENTER);
				box.getChildren().add(new Label(Integer.toString(enemyMoveLists.get(i).get(j))));
				enemyGrid.add(box, j, i);
				
				
			}
		}
	}
	
}
