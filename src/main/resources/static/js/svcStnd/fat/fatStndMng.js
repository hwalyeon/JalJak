let fatStndMng = new Vue({
    el: "#fatStndMng",
    data:
        {
            params:
                {
                    userId            : ''  ,
                    fatStndVer     : ''  ,    // 비만_기준_번호
                    ageYcnt         : ''  ,     // 나이_년수
                    sexCd            : ''   ,    // 성별_코드
                    paging          : 'Y',
                    totalCount    : 0  ,
                    rowCount     : 30,
                    currentPage : 1  ,
                    currentIndex: 0
                },
            code:
                {
                    sexCdList             : []
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
            initCodeList : function()
            {
                let $this = this;
                getCommonCodeList('SEX_CD',$this.code.sexCdList, function()
                {
                    $this.initGrid();
                    $this.searchFatStndList(true);
                })
            },
            initGrid: function()
            {  
                let $this = this;
                let sexCdList = commonGridCmonCd($this.code.sexCdList);
                let colModels =
                [
                    {name:"crud"                    , index: "crud"                   , label:"crud"                        , hidden:true                                                  },
                    {name: "fatStndVer"        , index: "fatStndVer"         , label: "비만기준버전"          , width: 80         , align: "center"  , editable: true},
                    {name: "fatStndNo"        , index: "fatStndNo"          , label: "비만기준번호"          , width: 80         , align: "center" , editable: true , editrules:{number:true}},
                    {name: "fatStndNoTemp"   , index: "fatStndNoTemp"      , label: "비만기준번호",     hidden:true},
                    {name: "sexCd"       , index: "sexCd"         , label: "성별"               , width: 80         , align: "center"
                        ,edittype :"select"  , formatter:"select"    , editable:true              , editoptions:{value:sexCdList}},
                    {name: "ageYcnt"            , index: "ageYcnt"             , label: "나이(년)"                    , width: 80         , align: "center", editable: true , editrules:{number:true}},
                    {name: "ageMcnt"           , index: "ageMcnt"           , label: "나이(개월)"                , width: 80          , align: "center", editable: true , editrules:{number:true}},
                    {name: "p5Fidx"               , index: "p5Fidx"              , label: "백분위5 비만지수"     , width: 80         , align: "center", editable: true , editrules:{number:true}},
                    {name: "p50Fidx"             , index: "p50Fidx"            , label: "백분위50 비만지수"   , width: 80         , align: "center", editable: true , editrules:{number:true}},
                    {name: "p95Fidx"             , index: "p95Fidx"            , label: "백분위95 비만지수"   , width: 80         , align: "center", editable: true , editrules:{number:true}},
                    {name: "regDt"                , index: "regDt"                , label: "등록일자"                  , width: 80   , align: "center"  , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);  }},
                    {name: "regTm"               , index: "regTm"               , label: "등록시각"                 , width: 80   , align: "center"  , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);  }},
                    {name: "regUserId"          , index: "regUserId"         , label: "등록사용자ID"          , width: 80   , align: "center"},
                    {name: "uptDt"                , index: "uptDt"                , label: "수정일자"                 , width: 80   , align: "center"  , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);   }},
                    {name: "uptTm"               , index: "uptTm"               , label: "수정시각"                 , width: 80   , align: "center"  , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);   }},
                    {name: "uptUserId"          , index: "uptUserId"         , label: "수정사용자ID"          , width: 80   , align: "center"}
                ];

                $("#fatStnd_list").jqGrid("GridUnload");
                $("#fatStnd_list").jqGrid($.extend(true, {}, commonEditGridOptions(),
                {
                    datatype  : "local",
                    mtype      : 'post',
                    url            : '/svcStnd/fat/fatStndMng/searchFatStndList.ab',
                    pager       : '#fatStnd_pager_list',
                    height      : 405,
                    colModel : colModels,
                    onPaging : function(data) {
                        onPagingCommon(data, this, function(resultMap)
                        {
                            $this.params.currentPage  = resultMap.currentPage;
                            $this.params.rowCount     = resultMap.rowCount;
                            $this.params.currentIndex = resultMap.currentIndex;
                            $this.searchFatStndList(false);
                        })
                    },
                    afterSaveCell : function (rowid , colId , val, e ){
                        if($("#fatStnd_list").getRowData(rowid).crud != "C" && $("#fatStnd_list").getRowData(rowid).crud != "D" ) {
                            $("#fatStnd_list").setRowData(rowid, {crud:"U"});
                        }
                    }
                }));
                resizeJqGridWidth("fatStnd_list", "fatStnd_list_wrapper");
            },
            searchFatStndList: function(isSearch)
            {
                let $this = this;
                let params = $.extend(true, {}, $this.params);

                if ( isSearch )
                {
                    params.currentPage = 1;
                    params.currentIndex = 0;
                }

                $("#fatStnd_list").setGridParam(
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
            },  /**/
            btnAddRow  :  function()
            {
                let $this = this;
                let date = new Date();
                var cnt = $("#fatStnd_list").jqGrid("getGridParam", "records")+1;

                var addRow =
                {
                    crud:"C",
                    fatStndVer            : "",
                    fatStndNo            : "",
                    fatStndNoTemp  : "",
                    sexCd	                 : "",
                    ageYcnt               : "",
                    ageMcnt              : "",
                    p5Fidx                 : "",
                    p50Fidx               : "",
                    p95Fidx               : "",
                    regDt                  : date  ,
                    regTm                 : date  ,
                    regUserId            : $this.userId  ,
                    uptDt                  : date  ,
                    uptTm                 : date  ,
                    uptUserId            : $this.userId

                };
                $("#fatStnd_list").addRowData(cnt, addRow);

            },
            btnDelRow : function() {
                //var checkIds = $("#dgem_list").jqGrid("getGridParam","selarrrow") + ""; // 멀티
                let checkIds = $("#fatStnd_list").jqGrid("getGridParam","selrow") + "";  // 단건
                if ( checkIds == "" )
                {
                    alert("삭제할 행을 선택해주십시요.");
                    return false;
                }

                let checkId = checkIds.split(",");
                for ( var i in checkId )
                {
                    if ( $("#fatStnd_list").getRowData(checkId[i]).crud == "C" )
                    {
                        $("#fatStnd_list").setRowData(checkId[i], {crud:"N"});
                        $("#"+checkId[i],"#fatStnd_list").css({display:"none"});
                    }
                    else
                    {
                        $("#fatStnd_list").setRowData(checkId[i], {crud:"D"});
                        $("#"+checkId[i],"#fatStnd_list").css({display:"none"});
                    }
                }
            },
            btnSave  :  function() {
                let $this = this;
                let gridData = commonGridGetDataNew($("#fatStnd_list"));

                if(gridData.length > 0)
                {
                    for (let data in gridData)
                    {
                        if(gridData[data].crud === 'C' || gridData[data].crud === 'U')
                        {
                            if(WebUtil.isNull(gridData[data].fatStndVer)){
                                Swal.alert(["성장기준버전 필수 입력입니다.", 'warning']);
                                return false;
                            }if(WebUtil.isNull(gridData[data].fatStndNo)){
                            Swal.alert(["성장기준번호 필수 입력입니다.", 'warning']);
                            return false;
                        }if(WebUtil.isNull(gridData[data].ageYcnt)){
                            Swal.alert(["나이(년수) 필수 입력입니다.", 'warning']);
                            return false;
                        }if(WebUtil.isNull(gridData[data].ageMcnt)){
                            Swal.alert(["나이(개월수) 필수 입력입니다.", 'warning']);
                            return false;
                        }if(WebUtil.isNull(gridData[data].p5Fidx)){
                            Swal.alert(["백분위5 비만지수 필수 입력입니다.", 'warning']);
                            return false;
                        }if(WebUtil.isNull(gridData[data].p50Fidx)){
                            Swal.alert(["백분위50 비만지수 필수 입력입니다.", 'warning']);
                            return false;
                        }if(WebUtil.isNull(gridData[data].p95Fidx)){
                            Swal.alert(["백분위95 비만지수 필수 입력입니다.", 'warning']);
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
                    url: "/svcStnd/fat/fatStndMng/saveFatStnd.ab",
                    param: param,
                    success: function(response) {
                        Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                            $this.searchFatStndList(true);
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
                        url: "/svcStnd/fat/fatStndMng/searchFatStndList/excel.ab",
                        param: params,
                        success: function(response)
                        {
                            saveFileLocal(response, 'fatStndMng.xls');
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
                    fatStndVer : ''  ,    // 성장_기준_번호
                    ageYcnt         : ''  ,     // 나이_년수
                    sexCd            : ''   ,    // 성별_코드
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