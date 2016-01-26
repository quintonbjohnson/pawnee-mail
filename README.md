PawneeMail
===================

![PawneeMail](http://i.imgur.com/DzfGSBo.png)

By QuintonJ

PawneeMail is a mock email client with a back-end of randomly generated messages. Includes working JavaFX interface as well as buttons to send and sort.

It demonstrates the following concepts:
  - JavaFX
  - Class inheritance
  - Polymorphism

Building / Compiling / Deployment
-----------------------------

- Mailbox: the class that represent a Mailbox object. Allows the user to move and remove messages between mailboxes.
- Message: the class that reperesent a Message object. Contains a header, sender, time, and message body.
- PawneeMail: the main "runner" of the project. Contains the JavaFX interface and creates a GUI to observe the mailboxes and their interactions.
- Person: the class that represent a Person object. Represents the sender, and his or her info.
- Server: the back end of the project that generates random messages to allocate to different mailboxes.

Future Improvements
-----------------------------

- The GUI class, PawneeMail, could be formatted for better readability. 
