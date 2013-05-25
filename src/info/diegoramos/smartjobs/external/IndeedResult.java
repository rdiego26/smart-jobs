package info.diegoramos.smartjobs.external;

/**
 * Representa os campos obtidos pelo JSON do Indeed
 * @author Diego Ramos <rdiego26@gmail>
 *
 */
public class IndeedResult {

	private String jobTitle;
	private String company;
	private String date;
	private String snippet;
	private String url;
	
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSnippet() {
		return snippet;
	}
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public IndeedResult(String jobTitle, String company, String date,
			String snippet, String url) {
		super();
		this.jobTitle = jobTitle;
		this.company = company;
		this.date = date;
		this.snippet = snippet;
		this.url = url;
	}
	
}