import javafx.collections.ObservableList;

/**
 * This class represents a Mailbox.
 * @author Quinton Johnson
 * @version 1.0
 */
public class Mailbox {

    private String name;
    private ObservableList<Message> messages;

    /**
    * Creates a Mailbox object
    * @param name the name of the Mailbox
    * @param messages the list of Messages
    */
    public Mailbox(String name, ObservableList<Message> messages) {
        this.name = name;
        this.messages = messages;
    }

    /**
    * Returns the Mailbox.
    * @return the Mailbox
    */
    public Mailbox get() {
        return this;
    }

    /**
    * Returns the name of the Mailbox.
    * @return the name of the Mailbox
    */
    public String getName() {
        return name;
    }

    /**
    * Returns the list of messages in the Mailbox.
    * @return the list of messages
    */
    public ObservableList<Message> getMessages() {
        return messages;
    }

    /**
    * Removes the given Message.
    * @param m the Message to remove
    * @return the Message that was removed
    */
    public Message remove(Message m) {
        messages.remove(m);
        return m;
    }

    /**
    * Adds the given Message to messages.
    * @param m the Message to add
    */
    public void add(Message m) {
        messages.add(m);
    }

    /**
    * Adds the given Message list to messages.
    * @param m the Messages to add
    * @return the ObservableList of messages
    */
    public ObservableList<Message> addAll(ObservableList<Message> m) {
        for (Message item : m) {
            messages.add(item);
        }
        return messages;
    }

    /**
    * Returns the name of the Mailbox.
    * @return the name of the Mailbox
    */
    public String toString() {
        return this.name;
    }
}
