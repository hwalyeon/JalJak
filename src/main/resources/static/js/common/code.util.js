/**
 * File Name   : code.util.js
 * Description : 코드 유틸 자바스크립트
 * Author      : boneis
 * Date        : 2019.06.03
 */

var CodeUtil = {

    /**
     * 시간 목록 구하기
     */
    getHourList: function() {
        var resultList = [];
        var m = 0;
        var hour = "";

        for (m = 0; m <= 23; m++) {
            if (m < 10) {
                hour = "0" + m;
            } else {
                hour = m + "";
            }

            resultList.push({
                value: hour,
                text: hour + "시"
            });
        }

        return resultList;
    },

    /**
     * 분 목록 구하기
     */
    getMinuteList: function() {
        var resultList = [];
        var m = 0;
        var mint = "";

        for (m = 0; m <= 59; m++) {
            if (m < 10) {
                mint = "0" + m;
            } else {
                mint = m + "";
            }

            resultList.push({
                value: mint,
                text: mint + "분"
            });
        }

        return resultList;
    },

    /**
     * 기간 날짜 목록 구하기
     */
    getPeriodDateList: function() {
        return [
            { value: "TODAY",            text: "오늘" },
            { value: "YESTERDAY",        text: "어제" },
            { value: "RECENT_WEEK",      text: "최근한주" },
            { value: "THIS_WEEK",        text: "이번주" },
            { value: "LAST_WEEK",        text: "지난주" },
            { value: "RECENT_MONTH",     text: "최근한달" },
            { value: "THIS_MONTH",       text: "이번달" },
            { value: "LAST_MONTH",       text: "지난달" },
            { value: "PRE_THREE_MONTH",  text: "최근3개월" },
            { value: "PRE_SIX_MONTH",    text: "최근6개월" }
        ];
    },

    /**
     * 거래처 목록 조회
     */
    selectSelAcntList: function(callback) {
        AjaxUtil.get({
            url: "/system/rest/sellAccountCodeList",
            param: null,
            success: function(response) {
                if (typeof callback === "function") {
                    callback(response.data.result);
                }
            }
        });
    },

    /**
     * 거래처 MD 목록 조회
     */
    selectSelAcntMdList: function(selAcntNo, callback) {
        AjaxUtil.get({
            url: "/system/rest/selAcntUser/" + selAcntNo,
            param: null,
            success: function(response) {
                if (typeof callback === "function") {
                    callback(response.data.result);
                }
            }
        });
    },

    /**
     * MD 목록 조회
     */
    selectMdList: function(callback) {
        AjaxUtil.get({
            url: "/system/rest/users",
            param: {
                empClf: "04",
                pagingSet: false
            },
            success: function(response) {
                if (typeof callback === "function") {
                    callback(response.data.result);
                }
            }
        });
    },

    /**
     * 표준카테고리 목록 조회
     */
    selectStdCtgrList: function(siteCd, hgrnkCtgrNo, callback) {
        AjaxUtil.get({
            url: "/system/rest/getStdCategories/" + hgrnkCtgrNo + "/" + siteCd,
            param: null,
            success: function(response) {
                if (typeof callback === "function") {
                    callback(response.data.result);
                }
            }
        });
    },

    /**
     * 전시카테고리 목록 조회
     */
    selectDispCtgrList: function(siteCd, level, hgrnkCtgrNo, callback) {
        AjaxUtil.get({
            url: "/display/rest/selectDispCtgr",
            param: {
                level: level,
                dispCtgrNo: hgrnkCtgrNo,
                siteCd: siteCd
            },
            success: function(response) {
                if (typeof callback === "function") {
                    callback(response.data.result);
                }
            }
        });
    },

    /**
     * 코드 참조값 목록 조회
     */
    selectCodeRefList: function(grpCd, refVal, callback) {
        AjaxUtil.get({
            url: "/common/rest/getCodeList/" + grpCd,
            param: { cdValue1: refVal },
            success: function(response) {
                if (typeof callback === "function") {
                    callback(response.data.result);
                }
            }
        });
    }

};
