package application.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Enemy extends Person {
	private List<List<Integer>> enemyMoveLists = new ArrayList<>();
	private List<List<Integer>> sortedEnemyMoveLists = new ArrayList<>();
	

	public List<List<Integer>> getEnemyMoveLists() {
		return enemyMoveLists;
	}
	
	public List<List<Integer>> getSortedEnemyMoveLists() {
		return sortedEnemyMoveLists;
	}

	public Enemy() {
		createEnemyMoveLists();
		createSortedEnemyMoveLists();
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
	
	public void createSortedEnemyMoveLists() {
		List<Integer> currentList;
		for(List<Integer> list : enemyMoveLists) {
			Set<Integer> set = new HashSet<>(list);
			currentList = new ArrayList<>(set);
			Collections.sort(currentList);
			Collections.reverse(currentList);
			sortedEnemyMoveLists.add(currentList);
		}
	}
}
