let actStndMng = new Vue({
    el: "#actStndMng",
    data:
        {
            params:
                {
                    userId           : ''   ,
                    actNm          : ''  ,  // 활동_명
                    paging         : 'Y',
                    totalCount   : 0  ,
                    rowCount     : 30,
                    currentPage : 1  ,
                    currentIndex: 0
                },
            code:
                {
                    actClssCdList       : [],  // 활동_분류_코드 리스트
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
            initValue: function(){

                let $this = this;
                $this.userId = SessionUtil.getUserId();
            },
            initCodeList : function() {

                /*let $this = this;
                $this.codeList( $this.code.actClssCdList, function() {
                    $this.initGrid();
                    $this.searchActStndList(true);
                });*/
            },
            // 활동_분류_코드 리스트_조회
            codeList : function (arrayObject , callback){
                AjaxUtil.post({
                    url: "/svcStnd/grow/actStndMng/actClssCdList.ab",
                    param: {},
                    success: function(response) {
                        var dataList = response.rtnData.result;

                        if (Array.isArray(arrayObject)) {
                            var dataCnt = dataList.length;
                            var m = 0;

                            for (m = 0; m < dataCnt; m++) {
                                arrayObject.push({
                                    cdVal   : dataList[m].actClssCd,
                                    cdNm    : dataList[m].actClssCd,
                                    sortOrd : 0
                                });
                            }
                        }

                        if (typeof callback === "function") {
                            callback(dataList);
                        }
                    },
                    error: function (response) {
                        alert(response);
                    }
                });
            },
            initGrid: function()
            {  
                let $this = this;
                let selObj = "";
                let actClssCdList = commonGridCmonCd($this.code.actClssCdList);
                let colModels =
                [
                    {name: "crud"              , index: "crud"               , label: "crud"                          , hidden: true  },
                    {name: "actCdTemp"  , index: "actCdTemp"   , label: "활동코드"                   , width: 80  , align: "center"  , hidden: true  },
                    {name: "sortOrd"        , index: "sortOrd"          , label: "정렬순서"                   , width: 80  , align: "center"  , editable: true  },
                    {name: "actCd"           , index: "actCd"             , label: "활동코드"                   , width: 80  , align: "center"  , editable: true  },
                    {name: "actNm"          , index: "actNm"           , label: "활동명"                       , width: 80  , align: "center"  , editable: true  },
                    {name: "actClssCd"     , index: "actClssCd"       , label: "활동분류코드"           , width: 80  , align: "center"
                      ,edittype : "select"    , formatter: "select"      , editable:true                         , editoptions: {value: actClssCdList}  },
                    {name: "actDesc"        , index: "actDesc"         , label: "활동설명"                  , width: 80  , align: "center"  , editable: true  },
                    {name: "metVal"         , index: "metVal"          , label: "MET 값"                      , width: 80  , align: "center"  , editable: true  },
                    {name: "metMinCfct"  , index: "metMinCfct"  , label: "MET 분당 환산계수"  , width: 80  , align: "center"  , editable: true  },
                    {name: "regDt"            , index: "regDt"            , label: "등록일자"                  , width: 80  , align: "center"  , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);}  },
                    {name: "regTm"           , index: "regTm"          , label: "등록시각"                   , width: 80  , align: "center"  , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);}  },
                    {name: "regUserId"     , index: "regUserId"      , label: "등록사용자ID"          , width: 80   , align: "center"},
                    {name: "uptDt"            , index: "uptDt"            , label: "수정일자"                  , width: 80  , align: "center"  , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);}  },
                    {name: "uptTm"          , index: "uptTm"            , label: "수정시각"                  , width: 80  , align: "center"  , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);}  },
                    {name: "uptUserId"     , index: "uptUserId"      , label: "수정사용자ID"          , width: 80  , align: "center"  }
                ];

                $("#actStnd_list").jqGrid("GridUnload");
                $("#actStnd_list").jqGrid($.extend(true, {}, commonEditGridOptions(),
                {
                    datatype  : "local",
                    mtype      : 'post',
                    url            : '/svcStnd/grow/actStndMng/actStndList.ab',
                    pager       : '#actStnd_pager_list',
                    height      : 405,
                    colModel : colModels,
                    onPaging : function(data) {
                        onPagingCommon(data, this, function(resultMap)
                        {
                            $this.params.currentPage  = resultMap.currentPage;
                            $this.params.rowCount     = resultMap.rowCount;
                            $this.params.currentIndex = resultMap.currentIndex;
                            $this.searchActStndList(false);
                        })
                    },
                    afterSaveCell : function (rowid , colId , val, e ){
                        if($("#actStnd_list").getRowData(rowid).crud != "C" && $("#actStnd_list").getRowData(rowid).crud != "D" ) {
                            $("#actStnd_list").setRowData(rowid, {crud:"U"});
                        }
                    }
                }));
                resizeJqGridWidth("actStnd_list", "actStnd_list_wrapper");
            },
            searchActStndList: function(isSearch)
            {
                let $this = this;
                let params = $.extend(true, {}, $this.params);

                if ( isSearch )
                {
                    params.currentPage = 1;
                    params.currentIndex = 0;
                }

                $("#actStnd_list").setGridParam(
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
                let  $this = this;
                let date = new Date();
                var cnt = $("#actStnd_list").jqGrid("getGridParam", "records")+1;

                var addRow =
                {
                    crud             : "C",
                    sortOrd        : "" ,
                    actCd           : "" ,
                    actCdTemp  : "" ,
                    actNm          : "" ,
                    actClssCd     : "" ,
                    actDesc        : "" ,
                    metVal         : "" ,
                    metMinCfct  : "" ,
                    regDt            : date  ,
                    regTm           : date  ,
                    regUserId     : $this.userId  ,
                    uptDt            : date  ,
                    uptTm           : date  ,
                    uptUserId      : $this.userId

                };
                $("#actStnd_list").addRowData(cnt, addRow);

            },
            btnDelRow : function() {
                //var checkIds = $("#dgem_list").jqGrid("getGridParam","selarrrow") + ""; // 멀티
                let checkIds = $("#actStnd_list").jqGrid("getGridParam","selrow") + "";  // 단건
                if ( checkIds == "" )
                {
                    alert("삭제할 행을 선택해주십시요.");
                    return false;
                }

                let checkId = checkIds.split(",");
                for ( var i in checkId )
                {
                    if ( $("#actStnd_list").getRowData(checkId[i]).crud == "C" )
                    {
                        $("#actStnd_list").setRowData(checkId[i], {crud:"N"});
                        $("#"+checkId[i],"#actStnd_list").css({display:"none"});
                    }
                    else
                    {
                        $("#actStnd_list").setRowData(checkId[i], {crud:"D"});
                        $("#"+checkId[i],"#actStnd_list").css({display:"none"});
                    }
                }
            },
            btnSave  :  function() {
                let $this = this;
                let gridData = commonGridGetDataNew($("#actStnd_list"));

                if(gridData.length > 0)
                {
                    for (let data in gridData)
                    {
                        if(gridData[data].crud === 'C' || gridData[data].crud === 'U')
                        {
                            if(WebUtil.isNull(gridData[data].actCd)){
                                Swal.alert(["활동코드는 필수 입력입니다.", 'warning']);
                                return false;
                            }if(WebUtil.isNull(gridData[data].metVal)){
                                Swal.alert(["MET값은 필수 입력입니다.", 'warning']);
                                return false;
                            }if(WebUtil.isNull(gridData[data].metMinCfct)){
                                Swal.alert(["MET 분당 환산계수는 필수 입력입니다.", 'warning']);
                                return false;
                            }if(WebUtil.isNull(gridData[data].sortOrd)){
                                 Swal.alert(["정렬순서는 필수 입력입니다.", 'warning']);
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
                    url: "/svcStnd/grow/actStndMng/saveActStnd.ab",
                    param: param,
                    success: function(response) {
                        Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                            $this.searchActStndList(true);
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
                        url: "/svcStnd/grow/actStndMng/searchActStndList/excel.ab",
                        param: params,
                        success: function(response)
                        {
                            saveFileLocal(response, 'actStndMng.xls');
                        },
                        error: function (response)
                        {
                            Swal.alert([response, 'error']);
                        }
                    });
            },
            resetSearchParam: function()
            {
                let $this = this;
                $this.params =
                {
                    actNm           : ''  ,   // 활동_명
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