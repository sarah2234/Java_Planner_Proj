import net.bytebuddy.asm.Advice;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.util.*;

public class WebCrawling {
    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;
    private Actions actions;
    private WebElement element;
    private String url;
    private String category;
    private String id;
    private String password;
    private int cntPost; // 커밋 또는 게시글 수 카운트

    // 1. 드라이버 설치 경로
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static String WEB_DRIVER_PATH = ".//chromedriver.exe";

    public WebCrawling () {
        Scanner scanner = new Scanner(System.in);
        System.out.print("URL(Naver Blog 또는 GitHub) : ");
        url = scanner.next();
        if(url.contains("blog.naver")) {
            System.out.print("Please input the name of your category(if you don't use, press enter) : ");
            scanner.nextLine();
            category = scanner.nextLine();
        }
        else {
            System.out.print("ID : ");
            id = scanner.next();
            System.out.print("Password : ");
            password = scanner.next();
        }

        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH); // chrome driver 경로 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // 창 최대
        options.addArguments("headless"); // 창 숨김
        options.addArguments("--disable-popup-blocking"); // 팝업창 막기
        driver = new ChromeDriver(options); // 옵션 적용
        javascriptExecutor = (JavascriptExecutor) driver;
        actions = new Actions(driver); // 스크롤할 때 사용

        this.cntPost = 0; // 게시물 또는 커밋 수 초기화
        scanner.close();
    }

    public void checkPost() {
        if(url.contains("github")) {
            countCommits();
        }
        else if(url.contains("blog.naver")) {
            countPosts();
        }
    }

    // GitHub 사용 시 커밋 카운트
    private int countCommits() {
        try {
            driver.get("https://github.com/");
            //Thread.sleep(2000);

            element = driver.findElement(By.linkText("Sign in")); // Sign in 버튼
            element.click();

            //Thread.sleep(1000);

            element = driver.findElement(By.id("login_field")); // 아이디 입력 칸
            element.sendKeys(id);

            element = driver.findElement(By.id("password")); // 비밀번호 칸
            element.sendKeys(password);

            element = driver.findElement(By.name("commit")); // 로그인하기
            element.submit();
        } catch (Exception e) { // 이미 로그인된 상태일 때
            //e.printStackTrace();
            System.out.println("Already Logged In.");
        }

        try {
            driver.get(url); // 레포지토리 링크
            element.click();
            element = driver.findElement(By.xpath("//*[@id=\"repo-content-pjax-container\"]/div/div[2]/div[1]/div[3]/div[1]/div/div[4]/ul/li/a/span/strong"));
            cntPost = Integer.parseInt(element.getText());
            System.out.println(cntPost);
        } catch (Exception e) {
            System.out.println("Error occurred while counting commits.");
        } finally {
            driver.close();
            return cntPost;
        }
    }

    // Naver 사용 시 게시글 수 카운트
    private int countPosts() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 2); // wait for 2 secs
        try {
            // 네이버는 자동 로그인에 민감한지 자동으로 로그인 할 수 없다.
            // 따라서 아래의 코드는 사용 불가
//            try {
//                driver.get(url);
//                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe"))); // wait until 'iframe' exists
//                driver.switchTo().frame(driver.findElement(By.tagName("iframe"))); // driver is switched to iframe
//                element = driver.findElement(By.xpath("//*[@id=\"gnb-area\"]/ul/li[4]/a")); // 로그인 버튼
//                element.click();
//
//                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"id\"]")));
//                element = driver.findElement(By.xpath("//*[@id=\"id\"]")); // 아이디 입력
//                element.sendKeys(id);
//                element = driver.findElement(By.xpath("//*[@id=\"pw\"]")); // 비밀번호 입력
//                element.sendKeys(password);
//                element = driver.findElement(By.xpath("//*[@id=\"log.login\"]")); // 로그인 버튼
//                element.click();
//                Thread.sleep(2000);
//            } catch (Exception e) { // 이미 로그인된 상태일 때
//                //e.printStackTrace();
//                System.out.println("Already Logged In.");
//            }

            driver.get(url); // 블로그 주소
            driver.switchTo().frame(driver.findElement(By.tagName("iframe"))); // iframe 안으로 이동
            //driver.switchTo().defaultContent(); // iframe 밖으로 이동
            if(!category.equals("")) {
                //javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)"); // 페이지의 하단까지 스크롤
                //javascriptExecutor.executeScript("argument[0].scrollIntoView(true);", element); // 이건 왜 안 될까?
                element = driver.findElement(By.xpath("//*[@id=\"category-list\"]/div/ul")); // 카테고리 목록
                actions.moveToElement(element);
                actions.perform(); // 해당 element로 스크롤
                element = driver.findElement(By.linkText(category)); // 일치하는 카테고리 찾기
                element.click(); // 카테고리 클릭
            }
            // 카테고리 이름이 명시되어 있지 않으면 전체 게시물 내에서 찾기
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("se_publishDate")));
            element = driver.findElement(By.className("se_publishDate")); // 작성 날짜
            String date = element.getText(); // 작성 날짜 받아오기
            LocalDate today = LocalDate.now(); // 금일 날짜 불러오기
            if(date.contains(String.valueOf(today.getYear()))) // 게시물에 날짜가 적혀있으면 작성한지 하루 이상 지난 것
                System.out.println("Cannot find today's post.");
            else {// 오늘 쓴 게시물에 해당
                cntPost++;
                System.out.println("Successfully found today's post."); // 확인용 메세지
            }
        } catch (Exception e) {
            System.out.println("Error occurred while searching for posts."); // 확인용 메세지
        } finally {
            driver.close();
            return cntPost;
        }
    }

    public static void main(String[] args) {
        WebCrawling bot1 = new WebCrawling();
        bot1.checkPost();
    }
}