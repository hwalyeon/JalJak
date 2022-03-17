let termInfoMng = new Vue({
    el: "#termInfoMng",
    data: {
        params: {
            termDivCd:'',
            termDivNm:'',
            termVer:'',
            aplyStrtDt:'',
            termCntn:'',
            essnYn:'',
            sortOrd:'',
            paging: 'Y',
            totalCount: 0,
            rowCount: 30,
            currentPage: 1,
            currentIndex: 0
        },
        code:{
            termInfoList : []
        },
    },


    methods: {

        initialize: function() {

            let $this = this;

            $this.initCodeList();

            $this.initGrid();

            $this.searchTermInfoList(true);

        },
        initCodeList: function() {
            let $this = this;
            getCommonCodeList('TERM_DIV_CD',$this.code.termInfoList);
        },
        initGrid: function() {
            let $this = this;
            let colModels = [
                {name: "termDivCd"            , index: "termDivCd"            , label: "약관구분코드"         , width: 80, align: "center"},
                {name: "termDivNm"            , index: "termDivNm"            , label: "약관구분코드명"       , width: 80, align: "center"},
                {name: "termVer"              , index: "termVer"              , label: "약관버전"            , width: 80, align: "center"},
                {name: "aplyStrtDt"           , index: "aplyStrtDt"           , label: "적용시작일자"         , width: 80, align: "center"},
                {name: "termCntnTemp"             , index: "termCntnTemp"             , label: "약관내용"            , width: 300, align: "center"},
                {name: "termCntn"             , index: "termCntn"             , label: "약관내용"            , width: 300, align: "center", hidden:true},
                {name: "essnYn"               , index: "essnYn"               , label: "필수여부"            , width: 80, align: "center"},
                {name: "sortOrd"              , index: "sortOrd"              , label: "정렬순서"            , width: 80, align: "center"},
                {name: "regDt"                , index: "regDt"                , label: "등록일자"            , width: 80, align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);                               }},
                {name: "regTm"                , index: "regTm"                , label: "등록시각"            , width: 80, align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);                               }},
                {name: "regUserId"            , index: "regUserId"            , label: "등록사용자ID"        , width: 80, align: "center"},
                {name: "uptTm"                , index: "uptTm"                , label: "수정시각"            , width: 80, align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);                               }},
                {name: "uptDt"                , index: "uptDt"                , label: "수정일자"            , width: 80, align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);                               }},
                {name: "uptUserId"            , index: "uptUserId"            , label: "수정사용자ID"        , width: 80, align: "center"},
                {name: "termInfoDetlPop"  , index: "termInfoDetlPop"      , label: "상세정보보기"        , width: 80, align: "center",
                    formatter: function(cellValue, options, rowObject) {
                        return '<input type="button" class="btn btn-xs btn-outline btn-success" onclick="termInfoMng.regTermInfoMngPop(\'' + rowObject.termDivCd + '\')" value="상세보기" data-toggle="modal" data-target="#termInfoDetlPopup" />';
                    }
                }
            ];



            $("#grid_list").jqGrid("GridUnload");
            $("#grid_list").jqGrid($.extend(true, {}, commonGridOptions(), {
                datatype: "local",
                mtype: 'post',
                url: '/cmon/stnd/termInfoMng/searchTermInfoList.ab',
                pager: '#grid_pager_list',
                height: 405,
                colModel: colModels,
                onPaging : function(data) {
                    onPagingCommon(data, this, function(resultMap) {
                        $this.params.currentPage  = resultMap.currentPage;
                        $this.params.rowCount     = resultMap.rowCount;
                        $this.params.currentIndex = resultMap.currentIndex;
                        $this.searchTermInfoList(false);
                    })
                }
            }));

            resizeJqGridWidth("grid_list", "grid_list_wrapper");
        },
        searchTermInfoList: function(isSearch) {

            let $this = this;
            let params = $.extend(true, {}, $this.params);
            if ( isSearch ) {
                params.currentPage = 1;
                params.currentIndex = 0;
            }

            $("#grid_list").setGridParam({
                datatype: "json",
                postData: JSON.stringify(params),
                page: 1,
                loadComplete: function (response) {
                    if ( response.rtnData.result == 0 ) {
                        Swal.alert(['조회할 내용이 없습니다.', "info"]);
                    }
                }
            }).trigger("reloadGrid");

        },

        regTermInfoMngPop: function(termDivCd) {
            termInfoDetl.initPage(termDivCd);
        },
        downloadExcel : function()
        {
            let $this = this;
            let params = $.extend(true, {}, $this.params);

            AjaxUtil.post({
                dataType: 'binary',
                url: "/cmon/stnd/termInfoMng/searchTermInfoList/excel.ab",
                param: params,
                success: function(response)
                {
                    saveFileLocal(response, 'termInfoMng.xls');
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
                termDivCd:'',
                termVer:'',
                aplyStrtDt:'',
                termCntn:'',
                essnYn:'',
                sortOrd:'',
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
