package com.scaleset.cfbuilder.ec2.networkinterface;

import com.scaleset.cfbuilder.ec2.NetworkInterface;

/**
 Constructs a {@code PrivateIpAddressSpecification} to be used in a {@link NetworkInterface}.

 @see <a href=" https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-ec2-network-interface-privateipspec.html">Documentation Reference</a> */
public class PrivateIpAddressSpecification {

    private String privateIpAddress;

    private boolean Primary;

    public String getPrivateIpAddress() {
        return privateIpAddress;
    }

    public void setPrivateIpAddress(String privateIpAddress) {
        this.privateIpAddress = privateIpAddress;
    }

    public PrivateIpAddressSpecification privateIpAddress(String privateIpAddress) {
        this.privateIpAddress = privateIpAddress;
        return this;
    }

    public boolean isPrimary() {
        return Primary;
    }

    public void setPrimary(boolean primary) {
        Primary = primary;
    }

    public PrivateIpAddressSpecification primary(boolean primary) {
        Primary = primary;
        return this;
    }
}
