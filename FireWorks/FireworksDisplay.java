import ecs100.*;
import java.awt.Color;
import java.util.*;


/** FireworksDisplay   */

public class FireworksDisplay{

    public void setupGUI(){
        UI.addButton("Go", this::runFireworks);
        UI.addButton("Quit", UI::quit);
    }

    public void runFireworks(){
        Firework f1 = this.makeFirework();
        Firework f2 = this.makeFirework();
        while (true){
            f1.step();
            f2.step();
            if (f1.isFinished()){
                f1 = this.makeFirework();
            }
            if (f2.isFinished()){
                f2 = this.makeFirework();
            }
            UI.clearGraphics();
            UI.setColor(Color.black);
            UI.fillRect(0, 0, 900, 700);
            f1.draw();
            f2.draw();
            UI.sleep(50);            
        }

    }

    public Firework makeFirework(){
        double x = 50+Math.random()*600;
        Color c = Color.getHSBColor((float)Math.random(), 1.0f, 1.0f);
        double height = 100+Math.random()*300;
        return new Firework(x, c, height);
        
    }

    public static void main(String[] args){
        new FireworksDisplay().setupGUI();
    }
}