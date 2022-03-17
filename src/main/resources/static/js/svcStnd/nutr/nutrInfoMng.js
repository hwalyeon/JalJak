let nutrInfoMng = new Vue({
    el: "#nutrInfoMng",
    data:
        {
            params:
                {
                    userId            : ''  ,
                    nutrNm          : ''  ,    // 영양소_명
                    gfixDivCd      : ''  ,    // 성장비만지수_구분_코드
                    paging          : 'Y',
                    totalCount    : 0  ,
                    rowCount     : 30,
                    currentPage : 1  ,
                    currentIndex: 0
                },
            code:
                {
                    gfixDivCdList : []
                  , useYnList        : [{'cdVal':'Y', 'cdNm':'Y'},{'cdVal':'N', 'cdNm':'N'}]  //영양상태코드명 리스트
                },
        },
    methods:
        {
            initialize: function()
            {
                let $this = this;

                $this.initValue();
                $this.initCodeList();
            },
            initValue: function()
            {
                 let $this = this;
                 $this.userId = SessionUtil.getUserId();
            },
            initCodeList : function() {
                let $this = this;

                getCommonCodeList('GFIX_DIV_CD', $this.code.gfixDivCdList, function () {
                    console.log($this.code.gfixDivCdList);

                    $this.initGrid();
                    $this.searchNutrInfoList(true);
                });
            },
            initGrid: function()
            {
                let $this = this;
                let gfixDivCdList = commonGridCmonCd($this.code.gfixDivCdList);
                let useYnList     = commonGridCmonCd($this.code.useYnList);
                let colModels =
                [
                    {name: "crud"               , index: "crud"                  , label: "crud"                                   , hidden:true},
                    {name: "nutrCd"            , index: "nutrCd"              , label: "영양소코드"                       , editable :true	,  width: 80        , align: "center"},
                    {name: "nutrCdTemp"   , index: "nutrCdTemp"    , label: "영양소코드"                       , hidden: true                             },
                    {name: "nutrNm"           , index: "nutrNm"            , label: "영양소명"                           , editable :true  , width: 80        , align: "center"},
                    {name: "nutrUnitCd"     , index: "nutrUnitCd"        , label: "영양소 단위 코드"             , editable :true  , width: 80         , align: "center"},
                    {name: "gfixDivCd"	     , index: "gfixDivCd"	 , label: "성장비만지수 구분" , width: 80  	 ,align:"center"
                    ,edittype:"select"		 , formatter:"select"	 , editable :true		  , editoptions : {value:gfixDivCdList}},
                    {name: "sortOrd"           , index: "sortOrd"             , label: "정렬순서"                         , editable :true	, editrules : {number : true} , width: 80          , align: "center"},
                    {name: "useYn"                 , index: "useYn"            , label: "사용여부"                   , width: 80          , align: "center"       , editable: true
                        , edittype:"select"            , formatter:"select" ,editoptions:{value:useYnList}},
                    {name: "regDt"              , index: "regDt"                , label: "등록일자"                         , width: 80          , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);                                        }},
                    {name: "regTm"            , index: "regTm"               , label: "등록시각"                          , width: 80          , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);                                       }},
                    {name: "regUserId"       , index: "regUserId"         , label: "등록사용자ID"                  , width: 80          , align: "center"},
                    {name: "uptDt"             , index: "uptDt"                , label: "수정일자"                          , width: 80          , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);                                       }},
                    {name: "uptTm"            , index: "uptTm"               , label: "수정시각"                         , width: 80          , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);                                       }},
                    {name: "uptUserId"       , index: "uptUserId"         , label: "수정사용자ID"                  , width: 80          , align: "center"}
                    ,{name: "nutrStatStndDetlPopup" , index: "nutrStatStndDetlPopup" , label: "영양소상태기준", width: 80, align: "center",
                        formatter: function(cellValue, options, rowObject) {
                            return '<input type="button" class="btn btn-xs btn-outline btn-success" onclick="nutrInfoMng.regNutrStatStndPop(\'' + rowObject.nutrCd + '\')" value="상세보기" data-toggle="modal" data-target="#nutrStatStndDetlPopup" />';
                        }
                    }
                    ,{name: "nutrEatStndDetlPopup" , index: "nutrEatStndDetlPopup" , label: "영양소섭취기준", width: 80, align: "center",
                    formatter: function(cellValue, options, rowObject) {
                        return '<input type="button" class="btn btn-xs btn-outline btn-success" onclick="nutrInfoMng.regNutrEatStndPop(\'' + rowObject.nutrCd + '\' , \'' + rowObject.nutrNm + '\' )" value="상세보기" data-toggle="modal" data-target="#nutrEatStndDetlPopup" />';
                     }
                    }
                    ,{name: "nutrEatBlckDetlPopup" , index: "nutrEatBlckDetlPopup" , label: "영양소섭취구간", width: 80, align: "center"}
                ];

                $("#nutrInfo_list").jqGrid("GridUnload");
                $("#nutrInfo_list").jqGrid($.extend(true, {}, commonEditGridOptions(),
                {
                    datatype  : "local",
                    mtype      : 'post',
                    url            : '/svcStnd/nutr/nutrInfoMng/searchNutrInfoList.ab',
                    pager       : '#nutrInfo_pager_list',
                    height      : 405,
                    colModel : colModels,
                    onPaging : function(data) {
                        onPagingCommon(data, this, function(resultMap)
                        {
                            $this.params.currentPage  = resultMap.currentPage;
                            $this.params.rowCount     = resultMap.rowCount;
                            $this.params.currentIndex = resultMap.currentIndex;
                            $this.searchNutrInfoList(false);
                        })
                    },
                    afterSaveCell : function (rowid , colId , val, e ){
                        if($("#nutrInfo_list").getRowData(rowid).crud != "C" && $("#nutrInfo_list").getRowData(rowid).crud != "D" ) {
                            $("#nutrInfo_list").setRowData(rowid, {crud:"U"});
                        }
                    }
                }));
                resizeJqGridWidth("nutrInfo_list", "nutrInfo_list_wrapper");
            },
            searchNutrInfoList: function(isSearch)
            {
                let $this = this;
                let params = $.extend(true, {}, $this.params);

                if ( isSearch )
                {
                    params.currentPage = 1;
                    params.currentIndex = 0;
                }

                $("#nutrInfo_list").setGridParam(
                    {
                        datatype: "json",
                        postData: JSON.stringify(params),
                        page: 1,
                        loadComplete: function (response)

                        {
                            if ( response.rtnData.result == 0 )
                            {
                                Swal.alert(['조회할 내용이 없습니다.', "info"]);
                            }
                        }
                    }).trigger("reloadGrid");
            },
            /**/
        btnAddRow  :  function()
        {
            let $this = this;
            let date = new Date();
            var cnt = $("#nutrInfo_list").jqGrid("getGridParam", "records")+1;

            var addRow =
            {
                crud	    : "C" ,
                nutrCd      : "" ,
                nutrCdTemp  : "" ,
                nutrNm          : "" ,
                nutrUnitCd     : "" ,
                gfixDivCd      : "" ,
                sortOrd          : "" ,
                useYn             : "" ,
                regDt            : date  ,
                regTm           : date  ,
                regUserId     : $this.userId  ,
                uptDt            : date  ,
                uptTm           : date  ,
                uptUserId      : $this.userId
            };
            $("#nutrInfo_list").addRowData(cnt, addRow);

        },
        btnDelRow : function() {

            //var checkIds = $("#dgem_list").jqGrid("getGridParam","selarrrow") + ""; // 멀티
            let checkIds = $("#nutrInfo_list").jqGrid("getGridParam","selrow") + "";  // 단건
            if ( checkIds == "" )
            {
                alert("삭제할 행을 선택해주십시요.");
                return false;
            }

            let checkId = checkIds.split(",");
            for ( var i in checkId )
            {
                if ( $("#nutrInfo_list").getRowData(checkId[i]).crud == "C" )
                {
                    $("#nutrInfo_list").setRowData(checkId[i], {crud:"N"});
                    $("#"+checkId[i],"#nutrInfo_list").css({display:"none"});
                }
                else
                {
                    $("#nutrInfo_list").setRowData(checkId[i], {crud:"D"});
                    $("#"+checkId[i],"#nutrInfo_list").css({display:"none"});
                }
            }
        },
        btnSave  :  function() {
            let $this = this;
            let gridData = commonGridGetDataNew($("#nutrInfo_list"));

            if(gridData.length > 0){
                for (let data in gridData){
                    if(gridData[data].crud === 'C' || gridData[data].crud === 'U'){
                        if(WebUtil.isNull(gridData[data].nutrCd)){
                            Swal.alert(["영양소코드는 필수 입력입니다.", 'warning']);
                            return false;
                        }
                        if(WebUtil.isNull(gridData[data].sortOrd)){
                            Swal.alert(["정렬순서는 필수 입력입니다.", 'warning']);
                            return false;
                        }
                        if(WebUtil.isNull(gridData[data].useYn)){
                            Swal.alert(["사용여부는 필수 입력입니다.", 'warning']);
                            return false;
                        }
                    }
                }
            }else {
                Swal.alert(["저장 대상 데이터가 없습니다.", 'warning']);
                return false;
            }
            let param = { gridList : []}
            param.gridList = gridData;

            AjaxUtil.post({
                url: "/svcStnd/nutr/nutrInfoMng/saveNutrInfo.ab",
                param: param,
                success: function(response) {
                    Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                        $this.searchNutrInfoList(true);
                    });
                },
                error: function (response) {
                    Swal.alert([response, 'error']);
                }
            });
        },
        /**/
        downloadExcel : function()
        {
            let $this = this;
            let params = $.extend(true, {}, $this.params);

            AjaxUtil.post(
                {
                    dataType: 'binary',
                    url: "/svcStnd/nutr/nutrInfoMng/searchNutrInfoList/excel.ab",
                    param: params,
                    success: function(response)
                    {
                        saveFileLocal(response, 'nutrInfoMng.xls');
                    },
                    error: function (response)
                    {
                        Swal.alert([response, 'error']);
                    }
                });
        },
        regNutrStatStndPop : function (nutrCd){
            nutrStatStndDetl.initPage(nutrCd);
        },
        regNutrEatStndPop : function (nutrCd, nutrNm){
            nutrEatStndDetl.initPage(nutrCd, nutrNm);
        },
        resetSearchParam: function()
        {
            let $this = this;
            $this.params =
            {
                nutrNm          : ''  ,    // 영양소_명
                paging          : 'Y',
                totalCount    : 0  ,
                rowCount     : 30,
                currentPage : 1  ,
                currentIndex: 0
            }
        }
    },
    computed:
        {
        },
    watch:
        {
        },
    mounted: function()
    {
        let self = this;
        $(document).ready(function()
        {
            self.initialize();
        });
    }
});