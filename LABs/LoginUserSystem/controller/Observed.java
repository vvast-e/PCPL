package controller;

import model.Observer;

public interface Observed {
    public boolean addObserver(Observer observer);

    public boolean removeObserver(Observer observer);

    public boolean notifyObservers();
}
