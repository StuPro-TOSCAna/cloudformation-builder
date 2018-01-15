package com.scaleset.cfbuilder.lambda;

import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Taggable;

/**
 required properties are:
 code
 handler
 role
 runtime
 */
@Type("AWS::Lambda::Function")
public interface Function extends Taggable {

    /**
     required
     */
    Function code(Object value);

    Function deadLetterConfig(Object value);

    Function description(String value);

    Function environment(Object value);

    Function functionName(String value);

    /**
     required
     */
    Function handler(String value);

    Function kmsKeyArn(String value);

    Function memorySize(Integer value);

    /**
     required
     */
    Function role(Object value);

    /**
     required
     */
    Function runtime(String value);

    Function timeout(Integer value);

    Function tracingConfig(Object value);

    Function vpcConfig(Object value);

    default Function name(String name) {
        tag("Name", name);
        return this;
    }
}
