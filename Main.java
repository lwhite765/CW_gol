package CW_gol;


/**
 * The class containing the main method
 *
 * @author Logan White
 */
public class Main {
    public static void main(String[] args) {
        final String TITLE = "Conway's game of life";
        final int WIDTH = 100;
        final int HEIGHT = 100;

        Grid grid = new Grid(WIDTH, HEIGHT);
        //ControlPad controlPad = new ControlPad() TODO add callbacks
        Component components[] = {
            // TODO handle layouts
            new Component(grid, ""),
            //new Component(controlPad, ""),
        };

        new MainFrame(components, TITLE);
    }
}
