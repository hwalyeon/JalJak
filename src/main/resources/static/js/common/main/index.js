var index = new Vue({
	el: '#indexApp',
	data: {
		isLoading: false,
		currentDate: new Date(),
		currentNumber: 100000,
		currentIframe: {id: 'tabFrame_1'},
		closeAllTabsTitle: '전체탭닫기',
		mainMenuInfo: { menuNm: 'Main', menuUrl: '/main.pg', menuId: 1, menuPath: ['메인'], interval: null},
		menuList: [
			{ menuNm: 'Main', menuUrl: '/main.pg', menuId: 1, menuPath: ['메인'], interval: null}
		],	
        searchAuthParam : {
            searchClass : '01',
            userId : 'admin',
            userNm : '원격사용자'
        },
		leftMenuList: [],
        index: index,
        postParam: {
            empNo: 0,
            empScrtNo: '',
            orgEmpScrtNo: '',
            confirmEmpScrtNo: '',
            rsaModules: '',
            rsaExponent: ''
        }
	},
	/* Vue Instance 내 method 정의 */
	methods: {
		calcHeight: function(target, isInterval) {
//			console.log('calcHeight');
			if(this.currentIframe.id != target.id) {
				return;
			}
//			console.log(this.currentIframe.id + '' + target.id);
			//find the height of the internal page
			var frameObj = target;
			try {
				if(!isInterval) $(frameObj).height(0);
				$(frameObj).height($(frameObj).contents().outerHeight());
				frameObj.scrolling = "no";
				framebj.style.overflow = "hidden";
			 } catch(e) {}
		},
		resizeEvent: function($event, index) {
//			console.log('resizeEvent');
			var target = $event.target;
			
            $(target).iFrameResize({log:false, heightCalculationMethod:'taggedElement'});
		},
		// New Window
		windowOpen: function(id) {
//			console.log('windowOpen');

			var w = 1024;
			var h = 768;
			var currentFrameSrc = $('#tabFrame_' + id).attr('src');
			if(currentFrameSrc) {
				var name ="window_"+ id;

				// 윈도우 팝업 중앙으로 열기
				openWinPop({
                    name: name,
                    url: currentFrameSrc,
                    width: w,
                    height: h
                });
			}
		},
		// Reload
		reloadTab: function(id) {
		    var currentFrameSrc = $('#tabFrame_' + id).attr('src');
		    if(currentFrameSrc) {
		    	$('#tabFrame_' + id).attr('src', currentFrameSrc);
		    }
		},
		// 기 추가된 Tab 인지 체크
		checkTab: function(menuId) {
//			console.log('checkTab');
			var tabs = $("#tab-list li");
			var existTab = $("#tab-list a[href='#tab" + menuId +"']");

			// 기 존재하는 Tab 일 경우 Focuse만.
			if(existTab.length > 0) {
				existTab.tab("show");
				return false;
			} else {
				// Tab Max Size 를 초과할 경우 Dialog
				var MAX_TAB_CNT = 10;
				if(tabs.length > MAX_TAB_CNT) {
					Swal.alert(['탭은 10개까지 열수 있습니다.', "info"]);
					return false;
				} else {
					return true;
				}
			}
		},
		// 기 추가된 Tab 인지 체크
		onlyCheckTab: function(menuId) {
//			console.log('onlyCheckTab');
			var tabs = $("#tab-list li");
			var existTab = $("#tab-list a[href='#tab" + menuId +"']");

			// 기 존재하는 Tab 일 경우 Focuse만.
			if(existTab.length > 0) {
				return false;
			} else {
				// Tab Max Size 를 초과할 경우 Dialog
				var MAX_TAB_CNT = 10;
				if(tabs.length > MAX_TAB_CNT) {
					return false;
				} else {
					return true;
				}
			}
		},
		// 좌측 메뉴 클릭 시 Tab 생성 > 포커스
		newTab: function(menuNm, menuUrl, menuId, menuPath) {
//			console.log('newTab');
			
			if ( WebUtil.isNull(WebUtil.getStorageData('jwtToken', false)) ) {
				window.location.href = '/login.pg';
			} else {
				if(this.checkTab(menuId)) {
			        this.menuList.push({ menuNm: menuNm, menuUrl: menuUrl, menuId: menuId, menuPath: menuPath, interval: null });
	                Vue.nextTick(function() {

	                })
				}
			}
		},
		// 탬 생성 후 초기화 처리...(mounted, updated 에서 Call)
		initTab: function() {
//			console.log('initTab');
			//display new tab & init
			var tabNew = $('#tab-list a:last');
		    tabNew.tab('show');
		    // show 시 event 처리 추가.(iframe height resize)
		    var self = this;
		    tabNew.on('shown.bs.tab', function (e) {
		    	var target = $(e.target).attr("href"); // activated tab pane
				self.currentIframe = $(target.replace('#tab', '#tabFrame_')).get(0);
				self.calcHeight(self.currentIframe);
				var index = $(e.target).attr("index");
//				console.log('shown.bs.tab : ' + index);
			});
		},
		
		initValue: function() {
			let $this = this;
			$this.searchAuthParam.userId = SessionUtil.getUserId();
			$this.searchAuthParam.userNm = SessionUtil.getUserNm();
			
			$('#topNavBarUserNm').text(SessionUtil.getUserNm());
		},
		logout: function() {
			
			let $this = this;
			
			AjaxUtil.post({
                url: "/v1/auth/logout",
                param: $this.searchAuthParam,
                success: function(response) {
                	
                	SessionUtil.initSession();
                	
                	window.location.href = "/login.pg";
                },
                error: function (response) {
                    Swal.alert([response, 'info']);
                }
            });
		},
		closeTab: function($event, menuIndex) {
//			console.log('closeTab');
			var self = this;
			var vueObj = this;
			//messageString = '열려진 Tab이 삭제됩니다. 해당 메뉴에 저장하지 않으신 정보가 있으시면 저장해 주세요. \n Tab을 삭제하시겠습니까?';
			Swal.confirm(["열려진 Tab이 삭제됩니다. 해당 메뉴에 저장하지 않으신 정보가 있으시면 저장해 주세요.\nTab을 삭제하시겠습니까?", "warning"])
				.then(function(value){
					if (value) {
						// selected menu remove in array
						vueObj.closeAllInterval(menuIndex);
						vueObj.menuList.splice(menuIndex, 1);

						self.activeTab(vueObj.menuList[vueObj.menuList.length - 1].menuId);
					}
				});
		},
        activeTab: function(menuId){
//			console.log('activeTab:' + menuId);
			// Main 메뉴
			if(menuId == 1) {
				$('ul', $('#side-menu')).collapse('hide');
				$('li', $('#side-menu')).removeClass('active');
			}else {
				// 현재 활성화 된 탭의 메뉴ID
				var currentTabMenuId = $("#tab-list li.active > a").attr('href').replaceAll('#tab', '');

				// 현재 활성화 된 탭의 메뉴 트리
				var currentMenuTree = ($('#' + currentTabMenuId, $('#side-menu')).attr("menuTree")).split('_');
				// 선택한 탭의 메뉴 트리
				var menuTree = ($('#' + menuId, $('#side-menu')).attr("menuTree")).split('_');

				// 현재탭과 선택탭의 상위 메뉴 활성화 상태 비교
				for (var i = 0; i < currentMenuTree.length; i++) {
					if (currentMenuTree[i] != menuTree[i]) {
						$('#' + currentMenuTree[i]).removeClass('active');
						$('#' + menuTree[i]).addClass("active");

						if (i < 2) {
							if (currentMenuTree[i + 1] != menuTree[i + 1]) {
								$('#' + currentMenuTree[i]).parent().collapse('hide');
								$('#' + menuTree[i]).parent().collapse('show');
							}
						}
					} else {
						$('#' + menuTree[i]).addClass('active');

						if (!$('#' + menuTree[i]).parent().hasClass('metismenu')) {
							$('#' + menuTree[i]).parent().collapse('show');
						}
					}
				}
			}
		},
		closeAllTabs: function() {
//			console.log('closeAllTabs');
			var tabs = $("#tab-list li:not(:first)");
	    	var vueObj = this;
	    	if (tabs.length > 0) {
	    		Swal.confirm(["열려진 Tab이 삭제됩니다. 해당 메뉴에 저장하지 않으신 정보가 있으시면 저장해 주세요.\nTab을 삭제하시겠습니까?", "warning"])
					.then(function(value){
						if (value) {
							// Initial menu list.
							vueObj.closeAllInterval(0);
							vueObj.menuList = [vueObj.mainMenuInfo]

							$('li', '#side-menu').removeClass('active');
							$('ul', '#side-menu').collapse("hide");

							Swal.alert(["탭이 모두 삭제되었습니다.", "success"]);
						}
					});
	    	}
		},
		closeAllInterval: function(menuIndex) {
//			console.log('closeAllInterval');
			for(var index in this.menuList) {
				if(index >= menuIndex) {
					clearInterval(this.menuList[index].interval);
                    this.menuList[index].interval = null;

				}
			}
		},
		// onClick > $event > $event.target 으로 해당 Event Target Object를 전달할 수 있다. javascript:function(this) 와 동일.
		editHandler: function($event) {
//			console.log('editHandler');
			var t = $($event.target);
			//console.log(t);
		  	t.css("visibility", "hidden");
		  	$($event.target).prev().attr("contenteditable", "true").focusout(function() {
		    	$($event.target).prev().removeAttr("contenteditable").off("focusout");
		    	t.css("visibility", "visible");
		  	});
		  	// editable 처리 이후 focusing...
		  	$($event.target).prev().focus();
		},
		// REST Errorhandling 을 위한 정리는 추후 별도 진행 예정.
		reloadResourceBundle: function() {
//			console.log('reloadResourceBundle');
			ladda_button_div = $('.ladda-button-reload');
			if(!isLoading) {
				$.ajax({
					type: 'get',
					url: '/common/messageResource/reload',
					data: '',
					success : function(result) {
						try {
							// confirm 외에 alert 도 Ok/확인 버튼 이후 작업이 필요할 경우 then 으로 처리.
							// 그렇지 않을 경우 dialog 뜨고 나서 바로 실행이 됨.
							Swal.alert(['Reload resource bundle messages completed', 'success'])
//							.then(() => {
//								location.reload();
//							});
							.then(function(value){
								location.reload();
							});
						} catch(ex){
	                        Swal.alert(['오류가 발생하였습니다.', 'error']);
	                    }
					},
					error : function(xhr, status, error) {
						Swal.alert(['오류가 발생하였습니다.', 'error']);
						return false;
					}
				})
			}
		},
        getMenuList: function() {
//			console.log('getMenuList');
            if(index.searchAuthParam.searchClass == '' && index.searchAuthParam.empNo <= 0) {
                return;
			}
            $.ajax({
                type: 'get',
                url: '/system/rest/getAuthMenuList',
                data: index.searchAuthParam,
                success : function(response) {
                    var data = response;
                    if(response.data) data = response.data.result;
                    //console.log(response);
                    index.leftMenuList = data;
                    for(i=0; i<index.leftMenuList.length; i++){
                    	//console.log('menuPath : ' + index.leftMenuList[i].menuPath);
                        //userList.searchClassList.push({value: data[i].dtlsCd, text: data[i].dtlsComNm});
                    }
                }
            })
        },
        checkValid: function() {
            var param = index.postParam;

			if('undefined' == typeof param.orgEmpScrtNo || '' == param.orgEmpScrtNo.trim()) {
				Swal.alert([$.t('이전 비밀번호를 입력해 주세요'), 'warning'])
				return false;
			}

            if('undefined' == typeof param.empScrtNo || '' == param.empScrtNo.trim()) {
                Swal.alert([$.t('sys.user.pwd.fail'), 'warning'])
                return false;
            } else {
                if(!isValidPassword(param.empScrtNo)) {
                    Swal.alert([$.t('sys.user.pwd.chk.fail'), 'warning'])
                    return false;
                }
            }

            if('undefined' == typeof param.confirmEmpScrtNo || '' == param.confirmEmpScrtNo.trim()) {
                Swal.alert([$.t('비밀번호 확인을 입력해 주세요'), 'warning'])
                return false;
            } else {
                if(!isValidPassword(param.confirmEmpScrtNo)) {
                    Swal.alert([$.t('sys.user.pwd.chk.fail'), 'warning'])
                    return false;
                }
            }

            if(param.empScrtNo.trim() != param.confirmEmpScrtNo.trim()) {
                Swal.alert([$.t('신규 비밀번호를 확인해 주세요'), 'warning'])
                return false;
            }

            if(param.orgEmpScrtNo.trim() == param.empScrtNo.trim()) {
                Swal.alert([$.t('이전 비밀번호와 동일합니다'), 'warning'])
                return false;
            }

            return true;
        },
        resetPassword: function() {
            if(!index.checkValid()) {
                return;
            }

            // RSA 암호화
            if(index.postParam.rsaModules == '' || index.postParam.rsaExponent == '' ) {
                Swal.alert([$.t('암호화 모듈이 준비되지 않았습니다.'), 'warning'])
                return false;
            }

            // RSA 암호화
            var rsa = new RSAKey();
            rsa.setPublic(index.postParam.rsaModules, index.postParam.rsaExponent);
            index.postParam.empScrtNo = rsa.encrypt(index.postParam.empScrtNo);
            index.postParam.orgEmpScrtNo = rsa.encrypt(index.postParam.orgEmpScrtNo);

            ladda_button_div = $('.ladda-button-resetpwd');
            $.ajax({
                type: 'put',
                contentType: "application/json",
                url: '/system/rest/users/' + index.postParam.empNo,
                data: JSON.stringify(index.postParam),
                success : function(response) {
                    if(response.resultStatus.status) {
                        Swal.alert([response.resultStatus.message, "success"])
                            .then(function(){
                                index.resetPostParam();
                                $('#updateUserPassword').modal('hide');
                            });
                    } else {
                        index.resetPostParam();
                        $('#updateUserPassword').modal('hide');
                        Swal.alert([response.resultStatus.message, "error"])
                    }
                }
            })
        },
        // 암호화를 위한 초기화 및 공개키 조회.
        initRsa: function() {
            $.ajax({
                type: 'get',
                url: '/system/rest/initRsa',
                success : function(response) {
                    var result = response;
                    if(response.data) result = response.data.result;

                    if(result == '') {
                        Swal.alert([$.t('error.dataErr'), 'warning'])
                    }
                    //console.log(result);
                    index.postParam.rsaModules = result.rsaModules;
                    index.postParam.rsaExponent = result.rsaExponent;
                }
            });
        },
        randomPassword: function() {
            var newPassword;
            var randomValue="abvdefghijklmnopqrstuvwxyz0123456789";

            for(i=1; i <=15; i++){
                randomPoint = Math.round(Math.random()*34*1);
                Pwdchar = randomValue.charAt(randomPoint);
                if(i == 1) {
                    newPassword = Pwdchar;
                } else {
                    newPassword += Pwdchar;
                }
            }

            return newPassword;
        },
        resetPostParam: function() {
            // Param Reset...
            index.postParam.empScrtNo=  '';
            index.postParam.orgEmpScrtNo = '';
            index.postParam.confirmEmpScrtNo = '';
        },
	},
	// filter 는 별도 공통 으로 정의 필요.
	filters: {
		capitalize: function (value) {
			if (!value) return ''
			value = value.toString()
			return value.charAt(0).toUpperCase() + value.slice(1)
		}
	},
	
	/* Lifecycle 단계에 따른 초기화 method custom 시 구현 */
	created: function () {

		// Vue 데이터 핸들링 외 초기 처리 시 오류 발생.
		var $this = this;
		$this.$on('SET_PARAM', function(param) {
			$this.$emit('GET_PARAM', param);
		});

	},
	// Vue 이외 화면 요소(Bootstrap/jquery/htmleditor 등) 로딩 이후 호출.
	// Vue 이외 화면 요소(Bootstrap/jquery/htmleditor 등)들에 대한 초기작업할 경우 mounted 에 작성.
	mounted: function () {
		
        var self = this;
        $(document).ready(function() {
            // 첫 Tab 추가 후 jquery document 요소 mount 후 Show 처리.
            //display new tab & init
            self.initTab();
            self.initValue();
            $("#updateUserPassword").on("shown.bs.modal", function (e) {
                // 암호화 모듈 생성.
                self.postParam.empScrtNo = '';
                self.postParam.orgEmpScrtNo = '';
                self.postParam.confirmEmpScrtNo = '';
                self.initRsa();
            });
            
//            setTimeout(function () {
//            	console.log(moment().format("YYYY-MM-DD HH:mm:ss"));
//            }.bind(this), 1000);
        });
	},
	// Vue 데이터 변경 또는 Template 랜더링 이후 호출.
	// Data 변경 또는 화면 렌더링 이후에 처리해야 할 기본 작업의 경우 updated 에 작성.
	updated: function () {
		// Vue Data Updated 시
		// Dynamic 하게 화면이 추가 렌더링 될 경우 호출 시킴.
        updateI18NextContent();
		// New Tab <> Remove Tab 분기 처리 필요.
		//display new tab & init
        this.initTab();

        // Tab 이 Show 될 경우 iframe resize 처리.
	},
	destroyed: function () {
	},
	computed: {
		initResource: function() {
			return 'computed tabId is: ' + this.tabId;
		}
	}
})
	