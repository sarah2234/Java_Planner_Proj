
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.time.*;
import java.util.*;

public class WebCrawling {
    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;
    private Actions actions;
    private WebElement element;
    private String url; // 깃허브 레포지토리 또는 네이버 블로그 주소
    private String category; // 네이버 블로그 사용시 카테고리 입력
    private int cntPost; // 커밋 또는 게시물 올린 횟수

    // 드라이버 설치 경로
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static String WEB_DRIVER_PATH = ".//chromedriver.exe"; // chrome driver 경로 (현재 프로젝트 폴더 안에 있음)

    public WebCrawling () {
        Scanner scanner = new Scanner(System.in);
        System.out.print("URL(Naver Blog 또는 GitHub) : ");
        url = scanner.next(); // 페이지 주소 입력
        if(url.contains("blog.naver")) {
            System.out.print("Please input the name of your category(if you don't use, press enter) : ");
            scanner.nextLine(); // 버퍼 비우기
            category = scanner.nextLine(); // 네이버 블로그면 카테고리 입력 (특수문자 입력 x)
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

    // 해당 메소드만으로 오늘 공부했는지 확인
    public int checkPost() {
        if(url.contains("github")) { // 깃허브 레포지토리 주소 입력 시
            countCommits();
        }
        else if(url.contains("blog.naver")) { // 네이버 블로그 주소 입력 시
            countPosts();
        }
        else { // 잘못된 주소
            System.out.println("Invalid webpage.");
        }
        return cntPost; // 현재까지 게시물 올린 횟수 리턴 (또는 커밋한 횟수 리턴. 이때 커밋한 횟수는 하루에 최대 1번으로 카운트)
    }

    // GitHub 사용 시 커밋 카운트
    private int countCommits() {
        try {
            driver.get(url); // 레포지토리 링크
            WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2)); // wait for 2 secs

            element = driver.findElement(By.tagName("relative-time")); // 커밋 날짜 받아오기
            String date_s = element.getAttribute("datetime");
            String year_s = date_s.substring(0, 4);
            int year_i = Integer.parseInt(year_s); // 가장 최근에 커밋한 날짜의 년도
            String month_s = date_s.substring(5, 7);
            int month_i = Integer.parseInt(month_s); // 가장 최근에 커밋한 날짜의 월
            String day_s = date_s.substring(8, 10);
            int day_i = Integer.parseInt(day_s); // 가장 최근에 커밋한 날짜의 일자
            String hour_s = date_s.substring(11, 13);
            int hour_i = Integer.parseInt(hour_s); // UTC 기준
            if(hour_i + 9 >= 24) {
                day_i++; // UTC와 한국 시각 차이 : 9시
            }
            LocalDate today = LocalDate.now(); // 오늘 날짜 불러오기
            if(year_i == today.getYear() && month_i == today.getMonthValue() && day_i == today.getDayOfMonth()) { // 오늘 날짜와 'relative-time'의 날짜가 일치
                cntPost++; // 게시물 올린 횟수 증가
                System.out.println(cntPost); // 확인용 메세지
            }
            else { // 오늘 날짜와 'relative-time'의 날짜가 불일치
                System.out.println("Cannot find today's commits."); // 확인용 메세지
            }
        } catch (Exception e) { // 해당 레포지토리의 주소가 유효하지 않음
            System.out.println("The repository doesn't exist."); // 확인용 메세지
        } finally {
            driver.close();
            return cntPost;
        }
    }

    // Naver 사용 시 게시글 수 카운트
    private int countPosts() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2)); // wait for 2 secs
        try {
            driver.get(url); // 블로그 주소
            //webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("iframe"))); // iframe이 로딩될 때까지 기다리기 (최대 2초)
            driver.switchTo().frame(driver.findElement(By.tagName("iframe"))); // iframe 안으로 이동
            //driver.switchTo().defaultContent(); // iframe 밖으로 이동
            element = driver.findElement(By.xpath("//*[@id=\"blog-menu\"]/div/table/tbody/tr/td[1]/ul/li[2]/a")); // 상단의 '블로그' 링크 클릭(프롤로그가 메인 화면인 블로그인 경우를 위해)
            element.click();
            // 카테고리 찾기
            try {
                if (!category.equals("")) {
                    //javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)"); // 페이지의 하단까지 스크롤
                    //javascriptExecutor.executeScript("argument[0].scrollIntoView(true);", element); // 이건 왜 안 될까?
                    element = driver.findElement(By.linkText(category)); // 카테고리 목록
                    actions.moveToElement(element);
                    actions.perform(); // 해당 element로 스크롤
                    element.click(); // 카테고리 클릭
                }
            } catch(Exception e) { // 게시물 찾는 과정에서 에러 발생
                System.out.println("Error occurred while searching for the category."); // 확인용 메세지
            }

            // 카테고리 이름이 명시되어 있지 않으면 전체 게시물 내에서 찾기
            //webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body"))); // body가 로딩될 때까지 기다리기
            if(isElementPresent(By.className("se_publishDate"))) {
                element = driver.findElement(By.className("se_publishDate")); // 작성 날짜 (블로그형)
            }
            else {
                element = driver.findElement(By.className("date")); // 작성 날짜 (앨범형)
            }
            actions.moveToElement(element);
            actions.perform(); // 해당 element로 스크롤
            String date = element.getText(); // 작성 날짜 받아오기
            if(date.contains(".")) // 게시물에 날짜(예: 2021.11.19)가 적혀있으면 작성한지 하루 이상 지난 것
                System.out.println("Cannot find today's post."); // 확인용 메세지
            else { // 오늘 쓴 게시물에 해당
                cntPost++;
                System.out.println("Successfully found today's post."); // 확인용 메세지
            }
        } catch (Exception e) { // 해당 블로그의 주소가 유효하지 않음
            System.out.println("The blog doesn't exist."); // 확인용 메세지
        } finally {
            driver.close();
            return cntPost;
        }
    }

    // 해당 WebElement가 있으면 true, 없으면 false
    private Boolean isElementPresent(By locatorKey) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        WebCrawling bot1 = new WebCrawling();
        bot1.checkPost();
    }
}