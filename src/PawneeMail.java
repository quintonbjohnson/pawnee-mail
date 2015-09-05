import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This class represents a PawneeMail GUI.
 * @author Quinton Johnson
 * @version 1.0
 */
public class PawneeMail extends Application {

    private Button refresh = new Button();
    private Button sortByDate = new Button();
    private Button sortBySender = new Button();
    private Button trashButton = new Button();
    private Button sortBySubject = new Button();
    private Button flag = new Button();
    private ListView<Message> messageView = new ListView<>();
    private ListView<Mailbox> mailboxView = new ListView<>();
    private Mailbox inbox;
    private Mailbox trash;
    private Mailbox important;
    private HBox toolbar = new HBox();
    private Separator separate1 = new Separator();
    private Separator separate2 = new Separator();
    private Server server = new Server();
    private TextArea messageBody = new TextArea();
    private TextArea messageInfo = new TextArea();
    private VBox viewInfo = new VBox();
    private VBox viewMessage = new VBox();

    @Override
    public void start(Stage primaryStage) {
        ArrayList<Message> inboxList = server.generateMessages();
        ObservableList<Message> inboxMessages = FXCollections
                                                .observableArrayList(inboxList);
        inbox = new Mailbox("Inbox", inboxMessages);
        ObservableList<Message> trashMessages =
                                            FXCollections.observableArrayList();
        trash = new Mailbox("Trash", trashMessages);
        ObservableList<Message> importantMessages =
                                            FXCollections.observableArrayList();
        important = new Mailbox("Important", importantMessages);
        BorderPane borderPane = new BorderPane();
        ObservableList<Mailbox> obsMailboxes = mailboxView.getItems();
        obsMailboxes.add(inbox);
        obsMailboxes.add(important);
        obsMailboxes.add(trash);
        mailboxView.setItems(obsMailboxes);
        borderPane.setLeft(mailboxView);
        refresh.setText("Refresh");
        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                inboxMessages.add(0, server.makeMessage());
            }
        });
        sortByDate.setText("Sort By Date");
        sortByDate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Mailbox currentMailbox = mailboxView.getSelectionModel()
                                    .getSelectedItem();
                Collections.sort(currentMailbox.getMessages(),
                                    Collections.reverseOrder());
            }
        });
        sortBySender.setText("Sort By Sender");
        sortBySender.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Mailbox currentMailbox = mailboxView.getSelectionModel()
                                    .getSelectedItem();
                Collections.sort(currentMailbox.getMessages(),
                                    new Comparator<Message>() {
                            @Override
                            public int compare(Message m1, Message m2) {
                                return m1.getSender().compareTo(m2.getSender());
                            }
                        });
            }
        });
        sortBySubject.setText("Sort By Subject");
        sortBySubject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Mailbox currentMailbox = mailboxView.getSelectionModel()
                                    .getSelectedItem();
                Collections.sort(currentMailbox.getMessages(),
                                    new Comparator<Message>() {
                            @Override
                            public int compare(Message m1, Message m2) {
                                return m1.getSubject().compareTo(m2
                                                        .getSubject());
                            }
                        });
            }
        });
        trashButton.setText("Trash");
        trashButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Mailbox currentMailbox = mailboxView.getSelectionModel()
                                    .getSelectedItem();
                Message currentMessage = messageView.getSelectionModel()
                                    .getSelectedItem();
                if (currentMessage != null) {
                    trash.add(currentMailbox.remove(currentMessage));
                }
                if (currentMailbox == trash) {
                    trash.remove(currentMessage);
                }
            }
        });
        flag.setText("Flag");
        flag.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Mailbox currentMailbox = mailboxView.getSelectionModel()
                                    .getSelectedItem();
                Message currentMessage = messageView.getSelectionModel()
                                    .getSelectedItem();
                if (currentMessage != null) {
                    important.add(currentMailbox.remove(currentMessage));
                }
            }
        });
        borderPane.setTop(toolbar);
        separate1.setMinWidth(15);
        separate2.setMinWidth(25);
        toolbar.setSpacing(10);
        toolbar.getChildren().addAll(refresh, separate1, sortByDate,
                                    sortBySender, sortBySubject, separate2,
                                    trashButton, flag);
        messageInfo.setPrefHeight(100);
        messageInfo.setPrefWidth(350);
        messageInfo.setEditable(false);
        messageBody.setPrefHeight(400);
        messageBody.setPrefWidth(350);
        messageBody.setEditable(false);
        viewMessage.getChildren().addAll(messageInfo);
        viewInfo.getChildren().addAll(viewMessage, messageBody);
        borderPane.setRight(viewInfo);
        mailboxView.setOnMouseClicked(e -> {
                Mailbox currentMailbox = mailboxView.getSelectionModel()
                                                            .getSelectedItem();
                try {
                    Message currentMessage = messageView.getSelectionModel()
                                                            .getSelectedItem();
                    int index = messageView.getSelectionModel()
                                                            .getSelectedIndex();
                    messageView.getSelectionModel().clearSelection(index);
                } catch (NullPointerException n) {
                    n.getMessage();
                }
                ObservableList<Message> newList = currentMailbox.getMessages();
                currentMailbox.addAll(newList);
            });
        mailboxView.setOnMouseClicked(e -> {
                Mailbox currentMailbox = mailboxView.getSelectionModel()
                                                            .getSelectedItem();
                ObservableList<Message> obsMessages = currentMailbox
                                                                .getMessages();
                messageView.setItems(obsMessages);
            });
        borderPane.setCenter(messageView);
        messageView.setOnMouseClicked(e -> {
                Mailbox currentMailbox = mailboxView.getSelectionModel()
                                                            .getSelectedItem();
                Message currentMessage = messageView.getSelectionModel()
                                                            .getSelectedItem();
                if (currentMessage != null) {
                    messageInfo.setText(currentMessage.getHeader());
                    messageBody.setText(currentMessage.getText());
                }
            });
        Scene scene = new Scene(borderPane, 800, 500);
        primaryStage.setTitle("Pawnee Mail");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
    * The main method.
    * @param args the command-line arguements
    */
    public static void main(String[] args) {
        launch(args);
    }
}
