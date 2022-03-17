let prntInfoDetl = new Vue({
    el: "#prntInfoDetlPopup",
    data: {
    	params: {
    		crud              : 'C',
    		userId            : '' ,
    		guarNo            : '' , //보호자_번호
    		guarNm            : '' , //보호자_명
			guarTelNo         : '' , //보호자_전화_번호
    		guarPw            : '' , //보호자_비밀번호
			selfCertDttm      : '' , //본인_인증_일시
			autoLoginYn       : '' , //자동_로그인_여부
			devcCertVal       : '' , //장치_인증_값
    		raceDivCd         : '' , //인종_구분_코드
    		sexCd             : '' , //성별_코드
			hghtVal           : '' , //키_값
			wghtVal		      : '' , //체중_값
			bmiVal		      : '' , //BMI_값
			sposNo		      : '' , //배우자_번호
			dzoneAlamYn       : '' , //위험지역_알림_여부
			szoneAlamYn       : '' , //세이프존_알림_여부
			fallAlamYn	      : '' , //낙상_발생_알림_여부
			hbitAbnmAlamYn	  : '' , //심박_이상_알림_여부
			tempAbnmAlamYn    : '' , //체온_이상_알림_여부
			bodyHistAlamYn    : '' , //신체_기록_알림_여부
			mealNoinAlamYn    : '' , //식사_미입력_알림_여부
			excsNoinAlamYn    : '' , //운동_미입력_알림_여부
			cbeeUseAlamYn     : '' , //캐시비_사용_알림_여부
			batrLackAlamYn    : '' , //배터리_부족_알림_여부
			ltrmNuseAlamYn    : '' , //장기_미사용_알림_여부
			entrDt            : '' , //가입_일자
			relsDt            : '' , //해지_일자
			relsResnCd        : '' , //해지_사유_코드
			relsResnCntn      : '' , //해지_사유_내용
			entrStatCd        : '' , //가입_상태_코드
			paging            : 'Y',
			totalCoun         : 0  ,
			rowCount          : 30 ,
			currentPage       : 1  ,
			currentIndex      : 0
    	},
		code: {
			sexCdList      : [] , //성별_코드_리스트
			raceDivCdList  : [] , //인종_구분_코드_리스트
			entrStatCdList : [] , //가입_상태_코드_리스트
			relsResnCdList : [] , //해지_사유_코드_리스트
		}
	},
    methods: {

        initialize: function() {
        	
        	let $this = this;

        	$this.initValue();
        	$this.initCodeList();      
        	        	
        },
        initCodeList: function() {

        	let $this = this;
			getCommonCodeList('SEX_CD'      , $this.code.sexCdList);      //성별_코드_리스트
			getCommonCodeList('RACE_DIV_CD' , $this.code.raceDivCdList);  //인종_구분_코드_리스트
			getCommonCodeList('ENTR_STAT_CD', $this.code.entrStatCdList); //가입_상태_코드_리스트
			getCommonCodeList('RELS_RESN_CD', $this.code.relsResnCdList); //해지_사유_코드_리스트

		},
		initValue()
		{
			let $this = this;
			$this.userId = SessionUtil.getUserId();
		},
        initPage: function(guarNo) {

			let $this = this;
			$this.resetPrntDetlInfo();
			if ( !WebUtil.isNull(guarNo) )
			{
				let params = {
					'guarNo' : guarNo
				}
				AjaxUtil.post({
					url: "/user/prnt/prntInfoMng/searchPrntInfo.ab",
					param: params,
					success: function(response) {
						if ( !!response.rtnData.result )
						{
							console.log(response.rtnData.result.result);
							$this.params.crud = 'U';

							$.each(response.rtnData.result.result, function(key, val) {
								$this.params[key] = val;

							});
						}
					},
					error: function (response) {
						Swal.alert([response, 'error']);
					}
				});
			}
		},

			isValid: function() {

        	let $this = this;        	        	

        	if ( WebUtil.isNull($this.params.autoLoginYn) ) {
        		Swal.alert(['자동로그인 여부는 필수 입력 값입니다.', 'info']);
        		return false;
        	}
        	if ( WebUtil.isNull($this.params.hghtVal) ) {
        		Swal.alert(['키는 필수 입력 값입니다.', 'info']);
        		return false;
        	}
        	if ( WebUtil.isNull($this.params.wghtVal) ) {
        		Swal.alert(['체중은 여부는 필수 입력 값입니다.', 'info']);
        		return false;
        	}
        	if ( WebUtil.isNull($this.params.bmiVal) ) {
        		Swal.alert(['BMI는 필수 입력 값입니다.', 'info']);
        		return false;
        	}
        	if ( WebUtil.isNull($this.params.dzoneAlamYn) ) {
        		Swal.alert(['위험지역 알림 여부는 필수 입력 값입니다.', 'info']);
        		return false;
        	}
        	if ( WebUtil.isNull($this.params.szoneAlamYn) ) {
        		Swal.alert(['위험지역 이탈 알림 여부는 필수 입력 값입니다.', 'info']);
        		return false;
        	}
        	if ( WebUtil.isNull($this.params.fallAlamYn) ) {
        		Swal.alert(['낙상 알림 여부는 필수 입력 값입니다.', 'info']);
        		return false;
        	}
        	if ( WebUtil.isNull($this.params.hbitAbnmAlamYn) ) {
        		Swal.alert(['심박 이상 알림 여부는 필수 입력 값입니다.', 'info']);
        		return false;
        	}
        	if ( WebUtil.isNull($this.params.tempAbnmAlamYn) ) {
        		Swal.alert(['체온 이상 알림 여부는 필수 입력 값입니다.', 'info']);
        		return false;
        	}
        	if ( WebUtil.isNull($this.params.bodyHistAlamYn) ) {
        		Swal.alert(['신체 기록 알림 여부는 필수 입력 값입니다.', 'info']);
        		return false;
        	}
        	if ( WebUtil.isNull($this.params.mealNoinAlamYn) ) {
        		Swal.alert(['식사 미입력 알림 여부는 필수 입력 값입니다.', 'info']);
        		return false;
        	}
        	if ( WebUtil.isNull($this.params.excsNoinAlamYn) ) {
        		Swal.alert(['운동 미입력 알림 여부는 필수 입력 값입니다.', 'info']);
        		return false;
        	}
        	if ( WebUtil.isNull($this.params.cbeeUseAlamYn) ) {
        		Swal.alert(['캐시비 사용 알림 여부는 필수 입력 값입니다.', 'info']);
        		return false;
        	}
        	if ( WebUtil.isNull($this.params.batrLackAlamYn) ) {
        		Swal.alert(['베터리 부족 알림 여부는 필수 입력 값입니다.', 'info']);
        		return false;
        	}
        	if ( WebUtil.isNull($this.params.ltrmNuseAlamYn) ) {
        		Swal.alert(['장기 미사용 알림 여부는 필수 입력 값입니다.', 'info']);
        		return false;
        	}


        	return true;
        },
		//숫자타입 체크
		nanCk : function(event)
		{
			let $this = this;

			let name  = event.target.name;
			let cdNm;
			if(isNaN($this.params[name])){

				if(name=='guarTelNo'){
					cdNm='보호자 전화번호';
				}else if(name=='selfCertDttm'){
					cdNm='본인 인증 일시';
				}else if(name=='hghtVal'){
					cdNm='키';
				}else if(name=='wghtVal'){
					cdNm='몸무게';
				}else if(name=='bmiVal'){
					cdNm='BMI';
				}

				this.params[name] = this.params[name].replace(/\D/g,'');
				Swal.alert([cdNm+"는 숫자만 사용 가능합니다.", 'info']);
			}

		},
		saveprntInfoDetl: function() {
			
			let $this = this;
			
            if ( !this.isValid() ) {
                return false;
            }

			AjaxUtil.post({
                url: "/user/prnt/prntInfoMng/savePrntInfoDetl.ab",
                param: $this.params,
                success: function(response) {
                	Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                		closeModal($('#prntInfoDetlPopup'));
						prntInfoMng.searchPrntInfoList(true);
                	});                	
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
		},

		deleteUser: function() {
			
			let $this = this;
			
			$this.params.crud = 'D';
			
            AjaxUtil.post({
                url: "/user/prnt/prntInfoMng/savePrntInfoDetl.ab",
                param: $this.params,
                success: function(response) {
                	Swal.alert(['삭제가 완료되었습니다.', 'success']).then(function() {
                		 closeModal($('#prntInfoDetlPopup'));
						prntInfoMng.searchPrntInfoList(true);
                	});
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
		},
		resetPrntDetlInfo: function() {
			this.params = {
				crud           : 'C',
				userId         : '' ,
				guarNo         : '' , //보호자_번호
				guarNm         : '' , //보호자_명
				guarTelNo      : '' , //보호자_전화_번호
				guarPw         : '' , //보호자_비밀번호
				selfCertDttm   : '' , //본인_인증_일시
				autoLoginYn    : '' , //자동_로그인_여부
				devcCertVal    : '' , //장치_인증_값
				raceDivCd      : '' , //인종_구분_코드
				sexCd          : '' , //성별_코드
				hghtVal        : '' , //키_값
				wghtVal		   : '' , //체중_값
				bmiVal		   : '' , //BMI_값
				sposNo		   : '' , //배우자_번호
				dzoneMoinAlamYn: '' , //위험지역_진입_알림_여부
				dzoneMoutAlamYn: '' , //위험지역_이탈_알림_여부
				szoneMoinAlamYn: '' , //세이프존_진입_알림_여부
				szoneMoutAlamYn: '' , //세이프존_이탈_알림_여부
				fallOccrAlamYn : '' , //낙상_발생_알림_여부
				strsAbnmAlamYn : '' , //스트레스_이상_알림_여부
				selfCertDttm   : '' , //가입_일자
				relsResnCntn   : '' , //해지_일자
				relsResnCd     : '' , //해지_사유_코드
				relsResnCntn   : '' , //해지_사유_내용
				entrStatCd     : '' , //가입_상태_코드
				paging         : 'Y',
				totalCoun      : 0  ,
				rowCount       : 30 ,
				currentPage    : 1  ,
				currentIndex   : 0
	    	}
		},
    },
    computed:
	{
    },
    watch:
	{
    },
    mounted: function() {
        let self = this;
        $(document).ready(function() {
            self.initialize();
        });
    }
});
