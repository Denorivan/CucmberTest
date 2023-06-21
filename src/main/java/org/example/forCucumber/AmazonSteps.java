package org.example.forCucumber;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.entity.Book;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class AmazonSteps {

    List<Book> bookInfoList = new ArrayList<>();
    Book book = new Book();
    private final SelenideElement choosingBooks = $x("//option[@value='search-alias=stripbooks-intl-ship']");
    private final SelenideElement allChoose = $x("//div[@class='nav-search-scope nav-sprite']");
    private final SelenideElement textContainer = $(By.id("twotabsearchtextbox"));
    private final SelenideElement finderButton = $(By.id("nav-search-submit-button"));
    private final SelenideElement productTitle = $(By.id("productTitle"));
    private final SelenideElement productAuthor = $x("//span[@class=\"author notFaded\"]/a");

    @When("Click on dropdown card")
    public void clickOnDropdownCard() {
        allChoose.click();
    }

    @And("Choose books")
    public void chooseBooks() {
        choosingBooks.click();
    }

    @And("Enter {string} in searching field")
    public void enterInSearchingField(String keyword) {
        textContainer.sendKeys(keyword);

    }

    @And("Press button to search")
    public void pressButtonToSearch() {
        finderButton.click();
    }

    @And("Take all books param from the received page")
    public void takeAllBooksParamFromTheReceivedPage() {
        ElementsCollection elements = $$("div[data-component-type='s-search-result']");

        List<SelenideElement> bookElements = elements.stream().toList();
        for (SelenideElement bookElement:bookElements){
            SelenideElement title = bookElement.$(By.xpath(".//span[@class='a-size-medium a-color-base a-text-normal']"));
            SelenideElement author = bookElement.$(By.xpath(".//div[@class='a-row']/span[contains(text(), 'by')]/following-sibling::*[1]"));
            Book book = new Book();
            if(title != null & author != null){
                book.setTitle(title.getText());
                book.setAuthor(author.getText());
            }else {
                book.setTitle("null");
                book.setAuthor("null");
            }
            bookInfoList.add(book);
        }
    }

    @And("Find particular book and click {string}")
    public void findParticularBookAndClick(String searchingNameOfTitle) {
        SelenideElement headOfJava = $x("//span[contains(text(), '" + searchingNameOfTitle + "')]");
        headOfJava.click();
    }

    @Then("Check that the book is on the list Head First Java: A Brain-Friendly Guide by Kathy Sierra")
    public void checkThatTheBookIsOnTheListBy() {
        String title = productTitle.getText();
        String author = productAuthor.getText();

        book = new Book(title, author);
        Assert.assertTrue(bookInfoList.contains(book));
       /* book =new Book(title, author);
        Assert.assertTrue(bookInfoList.contains(book));*/
    }
}
