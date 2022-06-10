let dgemStndMng = new Vue({
    el: "#dgemStndMng", //위험감정_상태_기준
    data:
    {
    	params:
        {
            userId       : ''  ,
            actDivCd     : ''  , //활동_구분_코드
            hbitStatCd   : ''  , //심박_상태_코드
            plcClssCd    : ''  , //장소_구분_코드
            tempStatCd   : ''  , //체온_상태_코드
            dgemStatCd   : ''  , //위험감정_상태_코드
    		paging       : 'Y' ,
    		totalCount   : 0   ,
            rowCount     : 30  ,
            currentPage  : 1   ,
            currentIndex : 0
        },
        code:
        {
            actDivCdList   : [] , //활동_구분_코드_리스트
        	hbitStatCdList : [] , //심박_상태_코드_리스트
            plcClssCdList  : [] , //장소_구분_코드_리스트
        	tempStatCdList : [] , //체온_상태_코드_리스트
            dgemStatCdList : [] , //위험감정_상태_코드_리스트
        },
	},
    methods:
    {
        initialize: function()
        {
        	let $this = this;

            $this.initValue();
        	$this.initCodeList();
        	$this.initGrid();
            $this.searchDgemList(true);
        },
        initValue: function()
        {
            let $this = this;
            $this.userId = SessionUtil.getUserId();
        },
        initCodeList : function()
        {
            let $this = this;
           
            getCommonCodeList('ACT_DIV_CD'  , $this.code.actDivCdList);   //활동_구분_코드_리스트          
            getCommonCodeList('HBIT_STAT_CD', $this.code.hbitStatCdList); //심박_상태_코드_리스트          
            getCommonCodeList('PLC_CLSS_CD' , $this.code.plcClssCdList);  //장소_구분_코드_리스트          
            getCommonCodeList('TEMP_STAT_CD', $this.code.tempStatCdList); //체온_상태_코드_리스트          
            getCommonCodeList('DGEM_STAT_CD', $this.code.dgemStatCdList); //위험감정_상태_코드_리스트        
          
            //위험감정상태 코드 목록 조회 공통코드의 경우 1건인 경우

            //별도 테이블의 경우 - 1건의 경우
            // $this.codeList( $this.code.dgemStatCdList, function() {
            //     $this.initGrid();
            //     $this.searchDgemList(true);
            // });
        },
        initGrid: function()
        {
        	 let $this = this;				                                                             
             
        	let colModels = [
                {name: "crud"           , index: "crud"           , label: "crud"         , hidden: true },
                {name: "cctvHistNo"   , index: "cctvHistNo"   , label: "이력번호" 	  , width: 50 , align: "center"},
                {name: "senserId"   , index: "senserId"   , label: "센서ID" 	  , width: 50 , align: "center"},
                {name: "senserNm" , index: "senserNm" , label: "센서 명" 	  , width: 50 , align: "center"},
                {name: "senserValue"  , index: "senserValue"  , label: "센서 값" 	  , width: 50 , align: "center"},
                {name: "alarmId" , index: "alarmId" , label: "알람ID" 	  , width: 50 , align: "center"},
                {name: "alarmNm" , index: "alarmNm" , label: "알람 명" , width: 50 , align: "center"},
                {name: "alarmValue"       , index: "alarmValue"       , label: "알람 값" 	  , width: 50 , align: "center"},
                {name: "regDt"          , index: "regDt"          , label: "등록일자"   	  , width: 50 , align: "center"  , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);}  },
                {name: "regTm"          , index: "regTm"          , label: "등록시각"   	  , width: 50 , align: "center"  , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);}  },
                {name: "regUserId"      , index: "regUserId"      , label: "등록사용자ID"	  , width: 50 , align: "center"  },
                {name: "uptDt"          , index: "uptDt"          , label: "수정일자"    	  , width: 50 , align: "center"  , formatter: function(cellValue, options, rowObject) { return formatDate(cellValue);}  },
                {name: "uptTm"          , index: "uptTm"          , label: "수정시각"    	  , width: 50 , align: "center"  , formatter: function(cellValue, options, rowObject) { return formatTime(cellValue);}  },
                {name: "uptUserId"      , index: "uptUserId"      , label: "수정사용자ID"	  , width: 50 , align: "center"  }
            ];
  
            $("#dgem_list").jqGrid("GridUnload");
           	$("#dgem_list").jqGrid($.extend(true, {}, commonEditGridOptions(),
            {
            	datatype : "local",
            	mtype    : 'post'  ,
                url      : '/svcStnd/dgem/dgemStndMng/searchDgemList.ab',
                pager    : '#dgem_pager_list',
				height   : 405     ,
                colModel : colModels,
                onPaging : function(data)
                {
                    onPagingCommon(data, this, function(resultMap)
                    {
                        $this.params.currentPage  = resultMap.currentPage;
                        $this.params.rowCount      = resultMap.rowCount;
                        $this.params.currentIndex = resultMap.currentIndex;
                        $this.searchDgemList(false);
                    })
                },
                afterSaveCell : function (rowid , colId , val, e )
                {

                    if($("#dgem_list").getRowData(rowid).crud != "C" && $("#dgem_list").getRowData(rowid).crud != "D" )
                    {
                        $("#dgem_list").setRowData(rowid, {crud:"U"});
                    }
                }
            }));
            resizeJqGridWidth("dgem_list", "dgem_list_wrapper");
        },
        searchDgemList: function(isSearch)
        {
			let $this     = this;
            let params = $.extend(true, {}, $this.params);
			
            if ( isSearch )
            {
                params.currentPage = 1;
                params.currentIndex = 0;
            }
            
			$("#dgem_list").setGridParam(
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
		regDgemStndDetlPop: function(actDivCd, hbitStatCd, plcClssCd, tempStatCd, dgemStatCd) {
			dgemStndDetl.initPage(actDivCd, hbitStatCd, plcClssCd, tempStatCd, dgemStatCd);
        },
		downloadExcel : function()
        {
			let $this = this;
			let params = $.extend(true, {}, $this.params);
			
			AjaxUtil.post(
{
				dataType : 'binary',
                url            : "/svcStnd/dgem/dgemStndMng/searchDgemList/excel.ab",
                param     : params,
                success: function(response)
                {
                	saveFileLocal(response, 'dgemStndMng.xls');
                },
                error: function (response)
                {
                    Swal.alert([response, 'error']);
                }
            });
		},
        /*codeList : function (arrayObject , callback)
        {
            AjaxUtil.post({
                url: "/svcStnd/dgem/dgemStndMng/searchDgemList.ab",
                param: {},
                success: function(response) {
                    var dataList = response.rtnData.result;

                    if (Array.isArray(arrayObject)) {
                        var dataCnt = dataList.length;
                        var m = 0;

                        for (m = 0; m < dataCnt; m++) {
                            arrayObject.push({
                                cdVal   : dataList[m].dgemStatCd,
                                cdNm    : dataList[m].dgemStatCntn,
                                sortOrd : 0
                            });
                        }
                    }

                    if (typeof callback === "function") {
                        callback(dataList);
                    }
                },
                error: function (response) {
                    alert(response);
                }
            });
        },*/
		resetSearchParam: function()
        {
			let $this = this;
			$this.params =
            {
		            userId       : ''  ,
		            actDivCd     : ''  , //활동_구분_코드
		            hbitStatCd   : ''  , //심박_상태_코드
		            plcClssCd    : ''  , //장소_구분_코드
		            tempStatCd   : ''  , //체온_상태_코드
		            dgemStatCd   : ''  , //위험감정_상태_코드
		    		paging       : 'Y' ,
		    		totalCount   : 0   ,
		            rowCount     : 30  ,
		            currentPage  : 1   ,
		            currentIndex : 0
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