package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

// Me
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ResourceLoader;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    private static final Logger LOGGER = Logger.getLogger(Player.class.getName());

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        this.x = 100;
        this.y = 100;
        this.speed = 4;
        this.direction = "down";
    }


    public void getPlayerImage() {
        try {
            this.up1    = ResourceLoader.loadImage(Player.class, "/player/boy_up_1.png");
            this.up2    = ResourceLoader.loadImage(Player.class, "/player/boy_up_2.png");
            this.down1  = ResourceLoader.loadImage(Player.class, "/player/boy_down_1.png");
            this.down2  = ResourceLoader.loadImage(Player.class, "/player/boy_down_2.png");
            this.left1  = ResourceLoader.loadImage(Player.class, "/player/boy_left_1.png");
            this.left2  = ResourceLoader.loadImage(Player.class, "/player/boy_left_2.png");
            this.right1 = ResourceLoader.loadImage(Player.class, "/player/boy_right_1.png");
            this.right2 = ResourceLoader.loadImage(Player.class, "/player/boy_right_2.png");
        } catch (IOException | IllegalStateException e) {
            LOGGER.log(Level.SEVERE, "Erro ao carregar sprites do Player", e);
        }
    }

    public void update(){
        if(keyH.downPressed || keyH.upPressed || keyH.leftPressed || keyH.rightPressed){
            if (keyH.upPressed){
                this.direction = "up";
                this.y -= this.speed;
            }
            else if (keyH.downPressed){
                this.direction = "down";
                this.y += this.speed;
            }
            else if (keyH.leftPressed){
                this.direction = "left";
                this.x -= this.speed;
            }
            else if (keyH.rightPressed){
                this.direction = "right";
                this.x += this.speed;
            }

            spriteCounter++;
            if (spriteCounter > 12){
                if (spriteNum == 1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2){
//      g2.setColor(Color.WHITE);
//      g2.fillRect(this.x, this.y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction){
            case "up":
                if (spriteNum == 1){
                    image = this.up1;
                }
                if (spriteNum == 2){
                    image = this.up2;
                }
                break;
            case "down":
                if (spriteNum == 1){
                    image = this.down1;
                }
                if (spriteNum == 2){
                    image = this.down2;
                }
                break;
            case "left":
                if (spriteNum == 1){
                    image = this.left1;
                }
                if (spriteNum == 2){
                    image = this.left2;
                }
                break;
            case "right":
                if (spriteNum == 1){
                    image = this.right1;
                }
                if (spriteNum == 2){
                    image = this.right2;
                }
                break;
        }
        g2.drawImage(image, this.x, this.y, gp.tileSize, gp.tileSize, null);
    }
}
