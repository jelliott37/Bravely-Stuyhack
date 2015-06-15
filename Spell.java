public class Spell {
    private String name;
    private int damage;
    private char symbol;
    private int cost;
    public Spell(String n, int d, char s, int c){
	name = n;
	damage = d;
	symbol = s;
	cost = c;
    }
    public int getCost(){
	return cost;
    }
    public char getSymbol(){
	return symbol;
    }
    public int getDamage(){
	return damage;
    }
    public String getName(){
	return name;
    }
    public String toString(){
	return name;
    }
}
