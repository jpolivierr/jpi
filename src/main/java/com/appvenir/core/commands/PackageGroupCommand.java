package com.appvenir.core.commands;

import java.util.NoSuchElementException;
import com.appvenir.core.ActiveProjectDetails;
import com.appvenir.core.action.CreatePackageGroupAction;
import com.appvenir.core.model.PackageGroup;
import com.appvenir.core.model.WorkspaceTemplate;
import com.appvenir.init.CliContext;
import com.appvenir.system.logger.Logger;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
    name = "packages"
)
public class PackageGroupCommand extends Reversable implements Runnable{

    @Parameters(
        index = "0",
        description = "package group id"
    )
    private String packageGroupId;

    @Option(
        names = {"-f", "--file"},
        description = "File names"
    )
    private String fileNames;

    @Option(
        names = {"--inverse"},
        description = "Inverse"
    )
    private boolean isInverted;

    @Override
    public void run() {
        ActiveProjectDetails activeProjectDetails = CliContext.getActiveProjectDetails();
        WorkspaceTemplate workspaceTemplate = CliContext.getWorkspaceTemplate();

        PackageGroup packageGroup = workspaceTemplate.getPackageGroup(packageGroupId)
                    .orElseThrow(() -> new NoSuchElementException("Could not find a package group with id: " + packageGroupId));
          
        String[] splitFileNames = fileNames == null || fileNames.isBlank() ? new String[0] : fileNames.split(",");
        CreatePackageGroupAction createPackageGroupAction = new CreatePackageGroupAction(packageGroup.getPackages(), splitFileNames, activeProjectDetails.getAppRootPath(), isInverted);
        try {
            if(!rollback)
            {
                createPackageGroupAction.execute();
            } else {
                createPackageGroupAction.undo();
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
        }

    }
    
}