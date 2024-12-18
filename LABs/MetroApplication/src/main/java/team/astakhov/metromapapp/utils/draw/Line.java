package team.spirit.metromapapp.utils.draw;

import team.spirit.metromapapp.utils.algo.MyVertex;

import java.util.List;
import javafx.scene.paint.Color;

public class Line {
    String name;
    public String color;
    Color pColor;
    public List<MyVertex> stations;

    public Line(String name, String color, Color pColor, List<MyVertex> stations) {
        this.name = name;
        this.color = color;
        this.pColor = pColor;
        this.stations = stations;
    }
}
