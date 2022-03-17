let termInfoDetl = new Vue({
    el: "#termInfoDetlPopup",
    data: {
		termInfoMngInfo: {
    		crud: 'C',
			termDivCd:'',
			termVer:'',
			aplyStrtDt:'',
			termCntn:'',
			essnYn:''
    	},
		code: {
			termInfoList: []
		}
	},
	components: {'summer-note': summernote },
    methods: {

        initialize: function() {
        	
        	let $this = this;
        	
        	$this.initCodeList();
        	        	
        },
        initCodeList: function() {
        	let $this = this;
			getCommonCodeList('TERM_DIV_CD',$this.code.termInfoList);

        },
        initPage: function(termDivCd) {
        	
        	let $this = this;
        	$this.resetTermInfo();
			console.log("termInfoMngInfo" + $this.termInfoMngInfo.termVer);
			console.log($this.code.termInfoList);
			console.log($this.termInfoMngInfo);
        	
        	if ( !WebUtil.isNull(termDivCd) )
    		{
        		let params = {
        			'termDivCd' : termDivCd
        		}
        		
        		AjaxUtil.post({
                    url: "/cmon/stnd/termInfoMng/searchTermInfo.ab",
                    param: params,
                    success: function(response) {
                    	if ( !!response.rtnData.result ) {
                    		$this.termInfoMngInfo.crud = 'U';
                    		$.each(response.rtnData.result, function(key, val) {
            					$this.termInfoMngInfo[key] = val;
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
        	
        	if ( WebUtil.isNull($this.termInfoMngInfo.termDivCd) ) {
        		Swal.alert(['성장판정코드 입력하세요.', 'info']);
        		return false;
        	}
        	
        	if ( WebUtil.isNull($this.termInfoMngInfo.termVer) ) {
        		Swal.alert(['성장지수_FR를 입력하세요.', 'info']);
        		return false;
        	}
        	
        	if ( WebUtil.isNull($this.termInfoMngInfo.aplyStrtDt) ) {
        		Swal.alert(['성장지수_TO를 입력하세요.', 'info']);
        		return false;
        	}

			if ( WebUtil.isNull($this.termInfoMngInfo.termCntn) ) {
				Swal.alert(['요약내용를 입력하세요.', 'info']);
				return false;
			}

			if ( WebUtil.isNull($this.termInfoMngInfo.essnYn) ) {
				Swal.alert(['상세내용를 입력하세요.', 'info']);
				return false;
			}
        	return true;
        },

		saveInfo: function() {
			
			let $this = this;

            if ( !this.isValid() ) {
                return false;
            }

			AjaxUtil.post({
                url: "/cmon/stnd/termInfoMng/saveInfo.ab",
                param: $this.termInfoMngInfo,
                success: function(response) {
                	Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                		closeModal($('#termInfoDetlPopup'));
						termInfoMng.searchTermInfoList(true);
                	});                	
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });

		},
		deleteInfo: function() {
			
			let $this = this;
			
			$this.termInfoMngInfo.crud = 'D';
			
            AjaxUtil.post({
                url: "/cmon/stnd/termInfoMng/saveInfo.ab",
                param: $this.termInfoMngInfo,
                success: function(response) {
                	Swal.alert(['삭제가 완료되었습니다.', 'success']).then(function() {
                		 closeModal($('#termInfoDetlPopup'));
						termInfoMng.searchTermInfoList(true);
                	});                	
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
		},
		resetTermInfo: function() {
			this.termInfoMngInfo = {
				crud: 'C',
				termDivCd:'',
				termVer:'',
				aplyStrtDt:'',
				termCntn:'',
				essnYn:''
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
