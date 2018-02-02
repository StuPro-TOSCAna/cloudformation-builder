package com.scaleset.cfbuilder.iam;

import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Resource;

/**
 Constructs a `Role` to enable applications running on an EC2 instance to securely access AWS resources.
 Type: `AWS::IAM::Role`
 Documentation: https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-resource-iam-role.html
 */
@Type("AWS::IAM::Role")
public interface Role extends Resource {

    Role path(Object value);

    Role assumeRolePolicyDocument(Object value);

    Role policies(Object... policies);
}
