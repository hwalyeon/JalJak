/**
 * Summernote Fixed Toolbar
 *
 * This is a plugin for Summernote (www.summernote.org) WYSIWYG editor.
 * It will keep the toolbar fixed to the top of the screen as you scroll.
 *
 * @author Jason Byrne, FloSports <jason.byrne@flosports.tv>
 *
 */

(function(factory) {
    /* global define */
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define(['jquery'], factory);
    }
    else {
        // Browser globals: jQuery
        factory(window.jQuery);
    }
}(function($) {

    $.summernote.addPlugin({
        name: 'fixedToolbar',
        init: function(layoutInfo) {
            // Find editor
            var $editor = layoutInfo.holder().siblings('.note-editor'),
                $toolbar = $editor.find('.note-toolbar');
            // Scrolling event
            var repositionToolbar = function() {
                var windowTop = $(window).scrollTop(),
                    editorTop = $editor.offset().top,
                    editorBottom = editorTop + $editor.height();
                if (windowTop > editorTop && windowTop < editorBottom) {
                    $toolbar.css('position', 'fixed');
                    $toolbar.css('top', '0px');
                    $toolbar.css('width', $editor.width() + 'px');
                    $toolbar.css('z-index', '99999');
                    $editor.css('padding-top', '42px');
                }
                else {
                    $toolbar.css('position', 'static');
                    $editor.css('padding-top', '0px');
                }
            };
            // Move it
            $(window).scroll(repositionToolbar);
            repositionToolbar();
        }
    });

}));