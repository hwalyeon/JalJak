let nutrEatStndDetl = new Vue({
    el: "#nutrEatStndDetlPopup",
    data:
    {
        codeCnt : 0,
        nutrEatStndList : [],
        params:
            {
                crud            : 'C'  ,
                orgNutrCd       : ''  ,
                nutrCd          : ''  ,
                totalCount    : 0  ,
                rowCount     : 3000,
                currentPage : 1  ,
                currentIndex: 0
            },
        code:
            {

            },
    },
    methods:
    {
        initialize: function()
        {
            let $this = this;
            $this.initCodeList();
        },
        initCodeList : function() {


        },

        initGrid: function()
        {
            let colModels =
            [
                {name: "crud"             , index: "crud"         , label: "crud"        , hidden:true},
                {name: "sexCd"            , index: "sexCd"        , label: "성별코드"      , width: 50        , align: "center", hidden:true},
                {name: "sexNm"            , index: "sexNm"        , label: "성별"         , width: 50        , align: "center"},
                {name: "ageYcnt"          , index: "ageYcnt"      , label: "나이년수"      , width: 50        , align: "center"},
                {name: "nutrCd"           , index: "nutrCd"       , label: "영양소코드"    , width: 50        , align: "center"},
                {name: "nutrNm"           , index: "nutrNm"       , label: "영양소명"      , width: 50        , align: "center"},
                {name: "ddRcmdQty"        , index: "ddRcmdQty"    , label: "일권장량"      , width: 50        , align: "center", editable :true, editrules:{number:true}},
                {name: "ddNeedQty"        , index: "ddNeedQty"    , label: "일필요량"      , width: 50        , align: "center", hidden:true}
            ];

            $("#nutrEat_list").jqGrid("GridUnload");
            $("#nutrEat_list").jqGrid($.extend(true, {}, commonEditGridOptions(),
            {
                datatype  : "local",
                mtype     : 'post',
                url       : '/svcStnd/nutr/nutrInfoMng/searchNutrEatStndInfoList.ab',
                shrinkToFit: true,
                pager       : '#nutrEat_pager_list',
                pgbuttons : false,
                pginput : false,
                rowList : [100,200,300,400],
                height      : 400,
                colModel : colModels,
                rowNum : 3000
            }));
            resizeJqGridWidth("nutrStat_list", "nutrStat_list_wrapper");
        },

        initPage: function(nutrCd, nutrNm) {
            let $this = this;
            $this.resetSearchParam();
            $this.params.nutrCd = nutrCd;
            $this.params.nutrNm = nutrNm;

            setTimeout(function() {
                $this.initGrid();
                $this.searchNutrEatInfoList(true);
            }, 500);

        },

        searchNutrEatInfoList: function(isSearch)
        {
            const $this = this;

            $("#nutrEat_list").setGridParam(
            {
                datatype: "json",
                postData: JSON.stringify( $this.params),
                page: 1,
                loadComplete: function (response)
                {
                    if ( response.rtnData.result == 0 )
                    {
                        $this.newList();
                    }
                }
            }).trigger("reloadGrid");
        },


        newList : function ()
        {
            let $this = this;
            $this.nutrEatStndList = [];

            var addRow = {};
            var sexCd = "";
            var sexNm = "";
            var cnt = 0;
            for (var i = 0 ; i < 2 ; i ++){
                if(i===0){
                    sexCd = "FEMALE";
                    sexNm = "여자";
                }
                else {
                    sexCd = "MALE";
                    sexNm = "남자";
                }
                for (var x = 3 ; x < 20 ; x ++){
                    addRow = {};
                    addRow = {
                        crud	    : "C" ,
                        sexCd       : sexCd,
                        sexNm       : sexNm,
                        nutrCd      : $this.params.nutrCd ,
                        nutrNm      : $this.params.nutrNm ,
                        ageYcnt      : x ,
                        ddRcmdQty    : '' ,
                        ddNeedQty    : '' ,
                    };
                    cnt ++;
                    $("#nutrEat_list").addRowData(cnt, addRow);
                }
            }
        },

        saveInfo  :  function() {
            let $this = this;

            let gridData = commonGridGetDataNew($("#nutrEat_list"));

            if(gridData.length > 0) {
                for (let data in gridData) {
                    if (gridData[data].crud === 'C' || gridData[data].crud === 'U') {
                        if (WebUtil.isNull(gridData[data].ddRcmdQty)) {
                            Swal.alert(["일 권장량은 필수 입력입니다.", 'warning']);
                            return false;
                        }
                    }
                }
            }

            let param = { gridList : []}
            param.gridList = gridData;

            AjaxUtil.post({
                url: "/svcStnd/nutr/nutrInfoMng/saveNutrEatStndInfo.ab",
                param: param,
                success: function(response) {
                    Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                        $("#nutrEat_list").jqGrid('clearGridData', true);
                        $this.searchNutrEatInfoList(true);
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
                    crud            : 'C',
                    nutrCd          : '' ,
                    paging          : 'N',
                    totalCount    : 0  ,
                    rowCount     : 3000,
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