package com.appvenir.init;

import com.appvenir.core.ActiveProjectDetails;
import com.appvenir.core.model.WorkspaceTemplate;
import com.appvenir.infrastructure.config.CliConfig;
import com.appvenir.infrastructure.system.logger.Logger;

public class CliContext {
    private static boolean initialized = false;
    private static CliConfig cliConfig;
    private static WorkspaceTemplate workspaceTemplate;
    private static ActiveProjectDetails activeProjectDetails;

    private CliContext() {}

    private static void setCliConfig(CliConfig currentCliConfig)
    {
        if(cliConfig == null)
        {
            cliConfig = currentCliConfig;
        }
    }

    private static void setWorkspaceTemplate(WorkspaceTemplate currentWorkspaceTemplate)
    {
        if(workspaceTemplate == null)
        {
            workspaceTemplate = currentWorkspaceTemplate;
        }
    }

    private static void setActiveProjectDetails(ActiveProjectDetails currentActiveProjectDetails)
    {
        if(activeProjectDetails == null)
        {
            activeProjectDetails = currentActiveProjectDetails;
        }
    }

    public static void initialize(CliConfig currentCliConfig, WorkspaceTemplate currentWorkspaceTemplate, ActiveProjectDetails activeProjectDetails)
    {
        if(!initialized)
        {
            setCliConfig(currentCliConfig);
            setWorkspaceTemplate(currentWorkspaceTemplate);
            setActiveProjectDetails(activeProjectDetails);
        }
        else 
        {
            Logger.warn("CliContext has already been initialized.");
        }
    }

    public static CliConfig getCliConfig() {
        if (cliConfig == null) {
            throw new IllegalStateException("CliContext has not been initialized.");
        }
        return cliConfig;
    }

    public static WorkspaceTemplate getWorkspaceTemplate() {
        if (workspaceTemplate == null) {
            throw new IllegalStateException("CliContext has not been initialized.");
        }
        return workspaceTemplate;
    }

    public static ActiveProjectDetails getActiveProjectDetails() {
        if (activeProjectDetails == null) {
            throw new IllegalStateException("CliContext has not been initialized.");
        }
        return activeProjectDetails;
    }
    
}
