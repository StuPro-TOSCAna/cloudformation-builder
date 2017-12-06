package com.scaleset.cfbuilder.ec2.metadata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Config implements Configs {

    @JsonProperty("commands")
    public Map<String, Command> commands;

    @JsonIgnore
    public String id;

    public Config(String id) {
        this.id = id;
        this.commands = new HashMap<>();
    }

    public Config putCommand(Command command){
        this.commands.put(command.getId(), command);
        return this;
    }

    public String getId() {
        return this.id;
    }

}
