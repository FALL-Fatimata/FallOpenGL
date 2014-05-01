package moduleIG;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Affichage.Affichage;
import Jeu.Manitou;
import Testeurs.TestThread2;

public class FenetreDemarrage {

	private  JFrame fenetre=new JFrame("Menu Principal- MENTAL PONG");
	private Manitou manitou;
	
	private TestThread2 t2;
	private ConfigData donnees=new ConfigData();
	private  JPanel panneauPrinc=(JPanel) this.fenetre.getContentPane();
	private FenetreConfig configurations = new FenetreConfig(this);
	public void show(){
		this.getReady();
		this.fenetre.pack();
		this.fenetre.setLocationRelativeTo(null);
		this.fenetre.setVisible(true);
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private Affichage pong ;
	public FenetreDemarrage(Manitou manitou,Affichage pong){
		this.manitou=manitou;
			this.pong=pong;
			
			t2 = new TestThread2(pong);

	}

	private void getReady(){
		panneauPrinc.setLayout(new FlowLayout());
		JButton jouer = new JButton("Jouer");

		jouer.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				
				
				pong.play();
				close();
				FenetreFin window = new FenetreFin(manitou,donnees);
				window.show();
				
			}
		});
		jouer.setIcon(new ImageIcon(getClass().getResource("/ImagesGUI/play.png")));
		panneauPrinc.add(jouer);
		JButton config = new JButton("Configurer");
		config.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				configurations=new FenetreConfig(getThis());
				configurations.show();
				
			}
		});
		config.setIcon(new ImageIcon(getClass().getResource("/ImagesGUI/settings.png")));
		panneauPrinc.add(config);
		JButton aide = new JButton("Aide");
		aide.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				JOptionPane.showMessageDialog(fenetre, "Vous avez ouvert la fenetre d'aide.","Aide", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		aide.setIcon(new ImageIcon(getClass().getResource("/ImagesGUI/help.png")));
		panneauPrinc.add(aide);
	}

	public void traiterDonnees(ConfigData donnees){
		System.out.println(donnees.toString());
		this.donnees=donnees;
		manitou.traiterDonnees(donnees);
		pong.traiterDonnees(donnees);
		

	}
	
	public FenetreDemarrage getThis(){
		return this;
	}
	public void close(){
		fenetre.setVisible(false);
		fenetre.dispose();
	}

}
