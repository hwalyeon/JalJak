let cdMng = new Vue({
    el: "#cdMng",
    data: {
		params : {
			cdGrp : {
				cdGrp: '',
				cdGrpNm: '',
				paging: 'Y',
	            totalCount: 0,
	            rowCount: 30,
	            currentPage: 1,
	            currentIndex: 0
			},
			cdSpec: {
				cdGrp: '',
				cdGrpNm: '',
				paging: 'Y',
	            totalCount: 0,
	            rowCount: 30,
	            currentPage: 1,
	            currentIndex: 0
			}
		},
		code : {

		}
	},
	
    methods: {

        initialize: function() {
        	
        	let $this = this;
        	
        	$this.initCodeList();
        	
        	$this.initGrid();
        	
        	$this.searchCdGrpList(true);

		},
		initCodeList : function() {
			
			let $this = this;
		},
		initGrid: function() {
			
			let $this = this;
			
			let cdGrpColModels = [
                {name: "cdGrp"       , index: "cdGrp"       , label: "코드그룹"  , width: 100 , align: "center"},
                {name: "cdGrpNm"     , index: "cdGrpNm"     , label: "코드그룹명" , width: 100, align: "left"  },
                {name: "useYn"       , index: "useYn"       , label: "사용여부"  , width: 50 , align: "center"},
                {name: "cdGrpDetlPop", index: "cdGrpDetlPop", label: "코드그룹 정보보기", width: 70, align: "center",
                    formatter: function(cellValue, options, rowObject) {
                        return '<input type="button" class="btn btn-xs btn-outline btn-success" onclick="cdMng.regCdGrpPop(\'' + rowObject.cdGrp + '\')" value="상세보기" data-toggle="modal" data-target="#cdGrpDetlPopup" />';
                    }
                }
            ];
                
            $("#cdGrp_list").jqGrid("GridUnload");
           	$("#cdGrp_list").jqGrid($.extend(true, {}, commonGridOptions(), {
            	datatype: "local",
                mtype: 'post',
                height: 450,
                url: '/set/cdMng/searchCdGrpList.ab',
                pager: "#cdGrp_pager_list",
                colModel: cdGrpColModels,
                onPaging : function(data) {
                    onPagingCommon(data, this, function(resultMap) {
                    	$this.params.cdGrp.currentPage  = resultMap.currentPage;
                    	$this.params.cdGrp.rowCount     = resultMap.rowCount;
                    	$this.params.cdGrp.currentIndex = resultMap.currentIndex;
                    	$this.searchCdGrpList(false);
                    })
                },
                onSelectRow: function(rowId, status, e) {
                	let item = $('#cdGrp_list').jqGrid('getRowData', rowId);
                    if ( !!item.cdGrp )
                    {
                    	$this.params.cdSpec.cdGrp   = item.cdGrp;
                    	$this.params.cdSpec.cdGrpNm = item.cdGrpNm;
                    	$this.searchCdSpecList(true);
                    }
                },
                gridComplete: function () {
                    var grid = this;

                    $('td[rowspan="1"]', grid).each(function () {
                        var spans = $('td[rowspanid="' + this.id + '"]', grid).length + 1;

                        if (spans > 1) {
                            $(this).attr('rowspan', spans);
                        }
                    });
                }
            }));

            resizeJqGridWidth("cdGrp_list", "cdGrp_list_wrapper");
            
  
            let cdSpecColModels = [
            	{name: "cdGrp"        , index: "cdGrp"        , label: "코드그룹"   , width: 90 , align: "center"},
                {name: "cdGrpNm"  , index: "cdGrpNm"      , label: "코드그룹명" , width: 90, align: "left"},
                {name: "cdVal"        , index: "cdVal"        , label: "코드값"    , width: 90 , align: "center"},
                {name: "cdNm"         , index: "cdNm"         , label: "코드명"    , width: 90, align: "left"  },
                {name: "cdDesc"         , index: "cdDesc"         , label: "코드내용"    , width: 100, align: "left"  },
                {name: "fltrVal1"         , index: "fltrVal1"         , label: "필터값1"    , width: 60, align: "left"  },
                {name: "fltrVal2"         , index: "fltrVal2"         , label: "필터값2"    , width: 60, align: "left"  },
                {name: "fltrVal3"         , index: "fltrVal3"         , label: "필터값3"    , width: 60, align: "left"  },
                {name: "sortOrd"      , index: "sortOrd"      , label: "정렬순서"   , width: 50 , align: "center"},
                {name: "useYn"        , index: "useYn"        , label: "사용여부"   , width: 50 , align: "center"},
                {name: "cdSpecDetlPop", index: "cdSpecDetlPop", label: "코드 정보보기", width: 70, align: "center",
                    formatter: function(cellValue, options, rowObject) {
                    	return '<input type="button" class="btn btn-xs btn-outline btn-success" onclick="cdMng.regCdSpecPop(\'' + rowObject.cdGrp + '\',\'' + rowObject.cdVal + '\')" value="상세보기" data-toggle="modal"/>';
                    }
                }
            ];
                
            $("#cdSpec_list").jqGrid("GridUnload");
           	$("#cdSpec_list").jqGrid($.extend(true, {}, commonGridOptions(), {
                mtype: 'post',
                height: 450,
                datatype: "local",
                url: '/set/cdMng/searchCdSpecList.ab',
                pager: "#cdSpec_pager_list",
                colModel: cdSpecColModels,
                onPaging : function(data) {
                    onPagingCommon(data, this, function(resultMap) {
                    	$this.params.cdSpec.currentPage  = resultMap.currentPage;
                    	$this.params.cdSpec.rowCount     = resultMap.rowCount;
                    	$this.params.cdSpec.currentIndex = resultMap.currentIndex;
                    	$this.searchCdSpecList(false);
                    })
                },
                loadComplete: function(datas) {
                	var grid = this;
                    $('td[rowspan="1"]', grid).each(function () {
                    	var spans = $('td[rowspanid="' + this.id + '"]', grid).length + 1;
                    	if (spans > 1) {
                    		$(this).attr('rowspan', spans);
                    	}
                    });
                }
            }));
           	
            resizeJqGridWidth("cdSpec_list", "cdSpec_list_wrapper");
            
		},
		searchCdGrpList: function(isSearch) {
			
			let $this = this;
			
			let params = $.extend(true, {}, this.params.cdGrp);
			
            if ( isSearch ) {
                params.currentPage  = 1;
                params.currentIndex = 0;
            }
            
            $this.params.cdSpec = {
				cdGrp: '',
				paging: 'Y',
	            totalCount: 0,
	            rowCount: 30,
	            currentPage: 1,
	            currentIndex: 0
			}
            $("#cdSpec_list").clearGridData();
            
            
			$("#cdGrp_list").setGridParam({
                datatype: "json",
                postData: JSON.stringify(params),
                page: 1,
                loadComplete: function(response) {
                    if ( response.rtnData.result == 0 ) {
                        Swal.alert(["데이터가 없습니다.", "info"]);
                    }
                }
            }).trigger("reloadGrid");
		},
		searchCdSpecList : function(isSearch) {
			
			let $this = this;
			
			let params = $.extend(true, {}, this.params.cdSpec);
			
            if ( isSearch ) {
                params.currentPage  = 1;
                params.currentIndex = 0;
            }
            
            $("#cdSpec_list").setGridParam({
                datatype: "json",
                postData: JSON.stringify(params),
                page: 1,
                loadComplete: function(response) {
                    if ( response.rtnData.result == 0 ) {
                        Swal.alert(["데이터가 없습니다.", "info"]);
                    }
                }
            }).trigger("reloadGrid");
		},
		downloadExcelCdGrp : function() {
			
			let $this = this;
			
			let params = $.extend(true, {}, $this.params.cdGrp);
			
			AjaxUtil.post({
				dataType: 'binary',
                url: "/set/cdMng/searchCdGrpList/excel.ab",
                param: params,
                success: function(response) {
                	saveFileLocal(response, 'CodeGroup.xls');
                },
                error: function (response) {
                    Swal.alert([response, 'error']);
                }
            });
		},
		downloadExcelCdSpec : function() {
			
			let $this = this;
			
			let params = $.extend(true, {}, $this.params.cdSpec);
			
			AjaxUtil.post({
				dataType: 'binary',
                url: "/set/cdMng/searchCdSpecList/excel.ab",
                param: params,
                success: function(response) {
                	saveFileLocal(response, 'CodeSpec.xls');
                },
                error: function (response) {
                    Swal.alert([response, 'error']);
                }
            });
		},
		regCdGrpPop: function(cdGrp) {
			
			cdGrpDetl.initPage(cdGrp);
		},
		regCdSpecPop: function(cdGrp, cdVal) {
			
			let $this = this;
			
			if ( WebUtil.isNull($this.params.cdSpec.cdGrp) && WebUtil.isNull(cdGrp) )
			{
				Swal.alert(['코드그룹을 먼저 조회하시기 바랍니다.', 'info']);
			}
			else
			{
				let crud = 'U';
				let cdGrpNm = '';
				if ( WebUtil.isNull(cdGrp) ) {
					crud    = 'C';
					cdGrp   = $this.params.cdSpec.cdGrp;
					cdGrpNm = $this.params.cdSpec.cdGrpNm;
				}
				$('#cdSpecDetlPopup').modal('show');
				cdSpecDetl.initPage(crud, cdGrp, cdVal);
			}
		},
		resetSearchParam: function() {
			let $this = this;
			$this.params = {
				cdGrp : {
					cdGrp: '',
					cdGrpNm: '',
					paging: 'Y',
		            totalCount: 0,
		            rowCount: 30,
		            currentPage: 1,
		            currentIndex: 0
				},
				cdSpec: {
					cdGrp: '',
					paging: 'Y',
		            totalCount: 0,
		            rowCount: 30,
		            currentPage: 1,
		            currentIndex: 0
				}
			}
		},
		 cdMng_typing : function(e){    	
	            this.max_length(e, 40, '#cdGrp');
	            this.max_length(e, 100, '#cdGrpNm');
	        },
	        max_length : function(e, len,id)
	        {
	            var val =  e.target.value;    			
	            if (val.length > len){    				
	            	Swal.alert(['최대 글자수를 초과하였습니다.' ]);
	            	 $(id).val(val.substring(0, len));
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
