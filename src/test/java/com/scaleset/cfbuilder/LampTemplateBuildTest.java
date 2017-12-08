package com.scaleset.cfbuilder;

import com.scaleset.cfbuilder.core.Fn;
import com.scaleset.cfbuilder.core.Module;
import com.scaleset.cfbuilder.core.Parameter;
import com.scaleset.cfbuilder.core.Template;
import com.scaleset.cfbuilder.ec2.Instance;
import com.scaleset.cfbuilder.ec2.SecurityGroup;
import com.scaleset.cfbuilder.ec2.UserData;
import com.scaleset.cfbuilder.ec2.metadata.*;
import com.scaleset.cfbuilder.rds.DBInstance;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.assertNotNull;

public class LampTemplateBuildTest extends Module {

    @Test
    public void testTemplateBuilding() throws Exception {

        Template lampTemplate = new Template();

        new LampModule().id("").template(lampTemplate).build();

        assertNotNull(lampTemplate);
        System.err.println(lampTemplate.toString(true));

        //TODO remove this
        try (PrintStream out = new PrintStream(new FileOutputStream("lamp-template.yaml"))) {
            out.print(lampTemplate.toString(true));
        }
    }

    class LampModule extends Module {
        private static final String KEYNAME_DESCRIPTION = "Name of an existing EC2 KeyPair to enable SSH access to the instances";
        private static final String KEYNAME_TYPE = "AWS::EC2::KeyPair::KeyName";
        private static final String KEYNAME_CONSTRAINT_DESCRIPTION = "must be the name of an existing EC2 KeyPair.";

        private static final String CFNINIT_CONFIGSET = "InstallAndRun";
        private static final String CFNINIT_CONFIG_INSTALL = "Install";
        private static final String CFNINIT_CONFIG_CONFIGURE = "Configure";

        public void build() throws Exception {
            String myPHPAppFileContent = "";
            String cfnHupFileContent = "";
            String mySQLCredentialsFileContent = "";
            String cfnAutoReloaderFileContent = "";
            String configureMyPHPAppFileContent = "";
            String mySQLDBMSConfigureFileContent = "";

            try {
                myPHPAppFileContent = "!Sub |\n";
                myPHPAppFileContent += new Scanner(new File("src/test/resources/lamp-template-build-test/myphpapp.php"))
                        .useDelimiter("\\Z")
                        .next();
                mySQLCredentialsFileContent = "!Sub |\n";
                mySQLCredentialsFileContent += new Scanner(new File("src/test/resources/lamp-template-build-test/mysql-credentials.php"))
                        .useDelimiter("\\Z")
                        .next();
                configureMyPHPAppFileContent = "|\n";
                configureMyPHPAppFileContent = new Scanner(new File("src/test/resources/lamp-template-build-test/configure_myphpapp.sh"))
                        .useDelimiter("\\Z")
                        .next();
                mySQLDBMSConfigureFileContent = "|\n";
                mySQLDBMSConfigureFileContent = new Scanner(new File("src/test/resources/lamp-template-build-test/mysql_dbms_configure.sh"))
                        .useDelimiter("\\Z")
                        .next();
            } catch (FileNotFoundException e) {
                System.err.println("File not Found.");
            }

            Parameter keyName = (Parameter) option("KeyName").orElseGet(
                    () -> strParam("KeyName")
                            .type(KEYNAME_TYPE)
                            .description(KEYNAME_DESCRIPTION)
                            .constraintDescription(KEYNAME_CONSTRAINT_DESCRIPTION));

            Object cidrIp = "0.0.0.0/0";
            Object keyNameVar = template.ref("KeyName");
            Object webServerSecurityGroupName = template.ref("WebServerSecurityGroup");
            Object dbEc2SecurityGroupId = template.fnGetAtt("DBEC2SecurityGroup", "GroupId");

            CFNPackage cfnPackage = new CFNPackage("apt")
                    .addPackage("apache2")
                    .addPackage("php")
                    .addPackage("php-mysql")
                    .addPackage("libapache2-mod-php7.0")
                    .addPackage("mysql-client");

            CFNFile myPHPAappFile = new CFNFile("/var/www/html/myphpapp.php")
                    .setContent(myPHPAppFileContent)
                    .setMode("000600")
                    .setOwner("www-data")
                    .setGroup("www-data");

            CFNFile mySQLCredentialsFile = new CFNFile("/var/www/html/mysql-credentials.php")
                    .setContent(mySQLCredentialsFileContent)
                    .setMode("000400")
                    .setOwner("www-data")
                    .setGroup("www-data");

            CFNFile cfnHupFile = new CFNFile("/etc/cfn/cfn-hup.conf")
                    .setContent("PLACEHOLDER") //TODO insert content
                    .setMode("000400")
                    .setOwner("root")
                    .setGroup("root");

            CFNFile cfnAutoReloaderFile = new CFNFile("/etc/cfn/hooks.d/cfn-auto-reloader.conf")
                    .setContent("PLACEHOLDER") //TODO insert content
                    .setMode("000400")
                    .setOwner("root")
                    .setGroup("root");

            CFNFile configureMyPHPAppFile = new CFNFile("/tmp/configure_myphpapp.sh")
                    .setContent(configureMyPHPAppFileContent)
                    .setMode("000500")
                    .setOwner("root")
                    .setGroup("root");

            CFNFile mySQLDBMSConfigureFile = new CFNFile("/tmp/mysql_dbms_configure.sh")
                    .setContent(mySQLDBMSConfigureFileContent)
                    .setMode("000500")
                    .setOwner("root")
                    .setGroup("root");

            CFNService apacheService = new CFNService().addService(new SimpleService("apache2")
                    .setEnabled(true)
                    .setEnsureRunning(true)
                    .addPackage("apt", "libapache2-mod-php7.0"));

            CFNService cfnHupService = new CFNService().addService(new SimpleService("apache2")
                    .setEnabled(true)
                    .setEnsureRunning(true)
                    .addFile("/etc/cfn/cfn-hup.conf")
                    .addFile("/etc/cfn/hooks.d/cfn-auto-reloader.conf"));

            CFNCommand dbmsConfigureCommand = new CFNCommand("dbms_configure", "/bin/sh /tmp/mysql_dbms_configure.sh")
                    .addEnv("database_host", "!GetAtt MySQLDatabase.Endpoint.Address")
                    .addEnv("database_port", "!GetAtt MySQLDatabase.Endpoint.Port")
                    .addEnv("database_name", "mydatabase")
                    .addEnv("database_password", "abcd1234");

            CFNCommand configureMyPHPAppCommand = new CFNCommand("configure_myphpapp", "/bin/sh /tmp/configure_myphpapp.sh")
                    .addEnv("database_host", "!GetAtt MySQLDatabase.Endpoint.Address")
                    .addEnv("database_port", "!GetAtt MySQLDatabase.Endpoint.Port")
                    .addEnv("database_name", "mydatabase")
                    .addEnv("database_password", "abcd1234");

            Config install = new Config(CFNINIT_CONFIG_INSTALL)
                    .putFile(myPHPAappFile)
                    .putFile(mySQLCredentialsFile)
                    .putFile(cfnHupFile)
                    .putFile(cfnAutoReloaderFile)
                    .putFile(configureMyPHPAppFile)
                    .putFile(mySQLDBMSConfigureFile)
                    .putPackage(cfnPackage)
                    .putService(apacheService)
                    .putService(cfnHupService);

            Config configure = new Config(CFNINIT_CONFIG_CONFIGURE)
                    .putCommand(configureMyPHPAppCommand)
                    .putCommand(dbmsConfigureCommand);

            CFNInit webServerCfnInit = new CFNInit(CFNINIT_CONFIGSET)
                    .addConfig(CFNINIT_CONFIGSET, install)
                    .addConfig(CFNINIT_CONFIGSET, configure);

            UserData webServerUserData = new UserData(new Fn("Join", "", "|\n" +
                            "#!/bin/bash -xe" ,
                            "|\n" +
                            "mkdir -p /tmp/aws-cfn-bootstrap-latest" ,
                            "|\n" +
                            "curl https://s3.amazonaws.com/cloudformation-examples/aws-cfn-bootstrap-latest.tar.gz | tar xz -C /tmp/aws-cfn-bootstrap-latest --strip-components 1" ,
                            "|\n" +
                            "apt-get update" ,
                            "|\n" +
                            "apt-get -y install python-setuptools" ,
                            "|\n" +
                            "easy_install /tmp/aws-cfn-bootstrap-latest" ,
                            "|\n" +
                            "cp /tmp/aws-cfn-bootstrap-latest/init/ubuntu/cfn-hup /etc/init.d/cfn-hup\n" ,
                            "|\n" +
                            "chmod 755 /etc/init.d/cfn-hup" ,
                            "|\n" +
                            "update-rc.d cfn-hup defaults" ,
                            "|+" ,
                            "|\n" +
                            "# Install the files and packages from the metadata" ,
                            "/usr/local/bin/cfn-init -v " ,
                            "         --stack " ,
                            template.ref("AWS::StackName"),
                            "         --resource WebServerInstance " ,
                            "         --configsets InstallAndRun " ,
                            "         --region " ,
                            template.ref("'AWS::Region") ,
                            "|+" ,
                            "" ,
                            "|\n" +
                            "# Signal the status from cfn-init" ,
                            "/usr/local/bin/cfn-signal -e $? " ,
                            "         --stack " ,
                            template.ref("AWS::StackName") ,
                            "         --resource WebServerInstance '" ,
                            "         --region " ,
                            template.ref("AWS::Region") ,
                            "|+")
                    );

            SecurityGroup webServerSecurityGroup = resource(SecurityGroup.class, "WebServerSecurityGroup")
                    .groupDescription("Enable ports 80 and 22")
                    .ingress(ingress -> ingress.cidrIp(cidrIp), "tcp", 80, 22);
            SecurityGroup dbEc2SecurityGroup = resource(SecurityGroup.class, "DBEC2SecurityGroup")
                    .groupDescription("Open database for access")
                    .ingress(ingress -> ingress.sourceSecurityGroupName(webServerSecurityGroupName), "tcp", 3306);

            Instance webServerInstance = resource(Instance.class, "WebServerInstance")
                    .addCFNInit(webServerCfnInit)
                    .imageId("ami-0def3275")
                    .instanceType("t2.micro")
                    .securityGroupIds(webServerSecurityGroup)
                    .keyName(keyNameVar)
                    .userData(webServerUserData); //TODO add userdata

            DBInstance mySQLDatabase = resource(DBInstance.class, "MySQLDatabase")
                    .engine("MySQL")
                    .dBName("mydatabase")
                    .masterUsername("root")
                    .masterUserPassword("abcd1234")
                    .dBInstanceClass("db.t2.micro")
                    .allocatedStorage(20)
                    .storageType("gp2")
                    .vPCSecurityGroups(dbEc2SecurityGroupId);

            Object publicDNSName = webServerInstance.fnGetAtt("PublicDnsName");

            output("websiteURL", publicDNSName, "URL for newly created LAMP stack");
        }
    }
}
