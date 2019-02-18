package it.evolvere.controller;

import it.evolvere.dao.LibroDao;
import it.evolvere.factory.ConnectionFactory;
import it.evolvere.factory.DaoFactory;
import it.evolvere.model.Libro;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class BibliotecaController {

    private DaoFactory daoFactory;

    @FXML
    private Label label;

    @FXML private ListView<Libro> libri;

    @FXML private TextField categoria;

    @FXML private TextField codice;

    @FXML private TextField titolo;

    @FXML private TextArea descrizione;

    @FXML private void selezionaLibro(MouseEvent event){
        Libro libro = libri.getSelectionModel().getSelectedItem();
        categoria.setText(libro.getCategoria());
        codice.setText(libro.getCodice());
        titolo.setText(libro.getTitolo());
        descrizione.setText(libro.getDescrizione());
    }

    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " + javaVersion + ".");

        DaoFactory daoFactory = new DaoFactory(ConnectionFactory.getConnection());
        LibroDao libroDao = (LibroDao) daoFactory.getDao(DaoFactory.LIBRO);

        List<Libro> list = libroDao.findByName("");
        for(Libro libro : list){
            libri.getItems().add(libro);
        }
    }
}
