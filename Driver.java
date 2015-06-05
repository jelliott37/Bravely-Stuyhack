import java.util.Scanner;

public class Driver{
    
    public static void main(String[] args){
	bool complete = false;
	Scanner in = new(System.in);
	System.out.println("Welcome, traveller.");
	in.nextLine();
	System.out.println("Tell me, what is your name?");
	String n = in.nextLine();
	while(!complete){
	    System.out.println(n + "? A worthy name for a hero such as yourself. Would you say you are better at offense(O) or defense(D)?");
	    char a = in.nextLine().charAt(0);
	    if(a == 'O' || a == 'D'){
		
	    }
	Player p = new Player();
	World w = new World(21,p,20);
	
	w.generate();
	System.out.println(w);
    }
}
