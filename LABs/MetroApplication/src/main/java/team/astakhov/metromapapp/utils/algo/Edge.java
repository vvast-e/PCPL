package team.spirit.metromapapp.utils.algo;

public class Edge {
    Vertex start;
    Vertex end;
    double weight;
    public Edge(double weight, Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    public Vertex getStart() {
        return start;
    }
    public void setStart(Vertex start) {
        this.start = start;
    }
    public Vertex getEnd() {
        return end;
    }
    public void setEnd(Vertex end) {
        this.end = end;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
