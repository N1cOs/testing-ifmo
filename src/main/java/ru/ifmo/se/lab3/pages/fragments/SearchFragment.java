package ru.ifmo.se.lab3.pages.fragments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class SearchFragment extends AbstractFragment {

    public SearchFragment(WebDriver driver) {
        super(driver);
    }

    public List<String> getTableColumns() {
        var table = waitFor(By.cssSelector("table.tablebasic"));
        var headerElems = table.findElements(By.xpath("(tbody/tr[1]/td | //th)[position() > 1]"));
        return headerElems.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getErrorText() {
        var errorElem = waitFor(By.cssSelector(".tablefill > b:nth-child(1)"));
        return errorElem.getText();
    }
}
