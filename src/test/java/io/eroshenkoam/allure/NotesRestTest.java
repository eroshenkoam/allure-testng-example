package io.eroshenkoam.allure;

import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Layer("rest")
@Owner("baev")
@Feature("Notes")
public class NotesRestTest {

    private static final String NOTE_TEXT = "Cool place";

    private final RestSteps steps = new RestSteps();

    @AllureId("6")
    @Story("Creating note")
    @Test(description = "Creating note via api", groups = {"api", "smoke"})
    public void shouldCreateUserNote() {
        steps.createNoteWithText(NOTE_TEXT);
        steps.notesShouldContainsNoteWithText(NOTE_TEXT);
    }

    @AllureId("4")
    @Story("Delete note")
    @JiraIssues({@JiraIssue("AE-1")})
    @Test(description = "Deleting note via api", groups = {"web", "regress"})
    public void shouldDeleteUserNote() {
        steps.createNoteWithText(NOTE_TEXT);
        steps.deleteNoteWithText(NOTE_TEXT);
    }


}
