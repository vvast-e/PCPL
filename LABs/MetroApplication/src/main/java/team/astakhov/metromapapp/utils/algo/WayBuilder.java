package team.spirit.metromapapp.utils.algo;

import javafx.util.Pair;
import team.spirit.metromapapp.controllers.MainController;
import team.spirit.metromapapp.model.Model;

import java.util.ArrayList;
import java.util.List;


public class WayBuilder {
    Model model = Model.getInstance();

    private MainController mainController;

    public WayBuilder() {
        mainController = MainController.getInstance(model);
        model.Graph = MainController.getEdges();
    }

    public List<Vertex> createVertexes() {
        List<Vertex> dg = new ArrayList<>();
        for (Integer id : mainController.getAllId()) {
            dg.add(mainController.getVertexByIdStation(id));
        }
        return dg;
    }

    public Pair<List<MyVertex>, Double> createWay(String from, String to) {
        List<Vertex> dGraph = createVertexes();
        for (MyEdge edge : model.Graph) {
            dGraph.get(edge.getId1() - 1).addNeighbour(new Edge(edge.getTime(), dGraph.get(edge.getId1() - 1), dGraph.get(edge.getId2() - 1)));
            dGraph.get(edge.getId2() - 1).addNeighbour(new Edge(edge.getTime(), dGraph.get(edge.getId2() - 1), dGraph.get(edge.getId1() - 1)));
        }
        Dijkstra dijkstra = new Dijkstra();
        String[] partsFrom = from.split(" ");
        String[] partsTo = to.split(" ");
        int fromIndex = 0, toIndex = 0;
        int index = 0;
        for (Vertex v : dGraph) {
            if (partsFrom[0].equals(v.getName()) && partsFrom[1].equals(v.getColor())) {
                fromIndex = index;
            }
            if (partsTo[0].equals(v.getName()) && partsTo[1].equals(v.getColor())) {
                toIndex = index;
            }
            ++index;
        }
        dijkstra.compute(dGraph.get(fromIndex));
        List<MyVertex> way = dijkstra.getPath(dGraph.get(toIndex));
        return new Pair<>(way, dGraph.get(toIndex).getDistance());
    }
}
