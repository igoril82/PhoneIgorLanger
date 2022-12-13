package PhoneIgorLanger;

public class People {
    protected String name;
    protected String phoneNumber;



    public People(String nameOfContact, String numberOfContact) {

        name = nameOfContact;
        phoneNumber = numberOfContact;

    }


    public String getName() {

        return name;
    }

    public void setName(String newName) {

        this.name = newName;

    }

    public String getPhoneNumber() {

        return phoneNumber;

    }

    public void setPhoneNumber(String newPhoneNumber) {

        this.phoneNumber = newPhoneNumber;

    }
}


