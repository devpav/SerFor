import java.util.*;

public interface RealmService {

    public Realm create(String name) throws RealmException;
    
    public Realm get(String name);

}
