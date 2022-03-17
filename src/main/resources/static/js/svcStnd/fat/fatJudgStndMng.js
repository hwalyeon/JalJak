let fatJudgStndMng = new Vue({
    el: "#fatJudgStndMng",
    data: {
        params: {
            fatJudgCd:'',
            fatJudgNm:'',
            fidxFr:'',
            fidxTo:'',
            currEvalCntn:'',
            prdtEvalCntn:'',
            regDt:'',
            regTm:'',
            regUserId:'',
            uptDt:'',
            uptTm:'',
            uptUserId:'',
            useYn:'Y',
            paging: 'Y',
            totalCount: 0,
            rowCount: 30,
            currentPage: 1,
            currentIndex: 0
        },
        code:{
            mentFatJudgCdList : []
        },
    },


    methods: {

        initialize: function() {

            let $this = this;

            $this.initCodeList();

            $this.initGrid();

            $this.searchFatJudgList(true);


        },
        initCodeList: function() {
            let $this = this;
            getCommonCodeList('FAT_JUDG_CD',$this.code.mentFatJudgCdList);
        },
        initGrid: function() {

            let colModels = [
                {name: "fatJudgCd"          , index: "fatJudgCd"        , label: "비만판정코드"           , width: 80, align: "center"},
                {name: "fatJudgNm"          , index: "fatJudgNm"        , label: "비만판정코드명"         , width: 80, align: "center"},
                {name: "bmiFr"              , index: "bmiFr"            , label: "BMI_FR"          , width: 80, align: "center"},
                {name: "bmiTo"              , index: "bmiTo"            , label: "BMI_TO"            , width: 80, align: "center"},
                {name: "fidxFr"             , index: "fidxFr"           , label: "비만지수_FR"          , width: 80, align: "center"},
                {name: "fidxTo"             , index: "fidxTo"           , label: "비만지수_TO"            , width: 80, align: "center"},
                {name: "currEvalCntn"       , index: "currEvalCntn"     , label: "현재평가내용"           , width: 80, align: "center"},
                {name: "prdtEvalCntn"       , index: "prdtEvalCntn"     , label: "예측평가내용"           , width: 80, align: "center"},
                {name: "regDt"              , index: "regDt"            , label: "등록일자"              , width: 80, align: "center", formatter: function(cellValue, options, rowObject)
                    { return formatDate(cellValue); }},
                {name: "regTm", index: "regTm", label: "등록시각", width: 80, align: "center", formatter: function(cellValue, options, rowObject)
                    { return formatTime(cellValue); }},
                {name: "regUserId"          , index: "regUserId"        , label: "등록사용자ID"          , width: 80, align: "center"},
                {name: "uptDt", index: "uptDt", label: "수정일자", width: 80, align: "center", formatter: function(cellValue, options, rowObject)
                    { return formatDate(cellValue); }},
                {name: "uptTm", index: "uptTm", label: "수정시각", width: 80, align: "center", formatter: function(cellValue, options, rowObject)
                    { return formatTime(cellValue); }},
                {name: "uptUserId"          , index: "uptUserId"        , label: "수정사용자ID"          , width: 80, align: "center"},
                {name: "fatJudgStndDetlPop" , index: "fatJudgStndDetlPop" , label: "상세정보보기", width: 80, align: "center",
                    formatter: function(cellValue, options, rowObject) {
                        return '<input type="button" class="btn btn-xs btn-outline btn-success" onclick="fatJudgStndMng.regFatJudgStndPop(\'' + rowObject.fatJudgCd + '\')" value="상세보기" data-toggle="modal" data-target="#fatJudgStndDetlPopup" />';
                    }
                }



            ];

            $("#grid_list").jqGrid("GridUnload");
            $("#grid_list").jqGrid($.extend(true, {}, commonGridOptions(), {
                datatype: "local",
                mtype: 'post',
                url: '/svcStnd/fat/fatJudgStndMng/searchFatJudgList.ab',
                pager: '#grid_pager_list',
                height: 405,
                colModel: colModels,
                onPaging : function(data) {
                    onPagingCommon(data, this, function(resultMap) {
                        $this.params.currentPage  = resultMap.currentPage;
                        $this.params.rowCount     = resultMap.rowCount;
                        $this.params.currentIndex = resultMap.currentIndex;
                        $this.searchFatJudgList(false);
                    })
                }
            }));

            resizeJqGridWidth("grid_list", "grid_list_wrapper");
        },
        searchFatJudgList: function(isSearch) {

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
        mentJudgStndNmVal:function(){
            let $this = this;
        },

        regFatJudgStndPop: function(fatJudgCd) {
            fatJudgStndDetl.initPage(fatJudgCd);
        },

        downloadExcel : function()
        {
            let $this = this;
            let params = $.extend(true, {}, $this.params);

            AjaxUtil.post({
                dataType: 'binary',
                url: "/svcStnd/fat/fatJudgStndMng/searchFatJudgList/excel.ab",
                param: params,
                success: function(response)
                {
                    saveFileLocal(response, 'fatJudgStndMng.xls');
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
                mentStrsStatCd:'',
                physStrsStatCd:'',
                blngNm: '',
                telNo: '',
                mtelNo: '',
                mailAddr: '',
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