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
    public Tool (int d, boolean h, String n){
	dam = d;
	holy = h;
	name = n;
    }
    public boolean getHoly(){
	return holy;
    }		
    public void makeHoly(){
	holy = true;
    }
    public int getDam(){
	return dam;
    }

    public String toString(){
	if(!holy){
	    return name + " - " + dam + " damage";
	} else {
	    return name + " - " + dam + " holy damage";
	}
    }
}
