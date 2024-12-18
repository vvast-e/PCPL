package team.spirit.metromapapp;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;
import team.spirit.metromapapp.controllers.MainController;
import team.spirit.metromapapp.model.Model;
import team.spirit.metromapapp.utils.algo.MyVertex;
import team.spirit.metromapapp.utils.algo.WayBuilder;
import team.spirit.metromapapp.utils.draw.Painter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainApplication extends Application {
    static Model model;
    static MainController controller;

    @Override
    public void start(Stage stage) throws IOException {
        Pane pane = new Pane();

        model.AllStantions = MainController.getStantions();
        model.StantionsFrom = model.AllStantions;
        model.StantionsTo = model.AllStantions;

        Label label1 = new Label("Откуда"); //надписи для окон станций
        Label label2 = new Label("Куда");
        label1.setLayoutX(1000);
        label2.setLayoutX(1006.5);
        label1.setLayoutY(50);
        label2.setLayoutY(200);

        //Строки ввода станций
        ComboBox<String> From = new ComboBox<>();
        From.getItems().addAll(model.getNamesStantions(model.StantionsFrom));
        From.setEditable(true);

        From.setOnAction(event -> {
            model.StantionsFrom = MainController.getNameColorStations(From.getValue());
            From.getItems().clear();
            From.getItems().addAll(model.getNamesStantions(model.StantionsFrom));
        });

        ComboBox<String> To = new ComboBox<>();
        To.getItems().addAll(model.getNamesStantions(model.StantionsTo));
        To.setEditable(true);

        To.setOnAction(event -> {
            model.StantionsTo = MainController.getNameColorStations(To.getValue());
            To.getItems().clear();
            To.getItems().addAll(model.getNamesStantions(model.StantionsTo));
        });

        From.setLayoutX(1000);
        From.setLayoutY(100);
        To.setLayoutX(1000);
        To.setLayoutY(250);

        Button to_way = new Button("В путь!");
        to_way.setLayoutX(1000);
        to_way.setLayoutY(350);


        Label way1 = new Label("Время в пути");   //Отображение маршрута
        Label way2 = new Label("Полный маршрут");
        way1.setLayoutX(1000);
        way2.setLayoutX(700);
        way1.setLayoutY(400);
        way2.setLayoutY(600);

        Button move_button = new Button("<->");
        move_button.setLayoutX(1000);
        move_button.setLayoutY(155);

        move_button.setOnMouseClicked(event -> {
            String a1 = From.getValue();
            String a2 = To.getValue();
            From.setValue(a2);
            To.setValue(a1);
        });

        Button clean = new Button("Очистить");
        clean.setLayoutX(1100);
        clean.setLayoutY(155);

        clean.setOnMouseClicked(event -> {
            From.setValue(null);
            To.setValue(null);
        });


        WayBuilder wb = new WayBuilder();
        Painter painter = new Painter();

        ImageView imageView = new ImageView(model.image);
        imageView.setX(10);
        imageView.setY(10);

        to_way.setOnMouseClicked(event -> {
            String fromVertex = From.getValue();
            String toVertex = To.getValue();
            if (fromVertex.isEmpty() || toVertex.isEmpty()) {
                way1.setText("Вы не ввели начало или конец маршрута!");
                way2.setText(" ");
                throw new RuntimeException("Вы не ввели начало или конец маршрута!");
            }
            if (fromVertex.equals(toVertex)) {
                way1.setText("0 min, Вы ввели одинаковые станции");
                way2.setText(" ");
                throw new RuntimeException("Введены одинаковые станции");
            }

            Pair<List<MyVertex>, Double> result = wb.createWay(fromVertex, toVertex);
            model.Way = result.getKey();
            Double dist = result.getValue();

            String wayLine = dist + " minutes       ";

            if (model.Way.size() < 3 && !model.Way.get(0).getColor().equals(model.Way.get(1).getColor())) {
                wayLine += (model.Way.getFirst().getColor() + " -> " + model.Way.get(1).getColor());

            } else if (model.Way.size() < 3 && model.Way.get(0).getColor().equals(model.Way.get(1).getColor())) {
                wayLine += model.Way.getFirst().getColor();
            } else {
                wayLine += model.Way.getFirst().getColor() + " -> ";
                for (int i = 1; i < model.Way.size(); ++i) {
                    if (!Objects.equals(model.Way.get(i - 1).getColor(), model.Way.get(i).getColor())) {
                        wayLine += (model.Way.get(i).getColor() + " -> ");
                    }
                }
                wayLine = wayLine.substring(0, wayLine.length() - 4);
            }

            way1.setText(wayLine);
            wayLine = "";
            for (MyVertex v : model.Way) {
                wayLine += v.getName() + " -> ";
            }
            wayLine = wayLine.substring(0, wayLine.length() - 4);
            way2.setText(wayLine);

            painter.draw();
            imageView.setImage(model.image);

        });


        pane.getChildren().addAll(imageView, label1, label2, From, To, to_way, way1, way2, move_button, clean);
        Scene scene = new Scene(pane, 1500, 800);
        stage.setTitle("Metromap App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        model = Model.getInstance();
        controller = MainController.getInstance(model);
        launch();
    }
}