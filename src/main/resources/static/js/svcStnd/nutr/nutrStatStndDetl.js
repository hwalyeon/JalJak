let nutrStatStndDetl = new Vue({
    el: "#nutrStatStndDetlPopup",
    data:
    {
        codeCnt : 0,
        nutrStatStndList : [],
        params:
            {
                crud            : 'C'  ,
                orgNutrCd       : ''  ,
                nutrCd          : ''  ,
                nutrNm          : ''  ,
                nutrStatCd      : ''  ,
                nutrRmrk        : ''  ,
                paging          : 'N',
                totalCount    : 0  ,
                rowCount     : 30,
                currentPage : 1  ,
                currentIndex: 0
            },
        code:
            {
                nutrStatCd : []
              , nutrList : []
            },
    },
    components: {'summer-note': summernote },
    methods:
    {
        initialize: function()
        {
            let $this = this;
            $this.initCodeList();
        },
        initCodeList : function() {
            let $this = this;

            AjaxUtil.post(
                {
                    url: "/svcStnd/nutr/ddNutrEatStndMng/searchNutrCdNmList.ab",
                    param: {},
                    success: function(response) {
                        $this.code.nutrList = [];
                        if ( !!response.rtnData.result && response.rtnData.result.length > 0 ) {
                            $.each(response.rtnData.result, function(index, item) {
                                $this.code.nutrList.push({'cdVal':item.nutrCd, 'cdNm':item.nutrNm});
                            });
                        }
                        $this.codeCnt += 1;
                    },
                    error: function (response) {
                        Swal.alert([response, 'error']);
                    }
            });

            getCommonCodeList('NUTR_STAT_CD', $this.code.nutrStatCd, function () {
                $this.codeCnt += 1;
            });
        },
        initGrid: function()
        {
            let $this = this;
            let colModels =
            [
                {name: "crud"              , index: "crud"          , label: "crud"          , hidden:true},
                {name: "nutrCd"            , index: "nutrCd"        , label: "영양소코드"       , width: 50        , align: "center"},
                {name: "nutrStatCd"        , index: "nutrStatCd"    , label: "영양섭취상태"      , width: 50        , align: "center", hidden:true},
                {name: "nutrStatNm"        , index: "nutrStatNm"    , label: "영양섭취상태"      , width: 50        , align: "center"},
                {name: "nutrRmrk"          , index: "nutrRmrk"      , label: "영양섭취비고"      , width: 400        , align: "center"}
            ];

            $("#nutrStat_list").jqGrid("GridUnload");
            $("#nutrStat_list").jqGrid($.extend(true, {}, commonGridOptions(),
            {
                datatype  : "local",
                mtype     : 'post',
                url         : '/svcStnd/nutr/nutrInfoMng/searchNutrStatStndInfoList.ab',
                shrinkToFit: true,
                pager       : '#nutrStat_pager_list',
                height      : 150,
                colModel : colModels,
                onPaging : function(data) {
                    onPagingCommon(data, this, function(resultMap)
                    {
                        $this.params.currentPage  = resultMap.currentPage;
                        $this.params.rowCount     = resultMap.rowCount;
                        $this.params.currentIndex = resultMap.currentIndex;
                        $this.searchNutrStatInfoList(false);
                    })
                },
                onCellSelect : function (rowid , colId , val, e ){
                    var rowData = $("#nutrStat_list").getRowData(rowid);
                    $this.params.crud = rowData.crud;
                    $this.params.nutrCd = rowData.nutrCd;
                    $this.params.nutrRmrk = rowData.nutrRmrk;
                    $this.params.nutrStatCd = rowData.nutrStatCd;
                }
            }));
            resizeJqGridWidth("nutrStat_list", "nutrStat_list_wrapper");
        },

        btnNew  :  function()
        {
            let $this = this;
            $this.params.crud     =  "C";
            $this.params.nutrCd   = $this.params.orgNutrCd;
            $this.params.nutrRmrk = "";
            $this.params.nutrStatCd = "";
        },

        initPage: function(nutrCd) {
            let $this = this;
            $this.resetSearchParam();
            $this.params.orgNutrCd = nutrCd;
            $this.params.nutrCd = nutrCd;

            setTimeout(function() {
                $this.initGrid();
                $this.searchNutrStatInfoList(true);
            }, 500);

        },

        searchNutrStatInfoList: function(isSearch)
        {
            const $this = this;

            $("#nutrStat_list").setGridParam(
            {
                datatype: "json",
                postData: JSON.stringify( $this.params),
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


        saveInfo  :  function() {
            let $this = this;

            if(WebUtil.isNull($this.params.nutrCd)){
                Swal.alert(["영양소 섭취상태코드", 'warning']);
                return false;
            }
            if(WebUtil.isNull($this.params.nutrRmrk)){
                Swal.alert(["영양 섭취 비고는 필수 입력입니다.", 'warning']);
                return false;
            }


            AjaxUtil.post({
                url: "/svcStnd/nutr/nutrInfoMng/saveNutrStatStndInfo.ab",
                param: $this.params,
                success: function(response) {
                    Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                        $this.btnNew();
                        $this.searchNutrStatInfoList(true);
                    });
                },
                error: function (response) {
                    Swal.alert([response, 'error']);
                }
            });
        },

        delInfo  :  function() {
            let $this = this;

            $this.params.crud = 'D';

            AjaxUtil.post({
                url: "/svcStnd/nutr/nutrInfoMng/saveNutrStatStndInfo.ab",
                param: $this.params,
                success: function(response) {
                    Swal.alert(['삭제가 완료되었습니다.', 'success']).then(function() {
                        $this.btnNew();
                        $this.searchNutrStatInfoList(true);
                    });
                },
                error: function (response) {
                    Swal.alert([response, 'error']);
                }
            });
        },

        resetSearchParam: function()
        {
            let $this = this;
            $this.params =
            {
                crud            : 'C'  ,
                nutrCd          : ''  ,
                nutrNm          : ''  ,
                nutrStatCd      : ''  ,
                nutrRmrk        : ''  ,
                paging          : 'N',
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