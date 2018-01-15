package com.scaleset.cfbuilder.iam;

import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Resource;

/**
 required properties:
 assumeRolePolicyDocument
 */
@Type("AWS::IAM::Role")
public interface Role extends Resource {

    Role path(Object value);

    /**
     required
     */
    Role assumeRolePolicyDocument(AssumeRolePolicyDocument assumeRolePolicyDocument);

    Role policies(Object... policies);

    Role managedPolicyArns(String... values);

    Role roleName(String value);
}
