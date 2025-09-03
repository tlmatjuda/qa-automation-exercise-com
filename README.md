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
@Component @RequiredArgsConstructor
public class AuthFlow {
  private final HeaderBar header;
  private final LoginPage login;

  @Step("Login OK")
  public void loginOk(String email, String password, String displayName) {
    login.open().enterEmail(email).enterPassword(password).submit();
    header.shouldShowLoggedInUser(displayName);
  }

  @Step("Login BAD")
  public void loginBad(String email, String wrongPassword) {
    login.open().enterEmail(email).enterPassword(wrongPassword).submit();
    UI.shouldSee(".login-form .alert-danger", "Your email or password is incorrect!");
  }
}
```

### Test (spec): `LoginTests`

```java
@SpringBootTest
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

## 🛒 Example – Cart “Add & Verify Quantity”

**Product Page service**

```java
@Component
public class ProductDetailPage {

  @Step("Verify product details visible")
  public ProductDetailPage verifyVisible() {
    UI.shouldBeVisible(".product-information");
    return this;
  }

  @Step("Add to cart and go to cart")
  public ProductDetailPage addToCart() {
    UI.clickCss("button.cart");
    UI.shouldBeVisible("#cartModal .modal-content"); // wait modal
    UI.shouldSeeText("Added!");                      // confirm text
    UI.clickCssJs("#cartModal a[href='/view_cart']");// click in-modal link (avoid overlay)
    return this;
  }
}
```

**Cart Page service**

```java
@Component
public class CartPage {

  @Step("Verify quantity for product id={productId} is {qty}")
  public CartPage shouldHaveQuantity(int productId, int qty) {
    UI.shouldSee("tr#product-" + productId + " .cart_quantity",
                 String.valueOf(qty));
    return this;
  }
}
```

**Flow**

```java
@Component @RequiredArgsConstructor
public class CartFlow {
  private final HeaderBar header;
  private final ProductsPage products;
  private final ProductDetailPage details;
  private final CartPage cart;

  @Step("Add product and verify in cart")
  public void addAndVerify(int productId, int qty) {
    header.openProducts();
    products.shouldShowAllProducts()
            .openProductById(productId, qty);  // scroll + JS click inside
    details.verifyVisible()
           .addToCart();
    cart.shouldHaveQuantity(productId, qty);
  }
}
```

**Test**

```java
@SpringBootTest
class CartTests extends BaseUiTest {

  @Test
  void add_product_in_cart() {
    cart.addAndVerify(1, 1);
  }

  @Test
  void verify_product_quantity() {
    cart.addAndVerify(1, 3);
  }
}
```

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
