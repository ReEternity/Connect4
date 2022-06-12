import info.gridworld.actor.Rock;
import java.awt.Color;

public class C4Rock extends Rock{
    public int piece;
    public int turn;
    public C4Rock(int i){
        piece = i;
        switch(i){
            case 0:
                setColor(Color.BLACK);
            break;
            case 1:
                setColor(Color.WHITE);
            break;
        }
        turn = 0;
    }
}

public class C4Rock extends Rock{
    public int piece;
    public int turn;
    public C4Rock(int i){
        piece = i;
        switch(i){
            case 0:
                setColor(Color.BLACK);
            break;
            case 1:
                setColor(Color.WHITE);
            break;
        }
        turn = 0;
    }
}