package com.scaleset.cfbuilder;

import com.scaleset.cfbuilder.core.Module;
import com.scaleset.cfbuilder.core.Template;
import com.scaleset.cfbuilder.ec2.metadata.CFNInit;
import com.scaleset.cfbuilder.ec2.Instance;
import com.scaleset.cfbuilder.ec2.SecurityGroup;
import com.scaleset.cfbuilder.ec2.metadata.Command;
import com.scaleset.cfbuilder.ec2.metadata.Config;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CloudFormationBuilderTest extends Module{

    @Test
    public void simpleTest() throws Exception {
        Template lampTemplate = new Template();
//        Module testModule = new Module();

        // Build Resources
//        testModule.resource(WebServerInstance.class, "WebServerInstance")
//            .name
//        testModule.resource(WebServerInstance.class, "WebServerInstance").na;
        // Set Parameters
//        lampTemplate.strParam("KeyName").description("Name of an existing EC2 KeyPair to enable SSH access to the instances").type("AWS::EC2::KeyPair::KeyName").constraintDescription("must be the name of an existing EC2 KeyPair.");
//        // Set Resources
//        lampTemplate.resource(WebServerInstance.class, ns("WebServerInstance"));


        new CloudFormationBuilderTest.TestModule().id("").template(lampTemplate).build();

        assertNotNull(lampTemplate);
        System.err.println(lampTemplate.toString(true));

//        ObjectMapper mapper = new ObjectMapper().registerModule(new CloudFormationJsonModule().scanTypes());
//        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
//
//        Template template = mapper.readValue(lampTemplate.toString(), Template.class);
//        assertNotNull(template);
//        System.err.println(template.toString());
    }

    class TestModule extends Module {
        private static final String KEYNAME_DESCRIPTION = "Name of an existing EC2 KeyPair to enable SSH access to the instances";
        private static final String KEYNAME_TYPE = "AWS::EC2::KeyPair::KeyName";
        private static final String KEYNAME_CONSTRAINT_DESCRIPTION = "must be the name of an existing EC2 KeyPair.";

        private static final String CFNINIT_CONFIGSET = "InstallAndRun";
        private static final String CFNINIT_CONFIG_INSTALL = "Install";
        private static final String CFNINIT_CONFIG_CONFIGURE = "Configure";

        public void build() throws Exception {

            Object keyName = option("KeyName").orElseGet(
                    () -> strParam("KeyName").type(KEYNAME_TYPE).description(KEYNAME_DESCRIPTION).constraintDescription(KEYNAME_CONSTRAINT_DESCRIPTION));

            Object cidrIp = "0.0.0.0/0";
            Object keyNameVar = template.ref("KeyName");
            Config install = new Config(CFNINIT_CONFIG_INSTALL);
            Config configure = new Config(CFNINIT_CONFIG_CONFIGURE).putCommand(new Command("configure_myphp", "sh /tmp/configure_myhp.sh"));
            CFNInit cfnInit = new CFNInit(CFNINIT_CONFIGSET).addConfig(CFNINIT_CONFIGSET, install).addConfig(CFNINIT_CONFIGSET, configure);

            SecurityGroup webServerSecurityGroup = resource(SecurityGroup.class, "WebServerSecurityGroup").groupDescription("Enable ports 80 and 22")
                    .ingress(ingress -> ingress.cidrIp(cidrIp), "tcp", 80, 22);

            Object groupId = webServerSecurityGroup.fnGetAtt("GroupId");

            Instance webServerInstance = resource(Instance.class, "WebServerInstance")
                    .addCFNInit(cfnInit)
                    .imageId("ami-0def3275")
                    .instanceType("t2.micro")
                    .securityGroupIds(webServerSecurityGroup)
                    .keyName(keyNameVar);


            Object publicDNSName = webServerInstance.fnGetAtt("PublicDnsName");

            output("websiteURL", publicDNSName, "URL for newly created LAMP stack");
        }
    }
}
