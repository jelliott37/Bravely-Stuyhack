public class Tool{
    private int dam;
    private boolean holy;
    private String name;
    private String attack;
    public Tool (int d, boolean h, String n, String t){
	dam = d;
	holy = h;
	name = n;
	attack = t;
    }
    public String toString(){
	return name + " - " + dam + " damage";
    }
}
