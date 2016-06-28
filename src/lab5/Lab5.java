/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
/**
 *
 * @author Ada
 */
public class Lab5 extends Application implements Initializable{
    
    private ObservableList<Katedra> katedry=FXCollections.observableArrayList();;
    private ObservableList<Student> studenci=FXCollections.observableArrayList();;
    
    @FXML
    private TableView<Student> tabelaStudenci;
    @FXML
    private TableView <Katedra> tabelaKatedra;
    @FXML
    private TextField noweImie;
    @FXML
    private TextField noweNazwisko;
    @FXML
    private ChoiceBox nowaKatedra;
    
    private EntityManager em;
    @FXML
    private TableColumn imie;
    @FXML
    private TableColumn nazwisko;
    @FXML
    private TableColumn katedra;
    
    @Override
    public void start(Stage stage) throws IOException {
       
        stage.setTitle("Baza danych");
        Parent wzor = FXMLLoader.load(getClass().getResource("lab5.fxml"));        
        Scene scene = new Scene(wzor);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void dodaj(){
        System.out.print("Dodaje");
        String im=noweImie.getText();
        String naz=noweNazwisko.getText();
        String kat=(String)nowaKatedra.getValue();
        Integer indeks[]=new Integer[studenci.size()];
        for(int i=0; i<studenci.size(); i++){
            Student temp=studenci.get(i);
            indeks[i]=temp.getIdStudenta();
        }
        Arrays.sort(indeks);
        int id=indeks[studenci.size()-1]+1;
       Katedra idKat=null;
        for(int i=0; i<katedry.size(); i++){
            Katedra temp=katedry.get(i);
            if(temp.getSkrot().equals(kat))
                idKat=temp;
        
        }
        
        Student nowy=new Student(id,im, naz,idKat);
        studenci.add(nowy);
        em.getTransaction().begin();
        em.persist(nowy);
        em.getTransaction().commit();
        
    }
    
    public void raport() throws IOException{
        
        List<Object[]> results = em.createQuery("SELECT m.idKatedry.nazwa, COUNT(m) AS total FROM Student AS m GROUP BY m.idKatedry.nazwa ORDER BY total ASC").getResultList();
       

        
Stage stage = new Stage();
stage.setTitle("Raport");
        Parent wzor2 = FXMLLoader.load(getClass().getResource("raport.fxml"));  
        Label raportujemy=(Label)wzor2.lookup("#Raport");
        raportujemy.setText("");
         for (Object[] result : results) {
             String temp=raportujemy.getText();
            String name = (String) result[0];
            int count = ((Number) result[1]).intValue();
            raportujemy.setText(temp+"\n "+ name+ ": "+ count);
            //System.out.println(name + ": "+ count);
        }
        
        Scene scene = new Scene(wzor2);
        
        stage.setScene(scene);
        stage.show();

        
        
        
        
        
    }
    
    public void zaznacz(){
        
        Student zaznaczony=tabelaStudenci.getSelectionModel().getSelectedItem();
        tabelaKatedra.getSelectionModel().select(zaznaczony.getIdKatedry());
        
    }
    public void usun(){
        Student usuwany=tabelaStudenci.getSelectionModel().getSelectedItem();
        System.out.print(usuwany.getNazwisko());
        studenci.remove(usuwany);
        em.getTransaction().begin();
        em.remove(usuwany);
        em.getTransaction().commit();        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       em = Persistence.createEntityManagerFactory("lab5PU").createEntityManager();
       List<Katedra> tempKat=em.createNamedQuery("Katedra.findAll").getResultList();       
       katedry.addAll(tempKat);
       tabelaKatedra.setItems(katedry);
       tabelaKatedra.setEditable(true);
       //nowaKatedra.setValue(katedry.get(0));
       tabelaStudenci.setEditable(true);
       List<Student> tempStu=em.createNamedQuery("Student.findAll").getResultList();
             
       studenci.addAll(tempStu);
       tabelaStudenci.setItems(studenci);
       
       final ContextMenu menu = new ContextMenu();
        
        MenuItem usuwanie= new MenuItem("Usun");
        usuwanie.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                usun();
            }
        });       
        menu.getItems().add(usuwanie);        
        tabelaStudenci.setContextMenu(menu);
        imie.setCellFactory(TextFieldTableCell.forTableColumn());
        imie.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Student, String>>() {
       
            @Override
            public void handle(TableColumn.CellEditEvent<Student, String> t) {
                Student item = t.getRowValue();
                String newImie = t.getNewValue();
                item.setImie(newImie);
                em.getTransaction().begin();
                em.merge(item);
                em.getTransaction().commit();   
            }
        });
        
        nazwisko.setCellFactory(TextFieldTableCell.forTableColumn());
        nazwisko.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Student, String>>() {
       
            @Override
            public void handle(TableColumn.CellEditEvent<Student, String> t) {
                Student item = t.getRowValue();
                String newNazwisko = t.getNewValue();
                item.setNazwisko(newNazwisko);
                em.getTransaction().begin();
                em.merge(item);
                em.getTransaction().commit();   
            }
        });
        katedra.setCellFactory(ChoiceBoxTableCell.forTableColumn(katedry));
        katedra.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Student, String>>() {
       
            @Override
            public void handle(TableColumn.CellEditEvent<Student, String> t) {
                Student item = t.getRowValue();
                Object newNazwisko =t.getNewValue();
                Katedra nowa=(Katedra)newNazwisko;
                item.setIdKatedry(nowa);
                em.getTransaction().begin();
                em.merge(item);
                em.getTransaction().commit();   
            }
        });
     
     
       
    }
   
    
    
    
}
