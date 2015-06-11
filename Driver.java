import java.util.Scanner;

public class Driver{
    
    public static void main(String[] args){
	boolean complete = false;
	Scanner in = new Scanner(System.in);
	System.out.println("Welcome, traveller.");
	System.out.println("Tell me, what is your name?");
	String n = in.nextLine();
	System.out.print(n + "? A worthy name for a hero such as yourself.");
	int h=100;
	int m=100;
	int a=5;
	int d=5;
	Tool t = null;
	while(!complete){
	    System.out.println("Would you say you are better at offense(O) or defense(D)?");
	    try {
		char b = in.nextLine().charAt(0);
		if(b == 'O' || b == 'o' || b == '0'){
		    complete = true;
		    a += 3;
		    d--;
		} else if(b == 'D' || b == 'd'){
		    complete = true;
		    d += 3;
		    a--;
		} else {
		    System.out.println(n + ", please follow the instructions as they were given.");
		}	   
	    } catch (StringIndexOutOfBoundsException E){
		System.out.println(n + ", something is necessary to proceed. Please follow the instructions.");
	    }
	    
	}
	complete = false;
	while(!complete){
	    System.out.println("Would you say you are better with a sword(W), an axe(A), or a staff(T)?");
	    try {

		char b = in.nextLine().charAt(0);
		if(b == 'W' || b == 'w'){
		    complete = true;
		    a+=3;
		    d+=2;
		    h+=25;
		    m+=25;
		    t = new Tool(4, false, false, false, "Basic Sword", "swing at");
		} else if (b == 'A' || b == 'a'){ 
		    complete = true;
		    a+=2;
		    d+=4;
		    h+=50;
		    m-=50;
		    t = new Tool(5, false, false, false, "Basic Axe", "decapitate");
		} else if (b == 'T' || b == 't'){
		    complete = true;
		    a+=1;
		    d+=3;
		    m+=50;
		    h+=50;
		    t = new Tool(3, false, false, false, "Basic Staff", "whack"); 
		} else {
		    System.out.println(n + ", please follow the instructions as they were given.");
		}
	    } catch (StringIndexOutOfBoundsException E) {	
		System.out.println(n + ", something is necessary to proceed. Please follow the instructions.");
	    }
	}
	System.out.println("Well then, " + n + ", I welcome you to this temple, a house of the gods. I beg of you, hero, bring things back to order."); 
	in.nextLine();
	Player p = new Player('@', h, m, n, a, d, 20, 10, t);
	World w = new World(21,p,0);	
	
	w.generate();
	w.summon(p);
	w.generateMobs();
	while(p.getHealth() > 0){
	    System.out.println(w +"\n" + w.commands());
	    try{
		w.commandHandle(in.nextLine().charAt(0));
	    } catch (StringIndexOutOfBoundsException e){
		System.out.println("Please, enter a command when prompted. Press \"Enter\" now to try again.");
		in.nextLine();
	    }
	}
	System.out.println("Oh, hero! Your wounds are too grevious. I cannot maintain your health for much longer. Your death is nigh. Your eternal slumber must now begin...");
	System.exit(0);
    }
}
