package team.spirit.metromapapp.utils.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    PriorityQueue<Vertex> queue;

    public Dijkstra() {
        queue = new PriorityQueue<>();
    }

    public void compute(Vertex source) {
        source.setDistance(0);
        queue.add(source);

        while (!queue.isEmpty()) {
            Vertex curr = queue.poll();

            for (Edge e : curr.getNeighbours()) {
                Vertex end = e.getEnd();
                double w = e.getWeight();

                if (!end.isVisited()) {
                    if (curr.getDistance() + w < end.getDistance()) {
                        end.setDistance(curr.getDistance() + w);
                        if (queue.contains(end))
                            queue.remove(end);
                        queue.add(end);
                        end.setPredecessor(curr);
                    }

                }

            }
            curr.setVisited(true);

        }

    }

    public List<MyVertex> getPath(Vertex end) {
        List<Vertex> temp = new ArrayList<>();
        while (end != null) {
            temp.add(end);
            end = end.getPredecessor();
        }
        Collections.reverse(temp);
        List<MyVertex> path = new ArrayList<>();
        for (Vertex v : temp) {
            path.add(new MyVertex(v.getName(), v.getColor(), v.getX(), v.getY()));
        }
        return path;
    }
}
