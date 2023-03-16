package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src//test//resources//features//MiniCaseStudy.feature",
		glue = {"stepDefs"},
		monochrome=true,
		dryRun=false,
		plugin= {"pretty",
				"html:target//Reports//HtmlReport.html"
//				"json:target//Reports//JsonReport.json",
//				"usage:target//Reports//UsageReport",
//				"rerun:target//failedScenarios.txt"
		}
		)
public class MiniCaseStudyRunner extends AbstractTestNGCucumberTests {

}
