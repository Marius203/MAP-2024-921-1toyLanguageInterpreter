package View;

import Controller.IController;

public class MainView {
    IController controller;
    
    public MainView(IController controller) {
        this.controller = controller;
    }

    public void run() {
            controller.executeAll();
    }
        
}
