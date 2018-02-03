package com.scaleset.cfbuilder.ec2.instance;

/**
 Constructs a <tt>CreditSpecification</tt> to specify the credit option for CPU usage of a T2 instance.
 Property of the EC2 <tt>Instance</tt> resource.
 Documentation: https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-ec2-instance-creditspecification.html
 */
public class CreditSpecification {
    private String cPUCredits;

    public String getcPUCredits() {
        return cPUCredits;
    }

    public void setcPUCredits(String cPUCredits) {
        this.cPUCredits = cPUCredits;
    }

    public CreditSpecification cPUCredits(String cPUCredits) {
        this.cPUCredits = cPUCredits;
        return this;
    }
}
