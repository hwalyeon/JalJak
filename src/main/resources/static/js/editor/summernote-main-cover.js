/** 메인 이미지 지정 */
(function (factory) {
    /* global define */
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define(['jquery'], factory);
    } else if (typeof module === 'object' && module.exports) {
        // Node/CommonJS
        module.exports = factory(require('jquery'));
    } else {
        // Browser globals
        factory(window.jQuery);
    }
}(function ($) {

    $.extend($.summernote.options, {
        mainCover: {
            mainFn: function() { return new Promise(((resolve, reject) => {})); },
        }
    });

    $.extend($.summernote.plugins, {
        mainCover: function (context) {
            const $this = this;
            const ui = $.summernote.ui,
                $editable = context.layoutInfo.editable,
                $editor = context.layoutInfo.editor,
                $note = context.layoutInfo.note,
                options = context.options,
                lang = options.langInfo;

            context.memo('button.mainCover', function () {
                const button = ui.button({
                    id: 'mainCoverBtn',
                    contents: '<i class="bi-search"/>',
                    tooltip: "메인 이미지",
                    click: function () {
                        context.invoke('mainCover.setMain');
                    }
                });

                return button.render();
            });

            this.initialize = function () {
                //
            }

            this.destroy = function() {
                //
            };

            this.bindEnterKey = function($input, $btn) {
                $input.on('keypress', function(event) {
                    if (event.keyCode === 13) {
                        $btn.trigger('click');
                    }
                });
            };

            this.setMain = function() {
                const $img = $($editable.data('target'));
                options.mainCover.mainFn($img, context);
            }
        }
    });

}));