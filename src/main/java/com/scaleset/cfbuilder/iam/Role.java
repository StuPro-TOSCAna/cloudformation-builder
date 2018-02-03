package com.scaleset.cfbuilder.iam;

import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Resource;

/**
 Constructs a <tt>Role<tt> to enable applications running on an EC2 instance to securely access AWS resources.
 Type: <tt>AWS::IAM::Role<tt>
 Documentation: https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-resource-iam-role.html
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
