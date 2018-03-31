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

CloudFormation templates are built within a so-called `Module`. This module gets filled with all the CloudFormation resources needed to build the template.

The following is a quick example on how a CloudFormation template is built with the CloudFormation Builder:

```java
class Ec2withEbsModule extends Module {
    public void build() {
        this.template.setDescription("Ec2 block device mapping");

        EC2EBSBlockDevice ec2EBSBlockDeviceA = new EC2EBSBlockDevice()
                .volumeType("io1")
                .iops(200)
                .deleteOnTermination(false)
                .volumeSize("20");
        EC2BlockDeviceMapping ec2BlockDeviceMappingA = new EC2BlockDeviceMapping()
                .deviceName("/dev/sdm")
                .ebs(ec2EBSBlockDeviceA);

        EC2BlockDeviceMapping ec2BlockDeviceMappingB = new EC2BlockDeviceMapping()
                .deviceName("/dev/sdk")
                .noDevice(false);

        resource(Instance.class, "MyEC2Instance")
                .imageId("ami-79fd7eee")
                .keyName("testkey")
                .blockDeviceMappings(ec2BlockDeviceMappingA, ec2BlockDeviceMappingB);
    }
}
```

> **Note**: The example is taken from the [`InstanceTest`](/src/test/java/com/scaleset/cfbuilder/InstanceTest.java). See `/src/test/java/com/scaleset/cfbuilder/` for more tests containing examples that you can use.

This `Ec2withEbsModule` results in the following CloudFormation template:

```yaml
AWSTemplateFormatVersion: "2010-09-09"
Description: "Ec2 block device mapping"
Resources:
  MyEC2Instance:
    Type: "AWS::EC2::Instance"
    Properties:
      ImageId: "ami-79fd7eee"
      KeyName: "testkey"
      BlockDeviceMappings:
      - DeviceName: "/dev/sdm"
        Ebs:
          DeleteOnTermination: false
          Iops: 200
          VolumeSize: "20"
          VolumeType: "io1"
      - DeviceName: "/dev/sdk"
        NoDevice: false
```

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
