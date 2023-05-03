//package CaRo;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
//import javax.swing.JButton;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//
//public class playGame {
//	public static int sec = 0;
//    public static Timer timer = new Timer();
//    public static JLabel Time;
//    public static JButton  Start;
//    public static Board board;
//    public static JLabel player1;
//    public static JLabel player2;
//    public static JLabel player3;
//    public static void startGame(){
//        //Hỏi ai đi trước
////       int choice = JOptionPane.showConfirmDialog(null, "Player 1 đi trước đúng không?", "Ai là người đi trước?", JOptionPane.YES_NO_OPTION);
//       String currentPlayer =  Cell.X_VALUE ;
//       board.reset();
//       board.setCurrentPlayer(currentPlayer);
//      
//        sec = 0;
//        Time.setText("0 : 0");
//        timer.cancel();
//        timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                sec++;
//                String value = sec / 60 + " : " + sec % 60;
//                Time.setText(value);
//            }
//        }, 1000, 1000);
//
//       Start.setText("Stop");
//  }
//
//
//   public static void stopGame(){
//        Start.setText("Start");
//
//        sec = 0;
//        Time.setText("0 : 0");
//
//        timer.cancel();
//        timer = new Timer();
//
//        board.reset();
//    }
//   
//}
//
package CaRo;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class playGame {
	public static int sec = 0;
    public static Timer timer = new Timer();
    public static JLabel Time;
    public static JButton  Start;
    public static Board board;
    public static JLabel player1;
    public static JLabel player2;
    public static void startGame(){
        //Hỏi ai đi trước
        int choice = JOptionPane.showConfirmDialog(null, "Player 1 đi trước đúng không?", "Ai là người đi trước?", JOptionPane.YES_NO_OPTION);
        String currentPlayer = (choice == 0) ? Cell.X_VALUE : Cell.O_VALUE;
        board.reset();
        board.setCurrentPlayer(currentPlayer);
       
        //Đếm ngược
        sec = 0;
        Time.setText("0 : 0");
        timer.cancel();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sec++;
                String value = sec / 60 + " : " + sec % 60;
                Time.setText(value);
            }
        }, 1000, 1000);

       Start.setText("Stop");
  }


   public static void stopGame(){
        Start.setText("Start");

        sec = 0;
        Time.setText("0 : 0");

        timer.cancel();
        timer = new Timer();

        board.reset();
    }
}






