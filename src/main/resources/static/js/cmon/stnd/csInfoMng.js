let csInfoMng = new Vue({
    el: "#csInfoMng",
    data: {
        params: {
            regNo:'',
            csTelNo:'',
            csMailAddr:'',
            csUrl:'',
            bandHpgeUrl:'',
            regDT:'',
            regTm:'',
            regUserId:'',
            uptDt:'',
            uptTm:'',
            uptUserId:'',
            paging: 'Y',
            totalCount: 0,
            rowCount: 30,
            currentPage: 1,
            currentIndex: 0
        },
        code:{
            csInfoList : []
        },
    },


    methods: {

        initialize: function() {

            let $this = this;

            $this.initCodeList();

            $this.initGrid();

            $this.searchCsInfoList(true);


        },
        initCodeList: function() {
            let $this = this;
        },
        initGrid: function() {

            let colModels = [
                {name: "regNo"                , index: "regNo"                , label: "등록번호"            , width: 80, align: "center"},
                {name: "csTelNo"              , index: "csTelNo"              , label: "고객지원 전화번호"     , width: 80, align: "center"},
                {name: "csMailAddr"           , index: "csMailAddr"           , label: "고객지원 메일주소"     , width: 80, align: "center"},
                {name: "csUrl"                , index: "csUrl"                , label: "고객지원 URL"        , width: 80, align: "center"},
                {name: "bandHpgeUrl"          , index: "bandHpgeUrl"          , label: "밴드 홈페이지 URL"    , width: 80, align: "center"},
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
                {name: "csInfoDetlPopup"      , index: "csInfoDetlPopup"      , label: "상세정보보기"         , width: 80, align: "center",
                    formatter: function(cellValue, options, rowObject) {
                        return '<input type="button" class="btn btn-xs btn-outline btn-success" onclick="csInfoMng.regCsInfoMngPop(\'' + rowObject.regNo + '\')" value="상세보기" data-toggle="modal" data-target="#csInfoDetlPopup" />';
                    }
                }
            ];



            $("#grid_list").jqGrid("GridUnload");
            $("#grid_list").jqGrid($.extend(true, {}, commonGridOptions(), {
                datatype: "local",
                mtype: 'post',
                url: '/cmon/stnd/csInfoMng/searchCsInfoList.ab',
                pager: '#user_pager_list',
                height: 405,
                colModel: colModels,
                onPaging : function(data) {
                    onPagingCommon(data, this, function(resultMap) {
                        $this.params.currentPage  = resultMap.currentPage;
                        $this.params.rowCount     = resultMap.rowCount;
                        $this.params.currentIndex = resultMap.currentIndex;
                        $this.searchCsInfoList(false);
                    })
                }
            }));

            resizeJqGridWidth("grid_list", "grid_list_wrapper");
        },
        searchCsInfoList: function(isSearch) {

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

        regCsInfoMngPop: function(regNo) {
            csInfoDetl.initPage(regNo);
        },
        downloadExcel : function()
        {
            let $this = this;
            let params = $.extend(true, {}, $this.params);

            AjaxUtil.post({
                dataType: 'binary',
                url: "/cmon/stnd/csInfoMng/searchCsInfoList/excel.ab",
                param: params,
                success: function(response)
                {
                    saveFileLocal(response, 'csInfoMng.xls');
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
                regNo:'',
                csTelNo:'',
                csMailAddr:'',
                csUrl:'',
                bandHpgeUrl:'',
                regDT:'',
                regTm:'',
                regUserId:'',
                uptDt:'',
                uptTm:'',
                uptUserId:'',
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
