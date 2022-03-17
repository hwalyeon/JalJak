let userDetl = new Vue({
    el: "#userDetlPopup",
    data: {
    	userInfo: {
    		crud: 'C',
    		userId: '',
    		userNm: '',
    		userPw: '',
    		blngNm: '',
    		telNo: '',
    		mtelNo: '',
    		mailAddr: '',
    		entrDt: '',
    		relsDt: '',
    		useYn: '',
    		checkDupUserId: 'N'
    	},
		code: {
			useYnList: []
		}
	},
	
    methods: {

        initialize: function() {
        	
        	let $this = this;
        	
        	$this.initCodeList();      
        	        	
        },
        initCodeList: function() {
        	
        	getCommonCodeList('USE_YN', this.code.useYnList);
        	
        },
        initPage: function(userId) {
        	
        	let $this = this;
        	$this.resetUserInfo();

        	
        	if ( !WebUtil.isNull(userId) )
    		{
        		let params = {
        			'userId' : userId
        		}
        		
        		AjaxUtil.post({
                    url: "/set/userMng/searchUserInfo.ab",
                    param: params,
                    success: function(response) {
                    	if ( !!response.rtnData.result ) {
                    		$this.userInfo.crud = 'U';
                    		$.each(response.rtnData.result, function(key, val) {
            					$this.userInfo[key] = val;
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
        	
        	if ( WebUtil.isNull($this.userInfo.userId) ) {
        		Swal.alert(['사용자ID를 입력하세요.', 'info']);
        		return false;
        	}
        	
        	if ( WebUtil.isNull($this.userInfo.userNm) ) {
        		Swal.alert(['사용자명을 입력하세요.', 'info']);
        		return false;
        	}
        	
        	if ( WebUtil.isNull($this.userInfo.userPw) ) {
        		Swal.alert(['비밀번호를 입력하세요.', 'info']);
        		return false;
        	}
        	
        	if ( WebUtil.isNull($this.userInfo.blngNm) ) {
        		Swal.alert(['소속을 입력하세요.', 'info']);
        		return false;
        	}
        	
        	if ( WebUtil.isNull($this.userInfo.telNo) ) {
        		Swal.alert(['전화번호를 입력하세요.', 'info']);
        		return false;
        	}
        	
        	if ( WebUtil.isNull($this.userInfo.mtelNo) ) {
        		Swal.alert(['휴대전화번호를 입력하세요.', 'info']);
        		return false;
        	}
        	
        	if ( WebUtil.isNull($this.userInfo.mailAddr) ) {
        		Swal.alert(['이메일을 입력하세요.', 'info']);
        		return false;
        	}
        	
        	if ( WebUtil.isNull($this.userInfo.useYn) ) {
        		Swal.alert(['사용여부를 선택하세요.', 'info']);
        		return false;
        	}

        	// 학원/강사/학생 여부를 라디오로 변경하고 저장시에 값을 셋팅해주어야 함
        	
        	return true;
        },
        searchDupUserId: function() {
        	let $this = this;
			
        	if ( WebUtil.isNull($this.userInfo.userId) ) {
        		Swal.alert(['사용자ID를 입력하세요.', 'info']);
        		return false;
        	}
			
        	let params = {
        		userId : $this.userInfo.userId
        	}
        	
			AjaxUtil.post({
                url: "/set/userMng/searchDupUserId.ab",
                param: params,
                success: function(response) {
                	if ( response.rtnData.result.existsYn === 'N' ) {
                		$this.userInfo.checkDupUserId = 'Y';
                		Swal.alert(['해당 아이디는 사용할 수 있습니다.', 'success']);
                	} else {
                		$this.userInfo.userId = '';
                		Swal.alert(['해당 아이디는 이미 사용중입니다.', 'info']);
                	}
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
        },
		saveUser: function() {
			
			let $this = this;
			
            if ( !this.isValid() ) {
                return false;
            }
			
            if ( $this.userInfo.crud === 'C' ) {
	            if ( $this.userInfo.checkDupUserId != 'Y' ) {
	        		Swal.alert(['ID 중복체크를 하세요.', 'info']);
	        		return false;
	        	}
	            if ( isValidPhoneNumber($this.userInfo.telNo)==false) {
	            	Swal.alert(['전화번호를 형식에 맞춰 입력 하세요','info']);
	            	return false;
	            }	            	
	            if ( isValidEmail($this.userInfo.mailAddr)==false){
	            	Swal.alert(['이메일을 형식에 맞춰 입력 하세요','info']);
	            	return false;
	            }
            }
            

            
			AjaxUtil.post({
                url: "/set/userMng/saveUser.ab",
                param: $this.userInfo,
                success: function(response) {
                	Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                		closeModal($('#userDetlPopup'));
               		 	userMng.searchUserList(true);
                	});                	
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
		},
		deleteUser: function() {
			
			let $this = this;
			
			$this.userInfo.crud = 'D';
			
            AjaxUtil.post({
                url: "/set/userMng/saveUser.ab",
                param: $this.userInfo,
                success: function(response) {
                	Swal.alert(['삭제가 완료되었습니다.', 'success']).then(function() {
                		 closeModal($('#userDetlPopup'));
                		 userMng.searchUserList(true);
                	});                	
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
		},
		resetUserInfo: function() {
			this.userInfo = {
				crud: 'C',
				userId: '',
	    		userNm: '',
	    		userPw: '',
	    		blngNm: '',
	    		telNo: '',
	    		mtelNo: '',
	    		mailAddr: '',
	    		acdmYn: 'N',
	    		lctrYn: 'N',
	    		stdtYn: 'N',
	    		checkDupUserId: 'N'
	    	}
		},
		userIdChk : function(){
	      const reg = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;

	      if(reg.exec(this.userInfo.userId)!==null){
	    	  this.userInfo.userId = '';
	    	  Swal.alert(["아이디에 한글은 사용할 수 없습니다.", 'info']);
	      }
		}
    },
    computed: {

    },
    watch: {
    	'userInfo.userId': function(newVal, oldVal) {
    		this.userInfo.checkDupUserId = 'N';
    	}
    },
    mounted: function() {
        let self = this;
        $(document).ready(function() {
            self.initialize();
        });
    }
});
