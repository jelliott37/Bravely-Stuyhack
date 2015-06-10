public class Entity{
    //handles the characters (player and enemy)
    private char symbol; //the character's representation;
    private int health;
    private int mana;
    private String name;
    private int atk;
    private int def;
    private int xcor;
    private int ycor;
    private int maxHealth;
    private int maxMana;
    public Entity(char c, int h, int m, String n, int a, int d, int x, int y){
	symbol = c;
	health = h;
	maxHealth = h;
	mana = m;
	maxMana = m;
	name = n;
	atk = a;
	def = d;
	xcor = x;
	ycor = y;
    }
    public String getName(){
	return name;
    }
    public int getAttack(){
	return atk;
    }
    public int getDefense(){
	return def;
    }
    public int getHealth(){
	return health;
    }
    public int getMaxHealth(){
	return maxHealth;
    }
    public int getMana(){
	return mana;
    }
    public int getMaxMana(){
	return maxMana;
    }
    public int getX(){
	return xcor;
    }
    public int getY(){
	return ycor;
    }
    public void setX(int n){
	xcor = n;
    }
    public void setY(int n){
	ycor = n;
    }
    public char getSymbol(){
	return symbol;
    }
    public String attack(Entity e){return "";}
    public int damCalc(Entity e){return 0;} 
}
