package seedu.address.logic.commands.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAssignments.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ASSIGNMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assignment.Description;

public class EditAssignmentCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private final String desc1 = "new desc1";

    private final String desc2 = "new desc2";

    @Test
    public void execute_validIndexUnfilteredList_success() throws Exception {
        EditAssignmentCommand editCommand = new EditAssignmentCommand(INDEX_FIRST_ASSIGNMENT,
                new Description(desc1));

        String commandResult = editCommand.execute(model).getFeedbackToUser();
        assertTrue(commandResult.contains(desc1));
        assertEquals(desc1,
                model.getFilteredAssignmentList()
                        .get(INDEX_FIRST_ASSIGNMENT.getZeroBased())
                        .getDescription().toString());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAssignmentList().size() + 1);
        EditAssignmentCommand editCommand = new EditAssignmentCommand(outOfBoundIndex, new Description(""));
        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        EditAssignmentCommand editFirstCommand = new EditAssignmentCommand(INDEX_FIRST_ASSIGNMENT,
                new Description(desc1));
        EditAssignmentCommand editSecondCommand = new EditAssignmentCommand(INDEX_SECOND_ASSIGNMENT,
                new Description(desc2));

        // same object -> returns true
        assertEquals(editFirstCommand, editFirstCommand);

        // same values -> returns true
        EditAssignmentCommand editFirstCommandCopy = new EditAssignmentCommand(INDEX_FIRST_ASSIGNMENT,
                new Description(desc1));
        assertEquals(editFirstCommand, editFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, editFirstCommand);

        // null -> returns false
        assertNotEquals(null, editFirstCommand);

        // different person -> returns false
        assertNotEquals(editFirstCommand, editSecondCommand);
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        String desc = "desc";
        EditAssignmentCommand editCommand = new EditAssignmentCommand(targetIndex, new Description(desc));
        String expected = EditAssignmentCommand.class.getCanonicalName() + "{edit=" + targetIndex + ", description="
                + desc + "}";
        assertEquals(expected, editCommand.toString());
    }

}
