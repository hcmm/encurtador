package br.com.jettyrest.model;

public class Url {
	private Integer id;
	private String url;
	private String urlCurta;
	private String alias;
	
	public Url() {		
	}
	public Url(Integer id, String url, String urlCurta, String alias) {
		super();
		this.id = id;
		this.url = url;
		this.urlCurta = urlCurta;
		this.alias = alias;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrlCurta() {
		return urlCurta;
	}
	public void setUrlCurta(String urlCurta) {
		this.urlCurta = urlCurta;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	@Override
	public String toString() {
		return "Url [id=" + id + ", url=" + url + ", urlCurta=" + urlCurta + ", alias=" + alias + "]";
	}
}
