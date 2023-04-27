package be.odisee.brainstorm2023wa3sb;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;

import java.time.Duration;

public class Steps {

    WebDriver driver;


    @Given("I am on the first home page")
    public void i_am_on_first_the_home_page() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        // Met de nieuwste versie van Selenium Chromedriver 111
        // kwam er Java.io.IOException: Invalid Status code=403 text=Forbidden
        // --remote-allow-origins=* bleek nodig
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");   // die infobars zijn vervelend
        options.addArguments("--remote-allow-origins=*"); // dit bleek nu nodig

        driver = new ChromeDriver(options);
        driver.navigate().to("http://localhost:8888");
    }
    @When("^I click on login")
    public void I_click_on_login() {
        driver.findElement(By.linkText("Login")).click();
    }

    @When("^I click on overzicht")
    public void I_click_on_overzicht() {
        driver.findElement(By.linkText("OVERZICHT KLEDINGSTUKKEN")).click();
    }

    @When("^I click on kledingmanager")
    public void I_click_on_kledingmanager() {
        driver.findElement(By.linkText("Kledingmanager")).click();
    }
    @Then("^I should see a text \"([^\"]*)\"$")
    public void i_should_see_a_text(String textOnThePage) throws Throwable {
        Assert.assertTrue(driver.getPageSource().contains(textOnThePage));
    }

    @Then("^I close the browser$")
    public void i_close_the_browser() throws Throwable {
        driver.quit();
    }

    @Given("I am on the overzicht page")
    public void I_am_on_the_overzicht_page() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        // Met de nieuwste versie van Selenium Chromedriver 111
        // kwam er Java.io.IOException: Invalid Status code=403 text=Forbidden
        // --remote-allow-origins=* bleek nodig
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");   // die infobars zijn vervelend
        options.addArguments("--remote-allow-origins=*"); // dit bleek nu nodig

        driver = new ChromeDriver(options);
        driver.navigate().to("http://localhost:8080/overzicht.html");
    }
    @When("I click on kledingstuk toevoegen")
    public void I_click_on_kledingstuk_toeveogen(){
        driver.findElement(By.linkText("Nieuwe kledingstuk")).click();
    }

    @When("^I enter \"([^\"]*)\" in the ([^\"]*) field$")
    public void i_enter_in_the_firstname_field(String enteredText, String fieldName) throws Throwable {
        driver.findElement(By.id(fieldName)).sendKeys(enteredText);
    }

    @When("^I press on the save button")
    public void I_press_on_the_save_button() throws Throwable {
        driver.findElement(By.name("submit")).click();
    }
    @Then("^I should see a list containing \"([^\"]*)\"$")
    public void i_should_see_a_list_containing(String textOnThePage) throws Throwable {
        Assert.assertTrue(driver.getPageSource().contains(textOnThePage));
    }
    @When("I click on ferari")
    public void I_click_on_ferari(){
        driver.findElement(By.linkText("ferari")).click();
    }
    @When("I click on verwijderen")
    public void I_click_on_verwijderen(){
        driver.findElement(By.linkText("verwijderen")).click();
    }
    @When("I click on overzicht kleren")
    public void I_click_on_overzicht_kleren(){
        driver.findElement(By.linkText("OVERZICHT KLEDINGSTUKKEN")).click();
    }
    @When("I click on 2 overzicht kleren")
    public void I_click_on_2_overzicht_kleren(){
        driver.findElement(By.linkText("overzicht")).click();
    }

    @Then("^I should not see a list containing \"([^\"]*)\"$")
    public void i_should_not_see_a_list_containing(String textOnThePage) throws Throwable {
        Assert.assertFalse(driver.getPageSource().contains(textOnThePage));
    }
    @When("I click on pull")
    public void I_click_on_pull(){
        driver.findElement(By.linkText("pull")).click();
    }

    @When("^I enter updaten \"([^\"]*)\" in the ([^\"]*) field$")
    public void i_enter_updaten_in_the_firstname_field(String enteredText, String fieldName) throws Throwable {

        WebElement CustomfieldsTextBox = driver.findElement(By.id(fieldName));
        CustomfieldsTextBox.clear();
        driver.findElement(By.id(fieldName)).sendKeys(enteredText);
    }
    @When("I click on updaten")
    public void I_click_on_updaten(){
        driver.findElement(By.linkText("updaten")).click();
    }
    @When("I click on enter")
    public void I_click_on_enter(){
        driver.findElement(By.name("")).click();
    }




}
