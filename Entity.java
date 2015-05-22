public interface Entity{
    //handles the characters (player and enemy)
    private char sym; //the character's representation;
    private int health;
    private int mana;
    private String name;
    private String[] attacks;
    private int atk;
    private int def;
    public void attack(Entity e);
    public int damCalc(Entity e); 
}
