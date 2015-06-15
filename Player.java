import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
public class Player extends Entity{
    private Tool weapon;
    private boolean waiting = false;
    private LinkedList<Character> storedActions = new LinkedList<Character>();
    private Spell[] spells = new Spell[3];
    private Random rand = new Random();
    private Scanner in = new Scanner(System.in);
    private int[] inventory = new int[4];
    public Player(char c, int h, int m, String n, int a, int d, int x, int y, Tool w){
	super(c, h, m, n, a, d, x, y);
	weapon = w;
	spells[0] = new Spell("Flame Burst", 25, 'f', 10);
	spells[1] = new Spell("Basic Beam", 10, 'b', 25);
	spells[2] = new Spell("Energy Burst", 5, 'e', 50);
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
	System.out.println("What action do you want to store?");
	char c = '.';
	try{
	    c = in.nextLine().charAt(0);
	}
	catch (StringIndexOutOfBoundsException e){
	    System.out.println("Invalid entry. Action Storage Terminated.");
	}
	storedActions.add(c);
	if(c == 's' || c == 'S'){
	    System.out.println("What spell do you want to store?\n");
	    listSpells();
	    try{
		c = in.nextLine().charAt(0);
	    }
	    catch (StringIndexOutOfBoundsException e){
		c = spells[0].getSymbol();
	    }   
	    storedActions.add(c);
	} else if (c == ' '){
	    System.out.println("Which direction do you want to attack in?");
	    try{
		c = in.nextLine().charAt(0);
	    }
	    catch (StringIndexOutOfBoundsException e){
		c = 'w';
	    }   
	    storedActions.add(c);
	} else if (c == 'i'){
	    System.out.println("Which item do you want to use?");
	    try{
		c = in.nextLine().charAt(0);
	    }
	    catch (StringIndexOutOfBoundsException e){
		c = '0';
	    }   
	    storedActions.add(c);
	}
    }
    public void releaseStoredMoves(){
	
    }
    public void inventoryHandler(){

    }
    public void listSpells(){
	String ret = "";
	for (Spell s: spells){
	    ret += s.getName() + " (" + s.getSymbol() + "), ";
	}
	System.out.println("Heal (h), " + ret.substring(0, ret.length() - 2));
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
    public void attack(World w, Entity e){
	int attack = this.getAttack();
	int ph = attack;
	attack += weapon.getDam();
	if(weapon.getHoly()){
	    attack *= 2;
	}
	this.setAttack(attack);
	w.damcalc(this, e);
	this.setAttack(ph);
    }
    
}
