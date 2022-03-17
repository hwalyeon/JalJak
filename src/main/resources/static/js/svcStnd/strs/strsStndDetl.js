let strsStndDetl = new Vue({
    el: "#strsStndDetlPopup",
    data: {
    	strsStndInfo: {
    		crud: 'C',
			mentStrsStatCd:'',
			mentStrsStatCd: '',
			fatJudgNm: '',
			bmiFr: '',
			bmiTo: '',
			fidxFr: '',
			fidxTo: '',
			currEvalCntn: '',
			prdtEvalCntn: ''
    	},
		summerNoteId1 : 'summerNoteId1',
		summerNoteId2 : 'summerNoteId2',
 		code: {
			mentmentStrsStatCdList: []
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
			getCommonCodeList('FAT_JUDG_CD',$this.code.mentmentStrsStatCdList);
        	
        },
        initPage: function(mentStrsStatCd) {
        	
        	let $this = this;
        	$this.resetFatJudgStndInfo();

        	
        	if ( !WebUtil.isNull(mentStrsStatCd) )
    		{
        		let params = {
        			'mentStrsStatCd' : mentStrsStatCd
        		}
        		
        		AjaxUtil.post({
                    url: "/svcStnd/strs/strsStndMng/searchStrsStndInfo.ab",
                    param: params,
                    success: function(response) {
                    	if ( !!response.rtnData.result ) {
                    		$this.fatJudgStndInfo.crud = 'U';
                    		$.each(response.rtnData.result, function(key, val) {
            					$this.fatJudgStndInfo[key] = val;
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
        	
        	if ( WebUtil.isNull($this.fatJudgStndInfo.mentStrsStatCd) ) {
        		Swal.alert(['비만판정코드를 입력하세요.', 'info']);
        		return false;
        	}
        	
        	if ( WebUtil.isNull($this.fatJudgStndInfo.bmiFr) ) {
        		Swal.alert(['BMI_FR을 입력하세요.', 'info']);
        		return false;
        	}

			if ( WebUtil.isNull($this.fatJudgStndInfo.bmiTo) ) {
				Swal.alert(['BMI_TO을 입력하세요.', 'info']);
				return false;
			}
        	
        	if ( WebUtil.isNull($this.fatJudgStndInfo.fidxFr) ) {
        		Swal.alert(['비만지수_FO을 입력하세요.', 'info']);
        		return false;
        	}

			if ( WebUtil.isNull($this.fatJudgStndInfo.fidxTo) ) {
				Swal.alert(['비만지수_TO을 입력하세요.', 'info']);
				return false;
			}
			if ( WebUtil.isNull($this.fatJudgStndInfo.currEvalCntn) ) {
				Swal.alert(['현재평가내용을 입력하세요.', 'info']);
				return false;
			}

			if ( WebUtil.isNull($this.fatJudgStndInfo.prdtEvalCntn) ) {
				Swal.alert(['예측평가내용을 입력하세요.', 'info']);
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
                url: "/svcStnd/fat/fatJudgStndMng/saveInfo.ab",
                param: $this.fatJudgStndInfo,
                success: function(response) {
                	Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                		closeModal($('#fatJudgStndDetlPopup'));
						fatJudgStndMng.searchFatJudgList(true);
                	});                	
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });

		},
		deleteInfo: function() {
			
			let $this = this;
			
			$this.fatJudgStndInfo.crud = 'D';
			
            AjaxUtil.post({
                url: "/svcStnd/fat/fatJudgStndMng/saveInfo.ab",
                param: $this.fatJudgStndInfo,
                success: function(response) {
                	Swal.alert(['삭제가 완료되었습니다.', 'success']).then(function() {
                		 closeModal($('#fatJudgStndDetlPopup'));
                		 fatJudgStndMng.searchFatJudgList(true);
                	});                	
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
		},
		resetFatJudgStndInfo: function() {
			this.fatJudgStndInfo = {
				crud: 'C',
				mentStrsStatCd: '',
				fatJudgNm: '',
				bmiFr: '',
				bmiTo: '',
				fidxFr: '',
				fidxTo: '',
				currEvalCntn: '',
				prdtEvalCntn: ''
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
