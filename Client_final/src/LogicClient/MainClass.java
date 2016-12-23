package LogicClient;

import GUI.UIinicial;

/**
 * Starts the GUI
 *
 * @author ricar
 */
public class MainClass {

    /**
     * Calls the UI and starts the interfaces
     *
     * @param args
     */
    public static void main(String[] args) {
        UIinicial login = new UIinicial();
        login.setVisible(true);
    }
}
