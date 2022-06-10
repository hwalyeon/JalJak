let foodInfoMng = new Vue({
    el: "#foodInfoMng",
    data: {
    	params: {
            foodNo:'',
            foodLclsNm:'',
            foodMclsNm:'',
            foodNm:'',
            otimEatQty:'',
            eatUnitCd:'',
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
            mentFoodInfoCdList : []
        },
	},

	
    methods: {

        initialize: function() {
        	
        	let $this = this;
        	
        	$this.initCodeList();
        	
        	$this.initGrid();

        	$this.searchFoodInfoList(true);

        	
        },
        initCodeList: function() {
            let $this = this;
            getCommonCodeList('FOOD_NO',$this.code.mentFoodInfoCdList);
        },
        initGrid: function() {
            let $this = this;
        	let colModels = [
                {name: "buildId"     , index: "buildId"   , label: "건물_ID"   , width: 80, align: "center"},
                {name: "flValue"     , index: "flValue"   , label: "층수"   , width: 80, align: "center"},
                {name: "sencerId"     , index: "sencerId"   , label: "센서_ID"   , width: 80, align: "center"},
                {name: "sencerValue"     , index: "sencerValue"   , label: "센서_값"   , width: 80, align: "center"},
                {name: "alarmId"     , index: "alarmId"   , label: "알람_ID"   , width: 80, align: "center"},
                {name: "alarmNm"     , index: "alarmNm"   , label: "알람_명"   , width: 80, align: "center"},
                {name: "alarmValue"     , index: "alarmValue"   , label: "알람_값"   , width: 80, align: "center"},
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

        	console.log("1");
  
            $("#user_list").jqGrid("GridUnload");
           	$("#user_list").jqGrid($.extend(true, {}, commonGridOptions(), {
            	datatype: "local",
            	mtype: 'post',
                url: '/svcStnd/food/foodInfoMng/searchFoodInfoList.ab',
                pager: '#user_pager_list',
				height: 405,
                colModel: colModels,
                onPaging : function(data) {

                    onPagingCommon(data, this, function(resultMap) {
                        $this.params.currentPage  = resultMap.currentPage;
                        $this.params.rowCount     = resultMap.rowCount;
                        $this.params.currentIndex = resultMap.currentIndex;
                        $this.searchFoodInfoList(false);
                    })
                }
            }));
            console.log("2");
            resizeJqGridWidth("user_list", "user_list_wrapper");                        
        },
        searchFoodInfoList: function(isSearch) {
            console.log("3");
			let $this = this;
            let params = $.extend(true, {}, $this.params);
            console.log("4");
            if ( isSearch ) {
                params.currentPage = 1;
                params.currentIndex = 0;
            }
            
			$("#user_list").setGridParam({
                datatype: "json",
                postData: JSON.stringify(params),
                page: 1,
                loadComplete: function (response) {
                    console.log(response.rtnData.result);
                    if ( response.rtnData.result == 0 ) {
                        Swal.alert(['조회할 내용이 없습니다.', "info"]);
                    }
                }
            }).trigger("reloadGrid");
            console.log("5");
		},
        mentFoodInfoNmVal:function(){
            let $this = this;
        },

        downloadExcel : function()
        {
            let $this = this;
            let params = $.extend(true, {}, $this.params);

            AjaxUtil.post({
                dataType: 'binary',
                url: "/svcStnd/food/foodInfoMng/searchFoodInfoList/excel.ab",
                param: params,
                success: function(response)
                {
                    saveFileLocal(response, 'foodInfoMng.xls');
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
                foodNo:'',
                foodLclsNm:'',
                foodMclsNm:'',
                foodNm:'',
                otimEatQty:'',
                eatUnitCd:'',
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