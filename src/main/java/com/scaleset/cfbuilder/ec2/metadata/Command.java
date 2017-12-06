package com.scaleset.cfbuilder.ec2.metadata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Command {

    @JsonProperty("command")
    public String command;

    public Map<String, String> env;

    @JsonIgnore
    public String id;

    public Command(String id, String command){
        this.id = id;
        this.command = command;
    }

    public String getId() {
        return this.id;
    }
}
