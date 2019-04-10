package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Controller {
    @FXML
    private ListView<Contact> contactListView;
    @FXML
    private ImageView photoView;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private Button saveButton;
    @FXML
    private Button delButton;
    private ObservableList<Contact> contacts;
    private Contact currentContact;
    private FileChooser fileChooser;

    @FXML
    public void initialize(){
        addContact();
        contacts = FXCollections.observableArrayList();
        contactListView.setItems(contacts);
        contactListView.setCellFactory(listCell -> new ListCell<Contact>(){
            @Override
            protected void updateItem(Contact item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getLastName() + ", " + item.getFirstName());
                    setGraphic(item.getPhoto());
                }
            }
        });
        FileChooser.ExtensionFilter imageFilter = new
                FileChooser.ExtensionFilter("Images","*.jpg","*.png");
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(imageFilter);
        
        ImageView temp = new ImageView(new Image(getClass().getResourceAsStream("obama.jpg")));
        contacts.add(new Contact(temp, "Barack", "Obama", "obarack@us.gov", "123-456-7890", "White House"));
        temp = new ImageView(new Image(getClass().getResourceAsStream("hillary.jpg")));
        contacts.add(new Contact(temp, "Clinton", "Hillary", "hclinton@us.gov", "", ""));
        temp = new ImageView(new Image(getClass().getResourceAsStream("ariana.jpg")));
        contacts.add(new Contact(temp, "Grande", "Ariana", "arianag@gmail.com", "917-312-3122", ""));
        temp = new ImageView(new Image(getClass().getResourceAsStream("jisoo.jpg")));
        contacts.add(new Contact(temp, "Kim", "Jisoo", "kjisoo1@gmail.com", "", "555 Building 11223 Seoul, South Korea"));
        temp = new ImageView(new Image(getClass().getResourceAsStream("dua.jpg")));
        contacts.add(new Contact(temp, "Lipa", "Dua", "dlupa9@gmail.com", "", "USA"));
    }

    @FXML
    public void photoAdd() {
        File file  = fileChooser.showOpenDialog(photoView.getScene().getWindow());
        try {
            Image currentImage = new Image(new FileInputStream(file));
            photoView.setImage(currentImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteContact() {
        if(contacts.indexOf(contactListView.getSelectionModel().getSelectedItem()) > -1) {
            contacts.remove(contactListView.getSelectionModel().getSelectedItem());
            addContact();
        }
    }

    @FXML
    public void saveContact() {
        if (lastNameField.getText().isEmpty() || firstNameField.getText().isEmpty())
            return;

        Contact selected = contactListView.getSelectionModel().getSelectedItem();
        if(photoView.getImage() == null)
            currentContact.setPhoto(new ImageView(new Image(getClass().getResourceAsStream("no image.png"))));
        else
            currentContact.setPhoto(new ImageView(photoView.getImage()));
        currentContact.setLastName(lastNameField.getText());
        currentContact.setFirstName(firstNameField.getText());
        currentContact.setEmail(emailField.getText());
        currentContact.setPhone(phoneField.getText());
        currentContact.setHomeAddress(addressField.getText());
        if(selected == null) {
            contacts.add(currentContact);
        } else {
            contacts.set(contacts.indexOf(selected), currentContact);
        }
        addContact();
        FXCollections.sort(contacts, (o1, o2) -> o1.getLastName().compareToIgnoreCase(o2.getLastName()));
    }

    @FXML
    public void addContact() {
        currentContact = new Contact();
        saveButton.setText("Save");
        delButton.setVisible(false);
        photoView.setImage(null);
        lastNameField.clear();
        firstNameField.clear();
        emailField.clear();
        phoneField.clear();
        addressField.clear();
        contactListView.getSelectionModel().clearSelection();
    }

    @FXML
    public void displayContact() {
        Contact selected = contactListView.getSelectionModel().getSelectedItem();
        if(selected == null)
            return;
        saveButton.setText("Update");
        delButton.setVisible(true);
        photoView.setImage(selected.getPhoto().getImage());
        lastNameField.setText(selected.getLastName());
        firstNameField.setText(selected.getFirstName());
        emailField.setText(selected.getEmail());
        phoneField.setText(selected.getPhone());
        addressField.setText(selected.getHomeAddress());
    }
}
