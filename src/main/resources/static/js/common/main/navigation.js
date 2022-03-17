var navigation = null;
navigation = new Vue({
	/* template: ..., */
	el: '#navigation',
	data: {
        searchAuthParam : {
            searchClass : '01',
            empNo : 0
        },
        menuList: [
	/*
			{ menuNo: 'A', upprMenuNo: '0', menuNm: '작업정의', menuUrl: '/def', menuId: 'A', menuPath: '작업정의', menuIcon: 'fa fa-book', lev:0, child: [
                { menuNo: 'A010', upprMenuNo: 'A', menuNm: '워크스페이스관리', menuUrl: '/def/wsMng', menuId: 'A010', menuPath: '작업정의 > 워크스페이스관리', menuIcon: '', lev:1, child: []},
                { menuNo: 'A020', upprMenuNo: 'A', menuNm: '작업셋관리', menuUrl: '/def/jsetMng' , menuId: 'A020', menuPath: '작업정의 > 작업셋관리', menuIcon: '', lev:1, child: []},
                { menuNo: 'A030', upprMenuNo: 'A', menuNm: '워크플로우관리', menuUrl: '/def/jobMng' , menuId: 'A030', menuPath: '작업정의 > 단위작업관리', menuIcon: '', lev:1, child: []},
                { menuNo: 'A040', upprMenuNo: 'A', menuNm: '단위작업관리', menuUrl: '/def/schdMng' , menuId: 'A040', menuPath: '작업정의 > 스케줄러관리', menuIcon: '', lev:1, child: []}
            ]},
			{ menuNo: 'B', upprMenuNo: '0', menuNm: '작업실행', menuUrl: '/exec', menuId: 'B', menuPath: '작업실행', menuIcon: 'fa fa-clock-o', lev:0, child: [
                { menuNo: 'B010', upprMenuNo: 'B', menuNm: '종합현황', menuUrl: '/exec/totStat', menuId: 'B010', menuPath: '작업실행 > 종합현황', menuIcon: '', lev:1, child: []},
			    { menuNo: 'B020', upprMenuNo: 'B', menuNm: '워크플로우현황', menuUrl: '/exec/flowStat', menuId: 'B020', menuPath: '작업실행 > 워크플로우현황', menuIcon: '', lev:1, child: []},
                { menuNo: 'B030', upprMenuNo: 'B', menuNm: '단위작업현황', menuUrl: '/exec/jobStat', menuId: 'B030', menuPath: '작업실행 > 단위작업현황', menuIcon: '', lev:1, child: []}
            ]},
			{ menuNo: 'C', upprMenuNo: '0', menuNm: '이력/보고서', menuUrl: '/hist', menuId: 'C', menuPath: '이력/보고서', menuIcon: 'fa fa-bar-chart-o', lev:0, child: [
                { menuNo: 'C010', upprMenuNo: 'C', menuNm: '작업요약', menuUrl: '/hist/jobSumm', menuId: 'C010', menuPath: '이력/보고서 > 작업요약', menuIcon: '', lev:1, child: []},
                { menuNo: 'C020', upprMenuNo: 'C', menuNm: '작업이력', menuUrl: '/hist/jobHist', menuId: 'C020', menuPath: '이력/보고서 > 작업이력', menuIcon: '', lev:1, child: []},
                { menuNo: 'C050', upprMenuNo: 'C', menuNm: '알람이력', menuUrl: '/hist/notiHist', menuId: 'C050', menuPath: '이력/보고서 > 알람이력', menuIcon: '', lev:1, child: []}
            ]},
            { menuNo: 'D', upprMenuNo: '0', menuNm: '설정/관리', menuUrl: '/set', menuId: 'D', menuPath: '설정/관리', menuIcon: 'fa fa-cog', lev:0, child: [
                { menuNo: 'D010', upprMenuNo: 'D', menuNm: '에이전트관리', menuUrl: '/set/agntMng', menuId: 'D010', menuPath: '설정/관리 > 에이전트관리', menuIcon: '', lev:1, child: []},
                { menuNo: 'D020', upprMenuNo: 'D', menuNm: '사용자관리', menuUrl: '/set/userMng', menuId: 'D020', menuPath: '설정/관리 > 사용자관리', menuIcon: '', lev:1, child: []},
                { menuNo: 'D030', upprMenuNo: 'D', menuNm: '역할관리', menuUrl: '/set/roleMng', menuId: 'D030', menuPath: '설정/관리 > 역할관리', menuIcon: '', lev:1, child: []},
                { menuNo: 'D040', upprMenuNo: 'D', menuNm: '메뉴관리', menuUrl: 'set/menuMng', menuId: 'D040', menuPath: '설정/관리 > 메뉴관리', menuIcon: '', lev:1, child: []},
                { menuNo: 'D050', upprMenuNo: 'D', menuNm: '메뉴역할관리', menuUrl: '/set/menuRoleMng', menuId: 'D050', menuPath: '설정/관리 > 메뉴역할관리', menuIcon: '', lev:1, child: []},
                { menuNo: 'D060', upprMenuNo: 'D', menuNm: '공통코드관리', menuUrl: '/set/cdMng', menuId: 'D060', menuPath: '설정/관리 > 공통코드관리', menuIcon: '', lev:1, child: []},
                { menuNo: 'D070', upprMenuNo: 'D', menuNm: '알람관리', menuUrl: '/set/notiMng', menuId: 'D070', menuPath: '설정/관리 > 알람관리', menuIcon: '', lev:1, child: []},
                { menuNo: 'D080', upprMenuNo: 'D', menuNm: '라이센스정보', menuUrl: '/set/licMng', menuId: 'D080', menuPath: '설정/관리 > 라이센스정보', menuIcon: '', lev:1, child: []}
            ]},
//            { menuNo: 'E', menuNm: '샘플', menuUrl: '', menuId: 'E', menuPath: '샘플', menuIcon: 'fa fa-cog', lev:0, child: [
//                { menuNo: 'E010', menuNm: '전체', menuUrl: '/schd/comp/overview', menuId: 'E010', menuPath: '샘플 > 전체', menuIcon: '', lev:1, child: []},
//                { menuNo: 'E020', menuNm: '핸들', menuUrl: '/schd/comp/handle', menuId: 'E020', menuPath: '샘플 > 핸들', menuIcon: '', lev:1, child: []},
//                { menuNo: 'E030', menuNm: '이벤트', menuUrl: '/schd/comp/event', menuId: 'E030', menuPath: '샘플 > 이벤트', menuIcon: '', lev:1, child: []},
//                { menuNo: 'E040', menuNm: '함수', menuUrl: '/schd/comp/func', menuId: 'E040', menuPath: '샘플 > 함수', menuIcon: '', lev:1, child: []},
//                { menuNo: 'E050', menuNm: '커스텀', menuUrl: '/schd/comp/custom', menuId: 'E050', menuPath: '샘플 > 커스텀', menuIcon: '', lev:1, child: []}                
//            ]}*/
        ]

	},
	/* Vue Instance 내 method 정의 */
	methods: {
        getMenuList: function() {

            let $this = this;

            if ( $this.searchAuthParam.searchClass == '' || $this.searchAuthParam.empNo < 0) {
                return;
			}
            
            AjaxUtil.post({
                url: "/cmon/menu/searchMenuList.ab",
                param: $this.searchAuthParam,
                success: function(response) {
                	
                	if ( !!response.rtnData.result ) {

                		let data = response;
                        if ( response.rtnData ) {
                            data = response.rtnData.result;
                        }

    					let currentIndex1 = 0;
                        let currentIndex2 = 0;
                        let currentMenu;
                        for ( let i in data )
                        {
                            data[i].child = [];

							if ( !!data[i].menuUrl ) {
								data[i].menuUrl = data[i].menuUrl + '.pg';
							}

                            // 메뉴가 1뎁스인 경우
                        	if ( data[i].upprMenuNo === '0' )
                        	{
                        		data[i].menuUrl = '';
                                currentIndex1 = $this.menuList.push(data[i]);
    						}

                            // 현재 1뎁스 메뉴
                            currentMenu = $this.menuList[currentIndex1 - 1];

                            // 메뉴가 2뎁스인 경우
    						if ( data[i].upprMenuNo === currentMenu.menuNo && currentMenu ) {
                                currentIndex2 = currentMenu.child.push(data[i]);
    						} else {
                                // 메뉴가 3뎁스인 경우
                                for ( let j = 0 ; j < currentMenu.child.length ; j++ )
                                {
                                    if ( data[i].upprMenuNo === currentMenu.child[j].menuNo ) {
                                        currentMenu.child[j].child.push(data[i]);
                                        j = currentMenu.child[j].length;
                                    }
                                }
                            }
                        }
                	}                    		
                },
                error: function (response) {
                	Swal.alert([response, 'error']);
                }
            });
        },
        newTab: function(menuNm, menuUrl, menuId) {
            if(menuUrl != null && menuUrl != '' ) {
                //console.log(menuPath.split(' > '));
                index.newTab(menuNm, menuUrl, menuId, '');//menuPath.split(' > '));

                $('li', '#side-menu').removeClass("active");

                var menuTree = ($('#' + menuId, $('#side-menu')).attr("menuTree")).split('_');

                for(var i = 0; i < menuTree.length; i++) {
                    $('#' + menuTree[i]).addClass("active");
                }
            }
        },
	},
	mounted: function () {
		var self = this;
        $(document).ready(function() {
             self.getMenuList();
        });
	},
    updated: function () {
    	$('#side-menu').metisMenu();
    }
})
	