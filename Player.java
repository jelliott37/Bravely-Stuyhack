import java.util.LinkedList;
public class Player extends Entity{
    private Tool weapon;
    private boolean waiting = false;
    private LinkedList<Character> storedActions = new LinkedList<Character>();
    public Player(char c, int h, int m, String n, int a, int d, int x, int y, Tool w){
	super(c, h, m, n, a, d, x, y);
	weapon = w;
    }
    public String toString(){
	return this.getName() + ":\nHealth: " + this.getHealth() + "/" + this.getMaxHealth() + "\nMana: " + this.getMana() + "/" + this.getMaxMana() + "\nAttack: " + this.getAttack() + "\nDefense: " + this.getDefense() + "\nWeapon: " + weapon;
    }
    public void setWait(boolean in){
	waiting = in;
    }
    public boolean getWait(){
	return waiting;
    }
    public void inventoryHandler(){

    }
    public void cast(World w, char c){

    } 
    public void attack(World w, char c){

    }
    
}
