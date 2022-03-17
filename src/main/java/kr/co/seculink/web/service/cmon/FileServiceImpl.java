package kr.co.seculink.web.service.cmon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.CopyOption;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.co.seculink.exception.BizException;
import kr.co.seculink.util.XUtil;
import kr.co.seculink.web.model.cmon.TcFileVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("FileService")
public class FileServiceImpl implements FileService 
{
	@Value("${upload.prof}")
	private String profUpload;
	
	@Value("${upload.event}")
	private String eventUpload;
	
	@Value("${upload.hwork}")
	private String hworkUpload;
	
	private Path uploads;
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate dao;

	private void init(TcFileVO vo) throws BizException
	{
		if ( "STDT".equals(vo.getFileDivCd()) || "STDT_IMAGE".equals(vo.getFileDivCd()) || "LCTR".equals(vo.getFileDivCd()))
		{
			uploads = Paths.get(profUpload);
		}
		else if ( "REQ_ATTD".equals(vo.getFileDivCd()) || "RES_PRST".equals(vo.getFileDivCd()) )
		{
			uploads = Paths.get(eventUpload);
		}
		else if ( "HWORK".equals(vo.getFileDivCd()) )
		{
			uploads = Paths.get(hworkUpload);
		}
		
		try {
			if ( !Files.exists(uploads) ) {
				Files.createDirectories(uploads);
			}
		} catch ( IOException e ) {
			e.printStackTrace();
			throw new BizException("EFIL001");
		}		
	}
	
	@Override
	public void save(TcFileVO vo) throws BizException
	{
		this.validation(vo);
		
		this.createDirectory(vo);
		
		this.createPgicFileNm(vo);
		
		try {
			if ( "STDT".equals(vo.getFileDivCd()) || "LCTR".equals(vo.getFileDivCd()) || "HWORK".equals(vo.getFileDivCd()) )	{
				//uploads/stdt/id.png
				//uploads/lctr/id.png
				
				this.deletePgicFileNm(vo);
				
				Path uploadPath = Paths.get(uploads.toAbsolutePath().toString() + File.separator + vo.getFilePath());
				log.debug("uploadPath : " + uploadPath.toAbsolutePath());
				CopyOption[] options = new CopyOption[] {StandardCopyOption.REPLACE_EXISTING};
				Files.copy(vo.getFile().getInputStream(), uploadPath.resolve(vo.getPgicFileNm()), options);
			
			} else if ( "STDT_IMAGE".equals(vo.getFileDivCd()) ) {
				//uploads/stdt/id_20210121114900.png
				
				Path uploadPath = Paths.get(uploads.toAbsolutePath().toString() + File.separator + vo.getFilePath());
				log.debug("uploadPath : " + uploadPath.toAbsolutePath());
				CopyOption[] options = new CopyOption[] {StandardCopyOption.REPLACE_EXISTING};
				Files.copy(vo.getFile().getInputStream(), uploadPath.resolve(vo.getPgicFileNm()), options);
				
			} else if ( "REQ_ATTD".equals(vo.getFileDivCd()) || "RES_PRST".equals(vo.getFileDivCd()) ) {
				//uploads/events/cousNo/lectSeq/REQ_ATTD_20210121114900.png
				//uploads/events/cousNo/lectSeq/RES_PRST_20210121114900.png
				
				String image = vo.getDataURL().replace("data:image/png;base64,", "");
				log.debug(image);
				
				byte [] byteImage = Base64.decodeBase64(image);
				OutputStream stream = new FileOutputStream(uploads.toAbsolutePath().toString() + File.separator + vo.getFilePath() + vo.getPgicFileNm());
				stream.write(byteImage);
				
			} else {
				
			}
			
			int fileNo = dao.selectOne("cmon.file.selectFileNo");
			log.debug("fileNo : " + fileNo);
			vo.setFileNo(String.valueOf(fileNo));
			vo.setRegUserId(vo.getUserId());
			vo.setUptUserId(vo.getUserId());
			dao.insert("cmon.file.insertTcFile", vo);
		} catch ( FileAlreadyExistsException e ) {
			e.printStackTrace();
			throw new BizException("EFIL003");
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new BizException("EFIL001");
		}
	}

	private boolean validation(TcFileVO vo) throws BizException
	{
		if ( XUtil.isEmpty(vo.getFileDivCd()) ) {
			throw new BizException("VALID.001", new String[] {"파일구분"});
		}
				
		if ( "STDT".equals(vo.getFileDivCd()) || "STDT_IMAGE".equals(vo.getFileDivCd()) || "LCTR".equals(vo.getFileDivCd()) )	{
			
			if ( vo.getFile() == null ) {
				throw new BizException("VALID.001", new String[] {"이미지파일"});
			}
			
			if ( XUtil.isEmpty(vo.getUserId()) ) {
				throw new BizException("VALID.001", new String[] {"사용자아이디"});
			}
			
			if ( XUtil.isEmpty(vo.getLgicFileNm()) ) {
				vo.setLgicFileNm(vo.getFile().getOriginalFilename());
			}
			
			if ( XUtil.isEmpty(vo.getLgicFileNm()) ) {
				throw new BizException("VALID.001", new String[] {"원본파일명"});
			}
			
		} else if ( "REQ_ATTD".equals(vo.getFileDivCd()) || "RES_PRST".equals(vo.getFileDivCd()) ) {
			
			if ( XUtil.isEmpty(vo.getDataURL()) ) {
				throw new BizException("VALID.001", new String[] {"이미지정보"});
			}
			
			if ( XUtil.isEmpty(vo.getCousNo()) ) {
				throw new BizException("VALID.001", new String[] {"과정번호"});
			}
			
			if ( XUtil.isEmpty(vo.getLectSeq()) ) {
				throw new BizException("VALID.001", new String[] {"강의순번"});
			}
		
		} else if ( "HWORK".equals(vo.getFileDivCd()) ) {
			
			if ( vo.getFile() == null ) {
				throw new BizException("VALID.001", new String[] {"파일"});
			}
			
			if ( XUtil.isEmpty(vo.getCousNo()) ) {
				throw new BizException("VALID.001", new String[] {"과정번호"});
			}
			
			if ( XUtil.isEmpty(vo.getLectSeq()) ) {
				throw new BizException("VALID.001", new String[] {"강의순번"});
			}
			
			if ( XUtil.isEmpty(vo.getUserId()) ) {
				throw new BizException("VALID.001", new String[] {"사용자아이디"});
			}
			
			if ( XUtil.isEmpty(vo.getLgicFileNm()) ) {
				vo.setLgicFileNm(vo.getFile().getOriginalFilename());
			}
			
			if ( XUtil.isEmpty(vo.getLgicFileNm()) ) {
				throw new BizException("VALID.001", new String[] {"원본파일명"});
			}
			
		} else {
			
		}
		
		return true;
	}
	
	public void createDirectory(TcFileVO vo) throws BizException
	{
		init(vo);
		
		try {
			
			String filePath = "";
			if ( "STDT".equals(vo.getFileDivCd()) || "STDT_IMAGE".equals(vo.getFileDivCd()))
			{
				filePath = filePath + "stdt" + File.separator; 
			}
			else if ( "LCTR".equals(vo.getFileDivCd()) )
			{
				filePath = filePath + "lctr" + File.separator;
			}
			else if ( "REQ_ATTD".equals(vo.getFileDivCd()) || "RES_PRST".equals(vo.getFileDivCd()) )
			{
				filePath = filePath + "event" + File.separator + vo.getCousNo() + File.separator + vo.getLectSeq() + File.separator;
			}
			else if ( "HWORK".equals(vo.getFileDivCd()) )
			{
				filePath = filePath + "hwork" + File.separator + vo.getCousNo() + File.separator + vo.getLectSeq() + File.separator;
			}
			
			Path dir = Paths.get(uploads.toAbsolutePath().toString() + File.separator + filePath);
			log.debug("dir : " + dir.toAbsolutePath());
			
			if ( Files.notExists(dir) ) {
				Files.createDirectories(dir);
			}
			log.debug("filePath : " + filePath);
			vo.setFilePath(filePath);
			
		} catch ( IOException e ) {
			e.printStackTrace();
			throw new BizException("EFIL002");
		}
	}
	
	private void createPgicFileNm(TcFileVO vo)
	{
		String pgicFileNm = "";
		log.debug("getFileDivCd : " + vo.getFileDivCd());
		if ( "STDT".equals(vo.getFileDivCd()) || "LCTR".equals(vo.getFileDivCd()) )
		{
			String ext = vo.getLgicFileNm().split("\\.")[1];
			pgicFileNm = vo.getUserId() + "." + ext; 
		}
		else if ( "STDT_IMAGE".equals(vo.getFileDivCd()) )
		{
			String ext = vo.getLgicFileNm().split("\\.")[1];
			pgicFileNm = vo.getUserId() + "_" + System.currentTimeMillis() + "." + ext; 
		}
		else if ( "REQ_ATTD".equals(vo.getFileDivCd()) || "RES_PRST".equals(vo.getFileDivCd()) )
		{
			pgicFileNm = vo.getFileDivCd() + "_" + System.currentTimeMillis() + ".png";
			vo.setLgicFileNm(pgicFileNm);
		}
		else if ( "HWORK".equals(vo.getFileDivCd())  )
		{
			String ext = vo.getLgicFileNm().split("\\.")[1];
			pgicFileNm = vo.getUserId() + "_" + System.currentTimeMillis() + "." + ext; 
		}
		
		log.debug("pgicFileNm : " + pgicFileNm);
		vo.setPgicFileNm(pgicFileNm);
	}
	
	public boolean delete(String fileNo) throws BizException
	{
		TcFileVO vo = new TcFileVO();
		vo.setFileNo(fileNo);
		vo.setFileDivCd("");
		
		return deletePgicFileNm(vo);
	}
	
	public boolean delete(TcFileVO vo) throws BizException
	{
		return deletePgicFileNm(vo);
	}
	
	private boolean deletePgicFileNm(TcFileVO vo) throws BizException
	{
		boolean flag = false;
		if ( !XUtil.isEmpty(vo.getFileNo()) )
		{
			init(vo);
			
			TcFileVO existsFileVo = dao.selectOne("cmon.file.selectTcFile", vo);
			if ( existsFileVo != null && !XUtil.isEmpty(existsFileVo.getPgicFileNm()) )
			{
				File file = new File(uploads.toAbsolutePath().toString() + File.separator + existsFileVo.getFilePath() + existsFileVo.getPgicFileNm());
				flag = file.delete();
				if ( flag ) {
					log.debug("삭제성공");
					dao.delete("cmon.file.deleteTcFile", vo);
				} else {
					log.debug("삭제실패");
				}
			}
		}
		
		return flag;
	}
	
	public TcFileVO search(TcFileVO file) throws BizException
	{
		file = dao.selectOne("cmon.file.selectTcFile", file);
		
		return file;
	}
	
	/*
	@Override
	public org.springframework.core.io.Resource load(String fileNm) {
		try {
			Path file = uploadPath.resolve(fileNm);
			org.springframework.core.io.Resource resource = new UrlResource(file.toUri());
			
			if ( resource.exists() || resource.isReadable() ) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch ( MalformedURLException e ) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(uploadPath.toFile());		
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.uploadPath, 1).filter(path -> !path.equals(this.uploadPath)).map(this.uploadPath::relativize);
		} catch ( IOException e ) {
			throw new RuntimeException("Could not load the files!");
		}
	}
	*/
}
