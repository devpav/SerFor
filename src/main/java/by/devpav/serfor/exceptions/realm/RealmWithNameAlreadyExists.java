package by.devpav.serfor.exceptions.realm;

public class RealmWithNameAlreadyExists extends RuntimeException {

    public RealmWithNameAlreadyExists() {
        super("Realm with name already exists");
    }
}
