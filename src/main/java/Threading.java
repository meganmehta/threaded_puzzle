import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

public class Threading {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ArrayList<Character> gameBoard = new ArrayList<>(Arrays.asList('-','-','-','-','-','-','-','-','-'));

        ExecutorService ex = Executors.newFixedThreadPool(5);

        Character turn = 'x';

        for(int iter = 0; iter < 9; iter++) {

            Future<Integer> future = ex.submit(new MyCall(gameBoard, turn));

            try {
                Integer index = future.get();
                gameBoard.set(index, turn);
                System.out.println(gameBoard.get(0)+ " "+gameBoard.get(1)+" "+gameBoard.get(2));
                System.out.println(gameBoard.get(3)+ " "+gameBoard.get(4)+" "+gameBoard.get(5));
                System.out.println(gameBoard.get(6)+ " "+gameBoard.get(7)+" "+gameBoard.get(8));

            }catch(Exception e){System.out.println(e.getMessage());}

            if(turn == 'x') {
                turn = 'o';
            }
            else {
                turn = 'x';
            }
        }
        ex.shutdown();

    }



}

class MyCall implements Callable<Integer>{

    ArrayList<Character> board = new ArrayList<Character>();
    Character move;

    MyCall(ArrayList<Character> game, Character move){
        board = game;
        this.move = move;
    }
    @Override
    public Integer call() throws Exception {
        // TODO Auto-generated method stub
        boolean bool = true;
        Integer val = 0;


        while(bool) {

            Random r = new Random();
            val = r.nextInt(9);

            if(board.get(val) == 'x' || board.get(val) == 'o') {
                bool = true;

            }
            else {bool = false;}
        }

        Thread.sleep(1000);
        System.out.println("\n" + "player: " + move + " chooses index: "+val);
        return val;
    }

}


