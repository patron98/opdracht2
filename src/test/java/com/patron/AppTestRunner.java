package com.patron;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/patron/features",
        glue = "com.patron",
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class AppTestRunner {
}
