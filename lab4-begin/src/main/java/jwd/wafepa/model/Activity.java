package jwd.wafepa.model;

/**
 * Models a physical activity.
 * 
 *
 */
public class Activity {
	
	private Long id;
	private String name;
	private String adminComment="test";
	

	public Activity() {
		super();
	}

	public Activity(String name) {
		super();
		this.name = name;
	}

	public Activity(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	/**
	 * 
	 * @return Activity identifier.
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets activity identifier.
	 * @param id new identifier
 	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return Name of the activity.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param name New name of the activity.
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getAdminComment() {
		return adminComment;
	}

	public void setAdminComment(String adminComment) {
		this.adminComment = adminComment;
	}
	
	
}
