package com.scaleset.cfbuilder.ec2.metadata;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CFNInit {

    private static final String CONFIG_SETS = "configSets";

    public Map<String, Configs> configs;

    @JsonIgnore
    //public Map<String, ArrayList<String>> configSets;

    /**
     * Add the configset with a list
     * @param configSet
     */
    public CFNInit(String configSet) {
        this.configs = new HashMap<>();
        //this.configSets = new HashMap<>();
        //this.configSets.put(configSet, new ArrayList<>());
        this.configs.put(CONFIG_SETS, new ConfigSets(configSet));

    }

    /**
     * Add the config to the configset if the configset exists
     * @param configSet
     * @param config
     * @return
     */
    public CFNInit addConfig(String configSet, Config config) {
        ConfigSets configSets = (ConfigSets) this.configs.get(CONFIG_SETS);
        configSets.addConfig(configSet, config.getId());
        this.configs.put(config.getId(), config);
        return this;
    }

    public Map<String, Configs> getConfigs(){
        return this.configs;
    }

    private class ConfigSets implements Configs{

        public Map<String, List<String>> map;

        public ConfigSets(String configSet){
            this.map = new HashMap<>();
            this.map.put(configSet, new ArrayList<>());
        }
        public void addConfig(String configSet, String config) {
            if (this.map.containsKey(configSet)){
                //Add String to configset list
                this.map.get(configSet).add(config);
            } else {
                throw new IllegalArgumentException("Config set " + configSet + "does not exist");
            }
        }
    }
}
