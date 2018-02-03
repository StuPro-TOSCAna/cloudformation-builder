package com.scaleset.cfbuilder.ec2;

import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Taggable;
import com.scaleset.cfbuilder.ec2.networkinterface.Ipv6Address;
import com.scaleset.cfbuilder.ec2.networkinterface.PrivateIpAddressSpecification;

/**
 Describes a <tt>NetworkInterfact</tt> in an EC2 instance. Provided in a list in the NetworkInterfaces property of <tt>AWS::EC2::Instance</tt>.
 Type: <tt>AWS::EC2::NetworkInterface</tt>
 Documentation: https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-resource-ec2-network-interface.html
 */
@Type("")
public interface NetworkInterface extends Taggable {

    NetworkInterface description(Object value);

    NetworkInterface groupSet(Object... values);

    NetworkInterface ipv6AddressCount(int value);

    NetworkInterface ipv6Addresses(Ipv6Address... values);

    NetworkInterface privateIpAddress(Object value);

    NetworkInterface privateIpAddresses(PrivateIpAddressSpecification... values);

    NetworkInterface secondaryPrivateIpAddressCount(int value);

    NetworkInterface sourceDestCheck(boolean value);

    NetworkInterface subnetId(Object value);
}