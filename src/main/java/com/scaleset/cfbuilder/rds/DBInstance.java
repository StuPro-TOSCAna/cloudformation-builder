package com.scaleset.cfbuilder.rds;

import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Taggable;
import com.scaleset.cfbuilder.ec2.SecurityGroup;

@Type("AWS::RDS::DBInstance")
public interface DBInstance extends Taggable {

    DBInstance engine(Object engine);

    DBInstance dbName(String dbName);

    DBInstance masterUsername(String masterUsername);

    DBInstance masterUserPassword(String masterUserPassword);

    DBInstance dbInstanceClass(Object dbInstanceClass);

    DBInstance allocatedStorage(int allocatedStorage);

    DBInstance storageType(Object storageType);

    DBInstance vpcSecurityGroups(SecurityGroup... values);

    default DBInstance name(String name) {
        tag("Name", name);
        return this;
    }

}
