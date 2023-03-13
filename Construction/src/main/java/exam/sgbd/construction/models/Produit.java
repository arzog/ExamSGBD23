package exam.sgbd.construction.models;

public class Produit {
    private int _idproduit;
    private String _nom;
    private int _stock;
    private int _isActive;

    public Produit(int _idproduit, String _nom, int _stock) {
        this._idproduit = _idproduit;
        this._nom = _nom;
        this._stock = _stock;
    }
    public Produit(int _idproduit, String _nom, int _stock, int _isActive) {
        this._idproduit = _idproduit;
        this._nom = _nom;
        this._stock = _stock;
        this._isActive = _isActive;
    }
    public int get_idproduit() {
        return _idproduit;
    }

    public void set_idproduit(int _idproduit) {
        this._idproduit = _idproduit;
    }

    public String get_nom() {
        return _nom;
    }

    public void set_nom(String _nom) {
        this._nom = _nom;
    }

    public int get_stock() {
        return _stock;
    }

    public void set_stock(int _stock) {
        this._stock = _stock;
    }

    public int is_isActive() {
        return _isActive;
    }

    public void set_isActive(int _isActive) {
        this._isActive = _isActive;
    }
}
