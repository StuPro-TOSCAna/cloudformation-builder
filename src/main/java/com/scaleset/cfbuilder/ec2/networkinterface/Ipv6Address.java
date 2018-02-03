package com.scaleset.cfbuilder.ec2.networkinterface;

/**
 Constructs a <tt>Ipv6Address</tt> to associate with a <tt>NetworkInterface</tt>.
 Documentation: https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-ec2-networkinterface-ipv6addresses.html
 */
public class Ipv6Address {
    private String ipv6Address;

    public String getIpv6Address() {
        return ipv6Address;
    }

    public void setIpv6Address(String ipv6Address) {
        this.ipv6Address = ipv6Address;
    }

    public Ipv6Address ipv6Address(String ipv6Address) {
        this.ipv6Address = ipv6Address;
        return this;
    }
}
