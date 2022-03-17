let roleMng = new Vue(
		{
			el : "#roleMng",
			data : {
				params : {
					roleNm : '',
					roleDivCd : '',
					paging : 'Y',
					totalCount : 0,
					rowCount : 30,
					currentPage : 1,
					currentIndex : 0
				},
				code : {
					roleDivList : []
				}
			},

			methods : {

				initialize : function() {
					let $this = this;

					$this.initCodeList();

					$this.initGrid();

					$this.searchRoleList(true);
				},
				initCodeList : function() {
					getCommonCodeList('ROLE_DIV_CD', this.code.roleDivList);
				},
				initGrid : function() {

					let colModels = [
							{
								name : "roleCd",
								index : "roleCd",
								label : "역할코드",
								width : 50,
								align : "center"
							},
							{
								name : "roleNm",
								index : "roleNm",
								label : "역할명",
								width : 100,
								align : "left"
							},
							{
								name : "roleDivCd",
								index : "roleDivCd",
								label : "역할구분코드",
								width : 80,
								align : "center",
								hidden : true
							},
							{
								name : "roleDivCd",
								index : "roleDivCd",
								label : "역할구분",
								width : 50,
								align : "center"
							},
							{
								name : "roleDesc",
								index : "roleDesc",
								label : "역할설명",
								width : 150,
								align : "left"
							},
							{
								name : "roleDetlPop",
								index : "roleDetlPop",
								label : "역할 정보보기",
								width : 50,
								align : "center",
								formatter : function(cellValue, options,
										rowObject) {
									return '<input type="button" class="btn btn-xs btn-outline btn-success" onclick="roleMng.regRolePop(\''
											+ rowObject.roleCd
											+ '\')" value="상세보기" data-toggle="modal" data-target="#roleDetlPopup" />';
								}
							} ];

					$("#role_list").jqGrid("GridUnload");
					$("#role_list")
							.jqGrid(
									$
											.extend(
													true,
													{},
													commonGridOptions(),
													{
														datatype : "local",
														mtype : 'post',
														height : 455,
														url : '/set/roleMng/searchRoleList.ab',
														pager : '#role_pager_list',
														colModel : colModels,
														onPaging : function(
																data) {
															onPagingCommon(
																	data,
																	this,
																	function(
																			resultMap) {
																		$this.params.currentPage = resultMap.currentPage;
																		$this.params.rowCount = resultMap.rowCount;
																		$this.params.currentIndex = resultMap.currentIndex;
																		$this.searchRoleList(false);
																	})
														}
													}));

					resizeJqGridWidth("role_list", "role_list_wrapper");
				},
				searchRoleList : function(isSearch) {

					let $this = this;
					let params = $.extend(true, {}, $this.params);

					if (isSearch) {
						params.currentPage = 1;
						params.currentIndex = 0;
					}

					$("#role_list").setGridParam({
						datatype : "json",
						postData : JSON.stringify(params),
						page : 1,
						loadComplete : function(response) {
							if (response.rtnData.result == 0) {
								Swal.alert([ '조회할 내용이 없습니다.', "info" ]);
							}
						}
					}).trigger("reloadGrid");
				},
				downloadExcel : function() {

					let $this = this;

					let params = $.extend(true, {}, $this.params);

					AjaxUtil.post({
						dataType : 'binary',
						url : "/set/roleMng/searchRoleList/excel.ab",
						param : params,
						success : function(response) {
							saveFileLocal(response, 'RoleMng.xls');
						},
						error : function(response) {
							Swal.alert([ response, 'error' ]);
						}
					});
				},
				regRolePop : function(roleCd) {
					roleDetl.initPage(roleCd);
				},
				resetSearchParam : function() {
					let $this = this;
					$this.params = {
						roleNm : '',
						roleDivCd : '',
						paging : 'Y',
						totalCount : 0,
						rowCount : 30,
						currentPage : 1,
						currentIndex : 0
					}
				},
				   roleMng_typing : function(e){    	
			            this.max_length(e, 40, '#roleNm');
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

			computed : {

			},
			watch : {

			},
			mounted : function() {
				let self = this;
				$(document).ready(function() {
					self.initialize();
				});
			}
	
		});
