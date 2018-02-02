package com.scaleset.cfbuilder.iam;

import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Resource;

/**
 Constructs a <tt>User<tt> to create a user in your CloudFormation template.
 Type: <tt>AWS::IAM::User<tt>
 Documentation: https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-iam-user.html
 */
@Type("AWS::IAM::User")
public interface User extends Resource {

    User path(Object value);

    User groups(Object... value);

    User loginProfile(Object profile);

    User policies(Object... policies);
}
