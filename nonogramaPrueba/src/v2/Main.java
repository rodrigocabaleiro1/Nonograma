package v2;

//import vista.MenuInicial;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuInicial().setVisible(true));
    
    }
}
