package jwd.stanica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rezervacija{

	@Id
	@GeneratedValue
	@Column
	private Long id;


	@ManyToOne(fetch=FetchType.EAGER)
	private Linija linija;

	public void setId(Long id){
		this.id=id;
	}
	
	public Long getId(){
		return this.id;
	}
	public Linija getLinija() {
		return linija;
	}

	public void setLinija(Linija linija) {
		this.linija = linija;
		if(!linija.getRezervacije().contains(this)){
			linija.getRezervacije().add(this);
		}
	}
}