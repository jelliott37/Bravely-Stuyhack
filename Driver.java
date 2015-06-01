public class Driver{
    
    public static void main(String[] args){
	Player p = new Player();
	World w = new World(25,p);
	
	w.generate();
	System.out.println(w);
    }
}
