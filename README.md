# 🧳 Yatra Calendar Automation with Selenium

This project automates a real-world scenario on [Yatra.com](https://www.yatra.com) — extracting the **lowest available flight fare** for the **current and next month** from the calendar widget using Selenium WebDriver in Java.

Inspired by a challenge shared by [Jatin Sharma](https://www.youtube.com/@jatinhsharma), this script simulates a practical automation task that demonstrates DOM interaction, element traversal, price parsing, and pop-up handling.

---

## 📌 Features

- ✅ Handles dynamic **login pop-up**
- ✅ Clicks on the **Departure Date calendar**
- ✅ Extracts **lowest flight prices** from both current and next month
- ✅ **Compares** and displays which month has the cheaper fare
- ✅ Utilizes **modular & reusable methods** for maintainability

---

## 🧪 Tech Stack

- **Java**
- **Selenium WebDriver**
- **ChromeDriver**
- **XPath Locators**
- **Explicit Waits (WebDriverWait)**

---

## 📁 Project Structure

```bash
YatraCalendarAutomation/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── website/
│                   └── automation/
│                       └── YatraCalendarAutomation.java
