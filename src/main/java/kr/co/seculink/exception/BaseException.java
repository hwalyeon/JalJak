package kr.co.seculink.exception;

public class BaseException extends Exception {
	
	private static final long serialVersionUID = 4415356606330579261L;
	protected final String errCd;
	protected String[] cdParams;
	protected Throwable throwable;
	
	public BaseException(Throwable throwable, String errCd, String[] params) {
		super(errCd);
		
		this.throwable = throwable;
		this.errCd = errCd;
		this.cdParams = params;
	}
	
	public BaseException(String errCd, String[] params) {
		this(null, errCd, params);
	}
	
	public BaseException(String errCd) {
		this(null, errCd, null);
	}

	public String[] getCdParams() {
		return cdParams;
	}

	public void setCdParams(String[] cdParams) {
		this.cdParams = cdParams;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	public String getErrCd() {
		return errCd;
	}
}
