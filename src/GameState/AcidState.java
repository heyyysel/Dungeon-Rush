package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Handlers.Keys;
import main.GamePanel;

public class AcidState extends GameState {
	
	private float hue;
	private Color color;
	
	private double angle;
	private BufferedImage image;
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	private Font font2;
	
	public AcidState(GameStateManager gsm) {
		super(gsm);
		try {
			image = ImageIO.read(
			getClass().getResourceAsStream(
			"/Sprites/Player/PlayerSprites.gif"
			)).getSubimage(0, 0, 40, 40);
			
			// titles and fonts
			titleColor = Color.WHITE;
			titleFont = new Font("Times New Roman", Font.PLAIN, 28);
			font = new Font("Arial", Font.PLAIN, 12);
			font2 = new Font("Arial", Font.PLAIN, 9);
		}
		catch(Exception e) {}
	}
	
	public void init() {}
	
	public void update() {
		handleInput();
		color = Color.getHSBColor(hue, 1f, 1f);
		hue += 0.01;
		if(hue > 1) hue = 0;
		angle += 0.1;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		AffineTransform at = new AffineTransform();
		at.translate(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2);
		at.rotate(angle);
		g.drawImage(image, at, null);
		
		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("D U N G E O N", 70, 90);
		g.drawString("R u s h", 120, 120);
				
		// draw menu options
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Thank You for playing", 100, 165);
		g.drawString("Press 'ESC' to continue.", 100, 185);
		
		// credit screen
		g.setFont(font2);
		g.drawString("Credits to: ", 10, 232);
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) gsm.setState(GameStateManager.MENUSTATE);
	}

}
