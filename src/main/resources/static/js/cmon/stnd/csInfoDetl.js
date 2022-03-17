let csInfoDetl = new Vue({
    el: "#csInfoDetlPopup",
    data: {
		csInfoMngInfo: {
    		crud: 'C',
			regNo:'',
			csTelNo:'',
			csMailAddr:'',
			csUrl:'',
			bandHpgeUrl:''
    	},
		code: {
			csInfoList: []
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
        },
        initPage: function(regNo) {
        	
        	let $this = this;
        	$this.resetCsInfo();
        	console.log($this.csInfoMngInfo);

        	
        	if ( !WebUtil.isNull(regNo) )
    		{
        		let params = {
        			'regNo' : regNo
        		}
        		
        		AjaxUtil.post({
                    url: "/cmon/stnd/csInfoMng/searchCsInfo.ab",
                    param: params,
                    success: function(response) {
                    	if ( !!response.rtnData.result ) {
                    		$this.csInfoMngInfo.crud = 'U';
                    		$.each(response.rtnData.result, function(key, val) {
            					$this.csInfoMngInfo[key] = val;
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
        	
        	if ( WebUtil.isNull($this.csInfoMngInfo.regNo) ) {
        		Swal.alert(['등록번호를 입력하세요.', 'info']);
        		return false;
        	}
        	
        	if ( WebUtil.isNull($this.csInfoMngInfo.csTelNo) ) {
        		Swal.alert(['고객지원 전화번호를 입력하세요.', 'info']);
        		return false;
        	}
        	
        	if ( WebUtil.isNull($this.csInfoMngInfo.csMailAddr) ) {
        		Swal.alert(['고객지원 메일주소를 입력하세요.', 'info']);
        		return false;
        	}

			if ( WebUtil.isNull($this.csInfoMngInfo.csUrl) ) {
				Swal.alert(['고객지원 URL을 입력하세요.', 'info']);
				return false;
			}

			if ( WebUtil.isNull($this.csInfoMngInfo.bandHpgeUrl) ) {
				Swal.alert(['밴드 홈페이지 URL을 입력하세요.', 'info']);
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
                url: "/cmon/stnd/csInfoMng/saveInfo.ab",
                param: $this.csInfoMngInfo,
                success: function(response) {
                	Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                		closeModal($('#csInfoDetlPopup'));
						csInfoMng.searchCsInfoList(true);
                	});                	
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });

		},
		deleteInfo: function() {
			
			let $this = this;
			
			$this.csInfoMngInfo.crud = 'D';
			
            AjaxUtil.post({
                url: "/cmon/stnd/csInfoMng/saveInfo.ab",
                param: $this.csInfoMngInfo,
                success: function(response) {
                	Swal.alert(['삭제가 완료되었습니다.', 'success']).then(function() {
                		 closeModal($('#csInfoDetlPopup'));
						csInfoMng.searchCsInfoList(true);
                	});                	
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
		},
		resetCsInfo: function() {
			this.csInfoMngInfo = {
				crud: 'C',
				regNo:'',
				csTelNo:'',
				csmailAddr:'',
				csUrl:'',
				bandHpgeUrl:''
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
