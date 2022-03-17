//https://sweetalert.js.org/guides/#advanced-examples
// And with a third argument, you can add an icon to your alert! There are 4 predefined ones: "warning", "error", "success" and "info".
var Swal = (function() {
    var alert = function(param){
        if(parent && parent != window) {
            return parent.Swal.alert(param);
        } else {
            var options = {
                content: ""
                ,text: ""
                ,title: 'Notice'
                ,icon: null
                ,button: '확인'
                ,closeOnClickOutside: false
            };

            if(typeof param === 'object') {
                var msg = param[0];
                var type = param[1];
                //options.content = msg;
                options.text = msg.replace(/\\n/g, '\n');
                options.icon = type;
                // success 일 경우 toast 처리.
                if(type === 'success') {
                    options.button = null;
                    options.timer = 1200;
                }
            } else if(typeof param === 'string') {
                options.content = param;
            }

            $(".swal-overlay").css("position", "fixed");

            return swal(options).then(function() {
                $(".swal-overlay").css("position", "absolute");
            });
        }
    }

    var confirm = function(param){
        if(parent && parent != window) {
            return parent.Swal.confirm(param);
        } else {
            var options = {
                content: ""
                ,text: ""
                ,title: 'Confirm'
                ,icon: null
                ,buttons: {
                    cancel: '취소',
                    /*catch: {
                      text: "3Buttons",
                      value: "catch",
                    },
                    roll: {
                        text: "4Buttons",
                        value: "roll",*/
                    confirm: {
                        text: '확인',
                        value: true
                    },
                    //ok: true,
                }
                ,dangerMode: true
                ,closeOnClickOutside: false
                ,closeOnEsc: true
                ,timer: null
                //,className: "red-bg"
            };

            if(typeof param === 'object') {
                var msg = param[0];
                var type = param[1];
                //options.content = msg;
                options.text = msg.replace(/\\n/g, '\n');
                options.icon = type;
            } else if(typeof param === 'string') {
                options.content = param;
            }

            $(".swal-overlay").css("position", "fixed");

            return swal(options).then(function(resYn) {
                $(".swal-overlay").css("position", "absolute");

                return resYn;
            });
        }
    }

    return {
        alert : alert
        ,confirm : confirm
    }
})();