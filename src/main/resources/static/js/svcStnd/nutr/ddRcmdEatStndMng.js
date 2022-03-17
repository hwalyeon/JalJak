let ddRcmdEatStndMng = new Vue({
    el: "#ddRcmdEatStndMng",
    data:
        {
            codeCnt : 1,
            params:
                {
                    userId       : '' ,
                    sexCd        : '' ,    // 성별_코드
                    ageYcnt      : '' ,     // 나이_년수
                    nutrNm       : '' ,    // 영양소_코드_명
                    eatQtyRf     : '' ,     // 섭취_량_From
                    paging       : 'Y',
                    totalCount   : 0  ,
                    rowCount     : 30 ,
                    currentPage  : 1  ,
                    currentIndex : 0
                },
            code:
                {
                      sexCdList      : []
                    , nutrCdNmList   : [] //영양소코드명 리스트
                    , nutrStatCdList : [] //영양상태코드명 리스트
                    , useYnList      : [{'cdVal':'Y', 'cdNm':'Y'},{'cdVal':'N', 'cdNm':'N'}]  //영양상태코드명 리스트
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

                getCommonCodeList('NUTR_STAT_CD',$this.code.nutrStatCdList, function (){ $this.codeCnt += 1;});

                AjaxUtil.post(
                    {
                        url: "/svcStnd/nutr/ddNutrEatStndMng/searchNutrCdNmList.ab",
                        param: {},
                        success: function(response) {
                            $this.code.nutrCdNmList = [];
                            if ( !!response.rtnData.result && response.rtnData.result.length > 0 ) {
                                $.each(response.rtnData.result, function(index, item) {
                                    $this.code.nutrCdNmList.push({'cdVal':item.nutrCd, 'cdNm':item.nutrNm});
                                });
                            }
                            $this.codeCnt += 1;
                        },
                        error: function (response) {
                            Swal.alert([response, 'error']);
                        }
                    });

                getCommonCodeList('SEX_CD',$this.code.sexCdList, function()
                {
                    $this.codeCnt += 1;
                })
            },
            initGrid: function()
            {
                let $this = this;
                let sexCdList      = commonGridCmonCd($this.code.sexCdList);
                let nutrCdNmList   = commonGridCmonCd($this.code.nutrCdNmList);
                let nutrStatCdList = commonGridCmonCd($this.code.nutrStatCdList);
                let useYnList      = commonGridCmonCd($this.code.useYnList);
                let colModels =
                    [
                        {name:"crud"                   , index:"crud"                     , label: "crud"                        , hidden: true                              },
                        {name: "sexCdTemp"      ,  index: "sexCdTemp"       , label: "성별"                        , width: 80        , align: "center"       , hidden: true },
                        {name: "ageYcntTemp"  , index: "ageYcntTemp"     , label: "나이(년)"                 , width: 80         , align: "center"       , hidden: true },
                        {name: "nutrCdTemp"     , index: "nutrCdTemp"      , label: "영양소코드"             , width: 80         , align: "center"       , hidden: true },
                        {name: "eatQtyFrTemp"  ,  index: "eatQtyFrTemp"  , label: "섭취량(From)"         , width: 80         , align: "center"       , hidden: true },
                        {name: "nutrNm"            , index: "nutrNm"              , label: "영양소명"                    , hidden: true        }, //엑셀 다운로드용
                        {name: "sexCd"                , index: "sexCd"                 , label: "성별"                        , width: 80         , align: "center"       , editable: true
                            ,edittype:"select"         , formatter:"select", editoptions:{value:sexCdList}},
                        {name: "ageYcnt"            , index: "ageYcnt"             , label: "나이(년)"                  , width: 80         , align: "center"       , editable: true},
                        {name: "nutrCd"               , index: "nutrCd"               , label: "영양소명"                , width: 80         , align: "center"       , editable: true
                            ,edittype:"select"         , formatter:"select"           , editoptions:{value:nutrCdNmList}},
                        {name: "eatQtyFr"            , index: "eatQtyFr"            , label: "섭취량(From)"         , width: 80          , align: "center"       , editable: true},
                        {name: "eatQtyTo"           , index: "eatQtyTo"          , label: "섭취량(To)"              , width: 80          , align: "center"       , editable: true},
                        {name:"nutrStatCd"          , index: "nutrStatCd"        , label: "영양섭취상태"         , width: 80          , align: "center"       , editable: true
                            ,edittype:"select"          , formatter:"select", editoptions:{value:nutrStatCdList}},
                        {name: "useYn"                 , index: "useYn"            , label: "사용여부"                   , width: 80          , align: "center"       , editable: true
                         , edittype:"select"            , formatter:"select" ,editoptions:{value:useYnList}},
                        {name: "regDt"                 , index: "regDt"                 , label: "등록일자"               , width: 80          , align: "center"
                            , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);                                              }},
                        {name: "regTm"                , index: "regTm"               , label: "등록시각"               , width: 80          , align: "center"
                            , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);                                              }},
                        {name: "regUserId"           , index: "regUserId"         , label: "등록사용자ID"         , width: 80          , align: "center"},
                        {name: "uptDt"                 , index: "uptDt"                , label: "수정일자"                 , width: 80         , align: "center"
                            , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);                                              }},
                        {name: "uptTm"               , index: "uptTm"               , label: "수정시각"                 , width: 80         , align: "center"
                            , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);                                              }},
                        {name: "uptUserId"          , index: "uptUserId"         , label: "수정사용자ID"          , width: 80         , align: "center"}
                    ];

                $("#ddRcmdEatStnd_list").jqGrid("GridUnload");
                $("#ddRcmdEatStnd_list").jqGrid($.extend(true, {}, commonEditGridOptions(),
                    {
                        datatype  : "local",
                        mtype      : 'post',
                        url            : '/svcStnd/nutr/ddRcmdEatStndMng/searchDdRcmdEatStndList.ab',
                        pager       : '#ddRcmdEatStnd_pager_list',
                        height      : 405,
                        colModel : colModels,
                        onPaging : function(data) {
                            onPagingCommon(data, this, function(resultMap)
                            {
                                $this.params.currentPage  = resultMap.currentPage;
                                $this.params.rowCount     = resultMap.rowCount;
                                $this.params.currentIndex = resultMap.currentIndex;
                                $this.searchDdRcmdEatStndList(false);
                            })
                        },
                        afterSaveCell : function (rowid , colId , val, e ){
                            if($("#ddRcmdEatStnd_list").getRowData(rowid).crud != "C" && $("#ddRcmdEatStnd_list").getRowData(rowid).crud != "D" ) {
                                $("#ddRcmdEatStnd_list").setRowData(rowid, {crud:"U"});
                            }
                        }
                    }));
                resizeJqGridWidth("ddRcmdEatStnd_list", "ddRcmdEatStnd_list_wrapper");
            },
            searchDdRcmdEatStndList: function(isSearch)
            {
                let $this = this;
                let params = $.extend(true, {}, $this.params);

                if ( isSearch )
                {
                    params.currentPage = 1;
                    params.currentIndex = 0;
                }

                $("#ddRcmdEatStnd_list").setGridParam(
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
                var cnt = $("#ddRcmdEatStnd_list").jqGrid("getGridParam", "records")+1;

                var addRow = {
                    crud                  : "C" ,
                    sexCdTemp      : ""  ,
                    ageYcntTemp   : "" ,
                    nutrCdTemp     : "" ,
                    eatQtyFrTemp  : "" ,
                    nutrNm             : "" ,
                    sexCd                : "" ,
                    ageYcnt            : "" ,
                    nutrCd              : "" ,
                    eatQtyFr           : "" ,
                    eatQtyTo          : "" ,
                    nutrStatCd        : "" ,
                    useYn                : "" ,
                    regDt             : date  ,
                    regTm            : date  ,
                    regUserId       : $this.userId  ,
                    uptDt             : date  ,
                    uptTm            : date  ,
                    uptUserId       : $this.userId

                };
                $("#ddRcmdEatStnd_list").addRowData(cnt, addRow);

            },
            btnDelRow : function() {
                //var checkIds = $("#dgem_list").jqGrid("getGridParam","selarrrow") + ""; // 멀티
                let checkIds = $("#ddRcmdEatStnd_list").jqGrid("getGridParam","selrow") + "";  // 단건
                if ( checkIds == "" )
                {
                    alert("삭제할 행을 선택해주십시요.");
                    return false;
                }

                let checkId = checkIds.split(",");
                for ( var i in checkId )
                {
                    if ( $("#ddRcmdEatStnd_list").getRowData(checkId[i]).crud == "C" )
                    {
                        $("#ddRcmdEatStnd_list").setRowData(checkId[i], {crud:"N"});
                        $("#"+checkId[i],"#ddRcmdEatStnd_list").css({display:"none"});
                    }
                    else
                    {
                        $("#ddRcmdEatStnd_list").setRowData(checkId[i], {crud:"D"});
                        $("#"+checkId[i],"#ddRcmdEatStnd_list").css({display:"none"});
                    }
                }
            },
            btnSave  :  function() {
                let $this = this;
                let gridData = commonGridGetDataNew($("#ddRcmdEatStnd_list"));
                if(gridData.length > 0)
                {
                    for (let data in gridData)
                    {
                        if(gridData[data].crud === 'C' || gridData[data].crud === 'U')
                        {
                            if(WebUtil.isNull(gridData[data].sexCd))
                            {
                                Swal.alert(["성별은 필수 입력입니다.", 'warning']);
                                return false;
                            }if(WebUtil.isNull(gridData[data].ageYcnt))
                            {
                                Swal.alert(["나이(년수)는 필수 입력입니다.", 'warning']);
                                return false;
                            }if(WebUtil.isNull(gridData[data].nutrCd))
                            {
                                Swal.alert(["영양소명은 필수 입력입니다.", 'warning']);
                                return false;
                            }if(WebUtil.isNull(gridData[data].eatQtyFr))
                            {
                                Swal.alert(["섭취량(From)은 필수 입력입니다.", 'warning']);
                                return false;
                            }if(WebUtil.isNull(gridData[data].useYn)){
                                Swal.alert(["사용여부는 필수 입력입니다.", 'warning']);
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
                    url: "/svcStnd/nutr/ddRcmdEatStndMng/saveDdRcmdEatStnd.ab",
                    param: param,
                    success: function(response) {
                        Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                            $this.searchDdRcmdEatStndList(true);
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
                        url: "/svcStnd/nutr/ddRcmdEatStndMng/searchDdRcmdEatStndList/excel.ab",
                        param: params,
                        success: function(response)
                        {
                            saveFileLocal(response, 'ddRcmdEatStndMng.xls');
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
                    sexCd           : ''   ,    // 성별_코드
                    ageYcnt        : ''  ,     // 나이_년수
                    nutrNm         : ''  ,    // 영양소_코드_명
                    eatQtyRf       : ''  ,     // 섭취_량_From
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
            'codeCnt' : function (value){
                let $this = this;
                if(value ===  4){
                    $this.initGrid();
                    $this.searchDdRcmdEatStndList(true);
                }
            }
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