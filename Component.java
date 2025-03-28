import javax.swing.JComponent;

public class Component {
   private  JComponent core;
   private String position;
    public Component(JComponent core, String position) {
        this.core = core;
        this.positon = position;
    }

    public JComponent getCore() {
        return core;
    }

    public String getPosition() {
        return position;
    }
}
