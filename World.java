public class World{
    //handles the 2d array that is the gamespace
    private char[][] map;
    private int sideLength;
    public World(int n){ //precondition: n is odd
	map = new char[n][n];
	sideLength = n;
    }
    public void generate(){ //creates map
	generateBorders();
	
    }
    public void generateBorders(){ //creates borders and fills with blankspace
	for(int c = 0; c < sideLength; c++){
	    for(int r = 0; r < sideLength; r++){
		if(c == 0 || c == sideLength - 1){
		    if(r != sideLength/2 - 1 && 
		       r != sideLength/2 && 
		       r != sideLength/2 + 1){
			map[r][c] = '_';
		    }
		    else{
			map[r][c] = ' ';
		    }
		}
		else if(r == 0 || r == sideLength - 1){
		    if(c != sideLength/2 - 1 && 
		       c != sideLength/2 && 
		       c != sideLength/2 + 1){
			map[r][c] = '|';
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
