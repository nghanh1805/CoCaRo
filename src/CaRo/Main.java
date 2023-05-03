package CaRo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
 public class Main extends playGame{
    	
	public static void main(String[] args) {	
     
	    board =new Board();
	    board.setendGame(new endGame(){
	    	@Override
	    	public void end(String player,int st ) {
	    		if(st == Board.WIN) {
	    		   JOptionPane.showMessageDialog(null,"Người chơi "+ player + " thắng");
	    		   stopGame();
	    	    }else if(st == Board.DRAW) {
	    		   JOptionPane.showMessageDialog(null," Hòa rồi !!");
	    		   stopGame();
	    	    }
	    		
	    
	        }
	    });
	    
        JPanel jp = new JPanel();
        // giao dien add theo truc y
        BoxLayout boxlayout = new BoxLayout(jp,BoxLayout.Y_AXIS);
        jp.setLayout(boxlayout);
	      
	    // set size board
	    board.setPreferredSize(new Dimension(600,600));
	    //quan li bo cuc
	    FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
	    
	    JPanel bottomPanel = new JPanel();
	    bottomPanel.setLayout(fl);
	    

	    Start = new JButton("Start");
        //time va timetask
	    Time = new JLabel("0 : 0");
	    
	    player1 = new JLabel("Player 1: X              ");
        player2 = new JLabel("              Player 2: O");

	    
	    bottomPanel.add(player1);
	    bottomPanel.add(Start);
	    bottomPanel.add(Time);
	    bottomPanel.add(player2);

	    
	    
	 
	    
        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 if(Start.getText().equals("Start")){
                    startGame();
                }else{
                    stopGame();
            }
           }
      
        }); 
        
	    jp.add(board);
	    jp.add(bottomPanel);
	    
	    
	    Dimension dimention = Toolkit.getDefaultToolkit().getScreenSize();
		JFrame jf = new JFrame("GAME CỜ CARO");
		// thoat khoi chuong trinh khi dong cua so jframe
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// thay doi kich thuoc frame
		jf.setResizable(true);
		jf.add(jp);
		// Lấy kích thước JPanel
		Dimension panelSize = jp.getPreferredSize();
		// Tính toán vị trí JFrame giữa màn hình
		int x = (dimention.width - panelSize.width) / 2;
		int y = (dimention.height - panelSize.height) / 2;
		// Đặt vị trí JFrame
		jf.setLocation(x, y);
		// tu dong dieu chinh kich thuoc phu hop noi dung
		jf.pack();
		// Hien thi giao dien game
	   	jf.setVisible(true);
	}
	
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
