let cdGrpDetl = new Vue({
    el: "#cdGrpDetlPopup",
    data: {
    	cdGrpInfo: {
    		crud: 'C',
    		cdGrp: '',
    		cdGrpNm: '',
    		useYn: ''
    	},
    	code : {
    		useYnList: [
    			{cdVal: 'Y', cdNm: 'Y'},
    			{cdVal: 'N', cdNm: 'N'}
    		]
    	}
	},
	
    methods: {

        initialize: function() {
        	
        	let $this = this;
        	
        	$this.initCodeList();
        },
        initCodeList: function() {

        },
        initPage: function(cdGrp) {
        	
        	let $this = this;
        	$this.resetCdGrpInfo();
        	
        	if ( !WebUtil.isNull(cdGrp) )
    		{
        		let params = {
        			'cdGrp' : cdGrp
        		}
        		
        		AjaxUtil.post({
                    url: "/set/cdMng/searchCdGrpList.ab",
                    param: params,
                    success: function(response) {
                    	if ( response.rtnData.result.length == 1 ) {
                    		$this.cdGrpInfo.crud = 'U';
                    		$.each(response.rtnData.result[0], function(key, val) {
            					$this.cdGrpInfo[key] = val;
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
        	if ( WebUtil.isNull($this.cdGrpInfo.cdGrp) ) {
        		Swal.alert(['코드그룹을 입력하세요.', 'info']);
        		return false;
        	}else if ( WebUtil.isNull($this.cdGrpInfo.cdGrpNm) ) {
        		Swal.alert(['코드그룹명을 입력하세요.', 'info']);
        		return false;
        	}else if ( WebUtil.isNull($this.cdGrpInfo.useYn) ) {
        		Swal.alert(['사용여부를 선택하세요.', 'info']);
        		return false;
        	}else {
        			return true;
        		}
        	
        	
        
        },
        searchDupUserId: function() {
        	let $this = this;
        	
			if($this.isValid()){
			
        	let params =  {'cdGrp' : $this.cdGrpInfo.cdGrp
        							, 'cdGrpNm' : $this.cdGrpInfo.cdGrpNm}
      
			AjaxUtil.post({
                url: "/set/cdMng/searchDupCdGrp",
                param: params,
                success: function(response) {
                	if ( response.rtnData.existsYn === 'N' )
                	{
                		$this.saveCdGrp();
                	}
                	else 
                	{
                		$this.cdGrpInfo.cdGrp = '';
                		$this.cdGrpInfo.cdGrpNm = '';
                		
                		Swal.alert(['해당 코드그룹은 이미 사용중입니다.', 'info']);
                	}
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
			}
        },
        
		saveCdGrp: function() {
			
			let $this = this;
			
            if ( !this.isValid() ) {
                return false;
            }
			
			AjaxUtil.post({
                url: "/set/cdMng/saveCdGrp.ab",
                param: $this.cdGrpInfo,
                success: function(response) {
                	Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                		 closeModal($('#cdGrpDetlPopup'));
                		 cdMng.searchCdGrpList(true);
                	});                	
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
		},
		deleteCdGrp: function() {
			
			let $this = this;
			
			$this.cdGrpInfo.crud = 'D';	
			
			AjaxUtil.post({
                url: "/set/cdMng/saveCdGrp.ab",
                param: $this.cdGrpInfo,
                success: function(response) {
                	Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                		 closeModal($('#cdGrpDetlPopup'));
                		 cdMng.searchCdGrpList(true);
                	});                	
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
		},
		resetCdGrpInfo: function() {
			this.cdGrpInfo = {
				crud: 'C',
				cdGrp: '',
	    		cdGrpNm: '',
	    		useYn: ''
	    	}
		},
		cdGrp_typing : function(e){    	
			
            this.max_length(e, 40, '#cdGrp');
            this.max_length(e, 100, '#cdGrpNm');
        },
        max_length : function(e, len,id)
        {
            var val =  e.target.value;    			
            if (val.length > len){    				
            	Swal.alert(['최대 글자수를 초과하였습니다.' ]);
            	 $(id).val(val.substring(0, len));
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
