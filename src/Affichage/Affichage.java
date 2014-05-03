package Affichage;



import org.lwjgl.*;
import org.lwjgl.input.Keyboard;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.nio.FloatBuffer;
import java.util.Date;
import java.util.GregorianCalendar;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

import Jeu.Balle;

import Jeu.Manitou;
import Jeu.Raquette;
import Jeu.TableDePingPong;
import moduleIG.ConfigData;
import moduleIntegration.SetRaquetteDecoGuiAffichage;
import Affichage.Texture;
import Affichage.TexturedVBO;

// cette classe contient des methodes qui vont etre appelees par GUI pour lui donner le d�cor et la raquette choisis 


@SuppressWarnings("unused")
public class Affichage implements SetRaquetteDecoGuiAffichage {
	
	private String chemin = "data\\ReproductionEXEC";
	private String message = "";
	private String nom_texture_deco="/ImagesGUI/jungle.jpg", nom_texture_raquette="/ImagesGUI/banane.jpg",nom_texture_balle="/Images/ball.png";
	GregorianCalendar calendar = new GregorianCalendar();
	Date time  = calendar.getTime();
	public static final int WIDTH=640, HEIGHT=480;
	
	private long lastFrame;
	


	
	
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
	//message = message+"matrice du descripteur envoy�e � Classif  " + time;
	
	//creer un constructeur permettant de cr�er un �cran d'affichage (il est utilis� par ex dans la classe fenetre
	//pour appeler ses methodes setDeco et setRaquette
	

	public Affichage(Manitou le_manitou){
		
		balleTex= new Texture(0,0,0);
		raquette1Tex = new Texture(0, 0, 0);
		raquette2Tex= new Texture(0, 0, 0);

	    manitou = le_manitou;
	  
		
		setRaquette(1);
		setDeco(1);
	
	
		//setTimer();
		
			 }
	
	
	//Gestion de l'Affichage
	  
	  private void setDisplay() {
		
	   try{
		 
	 
		Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
		Display.setTitle("Mental Pong");
		Display.create();
		
	      }catch (LWJGLException e){
		 
		    e.printStackTrace();
	      }
	 
	 }
	  
	  // Initialisation
	   private void initGL() {
		
		glMatrixMode(GL_PROJECTION);
	    glLoadIdentity();

//	    glMatrixMode(GL_MODELVIEW);
	    glOrtho(0, 640, 480, 0, 1, -1);
	    glViewport(0, 0, Display.getWidth(), Display.getHeight());

	    glClearColor(0.5f, 0.5f, 0.5f, 0.0f); // fen�tre de couleur grise
		glClearDepth(1.0f); // efface le buffer pour la vue en profondeur
		glDisable(GL_DEPTH_TEST); // Active le test de profondeur
		glDepthFunc(GL_LEQUAL); //d�finit le type de test � utiliser pour le test de profondeur
		
		
		float fovy = 45.0f;
		float aspect = DISPLAY_MODE.getWidth() / (float)DISPLAY_MODE.getHeight();
		float zNear = 0.1f;
		float zFar = 200.0f;
		GLU.gluPerspective(fovy, aspect, zNear, zFar);
		
		glMatrixMode(GL_MODELVIEW);
		
		//choisit la qualit� de couleur/texture la plus correcte
		glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); 
		
		
       // Variables et m�thodes pour l'�clairage
		
		initLightArrays();
		glShadeModel(GL_SMOOTH);
		// d�finit une couleur de mati�re r�fl�chissante
		glMaterial(GL_FRONT, GL_SPECULAR, matSpecular);	
		
		// d�finit la brillance
		glMaterialf(GL_FRONT, GL_SHININESS, 50.0f);					
		// d�finit la position de la lumi�re
		glLight(GL_LIGHT0, GL_POSITION, lightPosition);	
		// met la lumi�re r�fl�chissante en blanc
		glLight(GL_LIGHT0, GL_SPECULAR, whiteLight);
		// met la lumi�re diffuse en blanc
		glLight(GL_LIGHT0, GL_DIFFUSE, whiteLight);		
		// lumi�re ambiante globale
		glLightModel(GL_LIGHT_MODEL_AMBIENT, lModelAmbient);	
		// active l'�clairage
		glEnable(GL_LIGHTING);	
		// active la lumi�re n�1
		glEnable(GL_LIGHT0);										
		// permet � OpenGL d'utiliser glColor3f pour d�finir la couleur 
		glEnable(GL_COLOR_MATERIAL);		
		// d�finir les propri�t�s ambiantes et diffuses des fronts de polygones 
		glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);			


		
		// Chargement des textures
		
		
		
		raquette2Tex=raquette2Tex.loadTexture(nom_texture_raquette);
		balleTex=balleTex.loadTexture(nom_texture_balle);

		raquette1Tex=raquette1Tex.loadTexture(nom_texture_raquette);
		// Creation des VBOs
	
		raquette1VBO = TexturedVBO.loadTexturedVBO(raquette1Tex);
		raquette2VBO = TexturedVBO.loadTexturedVBO(raquette2Tex);
		balleVBO = TexturedVBO.loadTexturedVBO(balleTex);
		//Chargement del'arriere-plan et redimensionnement du VBO
		
		Texture arriereplan=new Texture(0, 0, 0) ;
		arriereplan=arriereplan.loadTexture(nom_texture_deco);
		arriereplanVBO = TexturedVBO.loadTexturedVBO(arriereplan);
		arriereplan.width = 815;
		arriereplan.height =600;


		
	  }
	   
	 //------- Ajout� pour le test d'�clairage----------//
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

		//l� il doit prendre la raquette choisie et l'adapter dans l'ecran
		
	  public void setRaquette(int choixRaquette) {
		// TODO Auto-generated method stub	
		
		
		 // nom_texture_raquette = "C:/Users/FATIMATA/workspace/PACT3.2/";
		  if (choixRaquette==2) nom_texture_raquette = "/ImagesGUI/raquetteSport.jpg"; 
		  if (choixRaquette==1) nom_texture_raquette = "/ImagesGUI/banane.jpg";
		  if (choixRaquette==3) nom_texture_raquette = "/ImagesGUI/requin.jpg";
		
	 }
	  // Le rendu � ma fen�tre de jeu
	  
	  private void render() {
		
	     
	      glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );
			glLoadIdentity(); 
			glTranslatef(0.0f, 0.0f, zTranslation);
		
			//  render de l'arriere-plan
			
			arriereplanVBO.render(100,100);

			//  render de la raquette
		
			Raquette raquetteP1 = manitou.getRaquetteP1();
			Raquette raquetteP2 = manitou.getRaquetteP2();
			Balle balle = manitou.getBalle();
			
			raquette1VBO.render(raquetteP1.getX()+120, -(raquetteP1.getY()));
			raquette2VBO.render(raquetteP2.getX()+190, -(raquetteP2.getY()));
	  
	        balleVBO.render(-(balle.getX())+150, -(balle.getY()));
			
			
//			glColor3f(1.0f, 0.0f, 1.0f);
//			Sphere balle1 = new Sphere();
//			balle1.draw(0.2f, 20, 16);
	   
	  
	  
	  }

	 public void setDeco(int choixDeco) {
		// TODO Auto-generated method stub
		
		//l� il doit prendre l'image du d�co et le mettre dans l'ecran
		
	 	if (choixDeco==3) nom_texture_deco = "/ImagesGUI/ocean.jpg";
		if (choixDeco==1) nom_texture_deco = "/ImagesGUI/jungle.jpg";
		if (choixDeco==2) nom_texture_deco = "/ImagesGUI/newYork.jpg";
		
	
	 }
	
	//apr�s il affiche l'ecran et le jeu commence 
	 
	 	

	 	// Affichage � la console de la position de la balle et l'etat de la raquette 
	
	
	
	 	public void play(){
	 		setDisplay();
	 		initGL();
	 		while(manitou.pause){
	 			
			
				render();
				Display.update();
			
				if(Display.isCloseRequested()){
					
					manitou.pause=false;
				}
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			Display.destroy();

	 	}
	 	
	 	public void traiterDonnees(ConfigData donnees){
	 		this.setRaquette(donnees.raquette);
	 		this.setDeco(donnees.decor);
	 	}
	 	

	
}
