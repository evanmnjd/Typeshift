import java.util.Random;

import javafx.scene.image.Image;

public class Enemy {
	static Random rand = new Random();
	static Enemy randEnemy = new Enemy();
	String enemyName;
	String enemyPic;
	Image enemyImage;
	Enemy[] enemies = new Enemy[7];
	
	Enemy() {
		
	}
	
	public Enemy(String enemyName, String enemyPic) {
		this.enemyName = enemyName;
		this.enemyPic = enemyPic;
	}
	
	public Enemy(String enemyName, String enemyPic, Image enemyImage) {
		this.enemyName = enemyName;
		this.enemyPic = enemyPic;		//String location of image file, i.e. "/test.jpeg"
		this.enemyImage = enemyImage;	//Image object of the above string
	}
	
	public String getEnemyName(Enemy enemy) {
		return this.enemyName;
	}
	public String getEnemyPic(Enemy enemy) {
		return this.enemyPic;
	}
	public Image getEnemyImage(Enemy enemy) {
		return this.enemyImage;
	}
	
	public Image CreateImage(String enemyPic) { //Method to convert string into Image object
		Image enemyImage = new Image(enemyPic);
		return enemyImage;
	}
	
	public Enemy AddImage(Enemy enemy) {
		enemy.enemyImage = CreateImage(enemy.enemyPic);
		return enemy;
	}
	
	public Enemy[] PopulateEnemies() {
		int i;
		for (i = 0; i < 7; i++) {
			enemies[i] = new Enemy("enemy" + i, "/enemies/" + i + ".png");
			AddImage(enemies[i]);
		}
		return enemies;
	}
	
	public Enemy getRandomEnemy(Enemy[] enemies) {	//Find random enemy from the array and assign it the randEnemy variable
		randEnemy = enemies[rand.nextInt(6)];
		System.out.println("Random Enemy: " + randEnemy.enemyName);
		return randEnemy;
	}
	
	public static void main(String[] args) {
		
	}
}