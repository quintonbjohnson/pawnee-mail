import java.time.LocalDateTime;
import java.util.HashSet;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a Messsage.
 * @author Quinton Johnson
 * @version 1.0
 */
public class Message implements Comparable<Message> {

    private Person sender;
    private HashSet<Person> recepients;
    private LocalDateTime dateTime;
    private String subject;
    private String text;

    /**
    * Creates a Message object
    * @param sender the Person who the Message belongs to
    * @param recepients the set of recepients
    * @param dateTime the date and time of the Message
    * @param subject the subject of the Message
    * @param text the text of the Message
    */
    public Message(Person sender, HashSet<Person> recepients,
                LocalDateTime dateTime, String subject, String text) {
        this.sender = sender;
        this.recepients = recepients;
        this.dateTime = dateTime;
        this.subject = subject;
        this.text = text;
    }

    /**
    * Returns the Message's sender.
    * @return the sender of the Message
    */
    public Person getSender() {
        return sender;
    }

    /**
    * Returns the dateTime of the Message.
    * @return the dateTime of the Message
    */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
    * Returns the Message's subject.
    * @return the subject of the Message
    */
    public String getSubject() {
        return subject;
    }

    /**
    * Returns the Message's text.
    * @return the text of the Message
    */
    public String getText() {
        return text;
    }

    /**
    * Returns the Message's formatted header.
    * @return the header of the Message.
    */
    public String getHeader() {
        DateTimeFormatter formatter = DateTimeFormatter
                                        .ofPattern("MM/dd/yy- HH:mm:ss.SSS");
        String formattedDateTime = dateTime.format(formatter);
        String headerString = String.format(" %s \n %s \n %s %s \n %s %s",
                                    subject, formattedDateTime, "From: ",
                                    sender.getEmail(), "To: ",
                                    recepients.toString());
        return headerString;
    }

    /**
    * Compares this Message to the given Message
    * @return the int to signify the comparison
    */
    @Override
    public int compareTo(Message o) {
        return this.dateTime.compareTo(((Message) o).dateTime);
    }

    /**
    * Returns whether or not the two objects are equal.
    * @return whether or not the objects are equal
    */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Message)) {
            return false;
        } else {
            return ((Message) other).sender.equals(this.sender)
                && ((Message) other).recepients.equals(this.recepients)
                && ((Message) other).dateTime.equals(this.dateTime)
                && ((Message) other).subject.equals(this.subject)
                && ((Message) other).text.equals(this.text);
        }
    }

    /**
    * Creates a hash code for the object.
    * @return the object's calculated hash code
    */
    @Override
    public int hashCode() {
        return text.hashCode();
    }

    /**
    * Returns a nicely formatted string for the Message.
    * @return the formatted string
    */
    @Override
    public String toString() {
        String messageString = String.format("%s", subject);
        return messageString;
    }
}
