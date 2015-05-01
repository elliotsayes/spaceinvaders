/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameScreen;

/**
 *
 * @author Owner
 */
public class EnemyBoss extends BasicEnemy {

    public EnemyBoss(int x, int y, int health, int size, String imageName, int fireC, int healthC, int speedC) {
        super(x, y, health, size, imageName, fireC, healthC, speedC);
    }
    @Override
     public boolean hit(int damage) {
        health -= damage;
      
        if (health == 0) {
            return (true);
        }
        return (false);
    }
}
