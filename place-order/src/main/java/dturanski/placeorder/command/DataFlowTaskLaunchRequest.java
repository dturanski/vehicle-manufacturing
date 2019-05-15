package dturanski.placeorder.command;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class DataFlowTaskLaunchRequest {

    @JsonProperty("args")
    private List<String> commandlineArguments = new ArrayList<>();

    @JsonProperty("deploymentProps")
    private Map<String, String> deploymentProperties = new HashMap<>();

    @JsonProperty("name")
    private String taskName;

    public void setCommandlineArguments(List<String> commandlineArguments) {
        this.commandlineArguments = new ArrayList<>(commandlineArguments);
    }

    public List<String> getCommandlineArguments() {
        return this.commandlineArguments;
    }

    public void setDeploymentProperties(Map<String, String> deploymentProperties) {
        this.deploymentProperties = deploymentProperties;
    }

    public Map<String, String> getDeploymentProperties() {
        return this.deploymentProperties;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return this.taskName;
    }

    DataFlowTaskLaunchRequest addCommmandLineArguments(Collection<String> args) {
        this.commandlineArguments.addAll(args);
        return this;
    }
}

