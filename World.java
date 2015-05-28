public class World{
    //handles the 2d array that is the gamespace
    private char[][] map;
    private int sideLength;
    public world(int n){ //precondition: n is odd
	map = new char[n][n];
	sideLength = n;
    }
    public void generate(){ //creates map
	for(int r = 0; r < sideLength; r++){
		for(int c = 0; c < sideLength; c++){
		    if(r == 0 || r == sideLength - 1){
			if(c != sideLength/2 && 
			   c != sideLength/2 + 1 && 
			   c != sideLength/2 + 2){
			    
			}
		    }
		}
	}    
    }
    public String toString()
    {
	String s = "[2J\n";
	for (int y=0;y<sideLength;y++)
	    {
		for (int x=0;x<maxX;x++)
		    s = s +board[x][y];
		s=s+"\n";
	    }
	return s;
    }
}
