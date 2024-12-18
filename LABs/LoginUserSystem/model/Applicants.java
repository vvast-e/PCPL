package model;

import java.util.ArrayList;

public class Applicants extends ArrayList<Observer> {

    public void addApplicat(Observer Applicat) {
        this.add(Applicat);
    }

    public void removeApplicat(Observer Applicat) {
        this.remove(Applicat);
    }
}
