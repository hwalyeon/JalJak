    try{
    	$.fn.datepicker.defaults.format = 'yyyy-mm-dd';
    }catch(e){}
    var dataPickerFormat = "YYYY-MM-DD";

    formatTimestamp = function(date){
    	 if(value == null) return value;
         if(isNaN(value)) return value;
         //value -= serverTime;
         return moment(value).format(timestampFormatPattern);
         if(WebUtil.isNull(date)) return '';
         return moment(date).format(timestampFormatPattern);
    }

    formatDate = function(date){
    	 if(date == null) return ;
         if(WebUtil.isNull(date)) return '';
         return moment( date ).format(dateFormatPattern);
    }

    formatTime = function (date) {
        if(date == null) return ;
        if(WebUtil.isNull(date)) return '';
        return moment( date,  'HHmmss' ).format(timeFormatPattern);
    }

    numberFormat = function (value) {
        var num = parseInt(value);
        if( isNaN(num) ) return "0";
        var reg = /(^[+-]?\d+)(\d{3})/;
        var n = (value + '');
        while (reg.test(n)) n = n.replace(reg, '$1' + ',' + '$2');
        return n;
    }
    
    phoneFormatter = function(num,type){
    	var formatNum = '';
    	 try{
    	     if(!WebUtil.isNull(num)){

    	      if (num.length == 11) {
    	         if (type == 0) {
    	            formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-****-$3');
    	         } else {
    	            formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
    	         }
    	      } else if (num.length == 8) {
    	         formatNum = num.replace(/(\d{4})(\d{4})/, '$1-$2');
    	      } else {
    	         if (num.indexOf('02') == 0) {
    	            if (type == 0) {
    	               formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-****-$3');
    	            } else {
    	               formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-$2-$3');
    	            }
    	         } else {
    	            if (type == 0) {
    	               formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-***-$3');
    	            } else {
    	               formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
    	            }
    	         }
    	      }
             }
    	   } catch(e) {
    	      formatNum = num;
    	      console.log(e);
    	   }
    	   return formatNum;
    }

    formatDatePicker = function(date){
    	 if(date == null) return ;
         if(WebUtil.isNull(date)) return '';
         return moment( date ).format(dataPickerFormat);
    }

    var ValueLabel = function (pvalue, plabel){
    	this.value = pvalue;
    	this.label = plabel;
    }

    checkNumber = function (event){
		//console.log(event.charCode);
		return ((event.charCode >= 48 && event.charCode <= 57) || event.charCode == 0);
	}

    checkNumAndEng = function (str){
    	var err = 0;
    	for (var i=0; i<str.length; i++)  {
    	    var chk = str.substring(i,i+1);
    	    if(!chk.match(/[0-9]|[a-z]|[A-Z]/)) {
    	        err = err + 1;
    	    }
    	}
    	return err;
    }

    jQuery.fn.serializeObject = function() {
  	  var arrayData, objectData;
  	  arrayData = this.serializeArray();
  	  objectData = {};
  	  $.each(arrayData, function() {
  	    var value;
  	    if (this.value != null) {
  	      value = this.value;
  	    } else {
  	      value = '';
  	    }
  	    if (objectData[this.name] != null) {
  	      if (!objectData[this.name].push) {
  	        objectData[this.name] = [objectData[this.name]];
  	      }
  	      objectData[this.name].push(value);
  	    } else {
  	      objectData[this.name] = value;
  	    }
  	  });
  	  return objectData;
  	};

  	jQuery.fn.serializefiles = function() {
		var formData = new FormData();
		$.each($(this).find("input[type='file']"), function(i, tag) {
			$.each($(tag)[0].files, function(i, file) {
				formData.append(tag.name, file);
			});
		});
		var params = this.serializeArray();
		$.each(params, function (i, val) {
			formData.append(val.name, val.value);
		});
		return formData;
    };

    function appendFormdata(pFormData, data, name){
        name = name || '';
        if (typeof data === 'object'){
            $.each(data, function(index, value){
                if (name == ''){
                	//console.log("index : "+index);
                    appendFormdata(pFormData, value, index);
                } else {
                    appendFormdata(pFormData, value, name + '['+index+']');
                }
            })
        } else {
        	pFormData.append(name, data);
        }
    }

    function valid(f) {
    	f.value = removeEmoji(f.value);
    }

    function removeEmoji(val) {
    	if(!val || val == '') return val;
//    	var regex = /(?:[\u2700-\u27bf]|(?:\ud83c[\udde6-\uddff]){2}|[\ud800-\udbff][\udc00-\udfff]|[\u0023-\u0039]\ufe0f?\u20e3|\u3299|\u3297|\u303d|\u3030|\u24c2|\ud83c[\udd70-\udd71]|\ud83c[\udd7e-\udd7f]|\ud83c\udd8e|\ud83c[\udd91-\udd9a]|\ud83c[\udde6-\uddff]|\ud83c[\ude01-\ude02]|\ud83c\ude1a|\ud83c\ude2f|\ud83c[\ude32-\ude3a]|\ud83c[\ude50-\ude51]|\u203c|\u2049|[\u25aa-\u25ab]|\u25b6|\u25c0|[\u25fb-\u25fe]|\u00a9|\u00ae|\u2122|\u2139|\ud83c\udc04|[\u2600-\u26FF]|\u2b05|\u2b06|\u2b07|\u2b1b|\u2b1c|\u2b50|\u2b55|\u231a|\u231b|\u2328|\u23cf|[\u23e9-\u23f3]|[\u23f8-\u23fa]|\ud83c\udccf|\u2934|\u2935|[\u2190-\u21ff])/g;
//    	return val.replace(regex, '');
    	return val.replace(/([\uE000-\uF8FF]|\uD83C[\uDC00-\uDFFF]|\uD83D[\uDC00-\uDFFF]|[\u2694-\u2697]|\uD83E[\uDD10-\uDD5D])/g, '');
    }

    var priceRegExp = /^\d*(\.\d{0,2|)?$/;
    function checkPrice(_this, divId)  {
        var temp = _this.value; // .toLowerCase();
        if(!priceRegExp.test(temp)){
        	alert("형식이 올바르지 않습니다.");
        	return;
        }
    }

    function checkStock(_this)  {
    	var temp = _this.value; // .toLowerCase();
    	temp = temp.replace(/[^0-9]/gi, '');
    	_this.value = temp;
    	if(_this.value.length>20){
    		_this.value =_this.value.substring(0, 20);
    	}
    }

    function validCurrency(value){
        var regex = /^[1-9]\d*(((,\d{3}){1})?(\.\d{0,2})?)$/;
        if (regex.test(value))
        {
            var twoDecimalPlaces = /\.\d{2}$/g;
            var oneDecimalPlace = /\.\d{1}$/g;
            var noDecimalPlacesWithDecimal = /\.\d{0}$/g;

            if(value.match(twoDecimalPlaces ))
            {
                return value;
            }
            if(value.match(noDecimalPlacesWithDecimal))
            {
                return value+'00';
            }
            if(value.match(oneDecimalPlace ))
            {
                return value+'0';
            }
            return value+".00";
        }
        return null;
    }


	function clone(obj) {
		if (obj === null || typeof (obj) !== 'object')
			return obj;
		var copy = obj.constructor();
		for ( var attr in obj) {
			if (obj.hasOwnProperty(attr)) {
				copy[attr] = obj[attr];
			}
		}
		return copy;
	}

	// HTML <> 안의 내용을 지운다.
	function removeHTML(repStr) {
        repStr = repStr.replaceAll("<BR>", "=BR=").replaceAll("<br>", "=BR=").replaceAll("<p>", "=BR=").replaceAll("<P>", "=BR=");
        var objStrip = /[<][^>]*[>]/gi;
        var objStrip2 =  /<title>(.*?)<\/title>/gi;
        repStr = repStr.replace(objStrip2, "");
        return repStr.replace(objStrip, "").replaceAll("=BR=", "<br>");
    }

	// HTML <> 안의 내용을 지운다.
	function removeHTMLAll(repStr) {
        var objStrip = /[<][^>]*[>]/gi;
        return repStr.replace(objStrip, "");
    }

	// HTML <> 안의 내용을 지운다.
	function isTagHTML(repStr) {
        // console.log("repStr : " + repStr);
        var objStrip = /[<][^>]*[>]/gi;
        var repStr2 = repStr.replace(objStrip, "");
        // console.log("repStr2 : " + repStr2);
        if (repStr != repStr2) return true;
        else return false;
    }

	String.prototype.replaceAll = replaceAll;
	function replaceAll(strValue1, strValue2) {
    	var strTemp = this;
        strTemp = strTemp.replace(new RegExp(strValue1, "g"), strValue2);
        return strTemp;
    }

    // 이미지 확장자 체크
	function checkImgFileName (fileName) {
        var isErr = false,
            ext = [];
        var sarry = fileName.split("\\");	// 선택된 이미지 화일의 풀 경로

        // 파일 존재 여부 검사
        if (sarry.length <= 0) {
            return false;
        }

        // 확장자 추출
        ext = sarry[sarry.length - 1].split(".");
        // 확장자 존재 여부 검사
        if (ext.length <= 0) {
            return false;
        }
        if (ext[ext.length - 1].toLowerCase() != "jpg" &&
            ext[ext.length - 1].toLowerCase() != "jpeg" &&
            ext[ext.length - 1].toLowerCase() != "png") {

            Swal.alert(["확장자명이 .jpeg, .jpg, .png만 사용 가능합니다.", "warning"]);
            return false;
        }

        return true;
    }

	function delSpecialChar(valueStr){
        // console.log(valueStr);
        var re =/[\~!@#$%<>^&*\\()\=+_|'"]/gi;
        if (re.test(valueStr)) {
            Swal.alert(["Do not use space or special character.", "warning"]);
            valueStr = valueStr.replace(re,"");
        }
        // console.log(valueStr);
        return valueStr;
    }
	function delSpecialCharNoAlert(valueStr){
		// console.log(valueStr);
		var re =/[\~!@#$%<>^&*\\()\=+_|'"]/gi;
		if (re.test(valueStr)) {
			valueStr = valueStr.replace(re,"");
		}
		// console.log(valueStr);
		return valueStr;
	}

	function limitCharSize(valueStr, limitLen) {
        if (String(valueStr).length > limitLen) {
            Swal.alert([limitLen + '자 이내로 작성해 주세요.', "warning"]);
            valueStr = String(valueStr).substring(0, limitLen);
        }
        return valueStr;
    }

	function limitByteSize(valueStr, limitByte) {
		console.log(valueStr.value);
		var mbytes = 0;
		for(i=0; i < String(valueStr.value).length ; i++) {
			var ch = String(valueStr.value).charAt(i)
			if(escape(ch).length > 4) {
				mbytes += 2;
			} else if(ch == '\n') {
				if(String(valueStr.value).charAt(i-1) != '\r') {
					mbytes += 1;
				}
			} else {
				mbytes += 1;
			}
			if(mbytes > limitByte) {
				valueStr.value = String(valueStr.value).substr(0,i);
				Swal.alert([$.t('common.valid.input.length.byte', {0: limitByte}), 'warning']);
				break;
			}
		}
		return valueStr.value;
	}

	function onlyNumCheck(valueStr) {
        var re = /^[0-9\r\n]+$/;
        if (!re.test(valueStr)) {
            //Swal.alert([$.t('common.numinert.chk'), 'warning']);
            valueStr = String(valueStr).replace(/[^0-9]/g,'');
        }

        /*if(valueStr == '') {
        	valueStr = 0;
        }*/
        return valueStr;
    }

	function onlyNumCheck(valueStr, option) {
		var re = /^[0-9\r\n]+$/;
		var reg = /^[+\-]?\d+(?:[.]\d+)?$/g;
		//Option(1) : 오직 숫자만 허용 시
		if(!option || option == 1) {
	        if (!re.test(valueStr)) {
	            Swal.alert([$.t('common.numinert.chk'), 'warning']);
	            valueStr = String(valueStr).replace(/[^0-9]/g,'');
	        }
		}
		//Option(2) : +- 기호를 제외한 수숫점까지 허용 시
		if(option && option == 2) {
			re = /^[0-9.\r\n]+$/;
			if (!re.test(valueStr)) {
				Swal.alert([$.t('common.numinert.chk'), 'warning']);
				valueStr = String(valueStr).replace(/[^0-9.]/g,'');
			}
		}

		if(String(valueStr).startsWith('.')) {
			valueStr = '0'+String(valueStr);
		}
		//console.log($.isNumeric(valueStr));

		//if (!reg.test(valueStr)) {
		if (!$.isNumeric(valueStr)) {
           Swal.alert([$.t('common.numinert.chk'), 'warning']);
           //Option(3) : +-, 소숫점 까지 모두 허용
           if(option && option == 3) {
        	   valueStr = String(valueStr).replace(/[^0-9.]/g,'');
           }
           reg = /^[+\-]?\d+(?:[.]\d+)?/g;
           var matches = reg.exec(valueStr);
           //console.log(matches);
           if(matches && matches.length > 0) {
        	   valueStr = matches[0];
           } else {
        	   valueStr = '';
           }
        }

        return valueStr;
    }

	function checkNum(checkTxt) {
        var isContain = false,
            pattern = /^[0-9\r\n]+$/;

        if (pattern.test(checkTxt)) {
            isContain = true;
        }
        return isContain;
    }

	function numberLock(valueStr, $event) {
        var key = ($event.which) ? $event.which : $event.keyCode;
        var re = /[^0-9\r\n]+/gm;
        if(!((key == 8) || (key == 9) || (key == 13) || (key == 16) || (key == 37) || (key == 39) || (key == 116))
            || ($event.type == "keyup" && $event.ctrlKey && key == 86)){
            if(re.test(valueStr)) {
                Swal.alert([$.t('common.numinert.chk'), 'warning']);
                valueStr = valueStr.replace(/[^0-9\r\n]+/gm,"");
                return valueStr;
            }
        }
        return valueStr;
    }

    /**
     * Is Url (http://, https://)
     * @param strUrl
     */
	function isValidUrl(strUrl) {
        var expUrl = /^http[s]?\:\/\//i;
        return expUrl.test(strUrl);
    }

	//입력값 검사(이메일), chk_err_emailaddress_validstring
	function isValidEmail(email) {
		//var re = /^(([^<>()[]\\.,;:\s@\"]+(\.[^<>()[]\\.,;:\s@\"]+)*)|(\".+\"))@(([[0-9]{1,3}\‌​.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

		//alert(re.test(email));

		//return email.match(re);
		/*if(!re.test(email)) {
			return false
		}
		return true;*/
		return re.test(email);
	}

	//입력값 검사(전화번호), Phone number input format is incorrect.
	function isValidPhoneNumber(number) {
		var re = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
		re = /^[+]*[(]{0,1}[0-9]{1,3}[)]{0,1}[-\s\./0-9]*$/g;
		return re.test(number);
	}

	// at least one number, one lowercase and one uppercase letter
    // at least 8 characters
	function isValidPassword(str) {
		// /^[a-zA-Z0-9]{10,15}$/
		//var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,15}$/;
        var re = /^(?=.*\d)(?=.*[a-z])(?=.*[!@#$%^&*()_+]).{8,15}$/;
		return re.test(str);
    }

	//Username must contain only letters, numbers and underscores!
	function isValidId(str) {
		var re = /^\w+$/;
		return re.test(str);
	}

	//입력값 검사(비밀번호)
	function checkPassword(fromName) {
		var tmpform = eval("document.frm."+fromName);
		var pwd = tmpform.value;
		if(!/^[a-zA-Z0-9]{10,15}$/.test(pwd)) {
			alert('Passwords must use a combination of letters and numbers 6 ~ 15 characters.');
			return tmpform.focus;
		}

		var chk_num = pwd.search(/[0-9]/g);
		var chk_eng = pwd.search(/[a-z]/ig);

		if(chk_num < 0 || chk_eng < 0) {
			alert('Passwords must use a combination of letters and numbers 6 ~ 15 characters.');
			return tmpform.focus;
		}
	}

    function engNumCheck(fieldValue) {
        var re = /^[A-Za-z0-9+]*$/;
        if (!re.test(fieldValue)) {
            Swal.alert(['영문, 숫자만 입력 가능합니다.', 'warning']);
            fieldValue = String(fieldValue).replace(/[^A-Za-z0-9]/g,'');
        }

        return fieldValue;
    }

    function checkInputValue(fieldValue) {
    	var pattern = /(^[a-zA-Z0-9 -/:-@\[-\`\{-\~]+$)/;
    	if(fieldValue != "" && !pattern.test(fieldValue)){
			Swal.alert(["Please enter alphabet, number and symbol only", "warning"])
				return false;
    	}
    }

	var getParameters = function (paramName) {
	    // 리턴값을 위한 변수 선언
	    var returnValue;

	    // 현재 URL 가져오기
	    var url = location.href;

	    // get 파라미터 값을 가져올 수 있는 ? 를 기점으로 slice 한 후 split 으로 나눔
	    var parameters = (url.slice(url.indexOf('?') + 1, url.length)).split('&');

	    // 나누어진 값의 비교를 통해 paramName 으로 요청된 데이터의 값만 return
	    for (var i = 0; i < parameters.length; i++) {
	        var varName = parameters[i].split('=')[0];
	        if (varName.toUpperCase() == paramName.toUpperCase()) {
	            returnValue = parameters[i].split('=')[1];
	            return decodeURIComponent(returnValue);
	        }
	    }
	};

    function isValidBizRegNo(val) {
    	var result = false;

    	if(onlyNumCheck(val).length == 10) result = true;
    	return result;
    };

    function validChecker(val, type, size) {
    	var result = false;

    	if(WebUtil.isNotNull(val)) {
			switch (type) {
			  case 'email' :
				  return isValidEmail(val);
			  case 'phone' :
				  return isValidPhoneNumber(val);
			  case 'bizno' :
				  return isValidBizRegNo(val);
			  case 'mxlen' :
				  if(size > 0) return val == limitCharSize(val, size);
				  break;
			  default :
			    break;
			}
    	}

    	return result;
    };

	// 자릿수만큼 0 채우기
    function pad(n, width) {
        n = n + '';
        return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
    }

    // 윈도우 팝업 열기
    function openWinPop(opt) {
        if (WebUtil.isNull(opt.name)) {
            alert("팝업 이름은 필수 항목입니다.");
            return false;
    	}

        if (WebUtil.isNull(opt.url)) {
            alert("팝업 주소는 필수 항목입니다.");
            return false;
        }

        // 팝업 넓이
        var popWidth = WebUtil.toInt(opt.width);

        if (popWidth == 0) {
            alert("팝업 넓이는 필수 항목입니다.");
            return false;
        }

        // 팝업 높이
        var popHeight = WebUtil.toInt(opt.height);

        if (popHeight == 0) {
            alert("팝업 높이는 필수 항목입니다.");
            return false;
        }

        // 위치 정보
        var curTop    = window.screenY;
        var curLeft   = window.screenX;
        var curWidth  = document.body.clientWidth;
        var curHeight = window.outerHeight;

        // 부모 넓이
        if (window.parent != window.self) {
            curWidth = window.parent.document.body.clientWidth;
        }

        // 브라우저 정보
        var ua = navigator.userAgent.toLowerCase();

        // 팝업 추가 높이
        var etcHeight = 60;

        // 브라우저 체크
        if (ua.indexOf("msie") != -1 || ua.indexOf("trident") != -1) {
            etcHeight = 36;
        } else if (ua.indexOf("edge") != -1) {
            etcHeight = 90;
        } else if (ua.indexOf("firefox") != -1) {
            etcHeight = 69;
        }

        // 팝업 좌표
        var popTop  = Math.ceil(curTop + ((curHeight - popHeight - etcHeight) / 2));
        var popLeft = Math.ceil(curLeft + ((curWidth - popWidth) / 2));

        // 팝업 옵션
        var popOpt = "top=" + popTop + ", left=" + popLeft + ", width=" + popWidth + ", height=" + popHeight + ", scrollbars=yes, resizable=yes, status=no";

        // 팝업 열기
        window.open(opt.url, opt.name, popOpt);
    }

    // 강의실 팝업 열기
    function openLctrClssRoom(cousNo, lectSeq) {
        
        var popWidth = 1920;
        var popHeight = 980;

        // 위치 정보
        var curTop    = window.screenY;
        var curLeft   = window.screenX;
        var curWidth  = document.body.clientWidth;
        var curHeight = window.outerHeight;

        // 부모 넓이
        if (window.parent != window.self) {
            curWidth = window.parent.document.body.clientWidth;
        }

        // 브라우저 정보
        var ua = navigator.userAgent.toLowerCase();

        // 팝업 추가 높이
        var etcHeight = 60;

        // 브라우저 체크
        if (ua.indexOf("msie") != -1 || ua.indexOf("trident") != -1) {
            etcHeight = 36;
        } else if (ua.indexOf("edge") != -1) {
            etcHeight = 90;
        } else if (ua.indexOf("firefox") != -1) {
            etcHeight = 69;
        }

        // 팝업 좌표
        var popTop  = 0;//Math.ceil(curTop + ((curHeight - popHeight - etcHeight) / 2));
        var popLeft = 0;//Math.ceil(curLeft + ((curWidth - popWidth) / 2));

        // 팝업 옵션
        var popOpt = "top=" + popTop + ", left=" + popLeft + ", width=" + popWidth + ", height=" + popHeight + ", fullscreen=yes, scrollbars=auto, status=no";

        // 팝업 열기
        window.open("/lect/clssRoom?cousNo=" + cousNo + "&lectSeq=" + lectSeq, 'LctrClssRoom', popOpt);
    }

	// 강의실 팝업 열기
    function openStdtClssRoom(cousNo, lectSeq) {
        
        var popWidth = 1920;
        var popHeight = 980;

        // 위치 정보
        var curTop    = window.screenY;
        var curLeft   = window.screenX;
        var curWidth  = document.body.clientWidth;
        var curHeight = window.outerHeight;

        // 부모 넓이
        if (window.parent != window.self) {
            curWidth = window.parent.document.body.clientWidth;
        }

        // 브라우저 정보
        var ua = navigator.userAgent.toLowerCase();

        // 팝업 추가 높이
        var etcHeight = 60;

        // 브라우저 체크
        if (ua.indexOf("msie") != -1 || ua.indexOf("trident") != -1) {
            etcHeight = 36;
        } else if (ua.indexOf("edge") != -1) {
            etcHeight = 90;
        } else if (ua.indexOf("firefox") != -1) {
            etcHeight = 69;
        }

        // 팝업 좌표
        var popTop  = 0;//Math.ceil(curTop + ((curHeight - popHeight - etcHeight) / 2));
        var popLeft = 0;//Math.ceil(curLeft + ((curWidth - popWidth) / 2));

        // 팝업 옵션
        var popOpt = "top=" + popTop + ", left=" + popLeft + ", width=" + popWidth + ", height=" + popHeight + ", fullscreen=yes, scrollbars=auto, status=no";

        // 팝업 열기
		window.open("/clss/clssRoom?cousNo=" + cousNo + "&lectSeq=" + lectSeq, 'StdtClssRoom', popOpt);
    }
    
	// 지난강의 팝업 열기
    function openRcrdLectRoom(cousNo, lectSeq) {
        
        var popWidth = 1920;
        var popHeight = 980;

        // 위치 정보
        var curTop    = window.screenY;
        var curLeft   = window.screenX;
        var curWidth  = document.body.clientWidth;
        var curHeight = window.outerHeight;

        // 부모 넓이
        if (window.parent != window.self) {
            curWidth = window.parent.document.body.clientWidth;
        }

        // 브라우저 정보
        var ua = navigator.userAgent.toLowerCase();

        // 팝업 추가 높이
        var etcHeight = 60;

        // 브라우저 체크
        if (ua.indexOf("msie") != -1 || ua.indexOf("trident") != -1) {
            etcHeight = 36;
        } else if (ua.indexOf("edge") != -1) {
            etcHeight = 90;
        } else if (ua.indexOf("firefox") != -1) {
            etcHeight = 69;
        }

        // 팝업 좌표
        var popTop  = 0;
        var popLeft = 0;

        // 팝업 옵션
        var popOpt = "top=" + popTop + ", left=" + popLeft + ", width=" + popWidth + ", height=" + popHeight + ", fullscreen=yes, scrollbars=auto, status=no";

        // 팝업 열기
		window.open("/clss/rcrdLectRoom?cousNo=" + cousNo + "&lectSeq=" + lectSeq, 'replayPopup', popOpt);
    }

    function fullScreen(elem)
    {
    	if ( (document.fullScreenElement   !== undefined && document.fullScreenElement   === null) ||
           	 (document.msFullscreenElement !== undefined && document.msFullscreenElement === null) || 
           	 (document.mozFullScreen       !== undefined && !document.mozFullScreen              ) ||
           	 (document.webkitIsFullScreen  !== undefined && !document.webkitIsFullScreen         ) )
        {
    		/*
    		if ( !!elem.requestFullScreen ) {
    			elem.requestFullScreen();
    		} else if ( !!elem.mozRequestFullScreen ) {
    			elem.mozRequestFullScreen();
    		} else if ( !!elem.webkitRequestFullScreen ) {
    			elem.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT);
    		} else if ( !!elem.msRequestFullscreen ) {
    			elem.msRequestFullscreen();
    		}
    		*/
    		var requestMethod = elem.requestFullScreen || elem.webkitRequestFullScreen(elem.ALLOW_KEYBOARD_INPUT) || elem.mozRequestFullScreen || elem.msRequestFullScreen;

    	    if (requestMethod) { // Native full screen.
    	        requestMethod.call(el);
    	    } else if (typeof window.ActiveXObject !== "undefined") { // Older IE.
    	        var wscript = new ActiveXObject("WScript.Shell");
    	        if (wscript !== null) {
    	            wscript.SendKeys("{F11}");
    	        }
    	    }
        }
    	else
    	{
    		if ( !!document.cancelFullScreen ) {
    			document.cancelFullScreen();
    		} else if ( !!document.mozCancelFullScreen ) {
    			document.mozCancelFullScreen();
            } else if ( !!document.webkitCancelFullScreen ) {
            	document.webkitCancelFullScreen();
            } else if ( !!document.msExitFullscreen ) {
            	document.msExitFullscreen();
            }
    	}
    }
    
    // 상품 상세 윈도우 팝업
    function prdDtlWinPop(prdNo, tabId, popId) {
        var popUrl = "/product/productRegisterDetail";

        if (WebUtil.isNotNull(prdNo)) {
            popUrl = popUrl + "?prdNo=" + prdNo;

            if (WebUtil.isNotNull(tabId)) {
                popUrl = popUrl + "&tabId=" + tabId;
            }
        }
  
        if (WebUtil.isNull(popId)) {
        	popId = "productRegisterDetail";
        }

        openWinPop({
            name: popId,
            url: popUrl,
            width: 1000,
            height: 700
        });
    }

    // 상품 옵션 윈도우 팝업
    function prdOptWinPop(prdNo) {
        var popUrl = "/product/productManagerOptionPopup?prdNo=" + prdNo;

        openWinPop({
            name: "productManagerOptionPopup",
            url: popUrl,
            width: 1000,  
            height: 500
        });
    }

    // 기간 날짜 구하기
    function getPeriodDate(type) {
        var nowDate = new Date();
        var dateFormat = dateFormatPattern.toUpperCase();
        var strDt = "";
        var endDt = "";

        if (type == "TODAY") {
            strDt = moment(nowDate).format(dateFormat);
            endDt = moment(nowDate).format(dateFormat);
        } else if (type == "YESTERDAY") {
            strDt = moment(nowDate).add(-1, "days").format(dateFormat);
            endDt = moment(nowDate).add(-1, "days").format(dateFormat);
        } else if (type == "RECENT_WEEK") {
            strDt = moment(nowDate).add(-7, "days").format(dateFormat);
            endDt = moment(nowDate).format(dateFormat);
        } else if (type == "THIS_WEEK") {
            strDt = moment(nowDate).startOf("week").format(dateFormat);
            endDt = moment(nowDate).endOf("week").format(dateFormat);
        } else if (type == "LAST_WEEK") {
            strDt = moment(nowDate).add(-1, "weeks").startOf("week").format(dateFormat);
            endDt = moment(nowDate).add(-1, "weeks").endOf("week").format(dateFormat);
        } else if (type == "RECENT_MONTH") {
            strDt = moment(nowDate).add(-30, "days").format(dateFormat);
            endDt = moment(nowDate).format(dateFormat);
        } else if (type == "THIS_MONTH") {
            strDt = moment(nowDate).startOf("month").format(dateFormat);
            endDt = moment(nowDate).endOf("month").format(dateFormat);
        } else if (type == "LAST_MONTH") {
            strDt = moment(nowDate).add(-1, "months").startOf("month").format(dateFormat);
            endDt = moment(nowDate).add(-1, "months").endOf("month").format(dateFormat);
        } else if (type == "PRE_THREE_MONTH") {
            strDt = moment(nowDate).add(-3, "months").format(dateFormat);
            endDt = moment(nowDate).format(dateFormat);
        } else if (type == "PRE_SIX_MONTH") {
            strDt = moment(nowDate).add(-6, "months").format(dateFormat);
            endDt = moment(nowDate).format(dateFormat);
        } else if (type == "PRE_ONE_YEAR") {
            strDt = moment(nowDate).add(-1, "years").format(dateFormat);
            endDt = moment(nowDate).format(dateFormat);
        }

        return { strDt: strDt, endDt: endDt};
    }

    function copyToClipBoard(cpValue){
        var t = document.createElement("textarea");
        document.body.appendChild(t);
        t.value = cpValue;
        t.select();
        document.execCommand('copy');
        document.body.removeChild(t);

        /*var aux = document.createElement("div");
        aux.setAttribute("contentEditable", true);
        //aux.innerHTML = document.getElementById(element_id).innerHTML;
        aux.innerHTML = cpValue;
        aux.setAttribute("onfocus", "document.execCommand('selectAll',false,null)");
        document.body.appendChild(aux);
        aux.focus();
        document.execCommand("copy");
        document.body.removeChild(aux);*/
    }

    /**
     * 문자열의 byte 길이를 구한다.
     * @param str
     * @returns {number}
     */
    function getBytes(str) {
        return str.split('')
            .map(function(s) { return s.charCodeAt(0); })
            .reduce(function(prev, chr) { return (prev + ((chr === 10) ? 2 : ((chr >> 7) ? 2 : 1)));} , 0);
    }

    function numberTypeMaxLengCheck(obj){
      if (obj.value.length > obj.maxLength){
        obj.value = obj.value.slice(0, obj.maxLength);
      }
    }


    // 그리드 selectbox 형식으로 데이터 변환
    function commonGridCmonCd(cdList)
    {
        var selObj = "";

        if (WebUtil.isNull(cdList) == true)
        {
            return null;
        }
        else
        {
            for ( var ix = 0 ; ix < cdList.length ; ix ++ )
            {
                var cd = cdList[ix];
                if(selObj != "")
                {
                    selObj += ";";
                }
                selObj += cd.cdVal+":"+cd.cdNm;
            }
        }
        return selObj;
    }

    function toCamelCase(str) {
        return str.toLowerCase().replace(/[^a-zA-Z0-9]+(.)/g, (m, chr) => chr.toUpperCase());
    }