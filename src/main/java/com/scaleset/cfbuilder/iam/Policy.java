package com.scaleset.cfbuilder.iam;

import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Resource;

/**
 Constructs a `Policy` to associate an IAM policy with IAM users, roles, or groups.
 Type: `AWS::IAM::Policy`
 Documentation: https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-resource-iam-policy.html
 */
@Type("AWS::IAM::Policy")
public interface Policy extends Resource {

    Policy groups(Object... groups);

    Policy policyDocument(PolicyDocument value);

    Policy policyName(Object value);

    Policy roles(Object... roles);

    Policy users(Object... users);
}
