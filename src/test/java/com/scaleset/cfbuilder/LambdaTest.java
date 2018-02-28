package com.scaleset.cfbuilder;

import com.scaleset.cfbuilder.core.Fn;
import com.scaleset.cfbuilder.core.Module;
import com.scaleset.cfbuilder.core.Template;
import com.scaleset.cfbuilder.lambda.Code;
import com.scaleset.cfbuilder.lambda.Function;
import org.junit.Assert;
import org.junit.Test;

public class LambdaTest {

    @Test
    public void lambdaTest() throws Exception {
        Template lambdaTemplate = new Template();
        new LambdaModule().id("").template(lambdaTemplate).build();

        Assert.assertNotNull(lambdaTemplate);
        System.err.println(lambdaTemplate.toString(true));
    }

    class LambdaModule extends Module {
        private static final String KEYNAME_DESCRIPTION = "Name of an existing EC2 KeyPair to enable SSH access to " +
                "the instances";
        private static final String KEYNAME_TYPE = "AWS::EC2::KeyPair::KeyName";
        private static final String KEYNAME_CONSTRAINT_DESCRIPTION = "must be the name of an existing EC2 KeyPair.";

        public void build() {

            Object keyName = option("KeyName").orElseGet(
                    () -> strParam("KeyName").type(KEYNAME_TYPE).description(KEYNAME_DESCRIPTION)
                            .constraintDescription(KEYNAME_CONSTRAINT_DESCRIPTION));



            resource(Function.class, "lambdaFunction")
                .code(new Code()
                        .setZipFile(Fn.fn("Join", "var 1", "var 2")))
                .handler("index.handler")
                .runtime("python2.7")
            .role(fnGetAtt("lambdaExecutionRole", "arn"));
        }
    }
}
