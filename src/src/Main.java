import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MFrame frame = new MFrame();
            frame.setVisible(true);
        });
    }
}