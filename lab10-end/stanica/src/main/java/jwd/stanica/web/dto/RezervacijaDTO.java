package jwd.stanica.web.dto;

public class RezervacijaDTO{

	private Long id;

	private Long linijaId;

	public void setId(Long id){
		this.id=id;
	}
	
	public Long getId(){
		return this.id;
	}
	public Long getLinijaId() {
		return linijaId;
	}

	public void setLinijaId(Long linijaId) {
		this.linijaId = linijaId;
	}
}