package com.scaleset.cfbuilder.iam;

import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Resource;

@Type("AWS::IAM::InstanceProfile")
public interface InstanceProfile extends Resource{

    InstanceProfile path(Object value);

    InstanceProfile roles(Object... values);

    InstanceProfile instanceProfileName(Object value);
}
