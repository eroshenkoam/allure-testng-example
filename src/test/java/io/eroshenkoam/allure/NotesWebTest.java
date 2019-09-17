package io.eroshenkoam.allure;

import io.qameta.allure.AllureId;
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
@Feature("Notes")
@Page("/user/notes")
public class NotesWebTest {

    private static final String NOTE_TEXT = "Cool place";

    private final WebSteps steps = new WebSteps();

    @BeforeClass
    public void startDriver() {
        steps.startDriver();
    }

    @AllureId("4")
    @Story("Creating note")
    @JiraIssues({@JiraIssue("AE-2")})
    @Test(description = "Creating note for authorized user", groups = {"web", "critical", "smoke", "regress"})
    public void shouldCreateUserNote() {
        steps.openNotesPage();
        steps.createNoteWithText(NOTE_TEXT);
        steps.notesShouldContainsNoteWithText(NOTE_TEXT);
    }

    @AllureId("7")
    @Story("Creating note")
    @JiraIssues({@JiraIssue("AE-1")})
    @Test(description = "Adding note to advertisement", groups = {"web", "regress"})
    public void shouldAddNoteToTheAd() {
        final Long id = 12971230L;

        steps.openAdPage(id);
        steps.addNoteToAdd(NOTE_TEXT);
        steps.openNotesPage();
        steps.notesShouldContainsNoteWithText(NOTE_TEXT);
    }

    @AllureId("2")
    @Story("Delete note")
    @JiraIssues({@JiraIssue("AE-1")})
    @Test(description = "Deleting note for authorized user", groups = {"web", "regress"})
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