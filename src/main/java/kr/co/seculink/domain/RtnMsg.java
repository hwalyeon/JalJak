package kr.co.seculink.domain;

import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("공통응답")
public class RtnMsg<T> {
	
	@ApiModelProperty(notes = "결과 코드 [00:Success, 91:Parameter error, 92:Request JSON parse error, 99:System Error]", position = -2)
	private String rtnCd = "00";
	@ApiModelProperty(notes = "결과 메세지", position = -1)
	private String rtnMsg = "";
	@ApiModelProperty(notes = "결과 데이터", position = 0)
	private T rtnData;
	
	private String paging    = "N";
	private int totalCount   = 0;
	private int rowCount     = 30;
	private int currentPage  = 0;
	private int currentIndex = 0;
	
	public void setRtnData(T rtnData, Object params) {
		this.rtnData = rtnData;
		if ( params != null )
		{
			if ( params instanceof Map )
			{
				Map paramMap = (Map) params;
				if ( paramMap.get("paging") != null )
				{
					rowCount     = paramMap.get("rowCount")     == null ? 30 : Integer.parseInt(String.valueOf(paramMap.get("rowCount")));
					currentPage  = paramMap.get("currentPage")  == null ? 0  : Integer.parseInt(String.valueOf(paramMap.get("currentPage")));
					currentIndex = paramMap.get("currentIndex") == null ? 0  : Integer.parseInt(String.valueOf(paramMap.get("currentIndex")));
				}
			}
		}
	}
}
