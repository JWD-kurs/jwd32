package jwd.stanica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Prevoznik{

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column(unique=true, nullable=false)
	private String naziv;
	@Column
	private String adresa;
	@Column(unique=true, nullable=false)
	private String pib;

	@OneToMany(mappedBy="prevoznik", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Linija> linije = new ArrayList<>();

	public void setId(Long id){
		this.id=id;
	}
	
	public Long getId(){
		return this.id;
	}

	public void setNaziv(String naziv){
		this.naziv=naziv;
	}
	
	public String getNaziv(){
		return this.naziv;
	}

	public void setAdresa(String adresa){
		this.adresa=adresa;
	}
	
	public String getAdresa(){
		return this.adresa;
	}

	public void setPib(String pib){
		this.pib=pib;
	}
	
	public String getPib(){
		return this.pib;
	}
	public List<Linija> getLinije() {
		return linije;
	}

	public void setLinije(List<Linija> linije) {
		this.linije = linije;
	}
	
	public void addLinija(Linija linija) {
		if(linija.getPrevoznik() != this) {
			linija.setPrevoznik(this);
		}
		linije.add(linija);
	}
}