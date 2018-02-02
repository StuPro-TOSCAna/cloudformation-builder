package com.scaleset.cfbuilder.iam;

import java.util.ArrayList;
import java.util.List;

public class PolicyDocument {
    private String version;
    private String id;
    private List<Statement> statement;

    /**
     Constructs a `PolicyDocument` representing an IAM policy to be used within `Policy` and `InstanceProfile`.
     Documentation: https://docs.aws.amazon.com/IAM/latest/UserGuide/reference_policies.html
     */
    public PolicyDocument() {
        this.statement = new ArrayList<>();
    }

    public String getVersion() {
        return version;
    }

    public PolicyDocument version(String version) {
        this.version = version;
        return this;
    }

    public String getId() {
        return id;
    }

    public PolicyDocument id(String id) {
        this.id = id;
        return this;
    }

    public List<Statement> getStatement() {
        return statement;
    }

    public PolicyDocument statement(List<Statement> statement) {
        this.statement = statement;
        return this;
    }

    public PolicyDocument addStatement(Statement statement) {
        this.statement.add(statement);
        return this;
    }
}
