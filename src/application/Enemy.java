package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Person {
	private List<List<Integer>> enemyMoveLists = new ArrayList<>();
	private List<List<Integer>> indexesToDisableLists = new ArrayList<>();

	public List<List<Integer>> getEnemyMoveLists() {
		return enemyMoveLists;
	}

	public Enemy() {
		createEnemyMoveLists();
	}

	private void createEnemyMoveLists() {
		for (int row = 0; row < 10; row++) {
			List<Integer> rowList = new ArrayList<>();
			for (int col = 0; col < 5; col++) {
				Random rand = new Random();
				rowList.add(rand.nextInt((20 - 5) + 1) + 5);
			}
			enemyMoveLists.add(rowList);
		}
	}

}
