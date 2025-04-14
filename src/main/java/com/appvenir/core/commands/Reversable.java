package com.appvenir.core.commands;

import picocli.CommandLine.Option;

public class Reversable {

    @Option(
        names = {"-r", "--rollback"},
        description = "Rollback changes",
        defaultValue = "false"
        )
    protected boolean rollback;
    
}
