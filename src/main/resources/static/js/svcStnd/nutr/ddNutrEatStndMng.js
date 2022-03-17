let ddNutrEatStndMng = new Vue({
    el: "#ddNutrEatStndMng",
    data:
        {
            codeCnt : 0,
            params:
                {
                    userId           : '' ,
                    sexCd           : ''  ,    // 성별_코드
                    ageYcnt        : ''  ,     // 나이_년수
                    nutrNm         : ''  ,    // 영양소_코드_명
                    paging          : 'N',
                    totalCount    : 0  ,
                    rowCount     : 3000,
                    currentPage : 1  ,
                    currentIndex: 0
                },
            code:
                {
                    sexCdList             : []
                  , nutrCdNmList      : [] //영양소코드명
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
                getCommonCodeList('SEX_CD',$this.code.sexCdList, function() { $this.codeCnt += 1; })
            },
            initGrid: function()
            {  
                let $this = this;
                let sexCdList        = commonGridCmonCd($this.code.sexCdList);
                // , frozen:true
                let colModels =
                [
                    {name:"crud"       , index:"crud"                     , label: "crud"                  , hidden: true },
                    {name: "sexCd"     , index: "sexCd"                  , label: "성별"                    , width: 80          , align: "center"  ,  fixed: true    , formatter:"select", editoptions:{value:sexCdList}},
                    {name: "ageYcnt"   , index: "ageYcnt"              , label: "나이(년)"                   , width: 80          , align: "center"     ,  fixed: true  }
                ];

                if($this.code.nutrCdNmList.length > 0){
                    for(var i in $this.code.nutrCdNmList ){
                        var data = $this.code.nutrCdNmList[i];
                        if(data.cdVal === 'VIT_B' || data.cdVal === 'AMNO' || data.cdVal==='EPA_DHA'){
                            colModels.push( {name:toCamelCase(data.cdVal)  , index:toCamelCase(data.cdVal)    , label:data.cdNm + "("+data.cdVal+")" , editable :false , width: 80 ,  fixed: true } );
                        }else {
                            colModels.push( {name:toCamelCase(data.cdVal)  , index:toCamelCase(data.cdVal)    , label:data.cdNm + "("+data.cdVal+")" , editable :true , width: 80 ,  fixed: true } );
                        }
                    }
                }

                $("#ddNutrEatStnd_list").jqGrid("GridUnload");
                $("#ddNutrEatStnd_list").jqGrid($.extend(true, {}, commonEditGridOptions(3000,''),
                {
                    datatype  : "local",
                    mtype      : 'post',
                    url            : '/svcStnd/nutr/ddNutrEatStndMng/searchDdNutrEatStndList.ab',
                    pager       : '#ddNutrEatStnd_pager_list',
                    height      : 405,
                    autowidth: false,
                    colModel : colModels,
                    afterSaveCell : function (rowid , colId , val, e ){
                        let rowData = $("#ddNutrEatStnd_list").getRowData(rowid);
                        if($("#ddNutrEatStnd_list").getRowData(rowid).crud != "C" && $("#ddNutrEatStnd_list").getRowData(rowid).crud != "D" ) {
                            $("#ddNutrEatStnd_list").setRowData(rowid, {crud:"U"});
                        }

                        if(colId === 'vitB1' || colId === 'vitB2' || colId === 'nia' || colId === 'dfe' || colId === 'vitB12'){
                            let vaiBTemp = parseFloat(WebUtil.nvl(rowData.vitB1 , '0')) + parseFloat(WebUtil.nvl(rowData.vitB2, '0')) +parseFloat(WebUtil.nvl(rowData.nia, '0'))+ (WebUtil.nvl(parseFloat(rowData.dfe, '0')) / 1000) + (WebUtil.nvl(parseFloat(rowData.vitB12, '0')) / 1000);
                            $("#ddNutrEatStnd_list").setRowData(rowid, {vitB: Math.round(vaiBTemp * 10)/10});
                        }else if(colId === 'ile' || colId === 'leu' || colId === 'val'){
                            let amnoTemp = parseFloat(WebUtil.nvl(rowData.ile, '0')) + parseFloat(WebUtil.nvl(rowData.leu, '0')) +parseFloat(WebUtil.nvl(rowData.val, '0'));
                            $("#ddNutrEatStnd_list").setRowData(rowid, {amno: Math.round(amnoTemp * 10)/10});
                        }else if(colId === 'epa' || colId === 'dha'){
                            let epaDhaTemp = parseFloat(WebUtil.nvl(rowData.epa, '0')) + parseFloat(WebUtil.nvl(rowData.dha, '0'));
                            $("#ddNutrEatStnd_list").setRowData(rowid, {epaDha: Math.round(epaDhaTemp * 10)/10});
                        }
                    },
                }));

                $("#ddNutrEatStnd_list").jqGrid("setGroupHeaders",{
                    useColSpanStyle: true,
                    groupHeaders: [
                        {startColumnName: 'vitB', numberOfColumns: 6, titleText: '비타민_B(비타민B1,B2,B12,나이아신,엽산)'},
                        {startColumnName: 'amno', numberOfColumns: 4, titleText: '아미노산'},
                        {startColumnName: 'epa', numberOfColumns: 3, titleText: 'EPA+DHA'}
                    ]
                });

//                $("#ddNutrEatStnd_list").jqGrid("setFrozenColumns");

                resizeJqGridWidth("ddNutrEatStnd_list", "ddNutrEatStnd_list_wrapper");
            },
            searchDdNutrEatStndList: function(isSearch)
            {
                let $this = this;
                let params = $.extend(true, {}, $this.params);

                if ( isSearch )
                {
                    params.currentPage = 1;
                    params.currentIndex = 0;
                }

                $("#ddNutrEatStnd_list").setGridParam({
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

            btnSave  :  function() {
                let $this = this;
                let gridData = commonGridGetDataNew($("#ddNutrEatStnd_list"));
                let gridColModel = $("#ddNutrEatStnd_list").jqGrid("getGridParam","colModel");

                if(gridData.length > 0)
                {
                    for (let data in gridData) {
                        for(let key in gridData[data]){
                            if(WebUtil.isNull(gridData[data][key])){
                                var keyCol = gridColModel.filter(col => {
                                    return col.name == key;
                                })
                                var keyLabel = keyCol[0].label;
                                Swal.alert([keyLabel + "의 섭취기준을 입력하여 주시기 바랍니다.", 'warning']);
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
                    url: "/svcStnd/nutr/ddNutrEatStndMng/saveDdNutrEatStnd.ab",
                    param: param,
                    success: function(response) {
                        Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                            $this.searchDdNutrEatStndList(true);
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
                        url: "/svcStnd/nutr/ddNutrEatStndMng/searchDdNutrEatStndList/excel.ab",
                        param: params,
                        success: function(response)
                        {
                            saveFileLocal(response, 'ddNutrEatStndMng.xls');
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
                    sexCd            : ''   ,    // 성별_코드
                    ageYcnt         : ''  ,     // 나이_년수
                    nutrCd           : ''  ,     // 영양소_코드
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
                if(value ===  2){
                    $this.initGrid();
                    $this.searchDdNutrEatStndList(true);
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