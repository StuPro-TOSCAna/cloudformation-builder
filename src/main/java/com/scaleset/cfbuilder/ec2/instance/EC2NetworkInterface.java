package com.scaleset.cfbuilder.ec2.instance;

import java.util.ArrayList;
import java.util.List;

import com.scaleset.cfbuilder.ec2.Instance;
import com.scaleset.cfbuilder.ec2.networkinterface.Ipv6Address;
import com.scaleset.cfbuilder.ec2.networkinterface.PrivateIpAddressSpecification;

/**
 Constructs a {@code EC2NetworkInterface} to be attached to an EC2 {@link Instance}.

 @see <a href=" https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-ec2-network-iface-embedded.html">Documentation Reference</a> */
public class EC2NetworkInterface {
    private boolean associatePublicIpAddress;
    private boolean deleteOnTermination;
    private String description;
    private String deviceIndex;
    private List<String> groupSet;
    private String networkInterfaceId;
    private int ipv6AddressCount;
    private List<Ipv6Address> ipv6Addresses;
    private String privateIpAddress;
    private List<PrivateIpAddressSpecification> privateIpAddresses;
    private int secondaryPrivateIpAddressCount;
    private String subnetId;

    public EC2NetworkInterface() {
        this.groupSet = new ArrayList<>();
        this.ipv6Addresses = new ArrayList<>();
        this.privateIpAddresses = new ArrayList<>();
    }

    public boolean isAssociatePublicIpAddress() {
        return associatePublicIpAddress;
    }

    public void setAssociatePublicIpAddress(boolean associatePublicIpAddress) {
        this.associatePublicIpAddress = associatePublicIpAddress;
    }

    public EC2NetworkInterface associatePublicIpAddress(boolean associatePublicIpAddress) {
        this.associatePublicIpAddress = associatePublicIpAddress;
        return this;
    }

    public boolean isDeleteOnTermination() {
        return deleteOnTermination;
    }

    public void setDeleteOnTermination(boolean deleteOnTermination) {
        this.deleteOnTermination = deleteOnTermination;
    }

    public EC2NetworkInterface deleteOnTermination(boolean deleteOnTermination) {
        this.deleteOnTermination = deleteOnTermination;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EC2NetworkInterface description(String description) {
        this.description = description;
        return this;
    }

    public String getDeviceIndex() {
        return deviceIndex;
    }

    public void setDeviceIndex(String deviceIndex) {
        this.deviceIndex = deviceIndex;
    }

    public EC2NetworkInterface deviceIndex(String deviceIndex) {
        this.deviceIndex = deviceIndex;
        return this;
    }

    public List<String> getGroupSet() {
        return groupSet;
    }

    public void setGroupSet(List<String> groupSet) {
        this.groupSet = groupSet;
    }

    public EC2NetworkInterface groupSet(List<String> groupSet) {
        this.groupSet = groupSet;
        return this;
    }

    public EC2NetworkInterface addGroupSet(String groupID) {
        this.groupSet.add(groupID);
        return this;
    }

    public String getNetworkInterfaceId() {
        return networkInterfaceId;
    }

    public void setNetworkInterfaceId(String networkInterfaceId) {
        this.networkInterfaceId = networkInterfaceId;
    }

    public EC2NetworkInterface networkInterfaceId(String networkInterfaceId) {
        this.networkInterfaceId = networkInterfaceId;
        return this;
    }

    public int getIpv6AddressCount() {
        return ipv6AddressCount;
    }

    public void setIpv6AddressCount(int ipv6AddressCount) {
        this.ipv6AddressCount = ipv6AddressCount;
    }

    public EC2NetworkInterface ipv6AddressCount(int ipv6AddressCount) {
        this.ipv6AddressCount = ipv6AddressCount;
        return this;
    }

    public List<Ipv6Address> getIpv6Addresses() {
        return ipv6Addresses;
    }

    public void setIpv6Addresses(List<Ipv6Address> ipv6Addresses) {
        this.ipv6Addresses = ipv6Addresses;
    }

    public EC2NetworkInterface ipv6Addresses(List<Ipv6Address> ipv6Addresses) {
        this.ipv6Addresses = ipv6Addresses;
        return this;
    }

    public EC2NetworkInterface addIpv6Addresses(Ipv6Address ipv6Address) {
        this.ipv6Addresses.add(ipv6Address);
        return this;
    }

    public String getPrivateIpAddress() {
        return privateIpAddress;
    }

    public void setPrivateIpAddress(String privateIpAddress) {
        this.privateIpAddress = privateIpAddress;
    }

    public EC2NetworkInterface privateIpAddress(String privateIpAddress) {
        this.privateIpAddress = privateIpAddress;
        return this;
    }

    public List<PrivateIpAddressSpecification> getPrivateIpAddresses() {
        return privateIpAddresses;
    }

    public void setPrivateIpAddresses(List<PrivateIpAddressSpecification> privateIpAddresses) {
        this.privateIpAddresses = privateIpAddresses;
    }

    public EC2NetworkInterface privateIpAddresses(List<PrivateIpAddressSpecification> privateIpAddresses) {
        this.privateIpAddresses = privateIpAddresses;
        return this;
    }

    public EC2NetworkInterface addPrivateIpAddresses(PrivateIpAddressSpecification privateIpAddressSpecification) {
        privateIpAddresses.add(privateIpAddressSpecification);
        return this;
    }

    public int getSecondaryPrivateIpAddressCount() {
        return secondaryPrivateIpAddressCount;
    }

    public void setSecondaryPrivateIpAddressCount(int secondaryPrivateIpAddressCount) {
        this.secondaryPrivateIpAddressCount = secondaryPrivateIpAddressCount;
    }

    public EC2NetworkInterface secondaryPrivateIpAddressCount(int secondaryPrivateIpAddressCount) {
        this.secondaryPrivateIpAddressCount = secondaryPrivateIpAddressCount;
        return this;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public EC2NetworkInterface subnetId(String subnetId) {
        this.subnetId = subnetId;
        return this;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }
}
