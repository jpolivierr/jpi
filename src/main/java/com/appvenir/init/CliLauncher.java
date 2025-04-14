package com.appvenir.init;

import com.appvenir.core.ActiveProjectDetails;
import com.appvenir.core.ActiveProjectDetailsFactory;
import com.appvenir.core.model.WorkspaceTemplate;
import com.appvenir.infrastructure.config.CliConfig;
import com.appvenir.infrastructure.loaders.ConfigLoader;
import com.appvenir.infrastructure.loaders.WorkspaceTemplateLoader;
import com.appvenir.infrastructure.system.logger.Logger;
import com.appvenir.infrastructure.system.parcers.file.YamlParser;

public class CliLauncher {

    private static CliLauncher instance;
    private static boolean hasRun = false;

    private CliLauncher(){}

    private void run() {
        String configFile = "/Users/Fred/cli/jpi/config.properties";

        ConfigLoader configLoader = ConfigLoader.getInstance(configFile);
        CliConfig cliConfig = configLoader.getCliConfig();
        
        WorkspaceTemplateLoader workspaceTemplateLoader = WorkspaceTemplateLoader.getInstance(YamlParser.newInstant());
        WorkspaceTemplate workspaceTemplate = workspaceTemplateLoader.getWorkspaceTemplate(cliConfig.getWorkspaceTemplatePath(), WorkspaceTemplate.class);

        String projectRootPath = System.getProperty("user.dir");
        ActiveProjectDetailsFactory activeProjectDetailsFactory = ActiveProjectDetailsFactory.newInstance(projectRootPath, cliConfig);
        ActiveProjectDetails activeProjectDetails = activeProjectDetailsFactory.createFromProject();

        CliContext.initialize(cliConfig, workspaceTemplate, activeProjectDetails);
    }

    public static synchronized void launch() {
        if (!hasRun) {
            getInstance().run();
            hasRun = true;
        } else {
            Logger.warn("CLI has already been launched.");
        }
    }

    private static CliLauncher getInstance() {
        if (instance == null) {
            instance = new CliLauncher();
        }
        return instance;
    }
}


