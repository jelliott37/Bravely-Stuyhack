import java.util.Random;

public class Monster extends Entity{
    private String[] attacks;
    private Random rand = new Random();
    public Monster(char c, int h, int m, String n, int a, int d, int x, int y, String[] at){
	super(c, h, m, n, a, d, x, y);
	attacks = at;
    }
    public void trackPlayer(Player pc, World w){
	int pcx = pc.getX();
	int pcy = pc.getY();
	int mcx = this.getX();
	int mcy = this.getY();
	if (Math.sqrt((pcx-mcx)*(pcx-mcx)+(pcy-mcy)*(pcy-mcy))>8){
	    int dir = rand.nextInt(8);
	    if (dir == 0){w.move(this, this.getX()-1, this.getY()-1);}
	    if (dir == 1){w.move(this, this.getX(), this.getY()-1);}
	    if (dir == 2){w.move(this, this.getX()+1, this.getY()-1);}
	    if (dir == 3){w.move(this, this.getX()+1, this.getY());}
	    if (dir == 4){w.move(this, this.getX()+1, this.getY()+1);}
	    if (dir == 5){w.move(this, this.getX(), this.getY()+1);}
	    if (dir == 6){w.move(this, this.getX()-1, this.getY()+1);}
	    if (dir == 7){w.move(this, this.getX()-1, this.getY());}
	}
    }
}
