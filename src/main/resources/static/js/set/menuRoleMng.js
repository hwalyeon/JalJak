let menuRoleMng = new Vue({
    el: "#menuRoleMng",
    data: {
    	params: {
    		menuNo: '',
    		roleCd: '',
    		paging: 'Y',
    		totalCount: 0,
            rowCount: 30,
            currentPage: 1,
            currentIndex: 0
    	},
    	code : {
    		menuList: [],
    		roleList: []    		
    	},
    	selectedNode : {}
	},
	
    methods: {

        initialize: function() {
        	
        
        	let $this = this;
        	
        	$this.initCodeList();
        	
        	$this.initGrid();
        	
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
        	
        	AjaxUtil.post({
                url: "/set/menuMng/searchMenuList.ab",
                param: {},
                success: function(response) {
                	$this.code.menuList = [];
                	if ( !!response.rtnData.result && response.rtnData.result.length > 0 ) {
                		$this.code.menuList = response.rtnData.result;
                		$this.initMenuTree();
                    }
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
        	
        },
        initGrid: function() {
        	
        	let $this = this;
        	let colModels = [
        		{name: "roleCd"      , index: "roleCd"      , label: "역할코드"    , width: 80, align: "center", hidden: true},
        		{name: "roleNm"      , index: "roleNm"      , label: "역할명"     , width: 80, align: "center", hidden: true},
                {name: "menuNo"      , index: "menuNo"      , label: "메뉴번호"    , width: 80, align: "center"},
                {name: "menuNm"      , index: "menuNm"      , label: "메뉴명"     , width: 80, align: "left"},
                {name: "menuDesc"      , index: "menuDesc"      , label: "메뉴설명"     , width: 80, align: "center"},
                {name: "upprMenuNo"  , index: "upprMenuNo"  , label: "상위메뉴번호" , width: 80, align: "center", hidden: true},
                {name: "upprMenuNm"  , index: "upprMenuNm"  , label: "상위메뉴명"  , width: 80, align: "left"}
            ];
  
            $("#menuRole_list").jqGrid("GridUnload");
           	$("#menuRole_list").jqGrid($.extend(true, {}, commonGridOptions(), {
            	datatype: "local",
            	mtype: 'post',
            	multiselect: true,
            	height: 535,
                url: '/set/menuRoleMng/searchMenuRoleList.ab',
                pager: '#menuRole_pager_list',
                colModel: colModels,
                onPaging : function(data) {
                    onPagingCommon(data, this, function(resultMap) {
                        $this.params.currentPage  = resultMap.currentPage;
                        $this.params.rowCount     = resultMap.rowCount;
                        $this.params.currentIndex = resultMap.currentIndex;
                        $this.searchMenuRoleList(false);
                    })
                }
            }));

            resizeJqGridWidth("menuRole_list", "menuRole_list_wrapper");
        },
        initMenuTree: function() {
        	
        	let $this = this;
        	if ($('#menu_tree_list').jstree(true)) {
                $('#menu_tree_list').jstree(true).deselect_all();
                $('#menu_tree_list').jstree(true).close_all();
                $('#menu_tree_list').jstree(true).refresh();
                return;
            }

        	let convertMenuList = $this.filteredMenuList(1, "0");
        	
            $('#menu_tree_list').jstree({
                "plugins" : [ "types", "checkbox", "wholerow"],
                'core' : {
                	'data': convertMenuList
                }
            }).on('select_node.jstree', function(e, data) {	//Tree에서 Node 클릭시 호출되는 함수
            	$this.params.menuNo = data.selected[0];
            });
        },
        filteredMenuList: function(level, upprMenuNo) {
        	let $this = this;
        	let convertMenuList = [];
        	
        	$.each($this.code.menuList, function(index, item) {
        		if ( item.upprMenuNo === upprMenuNo ) {
        			let menu = {
            			'id':item.menuNo,
            			'text':item.menuNm,
            			'icon':level == 1 ? 'fa fa-folder' : 'fa fa-file-o'
            		};
        			
        			let childMenuList = $this.filteredMenuList(level + 1, item.menuNo);
        			if ( !!childMenuList && childMenuList.length > 0 ) {
        				menu['children'] = childMenuList;
        			}
        			
        			convertMenuList.push(menu);
        		}
			});
        	
        	return convertMenuList;
        },
		searchMenuRoleList: function(isSearch) {
			
			let $this = this;
            let params = $.extend(true, {}, $this.params);
			params.menuNo = '';
			
            if ( isSearch ) {
                params.currentPage = 1;
                params.currentIndex = 0;
            }
            
			$("#menuRole_list").setGridParam({
                datatype: "json",
                postData: JSON.stringify(params),
                page: 1,
                loadComplete: function (response) {
                    if ( response.rtnData.result == 0 ) {
                        Swal.alert(['조회할 내용이 없습니다.', "info"]);
                    }
                }
            }).trigger("reloadGrid");
		},
		isValid: function() {
			
/*			let menuRoleList = $('#menuRole_list').getRowData();
			
			if ( menuRoleList.length == 0 ) {
				Swal.alert(['저장할 내역이 없습니다.', 'info']);
				return false;
			}*/
			
			return true;
		},
		saveMenuRole: function() {
			
			let $this = this;
			
            if ( !this.isValid() ) {
                return false;
            }
			
            let params = {
            	roleCd : $this.params.roleCd,
            	menuRoleList : $('#menuRole_list').getRowData()
            }
            
			AjaxUtil.post({
                url: "/set/menuRoleMng/saveMenuRole.ab",
                param: params,
                success: function(response) {
                	Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                		 menuRoleMng.searchMenuRoleList(true);
                	});                	
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
		},
		changeRoleCd: function() {
			
			let $this = this;
			$this.searchMenuRoleList(true);
			
		},
		resetSearchParam: function() {
			let $this = this;
			$this.params = {
				roleCd: '',
	    		totalCount: 0,
	            rowCount: 30,
	            currentPage: 1,
	            currentIndex: 0
	    	}
		},
		addMenu: function() {
			let $this = this;
			if ( WebUtil.isNull($this.params.roleCd) ) {
				Swal.alert(['역할을 먼저 선택하시기 바랍니다.', 'info']);
				return false;
			}
			else if ( WebUtil.isNull($this.params.menuNo) ) {
				Swal.alert(['메뉴를 선택하시기 바랍니다.', 'info']);
				return false;
			} else {
				
				
				let checkMenuList = $('#menu_tree_list').jstree('get_checked');
				$.each(checkMenuList, function(index, menuNo) {
					
					let menuInfo = _.filter($this.code.menuList, function(item) {
						return item.menuNo === menuNo;
					})[0];
					
					$this.addUpperMenu(menuInfo);
					
					let menuRole = {
						'menuNo' : menuInfo.menuNo,
						'menuNm' : menuInfo.menuNm,
						'menuDesc' : menuInfo.menuDesc,
						'upprMenuNo' : menuInfo.upprMenuNo,
						'upprMenuNm' : menuInfo.upprMenuNm,
						'roleCd' : $this.params.roleCd
					}
					
					let menuRoleList = $('#menuRole_list').jqGrid('getRowData');
	                if ( menuRoleList.findIndex(row => row.menuNo === menuNo ) == -1 ) {
	                	$('#menuRole_list').jqGrid("addRowData"  , $('#menuRole_list').getGridParam("reccount") + 1, menuRole, 'last'); // 마지막 행에 Row 추가
	                	$('#menuRole_list').jqGrid('setSelection', $('#menuRole_list').getGridParam("reccount") + 1);
	                }
				});
				
				$('#menu_tree_list').jstree(true).deselect_all();
			}
		},
		addUpperMenu: function(menuInfo) {
			
			let $this = this;
			let arrUpprMenu = null;
			
			if ( !WebUtil.isNull(menuInfo.upprMenuNo) )
			{
				arrUpprMenu = _.filter($this.code.menuList, function(item) {
					return item.menuNo === menuInfo.upprMenuNo;
				});
			}
			
			while ( !!arrUpprMenu && arrUpprMenu.length > 0 )
			{
				let upprMenuInfo = arrUpprMenu.shift();
				
				$this.addUpperMenu(upprMenuInfo);				
				
				let menuRole = {
					'menuNo' : upprMenuInfo.menuNo,
					'menuNm' : upprMenuInfo.menuNm,
					'menuDesc' : upprMenuInfo.menuDesc,
					'upprMenuNo' : upprMenuInfo.upprMenuNo,
					'upprMenuNm' : upprMenuInfo.upprMenuNm,
					'roleCd' : $this.params.roleCd
				}
					
				let menuRoleList = $('#menuRole_list').jqGrid('getRowData');
	            if ( menuRoleList.findIndex(row => row.menuNo === upprMenuInfo.menuNo ) == -1 ) {
	            	$('#menuRole_list').jqGrid("addRowData"  , $('#menuRole_list').getGridParam("reccount") + 1, menuRole, 'last'); // 마지막 행에 Row 추가
	            	$('#menuRole_list').jqGrid('setSelection', $('#menuRole_list').getGridParam("reccount") + 1);
	            }
			}
			
		},
		removeMenu: function() {
            let menuRoleList = $('#menuRole_list');
            let selectedRow = menuRoleList.jqGrid('getGridParam', 'selarrrow'); // 선택된 ROW
            /*console.log(selectedRow);*/
            if ( !selectedRow || selectedRow.length == 0 ) {
                Swal.alert(['선택된 메뉴가 없습니다.', 'warning']);
                return false;
            }

//            $.each(selectedRow, function(index, item) {
//            	console.log(item);
//            	menuRoleList.jqGrid("delRowData", item);
//            });
            
            for ( let i = selectedRow.length - 1 ; i >= 0 ; i-- )
        	{
            	console.log(selectedRow[i]);
            	menuRoleList.jqGrid("delRowData", selectedRow[i]);
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
