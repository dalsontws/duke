import commands.Command;
import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tasks.TaskList;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
//    private Storage storage;
//    private TaskList tasks;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Ui ui = new Ui();
        ui.welcomeStatement();
        boolean isExit = false;
        Storage storage = this.duke.getStorage();
        TaskList tasks = this.duke.getTaskList();

        String introduction = "Hello I'm Duke.";

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(introduction, dukeImage)
        );

        while (!isExit) {
            try {
                String input = userInput.getText();

                Command c = Parser.parse(input);

//                String response = duke.getResponse(input);
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage)
//                        DialogBox.getDukeDialog(response, dukeImage)
                );
                userInput.clear();
                isExit = c.isExit();
            } catch (DukeException err) {
                System.out.println(err.getMessage());
            }
            ui.nextCommand();


        }
    }
}