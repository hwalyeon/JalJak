let bpalCalcStndMng = new Vue({
    el: "#bpalCalcStndMng",
    data:
        {
            params:
                {
                    userId            : ''  ,
                    sexCd            : ''   ,    // 성별_코드
                    ageYcntFr      : ''  ,     // 나이_년수_FROM
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
                    $this.searchBpalCalcStndList(true);
                })
            },
            initGrid: function()
            {  
                let $this = this;
                let sexCdList = commonGridCmonCd($this.code.sexCdList);
                let colModels =
                [
                	//name: 서버데이터명            ,index :"웹 출력 명"                 , name 없을 시 대타            , hidden:보일지 말지
                	{name:"crud"                     , index:"crud"                       , label:"crud"                 , hidden:true } ,
                    {name: "sexCdTemp"        , index: "sexCdTemp"          , label: "성별"                  , hidden:true } ,
                    {name: "ageYcntFrTemp"  , index: "ageYcntFrTemp"   , label: "나이(From)"       , hidden:true } ,     
                    {name: "ageYcntToTemp" , index: "ageYcntToTemp"  , label: "나이(To)"           , hidden:true } ,
                    {name: "sexCd"                  , index: "sexCd"                    , label: "성별"                 , width: 80       , align: "center", editable: true
                    ,edittype:"select", formatter:"select" ,editoptions:{value:sexCdList}},
                    {name: "ageYcntFr"           , index: "ageYcntFr"             , label: "나이(From)"       , width: 80        , align: "center", editable: true , editrules:{number:true}},
                    {name: "ageYcntTo"          , index: "ageYcntTo"            , label: "나이(To)"           , width: 80         , align: "center", editable: true, editrules:{number:true}},
                    {name: "calcFrml"              , index: "calcFrml"               , label: "계산식        "      , width: 80        , align: "center", editable: true},
                    {name: "regDt"                  , index: "regDt"                    , label: "등록일자"          , width: 80        , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);                                     }},
                    {name: "regTm"                 , index: "regTm"                   , label: "등록시각"          , width: 80       , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);                                     }},
                    {name: "regUserId"            , index: "regUserId"             , label: "등록사용자ID"   , width: 80        , align: "center"},
                    {name: "uptDt"                  , index: "uptDt"                    , label: "수정일자"          , width: 80        , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);                                     }},
                    {name: "uptTm"                 , index: "uptTm"                   , label: "수정시각"          , width: 80         , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);                                     }},
                    {name: "uptUserId"            , index: "uptUserId"             , label: "수정사용자ID"    , width: 80         , align: "center"}
                ];

                $("#bpalCalcStnd_list").jqGrid("GridUnload");
                $("#bpalCalcStnd_list").jqGrid($.extend(true, {}, commonEditGridOptions(),
                {
                    datatype  : "local",
                    mtype      : 'post'   ,
                    url            : '/svcStnd/fat/bpalCalcStndMng/searchBpalCalcStndList.ab',
                    pager       : '#bpalCalcStnd_pager_list',
                    height      : 405,
                    colModel : colModels,
                    onPaging : function(data) {
                        onPagingCommon(data, this, function(resultMap)
                        {
                            $this.params.currentPage  = resultMap.currentPage;
                            $this.params.rowCount     = resultMap.rowCount;
                            $this.params.currentIndex = resultMap.currentIndex;
                            $this.searchBpalCalcStndList(false);
                        })
                    },
                    afterSaveCell : function (rowid , colId , val, e ){
                        if($("#bpalCalcStnd_list").getRowData(rowid).crud != "C" && $("#bpalCalcStnd_list").getRowData(rowid).crud != "D" ) {
                            $("#bpalCalcStnd_list").setRowData(rowid, {crud:"U"});
                        }
                    }
                }));
                resizeJqGridWidth("bpalCalcStnd_list", "bpalCalcStnd_list_wrapper");
            },
            searchBpalCalcStndList: function(isSearch)
            {
                let $this = this;
                let params = $.extend(true, {}, $this.params);

                if ( isSearch )
                {
                    params.currentPage = 1;
                    params.currentIndex = 0;
                }

                $("#bpalCalcStnd_list").setGridParam(
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
                var cnt = $("#bpalCalcStnd_list").jqGrid("getGridParam", "records")+1;

                var addRow =
                {
                    crud         	         : "C" ,
                    sexCdTemp    	     : "" ,
                    ageYcntFrTemp   : "" ,
                    ageYcntToTemp	 : "" ,
                    sexCd                   : "" ,
                    ageYcntFr    	     : "" ,
                    ageYcntTo    	   	 : "" ,
                    calcFrml     	   	     : "" ,
                    regDt            	     : date  ,
                    regTm           	     : date  ,
                    regUserId             : $this.userId  ,
                    uptDt                   : date  ,
                    uptTm                  : date  ,
                    uptUserId         	 : $this.userId
                };
                $("#bpalCalcStnd_list").addRowData(cnt, addRow);

            },
            btnDelRow : function() {
                //var checkIds = $("#dgem_list").jqGrid("getGridParam","selarrrow") + ""; // 멀티
                let checkIds = $("#bpalCalcStnd_list").jqGrid("getGridParam","selrow") + "";  // 단건
                if ( checkIds == "" )
                {
                    alert("삭제할 행을 선택해주십시요.");
                    return false;
                }

                let checkId = checkIds.split(",");
                for ( var i in checkId )
                {
                    if ( $("#bpalCalcStnd_list").getRowData(checkId[i]).crud == "C" )
                    {
                        $("#bpalCalcStnd_list").setRowData(checkId[i], {crud:"N"});
                        $("#"+checkId[i],"#bpalCalcStnd_list").css({display:"none"});
                    }
                    else
                    {
                        $("#bpalCalcStnd_list").setRowData(checkId[i], {crud:"D"});
                        $("#"+checkId[i],"#bpalCalcStnd_list").css({display:"none"});
                    }
                }
            },
            btnSave  :  function() {
                let $this = this;
                let gridData = commonGridGetDataNew($("#bpalCalcStnd_list"));
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
                            }if(WebUtil.isNull(gridData[data].ageYcntFr))
                            {
                            	Swal.alert(["나이(From)는 필수 입력입니다.", 'warning']);
                            	return false;
	                        }if(WebUtil.isNull(gridData[data].ageYcntTo))
	                        {
	                            Swal.alert(["나이(To)는 필수 입력입니다.", 'warning']);
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
                    url: "/svcStnd/fat/bpalStndMng/saveBpalStnd.ab",
                    param: param,
                    success: function(response) {
                        Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                            $this.searchBpalCalcStndList(true);
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
                        url: "/svcStnd/fat/bpalCalcStndMng/searchBpalCalcStndList/excel.ab",
                        param: params,
                        success: function(response)
                        {
                            saveFileLocal(response, 'bpalCalcStndMng.xls');
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
                    ageYcntFr      : ''  ,     // 나이_년수_FROM
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