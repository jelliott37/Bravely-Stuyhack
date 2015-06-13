import java.util.Random;
import java.util.Scanner;
public class World{
    //handles the 2d array that is the gamespace
    private Scanner input = new Scanner(System.in);
    private char[][] map;
    private int sideLength;
    private Player pc;
    private Monster[] mobs;
    private int level;
    private Random rand = new Random();
    public World(int n, Player pci, int lvl){ //precondition: n is odd
	map = new char[n][n];
	sideLength = n;
	pc=pci;
	level = lvl;
    }
    public int getLevel(){
	return level;
    }
    public int getSL(){
	return sideLength;
    }
    public void generate(){ //creates map
	generateBorders();
	int ps = rand.nextInt(20)-level;
	if (sideLength < 11){
	    preset3();
	    generateChests(sideLength); 
	} else if(ps < 0){
	    generateBoss();
	} else if(ps % 4 == 0){
	    preset1();
	} else if(ps % 4 == 1){
	    preset2();
	} else if(ps % 4 == 2){
	    preset3();
	}
	if(ps >= 0){
	    generateChests(sideLength/3);
	}
	
    }
    public void generateBorders(){ //creates borders and fills with blankspace
	for(int c = 0; c < sideLength; c++){
	    for(int r = 0; r < sideLength; r++){
		if(c == 0 || c == sideLength - 1){
		    if(r != sideLength/2 - 1 && 
		       r != sideLength/2 && 
		       r != sideLength/2 + 1){
			map[r][c] = 'X';
		    }
		    else{
			map[r][c] = ' ';
		    }
		}
		else if(r == 0 || r == sideLength - 1){
		    if(c != sideLength/2 - 1 && 
		       c != sideLength/2 && 
		       c != sideLength/2 + 1){
			map[r][c] = 'X';
		    }
		    else{
			map[r][c] = ' ';
		    }
		}
		else{
		    map[r][c] = ' ';
		}
	    }
	}    
    }
    public void preset1(){
	int modifier = (sideLength - 2)/3;
	for(int c = sideLength/2 - modifier + 1; c < sideLength/2 + modifier; c++){
	    for(int r = sideLength/2 - modifier + 1; r < sideLength/2 + modifier; r++){
		if( r == sideLength/2 || c == sideLength/2){
		    map[r][c]='X';
		}
	    }
	}

    }
    public void preset2(){
	for(int c = 0; c < sideLength; c++){
	    for(int r = 0; r < sideLength; r++){
		if(Math.abs(r - sideLength/2) <= 1 || Math.abs(c - sideLength/2) <= 1){
		} else if((r + c) % 2 == 0 && r % 2 == 0){
		    map[r][c] = 'X';
		}
		
	    }
	}
    }
    public void preset3(){
	for(int c = 0; c < sideLength; c++){
	    for(int r = 0; r < sideLength; r++){
		if((Math.abs(r - sideLength/2) == 2 || 
		    Math.abs(c - sideLength/2) == 2) && 
		   !(r == 1 || c ==1 || r == sideLength/2 || c == sideLength/2 || 
		     r == sideLength - 2 || c == sideLength - 2)){
		    map[r][c] = 'X';
		}
	    }
	}
    }
    public void generateBoss(){
	int centerlow = sideLength/4;
	int centerhigh = sideLength*3/4;
	for(int c = 0; c < sideLength; c++){
	    for(int r = 0; r < sideLength; r++){
		if((Math.abs(r - centerlow) <= 1|| Math.abs(r - centerhigh) <= 1)
		   &&(Math.abs(c - centerlow)<=1||Math.abs(c - centerhigh)<= 1)){
		    map[r][c] = 'X';
		}
	    }
	}
    }
    public void generateChests(int n){
	int counter = n;
	for (int r = 1; r < sideLength - 1; r++){
	    for (int c = 1; c < sideLength - 1; c++){
		if(map[r][c] != 'X'){
		    if(counter >= rand.nextInt((int)(Math.pow(sideLength,2)))){
			map[r][c] = 'C';
			counter --;
		    } 
		}
	    }
	}
    }
    public void summon(Entity e){
	map[e.getX()][e.getY()] = e.getSymbol();
    }
    public void generateMobs(){
	int spawncap = 3;
	String[] SlimeAtt = {"Pound", "Slap"};//temporary location
	while(spawncap != 0){
	    int r = rand.nextInt(sideLength);
	    int c = rand.nextInt(sideLength);
	    if((map[r][c] != 'X') && (map[r][c] != 'C')){
		Monster m = new Monster('M', 10, 0, "Slime", 1, 0, r, c, SlimeAtt);
		summon(m);
	    }
	    spawncap--;
	}
    }
    public void move(Entity e, int x, int y){
	if(map[x][y] == ' '){
	    map[e.getX()][e.getY()] = ' ';
	    e.setX(x);
	    e.setY(y);
	    map[x][y]=e.getSymbol();
	}
	
    }
    public void clocal(Spell s){

    }
    public void clong(Spell s){

    }
    public void ccomplete(Spell s){

    }
    public String toString()
    {
	String s = "\033\143";

	for (int y=0; y<sideLength; y++)
	    {
		for (int x=0;x<sideLength;x++)
		    s = s + map[y][x];
		s=s+"\n";
	    }
	return s+ "Level = " + level + "\n" + pc.toString();
    }
    public void wait(int n){
	try{
	    Thread.sleep(n);
	} catch (Exception e){

	}

    }
    public String commands(){
	return "Commands: \nMovement: N(w), NW(q), W(a), SW(z), S(x), SE(c), E(d), NE(e) \nInventory(i), Attack(space), Spells(s)\nStore Command(o), Release Stored Time(p)";
    }
    public void commandHandle(char c){
	if(c == 'w' || c == 'W'){
	    if(pc.getX() == 0){
		level++;
		generate();	
		move(pc, sideLength - 1, pc.getY());
	    }
	    else {
		move(pc, pc.getX() - 1, pc.getY());	
	    }
	} else if (c == 'q' || c == 'Q'){
	    if(pc.getY() == 0){
		level++;
		generate();
		move(pc, pc.getX(), sideLength -1);
	    } else if(pc.getX() == 0){
		level++;
		generate();	
		move(pc, sideLength - 1, pc.getY());
	    }
	    else {
		move(pc, pc.getX() - 1, pc.getY() - 1);		
	    }
	} else if (c == 'a' || c == 'A'){
	    if(pc.getY() == 0){
		level++;
		generate();
		move(pc, pc.getX(), sideLength -1);
	    }
	    else {
		move(pc, pc.getX(), pc.getY() - 1);		
	    }
	} else if (c == 'z' || c == 'Z'){
	    if(pc.getY() == 0){
		level++;
		generate();
		move(pc, pc.getX(), sideLength -1);
	    } else if(pc.getX() == sideLength - 1){
		level++;
		generate();	
		move(pc, 0, pc.getY());
	    }
	    else {
		move(pc, pc.getX() + 1, pc.getY() - 1);
	    }
	} else if (c == 'x' || c == 'X'){
	    if(pc.getX() == sideLength - 1){
		level++;
		generate();	
		move(pc, 0, pc.getY());
	    }
	    else {
		move(pc, pc.getX() + 1, pc.getY());
	    }
	} else if (c == 'c' || c == 'C'){
	    if(pc.getY() == sideLength - 1){
		level++;
		generate();
		move(pc, pc.getX(), 0);
	    } else if(pc.getX() == sideLength - 1){
		level++;
		generate();	
		move(pc, 0, pc.getY());
	    }
	    else {
		move(pc, pc.getX() + 1, pc.getY() + 1);
	    }
	} else if(c == 'd' || c == 'D'){
	    if(pc.getY() == sideLength - 1){
		level++;
		generate();
		move(pc, pc.getX(), 0);
	    } else {
		move(pc, pc.getX(), pc.getY() + 1);
	    }
	} else if (c == 'e' || c == 'E'){
	    if(pc.getY() == sideLength - 1){
		level++;
		generate();
		move(pc, pc.getX(), 0);
	    } else if(pc.getX() == 0){
		level++;
		generate();	
		move(pc, sideLength - 1, pc.getY());
	    }
	    else {
		move(pc, pc.getX() - 1, pc.getY() + 1);
	    }		    
	} else if (c == 'I' || c == 'I'){
	    pc.inventoryHandler();
	} else if (c == ' '){
	    System.out.println("Please state a direction, using the key provided by Movement above.");
	    try{
		pc.attack(this, input.nextLine().charAt(0));
	    } catch (StringIndexOutOfBoundsException e){
		pc.attack(this, 'w');
	    }
	} else if (c == 's' || c == 'S'){
	    pc.listSpells();
	    try{
		pc.cast(this, input.nextLine().charAt(0));
	    } catch (StringIndexOutOfBoundsException e){
		pc.cast(this, 'h');
	    }
	} else if (c == 'o' || c == 'O'){
	    pc.storeMoves();
	} else if (c == 'p' || c == 'P'){
	    pc.releaseStoredMoves();
	}
	
    }
}
