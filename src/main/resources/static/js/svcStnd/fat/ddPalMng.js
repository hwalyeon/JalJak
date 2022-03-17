let ddPalMng = new Vue({
    el: "#ddPalMng",
    data: {
    	params: {
            currFatJudgCd:'',
            prdtFatJudgCd:'',
            sexCd:'',
            ageYcnt:'',
            palValFr:'',
            palValTo:'',
            calQtyFr:'',
            calQtyTo:'',
            ddCalQty:'',
            palCd:'',
            nutrCd:'',
            nutrStatCd:'',
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
            mentDdPalCdList : [],
            sexCdList       : [],
            nutrStatCdList  : [],
            palCdList       : [],
            nutrCdList      : []
        },
	},

	
    methods: {

        initialize: function() {
        	
        	let $this = this;

        	$this.initValue();
        	$this.initCodeList();

        },
        initValue: function()
        {
            let $this = this;
            $this.userId = SessionUtil.getUserId();
        },
        initCodeList: function() {
            let $this = this;

            getCommonCodeList('NUTR_STAT_CD',$this.code.nutrStatCdList, function()
            {
                $this.initGrid();
                $this.searchDdPalList(true);
            }
            );

            getCommonCodeList('FAT_JUDG_CD',$this.code.mentDdPalCdList, function()
            {
                $this.initGrid();
                $this.searchDdPalList(true);
            }
            );

            getCommonCodeList('SEX_CD',$this.code.sexCdList, function()
            {
                $this.initGrid();
                $this.searchDdPalList(true);
            }
            );


        },
        initGrid: function() {
            let $this = this;
            let mentDdPalCdList = commonGridCmonCd($this.code.mentDdPalCdList);
            let sexCdList       = commonGridCmonCd($this.code.sexCdList);
            let nutrStatCdList  = commonGridCmonCd($this.code.nutrStatCdList);
        	let colModels =
            [
                {name:"crud"               , index: "crud"            , label:"crud"               , hidden:true},
                {name: "currFatJudgCdTemp" , index: "currFatJudgCdTemp", label: "현재비만판정"            , width: 80   , align: "center", hidden  : true  },
                {name: "currFatJudgCd"     , index: "currFatJudgCd"   , label: "현재비만판정"     , width: 80   , align: "center"
                    , editable: true  ,edittype:"select"	, formatter:"select" , editoptions : {value:mentDdPalCdList}},
                {name: "prdtFatJudgCdTemp" , index: "prdtFatJudgCdTemp", label: "예측비만판정"            , width: 80   , align: "center", hidden  : true  },
                {name: "prdtFatJudgCd"     , index: "prdtFatJudgCd"   , label: "예측비만판정"     , width: 80   , align: "center" , editable: true
                    , editable: true  ,edittype:"select"	, formatter:"select" , editoptions : {value:mentDdPalCdList}},
                {name: "sexCdTemp"         , index: "sexCdTemp"       , label: "성별"            , width: 80   , align: "center", hidden  : true  },
                {name: "sexCd"             , index: "sexCd"           , label: "성별"            , width: 80   , align: "center" , editable: true
                    , editable: true  ,edittype:"select"	, formatter:"select", editable :true, editoptions : {value:sexCdList}},
                {name: "ageYcntTemp"       , index: "ageYcntTemp"     , label: "나이년수"         , width: 80   , align: "center", hidden  : true  },
                {name: "ageYcnt"           , index: "ageYcnt"         , label: "나이년수"            , width: 80   , align: "center"
                    , editable: true , editrules:{number:true}},
                {name: "calQtyFr"          , index: "calQtyFr"        , label: "칼로리량 FORM"       , width: 80   , align: "center"
                    , editable: true , editrules:{number:true}},
                {name: "calQtyTo"          , index: "calQtyTo"        , label: "칼로리량 TO"         , width: 80   , align: "center"
                    , editable: true , editrules:{number:true}},
                {name: "ddCalQty"          , index: "ddCalQty"        , label: "일일칼로리량"         , width: 80   , align: "center"
                    , editable: true , editrules:{number:true}},
                {name: "palCd"             , index: "palCd"           , label: "신체활동수준코드"      , width: 80   , align: "center"
                    , editable: true , editrules:{number:true}},
                {name: "nutrCd"            , index: "nutrCd"          , label: "영양소코드"           , width: 80   , align: "center"
                    , editable: true , editrules:{number:true}},
                {name: "nutrStatCdTemp"    , index: "nutrStatCdTemp"  , label: "영양섭취상태"           , width: 80   , align: "center"
                    , hidden  : true},
                {name: "nutrStatCd"        , index: "nutrStatCd"      , label: "영양섭취상태"      , width: 80   , align: "center"
                    , editable: true , edittype:"select"	, formatter:"select", editable :true, editoptions : {value:nutrStatCdList}},
                {name: "regDt"             , index: "regDt"           , label: "등록일자"        , width: 80          , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);                                              }},
                {name: "regTm"               , index: "regTm"         , label: "등록시각"       , width: 80          , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);                                              }},
                {name: "regUserId"          , index: "regUserId"      , label: "등록사용자ID"      , width: 80          , align: "center"},
                {name: "uptDt"                , index: "uptDt"        , label: "수정일자"     , width: 80          , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);                                              }},
                {name: "uptTm"               , index: "uptTm"         , label: "수정시각"       , width: 80          , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);                                              }},
                {name: "uptUserId"          , index: "uptUserId"      , label: "수정사용자ID"      , width: 80          , align: "center"}
            ];
        	console.log("1");
  
            $("#user_list").jqGrid("GridUnload");
           	$("#user_list").jqGrid($.extend(true, {}, commonEditGridOptions(), {
            	datatype: "local",
            	mtype: 'post',
                url: '/svcStnd/fat/ddPalMng/searchDdPalList.ab',
                pager: '#user_pager_list',
				height: 405,
                colModel: colModels,
                onPaging : function(data) {
                    onPagingCommon(data, this, function(resultMap) {
                        $this.params.currentPage  = resultMap.currentPage;
                        $this.params.rowCount     = resultMap.rowCount;
                        $this.params.currentIndex = resultMap.currentIndex;
                        $this.searchDdPalList(false);
                    })
                },
                afterSaveCell : function (rowid, colId, val, e)
                {
                    if($("#user_list").getRowData(rowid).crud != "C" && $("#user_list").getRowData(rowid).crud != "D" ) {
                        $("#user_list").setRowData(rowid, {crud: "U"}
                        );
                    }
                }
            }));
            resizeJqGridWidth("user_list", "user_list_wrapper");                        
        },
        searchDdPalList: function(isSearch) {
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
        mentDdPalNmVal:function(){
            let $this = this;
        },
        physStrsStatNmVal:function(){
            let $this = this;
        },

        btnAddRow  :  function() {
            let $this = this;
            let date  = new Date();
            var cnt = $("#user_list").jqGrid("getGridParam", "records")+1;

            var addRow = {crud:"C"
                ,currFatJudgCdTemp:""
                ,prdtFatJudgCdTemp:""
                ,sexCdTemp:""
                ,ageYcnt:""
                ,palValFr:""
                ,palValTo:""
                ,calQtyFr:""
                ,calQtyTo:""
                ,ddCalQty:""
                ,palCd:""
                ,nutrCd:""
                ,nutrStatCd:""
                ,regDt:date
                ,regTm:date
                ,regUserId:$this.userId
                ,uptDt:date
                ,uptTm:date
                ,uptUserId:$this.userId
            };
            $("#user_list").addRowData(cnt, addRow);

        },
        btnDelRow : function() {
            //var checkIds = $("#dgem_list").jqGrid("getGridParam","selarrrow") + ""; // 멀티
            let checkIds = $("#user_list").jqGrid("getGridParam","selrow") + "";  // 단건
            if ( checkIds == "" )
            {
                alert("삭제할 행을 선택해주십시요.");
                return false;
            }

            let checkId = checkIds.split(",");
            for ( var i in checkId )
            {
                if ( $("#user_list").getRowData(checkId[i]).crud == "C" )
                {
                    $("#user_list").setRowData(checkId[i], {crud:"N"});
                    $("#"+checkId[i],"#user_list").css({display:"none"});
                }
                else
                {
                    $("#user_list").setRowData(checkId[i], {crud:"D"});
                    $("#"+checkId[i],"#user_list").css({display:"none"});
                }
            }
        },
        btnSave  :  function() {
            let $this = this;
            let gridData = commonGridGetDataNew($("#user_list"));

            if(gridData.length > 0)
            {
                for (let data in gridData)
                {
                    if(gridData[data].crud === 'C' || gridData[data].crud === 'U')
                    {
                        if(WebUtil.isNull(gridData[data].currFatJudgCd)){
                            Swal.alert(["현재비만판정코드 필수 입력입니다.", 'warning']);
                            return false;
                        }if(WebUtil.isNull(gridData[data].prdtFatJudgCd)){
                        Swal.alert(["예측비만판정코드 필수 입력입니다.", 'warning']);
                        return false;
                    }if(WebUtil.isNull(gridData[data].sexCd)){
                        Swal.alert(["성별 코드 필수 입력입니다.", 'warning']);
                        return false;
                    }if(WebUtil.isNull(gridData[data].ageYcnt)){
                        Swal.alert(["나이년수 필수 입력입니다.", 'warning']);
                        return false;
                    }if(WebUtil.isNull(gridData[data].calQtyFr)){
                        Swal.alert(["칼로리량 FORM 필수 입력입니다.", 'warning']);
                        return false;
                    }if(WebUtil.isNull(gridData[data].calQtyTo)){
                        Swal.alert(["칼로리량 TO 필수 입력입니다.", 'warning']);
                        return false;
                    }if(WebUtil.isNull(gridData[data].ddCalQty)){
                        Swal.alert(["일일 칼로리량 필수 입력입니다.", 'warning']);
                        return false;
                    }if(WebUtil.isNull(gridData[data].palCd)){
                        Swal.alert(["신체활동수준 코드 필수 입력입니다.", 'warning']);
                        return false;
                    }if(WebUtil.isNull(gridData[data].nutrCd)){
                        Swal.alert(["영양소 코드 필수 입력입니다.", 'warning']);
                        return false;
                    }if(WebUtil.isNull(gridData[data].nutrStatCd)){
                        Swal.alert(["영양섭취 상태 코드 필수 입력입니다.", 'warning']);
                        return false;
                    }
                    }
                }
            }
            else
            {
                Swal.alert(["저장 대상 데이터가 없습니다.", 'warning']);
                return false;
            }
            let param = { gridList : []}
            param.gridList = gridData;

            AjaxUtil.post({
                url: "/svcStnd/fat/ddPalMng/saveDdPal.ab",
                param: param,
                success: function(response) {
                    Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                        $this.searchDdPalList(true);
                    });
                },
                error: function (response) {
                    Swal.alert([response, 'error']);
                }
            });
        },


        downloadExcel : function()
        {
            let $this = this;
            let params = $.extend(true, {}, $this.params);

            AjaxUtil.post({
                dataType: 'binary',
                url: "/svcStnd/fat/ddPalMng/searchDdPalList/excel.ab",
                param: params,
                success: function(response)
                {
                    saveFileLocal(response, 'ddPalMng.xls');
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
                currFatJudgCd:'',
                prdtFatJudgCd:'',
                sexCd:'',
                ageYcnt:'',
                palValFr:'',
                palValTo:'',
                calQtyFr:'',
                calQtyTo:'',
                ddCalQty:'',
                palCd:'',
                nutrCd:'',
                nutrStatCd:'',
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