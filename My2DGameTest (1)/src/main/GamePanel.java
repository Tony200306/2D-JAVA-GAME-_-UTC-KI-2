package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	// Cai dat man hinh
	final int originalTileSize = 16; //16x16
	final int scale = 3;//3
	
	public final int tileSize = originalTileSize * scale; //48x48
	public final int maxScreenCol = 25;//16
	public final int maxScreenRow = 14;//12
	public final int ScreenWidth = tileSize * maxScreenCol ; //768
	public final int ScreenHeight = tileSize * maxScreenRow ; //576
	
	//WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	public static boolean isGameEnd = false;
	public static void setGameEnd(boolean gameEnd) {
		isGameEnd = gameEnd;
	};
	public  static boolean youDead = false ;
	public static void setYouDead(boolean a) {
		youDead = a ;
	}
	
	//
	public String getTimeNow(){
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
		String formattedDate = myDateObj.format(myFormatObj);
		return formattedDate;
	}

	public String getDateNow(){
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = myDateObj.format(myFormatObj);
		return formattedDate;
	}
	private String BeginningTime = null ;
	private String PlayDay = getDateNow() ;
	private String FinishingTime;
	
	//FPS
	int FPS = 60;
	
	
	//System
	public TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this);
	Sound sound = new Sound();
    Thread gameThread;     
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[20];
	
	
	//player default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	//game state
	public int gameState;
	public final int playState=1;
	public final int pauseState=2;
	
	public GamePanel()	{
		
		this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);// draw in buffer
		this.addKeyListener(keyH);
		this.setFocusable(true);	//GamePanel focused to receive key input
		BeginningTime = getTimeNow() ;
	}
	
	public void setupGame() {
		
		aSetter.setObject();
		
		playMusic(1);
		gameState = playState;
	}
	
	public void startGameThread() {   
		
		setupGame();
		gameThread = new Thread(this);  // new Thread(this)
		gameThread.start();
	}
	
	
	
	
//	public void restartGame() {
//	    // reset game state
//	    // for example:
//	    score = 0;
//	    lives = 3;
//	    // reset game objects
//	    // for example:
//	    player.reset();
//	    enemies.reset();
//	    // start game again
//	    startGame();
//	    
//	}
	
//	public class Player {
//	    // player attributes
//	    private int x;
//	    private int y;
//	    // ...
//
//	    public void reset() {
//	        // reset player attributes to initial values
//	        x = startX;
//	        y = startY;
//	        // ...
//	    }
//	}
	
//	public void startGame() {
//	    // initialize game state
//	    // for example:
//	    score = 0;
//	    lives = 3;
//	    // initialize game objects
//	    // for example:
//	    player = new Player();
//	    enemies = new Enemies();
//	    // start game loop
//	    while (true) {
//	        // update game state
//	        updateGameState();
//	        // render game
//	        renderGame();
//	        // check for end of game
//	        if (isGameOver()) {
//	            break;
//	        }
//	    }

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; //FPS
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			
			//System.out.println("the game is running");
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			
			lastTime = currentTime;
			
			if(delta >= 1) {
				try {
					update();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				repaint();
				delta --;
			}
		}
	}
	
	public void update() throws InterruptedException {
		
		//
		if (isGameEnd) {
			System.out.println(PlayDay.replace(":","-"));
			int i =0 ;
			System.out.println(i);
			FinishingTime = getTimeNow() ;
			String TIME0FPLAYINGGAME = Time.Time_Handling(BeginningTime ,FinishingTime);
			System.out.println(TIME0FPLAYINGGAME);
			Import_to_DB();		//import to DataBase
			gameThread = null;
			GameOverPanel endPanel = new GameOverPanel("You WIN");

//			gameThread = null;
//			JFrame endPanel = new JFrame();	//
//			endPanel.setVisible(true);
//			GameOverPanel endPanel = new GameOverPanel();
		}
		
		if (youDead){
			GameOverPanel endPanel = new GameOverPanel("YOU ARE DEAD");
			playSE(5);
			gameThread = null ;
		}
		if(gameThread == null){
			stopMusic();
			//System.out.println("Sound STOP!!");
		}

		
		System.out.println("Tong Coin: " + TileManager.coinCounting );
		System.out.println("An duoc: " + CollisionChecker.cout_step_coin);
		if(gameState == playState)
		{
			player.update();
		}
		if(gameState == pauseState)
		{
			//do nothing
		}
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);

		//obj[0].draw(g2, this);
		
//		for (int i=0; i < obj.length; i++) {
//			if( obj[i] != null)
//				 obj[i].draw(g2, this);
//		}
		
		player.draw(g2);
		
		ui.draw(g2);
		
		g2.dispose();
	}
	
	public void playMusic(int i) {
		sound.setFile(i);
		sound.play();
		sound.loop();
	}
	
	public void stopMusic() {
		sound.stop();
	}
	
	public void playSE(int i) {
		sound.setFile(i);
		sound.play();
	}
	
	public void Import_to_DB () throws InterruptedException {
		try {
			//Tạo kết nối
			Connection connection = DataBase.getConnection();
			// Statement
			Statement statement = connection.createStatement();
			String sql = "    UPDATE `highscore` SET `Time`=' "+ PlayDay +" ',`HighScore`=" + Time.getTime() + " WHERE Player= '"+ Login.user+"'; " ;
			statement.executeUpdate(sql);

			//CLOSE
			DataBase.closeConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
