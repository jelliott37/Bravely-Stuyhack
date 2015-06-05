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
    public Entity(char c, int h, int m, String n, int a, int d, int x, int y){
	symbol = c;
	health = h;
	mana = m;
	name = n;
	atk = a;
	def = d;
	xcor = x;
	ycor = y;
    }
    public int getXCor(){
	return xcor;
    }
    public int getYCor(){
	return ycor;
    }
    public void setXCor(int n){
	xcor = n;
    }
    public void setYCor(int n){
	ycor = n;
    }
    public char getSymbol(){
	return symbol;
    }
    public String attack(Entity e){}
    public int damCalc(Entity e){return 0;} 
}
