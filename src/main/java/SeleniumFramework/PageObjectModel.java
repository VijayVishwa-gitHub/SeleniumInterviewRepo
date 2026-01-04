package SeleniumFramework;

/*
Order & Format Guidelines

Package declaration – Always first, matches your project structure.
Imports – Group Selenium imports together.
Class name – Match the page's purpose (LoginPage, DashboardPage).
WebDriver instance – Keep it private; pass from test class.
Locators – Group all @FindBy or By objects at the top.
Constructor – Initialize driver and call PageFactory.initElements.
Page actions – One method per user action (click, type, get text).
Optional workflow methods – Combine multiple small actions into one higher-level operation.
No assertions – Keep them in the test class.
*/

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObjectModel {
    public WebDriver driver;

    public PageObjectModel(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id= "password")
    private WebElement userPassword;

    @FindBy(xpath = "//button[@id='submit']")
    private WebElement loginButton;



    public void setUsername(String username){
        usernameField.sendKeys(username);
    }

    public void setPassword(String password){
        userPassword.sendKeys(password);
    }

    public void clickLoginButton(){
        loginButton.click();
    }

    public void LoginPage(String userNam, String pwd){
        setUsername(userNam);
        setPassword(pwd);
        clickLoginButton();
    }


}
