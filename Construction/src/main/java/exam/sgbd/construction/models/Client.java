package exam.sgbd.construction.models;
//TODO : FAIRE UNE INTERFACE; PROBLEME POUR FUTUR THEO
public class Client {
    public Client(int _idClient, String _nom, String _NISS, String _email, String _adresse) {
        this._idClient = _idClient;
        this._nom = _nom;
        this._NISS = _NISS;
        this._email = _email;
        this._adresse = _adresse;
    }

    private int _idClient;
    private String _nom;
    private String _NISS;
    private String _email;
    private String _adresse;

    public int get_idClient() {
        return _idClient;
    }

    public void set_idClient(int _idClient) {
        this._idClient = _idClient;
    }

    public String get_nom() {
        return _nom;
    }

    public void set_nom(String _nom) {
        this._nom = _nom;
    }

    public String get_NISS() {
        return _NISS;
    }

    public void set_NISS(String _NISS) {
        this._NISS = _NISS;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_adresse() {
        return _adresse;
    }

    public void set_adresse(String _adresse) {
        this._adresse = _adresse;
    }
}
