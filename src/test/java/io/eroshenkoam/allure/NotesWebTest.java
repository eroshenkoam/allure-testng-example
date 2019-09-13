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
@Feature("Notes")
@Page("/user/notes")
public class NotesWebTest {

    private static final String NOTE_TEXT = "Cool place";

    private final WebSteps steps = new WebSteps();

    @BeforeTest
    public void startDriver() {
        steps.startDriver();
    }

    @Test
    @AllureId("7")
    @Story("Creating note")
    @JiraIssues({@JiraIssue("AE-2")})
    public void shouldCreateUserNote() {
        steps.openNotesPage();
        steps.createNoteWithText(NOTE_TEXT);
        steps.notesShouldContainsNoteWithText(NOTE_TEXT);
    }

    @Test
    @AllureId("1")
    @Story("Creating note")
    @JiraIssues({@JiraIssue("AE-1")})
    public void shouldAddNoteToTheAd() {
        final Long id = 12971230L;

        steps.openAdPage(id);
        steps.addNoteToAdd(NOTE_TEXT);
        steps.openNotesPage();
        steps.notesShouldContainsNoteWithText(NOTE_TEXT);
    }

    @Test
    @AllureId("4")
    @Story("Delete note")
    @JiraIssues({@JiraIssue("AE-1")})
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