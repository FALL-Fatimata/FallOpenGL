package Testeurs;


import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
			// TODO Auto-generated method stub
			
			if (e.getKeyCode()==KeyEvent.VK_DOWN){
				manitou.setDirectionP1(0);
				System.out.println(manitou.getRaquetteP1().getY());
			}
			if(e.getKeyCode()==KeyEvent.VK_UP){
				manitou.setDirectionP1(1);
				System.out.println(manitou.getRaquetteP1().getY());
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public void testManitou(){
		fenetre.addKeyListener(new monListener());
		fenetre.setSize(new Dimension(250, 250));
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
		
	}
	
	
	
}
