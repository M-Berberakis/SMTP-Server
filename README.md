1. Cesarean_Encryption.java

This file provides functionality to encrypt messages using a Caesar cipher with a fixed key of 3. The class encrypts a message by shifting characters forward and supports both uppercase and lowercase letters, leaving non-alphabetic characters unchanged.

Key Methods:

Cesarean_Encryption(String message): Constructor that encrypts the provided plaintext message.

getEncryption(): Returns the encrypted message.

2. Cesarean_Inc_Decryption.java

This file provides functionality to decrypt messages using a Caesar cipher with a fixed key of 3. The class decrypts a message by shifting characters backward and supports both uppercase and lowercase letters, leaving non-alphabetic characters unchanged.

Key Methods:

Cesarean_Inc_Decryption(String message): Constructor that decrypts the provided encrypted message.

get_decrypted(): Returns the decrypted message.

3. Client.java

This file contains the client-side implementation for interacting with the SMTP server. It includes a simple login system and supports sending commands to the server.

Key Features:

Username and password validation for access.

Command-line input to simulate SMTP commands (e.g., HELO, MAIL FROM, QUIT).

Establishes a socket connection to the SMTP server on localhost and port 5000.

4. MailData.java

This file provides server-side functionality for managing mail data, including senders, recipients, and messages. The messages are stored in encrypted format.

Key Methods:

addMessage(String input): Adds an encrypted message to the server's message list.

addSender(String input): Adds a sender to the server's sender list.

addRecipient(String input): Adds a recipient to the server's recipient list.

getMessages(), getSenders(), getRecipients(): Retrieve stored messages, senders, and recipients.

5. Server_Commands.java

This file handles the commands and interactions between the SMTP server and clients. It processes SMTP commands such as MAIL FROM, RCPT TO, and DATA, and stores encrypted messages in the MailData object.

Key Features:

Validates and processes sender and recipient information.

Accepts and encrypts user messages using the Cesarean_Encryption class.

Generates a unique message ID for each email.

Implements the QUIT command to terminate the session.
