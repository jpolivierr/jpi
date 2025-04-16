package com.appvenir.core.commands;

import java.util.NoSuchElementException;

import com.appvenir.core.ActiveProjectDetails;
import com.appvenir.core.action.CreateFileStructureAction;
import com.appvenir.core.model.PathLayout;
import com.appvenir.core.model.WorkspaceTemplate;
import com.appvenir.init.CliContext;
import com.appvenir.system.logger.Logger;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
    name = "layout"
    )
public class PathLayoutCommand implements Runnable {

    @Option(
            names = {"-r", "--rollback"},
            description = "Rollback changes",
            defaultValue = "false"
           )
    private boolean rollback;

    @Parameters(
        index="0",
        description="Schema id"
    )
    private String schemaId;

    @Override
    public void run() {

        ActiveProjectDetails activeProjectDetails = CliContext.getActiveProjectDetails();
        WorkspaceTemplate workspaceTemplate = CliContext.getWorkspaceTemplate();        
        PathLayout pathLayout = workspaceTemplate.getPathLayout(schemaId)
                                .orElseThrow(() -> new NoSuchElementException("Could not find a pathLayout with id: " + schemaId));

        CreateFileStructureAction createFileStructureAction = new CreateFileStructureAction(pathLayout, activeProjectDetails.getAppRootPath());

        try {
            if(!rollback)
            {
                createFileStructureAction.execute();
            } else {
                createFileStructureAction.undo();
            }
            
        } catch (Exception e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
}
