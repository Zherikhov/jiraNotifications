package ru.finam.jira.webwork;

import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.atlassian.sal.api.user.UserManager;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atlassian.jira.web.action.JiraWebActionSupport;

import javax.inject.Inject;

@Slf4j
@Data
public class WebworkTestAction extends JiraWebActionSupport {
    //private static final Logger log = LoggerFactory.getLogger(WebworkTestAction.class);
    //private final UserManager userManager;
    //private final JiraAuthenticationContext jiraAuthenticationContext;

    private final PluginSettings pluginSettings;
    private static final String PLUGIN_STORAGE_KEY = "ru.finam.jiraNotifications";
    private String parameter1;
    private String parameter2;

    @Inject
    public WebworkTestAction(@ComponentImport PluginSettingsFactory pluginSettingsFactory) {
        this.pluginSettings = pluginSettingsFactory.createGlobalSettings();
        this.parameter1 = pluginSettings.get(PLUGIN_STORAGE_KEY + ".parameter1") == null ? "no value" : pluginSettings.get(PLUGIN_STORAGE_KEY + ".parameter1").toString();
        this.parameter2 = pluginSettings.get(PLUGIN_STORAGE_KEY + ".parameter2") == null ? "no value" : pluginSettings.get(PLUGIN_STORAGE_KEY + ".parameter2").toString();
    }

    @Override
    public String execute() throws Exception {

        return SUCCESS;
    }

    public String doSave() {
        this.pluginSettings.put(PLUGIN_STORAGE_KEY + ".parameter1", this.parameter1);
        this.pluginSettings.put(PLUGIN_STORAGE_KEY + ".parameter2", this.parameter2);
        return SUCCESS;
    }

    public String doClear() {
        return SUCCESS;
    }

    public String getParameter1() {
        return parameter1;
    }

    public void setParameter1(String parameter1) {
        this.parameter1 = parameter1;
    }

    public String getParameter2() {
        return parameter2;
    }

    public void setParameter2(String parameter2) {
        this.parameter2 = parameter2;
    }
}
