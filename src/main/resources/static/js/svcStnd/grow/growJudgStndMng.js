let growJudgStndMng = new Vue({
    el: "#growJudgStndMng",
    data: {
        params: {
            growJudgCd:'',
            gidxFr:'',
            gidxTo:'',
            smryCntn:'',
            paging: 'Y',
            totalCount: 0,
            rowCount: 30,
            currentPage: 1,
            currentIndex: 0
        },
        code:{
            mentGrowJudgCdList : []
        },
    },


    methods: {

        initialize: function() {

            let $this = this;

            $this.initCodeList();

            $this.initGrid();

            $this.searchGrowJudgList(true);


        },
        initCodeList: function() {
            let $this = this;
            getCommonCodeList('GROW_JUDG_CD',$this.code.mentGrowJudgCdList);
        },
        initGrid: function() {

            let colModels = [
                {name: "growJudgCd"         , index: "growJudgCd"       , label: "성장판정코드"   , width: 80, align: "center"},
                {name: "growJudgNm"         , index: "growJudgNm"       , label: "성장판정코드명"   , width: 80, align: "center"},
                {name: "gidxFr"             , index: "gidxFr"           , label: "성장지수_FR"     , width: 80, align: "center"},
                {name: "gidxTo"             , index: "gidxTo"           , label: "성장지수_TO"   , width: 80, align: "center"},
                {name: "smryCntn"           , index: "smryCntn"         , label: "요약내용"     , width: 80, align: "center"},
                {name: "specCntn"           , index: "specCntn"         , label: "상세내용"        , width: 80, align: "center"},
                {name: "regDt"                , index: "regDt"                , label: "등록일자"                    , width: 80          , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);                                              }},
                {name: "regTm"               , index: "regTm"               , label: "등록시각"                   , width: 80          , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);                                              }},
                {name: "regUserId"          , index: "regUserId"         , label: "등록사용자ID"            , width: 80          , align: "center"},
                {name: "uptDt"                , index: "uptDt"                , label: "수정일자"                   , width: 80          , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);                                              }},
                {name: "uptTm"               , index: "uptTm"               , label: "수정시각"                   , width: 80          , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);                                              }},
                {name: "uptUserId"          , index: "uptUserId"         , label: "수정사용자ID"            , width: 80          , align: "center"},
                {name: "growJudgStndDetlPop" , index: "growJudgStndDetlPop" , label: "상세정보보기", width: 80, align: "center",
                    formatter: function(cellValue, options, rowObject) {
                        return '<input type="button" class="btn btn-xs btn-outline btn-success" onclick="growJudgStndMng.regGrowJudgStndPop(\'' + rowObject.growJudgCd + '\')" value="상세보기" data-toggle="modal" data-target="#growJudgStndDetlPopup" />';
                    }
                }

            ];



            $("#grid_list").jqGrid("GridUnload");
            $("#grid_list").jqGrid($.extend(true, {}, commonGridOptions(), {
                datatype: "local",
                mtype: 'post',
                url: '/svcStnd/grow/growJudgStndMng/searchGrowJudgList.ab',
                pager: '#user_pager_list',
                height: 405,
                colModel: colModels,
                onPaging : function(data) {
                    onPagingCommon(data, this, function(resultMap) {
                        $this.params.currentPage  = resultMap.currentPage;
                        $this.params.rowCount     = resultMap.rowCount;
                        $this.params.currentIndex = resultMap.currentIndex;
                        $this.searchGrowJudgList(false);
                    })
                }
            }));

            resizeJqGridWidth("grid_list", "grid_list_wrapper");
        },
        searchGrowJudgList: function(isSearch) {

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
        growJudgStndNmVal:function(){
            let $this = this;
        },
        physStrsStatNmVal:function(){
            let $this = this;
        },
        regGrowJudgStndPop: function(growJudgCd) {
            growJudgStndDetl.initPage(growJudgCd);
        },
        downloadExcel : function()
        {
            let $this = this;
            let params = $.extend(true, {}, $this.params);

            AjaxUtil.post({
                dataType: 'binary',
                url: "/svcStnd/grow/growJudgStndMng/searchGrowJudgList/excel.ab",
                param: params,
                success: function(response)
                {
                    saveFileLocal(response, 'growJudgStndMng.xls');
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
                growJudgCd:'',
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
