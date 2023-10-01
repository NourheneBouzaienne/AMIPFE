package com.example.microservicepfe.test.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RepondreSteps {
    WebDriver driver = null;

    Wait<WebDriver> wait = null;



    public void initializeDriver ( ) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\HP\\Desktop\\DriverChrome\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        //driver = new ChromeDriver();

        wait = new FluentWait<>(driver)

                .withTimeout(Duration.ofSeconds(15))

                .pollingEvery(Duration.ofSeconds(2))

                .ignoring(Exception.class);

    }



    @Given("^I open Chrome browserR$")

    public void iOpenChromeBrowser() throws Throwable {

        initializeDriver();

    }



    @When("^I navigate to login pageR$")

    public void iNavigateToLoginPage() throws Throwable {

        driver.get("http://localhost:3000/#/login/signin");

    }



    @When("^I provide username as \"([^\"]*)\" and password as \"([^\"]*)\" R$")

    public void iProvideUsernameAsHiAndPasswordAsHi(String user, String password) throws Throwable {

        // Focus on the dialog window by click on dialog window title

        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));

        usernameField.sendKeys(user);
        passwordField.sendKeys(password);

    }



    @When("^I click on login buttonR$")

    public void iClickOnLoginButton() throws Throwable {

        // Click on the Login Button

        driver.findElement(By.id("formFieldButton")).click();

    }



    @Then("^I navigate to reclamations pageR$")
    //@Contract(value = "_->new", pure = true)
    public void loginSuccessWindowShouldBeOpened() throws Throwable {

        WebDriverWait urlWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        boolean urlChanged = urlWait.until(ExpectedConditions.urlToBe("http://localhost:3000/#/Reclamations"));
    }
    @When("^I click on details buttonR$")
    public void iClickOnDetailsButton() throws Throwable {
        // Ciblez le bouton "Détails" par sa classe CSS spécifique
        WebElement detailsButton = driver.findElement(By.cssSelector(".css-1g4401f-MuiButtonBase-root-MuiFab-root"));

        // Cliquez sur le bouton "Détails"
        detailsButton.click();
        // Attendez que l'élément de la fenêtre de détail devienne visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Attendez jusqu'à 10 secondes au maximum
        WebElement detailWindow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".css-1v3v4ot-MuiPaper-root-MuiDialog-paper"))); // Remplacez ".detail-window" par le sélecteur CSS réel de la fenêtre de détail
    }
    @Then("^Error MessageR$")
    //@Contract(value = "_->new", pure = true)
    public void loginFailedWindowShouldBeOpened() throws Throwable {

        WebElement errorMessageButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("errorMessage")));

    }
}
