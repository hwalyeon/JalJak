let userMng = new Vue({
    el: "#userMng",
    data: {
    	params: {
    		userId: '',
    		userNm: '',
    		blngNm: '',
    		telNo: '',
    		mtelNo: '',
    		mailAddr: '',
    		useYn:'Y',
    		paging: 'Y',
    		totalCount: 0,
            rowCount: 30,
            currentPage: 1,
            currentIndex: 0
    	}
	},
	
    methods: {

        initialize: function() {
        	
        	let $this = this;
        	
        	$this.initCodeList();
        	
        	$this.initGrid();
        	
        	$this.searchUserList(true);

        	
        },
        initCodeList: function() {
        	
        },
        initGrid: function() {
        	        	        	
        	let colModels = [
                {name: "userId"     , index: "userId"     , label: "사용자ID"    , width: 80, align: "center"},
                {name: "userNm"     , index: "userNm"     , label: "사용자명"     , width: 80, align: "center"},
                {name: "blngNm"     , index: "blngNm"     , label: "소속"        , width: 80, align: "center"},
                {name: "telNo"      , index: "telNo"      , label: "전화번호"     , width: 80, align: "center"
                	,formatter:function(cellValue, options, rowObject){
                		console.log(phoneFormatter(cellValue));
                		return phoneFormatter(cellValue);
                	}
                },
                {name: "mtelNo"     , index: "mtelNo"     , label: "휴대전화번호"  , width: 80, align: "center"
                	,formatter:function(cellValue, options, rowObject){
                		console.log(phoneFormatter(cellValue));
                		return phoneFormatter(cellValue);
                	}
                },
                {name: "mailAddr"   , index: "mailAddr"   , label: "이메일"      , width: 80, align: "center"},
                {name: "entrDt"     , index: "acdmYn"     , label: "가입일자"     , width: 80, align: "center"
                	,formatter: function(cellValue, options, rowObject) {
                		console.log(formatDate(cellValue));
                		return formatDate(cellValue);
                	}
                },
                {name: "relsDt"     , index: "lctrYn"     , label: "해지일자"     , width: 80, align: "center"
                	,formatter: function(cellValue, options, rowObject) {
                    return moment(cellValue, 'YYYYMMDD').format("YYYY-MM-DD");
                }},
                {name: "useYn"     , index: "stdtYn"     , label: "사용여부"     , width: 80, align: "center"},
                {name: "userDetlPop", index: "userDetlPop", label: "사용자 정보보기", width: 80, align: "center",
                    formatter: function(cellValue, options, rowObject) {
                        return '<input type="button" class="btn btn-xs btn-outline btn-success" onclick="userMng.regUserPop(\'' + rowObject.userId + '\')" value="상세보기" data-toggle="modal" data-target="#userDetlPopup" />';
                    }
                }
            ];
  
            $("#user_list").jqGrid("GridUnload");
           	$("#user_list").jqGrid($.extend(true, {}, commonGridOptions(), {
            	datatype: "local",
            	mtype: 'post',
                url: '/set/userMng/searchUserList.ab',
                pager: '#user_pager_list',
				height: 405,
                colModel: colModels,
                onPaging : function(data) {
                    onPagingCommon(data, this, function(resultMap) {
                        $this.params.currentPage  = resultMap.currentPage;
                        $this.params.rowCount     = resultMap.rowCount;
                        $this.params.currentIndex = resultMap.currentIndex;
                        $this.searchUserList(false);
                    })
                }
            }));

            resizeJqGridWidth("user_list", "user_list_wrapper");                        
        },
        searchUserList: function(isSearch) {
			
			let $this = this;
            let params = $.extend(true, {}, $this.params);
			
            if ( isSearch ) {
                params.currentPage = 1;
                params.currentIndex = 0;
            }
            
			$("#user_list").setGridParam({
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
                url: "/set/userMng/searchUserList/excel.ab",
                param: params,
                success: function(response) {
                	saveFileLocal(response, 'UserMng.xls');
                },
                error: function (response) {
                    Swal.alert([response, 'error']);
                }
            });
		},
		regUserPop: function(userId) {
			userDetl.initPage(userId);
		},
		resetSearchParam: function() {
			let $this = this;
			$this.params = {
				userId: '',
	    		userNm: '',
	    		blngNm: '',
	    		telNo: '',
	    		mtelNo: '',
	    		mailAddr: '',
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