let userRoleMng = new Vue({
    el: "#userRoleMng",
    data: {
    	user : {
			userId: '',
			paging: 'Y',
    		totalCount: 0,
            rowCount: 30,
            currentPage: 1,
            currentIndex: 0
		},
		userRole: {
			roleCd: '',
			paging: 'Y',
    		totalCount: 0,
            rowCount: 30,
            currentPage: 1,
            currentIndex: 0
		},
    	code : {
    		roleList: []    		
    	}
	},
	
    methods: {

        initialize: function() {
        	
        	let $this = this;
        	
        	$this.initCodeList();
        	
        	$this.initGrid();
        	
        	$this.searchUserList(true);
        	
        	$('#user_pager_list_left').hide();
        	
        },
        initCodeList: function() {
        	
        	let $this = this;
        	
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
        },
        initGrid: function() {
        	
			let $this = this;
			
			let userColModels = [
        		{name: "userId"      , index: "userId"      , label: "사용자ID" , width: 80, align: "center"},
        		{name: "userNm"      , index: "userNm"      , label: "사용자명"  , width: 80, align: "center"},
                {name: "blngNm"      , index: "blngNm"      , label: "소속"     , width: 80, align: "left"}
            ];
  
            $("#user_list").jqGrid("GridUnload");
           	$("#user_list").jqGrid($.extend(true, {}, commonGridOptions(), {
            	datatype: "local",
            	mtype: 'post',
                url: '/set/userMng/searchUserList.ab',
                height: 615,
				multiselect: true,
                pager: '#user_pager_list',
                colModel: userColModels,
                onPaging : function(data) {
                    onPagingCommon(data, this, function(resultMap) {
                        $this.user.currentPage  = resultMap.currentPage;
                        $this.user.rowCount     = resultMap.rowCount;
                        $this.user.currentIndex = resultMap.currentIndex;
                        $this.searchUserList(false);
                    })
                }
            }));

            resizeJqGridWidth("user_list", "user_list_wrapper");

        	let colModels = [
        		{name: "roleCd"      , index: "roleCd"      , label: "역할코드"    , width: 80, align: "center", hidden: true},
        		{name: "roleNm"      , index: "roleNm"      , label: "역할명"     , width: 80, align: "center", hidden: true},
                {name: "userId"      , index: "userId"      , label: "사용자ID"   , width: 80, align: "center"},
                {name: "userNm"      , index: "userNm"      , label: "사용자명"    , width: 80, align: "center"},
				{name: "blngNm"      , index: "blngNm"      , label: "소속"       , width: 80, align: "left"},
            ];
  
            $("#userRole_list").jqGrid("GridUnload");
           	$("#userRole_list").jqGrid($.extend(true, {}, commonGridOptions(), {
            	datatype: "local",
            	mtype: 'post',
            	multiselect: true,
            	height: 535,
                url: '/set/userRoleMng/searchUserRoleList.ab',
                pager: '#userRole_pager_list',
                colModel: colModels,
                onPaging : function(data) {
                    onPagingCommon(data, this, function(resultMap) {
                        $this.userRole.currentPage  = resultMap.currentPage;
                        $this.userRole.rowCount     = resultMap.rowCount;
                        $this.userRole.currentIndex = resultMap.currentIndex;
                        $this.searchUserRoleList(false);
                    })
                }
            }));

            resizeJqGridWidth("userRole_list", "userRole_list_wrapper");
        },
        searchUserList: function(isSearch) {
        	
        	let $this = this;
            let params = $.extend(true, {}, $this.user);
			
            if ( isSearch ) {
                params.currentPage = 1;
                params.currentIndex = 0;
            }
        	
        	$("#user_list").setGridParam({
        		datatype: 'json',
        		postData: JSON.stringify(params),
        		page: 1,
        		loadComplete: function (response) {
                    if ( response.rtnData.result.length == 0 ) {
                        Swal.alert(['조회할 내용이 없습니다.', "info"]);
                    }
                }
        	}).trigger('reloadGrid');
        },
		searchUserRoleList: function(isSearch) {
			
			let $this = this;
            let params = $.extend(true, {}, $this.userRole);
			
            if ( isSearch ) {
                params.currentPage = 1;
                params.currentIndex = 0;
            }

			$("#userRole_list").setGridParam({
                datatype: "json",
                postData: JSON.stringify(params),
                page: 1,
                loadComplete: function (response) {
                    if ( response.rtnData.result.length == 0 ) {
                        Swal.alert(['조회할 내용이 없습니다.', "info"]);
                    }
                }
            }).trigger("reloadGrid");
		},
		isValid: function() {
			
/*			let userRoleList = $('#userRole_list').getRowData();
			
			if ( userRoleList.length == 0 ) {
				Swal.alert(['저장할 내역이 없습니다.', 'info']);
				return false;
			}*/
			
			return true;
		},
		saveUserRole: function() {
			
			let $this = this;
			
            if ( !$this.isValid() ) {
                return false;
            }
			
            let params = {
            	roleCd : $this.userRole.roleCd,
            	userRoleList : $('#userRole_list').getRowData()
            }
            
			AjaxUtil.post({
                url: "/set/userRoleMng/saveUserRole.ab",
                param: params,
                success: function(response) {
                	Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                		 $this.searchUserRoleList(true);
                	});                	
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
		},
		changeRoleCd: function() {
			
			let $this = this;
			$this.searchUserRoleList(true);
			
		},
		resetSearchParam: function() {
			let $this = this;
			$this.user = {
				userId: '',
				paging: 'Y',
	    		totalCount: 0,
	            rowCount: 30,
	            currentPage: 1,
	            currentIndex: 0
			};
			$this.userRole = {
				roleCd: '',
				paging: 'Y',
	    		totalCount: 0,
	            rowCount: 30,
	            currentPage: 1,
	            currentIndex: 0
			}
		},
		addUser: function() {
			
			let $this = this;
			
			if ( WebUtil.isNull($this.userRole.roleCd) ) {
				Swal.alert(['역할을 먼저 선택하시기 바랍니다.', 'info']);
				return false;
			} else {
				
				let userRoleList = $('#userRole_list').jqGrid('getRowData');
				let checkUserList = $('#user_list').jqGrid('getGridParam', 'selarrrow');
				if ( !checkUserList || checkUserList.length == 0 ) {
	                Swal.alert(['사용자를 선택하시기 바랍니다.', 'info']);
	                return false;
	            }
				
				$.each(checkUserList, function(index, rowIdx) {
					
					let user = $('#user_list').jqGrid('getRowData', rowIdx);
					
					let userRole = {
						'userId' : user.userId,
						'userNm' : user.userNm,
						'blngNm' : user.blngNm,
						'roleCd' : $this.userRole.roleCd,
						'roleNm' : WebUtil.getCodeNm($this.code.role, $this.userRole.roleCd)
					}
					
	                if ( userRoleList.findIndex(row => row.userId === user.userId && row.roleCd === $this.userRole.roleCd ) == -1 ) {
	                	$('#userRole_list').jqGrid("addRowData"  , $('#userRole_list').getGridParam("reccount") + 1, userRole, 'last'); // 마지막 행에 Row 추가
	                	$('#userRole_list').jqGrid('setSelection', $('#userRole_list').getGridParam("reccount") + 1);
	                }
				});
			}
		},
		removeUser: function() {
            let userRoleList = $('#userRole_list');
            let selectedRow = userRoleList.jqGrid('getGridParam', 'selarrrow'); // 선택된 ROW

            if ( !selectedRow || selectedRow.length == 0 ) {
                Swal.alert(['선택된 사용자가 없습니다.', 'warning']);
                return false;
            }

            for ( let i = selectedRow.length - 1 ; i >= 0 ; i-- )
        	{
            	userRoleList.jqGrid("delRowData", selectedRow[i]);
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
