let strsStndMng = new Vue({
    el: "#strsStndMng",
    data: {
    	params: {
            mentStrsStatCd:'',
            physStrsStatCd:'',
            strsJudgCntn:'',
            regDt:'',
            regTm:'',
            regUserId:'',
            uptDt:'',
            uptTm:'',
            uptUserId:'',
    		useYn:'Y',
    		paging: 'Y',
    		totalCount: 0,
            rowCount: 30,
            currentPage: 1,
            currentIndex: 0
    	},
        code:{
            strsStatList : []
        },
	},

	
    methods: {

        initialize: function() {
        	
        	let $this = this;
        	
        	$this.initCodeList();

        	$this.initValue();
        },
        initValue: function()
        {
            let $this = this;
            $this.userId = SessionUtil.getUserId();
        },

        initCodeList: function() {
            let $this = this;
            getCommonCodeList('STRS_STAT_CD',$this.code.strsStatList, function(){
                $this.initGrid();
                $this.searchStrsList(true);
                $this.searchCdSpecList(true);
            }
            );
        },
        initGrid: function() {
            let $this = this;
            let strsStatList = commonGridCmonCd($this.code.strsStatList);             
        	let colModels = [
                {name: "crud"               , index: "crud"             , label: "crud"                  , hidden:true},
                //   {name: "mentStrsStatCd"     , index: "mentStrsStatCd"   , label: "정신적스트레스상태코드"    , editable :true , editrules : {number : true}, width: 80, align: "center"},
                {name: "mentStrsStatCd"	     , index: "mentStrsStatCd"	 , label: "정신적스트레스상태명" , width: 80  	 ,align:"center"
                    ,edittype:"select"	, formatter:"select", editable :true, editoptions : {value:strsStatList}},
                {name: "mentStrsStatCdTemp"      , index: "mentStrsStatCdTemp"   ,label: "정신적스트레스상태명"  , hidden:true },
                {name: "physStrsStatCd"	     , index: "physStrsStatCd"	 , label: "신체적스트레스상태명" , width: 80  	 ,align:"center"
                    ,edittype:"select"	, formatter:"select", editable :true, editoptions : {value:strsStatList}},
                {name: "physStrsStatTemp"      , index: "physStrsStatTemp"   ,label: "신체적스트레스상태명"  , hidden:true },
                {name: "strsJudgCntn"       , index: "strsJudgCntn"     , label: "스트레스판정내용"         , editable :true , editrules : {text : true}, width: 80, align: "center"},
                {name: "cdSpecDetlPop", index: "cdSpecDetlPop", label: "코드 정보보기", width: 70, align: "center",
                    formatter: function(cellValue, options, rowObject) {
                        return '<input type="button" class="btn btn-xs btn-outline btn-success" onclick="strsStndMng.regStrsStndPop(\'' + rowObject.mentStrsStatCd + '\',\'' + rowObject.cdVal + '\')" value="상세보기" data-toggle="modal"/>';
                    }
                }

            ];
  
            $("#strsStnd_list").jqGrid("GridUnload");
           	$("#strsStnd_list").jqGrid($.extend(true, {}, commonEditGridOptions(), {
            	datatype: "local",
            	mtype: 'post',
                height: 450,
                url: '/svcStnd/strs/strsStndMng/searchStrsList.ab',
                pager: '#strsStnd_pager_list',
                colModel: colModels,
                onPaging : function(data) {
                    onPagingCommon(data, this, function(resultMap) {
                        $this.params.currentPage  = resultMap.currentPage;
                        $this.params.rowCount     = resultMap.rowCount;
                        $this.params.currentIndex = resultMap.currentIndex;
                        $this.searchStrsList(false);
                    })
                },
            }));

            resizeJqGridWidth("strsStnd_list", "strsStnd_list_wrapper");

            let strsCdGrpModels = [
                {name: "cdVal"        , index: "cdVal"        , label: "코드값"      , width: 90 , align: "center"},
                {name: "cdNm"         , index: "cdNm"         , label: "코드명"      , width: 90 , align: "left"  },
                {name: "cdDesc"       , index: "cdDesc"       , label: "코드내용"    , width: 100, align: "left"  },
                {name: "sortOrd"      , index: "sortOrd"      , label: "정렬순서"    , width: 50 , align: "center"},
                {name: "useYn"        , index: "useYn"        , label: "사용여부"    , width: 50 , align: "center"},
                {name: "cdSpecDetlPop", index: "cdSpecDetlPop", label: "코드 정보보기", width: 70 , align: "center",
                    formatter: function(cellValue, options, rowObject) {
                        return '<input type="button" class="btn btn-xs btn-outline btn-success" onclick="strsStndMng.regStrsStndPop(\'' + rowObject.cdGrp + '\',\'' + rowObject.cdVal + '\')" value="상세보기" data-toggle="modal"/>';
                    }
                }
            ];

            $("#strsCdGrp_list").jqGrid("GridUnload");
            $("#strsCdGrp_list").jqGrid($.extend(true, {}, commonGridOptions(), {
                mtype: 'post',
                height: 450,
                datatype: "local",
                url: '/svcStnd/strs/strsStndMng/searchCdSpecList.ab',
                pager: "#strsCdGrp_pager_list",
                colModel: strsCdGrpModels,
                onPaging : function(data) {
                    onPagingCommon(data, this, function(resultMap) {
                        $this.params.cdSpec.currentPage  = resultMap.currentPage;
                        $this.params.cdSpec.rowCount     = resultMap.rowCount;
                        $this.params.cdSpec.currentIndex = resultMap.currentIndex;
                        $this.searchCdSpecList(false);
                    })
                },
                loadComplete: function(datas) {
                    var grid = this;
                    $('td[rowspan="1"]', grid).each(function () {
                        var spans = $('td[rowspanid="' + this.id + '"]', grid).length + 1;
                        if (spans > 1) {
                            $(this).attr('rowspan', spans);
                        }
                    });
                }
            }));

            resizeJqGridWidth("strsCdGrp_list", "strsCdGrp_list_wrapper");
        },
        searchStrsList: function(isSearch) {
			let $this = this;
            let params = $.extend(true, {}, $this.params);
            if ( isSearch ) {
                params.currentPage = 1;
                params.currentIndex = 0;
            }
            
			$("#strsStnd_list").setGridParam({
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
        searchCdSpecList : function(isSearch) {

            let $this = this;

            let params = $.extend(true, {}, this.params.cdSpec);

            if ( isSearch ) {
                params.currentPage  = 1;
                params.currentIndex = 0;
            }

            $("#strsCdGrp_list").setGridParam({
                datatype: "json",
                postData: JSON.stringify(params),
                page: 1,
                loadComplete: function(response) {
                    if ( response.rtnData.result == 0 ) {
                        Swal.alert(["데이터가 없습니다.", "info"]);
                    }
                }
            }).trigger("reloadGrid");
        },

        mentStrsStatNmVal:function(){
            let $this = this;
        },
        physStrsStatNmVal:function(){
            let $this = this;
        },
        regStrsStndPop: function(mentStrsStatCd){
            strsStndDetl.initPage(mentStrsStatCd);
        },

        downloadExcel : function()
        {
            let $this = this;
            let params = $.extend(true, {}, $this.params);

            AjaxUtil.post({
                dataType: 'binary',
                url: "/svcStnd/strs/strsStndMng/searchStrsList/excel.ab",
                param: params,
                success: function(response)
                {
                    saveFileLocal(response, 'strsStndMng.xls');
                },
                error: function (response)
                {
                    Swal.alert([response, 'error']);
                }
            });
        },

		resetSearchParam: function() {
			let $this = this;
			$this.params = {
                mentStrsStatCd:'',
                physStrsStatCd:'',
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