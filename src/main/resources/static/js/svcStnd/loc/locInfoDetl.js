let locInfoDetl = new Vue({
    el: "#locInfoDetl",
    data:
        {
            params:
                {
                    growStndVer : ''  ,    // 성장_기준_번호
                    ageYcnt         : ''  ,     // 나이_년수
                    sexCd            : ''   ,    // 성별_코드
                    paging          : 'Y',
                    totalCount    : 0  ,
                    rowCount     : 30,
                    currentPage : 1  ,
                    currentIndex: 0
                },
            code:
                {
                    growStndVerList : [],  // 성장_기준_버전_리스트
                    ageYcntList         : [],  // 나이_년수_리스트
                    sexCdList             : [{cdVal:'MALE', cdNm:'남성'},{cdVal:'FEMALE', cdNm:'여성'}] // 성별_리스트
                },
        },
    methods:
        {
            initialize: function()
            {
                let $this = this;

                $this.initCodeList();

                $this.initGrid();

                $this.searchGrowStndList(true);
            },
            initCodeList : function()
            {

                let $this = this;
                // 나이_년수_리스트_조회
                AjaxUtil.post({
                    url: "/svcStnd/grow/growStndMng/ageYcntList.ab",
                    param: {},
                    success: function(response) {

                        $this.code.ageYcntList = [];
                        if ( !!response.rtnData.result && response.rtnData.result.length > 0 ) {
                            $.each(response.rtnData.result, function(index, item) {
                                $this.code.ageYcntList.push({'cdVal':item.ageYcnt});
                            });
                        }
                    },
                    error: function (response) {
                        Swal.alert([response, 'error']);
                    }
                });

                // 성장_기준_버전_리스트_조회
                AjaxUtil.post({
                    url: "/svcStnd/grow/growStndMng/growStndVerList.ab",
                    param: {},
                    success: function(response) {
                        $this.code.growStndVerList = [];
                        if ( !!response.rtnData.result && response.rtnData.result.length > 0 ) {
                            $.each(response.rtnData.result, function(index, item) {
                                $this.code.growStndVerList.push({'cdVal':item.growStndVer});
                            });
                        }
                    },
                    error: function (response)
                    {
                        Swal.alert([response, 'error']);
                    }
                });
            },
            initGrid: function()
            {  
                let $this = this;
                let colModels =
                [
                    {name: "growStndVer"     , index: "growStndVer"     , label: "성장기준버전"          , width: 80         , align: "center"},
                    {name: "growStndNo"     , index: "growStndNo"      , label: "성장기준번호"          , width: 80         , align: "center"},
                    {name: "sexCd"                , index: "sexCd"                 , label: "성별코드"                  , width: 80          , align: "center",  hidden:true},
                    {name: "fnGetcdnm"       , index: "fnGetcdnm"         , label: "성별"                          , width: 80          , align: "center"},
                    {name: "ageYcnt"            , index: "ageYcnt"              , label: "나이(년)"                    , width: 80          , align: "center"},
                    {name: "ageMcnt"           , index: "ageMcnt"            , label: "나이(개월)"                , width: 80          , align: "center"},
                    {name: "p3Gidx"              , index: "p3Gidx"              , label: "백분위3 성장지수"     , width: 80          , align: "center"},
                    {name: "p50Gidx"            , index: "p50Gidx"            , label: "백분위50 성장지수"   , width: 80          , align: "center"},
                    {name: "p97Gidx"            , index: "p97Gidx"            , label: "백분위97 성장지수"   , width: 80          , align: "center"},
                    {name: "regDt"                , index: "regDt"                , label: "등록일자"                    , width: 80          , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);                                              }},
                    {name: "regTm"               , index: "regTm"               , label: "등록시각"                   , width: 80          , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);                                              }},
                    {name: "regUserId"          , index: "regUserId"         , label: "등록사용자ID"            , width: 80          , align: "center"},
                    {name: "uptDt"                , index: "uptDt"                , label: "수정일자"                   , width: 80          , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);                                              }},
                    {name: "uptTm"               , index: "uptTm"               , label: "수정시각"                   , width: 80          , align: "center"
                    , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);                                              }},
                    {name: "uptUserId"          , index: "uptUserId"         , label: "수정사용자ID"            , width: 80          , align: "center"}
                ];

                $("#growStnd_list").jqGrid("GridUnload");
                $("#growStnd_list").jqGrid($.extend(true, {}, commonGridOptions(),
                {
                    datatype  : "local",
                    mtype      : 'post',
                    url            : '/svcStnd/grow/growStndMng/searchGrowStndList.ab',
                    pager       : '#growStnd_pager_list',
                    height      : 405,
                    colModel : colModels,
                    onPaging : function(data) {
                        onPagingCommon(data, this, function(resultMap)
                        {
                            $this.params.currentPage  = resultMap.currentPage;
                            $this.params.rowCount     = resultMap.rowCount;
                            $this.params.currentIndex = resultMap.currentIndex;
                            $this.searchGrowStndList(false);
                        })
                    }
                }));
                resizeJqGridWidth("growStnd_list", "growStnd_list_wrapper");
            },
            searchGrowStndList: function(isSearch)
            {
                let $this = this;
                let params = $.extend(true, {}, $this.params);

                if ( isSearch )
                {
                    params.currentPage = 1;
                    params.currentIndex = 0;
                }

                $("#growStnd_list").setGridParam(
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
            downloadExcel : function()
            {
                let $this = this;
                let params = $.extend(true, {}, $this.params);

                AjaxUtil.post(
                    {
                        dataType: 'binary',
                        url: "/svcStnd/grow/growStndMng/searchGrowStndList/excel.ab",
                        param: params,
                        success: function(response)
                        {
                            saveFileLocal(response, 'growStndMng.xls');
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
                    growStndVer : ''  ,    // 성장_기준_번호
                    ageYcnt         : ''  ,     // 나이_년수
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