package sample;

import javafx.scene.image.ImageView;


public class Contact {
    private ImageView photo;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private String homeAddress;

    public Contact(ImageView photo, String lastName, String firstName, String email, String phone, String home) {
    	this.photo = photo;
    	this.lastName = lastName;
    	this.firstName = firstName;
    	this.email = email;
    	this.phone = phone;
    	this.homeAddress = home;
    	photo.setPreserveRatio(true);
        photo.setFitHeight(100);
        photo.setFitWidth(150);
    }
    
    public Contact() {
    	
	}

	public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
        photo.setPreserveRatio(true);
        photo.setFitHeight(100);
        photo.setFitWidth(150);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }
}
