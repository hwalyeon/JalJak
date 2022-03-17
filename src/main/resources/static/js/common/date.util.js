/**
 * File Name   : date.util.js
 * Description : 날짜 유틸 자바스크립트
 * Author      : boneis
 * Date        : 2019.07.03
 */

var DateUtil = {

    /**
     * datePicker 바인딩 초기화 - 생성
     * id  : 엘리먼트 아이디
     * obj : 바인딩 객체
     * key : 바인딩 키
     */
    initDatePickerBind: function(id, obj, key) {
        $("#" + id).datepicker({
            todayBtn: "linked",
            keyboardNavigation: false,
            forceParse: false,
            calendarWeeks: true,
            format: dateFormatPattern,
            autoclose: true,
            todayHighlight: true
        }).on("changeDate", function() {
            var value = "";

            if (this.tagName.toLowerCase() == "input") {
                value = $(this).val();
            } else {
                value = $("input", this).val();
            }

            if (WebUtil.isNotNull(value)) {
                obj[key] = value;
            } else {
                $(this).datepicker("update", obj[key]);
            }
        });
    },

    /**
     * datePicker 변경 초기화 - 생성
     * id           : 엘리먼트 아이디
     * onChangeDate : 날짜 변경 이벤트 함수
     */
    initDatePickerChange: function(id, onChangeDate) {
        $("#" + id).datepicker({
            todayBtn: "linked",
            keyboardNavigation: false,
            forceParse: false,
            calendarWeeks: true,
            format: dateFormatPattern,
            autoclose: true,
            todayHighlight: true
        }).on("changeDate", function() {
            if (typeof onChangeDate === "function") {
                onChangeDate();
            }
        });
    },

    /**
     * datePicker 클리어 - 날짜 초기화
     * id : 엘리먼트 아이디
     */
    clearDatePicker: function(id) {
        $("#" + id).datepicker("update", new Date()).datepicker("update", "");
    },

    /**
     * datePicker 변경
     * id  : 엘리먼트 아이디
     * val : 변경 값
     */
    updateDatePicker: function(id, val) {
        if (WebUtil.isNotNull(val)) {
            $("#" + id).datepicker("update", val);
        } else {
            $("#" + id).datepicker("update", new Date()).datepicker("update", "");
        }
    },

    /**
     * datePicker 바인딩
     * id  : 엘리먼트 아이디
     * obj : 바인딩 객체
     * key : 바인딩 키
     */
    bindDatePicker: function(id, obj, key) {
        var elmt = $("#" + id);
        var value = "";

        if (elmt[0].tagName.toLowerCase() == "input") {
            value = elmt.val();
        } else {
            value = $("input", elmt).val();
        }

        if (WebUtil.isNotNull(value)) {
            obj[key] = value;
        } else {
            elmt.datepicker("update", obj[key]);
        }
    },

    /**
     *  현재 날짜, 시간 반환 (yyyy-MM-dd HH:mm:ss)
     */
    getCurrentDt: function () {

        var date = new Date();
        var s =
            DateUtil.prefixZeros(date.getFullYear(), 4) + '-' +
            DateUtil.prefixZeros(date.getMonth() + 1, 2) + '-' +
            DateUtil.prefixZeros(date.getDate(), 2) + ' ' +

            DateUtil. prefixZeros(date.getHours(), 2) + ':' +
            DateUtil. prefixZeros(date.getMinutes(), 2) + ':' +
            DateUtil.prefixZeros(date.getSeconds(), 2);
        return s;

    },

    /**
     * 한 자리수의 경우 텍스트 앞 0 처리
     * num : 입력받은 숫자
     * digits : 노출 되야 할 자리수
     */
    prefixZeros: function (num, digits) {
        var zero = '';
        num = num.toString();

        if (num.length < digits) {
            for (i = 0; i < digits - num.length; i++)
                zero += '0';
        }
        return zero + num;
    }

};
