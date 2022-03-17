let sposInfoDetl = new Vue({
    el: "#sposInfoDetlPopup",
    data: {
    	params: {
    		crud              : 'C',
    		userId            : '' ,
    		sposNo            : '' , //배우자_번호
    		sposNm            : '' , //배우자_명
			sposTelNo         : '' , //배우_전화_번호
    		sexCd             : '' , //성별_코드
			hghtVal           : '' , //키_값
			wghtVal		      : '' , //체중_값
			raceDivCd         : '' , //인종_구분_코드
			paging            : 'Y',
			totalCoun         : 0  ,
			rowCount          : 30 ,
			currentPage       : 1  ,
			currentIndex      : 0
    	},
		code: {
			sexCdList      : [] , //성별_코드_리스트
			raceDivCdList  : [] , //인종_구분_코드_리스트
		}
	},
    methods: {

        initialize: function()
		{
        	let $this = this;

        	$this.initValue();
        	$this.initCodeList();
        },
        initCodeList: function()
		{
        	let $this = this;
			getCommonCodeList('SEX_CD', $this.code.sexCdList);            //성별_코드_리스트
			getCommonCodeList('RACE_DIV_CD', $this.code.raceDivCdList);   //인종_구분_코드_리스트
		},
		initValue()
		{
			let $this = this;
			$this.userId = SessionUtil.getUserId();
		},
        initPage: function(sposNo) {

			let $this = this;
			$this.resetSposDetlInfo();

			if ( !WebUtil.isNull(sposNo) )
			{
				let params = {
					'sposNo' : sposNo
				}
				AjaxUtil.post({
					url: "/user/prnt/prntInfoMng/searchSposInfo.ab",
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
		isValid: function()
		{
        	let $this = this;
        	return true;
        },
		//숫자타입 체크
		nanCk : function(event)
		{
			let $this = this;

			let name  = event.target.name;
			let cdNm;
			if(isNaN($this.params[name])){

				if(name=='sposTelNo'){
					cdNm='보호자 전화번호';
				}else if(name=='hghtVal'){
					cdNm='키';
				}else if(name=='wghtVal'){
					cdNm='몸무게';
				}

				this.params[name] = this.params[name].replace(/\D/g,'');
				Swal.alert([cdNm+"는 숫자만 입력 가능합니다.", 'info']);
			}

		},
		saveSposInfoDetl: function() {
			
			let $this = this;
			
            if ( !this.isValid() ) {
                return false;
            }

			AjaxUtil.post({
                url: "/user/prnt/prntInfoMng/saveSposInfoDetl.ab",
                param: $this.params,
                success: function(response) {
                	Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                		closeModal($('#sposInfoDetlPopup'));
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
                url: "/user/prnt/prntInfoMng/saveSposInfoDetl.ab",
                param: $this.params,
                success: function(response) {
                	Swal.alert(['삭제가 완료되었습니다.', 'success']).then(function() {
                		 closeModal($('#sposInfoDetlPopup'));
						prntInfoMng.searchPrntInfoList(true);
                	});
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
		},
		resetSposDetlInfo: function() {
			this.params = {
				crud              : 'C',
				userId            : '' ,
				sposNo            : '' , //배우자_번호
				sposNm            : '' , //배우자_명
				sposTelNo         : '' , //배우_전화_번호
				sexCd             : '' , //성별_코드
				hghtVal           : '' , //키_값
				wghtVal		      : '' , //체중_값
				raceDivCd         : '' , //인종_구분_코드
				paging            : 'Y',
				totalCoun         : 0  ,
				rowCount          : 30 ,
				currentPage       : 1  ,
				currentIndex      : 0
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
