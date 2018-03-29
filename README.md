cloudformation-builder
======================
[![Build Status](https://travis-ci.org/StuPro-TOSCAna/cloudformation-builder.svg?branch=master)](https://travis-ci.org/StuPro-TOSCAna/cloudformation-builder)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/1e8da2c529bd4eea95a369bed5f8e582)](https://www.codacy.com/app/stupro-toscana/cloudformation-builder?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=StuPro-TOSCAna/cloudformation-builder&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/StuPro-TOSCAna/cloudformation-builder/branch/master/graph/badge.svg)](https://codecov.io/gh/StuPro-TOSCAna/cloudformation-builder)


CloudFormation-Builder is a Java 8 DSL for creating AWS CloudFormation templates.

Quick Start
-----------

### Dependency

Use [jitpack](jitpack.io) to import this project as a maven dependency.
```xml
<dependency>
    <groupId>com.github.StuPro-TOSCAna</groupId>
    <artifactId>cloudformation-builder</artifactId>
    <version>{releaseVersion/commitHash}</version>
</dependency>
```
Use a release version or simply the hash of a commit to specify the version.

### Example

```java
    class TestModule extends Module {

        public void build() {

            Object instanceType = option("instanceType").orElseGet(
                    () -> strParam("InstanceType").defaultValue("m1.small").description(ns("Instance") + " instance type"));
            Object nodeCount = option("nodeCount").orElseGet(
                    () -> numParam("NodeCount").defaultValue(2).description("Number of elasticsearch nodes to create"));

            Object clusterName = option("clusterName").orElse("elasticsearch");
            Object cidrIp = template.ref("OpenCidrIp");
            Object keyName = template.ref("KeyName");
            Object imageId = template.ref("ImageId");
            Object az = template.ref("MyAZ");
            Object instanceProfile = ref("InstanceProfile");
            Object vpcId = template.ref("VpcId");

            SecurityGroup securityGroup = resource(SecurityGroup.class, "SecurityGroup")
                    .vpcId(vpcId)
                    .ingress(ingress -> ingress.cidrIp(cidrIp), "tcp", 22, 9200, 9300, range(27018, 27019));

            Object groupId = securityGroup.fnGetAtt("GroupId");

            resource(SecurityGroupIngress.class, "SelfReferenceIngress")
                    .sourceSecurityGroupId(groupId)
                    .groupId(groupId)
                    .ipProtocol("tcp")
                    .port(9300);

            resource(Instance.class, "Instance")
                    .name(ns("Instance"))
                    .availabilityZone(az)
                    .keyName(keyName)
                    .imageId(imageId)
                    .instanceProfile(instanceProfile)
                    .instanceType(instanceType)
                    .securityGroupIds(securityGroup);
        }
    }

```

Example is taken from [test](/src/test/java/com/scaleset/cfbuilder/TemplateTest.java).
See `/src/test/java/com/scaleset/cfbuilder/` for more tests containing examples that you can use.

## Contributing
See our [contribution guidelines](CONTRIBUTING.md) for detailed information on how to contribute to the cloudformation-builder.

## Tools
Tools that are used in this project.

- IDE: [IntelliJ](https://www.jetbrains.com/idea/)
- Code generation: [Project Lombok](https://projectlombok.org/)
- UML Modelling: [Lucidchart](https://www.lucidchart.com/)
- Project management: [ZenHub](https://www.zenhub.com/)
- CI: [TravisCI](https://travis-ci.org/StuPro-TOSCAna/TOSCAna)
- Code analysis: [Codacy](https://www.codacy.com/app/stupro-toscana/TOSCAna/dashboard)
- Code coverage: [Codecov](https://codecov.io/gh/StuPro-TOSCAna/TOSCAna), [Get browser extension](https://github.com/codecov/browser-extension)

### License

CloudFormation-Builder is licensed under the [Apache License 2.0](LICENSE).
