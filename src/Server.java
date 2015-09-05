import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Collections;

/**
 * This class represents a Server that has messages, subjects, and senders.
 * @author Quinton Johnson
 * @version 1.0
 */
public class Server {

    private ArrayList<Person> senders = new ArrayList<Person>();
    private ArrayList<String> text = new ArrayList<String>();
    private ArrayList<String> subjects = new ArrayList<String>();
    private Person[] sendersList = new Person[5];
    private String[] subjectsList = new String[] {"Snakehole Lounge",
        "Raccoons", "Lot 48", "Treat Yo Self", "Thinking of You"};
    private String[] textList = new String[] {
        "Hope everything is going okay.",
        "Have you taken another look at Lot 48?",
        "There is definitely another racoon problem.",
        "The Snakehole Lounge is open tonight!",
        "Treat Yo 'Self 2015 is coming up...look at deals now!"};

    /**
    * Creates a Server object along with Persons who are on the Server.
    */
    public Server() {
        Person benWyatt = new Person("Ben Wyatt",
                                    "benwyatt@pawnee.gov");
        senders.add(benWyatt);
        Person leslieKnope = new Person("Leslie Knope",
                                    "leslieknope@pawnee.gov");
        senders.add(leslieKnope);
        Person ronSwanson = new Person("Ron Swanson",
                                    "ronswanson@pawnee.gov");
        senders.add(ronSwanson);
        Person tomHaverford = new Person("Tom Haverford",
                                    "tomhaverford@pawnee.gov");
        senders.add(tomHaverford);
        Person annPerkins = new Person("Ann Perkins",
                                    "annperkins@pawnee.gov");
        senders.add(annPerkins);
        text.addAll(Arrays.asList(textList));
        subjects.addAll(Arrays.asList(subjectsList));
    }

    /**
    * Returns a random sender.
    * @return the random sender
    */
    public Person getRandomSender() {
        int index = ThreadLocalRandom.current().nextInt(5);
        return senders.get(index);
    }

    /**
    * Returns a random Message.
    * @return the random Message
    */
    public String getRandomText() {
        int index = ThreadLocalRandom.current().nextInt(5);
        return text.get(index);
    }

    /**
    * Returns a random subject.
    * @return the random subject
    */
    public String getRandomSubject() {
        int index = ThreadLocalRandom.current().nextInt(5);
        return subjects.get(index);
    }

    /**
    * Generates a HashSet of random recepients.
    * @return the recepient set generated
    */
    public HashSet<Person> generateRecepients() {
        HashSet<Person> recepientSet = new HashSet<Person>();
        for (int i = 0; i < 3; i++) {
            recepientSet.add(getRandomSender());
        }
        return recepientSet;
    }


    /**
    * Generates a Message with random instance variables.
    * @return the generate Message
    */
    public Message makeMessage() {
        LocalDateTime time = LocalDateTime.now();
        HashSet<Person> recepients = generateRecepients();
        Message newMessage = new Message(getRandomSender(), recepients,
                                        time, getRandomSubject(),
                                        getRandomText());
        return newMessage;
    }

    /**
    * Generates a list of messages using the random generator.
    * @return the generated list of messages
    */
    public ArrayList<Message> generateMessages() {
        ArrayList<Message> messages = new ArrayList<Message>();
        for (int i = 0; i < 5; i++) {
            Message generatedMessage = makeMessage();
            messages.add(generatedMessage);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        Collections.sort(messages, Collections.reverseOrder());
        return messages;
    }
}
