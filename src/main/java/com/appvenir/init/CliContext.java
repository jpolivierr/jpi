package com.appvenir.init;

import com.appvenir.core.ActiveProjectDetails;
import com.appvenir.core.model.Schemas;
import com.appvenir.infrastructure.config.CliConfig;
import com.appvenir.infrastructure.system.logger.Logger;

public class CliContext {
    private static boolean initialized = false;
    private static CliConfig cliConfig;
    private static Schemas schemas;
    private static ActiveProjectDetails activeProjectDetails;

    private CliContext() {}

    private static void setCliConfig(CliConfig currentCliConfig)
    {
        if(cliConfig == null)
        {
            cliConfig = currentCliConfig;
        }
    }

    private static void setSchemas(Schemas currentSchemas)
    {
        if(schemas == null)
        {
            schemas = currentSchemas;
        }
    }

    private static void setActiveProjectDetails(ActiveProjectDetails currentActiveProjectDetails)
    {
        if(activeProjectDetails == null)
        {
            activeProjectDetails = currentActiveProjectDetails;
        }
    }

    public static void initialize(CliConfig currentCliConfig, Schemas currentSchemas, ActiveProjectDetails activeProjectDetails)
    {
        if(!initialized)
        {
            setCliConfig(currentCliConfig);
            setSchemas(currentSchemas);
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

    public static Schemas getSchemas() {
        if (schemas == null) {
            throw new IllegalStateException("CliContext has not been initialized.");
        }
        return schemas;
    }

    public static ActiveProjectDetails getActiveProjectDetails() {
        if (activeProjectDetails == null) {
            throw new IllegalStateException("CliContext has not been initialized.");
        }
        return activeProjectDetails;
    }
    
}
