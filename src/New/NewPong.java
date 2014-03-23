package New;

import org.lwjgl.*;
import org.lwjgl.input.Keyboard;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


public class NewPong {

	public static final int WIDTH=640, HEIGHT=480;
	private boolean isRunning=true;
	private long lastFrame;
	private Balle balle;
	private Raquette raquet1,raquet2;


	  public NewPong(){
		
		
		setDisplay();
		initGL();
		setObjects();
		setTimer();
		

		
		while(isRunning){
			
			render();
			logic(getDelta());
			Display.update();
			input();
		
			
			if(Display.isCloseRequested()){
				
				isRunning=false;
			}
			
		}
		
		Display.destroy();
		
	  }
	
	  private void initGL() {
		
		glMatrixMode(GL_PROJECTION);
		glClearColor(0.5f, 0.5f, 0.5f, 0.0f);
		glLoadIdentity();
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glOrtho(0,640,480,0,1,-1);
		glMatrixMode(GL_MODELVIEW);
		  
		
		glShadeModel(GL_SMOOTH);
		glMaterialf(GL_FRONT, GL_SHININESS, 50.0f);				
		glEnable(GL_LIGHTING);										
		glEnable(GL_LIGHT0);											
		glEnable(GL_COLOR_MATERIAL);								
		glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE);			
		
	  }

	  //Gestion de l'Affichage
	  
	private void setDisplay() {
		
	 try{
		 
	 
		Display.setDisplayMode(new DisplayMode(WIDTH,HEIGHT));
		Display.setTitle("NewPong");
		Display.create();
		
	 }catch (LWJGLException e){
		 
		 e.printStackTrace();
	 }
	 
	}

	// Creation d'une classe Raquette
	
	private static class Raquette extends MovingObjects{
		
		public Raquette (double x, double y, double width, double height){
			
			super(x,y,width,height);
		}
		
		@Override 
		
		public void draw(){
			
			glRectd(x,y,x+width,y+height);
		}
	}

	// Creation d'une classe Balle
	
	  private static class Balle extends MovingObjects{
		
		 public Balle(double x, double y, double width, double height){
			
			super(x,y,width,height);
		}
		
		@Override 
		
		public void draw(){
			
			glRectd(x,y,x+width,y+height);
		 }
	  }

	  private void setObjects() {
			
		  // Dimensions des objets
		  
		    balle=new Balle(WIDTH/2-10/2,HEIGHT/2-10/2,10,10);
			raquet1=new Raquette(10,HEIGHT/2-80,10,200); 
			raquet2=new Raquette(630,HEIGHT/2-80,10,200);
			
			
			// Direction et Vélocité de la balle
			
			balle.setDX(-0.0003);
			
		}

	  
	   private void render() {
		
	      glClear(GL_COLOR_BUFFER_BIT);
	      glColor3f(1.0f,0.0f,0.0f);         // Balle rouge 
		  balle.draw();
		  glColor3f(0.0f,0.0f,1.0f);          // Raquette Bleu
		  raquet1.draw();
		  glColor3f(1.0f,0.0f,1.0f);          // Raquette Magenta
		  raquet2.draw();
		
	    }

// Controle du mouvement des raquettes à l'aide des touches du clavier
	    
	private void input() {
		
	if (Keyboard.isKeyDown(Keyboard.KEY_UP)){
		raquet1.setDY(-0.0005);
		raquet2.setDY(-0.0005);
	} else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
		raquet1.setDY(0.0005);
		raquet2.setDY(0.0005);
	} else {
		raquet1.setDY(0);
		raquet2.setDY(0);
	}
	
}

	// Recuperation du temps du systeme en millisecondes
	
	private long getTime(){
		
		return(Sys.getTime()*1000)/Sys.getTimerResolution();
	}
	

	private void setTimer() {
		lastFrame=getTime();
	}

// Retourne le temps écoulé entre 2 instants donnés

   private int getDelta() {
	
	int currentTime= (int) getTime();
	int delta=(int)(currentTime-lastFrame);
	lastFrame=getTime();
	return delta;
	
  }

  // logique du jeu

   private void logic(int delta) {

	balle.update(delta);
	raquet1.update(delta);
	raquet2.update(delta);
	
	if(balle.getX()<=raquet1.getX()+raquet1.getWidth() && balle.getX()>=raquet1.getX() && 
			balle.getY()>=raquet1.getY() && balle.getY()<=raquet1.getY()+raquet1.getHeight()){
		
		balle.setDX(0.1);
	}
	
	if(balle.getX()<=raquet2.getX()+raquet2.getWidth() && balle.getX()>=raquet2.getX() 
			&& balle.getY()>=raquet2.getY() && balle.getY()<=raquet2.getY()+raquet2.getHeight()){
		
		    
		balle.setDX(0.1);
	  }
    }



	public static void main(String[] args){
		
		new NewPong();
	}
	
	
 }
