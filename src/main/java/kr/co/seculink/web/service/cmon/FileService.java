package kr.co.seculink.web.service.cmon;

import kr.co.seculink.exception.BizException;
import kr.co.seculink.web.model.cmon.TcFileVO;

public interface FileService 
{
	public TcFileVO search(TcFileVO file) throws BizException;
	
	public void save(TcFileVO file) throws BizException;
	
	public boolean delete(String fileNo) throws BizException;
	
	public boolean delete(TcFileVO file) throws BizException;
	/*
	public Resource load(String fileNm);
	
	public void deleteAll();
	
	public Stream<Path> loadAll();
	*/
}
