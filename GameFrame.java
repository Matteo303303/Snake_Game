import javax.swing.JFrame;

public class GameFrame extends JFrame {
	GameFrame(){ 
		GamePanel panel=new GamePanel();
		//COMANDI AL JFRAME  //imposto i settaggi della finestra
		this.add(panel); //Aggiungo a Jframe il panel
		this.setTitle("Snake");//Metto il titolo
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//azione quando utente clicca l' "X" della finestra
		this.setResizable(false); //imposto che la finestra non può essere ridimensionata
		this.pack();//Da delle dimensioni alla finestra in base ai componenti
		this.setVisible(true);//rende visibile la finestra
		this.setLocationRelativeTo(null);//finestra appare al centro dello schermo
		
		
		
		
	}

}
