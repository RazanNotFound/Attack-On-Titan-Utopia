package game.gui;

import game.engine.*;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidCSVFormat;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.ColossalTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application{
	
	Group startRoot = new Group();
	Group gameOverRoot = new Group();
	Group weaponRoot = new Group();	
	Group instructionsRoot = new Group();
	Group difficultyRoot = new Group();
	Group titanRoot = new Group();
	Group easyGameRoot = new Group();
	Group hardGameRoot = new Group();
	Group gameRoot;
	
	Scene gameOverScene;
	Scene startScene;
	Scene weaponShopScene;
	Scene instructionsScene;
	Scene titanInfoScene;
	Scene difficultyScene;
	Scene easyGameScene;
	Scene hardGameScene;
	Scene game;
	
	private int setLane;
	private int spawnLane;
	private int titanCode;
	private int[] laneCoordinate = {103, 270, 465, 649, 748};
	MediaPlayer introMusicPlayer;
	
	private Battle battle;
	@Override
	public void start(Stage stage){
		//Battle battle;
		
		
		Label resourcesLabel = new Label();
		Label scoreLabel = new Label();
		Label resourcesLabelE = new Label();
		Label phaseLabelE = new Label();
		Label turnLabelE = new Label();
		Label resourcesLabelH = new Label();
		Label scoreLabelH = new Label();
		Label turnLabelH = new Label();
		Label phaseLabelH = new Label();
		
		
		
		
			Image gameIcon = new Image("AOT_ICON.jpg");
			stage.getIcons().add(gameIcon);
			stage.setHeight(1000);
			stage.setWidth(1000);
			stage.setTitle("AOT UTOPIA");
			stage.setMaximized(true);
			stage.setResizable(false);
			
			//_____________________________________________________________________________________________________________________________			
			
			
			//START ROOT 
		
			String musicFile = "/introMusic.mp3";
			Media media = new Media(getClass().getResource(musicFile).toExternalForm());
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			StackPane root = new StackPane();
			root.getChildren().add(new MediaView(mediaPlayer));
			mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
			mediaPlayer.play();
			
			String musicFile2 = "/gameMusic.mp3";
			Media media2 = new Media(getClass().getResource(musicFile2).toExternalForm());
			MediaPlayer mediaPlayer2 = new MediaPlayer(media2);
			StackPane root2 = new StackPane();
			root2.getChildren().add(new MediaView(mediaPlayer2));
			mediaPlayer2.setCycleCount(MediaPlayer.INDEFINITE);
			
			
		    //background image
			Image img2 = new Image("bgaot.png");
			startRoot.getChildren().add(new ImageView(img2));

			//start button
			Button startButton = new Button("START");
			startButton.setTranslateX(788.4);
			startButton.setTranslateY(521);
			startButton.setPrefSize(360, 109);
			startButton.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			startButton.setOnAction(e -> stage.setScene(difficultyScene));
			startRoot.getChildren().add(startButton);
			
			//instructions button			
			Button instructionsButton = new Button("instructions");
			instructionsButton.setTranslateX(788.4);
			instructionsButton.setTranslateY(664);
			instructionsButton.setPrefSize(360, 109);
			instructionsButton.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			instructionsButton.setOnAction(e -> stage.setScene(instructionsScene));
			startRoot.getChildren().add(instructionsButton);
			
			//quit Button
			Button quitButton = new Button("Quit");
			quitButton.setTranslateX(788.4);
			quitButton.setTranslateY(807.1);
			quitButton.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			quitButton.setOnAction(e -> stage.close());
			quitButton.setPrefSize(360, 109);
			startRoot.getChildren().add(quitButton);

			//_____________________________________________________________________________________________________________________________
			
			
			//INSTRUCTIONS ROOT
			
			Image inst1 = new Image("inst1.png");
			instructionsRoot.getChildren().add(new ImageView(inst1));

			
			Button menuButton = new Button("menu");
			menuButton.setTranslateX(347.8);
			menuButton.setTranslateY(886.9);
			menuButton.setPrefSize(248.8, 74.6);
			menuButton.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			menuButton.setOnAction(e -> stage.setScene(startScene));
			instructionsRoot.getChildren().add(menuButton);
			
			Button nextButton = new Button("next");
			nextButton.setTranslateX(1374.4);
			nextButton.setTranslateY(886.9);
			nextButton.setPrefSize(248.8, 74.6);
			nextButton.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			nextButton.setOnAction(e -> stage.setScene(titanInfoScene));
			instructionsRoot.getChildren().add(nextButton); 
			
			//_____________________________________________________________________________________________________________________________
			
			
			//TITAN ROOT
			
			Image inst2 = new Image("inst2.png");
			titanRoot.getChildren().add(new ImageView(inst2));
			
			//back button
			Button backButton = new Button("back");
			backButton.setTranslateX(347.8);
			backButton.setTranslateY(886.9);
			backButton.setPrefSize(248.8, 74.6);
			backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			backButton.setOnAction(e -> stage.setScene(instructionsScene));
			titanRoot.getChildren().add(backButton); 
			
			//_____________________________________________________________________________________________________________________________
			
			
			//DIFFICULTY TOOT
			
			Image easyGame = new Image("easyLane.png"); //easyLane.png
			easyGameRoot.getChildren().add(new ImageView(easyGame));
			
			Image difficulty = new Image("difficulty.png");
			difficultyRoot.getChildren().add(new ImageView(difficulty));
			
			Button easyButton = new Button("easy");
			easyButton.setTranslateX(588.5);
			easyButton.setTranslateY(339.2);
			easyButton.setPrefSize(591.6, 177.5);
			easyButton.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			easyButton.setOnAction(e -> {
				mediaPlayer2.play();
				mediaPlayer.stop();
				
			    stage.setScene(easyGameScene);
			    game = easyGameScene;
			    gameRoot = easyGameRoot;
			    try {
					battle = new Battle(1,0,182,3,250);
				} catch (IOException e2) {
					e2.printStackTrace();
					if(e2 instanceof InvalidCSVFormat)
						errorMessage("Error occured", "An error occured while starting the game");
				}
			    resourcesLabel.setText(String.valueOf(battle.getResourcesGathered()));
				scoreLabel.setText(String.valueOf(battle.getScore()));
				resourcesLabelE.setText(String.valueOf(battle.getResourcesGathered()));
				phaseLabelE.setText(String.valueOf(battle.getBattlePhase()));
				turnLabelE.setText(String.valueOf(battle.getNumberOfTurns()));
				resourcesLabelH.setText(String.valueOf(battle.getResourcesGathered()));
				scoreLabelH.setText(String.valueOf(battle.getScore()));
				turnLabelH.setText(String.valueOf(battle.getNumberOfTurns()));
				phaseLabelH.setText(String.valueOf(battle.getBattlePhase()));
			});
			difficultyRoot.getChildren().add(easyButton); 
			
			Button hardButton = new Button("hard");
			hardButton.setTranslateX(588.5);
			hardButton.setTranslateY(579);
			hardButton.setPrefSize(591.6, 177.5);
			hardButton.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			hardButton.setOnAction(e -> {
			    stage.setScene(hardGameScene);
				mediaPlayer2.play();
				mediaPlayer.stop();
			    game = hardGameScene;
			    gameRoot = hardGameRoot;
			    try {
					battle = new Battle(1,0,182,5,125);
				} catch (IOException e2) {
					e2.printStackTrace();
					if(e2 instanceof InvalidCSVFormat)
						errorMessage("Error occured", "An error occured while starting the game");
				}
			    resourcesLabel.setText(String.valueOf(battle.getResourcesGathered()));
				scoreLabel.setText(String.valueOf(battle.getScore()));
				resourcesLabelE.setText(String.valueOf(battle.getResourcesGathered()));
				phaseLabelE.setText(String.valueOf(battle.getBattlePhase()));
				turnLabelE.setText(String.valueOf(battle.getNumberOfTurns()));
				resourcesLabelH.setText(String.valueOf(battle.getResourcesGathered()));
				scoreLabelH.setText(String.valueOf(battle.getScore()));
				turnLabelH.setText(String.valueOf(battle.getNumberOfTurns()));
				phaseLabelH.setText(String.valueOf(battle.getBattlePhase()));
			});
			difficultyRoot.getChildren().add(hardButton);
			
			//_____________________________________________________________________________________________________________________________
			
			
			//TITAN IMAGES

				   Image Titan1 = new Image("pureTitan.png");
			       ImageView pureTitan = new ImageView(Titan1);
			       pureTitan.setFitWidth(97);
			       pureTitan.setFitHeight(145.5);
			       pureTitan.setUserData("titan");
			       
			       Image Titan2 = new Image("abnormalTitan.png");
			       ImageView abnormalTitan = new ImageView(Titan2);
			       abnormalTitan.setFitWidth(92.7);
			       abnormalTitan.setFitHeight(132.2);
			       abnormalTitan.setUserData("titan");
			       
			       Image Titan3 = new Image("armoredTitan.png");
			       ImageView armoredTitan = new ImageView(Titan3);
			       armoredTitan.setFitWidth(97);
			       armoredTitan.setFitHeight(145.5);
			       armoredTitan.setUserData("titan");
			       
			       Image Titan4 = new Image("colossalTitan.png");
			       ImageView colossalTitan = new ImageView(Titan4);
			       colossalTitan.setFitWidth(85.3);
			       colossalTitan.setFitHeight(156.4);
			       colossalTitan.setUserData("titan");
			       
			       Image[] titanImages = {Titan1, Titan2, Titan3, Titan4};
	        
	        
	        
	      //_____________________________________________________________________________________________________________________________
	        //WEAPONSHOP ROOT
			
			Image weaponShop = new Image("weaponShop.png"); //easyLane.png
			weaponRoot.getChildren().add(new ImageView(weaponShop));
			
			//piercing cannon buy button
			
			Button buyPcButton = new Button("piercing cannon");
			buyPcButton.setTranslateX(747.8);
			buyPcButton.setTranslateY(421.1);
			buyPcButton.setPrefSize(181, 54.3);
			buyPcButton.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			buyPcButton.setOnAction(e ->{
				
				if(battle.isGameOver())
					stage.setScene(gameOverScene);
				
			try {
				battle.purchaseWeapon(1, battle.getOriginalLanes().get(setLane-1));
				 Image image1 = new Image("piercingCannon.png");
			        ImageView piercingCannon = new ImageView(image1);
			        piercingCannon.setFitWidth(170.3);
			        piercingCannon.setFitHeight(56.8);
			        piercingCannon.setTranslateX(83.7);
			        piercingCannon.setUserData("weapon");
			        
				if (setLane ==1){	
					piercingCannon.setTranslateY(119.9);
					gameRoot.getChildren().add(piercingCannon);
				}
				if (setLane ==2){
					piercingCannon.setTranslateY(305.7);
					gameRoot.getChildren().add(piercingCannon);
				}
				if (setLane ==3){				
					piercingCannon.setTranslateY(486.8);
					gameRoot.getChildren().add(piercingCannon);
				}
				if (setLane ==4){				
					piercingCannon.setTranslateY(672.1);
					gameRoot.getChildren().add(piercingCannon);
				}
				if (setLane ==5){					
					piercingCannon.setTranslateY(860.7);
					gameRoot.getChildren().add(piercingCannon);
				}
				Titan t = battle.getApproachingTitans().get(0);
				if(t instanceof PureTitan)
					titanCode = 1;
				if(t instanceof AbnormalTitan)
					titanCode = 2;
				if(t instanceof ArmoredTitan)
					titanCode = 3;
				if(t instanceof ColossalTitan)
					titanCode = 4;
				ImageView currentTitan = new ImageView(titanImages[titanCode-1]);
				currentTitan.setUserData("titan");
				
				List<Node> children = easyGameRoot.getChildren();
				for (int i = children.size() - 1; i >= 0; i--) {
			        Node node = children.get(i);
			        if (node.getTranslateX()>50 && node instanceof ImageView && node.getUserData() != null && node.getUserData().equals("titan")) {
			        	node.setTranslateX(node.getTranslateX()-t.getSpeed()*10);
			        }
			    }
				
				List<Node> childrenH = hardGameRoot.getChildren();
				for (int i = childrenH.size() - 1; i >= 0; i--) {
			        Node node = childrenH.get(i);
			        if (node.getTranslateX()>50 && node instanceof ImageView && node.getUserData() != null && node.getUserData().equals("titan")) {
			        	node.setTranslateX(node.getTranslateX()-t.getSpeed()*10);
			        }
			    }
				
				for(int i=0; i<battle.getLanes().size(); i++){
					if(battle.getLanes().peek().equals(battle.getOriginalLanes().get(i)))
						spawnLane=i;
				}
				
				resourcesLabel.setText(String.valueOf(battle.getResourcesGathered()));
				scoreLabel.setText(String.valueOf(battle.getScore()));
				resourcesLabelE.setText(String.valueOf(battle.getResourcesGathered()));
				phaseLabelE.setText(String.valueOf(battle.getBattlePhase()));
				turnLabelE.setText(String.valueOf(battle.getNumberOfTurns()));
				resourcesLabelH.setText(String.valueOf(battle.getResourcesGathered()));
				scoreLabelH.setText(String.valueOf(battle.getScore()));
				turnLabelH.setText(String.valueOf(battle.getNumberOfTurns()));
				phaseLabelH.setText(String.valueOf(battle.getBattlePhase()));

				currentTitan.setTranslateX(1822);
				currentTitan.setTranslateY(laneCoordinate[spawnLane]);
				gameRoot.getChildren().add(currentTitan);
				easyGameRoot.getChildren().add(currentTitan);
				hardGameRoot.getChildren().add(currentTitan);
			} catch (Exception e1) {
				
				// TODO Auto-generated catch block
				e1.printStackTrace();
				if(e1 instanceof InsufficientResourcesException)
					errorMessage("Cannot buy weapon", "You have insufficient resources"+battle.getResourcesGathered());
				else if(e1 instanceof InvalidLaneException)
					errorMessage("Cannot buy weapon", "Invalid lane chosen");
			}
				stage.setScene(game); }
			);
			weaponRoot.getChildren().add(buyPcButton); 
			
			//sniper cannon buy button
			
			Button buyScButton = new Button("sniper cannon");
			buyScButton.setTranslateX(108);
			buyScButton.setTranslateY(826.4);
			buyScButton.setPrefSize(181, 54.3);
			buyScButton.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			buyScButton.setOnAction(e ->{
				
				if(battle.isGameOver())
					stage.setScene(gameOverScene);
				
				try {
					battle.purchaseWeapon(1, battle.getOriginalLanes().get(setLane-1));
					 Image image2 = new Image("sniperCannon.png");
				        ImageView sniperCannon = new ImageView(image2);
				        sniperCannon.setFitWidth(178.6);
				        sniperCannon.setFitHeight(54.4);
						sniperCannon.setTranslateX(83.7);
						sniperCannon.setUserData("weapon");
					if (setLane ==1){
					//ImageView  pIV= new ImageView("bgaot.png");	

						sniperCannon.setTranslateY(77);
						gameRoot.getChildren().add(sniperCannon);
					}
					if (setLane ==2){
						sniperCannon.setTranslateY(262.7);
						gameRoot.getChildren().add(sniperCannon);
					}
					if (setLane ==3){
						sniperCannon.setTranslateY(443.9);
						gameRoot.getChildren().add(sniperCannon);
					}
					if (setLane ==4){
						sniperCannon.setTranslateY(629.1);
						gameRoot.getChildren().add(sniperCannon);
					}
					if (setLane ==5){
						sniperCannon.setTranslateY(817.7);
						gameRoot.getChildren().add(sniperCannon);
					}
					Titan t = battle.getApproachingTitans().get(0);
					if(t instanceof PureTitan)
						titanCode = 1;
					if(t instanceof AbnormalTitan)
						titanCode = 2;
					if(t instanceof ArmoredTitan)
						titanCode = 3;
					if(t instanceof ColossalTitan)
						titanCode = 4;
					ImageView currentTitan = new ImageView(titanImages[titanCode-1]);
					currentTitan.setUserData("titan");
					
					List<Node> children = easyGameRoot.getChildren();
					for (int i = children.size() - 1; i >= 0; i--) {
				        Node node = children.get(i);
				        if (node.getTranslateX()>50 && node instanceof ImageView && node.getUserData() != null && node.getUserData().equals("titan")) {
				        	node.setTranslateX(node.getTranslateX()-t.getSpeed()*10);
				        }
				    }
					
					List<Node> childrenH = hardGameRoot.getChildren();
					for (int i = childrenH.size() - 1; i >= 0; i--) {
				        Node node = childrenH.get(i);
				        if (node.getTranslateX()>50 && node instanceof ImageView && node.getUserData() != null && node.getUserData().equals("titan")) {
				        	node.setTranslateX(node.getTranslateX()-t.getSpeed()*10);
				        }
				    }
					
					for(int i=0; i<battle.getLanes().size(); i++){
						if(battle.getLanes().peek().equals(battle.getOriginalLanes().get(i)))
							spawnLane=i;
					}
					
					resourcesLabel.setText(String.valueOf(battle.getResourcesGathered()));
					scoreLabel.setText(String.valueOf(battle.getScore()));
					resourcesLabelE.setText(String.valueOf(battle.getResourcesGathered()));
					phaseLabelE.setText(String.valueOf(battle.getBattlePhase()));
					turnLabelE.setText(String.valueOf(battle.getNumberOfTurns()));
					resourcesLabelH.setText(String.valueOf(battle.getResourcesGathered()));
					scoreLabelH.setText(String.valueOf(battle.getScore()));
					turnLabelH.setText(String.valueOf(battle.getNumberOfTurns()));
					phaseLabelH.setText(String.valueOf(battle.getBattlePhase()));

					currentTitan.setTranslateX(1822);
					currentTitan.setTranslateY(laneCoordinate[spawnLane]);
					gameRoot.getChildren().add(currentTitan);
					easyGameRoot.getChildren().add(currentTitan);
					hardGameRoot.getChildren().add(currentTitan);
				} catch (Exception e1) {
					
					// TODO Auto-generated catch block
					e1.printStackTrace();
					if(e1 instanceof InsufficientResourcesException)
						errorMessage("Cannot buy weapon", "You have insufficient resources"+battle.getResourcesGathered());
					else if(e1 instanceof InvalidLaneException)
						errorMessage("Cannot buy weapon", "Invalid lane chosen");
				}
					
				
				
				stage.setScene(game);}
				);
				
			weaponRoot.getChildren().add(buyScButton); 
			
			//wall trap buy button
			
			Button buyWtButton = new Button("Wall Trap");
			buyWtButton.setTranslateX(108);
			buyWtButton.setTranslateY(445.3);
			buyWtButton.setPrefSize(181, 54.3);
			buyWtButton.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			buyWtButton.setOnAction(e ->{
				
				if(battle.isGameOver())
					stage.setScene(gameOverScene);
				
				try {
					battle.purchaseWeapon(1, battle.getOriginalLanes().get(setLane-1));
					 Image image3 = new Image("wallTrap.png");
				        ImageView wallTrap = new ImageView(image3);
				        wallTrap.setFitWidth(170.3);
				        wallTrap.setFitHeight(56.8);
				        wallTrap.setTranslateX(83.7);
				        wallTrap.setUserData("weapon");
				        
					if (setLane ==1){	
						wallTrap.setTranslateY(162.1);
						gameRoot.getChildren().add(wallTrap);
					}
					if (setLane ==2){
						wallTrap.setTranslateY(347.9);
						gameRoot.getChildren().add(wallTrap);
					}
					if (setLane ==3){				
						wallTrap.setTranslateY(529.1);
						gameRoot.getChildren().add(wallTrap);
					}
					if (setLane ==4){				
						wallTrap.setTranslateY(714.3);
						gameRoot.getChildren().add(wallTrap);
					}
					if (setLane ==5){					
						wallTrap.setTranslateY(902.9);
						gameRoot.getChildren().add(wallTrap);
					}
					Titan t = battle.getApproachingTitans().get(0);
					if(t instanceof PureTitan)
						titanCode = 1;
					if(t instanceof AbnormalTitan)
						titanCode = 2;
					if(t instanceof ArmoredTitan)
						titanCode = 3;
					if(t instanceof ColossalTitan)
						titanCode = 4;
					ImageView currentTitan = new ImageView(titanImages[titanCode-1]);
					currentTitan.setUserData("titan");
					
					List<Node> children = easyGameRoot.getChildren();
					for (int i = children.size() - 1; i >= 0; i--) {
				        Node node = children.get(i);
				        if (node.getTranslateX()>86.5 && node instanceof ImageView && node.getUserData() != null && node.getUserData().equals("titan")) {
				        	node.setTranslateX(node.getTranslateX()-t.getSpeed()*10);
				        }
				    }
					
					List<Node> childrenH = hardGameRoot.getChildren();
					for (int i = childrenH.size() - 1; i >= 0; i--) {
				        Node node = childrenH.get(i);
				        if (node.getTranslateX()>50 && node instanceof ImageView && node.getUserData() != null && node.getUserData().equals("titan")) {
				        	node.setTranslateX(node.getTranslateX()-t.getSpeed()*10);
				        }
				    }
					
					for(int i=0; i<battle.getLanes().size(); i++){
						if(battle.getLanes().peek().equals(battle.getOriginalLanes().get(i)))
							spawnLane=i;
					}
					
					resourcesLabel.setText(String.valueOf(battle.getResourcesGathered()));
					scoreLabel.setText(String.valueOf(battle.getScore()));
					resourcesLabelE.setText(String.valueOf(battle.getResourcesGathered()));
					phaseLabelE.setText(String.valueOf(battle.getBattlePhase()));
					turnLabelE.setText(String.valueOf(battle.getNumberOfTurns()));
					resourcesLabelH.setText(String.valueOf(battle.getResourcesGathered()));
					scoreLabelH.setText(String.valueOf(battle.getScore()));
					turnLabelH.setText(String.valueOf(battle.getNumberOfTurns()));
					phaseLabelH.setText(String.valueOf(battle.getBattlePhase()));

					currentTitan.setTranslateX(1822);
					currentTitan.setTranslateY(laneCoordinate[spawnLane]);
					gameRoot.getChildren().add(currentTitan);
					easyGameRoot.getChildren().add(currentTitan);
					hardGameRoot.getChildren().add(currentTitan);
				} catch (Exception e1) {
					
					// TODO Auto-generated catch block
					e1.printStackTrace();
					if(e1 instanceof InsufficientResourcesException)
						errorMessage("Cannot buy weapon", "You have insufficient resources"+battle.getResourcesGathered());
					else if(e1 instanceof InvalidLaneException)
						errorMessage("Cannot buy weapon", "Invalid lane chosen");
				}
					
				
				
				stage.setScene(game);}
				);
			weaponRoot.getChildren().add(buyWtButton); 
			
			//volley spread buy button
			
			Button buyVsButton = new Button("volley spread");
			buyVsButton.setTranslateX(732.8);
			buyVsButton.setTranslateY(826.4);
			buyVsButton.setPrefSize(181, 54.3);
			buyVsButton.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			buyVsButton.setOnAction(e ->{
				
				if(battle.isGameOver())
					stage.setScene(gameOverScene);
				
				try {
					battle.purchaseWeapon(1, battle.getOriginalLanes().get(setLane-1));
					 Image image4 = new Image("volleySpread.png");
				        ImageView volleySpread = new ImageView(image4);
				        volleySpread.setFitWidth(170.3);
				        volleySpread.setFitHeight(56.8);
				        volleySpread.setTranslateX(83.7);
				        volleySpread.setUserData("weapon");
				        
					if (setLane ==1){	
						volleySpread.setTranslateY(206.4);
						gameRoot.getChildren().add(volleySpread);
					}
					if (setLane ==2){
						volleySpread.setTranslateY(392.1);
						gameRoot.getChildren().add(volleySpread);
					}
					if (setLane ==3){				
						volleySpread.setTranslateY(573.3);
						gameRoot.getChildren().add(volleySpread);
					}
					if (setLane ==4){				
						volleySpread.setTranslateY(758.6);
						gameRoot.getChildren().add(volleySpread);
					}
					if (setLane ==5){					
						volleySpread.setTranslateY(947.2);
						gameRoot.getChildren().add(volleySpread);
					}
					Titan t = battle.getApproachingTitans().get(0);
					if(t instanceof PureTitan)
						titanCode = 1;
					if(t instanceof AbnormalTitan)
						titanCode = 2;
					if(t instanceof ArmoredTitan)
						titanCode = 3;
					if(t instanceof ColossalTitan)
						titanCode = 4;
					ImageView currentTitan = new ImageView(titanImages[titanCode-1]);
					currentTitan.setUserData("titan");
					
					List<Node> children = easyGameRoot.getChildren();
					for (int i = children.size() - 1; i >= 0; i--) {
				        Node node = children.get(i);
				        if (node.getTranslateX()>50 && node instanceof ImageView && node.getUserData() != null && node.getUserData().equals("titan")) {
				        	node.setTranslateX(node.getTranslateX()-t.getSpeed()*10);
				        }
				    }
					
					List<Node> childrenH = hardGameRoot.getChildren();
					for (int i = childrenH.size() - 1; i >= 0; i--) {
				        Node node = childrenH.get(i);
				        if (node.getTranslateX()>50 && node instanceof ImageView && node.getUserData() != null && node.getUserData().equals("titan")) {
				        	node.setTranslateX(node.getTranslateX()-t.getSpeed()*10);
				        }
				    }
					
					for(int i=0; i<battle.getLanes().size(); i++){
						if(battle.getLanes().peek().equals(battle.getOriginalLanes().get(i)))
							spawnLane=i;
					}
					
					resourcesLabel.setText(String.valueOf(battle.getResourcesGathered()));
					scoreLabel.setText(String.valueOf(battle.getScore()));
					resourcesLabelE.setText(String.valueOf(battle.getResourcesGathered()));
					phaseLabelE.setText(String.valueOf(battle.getBattlePhase()));
					turnLabelE.setText(String.valueOf(battle.getNumberOfTurns()));
					resourcesLabelH.setText(String.valueOf(battle.getResourcesGathered()));
					scoreLabelH.setText(String.valueOf(battle.getScore()));
					turnLabelH.setText(String.valueOf(battle.getNumberOfTurns()));
					phaseLabelH.setText(String.valueOf(battle.getBattlePhase()));

					currentTitan.setTranslateX(1822);
					currentTitan.setTranslateY(laneCoordinate[spawnLane]);
					gameRoot.getChildren().add(currentTitan);
					easyGameRoot.getChildren().add(currentTitan);
					hardGameRoot.getChildren().add(currentTitan);
				} catch (Exception e1) {
					
					// TODO Auto-generated catch block
					e1.printStackTrace();
					if(e1 instanceof InsufficientResourcesException)
						errorMessage("Cannot buy weapon", "You have insufficient resources"+battle.getResourcesGathered());
					else if(e1 instanceof InvalidLaneException)
						errorMessage("Cannot buy weapon", "Invalid lane chosen");
				}
					
				
				
				stage.setScene(game);}
				);
			weaponRoot.getChildren().add(buyVsButton); 
			
			// done button in weapon shop
			
			Button doneBuyButton = new Button("done");
			doneBuyButton.setTranslateX(1453.6);
			doneBuyButton.setTranslateY(269.6);
			doneBuyButton.setPrefSize(335.2, 100.6);
			doneBuyButton.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			doneBuyButton.setOnAction(e -> stage.setScene(game));
			weaponRoot.getChildren().add(doneBuyButton); 
			
			
			
			resourcesLabel.setTranslateX(580.5);
			resourcesLabel.setTranslateY(120.2);
			resourcesLabel.setFont(new Font("Georgia", 30));
			resourcesLabel.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD , 30));
			weaponRoot.getChildren().add(resourcesLabel);
			
			//_____________________________________________________________________________________________________________________________
			
			
			//EASY GAME ROOT

			
			//exit button
			
			Button exitGameButtonE = new Button("exit");
			exitGameButtonE.setTranslateX(10.6);
			exitGameButtonE.setTranslateY(14.7);
			exitGameButtonE.setPrefSize(111.1, 33.3);
			exitGameButtonE.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			exitGameButtonE.setOnAction(e -> {
				mediaPlayer.play();
				mediaPlayer2.stop();
			    List<Node> children = easyGameRoot.getChildren();
			    for (int i = children.size() - 1; i >= 0; i--) {
			        Node node = children.get(i);
			        if (node instanceof ImageView && node.getUserData() != null && (node.getUserData().equals("weapon") || node.getUserData().equals("titan"))) {
			            easyGameRoot.getChildren().remove(node);
			        }
			    }
			    stage.setScene(startScene);
			});
			easyGameRoot.getChildren().add(exitGameButtonE);
			
			
			//pass turn button
			
			Button passTurnButtonE = new Button("pass turn");
			passTurnButtonE.setTranslateX(796.6);
			passTurnButtonE.setTranslateY(11.9);
			passTurnButtonE.setPrefSize(1643.5, 4);
			passTurnButtonE.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			passTurnButtonE.setOnAction(e -> {
				
				if(battle.isGameOver())
					stage.setScene(gameOverScene);
				
				battle.passTurn();

				Titan t = battle.getApproachingTitans().get(0);
				if(t instanceof PureTitan)
					titanCode = 1;
				if(t instanceof AbnormalTitan)
					titanCode = 2;
				if(t instanceof ArmoredTitan)
					titanCode = 3;
				if(t instanceof ColossalTitan)
					titanCode = 4;
				ImageView currentTitan = new ImageView(titanImages[titanCode-1]);
				currentTitan.setUserData("titan");
				
				List<Node> children = easyGameRoot.getChildren();
				for (int i = children.size() - 1; i >= 0; i--) {
			        Node node = children.get(i);
			        if (node.getTranslateX()>50 && node instanceof ImageView && node.getUserData() != null && node.getUserData().equals("titan")) {
			        	node.setTranslateX(node.getTranslateX()-t.getSpeed()*10);
			        }
			    }
				
				for(int i=0; i<battle.getLanes().size(); i++){
					if(battle.getLanes().peek().equals(battle.getOriginalLanes().get(i)))
						spawnLane=i;
				}
				
				resourcesLabel.setText(String.valueOf(battle.getResourcesGathered()));
				scoreLabel.setText(String.valueOf(battle.getScore()));
				resourcesLabelE.setText(String.valueOf(battle.getResourcesGathered()));
				phaseLabelE.setText(String.valueOf(battle.getBattlePhase()));
				turnLabelE.setText(String.valueOf(battle.getNumberOfTurns()));
				resourcesLabelH.setText(String.valueOf(battle.getResourcesGathered()));
				scoreLabelH.setText(String.valueOf(battle.getScore()));
				turnLabelH.setText(String.valueOf(battle.getNumberOfTurns()));
				phaseLabelH.setText(String.valueOf(battle.getBattlePhase()));
				
				currentTitan.setTranslateX(1822);
				currentTitan.setTranslateY(laneCoordinate[spawnLane]);
				gameRoot.getChildren().add(currentTitan);
				easyGameRoot.getChildren().add(currentTitan);
			});
			easyGameRoot.getChildren().add(passTurnButtonE);

			
			//score label ((EASY))
			
			
			
			scoreLabel.setTranslateX(1776.3);
			scoreLabel.setTranslateY(1015.4);
			scoreLabel.setFont(new Font("Georgia", 30));
			scoreLabel.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD , 30));
			easyGameRoot.getChildren().add(scoreLabel);

			
			// resources label ((EASY))
			
			
			
			resourcesLabelE.setTranslateX(1441);
			resourcesLabelE.setTranslateY(1015.9);
			resourcesLabelE.setFont(new Font("Georgia", 30));
			resourcesLabelE.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD , 30));
			easyGameRoot.getChildren().add(resourcesLabelE);

			
			// phase label ((EASY))
			
			
			
			phaseLabelE.setTranslateX(822);
			phaseLabelE.setTranslateY(1015.4);
			phaseLabelE.setFont(new Font("Georgia", 30));
			phaseLabelE.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD , 30));
			easyGameRoot.getChildren().add(phaseLabelE);
			
			
			//turn label ((EASY))
			
			
			
			turnLabelE.setTranslateX(392.1);
			turnLabelE.setTranslateY(1015.4);
			turnLabelE.setFont(new Font("Georgia", 30));
			turnLabelE.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD , 30));
			easyGameRoot.getChildren().add(turnLabelE);
			
			//lane 1 ((EASY))
			
			Button lane1E = new Button("Lane1");
			lane1E.setTranslateX(293);
			lane1E.setTranslateY(11.9);
			lane1E.setPrefSize(183.7, 55.1);
			lane1E.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			lane1E.setOnAction(e -> {
				setLane=1;
				stage.setScene(weaponShopScene);}
			);
			easyGameRoot.getChildren().add(lane1E);
			
			
			//lane 2 ((EASY))
			
			Button lane2E = new Button("Lane2");
			lane2E.setTranslateX(544.8);
			lane2E.setTranslateY(11.9);
			lane2E.setPrefSize(183.7, 55.1);
			lane2E.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			lane2E.setOnAction(e -> {
				setLane=2;
				stage.setScene(weaponShopScene);}
				);
			easyGameRoot.getChildren().add(lane2E);
			
			
			//lane 3 ((EASY))
			
			Button lane3E = new Button("lane 3");
			lane3E.setTranslateX(796.6);
			lane3E.setTranslateY(11.9);
			lane3E.setPrefSize(183.7, 55.1);
			lane3E.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			lane3E.setOnAction(e -> {
				setLane=3;
				stage.setScene(weaponShopScene);}
				);
			easyGameRoot.getChildren().add(lane3E);
			
			
			//_____________________________________________________________________________________________________________________________
			
			
			//HARD GAME ROOT



			Image hardGame = new Image("hardLane.png"); //easyLane.png
			hardGameRoot.getChildren().add(new ImageView(hardGame));
			
			
			Button passTurnButtonH = new Button("pass turn");
			passTurnButtonH.setTranslateX(796.6);
			passTurnButtonH.setTranslateY(11.9);
			passTurnButtonH.setPrefSize(1643.5, 4);
			passTurnButtonH.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			passTurnButtonH.setOnAction(e -> {
				
				if(battle.isGameOver())
					stage.setScene(gameOverScene);
				
				battle.passTurn();
				
				Titan t = battle.getApproachingTitans().get(0);
				if(t instanceof PureTitan)
					titanCode = 1;
				if(t instanceof AbnormalTitan)
					titanCode = 2;
				if(t instanceof ArmoredTitan)
					titanCode = 3;
				if(t instanceof ColossalTitan)
					titanCode = 4;
				ImageView currentTitan = new ImageView(titanImages[titanCode-1]);
				currentTitan.setUserData("titan");
				
				List<Node> childrenH = hardGameRoot.getChildren();
				for (int i = childrenH.size() - 1; i >= 0; i--) {
			        Node node = childrenH.get(i);
			        if (node.getTranslateX()>50 && node instanceof ImageView && node.getUserData() != null && node.getUserData().equals("titan")) {
			        	node.setTranslateX(node.getTranslateX()-t.getSpeed()*10);
			        }
			    }
				
				for(int i=0; i<battle.getLanes().size(); i++){
					if(battle.getLanes().peek().equals(battle.getOriginalLanes().get(i)))
						spawnLane=i;
				}
				
				resourcesLabel.setText(String.valueOf(battle.getResourcesGathered()));
				scoreLabel.setText(String.valueOf(battle.getScore()));
				resourcesLabelE.setText(String.valueOf(battle.getResourcesGathered()));
				phaseLabelE.setText(String.valueOf(battle.getBattlePhase()));
				turnLabelE.setText(String.valueOf(battle.getNumberOfTurns()));
				resourcesLabelH.setText(String.valueOf(battle.getResourcesGathered()));
				scoreLabelH.setText(String.valueOf(battle.getScore()));
				turnLabelH.setText(String.valueOf(battle.getNumberOfTurns()));
				phaseLabelH.setText(String.valueOf(battle.getBattlePhase()));
				
				currentTitan.setTranslateX(1822);
				currentTitan.setTranslateY(laneCoordinate[spawnLane]);
				gameRoot.getChildren().add(currentTitan);
				hardGameRoot.getChildren().add(currentTitan);
			});
			hardGameRoot.getChildren().add(passTurnButtonH);
			

			
			
			resourcesLabelH.setTranslateX(1441);
			resourcesLabelH.setTranslateY(1015.9);
			resourcesLabelH.setFont(new Font("Georgia", 30));
			resourcesLabelH.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD , 30));
			hardGameRoot.getChildren().add(resourcesLabelH);
			
			
			
			scoreLabelH.setTranslateX(1776.3);
			scoreLabelH.setTranslateY(1015.4);
			scoreLabelH.setFont(new Font("Georgia", 30));
			scoreLabelH.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD , 30));
			hardGameRoot.getChildren().add(scoreLabelH);
			
			
			//turn label ((HARD))
			
			
			
			turnLabelH.setTranslateX(392.1);
			turnLabelH.setTranslateY(1015.4);
			turnLabelH.setFont(new Font("Georgia", 30));
			turnLabelH.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD , 30));
			hardGameRoot.getChildren().add(turnLabelH);
			
			
			Button exitGameButtonH = new Button("exit");
			exitGameButtonH.setTranslateX(10.6);
			exitGameButtonH.setTranslateY(14.7);
			exitGameButtonH.setPrefSize(111.1, 33.3);
			exitGameButtonH.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			exitGameButtonH.setOnAction(e -> {
				mediaPlayer.play();
				mediaPlayer2.stop();
			    List<Node> children = hardGameRoot.getChildren();
			    for (int i = children.size() - 1; i >= 0; i--) {
			        Node node = children.get(i);
			        if (node instanceof ImageView && node.getUserData() != null && (node.getUserData().equals("weapon") || node.getUserData().equals("titan"))){
			        	hardGameRoot.getChildren().remove(node);
			        }
			    }
			    stage.setScene(startScene);
			});
			hardGameRoot.getChildren().add(exitGameButtonH);		
			
			
			
			
			phaseLabelH.setTranslateX(822);
			phaseLabelH.setTranslateY(1015.4);
			phaseLabelH.setFont(new Font("Georgia", 30));
			phaseLabelH.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD , 30));
			hardGameRoot.getChildren().add(phaseLabelH);
			
			//	HARD LANES
			
			
			//lane 1 ((HARD))
			
			Button lane1H = new Button("Lane1");
			lane1H.setTranslateX(293);
			lane1H.setTranslateY(11.9);
			lane1H.setPrefSize(183.7, 55.1);
			lane1H.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			lane1H.setOnAction(e -> {
			setLane=1;
			stage.setScene(weaponShopScene);}
			);
			hardGameRoot.getChildren().add(lane1H);
			
			
			//lane 2 ((HARD))
			
			Button lane2H = new Button("Lane2");
			lane2H.setTranslateX(544.8);
			lane2H.setTranslateY(11.9);
			lane2H.setPrefSize(183.7, 55.1);
			lane2H.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			lane2H.setOnAction(e -> {
				setLane=2;
				stage.setScene(weaponShopScene);}
				);
			hardGameRoot.getChildren().add(lane2H);
			
			
			//lane 3 ((HARD))
			
			Button lane3H = new Button("lane 3");
			lane3H.setTranslateX(796.6);
			lane3H.setTranslateY(11.9);
			lane3H.setPrefSize(183.7, 55.1);
			lane3H.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			lane3H.setOnAction(e -> {
				setLane=3;
				stage.setScene(weaponShopScene);}
				);
			hardGameRoot.getChildren().add(lane3H);
			
			
			//lane 4 ((HARD))
			
			Button lane4 = new Button("lane 4");
			lane4.setTranslateX(1048.4);
			lane4.setTranslateY(11.9);
			lane4.setPrefSize(183.7, 55.1);
			lane4.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			lane4.setOnAction(e -> {
				setLane=4;
				stage.setScene(weaponShopScene);}
				);
			hardGameRoot.getChildren().add(lane4);
			
			
			//lane 5 ((HARD))
			
			Button lane5 = new Button("lane 4");
			lane5.setTranslateX(1300.3);
			lane5.setTranslateY(11.9);
			lane5.setPrefSize(183.7, 55.1);
			lane5.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			lane5.setOnAction(e -> {
				setLane=5;
				stage.setScene(weaponShopScene);}
				);
			hardGameRoot.getChildren().add(lane5);
			
			
			//_____________________________________________________________________________________________________________________________
			
			
			
			//GAMEOVER ROOT
			
			Image gameov = new Image("gameOver.png");
			gameOverRoot.getChildren().add(new ImageView(gameov));
			
			//gameover button
			Button gameOverButton = new Button();
			gameOverButton.setText("Game Over");
			gameOverButton.setTranslateX(760.1);
			gameOverButton.setTranslateY(936.4);
			gameOverButton.setPrefSize(399.9, 120);
			gameOverButton.setStyle("-fx-background-color: transparent; -fx-text-fill: transparent;");
			gameOverButton.setOnAction(e -> stage.setScene(startScene));
			gameOverRoot.getChildren().add(gameOverButton);
			
			hardGameScene = new Scene(hardGameRoot);
			easyGameScene = new Scene(easyGameRoot); 
			difficultyScene = new Scene(difficultyRoot);
			weaponShopScene = new Scene(weaponRoot);
			titanInfoScene = new Scene(titanRoot);			
			gameOverScene = new Scene(gameOverRoot);
			instructionsScene = new Scene(instructionsRoot);
			startScene = new Scene(startRoot);
			
			
			stage.setScene(startScene);
			stage.show();
			

	}
	
	private void errorMessage(String title, String message){
		Stage stage = new Stage();
		stage.setHeight(150);
		stage.setWidth(500);
		stage.setTitle(title);
		stage.setResizable(false);
		Group root = new Group();
		
		Label label = new Label(message);
		label.setTranslateY(10);
		label.setTranslateY(30);
		label.setFont(new Font(20));
		root.getChildren().add(label);
		
		Scene scene = new Scene(root);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args){
		launch(args);
	}
}