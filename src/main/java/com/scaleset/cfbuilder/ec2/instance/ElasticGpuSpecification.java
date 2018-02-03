package com.scaleset.cfbuilder.ec2.instance;

/**
 Constructs a <tt>ElasticGpuSpecification</tt> to accelerate the graphics performance of your applications.
 Property of the EC2 <tt>Instance</tt> resource.
 Documentation: https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-ec2-instance-elasticgpuspecification.html
 */
public class ElasticGpuSpecification {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ElasticGpuSpecification type(String type) {
        this.type = type;
        return this;
    }
}
