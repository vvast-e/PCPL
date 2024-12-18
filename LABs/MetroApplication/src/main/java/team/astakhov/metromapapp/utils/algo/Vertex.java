package team.spirit.metromapapp.utils.algo;

import java.util.ArrayList;
import java.util.List;

public class Vertex extends MyVertex implements Comparable<Vertex> {
    boolean visited;
    List<Edge> neighbours;
    double distance;
    Vertex predecessor;

    public Vertex(String name, String color, int x, int y) {
        super(name, color, x, y);
        neighbours = new ArrayList<>();
        distance = Double.MAX_VALUE;
        predecessor = null;
    }

    public void addNeighbour(Edge e) {
        neighbours.add(e);
    }

    public Vertex getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertex predecessor) {
        this.predecessor = predecessor;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<Edge> getNeighbours() {
        return neighbours;
    }

    @Override
    public int compareTo(Vertex o) {
        return Double.compare(this.distance, o.distance);
    }
}
