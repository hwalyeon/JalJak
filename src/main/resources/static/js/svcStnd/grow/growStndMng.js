let growStndMng = new Vue({
    el: "#growStndMng",
    data:
        {
            params:
                {
                    userId             : ''   ,
                    growStndVer  : ''  ,  //성장_기준_버전
                    growStndNo  : ''  ,  //성장_기준_번호
                    paging            : 'Y',
                    totalCount      : 0  ,
                    rowCount        : 30,
                    currentPage    : 1  ,
                    currentIndex   : 0
                },
            code:
                {
                    sexCdList : []   //성별_코드_리스트
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
                    $this.searchGrowStndList(true);
                })
            },
            initGrid: function()
            {
                let $this = this;
                let sexCdList = commonGridCmonCd($this.code.sexCdList);
                let colModels =
                    [
                        {name: "crud"           , index: "crud"           , label: "crud"              , hidden: true  },
                        {name: "growStndNoTemp" , index: "growStndNoTemp" , label: "성장기준번호"       , hidden: true  },
                        {name: "growStndVer"    , index: "growStndVer"    , label: "성장기준버전"       , width: 80  , align: "center"  , editable: true  },
                        {name: "growStndNo"     , index: "growStndNo"     , label: "성장기준번호"       , width: 80  , align: "center"  , editable: true  , editrules: {number:true}  },
                        {name: "sexCd"          , index: "sexCd"          , label: "성별"              , width: 80   , align: "center"  , edittype : "select", formatter: "select", editable: true, editoptions: {value: sexCdList}  },
                        {name: "ageYcnt"        , index: "ageYcnt"        , label: "나이(년)"          , width: 80   , align: "center"  , editable: true  , editrules: {number:true}  },
                        {name: "ageMcnt"        , index: "ageMcnt"        , label: "나이(개월)"        , width: 80   , align: "center"  , editable: true  , editrules: {number:true}  },
                        {name: "p3Gidx"         , index: "p3Gidx"         , label: "백분위3 성장지수"  , width: 80   , align: "center"  , editable: true  , editrules: {number:true}  },
                        {name: "p50Gidx"        , index: "p50Gidx"        , label: "백분위50 성장지수" , width: 80   , align: "center"  , editable: true  , editrules: {number:true}  },
                        {name: "p97Gidx"        , index: "p97Gidx"        , label: "백분위97 성장지수" , width: 80   , align: "center"  , editable: true  , editrules: {number:true}  },
                        {name: "regDt"          , index: "regDt"          , label: "등록일자"          , width: 80   , align: "center"  , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);}  },
                        {name: "regTm"          , index: "regTm"          , label: "등록시각"          , width: 80   , align: "center"  , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);}  },
                        {name: "regUserId"      , index: "regUserId"      , label: "등록사용자ID"      , width: 80   , align: "center"  },
                        {name: "uptDt"          , index: "uptDt"          , label: "수정일자"          , width: 80   , align: "center"  , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);}  },
                        {name: "uptTm"          , index: "uptTm"          , label: "수정시각"          , width: 80   , align: "center"  , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);}  },
                        {name: "uptUserId"      , index: "uptUserId"      , label: "수정사용자ID"      , width: 80   , align: "center"  }
                    ];

                $("#growStnd_list").jqGrid("GridUnload");
                $("#growStnd_list").jqGrid($.extend(true, {}, commonEditGridOptions(),
                 {
                        datatype  : "local",
                        mtype      : 'post',
                        url            : '/svcStnd/grow/growStndMng/searchGrowStndList.ab',
                        pager       : '#growStnd_pager_list',
                        height      : 405,
                        colModel : colModels,
                        onPaging : function(data) {
                            onPagingCommon(data, this, function(resultMap)
                            {
                                $this.params.currentPage  = resultMap.currentPage;
                                $this.params.rowCount     = resultMap.rowCount;
                                $this.params.currentIndex = resultMap.currentIndex;
                                $this.searchGrowStndList(false);
                            })
                        },
                        afterSaveCell : function (rowid , colId , val, e )
                        {
                            if($("#growStnd_list").getRowData(rowid).crud != "C" && $("#growStnd_list").getRowData(rowid).crud != "D" )
                            {
                                $("#growStnd_list").setRowData(rowid, {crud:"U"});
                            }
                        }
                }));
                resizeJqGridWidth("growStnd_list", "growStnd_list_wrapper");
            },
            searchGrowStndList: function(isSearch)
            {
                let $this = this;
                let params = $.extend(true, {}, $this.params);

                if ( isSearch )
                {
                    params.currentPage = 1;
                    params.currentIndex = 0;
                }

                $("#growStnd_list").setGridParam(
                {
                        datatype: "json",
                        postData: JSON.stringify(params),
                        page       : 1,
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
                let date = new Date(); //현재 날짜
                var cnt = $("#growStnd_list").jqGrid("getGridParam", "records")+1;

                var addRow =
                {
                    crud                        : "C",
                    growStndNoTemp : ""  ,
                    growStndVer          : ""  ,
                    growStndNo          : ""  ,
                    sexCd                     : ""  ,
                    ageYcnt                  : ""  ,
                    ageMcnt                 : ""  ,
                    p3Gidx                    : ""  ,
                    p50Gidx                  : ""  ,
                    p97Gidx                  : ""  ,
                    regDt                      : date  ,
                    regTm                     : date  ,
                    regUserId                : $this.userId  ,
                    uptDt                      : date  ,
                    uptTm                     : date  ,
                    uptUserId                : $this.userId
                };
                $("#growStnd_list").addRowData(cnt, addRow);
            },
            btnDelRow : function()
            {
                let checkIds = $("#growStnd_list").jqGrid("getGridParam","selrow") + "";  // 단건
                if ( checkIds == "" )
                {
                    alert("삭제할 행을 선택해주십시요.");
                    return false;
                }
                let checkId = checkIds.split(",");
                for ( var i in checkId )
                {
                    if ( $("#growStnd_list").getRowData(checkId[i]).crud == "C" )
                    {
                        $("#growStnd_list").setRowData(checkId[i], {crud: "N"});
                        $("#"+checkId[i],"#growStnd_list").css({display: "none"});
                    }
                    else
                    {
                        $("#growStnd_list").setRowData(checkId[i], {crud: "D"});
                        $("#"+checkId[i],"#growStnd_list").css({display: "none"});
                    }
                }
            },
            btnSave  :  function()
            {
                let $this = this;
                let gridData = commonGridGetDataNew($("#growStnd_list"));
                if(gridData.length > 0)
                {
                    for (let data in gridData)
                    {
                        let ageYcnt= gridData[data].ageYcnt;
                        let ageMcnt = gridData[data].ageMcnt;
                        if(gridData[data].crud === 'C' || gridData[data].crud === 'U')
                        {
                            if(ageMcnt<ageYcnt*12 || ageMcnt>(ageYcnt*12)+11){
                                Swal.alert(["정확한 개월수를 입력해야합니다.", 'warning']);
                                return false;
                            }
                            if(WebUtil.isNull(gridData[data].growStndVer)){
                                Swal.alert(["성장기준버전 필수 입력입니다.", 'warning']);
                                return false;
                            }if(WebUtil.isNull(gridData[data].growStndNo)){
                                Swal.alert(["성장기준번호 필수 입력입니다.", 'warning']);
                                return false;
                            }if(WebUtil.isNull(gridData[data].ageYcnt)){
                                Swal.alert(["나이(년수) 필수 입력입니다.", 'warning']);
                                return false;
                            }if(WebUtil.isNull(gridData[data].ageMcnt)){
                                Swal.alert(["나이(개월수) 필수 입력입니다.", 'warning']);
                                return false;
                            }if(WebUtil.isNull(gridData[data].p3Gidx)){
                                Swal.alert(["백분위3 성장지수 필수 입력입니다.", 'warning']);
                                return false;
                            }if(WebUtil.isNull(gridData[data].p50Gidx)){
                                Swal.alert(["백분위50 성장지수 필수 입력입니다.", 'warning']);
                                return false;
                            }if(WebUtil.isNull(gridData[data].p97Gidx)){
                                Swal.alert(["백분위97 성장지수 필수 입력입니다.", 'warning']);
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

                AjaxUtil.post(
                 {
                    url         : "/svcStnd/grow/growStndMng/saveGrowStnd.ab",
                    param  : param,
                    success: function(response)
                    {
                        Swal.alert(['저장이 완료되었습니다.', 'success']).then(function()
                        {
                            $this.searchGrowStndList(true);
                        });
                    },
                    error: function (response)
                    {
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
                        url           : "/svcStnd/grow/growStndMng/searchGrowStndList/excel.ab",
                        param    : params,
                        success   : function(response)
                        {
                            saveFileLocal(response, 'growStndMng.xls');
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
                    growStndVer  : ''  ,    //성장_기준_버전
                    growStndNo  : ''  ,    //성장_기준_번호
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
