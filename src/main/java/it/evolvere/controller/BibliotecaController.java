package it.evolvere.controller;

import it.evolvere.dao.LibroDao;
import it.evolvere.dao.UtenteDao;
import it.evolvere.factory.ConnectionFactory;
import it.evolvere.factory.DaoFactory;
import it.evolvere.model.AmministratoreSingleton;
import it.evolvere.model.Autore;
import it.evolvere.model.Libro;
import it.evolvere.model.Utente;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.util.*;

public class BibliotecaController {

    private DaoFactory daoFactory;

    @FXML
    private Label gnazio;

    @FXML private ListView<Libro> libri;

    @FXML private TextField categoria;

    @FXML private TextField codice;

    @FXML private TextField titolo;

    @FXML private TextField autore;

    @FXML private TextArea descrizione;

    @FXML private void selezionaLibro(MouseEvent event){
        Libro libro = libri.getSelectionModel().getSelectedItem();
        categoria.setText(libro.getCategoria());
        codice.setText(libro.getCodice());
        titolo.setText(libro.getTitolo());
        autore.setText(libro.getAutore());
        descrizione.setText(libro.getDescrizione());
    }

    @FXML
    private void aggiungiLibro(){
        categoria.setText("");
        codice.setText("");
        titolo.setText("");
        autore.setText("");
        descrizione.setText("");
    }

    @FXML
    private void salvaLibro(){
        System.out.println("CIAO!!!");
        String autoreString = autore.getText();
        String titoloString = titolo.getText();
        String descrizioneString = descrizione.getText();
        String categoriaString = categoria.getText();
        String codiceString = codice.getText();

        Libro nuovoLibro = new Libro(0L,
                titoloString,
                descrizioneString,
                autoreString);

        nuovoLibro.setCategoria(categoriaString);
        nuovoLibro.setCodice(codiceString);

        Connection connection = ConnectionFactory.getConnection();
        DaoFactory daoFactory = new DaoFactory(connection);
        LibroDao libroDao = (LibroDao) daoFactory.getDao(DaoFactory.LIBRO);
        long id = libroDao.add(nuovoLibro);

        nuovoLibro.setId(id);
        libri.getItems().add(nuovoLibro);
        libri.refresh();
    }

    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        gnazio.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");

        Connection connection = ConnectionFactory.getConnection();

        //1. Pattern Factory:



        //Data Access Object (D.A.O.)
        DaoFactory daoFactory = new DaoFactory(connection);
        LibroDao libroDao = (LibroDao) daoFactory.getDao(DaoFactory.LIBRO);
        UtenteDao utenteDao = (UtenteDao) daoFactory.getDao(DaoFactory.UTENTE);

        List<Libro> list = libroDao.findByName("");
        for(Libro libro : list){
            libri.getItems().add(libro);
        }

        List<Utente> listUtenti = utenteDao.findByName("");
        System.out.println(listUtenti.size());

        Autore a = new Autore(1L, "Alessandro", "Manzoni");

        List<Autore> autori;
        autori = new ArrayList<>();
        autori.add(a);
        autori.add(a); //lo riaggiunge perchè non si preoccupa di gestire le duplicazioni
        autori.get(0);
        autori.remove(a);
        autori.clear();
        autori.size();


        Map<String, Autore> mappa;
        mappa = new HashMap<>();
        mappa.put("MNZ....", a);
        mappa.put("DFR....", a);
        mappa.get("MNZ....");
        mappa.isEmpty();
        mappa.remove("MNZ....");
        mappa.size();


        Map<Integer,Long> mappaPossibile;
        mappaPossibile = new HashMap<>();

        Map<Map<Integer,Integer>, Utente> mappaDiMappa;


        Set<Autore> set = new HashSet<>();
        set.add(a);
        set.add(a); //non lo riaggiunge perchè già presente
    }
}
