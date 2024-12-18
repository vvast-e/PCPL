package team.spirit.metromapapp.model;

import javafx.scene.image.WritableImage;
import team.spirit.metromapapp.utils.algo.MyEdge;
import team.spirit.metromapapp.utils.algo.MyVertex;
import team.spirit.metromapapp.utils.algo.Vertex;
import team.spirit.metromapapp.utils.draw.Painter;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private static Model INSTANCE;

    private Model() {
    }

    public static Model getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Model();
        }
        return INSTANCE;
    }

    private final String pathDataBase = "jdbc:sqlite:src/main/java/team/spirit/metromapapp/model/metro.db";


    public List<MyEdge> Graph;
    public List<MyVertex> Way;

    public WritableImage image;
    public List<MyVertex> AllStantions;
    public List<MyVertex> StantionsTo;
    public List<MyVertex> StantionsFrom;

    public List<String> getNamesStantions(List<MyVertex> Stantions) {
        List<String> names = new ArrayList<>();
        for (MyVertex vertex : Stantions) {
            names.add(vertex.getName() + " " + vertex.getColor());
        }
        return names;
    }

    public String getPathDataBase() {
        return pathDataBase;
    }
}