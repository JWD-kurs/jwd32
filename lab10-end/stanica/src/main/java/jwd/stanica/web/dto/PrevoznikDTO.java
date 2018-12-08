package jwd.stanica.web.dto;

public class PrevoznikDTO{

	private Long id;

	private String naziv;
	private String adresa;
	private String pib;


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
}