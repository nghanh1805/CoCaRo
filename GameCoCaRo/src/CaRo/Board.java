package CaRo;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;


public class Board extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private static final int N=24;
	private static final int M=24;
	
	public static final int DRAW = 0;
	public static final int WIN = 1;
	public static final int NORMAL = 2;
	//0:hòa,1:player hiện tại thắng,2:player chưa thắng

	private endGame eG;
	private Image imgX;
	private Image imgO;
	private Cell matrix[][] = new Cell[N][M];
	private String currentPlayer = Cell.EMPTY_VALUE;
	
	public Board(String player) {
		this();
		this.currentPlayer = player;
	}   
	public Board() {
        this.initMatrix();
		//goi phuong thuc mousePressed cua lop MouseAdapter giao dien MouseListener
		addMouseListener( new MouseAdapter() {
		
			@Override
			public void mousePressed(MouseEvent e) { //lay toa do chuot click
				super.mousePressed(e);
				int x = e.getX();
				int y = e.getY();
				
				if(currentPlayer.equals(Cell.EMPTY_VALUE)) {
					return;
				}
				
				//phat ra am thanh
				soundClick();
				//tinh toan x,y roi vao o nao trong board, sau do ve hinh x hoac y
		
				for(int i = 0;i < N;i++) {
					for(int j = 0;j < M;j++) {
						 Cell cell = matrix[i][j];

						 
						 int cXStart = cell.getX();
						 int cYStart = cell.getY();
						 
						 int cXEnd = cell.getW() + cXStart;
						 int cYEnd = cell.getH() + cYStart;
						 
						 if(x >= cXStart && x <= cXEnd && y >= cYStart && y <= cYEnd) {
							 if(cell.getValue().equals(Cell.EMPTY_VALUE)) {
						       cell.setValue(currentPlayer);
						       repaint();
						       int result = checkWin(currentPlayer,i,j);
						       if(eG != null) {
						        	  eG.end(currentPlayer, result);
							   }
							    if( result == NORMAL) {
							          currentPlayer  = currentPlayer.equals(Cell.X_VALUE) ? Cell.O_VALUE: Cell.X_VALUE;
						        }  
						      }
						  } 
					       
					}
				}
			}
		});
		
		try{
			imgX = ImageIO.read(getClass().getResource("imageX.png"));
			imgO = ImageIO.read(getClass().getResource("imageO.png"));
			
		}catch (Exception e){
			e.printStackTrace();
		}	
	}
	  private synchronized void soundClick(){
		  //tao tieng click
	        Thread thread = new Thread(new Runnable() {
	            @Override
	            public void run() {
	                try{
	                    Clip clip = AudioSystem.getClip();
	                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("soundclick.wav"));
	                    clip.open(audioInputStream);
	                    clip.start();
	                }catch (Exception e){
	                    e.printStackTrace();
	                }
	            }
	        });
	        thread.start();
	    }

	private void initMatrix() {
	//khoi tao matrix
			for(int i = 0;i < N;i++) {
				for(int j = 0;j < M;j++) {
					Cell cell = new Cell();
	                matrix[i][j] = cell;
			    }
			}
	}	
	
	public void reset() {
		this.initMatrix();
		this.setCurrentPlayer(Cell.EMPTY_VALUE);
		repaint();
		}
	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	public String getCurrentPlayer() {
	        return currentPlayer;
    }
	public void setendGame(endGame eG) {
		this.eG = eG;
	}


	public int checkWin(String player, int i , int j) {
		

		//Chiều ngang
        int count = 0;
        for(int col = 0; col < M; col++){
            Cell cell = matrix[i][col];
            if (cell.getValue().equals(currentPlayer)) {
                count++;
                if(count == 5){
                    System.out.println("Ngang");
                    return WIN;
                }
            }else {
                count = 0;
            }
        }


        //Chiều dọc
        count = 0;
        for(int row = 0; row < N; row++){
            Cell cell = matrix[row][j];
            if (cell.getValue().equals(currentPlayer)) {
                count++;
                if(count == 5){
                    System.out.println("Doc");
                    return WIN;
                }
            }else {
                count = 0;
            }
        }

        //Chéo trái
        int min = Math.min(i, j);
        int TopI = i - min;
        int TopJ = j - min;
        count = 0;

        for(;TopI < N && TopJ < M; TopI++, TopJ++){
            Cell cell = matrix[TopI][TopJ];
            if (cell.getValue().equals(currentPlayer)) {
                count++;
                if(count == 5){
                    System.out.println("Cheo trai");
                    return WIN;
                }
            }else {
                count = 0;
            }
        }


        //Chéo phải
        min = Math.min(i, j);
        TopI = i - min;
        TopJ = j + min;
        count = 0;
  
        if(TopJ >= M){
            int du = TopJ - (M - 1);
            TopI = TopI + du;
            TopJ = M - 1;
        }

        for(;TopI < N && TopJ >= 0; TopI++, TopJ--){
            Cell cell = matrix[TopI][TopJ];
            if (cell.getValue().equals(currentPlayer)) {
                count++;
                if(count == 5){
                    System.out.println("Cheo phai");
                    return WIN;
                }
            }else {
                count = 0;
            }
        }

        if(this.isFull()) {
            return DRAW;
        }
            return NORMAL;
     }
    
     private boolean isFull() {
    	 int number = N * M;
    	 int k=0;
    	 
    	 for(int i = 0;i < N;i++) {
				for(int j = 0;j < M;j++) {
			        Cell cell = matrix[i][j];
			        if(!cell.getValue().equals(Cell.EMPTY_VALUE)) {
			            k++;
			        }
				}
    	 }	      
    	 return k == number;
     }
	@Override
	public void 
	paint(Graphics g) {
		super.paint(g);
		int w = getWidth()/N;
		int h = getHeight()/M;
		
		Graphics2D g2 =(Graphics2D)g;
		
	
		for (int i=0;i< N;i++) {
			int k=i;
			for (int j=0;j< M;j++) {
				int x=j*w;//0*h,1*h,2*h,....
				int y=i*h;//0*w,1*w,2*w,....
				
				//cap nhat lai ma tran
				Cell cell = matrix[i][j];
				cell.setX(x);
				cell.setY(y);
				cell.setW(w);
				cell.setH(h);
				
				Color color =k%2 == 0 ? Color.LIGHT_GRAY : Color.white;
				g2.setColor(color);	
				g2.fillRect(x, y, w, h);
				
				if(cell.getValue().equals(Cell.X_VALUE)){
					Image img = imgX;
					g2.drawImage(img, x, y, w, h, this);
			    }else if(cell.getValue().equals(Cell.O_VALUE)) {
			    	Image img = imgO;
			    	g2.drawImage(img, x, y, w, h, this);
			    }		
				k++;
			}
			
		}

	}

}


												