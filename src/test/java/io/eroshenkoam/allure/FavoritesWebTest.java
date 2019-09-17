package io.eroshenkoam.allure;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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

    @BeforeClass
    public void startDriver() {
        steps.startDriver();
    }

    @Story("Add to favorites")
    @JiraIssues({@JiraIssue("AE-1"), @JiraIssue("AE-2")})
    @Test(description = "Creating note via api", groups = {"api", "smoke"})
    public void shouldCreateUserNote() {
        steps.openNotesPage();
        steps.createNoteWithText(NOTE_TEXT);
        steps.notesShouldContainsNoteWithText(NOTE_TEXT);
    }

    @JiraIssue("AE-2")
    @Story("Remove from favorites")
    @Test(description = "Deleting note via api", groups = {"web", "regress"})
    public void shouldDeleteUserNote() {
        steps.openNotesPage();
        steps.createNoteWithText(NOTE_TEXT);
        steps.deleteNoteWithText(NOTE_TEXT);
        steps.notesShouldNotContainsNoteWithText(NOTE_TEXT);
    }

    @AfterClass
    public void stopDriver() {
        steps.stopDriver();
    }

}