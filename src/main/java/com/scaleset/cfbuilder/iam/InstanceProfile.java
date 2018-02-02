package com.scaleset.cfbuilder.iam;

import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Resource;

/**
 Constructs an <tt>InstanceProfile<tt> to create an AWS Identity and Access Management (IAM)
 instance profile that can be used with IAM roles for EC2 instances.
 Type: <tt>AWS::IAM::InstanceProfile<tt>
 Documentation: https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-resource-iam-instanceprofile.html
 */
@Type("AWS::IAM::InstanceProfile")
public interface InstanceProfile extends Resource {

    InstanceProfile path(Object value);

    InstanceProfile roles(Object... values);

    InstanceProfile instanceProfileName(Object value);
}
