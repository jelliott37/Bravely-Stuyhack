public class World{
    //handles the 2d array that is the gamespace
    private char[][] map;
    private int sideLength;
    private Player pc;
    private int level
    public World(int n, Player pco, int lvl){ //precondition: n is odd
	map = new char[n][n];
	sideLength = n;
	pc=pco;
	level = lvl;
    }
    public void generate(){ //creates map
	generateBorders();
	int ps = r.nextInt(20)-level;
	if(ps < 0){
	    generateBoss();
	} else if(ps % 3 == 0){
	    preset1();
	} else if(ps % 3 == 1){

	} else if(ps % 3 == 2){

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
	for(int c = sideLength/2 - 3; c < sideLength/2 + 3; c++){
	    for(int r =sideLength/2 - 3; r < sideLength/2 + 3; r++){
		if( r == sideLength/2 || c == sideLength/2){
		    map[r][c]='X';
		}
	    }
	}

    }
    public void preset2(){

    }
    public void preset3(){

    }
    public void presetBoss(){
	
    }
    public void generateChests(){
	
    }
    public String toString()
    {
	String s = "\033\143";
	for (int y=0;y<sideLength;y++)
	    {
		for (int x=0;x<sideLength;x++)
		    s = s + map[x][y];
		s=s+"\n";
	    }
	return s;
    }
}
