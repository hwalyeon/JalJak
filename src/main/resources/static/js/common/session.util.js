/**
 * File Name   : session.util.js
 * Description : 세션 유틸 자바스크립트
 * Author      : boneis
 * Date        : 2019.01.28
 */

var SessionUtil = {

    /**
     * 최고관리자 여부
     */
    getSuperYn: function() {
        var resultYn = false;

        if (document.getElementById("gblAuthSuperCd").value == document.getElementById("gblAuthGrpcd").value) {
            resultYn = true;
        }

        return resultYn;
    },

    /**
     * 관리자 여부
     */
    getAdminYn: function() {
        var resultYn = false;

        if (document.getElementById("gblAuthAdminCd").value == document.getElementById("gblAuthGrpcd").value) {
            resultYn = true;
        }

        return resultYn;
    },

    /**
     * MD 여부
     */
    getMdYn: function() {
        var resultYn = false;
        var authGrpCd = document.getElementById("gblAuthGrpcd").value;

        if (authGrpCd == document.getElementById("gblAuthMdCd").value || authGrpCd == document.getElementById("gblAuthWpMdCd").value) {
            resultYn = true;
        }

        return resultYn;
    },

    /**
     * 거래처 여부
     */
    getSelAcntYn: function() {
        var resultYn = false;

        if (document.getElementById("gblAuthSelAcntCd").value == document.getElementById("gblAuthGrpcd").value) {
            resultYn = true;
        }

        return resultYn;
    },


    /**
     * CS 여부
     */
    getCsYn: function() {
        var resultYn = false;

        if (document.getElementById("gblAuthCsCd").value == document.getElementById("gblAuthGrpcd").value) {
            resultYn = true;
        }

        return resultYn;
    },

    /**
     * 입력대행 여부
     */
    getInptAgcyYn: function() {
        var resultYn = false;

        if (document.getElementById("gblAuthInptAgcyCd").value == document.getElementById("gblAuthGrpcd").value) {
            resultYn = true;
        }

        return resultYn;
    },

    /**
     * 사용자아이디
     */
    getUserId: function() {
    	return WebUtil.getStorageData('userInfo', false).userInfo.userId;
    	
    },
    
    setUserId: function(userId) {
        return document.getElementById("gblUserId").value = userId;
    },

    /**
     * 사용자명
     */
    getUserNm: function() {
    	return WebUtil.getStorageData('userInfo', false).userInfo.userNm;
    },
    
    setUserNm: function(userNm) {
        return document.getElementById("gblUserNm").value = userNm;
    },

    setUserInfo: function(userInfo) {
    	WebUtil.setStorageData('jwtToken', {'jwtToken' : userInfo.jwtToken}, false);
    	WebUtil.setStorageData('userInfo', {'userInfo' : userInfo.userInfo}, false);
    	WebUtil.setStorageData('roleInfo', {'roleInfo' : userInfo.roleList}, false);
    },
    
    initSession: function() {
    	WebUtil.clearStorageData();
    },
    
    getUserRoleList: function() {
        return WebUtil.getStorageData('roleInfo', false).roleInfo;
    },

    setUserRoleList: function(roleList) {
    	WebUtil.setStorageData('roleInfo', {'roleInfo' : roleList}, false);
    },
    
    hasRole: function(roleCd) {
    	var roleList = SessionUtil.getUserRoleList();
    	if ( !roleList || roleList.length == 0 ) {
    		return false;
    	} else {
	    	for ( var i = 0 ; i < roleList.length ; i++ ) {
	    		if ( roleList[i].roleCd == roleCd ) {
	    			return true;
	    		}
	    	}
	    	return false;
    	}
    },

	isAdmin: function() {
		var roleList = SessionUtil.getUserRoleList();
    	if ( !roleList || roleList.length == 0 ) {
    		return false;
    	} else {
	    	for ( var i = 0 ; i < roleList.length ; i++ ) {
	    		if ( roleList[i].roleCd == 'ADMIN' ) {
	    			return true;
	    		}
	    	}
	    	return false;
    	}
	},
    
	isAcdm: function() {		
		var roleList = SessionUtil.getUserRoleList();		
    	if ( !roleList || roleList.length == 0 ) {   		
    		return false;
    	} else {
	    	for ( var i = 0 ; i < roleList.length ; i++ ) {	    		
	    		if ( roleList[i].roleCd == 'ACDM' ) {
	    			return true;
	    		}
	    	}
	    	return false;
    	}
	},
	
	isLctr: function() {
		var roleList = SessionUtil.getUserRoleList();
    	if ( !roleList || roleList.length == 0 ) {
    		return false;
    	} else {
	    	for ( var i = 0 ; i < roleList.length ; i++ ) {
	    		if ( roleList[i].roleCd == 'LCTR' ) {
	    			return true;
	    		}
	    	}
	    	return false;
    	}
	},
	
	isStdt: function() {
		var roleList = SessionUtil.getUserRoleList();
    	if ( !roleList || roleList.length == 0 ) {
    		return false;
    	} else {
	    	for ( var i = 0 ; i < roleList.length ; i++ ) {
	    		if ( roleList[i].roleCd == 'STDT' ) {
	    			return true;
	    		}
	    	}
	    	return false;
    	}
	},
	
    /**
     * 사용자 거래처 목록
     */
    getUserSelAcntList: function() {
        var resultList = [];
        var selAcntList = document.getElementsByName("gblUserSelAcntList");

        if (selAcntList != null && selAcntList.length > 0) {
            var m = 0;
            var selAcntCnt = selAcntList.length;

            for (m = 0; m < selAcntCnt; m++) {
                resultList.push({
                    selAcntNo: selAcntList[m].getAttribute("data-sel-acnt-no"),
                    selAcntCd: selAcntList[m].getAttribute("data-sel-acnt-cd"),
                    selAcntNm: selAcntList[m].getAttribute("data-sel-acnt-nm"),
                    value: selAcntList[m].getAttribute("data-sel-acnt-no"),
                    text: selAcntList[m].getAttribute("data-sel-acnt-nm")
                });
            }
        }

        return resultList;
    }

};
