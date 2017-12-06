package com.scaleset.cfbuilder;

import com.scaleset.cfbuilder.core.Module;
import com.scaleset.cfbuilder.core.Template;
import com.scaleset.cfbuilder.ec2.Instance;
import com.scaleset.cfbuilder.ec2.Metadata;
import com.scaleset.cfbuilder.ec2.SecurityGroup;
import com.scaleset.cfbuilder.ec2.SecurityGroupIngress;
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

        public void build() throws Exception {

            Object keyName = option("KeyName").orElseGet(
                    () -> strParam("KeyName").type("AWS::EC2::KeyPair::KeyName").description("Name of an existing EC2 KeyPair to enable SSH access to the instances").constraintDescription("must be the name of an existing EC2 KeyPair."));

            Object clusterName = option("clusterName").orElse("elasticsearch");
            Object cidrIp = "0.0.0.0/0";
            Object keyNameVar = template.ref("KeyName");
            Object imageId = template.ref("ImageId");
            Object az = template.ref("MyAZ");
            Object instanceProfile = ref("InstanceProfile");
            Object vpcId = template.ref("VpcId");
            Metadata metadata = new Metadata();

            SecurityGroup webServerSecurityGroup = resource(SecurityGroup.class, "WebServerSecurityGroup").groupDescription("Enable ports 80 and 22")
                    .ingress(ingress -> ingress.cidrIp(cidrIp), "tcp", 80, 22);

            Object groupId = webServerSecurityGroup.fnGetAtt("GroupId");

            Instance webServerInstance = resource(Instance.class, "WebServerInstance").setMetadata(metadata).imageId("ami-0def3275")
                    .instanceType("t2.micro")
                    .securityGroupIds(webServerSecurityGroup)
                    .keyName(keyName);


            Object value = webServerInstance.fnGetAtt("PublicDnsName");

            output("websiteURL", value, "URL for newly created LAMP stack");
        }
    }
}
