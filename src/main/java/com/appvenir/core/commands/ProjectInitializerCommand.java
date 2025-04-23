package com.appvenir.core.commands;

import picocli.CommandLine.Command;

@Command(
    name = "init",
    subcommands = SpringBootInitializerCommand.class
)
public class ProjectInitializerCommand implements Runnable{


    @Override
    public void run() {
        System.out.println("Please select from sub command.");
    }   
    
}
