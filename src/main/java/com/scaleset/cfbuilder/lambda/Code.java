package com.scaleset.cfbuilder.lambda;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Code {
    @JsonProperty("S3Bucket")
    public String s3Bucket;

    @JsonProperty("S3Key")
    public String s3Key;

    @JsonProperty("S3ObjectVersion")
    public String s3ObjectVersion;

    @JsonProperty("ZipFile")
    public Object zipFile;

    public Code(String zipFile) {
        this.zipFile = zipFile;
    }

    public Code() {
    }

    public Code setS3Bucket(String s3Bucket) {
        this.s3Bucket = s3Bucket;
        return this;
    }

    public Code setS3Key(String s3Key) {
        this.s3Key = s3Key;
        return this;
    }

    public Code setS3ObjectVersion(String s3ObjectVersion) {
        this.s3ObjectVersion = s3ObjectVersion;
        return this;
    }

    public Code setZipFile(Object zipFile) {
        this.zipFile = zipFile;
        return this;
    }
}
