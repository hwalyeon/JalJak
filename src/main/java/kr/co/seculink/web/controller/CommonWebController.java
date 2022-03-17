package kr.co.seculink.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommonWebController {
	// thymleaf 적용하지 않을경우 static의 index.html로 간다.
	@GetMapping(path = "/")
	public String index() {
		log.debug("index!!");
		return "common/main/index";
	}

	@GetMapping(path = "/login.pg")
	public String login() {
		return "common/main/login";
	}

	@GetMapping(path = "/main.pg")
	public String main() {
		return "common/main/main";
	}

	@RequestMapping("/set/userMng.pg")
	public String getSetUserMng() {
		return "set/userMng";
	}

	@RequestMapping("/set/userRoleMng.pg")
	public String getSetUserRoleMng() {
		return "set/userRoleMng";
	}


	@RequestMapping("/set/roleMng.pg")
	public String getSetRoleMng() {
		return "set/roleMng";
	}

	@RequestMapping("/set/menuMng.pg")
	public String getSetMenuMng() {
		return "set/menuMng";
	}

	@RequestMapping("/set/menuRoleMng.pg")
	public String getSetMenuRoleMng() {
		return "set/menuRoleMng";
	}

	@RequestMapping("/set/cdMng.pg")
	public String getSetCdMng() {
		return "set/cdMng";
	}

	@RequestMapping("/svcStnd/strs/strsStndMng.pg")
	public String getSvsStndStrsStrsStndMng() {
		return "svcStnd/strs/strsStndMng";
	}

	//위험감정기준
	@RequestMapping("/svcStnd/dgem/dgemStndMng.pg")
	public String getSvcStndDgemDgemStndMng() {return "svcStnd/dgem/dgemStndMng";}

	//성장기준
	@RequestMapping("/svcStnd/grow/growStndMng.pg")
	public String getSvcStndGrowGrowStndMng() {return "svcStnd/grow/growStndMng";}

	@RequestMapping("/svcStnd/grow/growJudgStndMng.pg")
	public String getSvcStndGrowGrowJudgStndMng() {return "svcStnd/grow/growJudgStndMng";}

	//활동기준
	@RequestMapping("/svcStnd/grow/actStndMng.pg")
	public String getSvcStndGrowActStndMng() {return "svcStnd/grow/actStndMng";}

	//비만기준
	@RequestMapping("/svcStnd/fat/fatStndMng.pg")
	public String getSvcStndFatFatStndMng() {return "svcStnd/fat/fatStndMng";}

	//비만판정기준
	@RequestMapping("/svcStnd/fat/fatJudgStndMng.pg")
	public String getSvcStndFatFatJudgStndMng() {return "svcStnd/fat/fatJudgStndMng";}

	//휴식대사량기준
	@RequestMapping("/svcStnd/fat/bpalCalcStndMng.pg")
	public String getSvcStndFatBpalCalcStndMng() {return "svcStnd/fat/bpalCalcStndMng";}

	//영양소정보
	@RequestMapping("/svcStnd/nutr/nutrInfoMng.pg")
	public String getSvcStndNutrNutrInfoMng() {return "svcStnd/nutr/nutrInfoMng";}

	//일일 영양소섭취기준
	@RequestMapping("/svcStnd/nutr/ddNutrEatStndMng.pg")
	public String getSvcStndNutrDdNutrEatStndMng() {return "svcStnd/nutr/ddNutrEatStndMng";}

	//일일 권장섭취량기준
	@RequestMapping("/svcStnd/nutr/ddRcmdEatStndMng.pg")
	public String getSvcStndNutrDdRcmdEatStndMng() {return "svcStnd/nutr/ddRcmdEatStndMng";}

	//위치정보
	@RequestMapping("/svcStnd/loc/locInfoMng.pg")
	public String getSvcStndLocLocInfoMng() {return "svcStnd/loc/locInfoMng";}

	//교육시설정보보
	@RequestMapping("/svcStnd/loc/eorgInfoMng.pg")
	public String getSvcStndLocEorgInfoMng() {return "svcStnd/loc/eorgInfoMng";}

	//식품정보
	@RequestMapping("/svcStnd/food/foodInfoMng.pg")
	public String getSvcStndFoodFoodInfoMng() {return "svcStnd/food/foodInfoMng";}

	//일일 신체활동수준
	@RequestMapping("/svcStnd/fat/ddPalMng.pg")
	public String getSvcStndFatDdPalMng() {return "svcStnd/fat/ddPalMng";}

	//밴드/개통정보
	@RequestMapping("/devc/band/bandOpenInfoMng.pg")
	public String getDevcBandBandOpenInfoMng() {return "devc/band/bandOpenInfoMng";}

	//약관정보
	@RequestMapping("/cmon/stnd/termInfoMng.pg")
	public String getCmonStndTermInfoMng() {return "cmon/stnd/termInfoMng";}

	//고객지원센터
	@RequestMapping("/cmon/stnd/csInfoMng.pg")
	public String getCmonStndCsInfoMng() {return "cmon/stnd/csInfoMng";}

	//보호자 상세(등록)
	@RequestMapping("/user/prnt/prntInfoMng.pg")
	public String getUserPrntPrntInfoMng() {return "user/prnt/prntInfoMng";}

	//학생정보
	@RequestMapping("/user/stdt/stdtInfoMng.pg")
	public String getUserStdtStdtInfoMng() {return "user/stdt/stdtInfoMng";}

}