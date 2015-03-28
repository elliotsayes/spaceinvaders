/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameScreen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BasicPlayer {

    // co-ordinates of player

    int x;
    int y;

    // player size
    int height;
    int width;

    int fireRate;
    boolean move_left, move_right;
    Color color;
    int score;
    int health;
    BulletHandler bullets = new BulletHandler(-1, fireRate);

    // Unused
    //int xa;
    //ImageIcon image;
    // Player constructor
    public BasicPlayer() {
        // call default constructor with default width and height
        this(60, 10);
    }

    public BasicPlayer(int width, int height) {
        this.width = width;
        this.height = height;

        // initialise other values
        x = 400;
        y = 460;
        fireRate = 1000;
        color = Color.GREEN;
        health = 30;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // Moves Player as well as bullets
    public void move(JPanel win) {
        if (move_left && (x - 1 > 0 && x - 1 < win.getWidth() - width)) {
            x += -1;
        }
        if (move_right && (x + 1 > 0 && x + 1 < win.getWidth() - width)) {
            x += 1;
        }
        bullets.move();
        bullets.kill();
    }

    // Paints player and bullets, determins look of player
    public void paint(Graphics2D g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        bullets.paint(g);
        g.setColor(Color.WHITE);
        g.drawString("Score:", 20, 20);
        g.drawString(String.valueOf(score), 100, 20);
        g.drawString("Health:", 200, 20);
        g.drawString(String.valueOf(health), 280, 20);
    }

    // Player mechanics
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            move_left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            move_right = false;
        }
    }

    // Player mechanics
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) //xa = -1;
        {
            move_left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) //xa = 1;
        {
            move_right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bullets.spawnMissile(x + 30, y);
        }
    }

    // Returns bullets array
    public ArrayList getAmo() {
        return bullets.getbullets();
    }

    // Removes bullet and increases player score
    public void hit(int i) {
        bullets.remove(i);
        score = score + 100;

    }

    public int gety() {
        return y;
    }

    public int getx() {
        return x;
    }

    int health() {
        return health;
    }

    void playerHit() {
        health -= 1;
        locationRespawn();
    }

    public void locationRespawn() {
        x = 400;
        y = 460;
    }
}
