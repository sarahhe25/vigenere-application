# Vigenere Cipher Application
The Vigenere Cipher Application is a Java implementation of the Vigenere cipher, which is a historical method of encrypting alphabetic text. The program provides an interactive environment, allowing users to encrypt and decrypt messages using a keyword of their choice. Implemented for CSC207 AI-Assisted Software Design Project. 

# Brief Note on Vigenere Cipher
The Vigenere cipher is a classical method of encryption that dates back to the 16th century. It is a type of polyalphabetic substitution cipher, meaning that it uses multiple cipher alphabets to encrypt the plaintext, making it more secure than simple substitution ciphers.

Named after the French cryptographer Blaise de Vigenere, the Vigenere cipher employs a keyword or a phrase to determine the sequence of shifts applied to the letters in the plaintext. Each letter of the keyword corresponds to a specific shift value, which is used to transform the corresponding letters in plaintext.

The Vigenere cipher offers a simple yet effective way to encipher messages and was considered unbreakable for centuries. However, with the advancement of modern cryptography techniques, its security has been compromised. Nevertheless, understanding the principles behind the Vigenere cipher remains valuable in exploring the history and evolution of encryption methods.


# How to Use
1. Clone the repository to your local machine.
2. Open the project in your preferred Java IDE.
3. Compile the code.
4. Run the VigenereCipherApp class to start the application.
5. Upon running the application, you will be presented with the main menu, where you can choose between encryption, decryption, or exit options. Follow the prompts to enter the required inputs and perform the desired operation.


# Features
- Encrypt plaintext messages using a String key. 
- Decrypt ciphertext messages using the corresponding String key. 
- Save the encrypted ciphertext to a text file. 
- Decrypt ciphertext from a text file.

# Java Version
The application is developed using Java corretto 11. 

# Testing
The application uses JUnit as the testing framework for unit tests. The tests can be found in the test package and cover various use cases and functionalities of the Vigenere cipher.

# Design patterns:
1. Factory Method Pattern: VigenereFactory creates instances of Vigenere objects.
2. Dependency Injection: Interfaces CiphertextReader and CiphertextWriter are used for file handling in the FileHandler class.
3. Strategy Pattern: VigenereEncryptionUseCase and VigenereDecryptionUseCase encapsulate encryption and decryption algorithms.
4. Singleton Pattern (Implicit): Scanner is used as a singleton to handle user input.

# Additional Code Smells and CA Violations
The program mostly adheres to Clean Architecture principles, promoting modularity, maintainability, and testability.

1. Entities layer: This layer represents the core business logic and contains the Vigenere entity, which encapsulates the plaintext message and the encryption/decryption key.
2. Use Cases Layer: This layer holds the use case implementations for encryption and decryption. It contains the EncryptionBoundary and DecryptionBoundary interfaces, defining the contract for the use cases. The VigenereEncryptionUseCase and VigenereDecryptionUseCase classes implement these interfaces to perform the encryption and decryption operations.
3. Interface Adapters Layer: This layer is responsible for interacting with external systems and user interfaces. It contains the VigenerePresenter class, which handles user interactions, converts user input to domain objects, and calls the appropriate use cases. Additionally, the FileHandler class in this layer deals with file input and output operations.
4. Frameworks/Drivers Layer: This layer includes the VigenereCipherApp class, which acts as the entry point to the application and interacts with the VigenerePresenter to handle the user interface.


## Some issues that were identified and addressed:
1. Long and Complex Methods that violate Single Responsibility: Refactored classes and methods to adhere to the Single Responsibility Principle and improve readability.
2. Dependency Inversion Principle (DIP): We introduced interfaces for EncryptionBoundary and DecryptionBoundary, decoupling the VigenereController from specific use case implementations. 
3. Resource management of scanner: implemented singleton pattern to use one scanner to handle user input
4. Duplication of Code: We refactored and reused common methods, reducing code duplication and promoting maintainability. 
5. Error handling: included specific exception messages, graceful error recovery (allowing the user to re-enter), try-catch and throwing errors. 

## Remaining code smell & room for improvement
1. Further input validation: many input validation has been added, but additional checks could be added to handle edge cases more effectively. 
2. Extending to GUI: the user inputs are command-line based as of now. Can extend the application to GUI and include more accessibility features in the future. 

# Disclaimer 
This application is intended for educational purposes only and should not be used as a secure encryption method. The Vigenere cipher is a historical encryption technique and is not suitable for secure communications in modern environments.

Please use this application responsibly and ensure that the data you encrypt is not sensitive or confidential.
