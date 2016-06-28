/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ada
 */
@Entity
@Table(name = "KATEDRA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Katedra.findAll", query = "SELECT k FROM Katedra k"),
    @NamedQuery(name = "Katedra.findByIdKatedry", query = "SELECT k FROM Katedra k WHERE k.idKatedry = :idKatedry"),
    @NamedQuery(name = "Katedra.findBySkrot", query = "SELECT k FROM Katedra k WHERE k.skrot = :skrot"),
    @NamedQuery(name = "Katedra.findByNazwa", query = "SELECT k FROM Katedra k WHERE k.nazwa = :nazwa")})
public class Katedra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_KATEDRY")
    private Integer idKatedry;
    @Column(name = "SKROT")
    private String skrot;
    @Column(name = "NAZWA")
    private String nazwa;
    @OneToMany(mappedBy = "idKatedry")
    private Collection<Student> studentCollection;

    public Katedra() {
    }

    public Katedra(Integer idKatedry) {
        this.idKatedry = idKatedry;
    }

    public Integer getIdKatedry() {
        return idKatedry;
    }

    public void setIdKatedry(Integer idKatedry) {
        this.idKatedry = idKatedry;
    }

    public String getSkrot() {
        return skrot;
    }

    public void setSkrot(String skrot) {
        this.skrot = skrot;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @XmlTransient
    public Collection<Student> getStudentCollection() {
        return studentCollection;
    }

    public void setStudentCollection(Collection<Student> studentCollection) {
        this.studentCollection = studentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKatedry != null ? idKatedry.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Katedra)) {
            return false;
        }
        Katedra other = (Katedra) object;
        if ((this.idKatedry == null && other.idKatedry != null) || (this.idKatedry != null && !this.idKatedry.equals(other.idKatedry))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return skrot;
    }
    
}
