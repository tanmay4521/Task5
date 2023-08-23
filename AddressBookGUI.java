import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Contact {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNumber + ", Email Address: " + emailAddress;
    }
}

class AddressBook {
    private ArrayList<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public ArrayList<Contact> getAllContacts() {
        return contacts;
    }
}

public class AddressBookGUI extends JFrame implements ActionListener {
    private JTextField nameField, phoneField, emailField;
    private JTextArea outputArea;
    private JButton addButton, searchButton, displayButton, removeButton, exitButton;
    private AddressBook addressBook;

    public AddressBookGUI() {
        addressBook = new AddressBook();

        setTitle("Address Book System");
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name: "));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Phone Number: "));
        phoneField = new JTextField();
        inputPanel.add(phoneField);
        inputPanel.add(new JLabel("Email Address: "));
        emailField = new JTextField();
        inputPanel.add(emailField);

        addButton = new JButton("Add Contact");
        addButton.addActionListener(this);
        searchButton = new JButton("Search Contact");
        searchButton.addActionListener(this);
        displayButton = new JButton("Display All Contacts");
        displayButton.addActionListener(this);
        removeButton = new JButton("Remove Contact");
        removeButton.addActionListener(this);
        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(exitButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        add(inputPanel, BorderLayout.NORTH);
        add(outputArea, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addContact();
        } else if (e.getSource() == searchButton) {
            searchContact();
        } else if (e.getSource() == displayButton) {
            displayAllContacts();
        } else if (e.getSource() == removeButton) {
            removeContact();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    private void addContact() {
        String name = nameField.getText();
        String phoneNumber = phoneField.getText();
        String emailAddress = emailField.getText();

        if (name.isEmpty() || phoneNumber.isEmpty() || emailAddress.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Contact contact = new Contact(name, phoneNumber, emailAddress);
        addressBook.addContact(contact);

        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }

    private void searchContact() {
        String name = nameField.getText();
        Contact contact = addressBook.searchContact(name);

        if (contact != null) {
            outputArea.setText(contact.toString());
        } else {
            outputArea.setText("Contact not found.");
        }
    }

    private void displayAllContacts() {
        ArrayList<Contact> contacts = addressBook.getAllContacts();

        if (contacts.isEmpty()) {
            outputArea.setText("No contacts added yet.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Contact contact : contacts) {
                sb.append(contact.toString()).append("\n");
            }
            outputArea.setText(sb.toString());
        }
    }

    private void removeContact() {
        String name = nameField.getText();
        Contact contact = addressBook.searchContact(name);

        if (contact != null) {
            addressBook.removeContact(contact);
            outputArea.setText("Contact removed successfully.");
        } else {
            outputArea.setText("Contact not found.");
        }
    }
    public static void main(String[] args)
	{
        SwingUtilities.invokeLater(() -> new AddressBookGUI());
    }
}
