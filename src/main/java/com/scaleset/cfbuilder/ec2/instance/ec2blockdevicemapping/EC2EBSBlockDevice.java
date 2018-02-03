package com.scaleset.cfbuilder.ec2.instance.ec2blockdevicemapping;

/**
 Constructs a <tt>EC2EBSBlockDevice</tt> to be embedded in a <tt>EC2BlockDeviceMapping</tt>.
 Documentation: https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-ec2-blockdev-template.html
 */
public class EC2EBSBlockDevice {
    private boolean deleteOnTermination;
    private boolean encrypted;
    private int iops;
    private String snapshotId;
    private String volumeSize;
    private String volumeType;

    public boolean isDeleteOnTermination() {
        return deleteOnTermination;
    }

    public void setDeleteOnTermination(boolean deleteOnTermination) {
        this.deleteOnTermination = deleteOnTermination;
    }

    public EC2EBSBlockDevice deleteOnTermination(boolean deleteOnTermination) {
        this.deleteOnTermination = deleteOnTermination;
        return this;
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    public void setEncrypted(boolean encrypted) {
        this.encrypted = encrypted;
    }

    public EC2EBSBlockDevice encrypted(boolean encrypted) {
        this.encrypted = encrypted;
        return this;
    }

    public int getIops() {
        return iops;
    }

    public void setIops(int iops) {
        this.iops = iops;
    }

    public EC2EBSBlockDevice iops(int iops) {
        this.iops = iops;
        return this;
    }

    public String getSnapshotId() {
        return snapshotId;
    }

    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }

    public EC2EBSBlockDevice snapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
        return this;
    }

    public String getVolumeSize() {
        return volumeSize;
    }

    public void setVolumeSize(String volumeSize) {
        this.volumeSize = volumeSize;
    }

    public EC2EBSBlockDevice volumeSize(String volumeSize) {
        this.volumeSize = volumeSize;
        return this;
    }

    public String getVolumeType() {
        return volumeType;
    }

    public void setVolumeType(String volumeType) {
        this.volumeType = volumeType;
    }

    public EC2EBSBlockDevice volumeType(String volumeType) {
        this.volumeType = volumeType;
        return this;
    }
}
