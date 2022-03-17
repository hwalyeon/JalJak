package kr.co.seculink.exception;

public class BizException extends BaseException {

	private static final long serialVersionUID = -8748026006166150804L;

	public BizException(Throwable throwable, String errCd, String[] params) {
		super(throwable, errCd, params);
	}
	
	public BizException(String errCd, String[] params) {
		super(errCd, params);
	}
	
	public BizException(String errCd) {
		super(errCd);
	}

}
