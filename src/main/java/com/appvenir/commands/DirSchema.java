package com.appvenir.commands;

import com.appvenir.infrastructure.system.logger.Logger;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(
    name = "dir"
    )
public class DirSchema implements Runnable {

    @Parameters(
        index="0",
        description="Schema id"
    )
    private String schemaId;

    @Override
    public void run() {

        Logger.info(schemaId);
    }
    
}
