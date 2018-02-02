package com.scaleset.cfbuilder.iam;

import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Resource;

@Type("AWS::IAM::Policy")
public interface Policy extends Resource {

    Policy groups(Object... groups);

    Policy policyDocument(PolicyDocument value);

    Policy policyName(Object value);

    Policy roles(Object... roles);

    Policy users(Object... users);
}
