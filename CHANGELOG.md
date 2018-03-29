# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- Apache 2.0 licence file
- Jenkinsfile and travis.yml to allow CI
- Jacoco Plugin to the POM
- "AWS::RDS::DBInstance" resource
- Support of EC2s Metadata section
- Expand the functionality of the FN Class
- Add Authentication "AWS::CloudFormation::Authentication"
- Add parts of AWS Identity and Access Management (IAM) like roles, policies and users
- Add full support for "AWS::EC2::Instance", "AWS::EC2::NetworkInterface" and "AWS::EC2::Volume"
- Add basic support for AWS Beanstalk

### Removed
- Unneeded parts in `pom.xml` (signing, outdated and wrong parent)
