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
    private String status = "";
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
	    generateMobs();
	} else if(ps % 4 == 1){
	    preset2();
	    generateMobs();
	} else if(ps % 4 == 2){
	    preset3();
	    generateMobs();
	} else if(ps % 4 == 3){
	    generateMobs();
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
	int spawncap = level * 3 / 2;
	mobs = new Monster[spawncap];
	while(spawncap != 0){
	    int r = rand.nextInt(sideLength);
	    int c = rand.nextInt(sideLength);
	    if((map[r][c] != 'X') && (map[r][c] != 'C')){
		Monster m = new Monster('?',1000*level,1000*level,"MissingNo",100*level,1000*level, r, c);
		if(level % 3 == 0){
		     m = new Monster('S', 15 * level, 0, "Shambling Skeleton", level * 2, level, r, c);
		} else if(level % 3 == 1){
		    m  = new Monster('D', 15 * level, 0, "Minor Demon", level * 3, 0, r, c);
		} else if(level % 3 == 2){
		    m = new Monster('P', 15 * level, 0, "Corrupted Priest", level, level * 2, r, c);
		}
		summon(m);
		mobs[level*3/2-spawncap]=m;
	    }
	    spawncap--;
	}
    }
    public void move(Entity e, int x, int y){
	if(x >= 0 && x < sideLength && y >= 0 && y < sideLength){
	    if(map[x][y] == ' '){
		map[e.getX()][e.getY()] = ' ';
		e.setX(x);
		e.setY(y);
		map[x][y]=e.getSymbol();
	    }
	} 
	
    }
    public void clocal(Spell s){
	for(int i = 0; i < mobs.length; i++){
	    Monster m = mobs[i];
	    if(Math.abs(m.getX() - pc.getX()) <= 1 && Math.abs(m.getY() - pc.getY()) <= 1){
		int dam = s.getDamage();
		if(m.getHealth() < dam){
		    map[m.getX()][m.getY()] = ' ';
		    status += m.getName() + " has been killed by " + s.getName() + ".\n";
		}
		else {
		    m.setHealth(m.getHealth() - dam);
		    status += m.getName() + " was hit by " + s.getName() + " for " + dam + " damage.\n";
		}
	    }
	}
    }
    public void clong(Spell s){
	for(int i = 0; i < mobs.length; i++){
	    Monster m = mobs[i];
	    if(Math.abs(m.getX() - m.getY()) == Math.abs(pc.getX() - pc.getY())){
		int dam = s.getDamage();
		if(m.getHealth() < dam){
		    map[m.getX()][m.getY()] = ' ';
		    status += m.getName() + " has been killed by " + s.getName() + ".\n";
		}
		else {
		    m.setHealth(m.getHealth() - dam);
		    status += m.getName() + " was hit by " + s.getName() + " for " + dam + " damage.\n";
		}
	    }
	}
    }
    public void ccomplete(Spell s){
	for(int i = 0; i < mobs.length; i++){
	    Monster m = mobs[i];
	    if(rand.nextInt(3) < 2){
		int dam = s.getDamage();
		if(m.getHealth() < dam){
		    map[m.getX()][m.getY()] = ' ';
		    status += m.getName() + " has been killed by " + s.getName() + ".\n";
		}
		else {
		    m.setHealth(m.getHealth() - dam);
		    status += m.getName() + " was hit by " + s.getName() + " for " + dam + " damage.\n";
		    
		}
	    }
	}
    }
    public void damcalc(Entity e1, Entity e2){
	int atk = e1.getAttack();
	int def = e2.getDefense();
	int dam = atk - def/2;
	if(dam < 1){
	    dam = 1;
	}
	status += e1.getName() + " dealt " + dam + " damage to " + e2.getName() + ".\n";
	e2.setHealth(e2.getHealth() - dam);
	if(e2.getHealth() < 0){
	    System.out.println(e2 + " has died");
	}
    }
    public void attackDirection(char c){
	int xcor = pc.getX();
	int ycor = pc.getY();
	switch (c){
	case 'q': 
	    xcor--;
	    ycor--;
	    break;
	case 'w': 
	    xcor--;
	    break;
	case 'e':
	    xcor--;
	    ycor++;
	    break;
	case 'd':
	    ycor++;
	    break;
	case 'c':
	    ycor++;
	    xcor++;
	    break;
	case 'x':
	    xcor++;
	    break;
	case 'z':
	    xcor++;
	    ycor--;
	    break;
	case 'a':
	    ycor--;
	    break;
	}
	for(int i = 0; i < mobs.length;i++){
	    Monster m = mobs[i];
	    if(m != null){
		if( m.getX() == xcor){
		    if(m.getY() == ycor){
			pc.attack(this,m);
		    }
		}
	    }
	}
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
	return s+ "Level = " + level + "\n" + status + pc.toString();
    }
    public void wait(int n){
	try{
	    Thread.sleep(n);
	} catch (Exception e){

	}

    }
    public String commands(){
	return "Commands: \nMovement: N(w), NW(q), W(a), SW(z), S(x), SE(c), E(d), NE(e) \nInventory(i), Attack(space), Spells(s)\nStore Command(o), Release Stored Commands(p)";
    }
    public void commandHandle(char c){
	status = "";
	if(c == 'w' || c == 'W'){
	    if(pc.getX() == 0){ if(level != 20){
		level++;
		generate();	
		move(pc, sideLength - 1, pc.getY());}
	    }  
	    else {
		move(pc, pc.getX() - 1, pc.getY());	
	    }
	} else if (c == 'q' || c == 'Q'){
	    if(pc.getY() == 0){ if(level != 20){
		level++;
		generate();
		move(pc, pc.getX(), sideLength -1);}
	    } else if(pc.getX() == 0){ if(level != 20){
		level++;
		generate();	
		move(pc, sideLength - 1, pc.getY());}
	    }  

	    else {
		move(pc, pc.getX() - 1, pc.getY() - 1);		
	    }
	} else if (c == 'a' || c == 'A'){
	    if(pc.getY() == 0){ if(level != 20){
		level++;
		generate();
		move(pc, pc.getX(), sideLength -1);}
	    }  

	    else {
		move(pc, pc.getX(), pc.getY() - 1);		
	    }
	} else if (c == 'z' || c == 'Z'){
	    if(pc.getY() == 0){ if(level != 20){
		level++;
		generate();
		move(pc, pc.getX(), sideLength -1);}
	    } else if(pc.getX() == sideLength - 1){ if(level != 20){
		level++;
		generate();	
		move(pc, 0, pc.getY());}
	    } 
	    else {
		move(pc, pc.getX() + 1, pc.getY() - 1);
	    }
	} else if (c == 'x' || c == 'X'){
	    if(pc.getX() == sideLength - 1){ if(level != 20){
		level++;
		generate();	
		move(pc, 0, pc.getY());}
	    } 
	    else {
		move(pc, pc.getX() + 1, pc.getY());
	    }
	} else if (c == 'c' || c == 'C'){
	    if(pc.getY() == sideLength - 1){ if(level != 20){
		level++;
		generate();
		move(pc, pc.getX(), 0);}
	    } else if(pc.getX() == sideLength - 1){ if(level != 20){
		level++;
		generate();	
		move(pc, 0, pc.getY());}
	    } 
	    else {
		move(pc, pc.getX() + 1, pc.getY() + 1);
	    }
	} else if(c == 'd' || c == 'D'){
	    if(pc.getY() == sideLength - 1 ){ if(level != 20){ 
		level++;
		generate();
		move(pc, pc.getX(), 0);}
	    }  else {
		move(pc, pc.getX(), pc.getY() + 1);
	    }
	} else if (c == 'e' || c == 'E'){
	    if(pc.getY() == sideLength - 1){ if(level != 20){
		level++;
		generate();
		move(pc, pc.getX(), 0);}
	    } else if(pc.getX() == 0){ if(level != 20){
		level++;
		generate();	
		move(pc, sideLength - 1, pc.getY());}
	    }

	    else {
		move(pc, pc.getX() - 1, pc.getY() + 1);
	    }		    
	} else if (c == 'I' || c == 'I'){
	    pc.inventoryHandler();
	} else if (c == ' '){
	    System.out.println("Please state a direction, using the key provided by Movement above.");
	    char ph =  ' ';
	    try{
		c = input.nextLine().charAt(0);
	    } catch (StringIndexOutOfBoundsException e){
		c =  'w';
	    }
	    attackDirection(c);
	} else if (c == 's' || c == 'S'){
	    if(pc.getMana() > 0){
		pc.listSpells();
		try{
		    pc.cast(this, input.nextLine().charAt(0));
		} catch (StringIndexOutOfBoundsException e){
		    pc.cast(this, 'h');
		}
	    } else {
		status += "You lack the power to peform spells right now.";
	    }
	} else if (c == 'o' || c == 'O'){
	    pc.storeMoves();
	} else if (c == 'p' || c == 'P'){
	    pc.releaseStoredMoves();
	} else {
	    status += "Invalid entry";
	}
	for(Monster m : mobs){
	    if (m != null){
		if(m.getHealth() < 0){
		       map[m.getX()][m.getY()]=' ';
		       m = null;
		}
	        else {
		    m.trackPlayer(pc, this);
		}
	    }
	   
	}
	if(pc.getMaxHealth() < pc.getHealth()){
	    pc.setHealth(pc.getMaxHealth());
	}
	
    }
}
