package com.miejemplo.runners;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
        key   = Constants.GLUE_PROPERTY_NAME,
        value = "com.miejemplo"
)
@ConfigurationParameter(
        key   = Constants.PLUGIN_PROPERTY_NAME,
        value = "net.serenitybdd.cucumber.core.plugin.SerenityReporterParallel,pretty"
)
@ConfigurationParameter(
        key   = Constants.FEATURES_PROPERTY_NAME,
        value = "classpath:features"
)
public class TestRunner {

}
