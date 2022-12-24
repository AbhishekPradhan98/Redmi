package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Cucumber_Program extends BaseClass
{
	// Browser Launch
    @Before
    public void setup() throws IOException
    {


        // Load properties file
        configProp = new Properties();
        FileInputStream configPropfile = new FileInputStream("config.properties");
        configProp.load(configPropfile);


        String br = configProp.getProperty("browser");


        // Launching browser
        if (br.equals("firefox")) {


            // System.setProperty("webdriver.gecko.driver",
            // configProp.getProperty("firefoxpath"));
            // System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,
            // "true");
            //// System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,
            // "/dev/null");
            // driver = new FirefoxDriver();


            System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
            driver = new FirefoxDriver();
        }


        else if (br.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
            driver = new ChromeDriver();
        }


        else if (br.equals("edge")) {
            System.setProperty("webdriver.edge.driver", configProp.getProperty("edgepath"));
            driver = new EdgeDriver();
        }


        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);


    }


    // Take screenshot of failed testcases
    @After
    public void tearDown(Scenario scenario) 
    {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);
        }
    }
    
   
    @Given("i open browser with url {string}")
    public void i_open_browser_with_url(String url) 
    {
        driver.get(url);
    }
    @Then("i should see login page")
    public void i_should_see_login_page() 
    {
       if(driver.findElement(By.xpath("(//button[normalize-space()='Log in'])[1]")).isDisplayed())
       {System.out.println("login page is displayed:Test Pass");}
    }
    @When("i enter my user is as {string}")
    public void i_enter_my_user_is_as(String email) 
    {
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
    }
    @When("i enter my password as {string}")
    public void i_enter_my_password_as(String pwd) 
    {
       driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(pwd); 
    }



    //create Account
   
    @When("i click create account")
    public void i_click_create_account() 
    {
       driver.findElement(By.xpath("(//a[normalize-space()='Create New Account'])[1]")).click();
    }



    
}
