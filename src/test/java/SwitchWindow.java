import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.build.Plugin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.util.Iterator;
import java.util.Set;

public class SwitchWindow {

    public static WebDriver driver;

    @BeforeTest
    public void setup() {


        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("https://www.salesforce.com/in");
        driver.manage().window().maximize();

    }

    @Test(priority = 1)
    public void switchTab() throws InterruptedException {
        driver.findElement(By.xpath("//body/main[@id='main-content']/div[2]/section[1]/div[1]/article[1]/div[2]/div[1]/pbc-button[2]/a[1]")).click();

        Set<String> windowHandles= driver.getWindowHandles();
        System.out.println(windowHandles);

        Iterator<String> iterator = windowHandles.iterator();
        String parentWindow = iterator.next();
        String childWindow1 = iterator.next();

        driver.switchTo().window(childWindow1);

        Thread.sleep(2000);

        driver.findElement(By.name("UserFirstName")).sendKeys("bug");
        driver.findElement(By.name("UserLastName")).sendKeys("resistance");
    }

    @Test(priority = 2)
    public void newWindow() throws InterruptedException {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://bugresistance.com/");
        Thread.sleep(2000);
        /*String windowHandle= driver.getWindowHandle();
        System.out.println(windowHandle);
        Set<String> windowHandles= driver.getWindowHandles();
        System.out.println(windowHandles);
        Iterator<String> iterator = windowHandles.iterator();
        //String parentWindow = iterator.next();
        //String childWindow1 = iterator.next();
        String childWindow2 = iterator.next();

        driver.switchTo().window(childWindow2);*/
        /*driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[2]/div[2]/button[1]")).click();
        Thread.sleep(2000);*/
        String email = driver.findElement(By.xpath("//a[contains(text(),'Course Admission')]")).getText();
        System.out.println(email);
    }

    /*@AfterTest
    public void quit(){
        driver.quit();
    }*/

}
