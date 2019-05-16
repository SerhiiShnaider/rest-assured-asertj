package com.gmail.shnapi007.bdd;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
    features = "src/test/resources/rest.feature",
    glue = "com.gmail.shnapi007"
)
public class TestRunner {

  private TestNGCucumberRunner testNGCucumberRunner;

  @BeforeClass(alwaysRun = true)
  public void setUpClass() throws Exception {
    testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
  }

  @AfterClass(alwaysRun = true)
  public void tearDownClass() throws Exception {
    testNGCucumberRunner.finish();
  }

  @DataProvider
  public Object[][] features() {
    return testNGCucumberRunner.provideFeatures();
  }

  @Test(description = "Runs Cucumber Feature", dataProvider = "features")
  public void feature(CucumberFeatureWrapper cucumberFeature) {
    testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
  }

}
