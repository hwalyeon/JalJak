/**
 * File Name   : event.mixin.js
 * Description : 이벤트 mixin
 * Author      : boneis
 * Date        : 2018.11.09
 */

var EventMixin = {

    methods: {
        /**
         * ibox 토글
         */
        onBoxToggle: function(target, dispYn, slideYn) {
            var ibox = $(target).closest("div.ibox");
            var button = $(target).find("i");
            var content = ibox.children(".ibox-content");

            // 토글
            if (dispYn == "auto") {
                if (content.css("display") == "none") {
                    dispYn = "Y";
                } else {
                    dispYn = "N";
                }
            }

            // 슬라이드 Y
            if (WebUtil.isNull(slideYn)) {
                slideYn = "Y";
            }

            if (dispYn == "Y") {
                if (slideYn == "Y") {
                    content.slideDown(400, function() {
                        button.removeClass("fa-chevron-down").addClass("fa-chevron-up");
                        ibox.removeClass("border-bottom");
                        ibox.resize();
                    });
                } else {
                    content.css("display", "");
                    button.removeClass("fa-chevron-down").addClass("fa-chevron-up");
                    ibox.removeClass("border-bottom");
                    ibox.resize();
                }
            } else {
                if (slideYn == "Y") {
                    content.slideUp(400, function() {
                        button.removeClass("fa-chevron-up").addClass("fa-chevron-down");
                        ibox.addClass("border-bottom");
                        ibox.resize();
                    });
                } else {
                    content.css("display", "none");
                    button.removeClass("fa-chevron-up").addClass("fa-chevron-down");
                    ibox.addClass("border-bottom");
                    ibox.resize();
                }
            }
        }
    }

};
