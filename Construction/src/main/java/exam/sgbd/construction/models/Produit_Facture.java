package exam.sgbd.construction.models;

public class Produit_Facture {
    private int _idFacture;
    private int _idProduit;
    private int _quantite;


    public Produit_Facture(int _idFacture, int _idProduit, int _quantite) {
        this._idFacture = _idFacture;
        this._idProduit = _idProduit;
        this._quantite = _quantite;
    }

    public int get_idFacture() {
        return _idFacture;
    }

    public void set_idFacture(int _idFacture) {
        this._idFacture = _idFacture;
    }

    public int get_idProduit() {
        return _idProduit;
    }

    public void set_idProduit(int _idProduit) {
        this._idProduit = _idProduit;
    }

    public int get_quantite() {
        return _quantite;
    }

    public void set_quantite(int _quantite) {
        this._quantite = _quantite;
    }
}
