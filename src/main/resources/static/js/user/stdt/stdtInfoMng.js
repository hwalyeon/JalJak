let stdtInfoMng = new Vue({
    el: "#stdtInfoMng",
    data: {
        params: {
            entrDtFr       : '' ,  //가입_일자_From
            entrDtTo       : '' ,  //가입_일자_To
            entrDt:'',
            guarNo:'',
            guarNm:'',
            telNo:'',
            sposNm:'',
            stdtNo:'',
            stdtNm:'',
            plcClssCd:'',
            locNm:'',
            dgemStatCd:'',
            strsIdx:'',
            growIdx:'',
            fatIdx:'',
            bandId:'',
            telNo:'',
            blthId:'',
            sexCd:'',
            bithDt:'',
            locNo:'',
            locNm:'',
            bithDt:'',
            bandStatCd:'',
            mentStrsStatCd:'',
            physStrsStatCd:'',
            useCbeeAmt:'',
            cbeeBal:'',
            mmDd:'THIS_MONTH',
            paging: 'Y',
            totalCount: 0,
            rowCount: 30,
            currentPage: 1,
            currentIndex: 0
        },
        code:{
            mentGrowJudgCdList : [],
            mmDdList :           []
        },
    },


    methods: {

        initialize: function() {

            let $this = this;

            $this.initValue();

            $this.initCodeList();

            $this.initGrid();

            $this.searchGrowJudgList(true);

        },
        initValue: function() {
            let $this = this;
            $this.code.mmDdList = CodeUtil.getPeriodDateList();
            const terms = getPeriodDate($this.params.mmDd);
            this.params.entrDtFr = terms.strDt;
            this.params.entrDtTo = terms.endDt;
        },
        mmDdSelect: function()
        {
            let $this = this;
            const terms = getPeriodDate($this.params.mmDd);
            this.params.entrDtFr = terms.strDt;
            this.params.entrDtTo = terms.endDt;
        },
        initCodeList: function() {
            let $this = this;
            getCommonCodeList('GROW_JUDG_CD',$this.code.mentGrowJudgCdList);
        },
        initGrid: function()
        {
            let $this = this;
            let colModels = [
                {name: "crud"              , index: "crud"              , label: "crud"		 	    , hidden: true                },
                {name: "guarNoTemp"        , index: "guarNoTemp"        , label: "보호자번호"			, width: 80 , align: "center" , hidden: true},
                {name: "entrDt"            , index: "entrDt"            , label: "가입일자"		 	, width: 80 , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);}, fixed: true},
                {name: "guarNo"            , index: "guarNo"            , label: "학부모번호"		 	, width: 80 , align: "center" , fixed: true},
                {name: "guarNm"            , index: "guarNm"            , label: "학부모명"		 	, width: 80 , align: "center" , fixed: true},
                {name: "sposNm"            , index: "sposNm"            , label: "학부모명(배우자)" 	, width: 100 , align: "center" , fixed: true},
                {name: "stdtNo"            , index: "stdtNo"            , label: "학생번호"		 	, width: 80 , align: "center" , fixed: true},
                {name: "stdtNm"            , index: "stdtNm"            , label: "학생명"		 	, width: 80 , align: "center" , fixed: true},
                {name: "telNo"             , index: "telNo"             , label: "전화번호"		 	, width: 100 , align: "center"
                    ,formatter:function(cellValue, options, rowObject)   { return phoneFormatter(cellValue);}, fixed: true},
                {name: "plcClssCd"         , index: "plcClssCd"         , label: "현재위치분류"		, width: 80  , align: "center" , fixed: true},
                {name: "currLoc"           , index: "currLoc"           , label: "현재위치(주소)"	    , width: 250 , align: "center" , fixed: true},
                {name: "dgemStatCd"        , index: "dgemStatCd"        , label: "위험감정상태"	 	, width: 80  , align: "center" , fixed: true},
                {name: "strsIdx"           , index: "strsIdx"           , label: "스트레스상태"   	, width: 80  , align: "center" , fixed: true},
                {name: "growIdx"           , index: "growIdx"           , label: "성장상태"		 	, width: 80  , align: "center" , fixed: true},
                {name: "fatIdx"            , index: "fatIdx"            , label: "비만상태"		 	, width: 80  , align: "center" , fixed: true},
                {name: "bandId"            , index: "bandId"            , label: "밴드ID"		 	, width: 80  , align: "center" , fixed: true},
                {name: "telNo"             , index: "telNo"             , label: "전화번호"		 	, width: 100 , align: "center"
                    ,formatter:function(cellValue, options, rowObject)   { return phoneFormatter(cellValue);}    , fixed: true},
                {name: "blthId"            , index: "blthId"            , label: "블루투스ID"	 	, width: 100 , align: "center" , fixed: true},
                {name: "sexCd"             , index: "sexCd"             , label: "성별"  		 	, width: 80  , align: "center" , fixed: true},
                {name: "bithDt"            , index: "bithDt"            , label: "생년월일"		 	, width: 80  , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);}        , fixed: true},
                {name: "locNo"             , index: "locNo"             , label: "소속학교번호"	 	, width: 100 , align: "center" , fixed: true},
                {name: "locNm"             , index: "locNm"             , label: "소속학교명"		 	, width: 100 , align: "center" , fixed: true},
                {name: "age"               , index: "age"               , label: "(만)나이"  		, width: 80  , align: "center" , fixed: true},
                {name: "bandStatCd"        , index: "bandStatCd"        , label: "밴드상태"		 	, width: 80  , align: "center" , fixed: true},
                {name: "mentStrsStatCd"    , index: "mentStrsStatCd"    , label: "신체적스트레스상태" 	, width: 110 , align: "center" , fixed: true},
                {name: "physStrsStatCd"    , index: "physStrsStatCd"    , label: "정신적스트레스상태" 	, width: 110 , align: "center" , fixed: true},
                {name: "useTotal"          , index: "useTotal"          , label: "캐시비 적립 총액"	, width: 125 , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return numberFormat(cellValue);}        , fixed: true},
                {name: "saveTotal"         , index: "saveTotal"         , label: "캐시비 사용 금액" 	, width: 110 , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return numberFormat(cellValue);}        , fixed: true},
                {name: "cbeeBal"           , index: "cbeeBal"           , label: "캐시비 현재 잔액" 	, width: 110 , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return numberFormat(cellValue);}        , fixed: true},
                {name: "regDt"             , index: "regDt"             , label: "등록일자"           , width: 80  , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);} , hidden: true },
                {name: "regTm"             , index: "regTm"             , label: "등록시각"           , width: 80 , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);} , hidden: true },
                {name: "regUserId"         , index: "regUserId"         , label: "등록사용자ID"       , width: 80 , align: "center"  , hidden: true},
                {name: "uptDt"             , index: "uptDt"             , label: "수정일자"           , width: 80 , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);} , hidden: true },
                {name: "uptTm"             , index: "uptTm"             , label: "수정시각"           , width: 80 , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);} , hidden: true },
                {name: "uptUserId"         , index: "uptUserId"         , label: "수정사용자ID"       , width: 80 , align: "center"  , hidden: true},
                {name: "prntInfoDetlPopup" , index: "prntInfoDetlPopup" , label: "상세정보보기"       , width: 80 , align: "center"  ,  fixed: true ,
                    formatter: function(cellValue, options, rowObject) {
                        return '<input type="button" class="btn btn-xs btn-outline btn-success" onclick="prntInfoMng.regPrntInfoDetlPopup(\'' + rowObject.guarNo + '\')" value="상세보기" data-toggle="modal" data-target="#prntInfoDetlPopup" />';
                    }
                }

            ];



            $("#grid_list").jqGrid("GridUnload");
            $("#grid_list").jqGrid($.extend(true, {}, commonGridOptions(), {
                datatype: "local",
                mtype: 'post',
                url: '/user/stdt/stdtInfoMng/searchStdtInfoList.ab',
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
                url: "/user/stdt/stdtInfoMng/searchStdtInfoList/excel.ab",
                param: params,
                success: function(response)
                {
                    saveFileLocal(response, 'stdtInfoMng.xls');
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
                mmDd:'THIS_MONTH',
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
