package LogicServer;

public class Pair {

 
    private int id;
    private Game value;
 
    /**
     * 
     * @param id
     * @param value 
     */
    public Pair(int id, Game value) {
        this.id = id;
        this.value = value;
    }

    /**
     * 
     * @param id 
     */
    public void setKey(int id) { this.id = id; }
    public void setValue(Game value) { this.value = value; }
    public int getKey()   { return id; }
    public Game getValue() { return value; }
}