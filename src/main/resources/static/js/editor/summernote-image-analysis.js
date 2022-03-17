/**
 * summernote 에서 이미지 노타 분석 데이터가 있을 경우, 편집기 보기
 */
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
    $.extend(true, $.summernote.lang, {
        'en-US': {
            imageAnalysis: {
                edit: 'Image Object Detection',
                titleLabel: 'title',
                tooltip: 'Image Detection',
                dialogSaveBtnMessage: 'Update',
                dialogCloseBtnMessage: 'Close',
                dialogTitle: 'Image Object Detection',
            }
        },
        'ko-KR': {
            imageAnalysis: {
                edit: '이미지 속의 상품 찾기',
                titleLabel: '제목',
                tooltip: '이미지 속의 상품 찾기',
                dialogSaveBtnMessage: '저장',
                dialogCloseBtnMessage: '닫기',
                dialogTitle: '이미지 속의 상품 찾기',
            }
        }
    });

    $.extend($.summernote.options, {
        imageAnalysis: {
            icon: '<i class="note-icon"><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 14 14" width="14" height="14"><path d="M 8.781,11.11 7,11.469 7.3595,9.688 8.781,11.11 Z M 7.713,9.334 9.135,10.7565 13,6.8915 11.5775,5.469 7.713,9.334 Z M 6.258,9.5 8.513,7.12 7.5,5.5 6.24,7.5 5,6.52 3,9.5 6.258,9.5 Z M 4.5,5.25 C 4.5,4.836 4.164,4.5 3.75,4.5 3.336,4.5 3,4.836 3,5.25 3,5.6645 3.336,6 3.75,6 4.164,6 4.5,5.6645 4.5,5.25 Z m 1.676,5.25 -4.176,0 0,-7 9,0 0,1.156 1,0 0,-2.156 -11,0 0,9 4.9845,0 0.1915,-1 z"/></svg></i>',
            captionText: '이미지 상품 감지',
            fetchDataFn: function() { return new Promise(((resolve, reject) => {})); },
            saveFn: function() {},
        }
    });

    $.extend($.summernote.plugins, {
        imageAnalysis: function(context) {
            const $this = this;
            const ui = $.summernote.ui,
                $editable = context.layoutInfo.editable,
                $editor = context.layoutInfo.editor,
                $note = context.layoutInfo.note,
                options = context.options,
                lang = options.langInfo;

            context.memo('button.imageAnalysis', function() {
                const button = ui.button({
                    contents: options.imageAnalysis.icon,
                    tooltip: lang.imageAnalysis.tooltip,
                    click: function() {
                        context.invoke('imageAnalysis.show');
                    }
                });

                return button.render();
            });

            this.initialize = function() {
                const $container = options.dialogsInBody ? $(document.body) : $editor;
                const body = `
                    <div class="row">
                        <div class="col-9">
                            <div class="nota-contents" style="position: relative;">
                                <img src="/image/no_img.png" data-image/>
                                <div class="nota-meta-data"></div>
                            </div>
                        </div>
                        <div class="col-3">
                           <div class="list-group" id="nota-meta-list"></div>
                        </div>
                    </div>`; //TODO
                const footer = `
                    <button type="button" class="btn btn-primary note-save-btn">${lang.imageAnalysis.dialogSaveBtnMessage}</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">${lang.imageAnalysis.dialogCloseBtnMessage}</button>
                `;

                this.$dialog = ui.dialog({
                    title: lang.imageAnalysis.dialogTitle,
                    body: body,
                    footer: footer
                }).render().appendTo($container);
                $(this.$dialog).find(".modal-dialog").addClass("modal-xl");
            };

            this.destroy = function() {
                ui.hideDialog(this.$dialog);
                this.$dialog.remove();
            };

            this.bindEnterKey = function($input, $btn) {
                $input.on('keypress', function(event) {
                    if (event.keyCode === 13) {
                        $btn.trigger('click');
                    }
                });
            };

            this.show = function(node) {
                this.$dialog.find("img[data-image]").off("load");
                const $img = $(node || $editable.data('target'));
                const _imgInfo = {
                    title: $img.attr('title'),
                    src: $img.attr('src'),
                    alt: $img.attr('alt'),
                    width: $img.width(),
                    height: $img.height(),
                    naturalWidth: $img.attr('naturalWidth'),
                    naturalHeight: $img.attr('naturalHeight'),
                    target: $img.get(0),
                };

                const img = new Image();
                img.onload = function() {
                    _imgInfo.naturalWidth = img.width;
                    _imgInfo.naturalHeight = img.height;

                    const _img = $this.$dialog.find("img[data-image]");
                    _img.on('load', () => {
                        $this.showLinkDialog(_imgInfo);
                    });
                    _img.attr("src", $img.attr("src"))
                }
                img.src = $img.attr("src");
            };

            this.showLinkDialog = function(imgInfo) {
                return $.Deferred(function(deferred) {
                    const $saveBtn = $this.$dialog.find(".note-save-btn");
                    const $img = $this.$dialog.find("img[data-image]").get(0);
                    const $meta = $this.$dialog.find(".nota-meta-data");
                    const $metaList = $this.$dialog.find("#nota-meta-list");
                    const metaDataList = [];

                    options.imageAnalysis.fetchDataFn(imgInfo.target, imgInfo.src).then((metaInfos) => {
                        const ratio = {
                            x: $($img).width() / parseInt(imgInfo.naturalWidth),
                            y: $($img).height() / parseInt(imgInfo.naturalHeight),
                        };
                        let index = 1;
                        metaInfos.forEach((data, pos) => {
                            metaDataList.push({...data, useYn: 'Y'});
                            if (data.metaTypCd === "CM041") {
                                const rectStyle = {
                                    left: Math.round(data.metaLocX1 * ratio.x),
                                    top: Math.round(data.metaLocY1 * ratio.y),
                                    width: Math.round((data.metaLocX2 - data.metaLocX1) * ratio.x),
                                    height: Math.round((data.metaLocY2 - data.metaLocY1) * ratio.y),
                                };
                                const rect = document.createElement("div");
                                rect.style.left = rectStyle.left + "px";
                                rect.style.top = rectStyle.top + "px";
                                rect.style.width = rectStyle.width + "px";
                                rect.style.height = rectStyle.height + "px";
                                rect.className = "nota-meta-box draggable ui-widget-content";
                                rect.setAttribute("data-index", index);
                                rect.innerHTML = `
                                    <span data-toggle="tooltip" data-placement="bottom" title="${data.metaNm}">${index}</span>
                                    <div class="nota-image-mask">
                                        <div class="nota-image-mask-corner left-top"></div>
                                        <div class="nota-image-mask-corner left-bottom"></div>
                                        <div class="nota-image-mask-corner right-top"></div>
                                        <div class="nota-image-mask-corner right-bottom"></div>
                                    </div>`;
                                $meta.append(rect);

                                $metaList.append(`
                                    <a href="javascript:void(0)" class="list-group-item list-group-item-action" data-meta-id="${data.metaNo}" data-index="${index}" data-pos="${pos}">
                                        <span class="badge badge-info">${index}</span>
                                        ${data.metaNm}
                                        <button type="button" class="close" aria-label="Delete"><i class="bi-trash" size="small"/></button>
                                    </a>`);
                                index++;
                            }
                        });
                        $($meta).find(".nota-meta-box span").tooltip();

                        // 메타정보 목록 클릭시 리스트 active 처리 및 이미지에 border 표시
                        $($metaList).find("a.list-group-item-action").on("click", (event) => {
                            event.preventDefault();
                            const item = event.target;
                            const index = $(item).data("index");
                            $($metaList).find("a.list-group-item-action").removeClass("active");
                            $($meta).find(".nota-meta-box").removeClass("active");
                            $(item).addClass("active");
                            $($meta).find(`.nota-meta-box[data-index=${index}]`).addClass("active");
                        });

                        //목록에서 x 버튼 클릭시 메타정보 삭제(마킹) 처리, 저장시 실제 삭제...
                        $($metaList).find("a.list-group-item > button.close").on("click", (event) => {
                            event.preventDefault();
                            const btn = event.target;
                            const item = $(btn).closest("a[data-index]");
                            item.toggleClass("list-group-item-dark");
                            item.toggleClass("delete_item");
                            const index = $(item).data("index");
                            $($meta).find(`.nota-meta-box[data-index=${index}]`).toggleClass("delete_item");
                            if (item.hasClass("delete_item")) {
                                metaDataList[parseInt(item.data("pos"))].useYn = "N";
                            }
                            else {
                                metaDataList[parseInt(item.data("pos"))].useYn = "Y";
                            }
                        })
                    });

                    ui.onDialogShown($this.$dialog, function() {
                        context.triggerEvent('dialog.shown');
                        $saveBtn.click(function(e) {
                            e.preventDefault();
                            //메타 수정 저장, 저장된 이미지의 경우 기존 메타 정보 수정, 새로운 이미지 일 경우, 메타 정보 추가.
                            options.imageAnalysis.saveFn(metaDataList);
                            deferred.resolve({});
                        });
                    });

                    ui.onDialogHidden($this.$dialog, function() {
                        $meta.empty();
                        $metaList.empty();
                        $saveBtn.off("click");

                        if (deferred.state() === 'pending') {
                            deferred.reject();
                        }
                    });
                    ui.showDialog($this.$dialog);
                });
            };
        }
    });
}));
