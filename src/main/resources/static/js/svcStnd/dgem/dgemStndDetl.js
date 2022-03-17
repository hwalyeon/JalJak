let dgemStndDetl = new Vue({
    el: "#dgemStndDetlPopup",
    data: {
    	params: {
			crud: 'C',
            userId       : '' ,
            actDivCd     : '' , //활동_구분_코드
            hbitStatCd   : '' , //심박_상태_코드
            plcClssCd    : '' , //장소_구분_코드
            tempStatCd   : '' , //체온_상태_코드
            dgemStatCd   : '' , //위험감정_상태_코드 
            dgemIdx      : '' , //위험감정_지수 
            dgemSmryCntn : '' , //위험감정_요약_내용
            dgemStatCntn : '' , //위험감정_상세_내용
			currEvalCntn : '' ,
			prdtEvalCntn : ''
    	},
		summerNoteId1 : 'summerNoteId1',
		summerNoteId2 : 'summerNoteId2',
 		code: {
            actDivCdList   : [] , //활동_구분_코드_리스트
        	hbitStatCdList : [] , //심박_상태_코드_리스트
            plcClssCdList  : [] , //장소_구분_코드_리스트
        	tempStatCdList : [] , //체온_상태_코드_리스트
            dgemStatCdList : [] , //위험감정_상태_코드_리스트
		}
	},

	components: {'summer-note': summernote},
    methods: {

        initialize: function() {
        	
        	let $this = this;
        	
        	$this.initCodeList();      
        	        	
        },
        initCodeList: function() {
        	let $this = this;
            getCommonCodeList('ACT_DIV_CD'  , $this.code.actDivCdList);   //활동_구분_코드_리스트          
            getCommonCodeList('HBIT_STAT_CD', $this.code.hbitStatCdList); //심박_상태_코드_리스트          
            getCommonCodeList('PLC_CLSS_CD' , $this.code.plcClssCdList);  //장소_구분_코드_리스트          
            getCommonCodeList('TEMP_STAT_CD', $this.code.tempStatCdList); //체온_상태_코드_리스트          
            getCommonCodeList('DGEM_STAT_CD', $this.code.dgemStatCdList); //위험감정_상태_코드_리스트        
          
        },
        initPage: function(actDivCd, hbitStatCd, plcClssCd, tempStatCd, dgemStatCd) {
        	
        	let $this = this;
        	$this.resetDgemStndInfo();

        	
        	if ( !WebUtil.isNull(actDivCd, hbitStatCd, plcClssCd, tempStatCd, dgemStatCd) )
    		{	
        	
        		let params = {
        			'actDivCd'   : actDivCd   ,
        			'hbitStatCd' : hbitStatCd ,
        			'plcClssCd'  : plcClssCd  ,
        			'tempStatCd' : tempStatCd ,
        			'dgemStatCd' : dgemStatCd 
        		}
        		AjaxUtil.post({
                    url: "/svcStnd/dgem/dgemStndMng/searchDgemStndInfo.ab",
                    param: params,
                    success: function(response) {
                    	if ( !!response.rtnData.result ) {
                    		$this.params.crud = 'U';
                    		$.each(response.rtnData.result[0], function(key, val) {
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
        	
        	if ( WebUtil.isNull($this.params.actDivCd) ) {
        		Swal.alert(['활동 구분을 선택하세요.', 'info']);
        		return false;
        	}
        	
        	if ( WebUtil.isNull($this.params.hbitStatCd) ) {
        		Swal.alert(['심박 상태를 선택하세요.', 'info']);
        		return false;
        	}
        	
        	if ( WebUtil.isNull($this.params.plcClssCd) ) {
        		Swal.alert(['장소 구분을 선택하세요.', 'info']);
        		return false;
        	}
        	
        	if ( WebUtil.isNull($this.params.tempStatCd) ) {
        		Swal.alert(['체온 상태를 선택하세요.', 'info']);
        		return false;
        	}
        	
        	if ( WebUtil.isNull($this.params.dgemStatCd) ) {
        		Swal.alert(['위험감정 상태를 선택하세요.', 'info']);
        		return false;
        	}
        	
        	return true;
        },

		saveInfo: function() {
			
			let $this = this;

            if ( !this.isValid() ) {
                return false;
            }
            if($this.params.crud=='C'){
            	if(!$this.searchDupCdCk()){
            
                Swal.alert(["이미 등록된 코드 입니다.", 'warning']);
                return false;
            
            	}
            }

			AjaxUtil.post({
                url: "/svcStnd/dgem/dgemStndMng/saveInfo.ab",
                param: $this.params,
                success: function(response) {
                	Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                		closeModal($('#dgemStndDetlPopup'));
						dgemStndMng.searchDgemList(true);
                	});                	
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });

		},
		searchDupCdCk: function()
        {

            let $this = this;
            let param = $this.params

            AjaxUtil.post({
                url: "/svcStnd/dgem/dgemStndMng/searchDupCdCk.ab",
                param: $this.params,
                success: function(response) {
                    if ( response.rtnData.result.existsYn === 'Y' ) {
                        return false;
                    } else {
                        return true;
                    }
                },
                error: function (response) {
                    Swal.alert([response, 'error']);
                }
            });
        },

		deleteInfo: function() {
			
			let $this = this;
			
			$this.params.crud = 'D';
			
            AjaxUtil.post({
                url: "/svcStnd/dgem/dgemStndMng/saveInfo.ab",
                param: $this.params,
                success: function(response) {
                	Swal.alert(['삭제가 완료되었습니다.', 'success']).then(function() {
                		 closeModal($('#dgemStndDetlPopup'));
                		 dgemStndMng.searchDgemList(true);
                	});                	
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
		},
		resetDgemStndInfo: function() {
			this.params = {
				crud: 'C',
	            userId       : '' ,
	            actDivCd     : '' , //활동_구분_코드
	            hbitStatCd   : '' , //심박_상태_코드
	            plcClssCd    : '' , //장소_구분_코드
	            tempStatCd   : '' , //체온_상태_코드
	            dgemStatCd   : '' , //위험감정_상태_코드
	            dgemSmryCntn : '' , //위험감정_요약_내용
	            dgemStatCntn : '' , //위험감정_상세_내용
				currEvalCntn : '' ,
				prdtEvalCntn : ''
	    	}
		}
    },
    computed: {

    },
    watch: {

    },
    mounted: function() {
        let self = this;
        $(document).ready(function() {
            self.initialize();
        });
    }
});
