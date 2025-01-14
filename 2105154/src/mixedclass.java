import java.io.Serializable;

public class mixedclass implements Serializable {
    private database db;
    private String clienttype;

    public mixedclass(database d, String s) {
        db = d;
        clienttype = s;
    }

    public database getdatabase() {
        return db;
    }

    public String getclienttype() {
        return clienttype;
    }
}
