package team.spirit.metromapapp.utils.draw;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javafx.util.Pair;
import team.spirit.metromapapp.controllers.MainController;
import team.spirit.metromapapp.model.Model;
import team.spirit.metromapapp.utils.algo.MyVertex;

import javax.imageio.ImageIO;


public class Painter {
    Model model = Model.getInstance();


    String stringColor = "#000000";
    ;
    MainController controller;

    public Painter() {
        controller = MainController.getInstance(model);
        draw();
    }

    public void draw() {
        List<Line> lines = new ArrayList<>();

        List<Pair<String, String>> colors = new ArrayList<>();
        colors.add(new Pair<>("blue", "#2986cc"));
        colors.add(new Pair<>("brown", "#783f04"));
        colors.add(new Pair<>("grey", "#999999"));
        colors.add(new Pair<>("red", "#f44336"));
        colors.add(new Pair<>("green", "#1dce00"));

        //Приводим переменные color для станций и линий к одному типу данных
        for (MyVertex station : model.AllStantions) {
            boolean ExistLine = true;
            for (Line line : lines) {
                if (line.name.equals(station.getColor())) {
                    ExistLine = false;
                }
            }
            if (ExistLine) {
                for (Pair p : colors) {
                    if (p.getKey().equals(station.getColor())) {
                        stringColor = p.getValue().toString();
                    }
                }
                Color color = Color.web(stringColor);
                lines.add(new Line(station.getColor(), stringColor, color, null));
            }
        }

        List<MyVertex> stations = model.AllStantions;
        lines.sort(Comparator.comparing(o -> o.name));

        //Создаем пути(ветки)
        lines.get(0).stations = Arrays.asList(stations.get(8), stations.get(32), stations.get(0), stations.get(28), stations.get(15));
        lines.get(1).stations = Arrays.asList(stations.get(1), stations.get(21), stations.get(30), stations.get(10), stations.get(16), stations.get(33), stations.get(26), stations.get(7), stations.get(23), stations.get(24), stations.get(9), stations.get(12), stations.get(1));
        lines.get(2).stations = Arrays.asList(stations.get(27), stations.get(20), stations.get(34), stations.get(36), stations.get(18), stations.get(2));
        lines.get(3).stations = Arrays.asList(stations.get(31), stations.get(29), stations.get(4), stations.get(5), stations.get(35), stations.get(19));
        lines.get(4).stations = Arrays.asList(stations.get(11), stations.get(13), stations.get(6), stations.get(17), stations.get(22), stations.get(3), stations.get(14), stations.get(25));

        //Создаем ребра для пересадок
        lines.add(new Line("kyrsk-kyrsk", stringColor, Color.BLACK, Arrays.asList(stations.get(15), stations.get(16))));
        lines.add(new Line("pavez-pavez", stringColor, Color.BLACK, Arrays.asList(stations.get(26), stations.get(27))));
        lines.add(new Line("serpux-dob", stringColor, Color.BLACK, Arrays.asList(stations.get(31), stations.get(7))));
        lines.add(new Line("park-park", stringColor, Color.BLACK, Arrays.asList(stations.get(24), stations.get(25))));
        lines.add(new Line("kiev-kiev", stringColor, Color.BLACK, Arrays.asList(stations.get(8), stations.get(9))));
        lines.add(new Line("bel-bel", stringColor, Color.BLACK, Arrays.asList(stations.get(1), stations.get(2))));
        lines.add(new Line("mend-novo", stringColor, Color.BLACK, Arrays.asList(stations.get(19), stations.get(21))));
        lines.add(new Line("green-grey", stringColor, Color.BLACK, Arrays.asList(stations.get(36), stations.get(5))));
        lines.add(new Line("red-green-blue", stringColor, Color.BLACK, Arrays.asList(stations.get(22), stations.get(34), stations.get(28))));
        lines.add(new Line("grey-blue-red", stringColor, Color.BLACK, Arrays.asList(stations.get(4), stations.get(0), stations.get(3))));

        Canvas canvas = new Canvas(800, 750);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Рисуем линии
        for (Line line : lines) {
            gc.setStroke(line.pColor);
            gc.setLineWidth(3);
            for (int i = 0; i < line.stations.size() - 1; i++) {
                MyVertex s1 = line.stations.get(i);
                MyVertex s2 = line.stations.get(i + 1);
                gc.strokeLine(s1.getX(), s1.getY(), s2.getX(), s2.getY());
            }
        }

        // Обработка пути
        if (model.Way != null) {
            Line wayLine = new Line("Way", stringColor, Color.PINK, model.Way);
            gc.setStroke(Color.PURPLE);
            gc.setLineWidth(7);
            for (int i = 0; i < wayLine.stations.size() - 1; i++) {
                MyVertex s1 = wayLine.stations.get(i);
                MyVertex s2 = wayLine.stations.get(i + 1);
                gc.strokeLine(s1.getX(), s1.getY(), s2.getX(), s2.getY());
            }
        }

        // Рисуем станции
        for (MyVertex station : stations) {
            gc.setFill(Color.BLACK);
            gc.fillOval(station.getX() - 5, station.getY() - 5, 10, 10);
            gc.setFill(Color.BLACK);
            gc.fillText(station.getName(), station.getX(), station.getY() - 10);
        }

        // Создаем сцену и окно
        Pane pane = new Pane(canvas);
        pane.getChildren().addAll();
        Scene scene = new Scene(pane, 800, 750);

        model.image = scene.snapshot(null);

    }
}
