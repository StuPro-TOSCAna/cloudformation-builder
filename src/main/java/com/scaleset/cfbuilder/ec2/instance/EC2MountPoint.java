package com.scaleset.cfbuilder.ec2.instance;

/**
 Constructs an <tt>EC2MountPoint</tt> to be used as a property of an EC2 <tt>Instance</tt>.
 Documentation: https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-ec2-mount-point.html
 */
public class EC2MountPoint {
    private String device;
    private String volumeId;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public EC2MountPoint device(String device) {
        this.device = device;
        return this;
    }

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

    public EC2MountPoint volumeId(String volumeId) {
        this.volumeId = volumeId;
        return this;
    }
}
