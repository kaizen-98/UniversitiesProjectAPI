package UniversitiesProjectMainPackage;

import java.util.List;

public class MyObject {

	private String name;
	private String country;
	private String stateProvince;
	private List<String> domains;
	private List<String> web_pages;
	private String alpha_two_code;

	public MyObject() {
	}

	public MyObject(String name, String country, String stateProvince, List<String> domains, List<String> webPages,
			String alphaTwoCode) {
		this.name = name;
		this.country = country;
		this.stateProvince = stateProvince;
		this.domains = domains;
		this.web_pages = webPages;
		this.alpha_two_code = alphaTwoCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}

	public List<String> getDomains() {
		return domains;
	}

	public void setDomains(List<String> domains) {
		this.domains = domains;
	}

	public List<String> getWebPages() {
		return web_pages;
	}

	public void setWebPages(List<String> webPages) {
		this.web_pages = webPages;
	}

	public String getAlphaTwoCode() {
		return alpha_two_code;
	}

	public void setAlphaTwoCode(String alphaTwoCode) {
		this.alpha_two_code = alphaTwoCode;
	}
}
