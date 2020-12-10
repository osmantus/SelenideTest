package testSuites;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class WebSiteTest {

    @BeforeSuite
    public void setBrowser() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver\\chromedriver.exe");
        System.setProperty("selenide.browser", "chrome");
    }

    @BeforeMethod
    public void initTest() {
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
        open("https://rozetka.com.ua/");
    }

    @Test
    public void testBuyingNotebook() throws InterruptedException {
        SiteMainPageLogic mainSite = new SiteMainPageLogic().categoriesCallingClick();

        NotebooksPageLogic notebooksPageLogic = mainSite.getNotebooksChapterAndOpenIt().findFirstNotebook();
        notebooksPageLogic.buySelectedNotebook();

        mainSite.comparePreBoughtNotebookTitle();
    }
}
