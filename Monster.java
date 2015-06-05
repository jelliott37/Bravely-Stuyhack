public class Monster extends Entity{
    private String[] attacks;
    public Monster(char c, int h, int m, String n, int a, int d, int x, int y, String[] at){
	super(char c, int h, int m, String n, int a, int d, int x, int y);
	attacks = at;
    }
}
