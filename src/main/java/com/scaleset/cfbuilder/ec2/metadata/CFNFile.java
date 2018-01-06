package com.scaleset.cfbuilder.ec2.metadata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Class representing Files in the AWS::Cloudformation::Init
 */
@JsonPropertyOrder({"content", "source", "encoding", "mode", "owner", "group"})
public class CFNFile {

    @JsonProperty("content")
    public String content;

    @JsonProperty ("source")
    public String source;

    @JsonProperty("encoding")
    public String encoding;

    @JsonProperty("mode")
    public String mode;

    @JsonProperty("owner")
    public String owner;

    @JsonProperty("group")
    public String group;

    // The id represents the path where the file will be stored
    @JsonIgnore
    public String id;

    /**
     * Creates a <tt>CFNFile<tt> with the given id.
     *
     * @param id for the <tt>CFNFile<tt> to be created
     */
    public CFNFile(String id) {
        this.id = id;
    }

    //TODO: Make content and source mutually exclusive
    public CFNFile setContent(String content) {
        this.content = content;
        return this;
    }

    public CFNFile setSource(String source) {
        this.source = source;
        return this;
    }

    public CFNFile setEncoding(String encoding) {
        this.encoding = encoding;
        return this;
    }

    public CFNFile setMode(String mode) {
        this.mode = mode;
        return this;
    }

    public CFNFile setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public CFNFile setGroup(String group) {
        this.group = group;
        return this;
    }

    public String getId() {
        return this.id;
    }
}
