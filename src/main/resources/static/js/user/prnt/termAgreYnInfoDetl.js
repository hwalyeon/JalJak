let termAgreYnInfoDetl = new Vue({
    el: "#termAgreYnInfoDetlPopup",
    data: {
    	params: {
    		crud           : 'C',
    		userId         : '' ,
    		guarNo         : '' , //보호자_번호
    		termDivCd      : '' , //약관_구분_코드
			termVer        : '' , //약관_버전
    		termAgreYn     : '' , //약관_동의_여부
			termAgreDttm   : '' , //약관_동의_일시
			regDT          : '' , //등록_일자
			regTm          : '' , //등록_시간
			regUserId      : '' , //등록_사용자_ID
			uptDt          : '' , //수정_일자
			uptTm          : '' , //수정_시간
			uptUserId      : '' , //수정_사용자_ID
			paging         : 'Y',
			totalCoun      : 0  ,
			rowCount       : 30 ,
			currentPage    : 1  ,
			currentIndex   : 0
    	},
		code: {
			termDivCdList  : [] , //약관_구분_코드_리스트
			agreYnList      : [{'cdVal':'Y', 'cdNm':'Y'},{'cdVal':'N', 'cdNm':'N'}]  // 여부_리스트
		}
	},
    methods: {

        initialize: function()
		{
        	let $this = this;

        	$this.initValue();
        	$this.initCodeList();
        },
        initCodeList: function()
		{
        	let $this = this;
			getCommonCodeList('TERM_DIV_CD', $this.code.termDivCdList); //약관_구분_코드_리스트
		},
		initValue()
		{
			let $this = this;
			$this.userId = SessionUtil.getUserId();
		},
		initPage: function(guarNo) {
			let $this = this;
			$this.resetTermAgreYnInfoDetl();
			$this.params.guarNo = guarNo;

			setTimeout(function() {
				$this.initGrid();
				$this.searctTermAgreYnInfoList(true);
			}, 500);

		},
		initGrid: function()
		{
			let $this = this;
			let agreYnList = commonGridCmonCd($this.code.agreYnList);
			let colModels = [
				{name: "crud"      		, index: "crud"       	  , label: "crud"		      , hidden: true },
				{name: "guarNoTemp"		, index: "guarNoTemp"	  , label: "보호자번호"		  , hidden: true },
				{name: "termDivCd"      , index: "termDivCd"      , label: "약관 구분 코드" 	  , hidden: true },
				{name: "guarNo"         , index: "guarNo"         , label: "보호자번호" 	      , width: 50 , align: "center" },
				{name: "termVer"        , index: "termVer"        , label: "약관버전" 	      , width: 30 , align: "center" },
				{name: "termDivCdNm"    , index: "termDivCdNm"    , label: "약관 구분 코드명"  , width: 70 , align: "center" },
				{name: "termAgreYn"     , index: "termAgreYn"     , label: "약관 동의 여부" 	  , width: 30 , align: "center"  , edittype : "select", formatter: "select", editable: true, editoptions: {value: agreYnList} },
				{name: "termAgreDttm" 	, index: "termAgreDttm"   , label: "약관 동의 일시" 	  , width: 50 , align: "center"  }
			];
			$("#termAgreYnInfoDetl_list").jqGrid("GridUnload");
			$("#termAgreYnInfoDetl_list").jqGrid($.extend(true, {}, commonEditGridOptions(),
				{
					datatype : "local",
					mtype    : 'post'  ,
					url      : '/user/prnt/prntInfoMng/searchTermAgreYnInfoDetlList.ab',
					pager    : '#dgem_pager_list',
					colModel : colModels,
					onPaging : function(data)
					{
						onPagingCommon(data, this, function(resultMap)
						{
							$this.params.currentPage  = resultMap.currentPage;
							$this.params.rowCount      = resultMap.rowCount;
							$this.params.currentIndex = resultMap.currentIndex;
							$this.searctTermAgreYnInfoList(false);
						})
					},
					afterSaveCell : function (rowid , colId , val, e )
					{

						if($("#termAgreYnInfoDetl_list").getRowData(rowid).crud != "C" && $("#termAgreYnInfoDetl_list").getRowData(rowid).crud != "D" )
						{
							$("#termAgreYnInfoDetl_list").setRowData(rowid, {crud:"U"});
						}
					}
				}));
			resizeJqGridWidth("termAgreYnInfoDetl_list", "termAgreYnInfoDetl_list_wrapper");
		},
		searctTermAgreYnInfoList: function(isSearch)
		{
			let $this     = this;
			let params = $.extend(true, {}, $this.params);

			if ( isSearch )
			{
				params.currentPage = 1;
				params.currentIndex = 0;
			}

			$("#termAgreYnInfoDetl_list").setGridParam(
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

		isValid: function()
		{
        	let $this = this;
        	return true;
        },
		saveTermAgreYnInfoDetl: function() {

			let $this = this;

            if ( !this.isValid() ) {
                return false;
            }

			AjaxUtil.post({
                url: "/user/prnt/prntInfoMng/saveTermAgreYnInfoDetl.ab",
                param: $this.params,
                success: function(response) {
                	Swal.alert(['저장이 완료되었습니다.', 'success']).then(function() {
                		closeModal($('#termAgreYnInfoDetlPopup'));
						prntInfoMng.searchPrntInfoList(true);
                	});
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
		},

		deleteUser: function() {

			let $this = this;

			$this.params.crud = 'D';

            AjaxUtil.post({
                url: "/user/prnt/prntInfoMng/saveTermAgreYnInfoDetl.ab",
                param: $this.params,
                success: function(response) {
                	Swal.alert(['삭제가 완료되었습니다.', 'success']).then(function() {
                		 closeModal($('#termAgreYnInfoDetlPopup'));
						prntInfoMng.searchPrntInfoList(true);
                	});
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
		},
		resetTermAgreYnInfoDetl: function() {
			this.params = {
				crud           : 'C',
				userId         : '' ,
				guarNo         : '' , //보호자_번호
				termDivCd      : '' , //약관_구분_코드
				termVer        : '' , //약관_버전
				termAgreYn     : '' , //약관_동의_여부
				termAgreDttm   : '' , //약관_동의_일시
				regDT          : '' , //등록_일자
				regTm          : '' , //등록_시간
				regUserId      : '' , //등록_사용자_ID
				uptDt          : '' , //수정_일자
				uptTm          : '' , //수정_시간
				uptUserId      : '' , //수정_사용자_ID
				paging         : 'Y',
				totalCoun      : 0  ,
				rowCount       : 30 ,
				currentPage    : 1  ,
				currentIndex   : 0
	    	}
		},
    },
    computed:
	{
    },
    watch:
	{
    },
    mounted: function() {
        let self = this;
        $(document).ready(function() {
            self.initialize();
        });
    }
});
