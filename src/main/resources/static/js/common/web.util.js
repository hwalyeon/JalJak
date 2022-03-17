/**
 * File Name   : web.util.js
 * Description : 웹 유틸 자바스크립트
 * Author      : boneis
 * Date        : 2018.11.06
 */

var WebUtil = {

    /**
     * null 체크
     * str : 문자열
     */
    isNull: function(str) {
        var type = typeof str;

        if (type === "undefined" || str === null) {
            return true;
        }

        if (type === "string" && this.trim(str) == "") {
            return true;
        }

        return false;
    },

    /**
     * not null 체크
     * str : 문자열
     */
    isNotNull: function(str) {
        return !this.isNull(str);
    },

    /**
     * object 체크
     * obj : 오브젝트
     */
    isObject: function(obj) {
        if (obj !== null && typeof obj === "object") {
            return true;
        }

        return false;
    },

    /**
     * 변수 체크 - undefined
     * obj : 오브젝트
     */
    isVar: function(obj) {
        if (typeof obj === "undefined") {
            return false;
        }

        return true;
    },

    /**
     * 좌우 공백제거
     * str : 문자열
     */
    trim: function(str) {
        if (typeof str === "string") {
            return str.replace(/^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, "");
        }

        return str;
    },

    /**
     * null 값 바꾸기
     * str : 문자열
     * val : null 대체값
     */
    nvl: function(str, val) {
        if (this.isNull(str)) {
            return val;
        }

        return this.trim(str);
    },

    /**
     * 전체 치환
     * str     : 문자열
     * pattern : 패턴
     * re      : 치환될 문자
     */
    replaceAll: function(str, pattern, re) {
        var type = typeof str;

        if (type === "string" || type === "number") {
            var temp = this.trim(str) + "";

            if (temp != "") {
                return temp.replace(new RegExp(pattern, "g"), re);
            }
        }

        return str;
    },

    /**
     * prefix 설정
     * str   : 문자열
     * front : 앞문자
     * back  : 뒷문자
     */
    prefix: function(str, front, back) {
        var type = typeof str;

        if (type === "string" || type === "number") {
            if (this.isNotNull(str)) {
                var result = this.trim(str) + "";

                // 앞문자
                if (this.isNotNull(front)) {
                    result = front + result;
                }

                // 뒷문자
                if (this.isNotNull(back)) {
                    result = result + back;
                }

                return result;
            }
        }

        return "";
    },

    /**
     * 문자를 integer로 변환
     * str : 숫자, 문자열
     */
    toInt: function(str) {
        var type = typeof str;

        if (type === "string" || type === "number") {
            var temp = this.trim(str) + "";

            if (temp != "" && !isNaN(temp)) {
                return parseInt(temp, 10);
            }
        }

        return 0;
    },

    /**
     * 문자를 float로 변환
     * str   : 숫자, 문자열
     * point : 소수점 자릿수
     * max   : 최대값
     */
    toFloat: function(str, point, max) {
        var type = typeof str;
        var temp = "";

        if (type === "string" || type === "number") {
            temp = this.trim(str) + "";

            if (temp != "" && !isNaN(temp)) {
                var pow = Math.pow(10, point);
                temp = Math.floor(temp * pow) / pow;
                temp = Number(temp.toFixed(point));

                if (temp > max) {
                    temp = max;
                }

                return temp;
            }
        }

        return Number((0).toFixed(point));
    },

    /**
     * 숫자 구하기
     * str : 문자열
     */
    toNumber: function(str) {
        if (typeof str === "string") {
            var symbol = "";

            // 음수 기호 구하기
            if (str.indexOf("-") == 0) {
                symbol = "-";
            }

            return symbol + str.replace(/[^0-9]+/g, "");
        }

        return str;
    },

    /**
     * 소수점 구하기
     * str : 문자열
     */
    toDecimal: function(str) {
        if (typeof str === "string") {
            var symbol = "";

            // 음수 기호 구하기
            if (str.indexOf("-") == 0) {
                symbol = "-";
            }

            var num = str.replace(/[^0-9\.]+/g, "");
            var pointCnt = (num.match(/\./g) || []).length;

            if (pointCnt > 1) {
                var tempArray = num.split(".");
                num = tempArray[0] + "." + tempArray[1];
            }

            return symbol + num;
        }

        return str;
    },

    /**
     * 천단위 콤마 추가
     * str : 숫자, 문자열
     */
    addThousandComma: function(str) {
        var type = typeof str;

        if (type === "string" || type === "number") {
            var temp = this.trim(str) + "";

            if (temp != "") {
                return temp.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            }
        }

        return str;
    },

    /**
     * html 태그 제거
     * str : 문자열
     */
    removeTag: function(str) {
        if (typeof str === "string") {
            return str.replace(/<(\s*\/)?(\s*[a-z]*)(\s[a-z]*=[^>]*)?(\s)*(\/\s*)?>/gi, "");
        }

        return str;
    },

    /**
     * html xss filter
     * str : 문자열
     */
    escapeHtml: function(str) {
        if (typeof str === "string") {
            var temp = this.trim(str);

            if (temp != "") {
                var symbolMap = {
                    "&": "&amp;",
                    "<": "&lt;",
                    ">": "&gt;",
                    '"': "&quot;",
                    "'": "&#039;"
                };

                return temp.replace(/[&<>"']/g, function(key) { return symbolMap[key]; });
            }
        }

        return str;
    },

    /**
     * 엔터 구분자로 배열 구하기
     * str : 문자열
     */
    toEnterArray: function(str) {
        var resultList = [];

        if (this.isNotNull(str)) {
            var textList = str.split("\n");
            var textCnt = textList.length;
            var m = 0;

            for (m = 0; m < textCnt; m++) {
                if (this.isNotNull(textList[m])) {
                    resultList.push(this.trim(textList[m]));
                }
            }
        }

        return resultList;
    },

    /**
     * 배열 건수
     */
    arrayCount: function(itemList) {
        var itemCnt = 0;

        if (Array.isArray(itemList)) {
            itemCnt = itemList.length;
        }

        return itemCnt;
    },

    /**
     * 배열 중복 체크
     */
    isArrayDup: function(itemList, key, val) {
        var itemCnt = this.arrayCount(itemList);
        var m = 0;
        var dupYn = false;

        for (m = 0; m < itemCnt; m++) {
            // 중복 체크
            if (itemList[m][key] == val) {
                dupYn = true;
                break;
            }
        }

        return dupYn;
    },

    getCodeNm: function(codeList, code) {
    	var cdNm = '';
    	
    	if ( !!codeList && codeList.length > 0 ) {
	    	var codeInfo = _.filter(codeList, function(item) {
				return item.cdVal === code;
			});
	    	
	    	if ( !!codeInfo && codeInfo.length > 0 ) {
	    		cdNm = codeInfo[0].cdNm;
	    	}
    	}
    	
    	return cdNm;
    },
    
    /**
     * Session Storage 값 조회
     */
    getStorageData: function(key, useCache){
        var nowTime = new Date().getTime();
        var dataObj = JSON.parse(window.localStorage.getItem(key));
//        var dataObj = JSON.parse(window.sessionStorage.getItem(key));

        if (dataObj == null) {
            return null;
        }

        if (useCache !== undefined && useCache == false) {
            return dataObj;
        } else {
            if (dataObj['expireTime'] != null && nowTime < dataObj['expireTime']) {
                return dataObj;
            } else {
                return null;
            }
        }
    },

    /**
     * Session Storage 값 설정
     */
    setStorageData: function(key, obj, useCache) {
        if (useCache === undefined || useCache != false) {
            var expireTime = new Date();
            expireTime.setMinutes(expireTime.getMinutes() + 30);
            obj['expireTime'] = expireTime.getTime();
        }

        window.localStorage.setItem(key, JSON.stringify(obj));
//        window.sessionStorage.setItem(key, JSON.stringify(obj));
    },
    
    removeStorageData: function(key) {
//    	window.localStorage.removeItem(key);
    	window.sessionStorage.removeItem(key);
    },
    
    clearStorageData: function() {
    	window.localStorage.clear();
//    	window.sessionStorage.clear();
    },
    
    /**
     * 창 닫을시, 부모창 reload
     */
    beforeunload: function(callback) {
        window.addEventListener("beforeunload", function() {
            window.opener.location.reload();
        });
    }

	
};
