package kr.co.seculink.web.model.cmon;

import java.util.List;

import lombok.Data;

@Data
public class ExcelVO
{
	private String [] headers;
	private String [] keys;
	private List data;
}
