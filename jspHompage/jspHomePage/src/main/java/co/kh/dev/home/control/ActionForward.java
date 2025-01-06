package co.kh.dev.home.control;

public class ActionForward {
	private String url; 		//화면요청
	private boolean redirect;	//true redirect false forward
	
	public ActionForward(String url, boolean redirect) {
		super();
		this.url = url;
		this.redirect = redirect;
	}


	public ActionForward() {
		super();
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public boolean isRedirect() {
		return redirect;
	}


	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}


	@Override
	public String toString() {
		return "ActionForward [url=" + url + ", redirect=" + redirect + "]";
	}
	
	
	
}
