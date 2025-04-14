package com.appvenir.core.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.appvenir.core.ActiveProjectDetails;
import com.appvenir.core.PackageName;
import com.appvenir.core.action.CreatePackageAction;
import com.appvenir.core.model.WorkspaceTemplate;
import com.appvenir.infrastructure.system.logger.Logger;
import com.appvenir.init.CliContext;
import com.appvenir.utils.Condition;
import com.appvenir.utils.StringUtils;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
    name = "package"
)
public class PackageCommand extends Reversable implements Runnable{

    @Parameters(
        index = "0",
        description = "package id"
    )
    private String packageId;

    @Option(
        names = {"-f", "--file"},
        description = "File names"
    )
    private String fileNames;

    @Override
    public void run() {
        ActiveProjectDetails activeProjectDetails = CliContext.getActiveProjectDetails();
        WorkspaceTemplate workspaceTemplate = CliContext.getWorkspaceTemplate();
        Map<String, String> packages = workspaceTemplate.getPackages();

        String name = packages.get(this.packageId);
        Condition.notNullOrEmpty(name, "Could not find package name with the provided id: " + this.packageId);

        PackageName packageName = PackageName.create(name);
        List<String> javaFiles = new ArrayList<>();
        String[] splitJavaFiles = fileNames.split(",");

        for(String javaFile : splitJavaFiles)
        {
            javaFiles.add(appendPackageId(javaFile, this.packageId) + ".java");
        }

        CreatePackageAction createPackageAction = new CreatePackageAction(packageName, javaFiles, activeProjectDetails.getAppRootPath());
        try {
            if(!rollback)
            {
                createPackageAction.execute();
            } else {
                createPackageAction.undo();
            }
        } catch (Exception e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
        }

    }

    private String appendPackageId(String fileName, String packageId)
    {
        String lastName = StringUtils.capitalizeFirstLetter(packageId);
        return fileName + lastName;
    }
    
}
