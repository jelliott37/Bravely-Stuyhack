public class Driver{
    
    public static void main(String[] args){
	Player p = new Player();
	World w = new World(21,p,20);
	
	w.generate();
	System.out.println(w);
    }
}
