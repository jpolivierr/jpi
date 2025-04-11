package com.appvenir.init;

import com.appvenir.core.model.Schemas;
import com.appvenir.infrastructure.config.CliConfig;
import com.appvenir.infrastructure.system.logger.Logger;

public class CliContext {
    private static boolean initialized = false;
    private static CliConfig cliConfig;
    private static Schemas schemas;

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

    public static void initialize(CliConfig currentCliConfig, Schemas currentSchemas)
    {
        if(!initialized)
        {
            setCliConfig(currentCliConfig);
            setSchemas(currentSchemas);
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
    
}
