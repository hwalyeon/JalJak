/**
 * File Name   : ajax.util.js
 * Description : 아작스 유틸 자바스크립트
 * Author      : boneis
 * Date        : 2019.07.11
 */

var AjaxUtil = {

    syncGet: function(opt) {
        // 데이터 유형
        opt.dataType = WebUtil.nvl(opt.dataType, "json");

        return $.ajax({
            url: opt.url,
            method: "GET",
            data: opt.param,
            dataType: opt.dataType,
            async: false,
            cache: false,
            traditional: WebUtil.nvl(opt.traditional, false),
            success: function(response) {
                AjaxUtil.ajaxSuccess(opt, response);
            },
            error: function(xhr) {
                AjaxUtil.ajaxError(opt, xhr);
            }
        });
    },

    /**
     * GET 방식
     */
    get: function(opt) {
        // 데이터 유형
        opt.dataType = WebUtil.nvl(opt.dataType, "json");

        return $.ajax({
            url: opt.url,
            method: "GET",
            data: opt.param,
            dataType: opt.dataType,
            cache: false,
            traditional: WebUtil.nvl(opt.traditional, false),
            success: function(response) {
                AjaxUtil.ajaxSuccess(opt, response);
            },
            error: function(xhr) {
                AjaxUtil.ajaxError(opt, xhr);
            }
        });
    },

    /**
     * POST 방식
     */
    post: function(opt) {
        var param = opt.param;
        var reqType = WebUtil.nvl(opt.reqType, "json");
        var contType = "application/x-www-form-urlencoded; charset=UTF-8";

        // 파라미터 json 설정
        if (reqType == "json") {
            param = JSON.stringify(param);
            contType = "application/json; charset=UTF-8";
        }

        // 데이터 유형
        opt.dataType = WebUtil.nvl(opt.dataType, "json");
        
        return $.ajax({
            url: opt.url,
            method: "POST",
            data: param,
            dataType: opt.dataType,
            cache: false,
            traditional: WebUtil.nvl(opt.traditional, false),
            contentType: contType,
            success: function(response) {
                AjaxUtil.ajaxSuccess(opt, response);
            },
            error: function(xhr) {
                AjaxUtil.ajaxError(opt, xhr);
            }
        });
    },

    /**
     * FormData 방식
     */
    form: function(opt) {
        // FormData 체크
        if ((opt.param instanceof FormData) === false) {
            alert("FormData 파라미터를 설정해주세요.");
            return false;
        }

        // 데이터 유형
        opt.dataType = "json";

        return $.ajax({
            url: opt.url,
            method: "POST",
            data: opt.param,
            dataType: opt.dataType,
            cache: false,
            processData: false,
            contentType: false,
            success: function(response) {
                AjaxUtil.ajaxSuccess(opt, response);
            },
            error: function(xhr) {
                AjaxUtil.ajaxError(opt, xhr);
            }
        });
    },

    /**
     * ajax 성공
     */
    ajaxSuccess: function(opt, response) {
        if (typeof opt.success === "function") {
            if (opt.dataType == "json") {
                if (response.rtnCd === "00") {
                    opt.success(response);
                } else {
                    var errMsg = response.rtnMsg;

                    if (WebUtil.isNull(errMsg)) {
                        errMsg = '처리 중 오류가 발생하였습니다.';
                    }

                    if (typeof opt.error === "function") {
                        opt.error(errMsg);
                    } else {
                        Swal.alert([errMsg, "error"]);
                    }
                }
            } else {
                opt.success(response);
            }
        }
    },

    /**
     * ajax 에러
     */
    ajaxError: function(opt, xhr) {
    	
    	if ( xhr.status == 403 ) {
    		ajaxErrorHandle(xhr);
    	} else {
    		if (typeof opt.error === "function") {
                var response = xhr.responseJSON;
                var errMsg = '오류가 발생하였습니다.';

                if (WebUtil.isObject(response) && WebUtil.isNotNull(response.rtnMsg)) {
                    errMsg = response.rtnMsg;
                }

                opt.error(errMsg);
            } else {
                ajaxErrorHandle(xhr.responseJSON);
            }
    	}
    }
};
