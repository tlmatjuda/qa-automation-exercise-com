# QABase Web UI – Showcase on automationexercise.com

> **Highlights:** End-to-end **Web UI** automation using the **QABase Web UI** DSL (Selenide under the hood).  
> **Scope:** Demonstrates how to build clean **Pages → Flows → Tests** and how to use QABase **without** a Maven `<parent>` (using the **BOM** via `dependencyManagement` instead).

---

## About This Project

This repository is a **showcase** for Web UI automation with **QABase Web UI** against  
**https://www.automationexercise.com**.

It demonstrates:

- ✅ **Minimal Maven** setup using QABase as a **BOM** (no parent POM)  
- ✅ A crisp **DSL** (`UI`, `Sel`) for readable, stable UI steps  
- ✅ Clear **layering**: *Pages = service*, *Flows = business*, *Tests = specs*  
- ✅ **Allure**-friendly steps via `@Step` annotations in flows/pages

> We intentionally keep the test set focused to act as “**code as documentation**”.  
> Included scenarios: **Login (ok/bad)** and **Cart (add & verify quantity)**.

---

## ⚙️ Prerequisites

- ☕ **Java 17**
- 🧰 **Maven 3.8+**
- 🧪 Chrome installed (the project defaults to Chrome; headless on CI)
- 🔑 An account on **automationexercise.com** (see next section)

---

## 🔐 Account & Environment Variables

1) Register on **https://www.automationexercise.com**  
2) Export these **environment variables** (Terminal / shell profile / IntelliJ Run Config):

```bash
export AUTOMATIONEXERCISE_USER_NAME="your-display-name"
export AUTOMATIONEXERCISE_USER_EMAIL="your-email@example.com"
export AUTOMATIONEXERCISE_PASSWORD="your-password"
```

These are read by the tests (via a small base/parent class) and used for the login flows.

---

## 🧩 Minimal POM Setup (BOM, no parent)

This project shows **how to use QABase without `<parent>`** by importing the framework BOM:

```xml
<properties>
  <qabase-famework.version>1.3.1</qabase-famework.version>
  <java.version>17</java.version>
  <lombok.version>1.18.38</lombok.version>
</properties>

<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>io.github.toobprojects</groupId>
      <artifactId>qabase-framework</artifactId>
      <version>${qabase-famework.version}</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>

<dependencies>
  <!-- QABase Web UI (Selenide + DSL) -->
  <dependency>
    <groupId>io.github.toobprojects</groupId>
    <artifactId>qabase-web-ui</artifactId>
  </dependency>

  <!-- Lombok (optional but recommended for boilerplate-free Java) -->
  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>${lombok.version}</version>
    <scope>provided</scope>
  </dependency>
</dependencies>
```

That’s it—QABase manages compatible Selenium/Selenide and test libs via the BOM.

---

## 🛠️ QABase Web UI Configuration

Project config (YAML) used by the showcase:

```yaml
qabase:
  webui:
    base-url: "https://www.automationexercise.com"
    browser: "chrome"
    browser-window-size: "1920x1080"
    timeout: 10000
    headless: true
```

> Values can be overridden via system props or env if needed.

---

## 🏗️ Project Layout

- `pages/*` — **Service layer** (encapsulate selectors + tiny UI ops with DSL)
- `flows/*` — **Business layer** (compose page ops into user intent with `@Step`)
- `tests/*` — **Specs** (small, readable scenarios calling flows)

This layering keeps tests tiny and readable while concentrating logic/locators inside pages & flows.

---

## 💡 DSL Quick Glance

We lean on QABase’s DSL:

- `UI.shouldSee(css, text)` – expect text in a specific element  
- `UI.shouldSeeText("...")` – expect text anywhere **in body**  
- `UI.shouldBeVisible(css)` – element visible  
- `UI.clickCss(css)` / `UI.clickCssJs(css)` – regular / JS click  
- `UI.scrollIntoView(css)` – center element before clicking  
- `Sel.css(...)`, `Sel.id(...)`, `Sel.xpath(...)`, `Sel.text("...")` – selector & condition helpers

---

## 🧾 Example – Lean Page + Flow + Test

### Page (service): `HeaderBar`

```java
@Component
public class HeaderBar {
  @Step("Open Products page")
  public HeaderBar openProducts() {
    UI.clickCss("a[href='/products']"); // DSL click
    return this;
  }

  @Step("Verify logged-in header")
  public HeaderBar shouldShowLoggedInUser(String username) {
    UI.shouldBeVisible("a[href='/logout']");   // stable post-login artifact
    UI.shouldSeeText("Logged in as");          // tolerant of display-name formats
    return this;
  }
}
```

### Flow (business): `AuthFlow`

```java
@Component 
@RequiredArgsConstructor
public class AuthFlow {
  private final HeaderBar header;
  private final LoginPage login;

  @Step("Login OK")
  public void loginOk(String email, String password, String displayName) {
    login.open()
            .enterEmail(email)
            .enterPassword(password)
            .submit();
    header.shouldShowLoggedInUser(displayName);
  }

  @Step("Login BAD")
  public void loginBad(String email, String wrongPassword) {
    login.open()
            .enterEmail(email)
            .enterPassword(wrongPassword)
            .submit();
    UI.shouldSee(".login-form .alert-danger", "Your email or password is incorrect!");
  }
}
```

### Test (spec): `LoginTests`

```java
@QaBaseTest
class LoginTests extends BaseUiTest {

  @Test
  void shouldLoginSuccessfully() {
    auth.loginOk(
      env.userEmail(),
      env.userPassword(),
      env.userName()
    );
  }

  @Test
  void shouldRejectIncorrectPassword() {
    auth.loginBad(
      env.userEmail(),
      "not-the-password"
    );
  }
}
```

> Notice how tests read like **plain scenarios**. The DSL keeps noise low; pages & flows do the heavy lifting.

---

## 🧪 Running the Tests

```bash
# 1) Ensure env vars are set
export AUTOMATIONEXERCISE_USER_NAME="your-display-name"
export AUTOMATIONEXERCISE_USER_EMAIL="your-email@example.com"
export AUTOMATIONEXERCISE_PASSWORD="your-password"

# 2) Run the suite
mvn clean verify
```

### (Optional) Allure Reporting

Because this project demonstrates using QABase as a BOM (and not as a Maven parent), you won’t inherit the framework’s pre-wired Allure profile. To generate Allure reports you must define a local profile named allure-reports in your POM. Once this profile exists, you can run with either the profile id or a property.

Copy-paste profile snippet (taken from this project’s pom.xml):

```xml
<!-- Local profile with the SAME NAME as your framework: allure-reports
     Note: Because this project uses QABase as a BOM (not as a parent),
     you must add this profile in your own POM to enable Allure report generation. -->
<profiles>
    <profile>
        <id>allure-reports</id>

        <!-- Option A: activate by profile id (-Pallure-reports).
             Option B (also works): activate with -Dallure.reports=true -->
        <activation>
            <property>
                <name>allure.reports</name>
                <value>true</value>
            </property>
        </activation>

        <dependencies>
            <!-- This transitively brings allure-java-commons (annotations) -->
            <dependency>
                <groupId>io.qameta.allure</groupId>
                <artifactId>allure-junit5</artifactId>
            </dependency>
        </dependencies>

        <build>
            <plugins>
                <!-- Pass toggles into the test JVMs -->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>${maven-surefire-plugin.version}</version>
                    <configuration>
                        <systemPropertyVariables>
                            <!-- Your framework toggle -->
                            <allure.reports>true</allure.reports>
                            <!-- Where to write raw results -->
                            <allure.results.directory>${project.build.directory}/allure-results</allure.results.directory>
                        </systemPropertyVariables>
                    </configuration>
                </plugin>

                <!-- (Optional) If you use integration tests with Failsafe -->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-failsafe-plugin</artifactId>
                    <version>${maven-failsafe-plugin.version}</version>
                    <configuration>
                        <systemPropertyVariables>
                            <allure.reports>true</allure.reports>
                            <allure.results.directory>${project.build.directory}/allure-results</allure.results.directory>
                        </systemPropertyVariables>
                    </configuration>
                </plugin>

                <!-- Build the HTML site at 'verify' -->
                <plugin>
                    <groupId>io.qameta.allure</groupId>
                    <artifactId>allure-maven</artifactId>
                    <version>${allure-maven.version}</version>
                    <executions>
                        <execution>
                            <id>allure-report</id>
                            <phase>verify</phase>
                            <goals>
                                <goal>report</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>
            </plugins>
        </build>
    </profile>
</profiles>
```

If your local Maven has Allure plugin configured, you can generate/serve the report:

```bash
mvn clean verify -Dallure.reports=true
# or (if you have a profile)
mvn clean verify -Pallure-reports
mvn allure:serve
```

---

## 🧱 Why This Works Well

- **Readable**: DSL keeps assertions & actions expressive (`shouldSee`, `shouldSeeText`, `clickCssJs`, `scrollIntoView`)
- **Stable**: selectors scoped, modal/overlay handling included, timeouts centralized
- **Composable**: Pages provide tiny safe ops; Flows model business intent; Tests stay one-liners
- **Configurable**: YAML for browser/base URL; env vars for secrets/creds
- **Parent-less**: Demonstrates **BOM** (`dependencyManagement`) approach for teams that don’t want a parent POM

---

## 📌 Key Takeaways

- Use **QABase Web UI** as a BOM to keep your own parent POM clean  
- Stick to the **Pages → Flows → Tests** layering  
- Prefer **scoped** checks (`shouldSee(css, text)`) and stable signals after navigation  
- Use **`scrollIntoView` + `clickCssJs`** when dealing with overlays/ads

---

## ▶️ Next Steps

- Add CI (GitHub Actions) with matrix (headless/Chrome/Windows/Linux)  
- Extend examples: search, filters, checkout, address book  
- Mix **API + UI** flows (e.g., create data via API, assert via UI)

---

Built with ❤️ by **TOOB Projects** to simplify and accelerate QA Automation on the JVM.
