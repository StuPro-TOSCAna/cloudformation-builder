package com.scaleset.cfbuilder.ec2.metadata;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

public class CFNInit {

    public Map<String, Config> configs;

    public ConfigSets configSets;

    /**
     * Add the configset with a list
     * @param configSet
     */
    public CFNInit(String configSet) {
        this.configs = new HashMap<>();
        this.configSets = new ConfigSets(configSet);

    }

    /**
     * Add the config to the configset if the configset exists
     *
     * @param configSet
     * @param config
     * @return
     */
    public CFNInit addConfig(String configSet, Config config) {
        this.configSets.addConfig(configSet, config.getId());
        this.configs.put(config.getId(), config);
        return this;
    }

    public Map<String, Config> getConfigs() {
        return this.configs;
    }

    public ConfigSets getConfigSets() {
        return this.configSets;
    }
}
