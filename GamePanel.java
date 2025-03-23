import javax.swing.*;///importa JPanel
import java.awt.*;//importa i graphics di awt di java
import java.awt.event.*;//importa tutti gli event di awt di java(ActionEvent,ActionListener,KeyAdapter,Keyevent)
import java.util.Random;//serve per restituire un numero casuale (int,double etc...)

public class GamePanel extends JPanel implements ActionListener{
	
	//variabili static posso accedervi senza istanziare un oggetto
	static final int SCREEN_WIDTH=600;
	static final int SCREEN_HEIGHT=600;
	static final int UNIT_SIZE=25;//un unità del gioco è 25 pixel
	static final int GAME_UNITS=(SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY=75; //tempo prima dell' avvio del timer
	//MATRICE DEL SERPENTE
	final int x[]=new int[GAME_UNITS];//array di interi che ha come dimensione massima la cost GAME_UNITS
	final int y[]=new int[GAME_UNITS];
	int bodyParts=6; //grandezza iniziale serpente
	int applesEaten;
	int appleX; //coordinate della mela
	int appleY;
	char direction='R';
	boolean running = false;//si definisce false per buona prassi dichiararlo true non influirà nell' esecuzione
	Timer timer;
	Random random;

	
	GamePanel(){                      //public di default //this si riferisce alla finestra 
		random=new Random();//istanzio un oggetto di tipo random
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));//istanzio un oggetto temporaneo con i valori dati e lo passo al metodo setpreferredsize
		this.setBackground(Color.black);
		this.setFocusable(true);//mette in focus la pagina che si utilizza
		this.addKeyListener(new MyKeyAdapter());//dice al programma di ascoltare la pressione dei tasti della tastiera
		startGame(); 
		
	}
	public void startGame() {
		newApple();
		running=true; //dice allo snake di muoversi
		timer= new Timer(DELAY,this); //al costruttore Timer passo il delay prima dell' avvio e il riferimento alla classe GamePanel
		timer.start();  //parte il timer
	}
	public void paintComponent(Graphics g) {//il metodo si aspetta come parametro un' istanza g della classe Graphics
		super.paintComponent(g);//chiamo il metodo della classe padre JPanel
		draw(g);
		
	}
	public void draw(Graphics g) {
		
		if(running) {
			//DISEGNO CAMPO
			/*for (int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++) {
				g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,SCREEN_HEIGHT); //(X1,Y1,X2,Y2)//linee verticali
				g.drawLine(0,i*UNIT_SIZE,SCREEN_WIDTH,i*UNIT_SIZE);//linee orizzontali	
			}*/
			//DISEGNO MELA
			g.setColor(Color.red);//color ha attributi di tipo statico quindi non è necessario istanziare l' oggetto
			g.fillOval(appleX,appleY,UNIT_SIZE,UNIT_SIZE); //(x,y,width,height)
			//DISEGNO SERPENTE
			for(int i=0; i<bodyParts;i++) {	 //testa dev essere verde
				if (i==0) {
					g.setColor(Color.green);
					g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);//(x,y,width,height)
				}
				else {   //il resto random
					//g.setColor(new Color(45,180,0)); //uso colore sRGB per far vedere la variazione di colore della testa
					g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
					g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
				}
			}
			g.setColor(Color.white);
			g.setFont(new Font("Ink Free",Font.BOLD, 40));
			FontMetrics metrics= getFontMetrics(g.getFont());//serve a stampare lo score
			g.drawString("Score: "+applesEaten,(SCREEN_WIDTH-metrics.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());
			
		}
		else {
			gameOver(g);
		}
	}
	public void newApple() {
		appleX=random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;//(int) per sicurezza
		appleY=random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
		
	}
	public void move() {
		for(int i=bodyParts;i>0;i--) {
			x[i]=x[i-1];
			y[i]=y[i-1];
			
		}
		//DO IL COMANDO
		switch (direction) {
		case 'U':
			y[0]=y[0]-UNIT_SIZE;
			break;
		case 'D':
			y[0]=y[0]+UNIT_SIZE;
			break;
		case 'L':
			x[0]=x[0]-UNIT_SIZE;
			break;
		case 'R':
			x[0]=x[0]+UNIT_SIZE;
			break;
			
		}
		
	}
	public void checkApple() {
		if((x[0]==appleX)&&(y[0]==appleY)) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
		
	}
	public void checkCollisions() {
		//Controllo se la testa sbatte contro il corpo
		for (int i=bodyParts;i>0;i--) {
			if((x[0]==x[i])&&(y[0]==y[i])) {//X[0] TESTA DEL SERPENTE	
				running=false;
			}
		}
		//Controllo se la testa tocca il bordo sinistro
		if(x[0]<0) {
			running=false;
		}
		//Controllo se la testa tocca il bordo destro
		//if(x[0]>0) {
		if (x[0]>SCREEN_WIDTH) {
			running=false;
		}
		//Controllo se la testa tocca il bordo superiore
		if(y[0]<0) {
			running=false;
		}
		//Controllo se la testa tocca il bordo inferiore
		if (y[0]>SCREEN_HEIGHT) {
			running=false;
		}
		
		if (!running) {		//negato running= if (running==false)
			timer.stop();
		}
		
		
		
	}
	public void gameOver(Graphics g) {
		//Score
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD, 40));
		FontMetrics metrics1= getFontMetrics(g.getFont());//stampa la scritta score al gameover
		g.drawString("Score: "+applesEaten,(SCREEN_WIDTH-metrics1.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());
		
		//Testo del Game Over
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metrics2= getFontMetrics(g.getFont());//stampo la scritta game over
		g.drawString("GAME OVER",(SCREEN_WIDTH-metrics2.stringWidth("GAME OVER"))/2,SCREEN_HEIGHT/2);
		
					//ACTIONPERFORMED METODO DELL' INTERFACCIA IMPLEMENTATA  ACTIONLISTENER
	}				//SERVE ACTIONPERFORMED PER FARE IN MODO CHE I METODI SI AVVIINO ALL' APERTURA DELLA FINESTRA
	@Override      //SI AVVIA ALL' APERTURA DELLA FINESTRA E AD OGNI MOSSA DELLO SNAKE	
	public void actionPerformed(ActionEvent e) { //permette il movimento dello snake
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		//AGGIORNA LO SNAKE NEL PANEL
		repaint(); //invoco metodo repaint della classe JPanel
		
		}//SI AVVIA OGNI VOLTA CHE VIENE PREMUTO UN TASTO	
	public class MyKeyAdapter extends KeyAdapter{	//viene specificato cosa deve fare ogni determinato tasto	
		@Override //OVERRIDE=si intende una vera e propria riscrittura di un certo metodo di una classe che abbiamo ereditato
		public void keyPressed(KeyEvent e) {
			//ASSOCIO I TASTI AL COMANDO RISPETTIVO
			switch(e.getKeyCode()) {	//getkeycode mi dice il tasto premuto
			case KeyEvent.VK_LEFT:
				if (direction!='R') {   
					direction='L';
				}
				break;
			case KeyEvent.VK_RIGHT:				//se la direzione è diversa da destra posso andare a sinistra
				if (direction!='L') {
					direction='R';
				}
				break;
			case KeyEvent.VK_UP:
				if (direction!='D') {   
					direction='U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if (direction!='U') {
					direction='D';
				}
				break;
			
				
			}
			
		}
	}

}
