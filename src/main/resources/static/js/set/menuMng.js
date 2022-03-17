let menuMng = new Vue({
    el: "#menuMng",
    data: {
    	params: {
    		upprMenuNo: '',
    		menuNm: '',

    		paging: 'Y',
    		totalCount: 0,
            rowCount: 30,
            currentPage: 1,
            currentIndex: 0
    	},
    	code : {
    		upprMenuList: []
    	},
    	selectedNode : {}
	},
	
    methods: {

        initialize: function() {
        	
        	let $this = this;
        	
        	$this.initCodeList();
        	
        	$this.initGrid();
        	
        	$this.searchMenuList(true);
        	
        },
        initCodeList: function() {
        	let $this = this;
        	AjaxUtil.post({
                url: "/set/menuMng/searchUpprMenuList.ab",
                param: {},
                success: function(response) {
                	$this.code.upprMenuList = [];
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
        	
        },
        initGrid: function() {
        	
        	let $this = this;
        	
        	let colModels = [
                {name: "menuNo"      , index: "menuNo"      , label: "메뉴번호"   , width: 80, align: "center"},
                {name: "menuNm"      , index: "menuNm"      , label: "메뉴명"     , width: 100, align: "left"},
                {name: "upprMenuNo"  , index: "upprMenuNo"  , label: "상위메뉴번호" , width: 80, align: "center"},
                {name: "upprMenuNm"  , index: "upprMenuNm"  , label: "상위메뉴명1"  , width: 100, align: "left"},
                {name: "menuUrl"     , index: "menuUrl"     , label: "메뉴경로"   , width: 80, align: "left"},
                {name: "iconInfo"    , index: "iconInfo"    , label: "아이콘정보"  , width: 80, align: "center"},
                {name: "useYn"       , index: "useYn"       , label: "사용여부"   , width: 80, align: "center"},
                {name: "menuDesc"    , index: "menuDesc"    , label: "메뉴설명"   , width: 80, align: "left"},
                {name: "menuDetlPop" , index: "menuDetlPop" , label: "메뉴 정보보기", width: 80, align: "center",
                    formatter: function(cellValue, options, rowObject) {
                        return '<input type="button" class="btn btn-xs btn-outline btn-success" onclick="menuMng.regMenuPop(\'' + rowObject.menuNo + '\')" value="상세보기" data-toggle="modal" data-target="#menuDetlPopup" />';
                    }
                }
            ];
  
            $("#menu_list").jqGrid("GridUnload");
           	$("#menu_list").jqGrid($.extend(true, {}, commonGridOptions(), {
            	datatype: "local",
            	mtype: 'post',
            	height: 455,
                url: '/set/menuMng/searchMenuList.ab',
                pager: '#menu_pager_list',
                colModel: colModels,
                onPaging : function(data) {
                    onPagingCommon(data, this, function(resultMap) {                   	
                        $this.params.currentPage  = resultMap.currentPage;
                        $this.params.rowCount     = resultMap.rowCount;
                        $this.params.currentIndex = resultMap.currentIndex;
                        $this.searchMenuList(false);
                    })
                }
            }));

            resizeJqGridWidth("menu_list", "menu_list_wrapper");
        },
		searchMenuList: function(isSearch) {
			
			let $this = this;
            let params = $.extend(true, {}, $this.params);
			
            if ( isSearch ) {
                params.currentPage = 1;
                params.currentIndex = 0;
            }
            
			$("#menu_list").setGridParam({
                datatype: "json",
                postData: JSON.stringify(params),
                page: 1,
                loadComplete: function (response) {
                    console.log(response.rtnData.result);
                    if ( response.rtnData.result == 0 ) {
                        Swal.alert(['조회할 내용이 없습니다.', "info"]);
                    }
                }
            }).trigger("reloadGrid");
		},
		downloadExcel : function() {
			
			let $this = this;
			
			let params = $.extend(true, {}, $this.params);
			
			AjaxUtil.post({
				dataType: 'binary',
                url: "/set/menuMng/searchMenuList/excel.ab",
                param: params,
                success: function(response) {
                	saveFileLocal(response, 'MenuMng.xls');
                },
                error: function (response) {
                    Swal.alert([response, 'error']);
                }
            });
		},
		regMenuPop: function(menuNo) {
			menuDetl.initPage(menuNo);
			console.log(menuNo);			

		},
		resetSearchParam: function() {
			let $this = this;
			$this.params = {
				upprMenuNo: '',
		    	menuNm: '',
		    	paging: 'Y',
	    		totalCount: 0,
	            rowCount: 30,
	            currentPage: 1,
	            currentIndex: 0
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
