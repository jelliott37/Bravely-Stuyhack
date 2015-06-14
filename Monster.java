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
	    if (dir == 0){w.move(this, this.setX()-1, this.setY()-1);}
	    if (dir == 1){w.move(this, this.setX(), this.setY()-1);}
	    if (dir == 2){w.move(this, this.setX()+1, this.setY()-1);}
	    if (dir == 3){w.move(this, this.setX()+1, this.setY());}
	    if (dir == 4){w.move(this, this.setX()+1, this.setY()+1);}
	    if (dir == 5){w.move(this, this.setX(), this.setY()+1);}
	    if (dir == 6){w.move(this, this.setX()-1, this.setY()+1);}
	    if (dir == 7){w.move(this, this.setX()-1, this.setY());}
	} else if(Math.abs(mcx-pcx)!=1 && Math.abs(mcy-pcy)!=1){
	    if (mcx>pcx && mcy>pcy){w.move(this, this.setX()-1, this.setY()-1);}
	    if (mcx>pcx && mcy=pcy){w.move(this, this.setX()-1, this.setY());}
	    if (mcx>pcx && mcy<pcy){w.move(this, this.setX()-1, this.setY()+1);}
	    if (mcx=pcx && mcy<pcy){w.move(this, this.setX(), this.setY()+1);}
	    if (mcx<pcx && mcy<pcy){w.move(this, this.setX()+1, this.setY()+1);}
	    if (mcx<pcx && mcy=pcy){w.move(this, this.setX()+1, this.setY());}
	    if (mcx<pcx && mcy>pcy){w.move(this, this.setX()+1, this.setY()+1);}
	    if (mcx=pcx && mcy>pcy){w.move(this, this.setX(), this.setY()-1);}
	} else if (Math.abs(mcx-pcx)=1 || Math.abs(mcy-pcy)=1){
	    //attack player
	}

	public void attack(World w, char c){
	    
	}
}
