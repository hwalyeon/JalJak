let menuDetl = new Vue({
    el: "#menuDetlPopup",
    data: {
    	menuInfo: {
    		crud: 'C',
    		menuNo: '',
    		upprMenuNo: '',
			subUpprMenuNo : '',
    		menuUrl: '',
    		menuNm: '',
    		menuDesc: '',
    		iconInfo: '',
    		useYn: '',
    		roleList: []
    	},
    	
    	code : {
    		roleList: [],
    		upprMenuList: [],
			subUpprMenuList: [],
    		useYnList: []
    	}
	},
	
    methods: {

        initialize: function() {
        	
        	let $this = this;
        	$this.initCodeList();

        	
        },
        initCodeList: function() {
        	
        	let $this = this;
        	AjaxUtil.post({
                url: "/set/menuMng/searchUpprMenuList.ab",
				param: {"upprMenuNo" : "0"},
                success: function(response) {
                	
                	$this.code.upprMenuList = [];
                	$this.code.useYnList        = [];
                	if ( !!response.rtnData.result && response.rtnData.result.length > 0 ) {
                        $.each(response.rtnData.result, function(index, item) {
        					$this.code.upprMenuList.push({'cdVal':item.menuNo, 'cdNm':item.menuNm, 'url':item.menuUrl});
        				});
                    }
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
        	
        	AjaxUtil.post({
                url: "/set/roleMng/searchRoleList.ab",
                param: {},
                success: function(response) {
                	
                	$this.code.roleList = [];
                	if ( !!response.rtnData.result && response.rtnData.result.length > 0 ) {
                        $.each(response.rtnData.result, function(index, item) {
        					$this.code.roleList.push({'cdVal':item.roleCd, 'cdNm':item.roleNm});
        				});
                    }
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
        	
        	getCommonCodeList('USE_YN', this.code.useYnList);
        	
        },
        initPage: function(menuNo) {
        	
        	let $this = this;
        	$this.resetMenuInfo();


        	if ( !WebUtil.isNull(menuNo) )
    		{
        		let params = {
        			'menuNo' : menuNo
        			
        		}
        		
        		AjaxUtil.post({
                    url: "/set/menuMng/searchMenuInfo.ab",
                    param: params,
                    success: function(response) {
                    	if ( !!response.rtnData.result ) {
                    		$this.menuInfo.crud = 'U';
                    		$.each(response.rtnData.result, function(key, val) {
            					$this.menuInfo[key] = val;
            				});

							if($this.menuInfo.upprMenuNo.length != 1 ){
								$this.selectSubUpprMenuNo($this.menuInfo.mainUpprMenuNo);
								let subUpprMenuNo = $this.menuInfo.upprMenuNo;
								$this.menuInfo.upprMenuNo =$this.menuInfo.mainUpprMenuNo;
								$this.menuInfo.subUpprMenuNo = subUpprMenuNo;
							}
                    	}
                    	
                    	if ( !!response.rtnData.roleList && response.rtnData.roleList.length > 0 ) {
                    		$this.menuInfo.roleList = response.rtnData.roleList;
                    	}
  						$this.menuInfo.roleList.push($this.addRoleCd());
                    },
                    error: function (response) {
                    	Swal.alert([response, 'error']);
                    }
                });
    		}
        	else
    		{
        		$this.menuInfo.roleList = [];
        		$this.menuInfo.roleList.push($this.addRoleCd());
    		}
        },
        isValid: function() {
        	
        	let $this = this;
        	
        	if(''==''){
        		console.log($this.menuInfo.menuNo);
        	}
        	
        	if ( WebUtil.isNull($this.menuInfo.menuNo) ) {
        		Swal.alert(['메뉴번호를 입력하세요.', 'info']);
        		return false;
        	}
        	
        	if ( WebUtil.isNull($this.menuInfo.menuNm) ) {
        		Swal.alert(['메뉴명을 입력하세요.', 'info']);
        		return false;
        	}
        	        	
        	if ( WebUtil.isNull($this.menuInfo.upprMenuNo) ) {
        		Swal.alert(['상위메뉴를 입력하세요.', 'info']);
        		return false;
        	}
        	/*
        	if ( WebUtil.isNull($this.menuInfo.menuUrl) ) {
        		Swal.alert(['메뉴경로를 입력하세요.', 'info']);
        		return false;
        	}

        	if ( WebUtil.isNull($this.menuInfo.iconInfo) ) {
        		Swal.alert(['메뉴아이콘을 입력하세요.', 'info']);
        		return false;
        	}
        	*/
        	if ( WebUtil.isNull($this.menuInfo.useYn) ) {
        		Swal.alert(['사용여부를 선택하세요.', 'info']);
        		return false;
        	}
        	
        	if ( WebUtil.isNull($this.menuInfo.menuDesc) ) {
        		Swal.alert(['메뉴설명을 입력하세요.', 'info']);
        		return false;
        	}
        	
        	
        	
        	return true;
        },
		saveMenu: function() {
			
			let $this = this;
			console.log("^^^^^^^^^^^^^^ " + $this.menuInfo.roleList.roleCd);
            if ( !this.isValid() ) {
                return false;
            }

			AjaxUtil.post({
                url: "/set/menuMng/saveMenu.ab",
                param: $this.menuInfo,
                success: function(response) {
                	Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                		 closeModal($('#menuDetlPopup'));
                		 menuMng.searchMenuList(true);
                	});                	
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
		},
		deleteMenu: function() {
			
			let $this = this;
			
			$this.menuInfo.crud = 'D';
			
            AjaxUtil.post({
                url: "/set/menuMng/saveMenu.ab",
                param: $this.menuInfo,
                success: function(response) {
                	Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                		 closeModal($('#menuDetlPopup'));
                		 menuMng.searchMenuList(true);
                	});                	
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
		},
		changeUpprMenuNo: function() {
			let $this = this;
    		let menu = _.filter($this.code.upprMenuList, function(item) {
    			return item.cdVal === $this.menuInfo.upprMenuNo;
    		})[0];
    		
    		this.menuInfo.menuNo  = menu.cdVal;
    		this.menuInfo.menuUrl = menu.url + "/";

    		this.selectSubUpprMenuNo(this.menuInfo.menuNo);

		},

		selectSubUpprMenuNo : function (menuNo){
        	let $this = this;

			AjaxUtil.post({
				url: "/set/menuMng/searchUpprMenuList.ab",
				param: {"upprMenuNo" : menuNo},
				success: function(response) {

					$this.code.subUpprMenuList = [];
					$this.code.useYnList        = [];
					if ( !!response.rtnData.result && response.rtnData.result.length > 0 ) {
						$.each(response.rtnData.result, function(index, item) {
							$this.code.subUpprMenuList.push({'cdVal':item.menuNo, 'cdNm':item.menuNm, 'url':item.menuUrl});
						});
					}
				},
				error: function (response) {
					Swal.alert([response, 'error']);
				}
			});

		},

		changeSubUpprMenuNo: function() {
			let $this = this;
			let menu = _.filter($this.code.subUpprMenuList, function(item) {
				return item.cdVal === $this.menuInfo.subUpprMenuNo;
			})[0];

			this.menuInfo.menuNo  = menu.cdVal;
			this.menuInfo.menuUrl = menu.url + "/";
		},

		resetMenuInfo: function() {
			this.menuInfo = {
				crud: 'C',
	    		menuNo: '',
	    		upprMenuNo: '',
				subUpprMenuNo: '',
	    		menuUrl: '',
	    		menuNm: '',
	    		menuDesc: '',
	    		iconInfo: '',
	    		useYn: 'Y',
	    		roleList: []
	    	}
		},
		setRoleCd: function(index) {
			console.log('setRoleCd : ' + index);
			let $this = this;
			if ( !!$this.menuInfo && !!$this.menuInfo.roleList[$this.menuInfo.roleList.length - 1].roleCd ) {
				
				let roleCd = $this.menuInfo.roleList[index].roleCd;
				let roleList = _.filter($this.menuInfo.roleList, function(item) {
					return item.roleCd === roleCd;
				});
				
				if ( roleList.length > 1 ) {
					$this.menuInfo.roleList[index].roleCd = '';
				} else {
					$this.menuInfo.roleList.push($this.addRoleCd());
				}
			}
		},
		delRoleCd: function(index) {
			console.log('delRoleCd : ' + index);
			let $this = this;
			if ( $this.menuInfo.roleList.length == 1 ) {
				$this.menuInfo.roleList[index].roleCd = '';
			} else {
				$this.menuInfo.roleList.splice(index, 1);
			}
		},
		addRoleCd: function() {
			let $this = this;
			return {
				'menuNo': '',
				'roleCd': ''
    		}
		},
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
