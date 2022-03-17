/** summernote 웹 에디터 적용 테스트
 *  테스트 용도로만
 * */

const summernote = {
    template: `<textarea class="summernote" :id="summerNoteId" name="summerNoteId" ref="summerNoteId"></textarea>`,
    data() {
        return {
            changeValue: '',
        }
    },
    props:{
        id: {
            type: String,
            required: false,
            default: 'summernote',
        },
        summerNoteId:{
            type: String,
            required: false,
            default: 'summernote',
        },
        value: {
            type: String,
            required: false,
            default : ''
        },

        options: {
            type: Object,
            required: false,
            default() {
                return {
                    dialogsInBody: false,
                    height: null,
                    notUseVideo: false,
                    maxImageWidth: -1,
                    imageTypes: ["image/jpg", "image/jpeg", "image/png", "image/gif", "image/webp"]
                }
            }
        }
    },
    watch: {
        value: function(value) {
            if(this.changeValue !== value) {
                //set
                $('#' + this.summerNoteId).summernote('code', value);
            }
        }
    },
    methods:{
        initEditor() {
            let $this = this;
            let customOptions = [
                'custom', []
            ];

            $.extend(true, $.summernote.lang, {
                'en-US': {
                    video: {
                        providers: '(Youtube)'
                    }
                },
                'ko-KR': {
                    video: {
                        providers: '(Youtube 사용 가능)'
                    }
                }
            });

            let options = {
                dialogsInBody: this.options.dialogsInBody,
                focus: false,
                lang: 'ko-KR',
                height: this.options.height,
                fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '24', '36'],
                toolbar: [
                    ['fontname', ['fontname']],
                    ['fontsize', ['fontsize']],
                    ['style', ['bold', 'italic', 'underline','clear']],
                    ['color', ['forecolor','color']],
                    ['table', ['']],
                    ['para', ['ul', 'ol', 'paragraph']],
                    ['height', ['height']],
                    ['insert',['']],
                    ['view', ['']]
                ],
                fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
                // toolbar: [
                //     ['Paragraph style', ['style']],
                //     ['style', ['bold', 'italic', 'underline', 'clear']],
                //     ['font', ['strikethrough', 'superscript', 'subscript']],
                //     ['Font Style', ['fontname']],
                //     ['fontsize', ['fontsize']],
                //     ['color', ['color']],
                //     ['para', ['ul', 'ol', 'paragraph']],
                //     ['Insert', ['picture', 'table', 'video']],
                //     ['Misc', ['fullscreen', 'codeview']],
                // ],
                popover: {
                    image: [
                        //['remove', ['removeMedia']],
                    ]
                },
                callbacks: {},
            };

            /*
            if (this.options.metaOptions.view) {
                //customOptions[1].push('imageAnalysis');

                options['imageAnalysis'] = {
                    icon: '<i class="bi-app-indicator"/>',
                    fetchDataFn: this.options.metaOptions.fetchDataFn || function() { return new Promise(((resolve, reject) => {})); },
                    saveFn: this.options.metaOptions.saveFn || function() {},
                }
            }

            if (this.options.mainFn) {
                //customOptions[1].push('mainCover');
                options['mainCover'] = {
                    mainFn: this.options.mainFn
                };
            }

            if (customOptions[1].length > 0) {
                options.popover.image = [
                    customOptions,
                    ['remove', ['removeMedia']],
                ];
            }

            if (this.options.change) {
                options.callbacks['onChange'] = this.options.change;
            }

            if (this.options.init) {
                options.callbacks['onInit'] = this.options.init;
            }

            if (this.options.imageUpload) {
                options.callbacks['onImageUpload'] = this.options.imageUpload;
            }
            */


            //이미지 최대 가로 크기가 설정될 경우
            if (!isNaN(parseInt(this.options.maxImageWidth)) && this.options.maxImageWidth > 0) {
                const imageUtils = new EditorImageLoader({maxImageFileSize: 0, maxWidth: this.options.maxImageWidth, acceptTypes: this.options.imageTypes});
                options.callbacks['onImageUpload'] = (files) => {
                    if (!files || files.length < 1) {
                        return;
                    }
                    Array.from(files).forEach(file => {
                        console.log(file);
                        const img = imageUtils.insertImageFromURL(file);
                        if (img) {
                            img.then(imgNode => {
                                let wrapper = document.createElement('p');
                                wrapper.appendChild(imgNode);

                                let innerTag = document.createElement('p');
                                innerTag.appendChild(document.createElement('br'));

                                wrapper.appendChild(innerTag);

                                $("#" + this.summerNoteId).summernote("insertNode", wrapper);

                            }).catch(err => {
                                alert(err.message);
                            });
                        }
                    })
                }
            }

            $('#' + this.summerNoteId).summernote(options)
                .on('summernote.change', function(we, contents, $editable) {
                    $this.changeValue = contents;
                    $this.$emit("input", $this.changeValue);
                });

            // $('#' + this.id).summernote("code", this.value);

            // 초기값 가운데 정렬
            $('#' + this.summerNoteId).summernote('justifyCenter');

            //youtube 만 입력 가능하도록 변경 ---
            const customVideoDialog = $.summernote.options.modules.videoDialog;
            customVideoDialog.prototype.createVideoNode = function createVideoNode(url) {
                // video url patterns(youtube, instagram, vimeo, dailymotion, youku, mp4, ogg, webm)
                const ytRegExp = /\/\/(?:(?:www|m)\.)?(?:youtu\.be\/|youtube\.com\/(?:embed\/|v\/|watch\?v=|watch\?.+&v=))([\w|-]{11})/;
                const ytMatch = url.match(ytRegExp);
                let $video;

                if (ytMatch && ytMatch[1].length === 11) {
                    const youtubeId = ytMatch[1];

                    $video = $('<iframe>').attr('frameborder', 0).attr('src', '//www.youtube.com/embed/' + youtubeId).attr('width', '640').attr('height', '360');
                } else {
                    // this is not a known video link. Now what, Cat? Now what?
                    Swal.alert(["Youtube video 만 입력 가능합니다.", "warning"]);
                    return false;
                }

                $video.addClass('note-video-clip');
                return $video[0];
            }

        },
        focus: function() {
            this.$refs.summerNoteId.focus();
        }
    },
    mounted:function () {
        $(document).ready(() => {
            this.initEditor();
        });
    }
};

class EditorImageLoader {
    constructor(options = {}) {
        this.maxImageFileSize = options.maxImageFileSize || 0;
        this.maxWidth = options.maxWidth || 0;
        this.acceptTypes = options.imageTypes || ["image/jpg", "image/jpeg", "image/png", "image/gif", "image/webp"];
    }

    insertImage(src, filename, filetype) {
        return new Promise((resolve, reject) => {
            // target
            this.createImage(src, filename).then(imgNode => {
                this.resizeImageIfNecessary(imgNode, filetype).then(resizeNode => {
                    resolve(resizeNode);
                });
            });
        });
    }

    insertImageFromURL(file) {
        return new Promise((resolve, reject) => {
            const filename = file.name;
            const filetype = file.type;
            if (this.acceptTypes.indexOf(filetype) < 0) {
                console.error("insertImageFromURL error: UNSUPPORTED_FILE_TYPE");
                reject({status: false, errorCode: "UNSUPPORTED_FILE_TYPE", message: "지원하지 않는 파일 유형입니다.[파일유형:" + filetype + "]"});
                return;
            }
            if (this.maxImageFileSize > 0 && this.maxImageFileSize < file.size) {
                console.error("insertImageFromURL error: MAX_FILE_SIZE");
                reject({status: false, errorCode: "MAX_FILE_SIZE", message: "최대 파일크기를 초과했습니다."});
                return;
            }
            else {
                this.readFileAsDataURL(file).then(dataURL => {
                    this.insertImage(dataURL, filename, filetype).then(node => resolve(node));
                }).catch((err) => {
                    // trigger error
                    console.error("insertImageFromURL error", err);
                    reject({status: false, errorCode: "READ_ERROR", message: err.message});
                });
            }
        });
    }

    readFileAsDataURL(file) {
        return new Promise((resolve, reject) => {
            const fileReader = new FileReader();
            fileReader.onload = (e) => {
                const dataURL = e.target.result;
                resolve(dataURL);
            };
            fileReader.onerror = (err) => {
                reject(err);
            };
            fileReader.readAsDataURL(file);
        });
    }

    createImage(url, filename) {
        return new Promise((resolve, reject) => {
            const img = document.createElement("img");
            img.onload = () => {
                img.onerror = null;
                resolve(img);
            };
            img.onerror = () => {
                img.onload = null;
                reject(img);
            }
            //img.style.display = "none";
            img.setAttribute("data-filename", filename);
            img.src = url;
        });
    }

    resizeImageIfNecessary(image, filetype) {
        return new Promise(resolve => {
            const canvas = document.createElement("canvas");
            let width = image.width;
            let height = image.height;
            if (this.maxWidth > 0 && width > this.maxWidth) {
                const ratio = Math.ceil((this.maxWidth / width) * 100) / 100;
                width = this.maxWidth;
                height = Math.floor(height * ratio);

                canvas.width = width;
                canvas.height = height;
                canvas.getContext("2d").drawImage(image, 0, 0, width, height);
                const filename = image.getAttribute("data-filename");
                this.createImage(canvas.toDataURL(filetype), filename).then(img => resolve(img));
            }
            else {
                resolve(image);
            }
        });
    }
}