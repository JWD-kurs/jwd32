package jwd.stanica.web.dto;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class LinijaDTO{

	private Long id;

	@Min(0)
	private Integer brojMesta;
	private Double cenaKarte;
	private String vremePolaska;
	@NotBlank
	private String destinacija;

	private Long prevoznikId;
	private String prevoznikNaziv;

	public void setId(Long id){
		this.id=id;
	}
	
	public Long getId(){
		return this.id;
	}

	public void setBrojMesta(Integer brojMesta){
		this.brojMesta=brojMesta;
	}
	
	public Integer getBrojMesta(){
		return this.brojMesta;
	}

	public void setCenaKarte(Double cenaKarte){
		this.cenaKarte=cenaKarte;
	}
	
	public Double getCenaKarte(){
		return this.cenaKarte;
	}

	public void setVremePolaska(String vremePolaska){
		this.vremePolaska=vremePolaska;
	}
	
	public String getVremePolaska(){
		return this.vremePolaska;
	}

	public void setDestinacija(String destinacija){
		this.destinacija=destinacija;
	}
	
	public String getDestinacija(){
		return this.destinacija;
	}
	public Long getPrevoznikId() {
		return prevoznikId;
	}

	public void setPrevoznikId(Long prevoznikId) {
		this.prevoznikId = prevoznikId;
	}

	public String getPrevoznikNaziv() {
		return prevoznikNaziv;
	}

	public void setPrevoznikNaziv(String prevoznikNaziv) {
		this.prevoznikNaziv = prevoznikNaziv;
	}
}