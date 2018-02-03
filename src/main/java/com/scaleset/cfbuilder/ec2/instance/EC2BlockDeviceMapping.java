package com.scaleset.cfbuilder.ec2.instance;

import java.util.ArrayList;
import java.util.List;

import com.scaleset.cfbuilder.ec2.instance.ec2blockdevicemapping.EC2EBSBlockDevice;

/**
 Constructs an <tt>EC2BlockDeviceMapping</tt> to be embedded în an <tt>Instance</tt> resource.
 Documentation: https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-ec2-blockdev-mapping.html
 */
public class EC2BlockDeviceMapping {
    private String deviceName;
    private List<EC2EBSBlockDevice> ebs;
    private boolean noDevice;
    private String virtualName;

    public EC2BlockDeviceMapping(){
        this.ebs = new ArrayList<>();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public EC2BlockDeviceMapping deviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    public List<EC2EBSBlockDevice> getEbs() {
        return ebs;
    }

    public void setEbs(List<EC2EBSBlockDevice> ebs) {
        this.ebs = ebs;
    }

    public EC2BlockDeviceMapping ebs(List<EC2EBSBlockDevice> ebs) {
        this.ebs = ebs;
        return this;
    }

    public EC2BlockDeviceMapping addEbs(EC2EBSBlockDevice ebd){
        this.ebs.add(ebd);
        return this;
    }

    public boolean isNoDevice() {
        return noDevice;
    }

    public void setNoDevice(boolean noDevice) {
        this.noDevice = noDevice;
    }

    public EC2BlockDeviceMapping noDevice(boolean noDevice) {
        this.noDevice = noDevice;
        return this;
    }

    public String getVirtualName() {
        return virtualName;
    }

    public void setVirtualName(String virtualName) {
        this.virtualName = virtualName;
    }

    public EC2BlockDeviceMapping virtualName(String virtualName) {
        this.virtualName = virtualName;
        return this;
    }
}
