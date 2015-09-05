/**
 * This class represents a Person.
 * @author Quinton Johnson
 * @version 1.0
 */
public class Person implements Comparable<Person> {

    private String name;
    private String email;

    /**
    * Creates a Person object.
    * @param name the name of the Person
    * @param email the Email belonging to the person
    */
    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
    * Returns the Person's name.
    * @return the name of the person
    */
    public String getName() {
        return name;
    }

    /**
    * Returns the Person's Email.
    * @return the Email
    */
    public String getEmail() {
        return email;
    }

    /**
    * Returns the Person's name.
    * @return the name
    */
    public String toString() {
        return name;
    }

    /**
    * Compares this Person to the given Person
    * @return the int to signify the comparison
    */
    @Override
    public int compareTo(Person o) {
        return this.email.compareTo(((Person) o).email);
    }
}
