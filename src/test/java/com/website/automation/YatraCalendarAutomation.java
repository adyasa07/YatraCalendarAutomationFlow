package com.website.automation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YatraCalendarAutomation {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		WebDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		driver.get("https://www.yatra.com");
		driver.manage().window().maximize();

		By loginPopUpLOcator = By.xpath("//div[contains(@class,\"style_popup\")][1]");
		By closeButtonLocator = By.xpath(".//section[contains(@class,\"style_box\")]//img[@alt=\"cross\"]");

		// handling login popup
		try {
			WebElement loginPopUp = wait.until(ExpectedConditions.visibilityOfElementLocated(loginPopUpLOcator));
			WebElement closePopUpButton = loginPopUp.findElement(closeButtonLocator);
			closePopUpButton.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Login Pop-Up is not shown!!");
		}

		// clicking on the departure date section
		By departureDateButtonLocator = By
				.xpath("//div[@aria-label=\"Departure Date inputbox\" and @role=\"button\" ]");
		WebElement departureDateButton = wait
				.until(ExpectedConditions.elementToBeClickable(departureDateButtonLocator));

		departureDateButton.click();

		// selecting current month and next month
		WebElement currentMonth = getMonthFromCalendar(wait, 0);
		WebElement nextMonth = getMonthFromCalendar(wait, 1);
		
		String lowestPriceForCurrentMonth = getTheLowestPrice(currentMonth);
		String lowestPriceForNextMonth = getTheLowestPrice(nextMonth);
		
		System.out.println(lowestPriceForCurrentMonth);
		System.out.println(lowestPriceForNextMonth);

		getMinPriceBetweenTwoMonths(lowestPriceForCurrentMonth, lowestPriceForNextMonth);

	}

	public static WebElement getMonthFromCalendar(WebDriverWait w, int index) {
		By monthsCalendarLocator = By.xpath("//div[@class=\"react-datepicker__month-container\"]");

		List<WebElement> monthsCalendarList = w
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(monthsCalendarLocator));

		WebElement currentMonthCalendar = monthsCalendarList.get(index);

		return currentMonthCalendar;

	}

	public static String getTheLowestPrice(WebElement currentMonthElement) throws InterruptedException {

		Thread.sleep(2000);

		By priceElementLocator = By.xpath(".//span[contains(@class ,\"custom-day-content\")]");
		List<WebElement> priceElementList = currentMonthElement.findElements(priceElementLocator);

		int lowestPrice = Integer.MAX_VALUE;
		WebElement priceDetail = null;
		for (WebElement priceElement : priceElementList) {

			String priceString = priceElement.getText().replace("₹", "").replace(",", "");

			int priceInt = Integer.parseInt(priceString);
			if (priceInt < lowestPrice) {

				lowestPrice = priceInt;
				priceDetail = priceElement;
			}
		}
		WebElement dateDetailsElement = priceDetail.findElement(By.xpath(".//parent::span//parent::div"));
		String dateDetails = dateDetailsElement.getAttribute("aria-label");
		String result = dateDetails + "-----Price is Rs" + lowestPrice;
		return result;

	}

	public static void getMinPriceBetweenTwoMonths(String currentMonthPrice, String nextMonthPrice) {

		int currentMonthPriceIndex = currentMonthPrice.indexOf("Rs");
		int nextMonthPriceIndex = nextMonthPrice.indexOf("Rs");

		String currentPriceString = currentMonthPrice.substring(currentMonthPriceIndex + 2);
		String nextPriceString = nextMonthPrice.substring(nextMonthPriceIndex + 2);

		int currentPrice = Integer.parseInt(currentPriceString);
		int nextPrice = Integer.parseInt(nextPriceString);
		System.out.println(currentPrice);
		System.out.println(nextPrice);

		if (currentPrice < nextPrice) {

			System.out.println("Lowest Price is from current month, i.e. " + currentPrice);
		} else if (currentPrice == nextPrice) {

			System.out.println("Both months have same lowest price, choose dates convinient to you!!");
		} else {

			System.out.println("Lowest Price is from next month, i.e. " + nextPrice);
		}

	}

}
