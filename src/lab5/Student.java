/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ada
 */
@Entity
@Table(name = "STUDENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findByIdStudenta", query = "SELECT s FROM Student s WHERE s.idStudenta = :idStudenta"),
    @NamedQuery(name = "Student.findByImie", query = "SELECT s FROM Student s WHERE s.imie = :imie"),
    @NamedQuery(name = "Student.findByNazwisko", query = "SELECT s FROM Student s WHERE s.nazwisko = :nazwisko")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_STUDENTA")
    private Integer idStudenta;
    @Column(name = "IMIE")
    private String imie;
    @Column(name = "NAZWISKO")
    private String nazwisko;
    @JoinColumn(name = "ID_KATEDRY", referencedColumnName = "ID_KATEDRY")
    @ManyToOne
    private Katedra idKatedry;

    public Student() {
    }

    public Student(Integer idStudenta) {
        this.idStudenta = idStudenta;
    }
    
    public Student(Integer idStudenta, String imie, String nazwisko, Katedra idKatedry) {
        this.idStudenta = idStudenta;
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.idKatedry=idKatedry;
        
    }
    public Integer getIdStudenta() {
        return idStudenta;
    }

    public void setIdStudenta(Integer idStudenta) {
        this.idStudenta = idStudenta;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Katedra getIdKatedry() {
        return idKatedry;
    }

    public void setIdKatedry(Katedra idKatedry) {
        this.idKatedry = idKatedry;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idStudenta != null ? idStudenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.idStudenta == null && other.idStudenta != null) || (this.idStudenta != null && !this.idStudenta.equals(other.idStudenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "lab5.Student[ idStudenta=" + idStudenta + " ]";
    }
    
}
