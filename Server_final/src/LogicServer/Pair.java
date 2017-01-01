package LogicServer;

public class Pair {

 
    private int id;
    private Game value;
 
    /**
     * Constructor
     * @param id
     * @param value 
     */
    public Pair(int id, Game value) {
        this.id = id;
        this.value = value;
    }

    /**
     * Method to set a new key
     * @param id 
     */
    public void setKey(int id) { this.id = id; }
    /**
     * Method to set a new valeu
     * @param value 
     */
    public void setValue(Game value) { this.value = value; }
    /**
     * Method to get the key
     * @return 
     */
    public int getKey()   { return id; }
    /**
     * Method to get the valeu
     * @return 
     */
    public Game getValue() { return value; }
}