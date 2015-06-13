import java.util.LinkedList;
import java.util.Random;
public class Player extends Entity{
    private Tool weapon;
    private boolean waiting = false;
    private LinkedList<Character> storedActions = new LinkedList<Character>();
    private Spell[] spells = new Spell[4];
    private Random rand = new Random();
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
    public void storeMoves(){

    }
    public void releaseStoredMoves(){

    }
    public void inventoryHandler(){

    }
    public void listSpells(){
	String ret = "";
	for (Spell s: spells){
	    ret += s.getName() + " (" + s.getSymbol() + "),";
	}
	System.out.println("Heal (h), " + ret.substring(ret.length() - 1));
    }
    public void cast(World w, char c){
	if(c == 'h' || c == 'H'){
	    int ph = this.getMaxHealth() - this.getHealth();
	    this.setHealth( this.getHealth() + ph / 2);
	    this.setMana(this.getMana() - ph / 4);
	    System.out.println("You healed yourself for " + (ph / 2) + " damage." );
	} else if (c == spells[0].getSymbol()){
	    w.clocal(spells[0]);
	    this.setMana(this.getMana() - spells[0].getCost());
	} else if (c == spells[1].getSymbol()){
	    w.clong(spells[1]);
	    this.setMana(this.getMana() - spells[2].getCost());
	} else if (c == spells[2].getSymbol()){
	    w.ccomplete(spells[2]);
	    this.setMana(this.getMana() - spells[2].getCost());
	} else {
	    int ph = rand.nextInt(this.getMaxMana()/10) + this.getMaxMana()/8;
	    this.setMana(this.getMana() - ph);
	    System.out.println("Your spell fizzled and died. You lost " + ph + " mana");
	}
    } 
    public void attack(World w, char c){

    }
    
}
