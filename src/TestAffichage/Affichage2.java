package TestAffichage;



import org.lwjgl.*;
import org.lwjgl.input.Keyboard;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.awt.Font;
import java.nio.FloatBuffer;
import java.util.Date;
import java.util.GregorianCalendar;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import Jeu.Balle;
//import Jeu.DecorInterface;
import Jeu.Manitou;
import Jeu.Raquette;
import Jeu.TableDePingPong;
import moduleIntegration.SetRaquetteDecoGuiAffichage;
import Affichage.Texture;
import Affichage.TexturedVBO;

// cette classe contient des methodes qui vont etre appelees par GUI pour lui donner le décor et la raquette choisis 


@SuppressWarnings("unused")
public class Affichage2 implements SetRaquetteDecoGuiAffichage {

	private String chemin = "data\\ReproductionEXEC";
	private String message = "";
	private String nom_texture_deco="", nom_texture_raquette="",nom_texture_balle="Images/ball.png";
	GregorianCalendar calendar = new GregorianCalendar();
	Date time  = calendar.getTime();
	public static final int WIDTH=640, HEIGHT=480;
	private boolean isRunning=true;
	private long lastFrame;

//	private DecorInterface decor=new TableDePingPong(10,6,0);
	private int speed=1;
	private double alea=Math.random()*Math.PI*2;

	private float zTranslation = -2f;
	private static final DisplayMode DISPLAY_MODE = new DisplayMode(WIDTH, HEIGHT);
	//----------- Variables added for Lighting Test -----------//
	private FloatBuffer matSpecular;
	private FloatBuffer lightPosition;
	private FloatBuffer whiteLight; 
	private FloatBuffer lModelAmbient;

	 // The textures
    Texture balleTex, raquette1Tex, raquette2Tex;

    // The VBOs
    TexturedVBO balleVBO, raquette1VBO, raquette2VBO, arriereplanVBO;

    // Gestionnaire  
    Manitou manitou;
	//message = message+"matrice du descripteur envoyée à Classif  " + time;

	private TrueTypeFont font;

	
	private boolean antiAlias = true;


	public Affichage2(Manitou le_manitou){

	    manitou = le_manitou;
		setDisplay();
		setRaquette(1);
		setDeco(1);
		initGL();
		initText();
		
		//setTimer();

		while(isRunning){

			render();
			
			Display.update();

			if(Display.isCloseRequested()){

				isRunning=false;
			}

		}

		Display.destroy();
	 }


	//Gestion de l'Affichage

	  private void setDisplay() {

	   try{


		Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
		Display.setTitle("Pong");
		Display.create();

	      }catch (LWJGLException e){

		    e.printStackTrace();
	      }

	 }

	  // Initialisation
	   private void initGL() {

		glMatrixMode(GL_PROJECTION);
		glClearColor(0.5f, 0.5f, 0.5f, 0.0f);
		glLoadIdentity();
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glOrtho(0,640,480,0,1,-1);
		glClearColor(0.5f, 0.5f, 0.5f, 0.0f); // fenêtre de couleur grise
		glClearDepth(1.0f); // efface le buffer pour la vue en profondeur
		glDisable(GL_DEPTH_TEST); // Active le test de profondeur
		glDepthFunc(GL_LEQUAL); //définit le type de test à utiliser pour le test de profondeur


		float fovy = 45.0f;
		float aspect = DISPLAY_MODE.getWidth() / (float)DISPLAY_MODE.getHeight();
		float zNear = 0.1f;
		float zFar = 200.0f;
		GLU.gluPerspective(fovy, aspect, zNear, zFar);

		glMatrixMode(GL_MODELVIEW);

		//choisit la qualité de couleur/texture la plus correcte
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); 


       // Variables et méthodes pour l'éclairage

		initLightArrays();
		glShadeModel(GL_SMOOTH);
		// définit une couleur de matière réfléchissante
		glMaterial(GL_FRONT, GL_SPECULAR, matSpecular);	

		// définit la brillance
		glMaterialf(GL_FRONT, GL_SHININESS, 50.0f);					
		// définit la position de la lumière
		glLight(GL_LIGHT0, GL_POSITION, lightPosition);	
		// met la lumière réfléchissante en blanc
		glLight(GL_LIGHT0, GL_SPECULAR, whiteLight);
		// met la lumière diffuse en blanc
		glLight(GL_LIGHT0, GL_DIFFUSE, whiteLight);		
		// lumière ambiante globale
		glLightModel(GL_LIGHT_MODEL_AMBIENT, lModelAmbient);	
		// active l'éclairage
		glEnable(GL_LIGHTING);	
		// active la lumière n°1
		glEnable(GL_LIGHT0);										
		// permet à OpenGL d'utiliser glColor3f pour définir la couleur 
		glEnable(GL_COLOR_MATERIAL);		
		// définir les propriétés ambiantes et diffuses des fronts de polygones 
		glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);			


		// Chargement des textures


		raquette1Tex = Texture.loadTexture(nom_texture_raquette);
		raquette2Tex = Texture.loadTexture(nom_texture_raquette);
		balleTex = Texture.loadTexture(nom_texture_balle);

		// Creation des VBOs

		raquette1VBO = TexturedVBO.loadTexturedVBO(raquette1Tex);
		raquette2VBO = TexturedVBO.loadTexturedVBO(raquette2Tex);
		balleVBO = TexturedVBO.loadTexturedVBO(balleTex);
		//Chargement del'arriere-plan et redimensionnement du VBO

		Texture arriereplan = Texture.loadTexture(nom_texture_deco);
		arriereplanVBO = TexturedVBO.loadTexturedVBO(arriereplan);
		arriereplan.width = 800;
		arriereplan.height =600;



	  }

	 //------- Ajouté pour le test d'éclairage----------//
		private void initLightArrays() {

			matSpecular = BufferUtils.createFloatBuffer(4);
			matSpecular.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();

			lightPosition = BufferUtils.createFloatBuffer(4);
			lightPosition.put(1.0f).put(1.0f).put(1.0f).put(0.0f).flip();

			whiteLight = BufferUtils.createFloatBuffer(4);
			whiteLight.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();

			lModelAmbient = BufferUtils.createFloatBuffer(4);
			lModelAmbient.put(0.5f).put(0.5f).put(0.5f).put(1.0f).flip();

		}

		public void initText() {
			// load a default java font
			Font awtFont = new Font("Times New Roman", Font.BOLD, 16);
			font = new TrueTypeFont(awtFont, antiAlias);
			
		
		}
	 
		//là il doit prendre la raquette choisie et l'adapter dans l'ecran

	  public void setRaquette(int choixRaquette) {
		// TODO Auto-generated method stub	


		 // nom_texture_raquette = "C:/Users/FATIMATA/workspace/PACT3.2/src/ImagesGUI/";
		  if (choixRaquette==1) nom_texture_raquette = "Images/raquetteSport.jpg"; 
		  if (choixRaquette==2) nom_texture_raquette = "Images/banane.jpg";
		  if (choixRaquette==3) nom_texture_raquette = "Images/raquetteSport.jpg";
		  if (choixRaquette==4) nom_texture_raquette = "Images/banane.jpg";

	 }
	  // Le rendu à ma fenêtre de jeu

	  private void render() {


	      glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );
			glLoadIdentity(); 
			glTranslatef(0.0f, 0.0f, zTranslation);

			//  render de l'arriere-plan

			arriereplanVBO.render(100,100, 0);

			//  render de la raquette

			raquette1VBO.render(manitou.getRaquetteP1().getX()-150, manitou.getRaquetteP1().getY(), manitou.getRaquetteP1().getZ());
			raquette2VBO.render(manitou.getRaquetteP2().getX()+450, manitou.getRaquetteP2().getY(), manitou.getRaquetteP2().getZ());

	        balleVBO.render(manitou.getBalle().getX(), manitou.getBalle().getY(), manitou.getBalle().getZ());

	        //glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );
	       // glLoadIdentity(); 
	        
	      //  Color.white.bind();
	        
			//font.drawString(10, 5, "Joueur 1", Color.yellow);
			//font.drawString(10, 10, "getP1Score()", Color.yellow);
			//font.drawString(730, 5, "Joueur 2", Color.green);
			
			//font.drawString(10, 5, "getP2Score()", Color.yellow);

		//	glColor3f(1.0f, 0.0f, 1.0f);
		//	Sphere balle = new Sphere();
		//	balle.draw(0.2f, 20, 16);



	  }

	 public void setDeco(int choixDeco) {
		// TODO Auto-generated method stub

		//là il doit prendre l'image du déco et le mettre dans l'ecran

	 	if (choixDeco==1) nom_texture_deco = "Images/ocean.jpg";
		if (choixDeco==2) nom_texture_deco = "jungle.jpg";
		if (choixDeco==3) nom_texture_deco = "newYork.jpg";
		if (choixDeco==4) nom_texture_deco = "pelouse.jpg";


	 }

	//après il affiche l'ecran et le jeu commence 

	 	public static void main(String[] args){

		    Manitou manitou = new Manitou();
		  
			new Affichage2(manitou);

	 	}

	 	// Affichage à la console de la position de la balle et l'etat de la raquette 

	 	public void afficher(){

		  Manitou manitou = new Manitou();
		  Balle balle = manitou.getBalle();
		  System.out.println(balle.getX()+"  "+balle.getY()+"  "+balle.getZ()+"  ");
		  manitou.getRaquetteP1();
		  System.out.println("j'ai reçu la raquette");


	 	}



}