package Testeurs;


import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import Jeu.Manitou;

public class DirectionGiver {

	private Manitou manitou=new Manitou();
	private JFrame fenetre = new JFrame("Test");

	public DirectionGiver(final Manitou manitou){
		this.manitou=manitou;	
	}

	class monListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {


			if (e.getKeyCode()==KeyEvent.VK_DOWN){
				manitou.setDirectionP1(0);


			}
			if(e.getKeyCode()==KeyEvent.VK_UP){
				manitou.setDirectionP1(1);

			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent e) {


		}

	}
	public void testManitou(){
		fenetre.addKeyListener(new monListener());
		fenetre.setSize(new Dimension(250, 250));
		fenetre.setLocation(0,60);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);


	}



}
