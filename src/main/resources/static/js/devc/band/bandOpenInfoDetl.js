let bandOpenInfoDetl = new Vue({
    el: "#bandOpenInfoDetlPopup",
    data: {
    	params: {
    		crud           : 'C',
			userId         : '' ,
			bandIdTemp     : '' ,
			bandId         : '' ,  //밴드_ID
			bandYtypCd     : '' ,  //밴드_출고_년월
			bandMdlCd      : '' ,  //밴드_모델_코드
			telNo          : '' ,  //밴드_전화_번호
			guarTelNo      : '' ,  //보호자_전화_번호
			bandOpenStatCd : '' ,  //밴드_개통_상태_코드
			blthId         : '' ,  //블루투스_ID
			apiUrlGramNo   : '' ,  //API_URL_전문_번호
			apiUrlDttm     : '' ,  //API_URL_일시
			openGramNo     : '' ,  //개통_전문_번호
			checkDupBandId : '' ,  //밴드ID_확인_여부
			paging         : 'Y',
			totalCoun      : 0  ,
			rowCount       : 30 ,
			currentPage    : 1  ,
			currentIndex   : 0
    	},
		code: {
			bandOpenStatCdList : [] , //밴드_개통_상태_코드_리스트
			bandYtypCdList     : [] , //밴드_출고년월_리스트
			bandMdlCdList      : [] , //밴드_모델_코드_리스트
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
			getCommonCodeList('BAND_MDL_CD', $this.code.bandMdlCdList);  //밴드_모델_코드_리스트
			getCommonCodeList('BAND_OPEN_STAT_CD', $this.code.bandOpenStatCdList);//밴드_개통_상태_코드_리스트

		},
		initValue()
		{
			let $this = this;
			$this.initBandYtypCdValue();
		},
        initPage: function(bandId) {

			let $this = this;
			$this.resetBandOpenInfo();

			if ( !WebUtil.isNull(bandId) )
			{
				let params = {
					'bandId' : bandId
				}
				AjaxUtil.post({
					url: "/devc/band/bandOpenInfoMng/searchBandOpenInfo.ab",
					param: params,
					success: function(response) {
						if ( !!response.rtnData.result )
						{
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

		//출고_년월_리스트_값 세팅
		initBandYtypCdValue: function() {
			let $this = this;
			for (let i = 2022; i < 2032; i++) {
				let cdVal;
				let cdNm;
				for (let j = 1; j < 13; j++) {
					if (j < 10) {
						cdVal = i + '0' + j;
						cdNm = i + '-0' + j;
						$this.code.bandYtypCdList.push({'cdVal': cdVal, 'cdNm': cdNm});
					} else {
						cdVal = String(i) + j;
						cdNm = i + '-' + j;
						$this.code.bandYtypCdList.push({'cdVal': cdVal, 'cdNm': cdNm});
					}
				}
			}
		},
			isValid: function() {

        	let $this = this;        	        	
        	
        	if ( WebUtil.isNull($this.params.bandId) ) {
        		Swal.alert(['밴드ID는 필수 입력 값입니다.', 'info']);
        		return false;
        	}


        	return true;
        },
		//밴드 ID 중복 검사
		searchDupBandId: function()
		{

        	let $this = this;

			let bandIdStr2 = $this.params.bandId.substr(1,1);
			let bandIdStr3 = $this.params.bandId.substr(2,1);


        	if ( WebUtil.isNull($this.params.bandId) ) {
        		Swal.alert(['밴드ID를 입력하세요.', 'info']);
        		return false;
        	}if($this.params.bandId.length != 10) {
        		Swal.alert(['밴드ID의 자리수는 10자리입니다.', 'info']);
        		return false;
        	}if(bandIdStr2 != 0 && bandIdStr2 != 1 ) {
				Swal.alert(['ID의 2번째 자리는 0,1 중 선택해야 합니다.', 'info']);
				return false;
			}
			if(bandIdStr2 == 0 && bandIdStr3 == 0 ) {
				Swal.alert(['ID의 2번째, 3번째 자리에 동시에 0을 입력할 수 없습니다.', 'info']);
				return false;
			}

			AjaxUtil.post({
                url: "/devc/band/bandOpenInfoMng/searchDupBandId.ab",
                param: $this.params,
                success: function(response) {
                	if ( response.rtnData.result.existsYn === 'N' ) {
                		$this.params.checkDupBandId = 'Y';
                		 $this.params.bandIdTemp = $this.params.bandId;
                		Swal.alert(['해당 아이디는 사용할 수 있습니다.', 'success']);


                	} else {
                		$this.params.BandId = '';
                		Swal.alert(['해당 아이디는 이미 사용중입니다.', 'info']);
                	}
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
        },
		numberingSelect: function(){
        let $this =this;
        $this.params.bandId = '';
		},
		//밴드ID 채번
		numberingBandId: function()
		{
			let $this = this;

			if ( WebUtil.isNull($this.params.bandYtypCd) ) {
				Swal.alert(['출고년월을 선택하세요.', 'info']);
				return false;
			}if ( WebUtil.isNull($this.params.bandMdlCd) ) {
				Swal.alert(['모델타입을 선택하세요.', 'info']);
				return false;
			}
			$this.params.checkDupBandId = 'N';

			AjaxUtil.post({
				url: "/devc/band/bandOpenInfoMng/numberingBandId.ab",
				param: $this.params,
				success: function(response) {
					if ( !!response.rtnData.result ) {
						$this.params.bandId = response.rtnData.result.result.bandId;

					}console.log($this.params.bandId );
				},
				error: function (response) {
					Swal.alert([response, 'error']);
				}
			});
		},
		//숫자타입 체크
		nanCk : function(event)
		{
			let $this = this;

			let name  = event.target.name;
			let cdNm;
			if(isNaN($this.params[name])){

				if(name=='telNo'){
					cdNm='전화 번호';
				}else if(name=='guarTelNo'){
					cdNm='보호자 전화번호';
				}else{
					cdNm='밴드ID';
				}

				this.params[name] = this.params[name].replace(/\D/g,'');
				Swal.alert([cdNm+"는 숫자만 사용 가능합니다.", 'info']);
			}

			if(name=='bandId'){
				$this.params.bandYtypCd ='';
				$this.params.bandMdlCd  ='';
			}
		},
		saveBandOpenInfoDetl: function() {
			
			let $this = this;
			
            if ( !this.isValid() ) {
                return false;
            }
            if ( $this.params.crud === 'C' ) {
	            if ( $this.params.checkDupBandId != 'Y' ) {
	        		Swal.alert(['ID 확인을 하지 않았습니다.', 'info']);
	        		return false;
	        	}
				if($this.params.bandId != $this.params.bandIdTemp) {
						Swal.alert(['ID 확인을 하지 않았습니다.', 'info']);
						return false;
				}
            }

            $this.params.bandMdlCd = ($this.params.bandId).substr(3,1);

			AjaxUtil.post({
                url: "/devc/band/bandOpenInfoMng/saveBandOpenInfoDetl.ab",
                param: $this.params,
                success: function(response) {
                	Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                		closeModal($('#bandOpenInfoDetlPopup'));
						bandOpenInfoMng.searchBandOpenInfoList(true);
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
                url: "/devc/band/bandOpenInfoMng/saveBandOpenInfoDetl.ab",
                param: $this.params,
                success: function(response) {
                	Swal.alert(['삭제가 완료되었습니다.', 'success']).then(function() {
                		 closeModal($('#bandOpenInfoDetlPopup'));
						bandOpenInfoMng.searchBandOpenInfoList(true);
                	});
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
		},
		resetBandOpenInfo: function() {
			this.params = {
				crud           : 'C',
				userId         : '' ,
				bandId         : '' ,  //밴드_ID
				bandYtypCd     : '' ,  //밴드_출고_년월
				bandMdlCd      : '' ,  //밴드_모델_코드
				telNo          : '' ,  //밴드_전화_번호
				guarTelNo      : '' ,  //보호자_전화_번호
				bandOpenStatCd : 'STBY' ,  //밴드_개통_상태_코드
				paging         : 'Y',
				totalCoun      : 0  ,
				rowCount       : 30 ,
				currentPage    : 1  ,
				currentIndex   : 0 ,
	    		checkDupBandId: 'N'
	    	}
		},
    },
    computed: {

    },
    watch: {
    	'params.bandId': function(newVal, oldVal) {
    		this.params.checkDupBandId = 'N';
		},


    },
    mounted: function() {
        let self = this;
        $(document).ready(function() {
            self.initialize();
        });
    }
});
