package io.eroshenkoam.allure;

import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author eroshenkoam (Artem Eroshenko).
 */
@Layer("web")
@Owner("eroshenkoam")
@Feature("Favorites")
@Page("/user/favorites")
public class FavoritesWebTest {

    private static final String NOTE_TEXT = "Cool place";

    private final WebSteps steps = new WebSteps();

    @BeforeTest
    public void startDriver() {
        steps.startDriver();
    }

    @Test
    @AllureId("6")
    @Story("Add to favorites")
    @JiraIssues({@JiraIssue("AE-1"), @JiraIssue("AE-2")})
    public void shouldCreateUserNote() {
        steps.openNotesPage();
        steps.createNoteWithText(NOTE_TEXT);
        steps.notesShouldContainsNoteWithText(NOTE_TEXT);
    }

    @Test
    @AllureId("5")
    @JiraIssue("AE-2")
    @Story("Remove from favorites")
    public void shouldDeleteUserNote() {
        steps.openNotesPage();
        steps.createNoteWithText(NOTE_TEXT);
        steps.deleteNoteWithText(NOTE_TEXT);
        steps.notesShouldNotContainsNoteWithText(NOTE_TEXT);
    }

    @AfterTest
    public void stopDriver() {
        steps.stopDriver();
    }

}