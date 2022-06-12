import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.Scanner;

public class Connect4{
    public Scanner in = new Scanner(System.in);
    public BoundedGrid gr;
    public BoundedGrid inventory;
    public int turn = 0;
    public int totalTurn = 0;
    public static Connect4 main;
    public ActorWorld world;
    public int winner;
    public int limit;
    public int row;
    public int col;
    public static int invSpace;
    public Node head = null;
    public Node tail = null;
    public String line = "\n===========================================================================================\n";
    public static void main(){
        main = new Connect4(4, 6, 7);
        //Mode: normal, testing, WIP...
        main.begin("normal");
    }

    public Connect4(int lim, int Srow, int Scol){
        limit = lim;
        row = Srow;
        col = Scol;
        gr = new BoundedGrid(Srow,Scol);
        invSpace = Srow*Scol/2;
        inventory = new BoundedGrid(2,invSpace);

    }

    public void begin(String mode){
        world = new ActorWorld(gr);
        for (int i = 0; i < invSpace; i++){
            C4Rock temp0 = new C4Rock(0);
            temp0.putSelfInGrid(inventory, new Location(0, i));
            C4Rock temp1 = new C4Rock(1);
            temp1.putSelfInGrid(inventory, new Location(1, i));
        }
        world.show();
        boolean nEnd = true;
        while (nEnd){
            end:{
                if (check4()){
                    nEnd = false;
                    break end;
                }
                System.out.println(line);
                System.out.println("Where do you want to put it: 1 to " + col);
                int where = 0;
                int times = 0;
                while (!(where<=col) || !(where>0)){
                    if (times!=0){
                        System.out.println("Please enter a valid number");
                    }
                    String temp = in.nextLine();
                    if (temp.equals("")){
                        temp = in.nextLine();
                    }
                    try {
                        where = Integer.parseInt(temp);
                    } catch (NumberFormatException e) {
                        where = 0;
                    }
                    times++;
                }
                C4Rock temp = (C4Rock)inventory.get(new Location(turn, totalTurn/2));
                for (int i = 0; i < row; i++){
                    if (gr.get(new Location(i, where-1))==null){
                        temp.removeSelfFromGrid();
                        temp.putSelfInGrid(gr, new Location(i, where-1));
                        if (head == null){
                            head = new Node(temp, new Location(i, where-1), null);
                            tail = head;
                        }else{
                            Node nTemp = new Node(temp, new Location(i, where-1), null);
                            tail.next = nTemp;
                            tail = nTemp;
                        }
                    }
                }
                //System.out.println(head);
                System.out.println(line);
                world.show();

                if (turn==0){
                    turn++;
                    totalTurn++;
                }else{
                    turn=0;
                    totalTurn++;
                }
            }
        }
        System.out.println(line);
        if (winner==0){
            System.out.println("Red won! Game ended!");
        }else{
            System.out.println("Yellow won! Game ended!");
        }
        System.out.println(line);

    }

    public boolean check4(){
        if (head==null){
            return false;
        }
        Node current = head;
        while (current.hasNext()){
            if(check(current.who)){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean check(C4Rock a){
        int ang2 = 0;
        int victor = a.piece;
        for (int i = 0; i < 8; i++){
            Location next = a.getLocation();
            int space = 0;
            while (gr.isValid(next) && gr.get(next)!=null && ((C4Rock)gr.get(next)).piece==victor){
                next = next.getAdjacentLocation(ang2);
                space++;
            }
            if (space>=limit){
                winner = a.piece;
                return true;
            }
            ang2+=45;
        }
        return false;
    }
    public class Node{
        public C4Rock who;
        public Location where;
        public Node next;

        public Node(C4Rock a, Location s, Node n){
            who = a;
            where = s;
            next = n;
        }

        public boolean hasNext(){
            if (next==null){
                return false;
            }else{
                return true;
            }
        }
       
        public String toString(){
            return "Who: " + who + "Where: " + where + "\n";
        }

        public String toStringLinked(){
            if (next==null){
                return "Who: " + who + "Where: " + where + "\n";
            }else{
                return "Who: " + who + "Where: " + where + "\n" + next.toStringLinked();
            }
        }
    }
}

