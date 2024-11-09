package Model.commands;

import Controller.Controller;

public class RunExampleCommand extends Command{
    private final Controller controller;

    public RunExampleCommand(String key, String desc, Controller controller){
        super(key, desc);
        this.controller = controller;
    }

    @Override
    public void execute(){
            this.controller.executeAll();
        }
    }
