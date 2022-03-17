var ImageInfo = function(img, maxLength){
    this.oWidth = 0;
    this.oHeight = 0;
    try{
        this.oWidth = img.naturalWidth;
        this.oHeight = img.naturalHeight;
    }catch(e){
        this.oWidth = img.width;
        this.oHeight = img.height;
    }
    var ratio = 1.0;
    if (this.oWidth > this.oHeight) {
        if (this.oWidth > maxLength) {
            ratio = maxLength / this.oWidth;
        }
    }else{
        if (this.oHeight > maxLength) {
            ratio = maxLength / this.oHeight;
        }
    }
    
    if(ratio > 1.0) ratio = 1.0;
    this.rWidth = this.oWidth * ratio;
    this.rHeight = this.oHeight * ratio;
} 

function chooseImageClk(divId, minLength, maxLength, func, wr, hr){
	var ua = navigator.userAgent.toLowerCase();
	var isAndroid = ua.indexOf("android") > -1; //&& ua.indexOf("mobile");
	//alert(isAndroid);
	if(isAndroid) {
		Native.onShowFileChooser(minLength, maxLength, func, wr, hr);	
	}else{
		if (navigator.userAgent.match(/(iPad|iPhone|iPod)/i)) {
			location.href="app://onShowFileChooser?minWidth="+minLength+"&maxWidth="+maxLength+"&func="+func+"&aspectRatioX="+wr+"&aspectRatioY="+hr;
		}else{
			$('#'+divId).click();
		}
		//alert("브라우저");
	}
} 

var imageMimes = ['image/png', 'image/bmp', 'image/gif', 'image/jpeg', 'image/tiff']; //Extend as necessary 

function chooseImage(_this, minLength, maxLength, func, wr, hr) {
	//alert("이미지 선택 후");
    var files = $(_this)[0].files;
    
    for(var i=0;i<files.length;i++){
        var imgFile = files[i];
        var reader  = new FileReader();
        reader.addEventListener("load", function () {
            var img = new Image();
            img.onload = function(){
                var imgInfo = new ImageInfo(img, maxLength);
                if((imgInfo.rWidth >= imgInfo.rHeight && imgInfo.rWidth < minLength) ||
                		(imgInfo.rWidth <= imgInfo.rHeight && imgInfo.rHeight < minLength)
                		){
                	return func(null, false, 400);
                }
                
                if( hr && wr && hr>0 && wr>0 && (parseInt(imgInfo.rHeight/imgInfo.rWidth*100) != parseInt(hr/wr*100))){
                	return func(null, false, 401);
                }
                
                var canvas  = document.createElement('canvas');
                var ctx = canvas.getContext("2d");
                canvas.width = imgInfo.rWidth;
                canvas.height = imgInfo.rHeight;
                //console.log(imgInfo.rWidth+":"+imgInfo.rHeight);
                ctx.drawImage(img, 0, 0, imgInfo.oWidth, imgInfo.oHeight, 0, 0, imgInfo.rWidth, imgInfo.rHeight);
                //dataUrl = canvas.toDataURL("image/png");
                dataUrl = canvas.toDataURL();
                
                if(typeof func == "function"){
                	return func(dataUrl, true, 200);
                }else{
                	return chooseImgResult(dataUrl, true, 200);
                }
            }
    
            img.src = this.result;
        }, false);
          
        if (imgFile) {
            reader.readAsDataURL(imgFile);
        }
    }
}

function chooseImgResult(dataUrl, status, errorCode){
	if(!handleImgMsg(status, errorCode)){
		return;
	}
    var img = handleImg(dataUrl, 180) ;
    var preview = $('#preview');
    preview.append( img );
}

function handleImg(dataUrl, maxLength){
    var img = new Image();
    img.src  = dataUrl ; 
    var imgInfo = new ImageInfo(img, maxLength);
    img.width=imgInfo.rWidth;
    img.height=imgInfo.rHeight;
    return img;
}

function handleImgMsg(status, errorCode, imgId){
	if(!status){
		if(errorCode == 400){
			AlertUtil.alertPropFailure("shopinfo.valid.imagesize");
		    return false;
		}else if(errorCode == 401){
			AlertUtil.alertPropFailure("shopinfo.valid.imageratio");
		    return false;
		}else{
			AlertUtil.alertPropFailure("error.unkown");
		    return false;
		}
		if(imgId && imgId != ""){
		    $("#"+imgId).addClass("border-fail");
		    $("#"+imgId).removeClass("border-success");
		}
	}
	return true;
}

function dataURItoBlob(dataURI) {
	try{
	    var byteString = atob(dataURI.split(',')[1]);
	    var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
	    var ab = new ArrayBuffer(byteString.length);
	    var ia = new Uint8Array(ab);
	    for (var i = 0; i < byteString.length; i++) {
	        ia[i] = byteString.charCodeAt(i);
	    }
	    
	    try{
	        return new Blob([ab], {type: mimeString});
	     } catch (e) {
	    	 alert(e.name);
	        // The BlobBuilder API has been deprecated in favour of Blob, but older
	        // browsers don't know about the Blob constructor
	        // IE10 also supports BlobBuilder, but since the `Blob` constructor
	        //  also works, there's no need to add `MSBlobBuilder`.
	        var BlobBuilder = window.WebKitBlobBuilder || window.MozBlobBuilder;
	        var bb = new BlobBuilder();
	        bb.append(ab);
	        return bb.getBlob(mimeString);
	     }

	}catch(e){
   	    alert("2222 : "+JSON.stringify(e));
		return null;
	}
}

/*jQuery.fn.serializefiles = function() {
	var formData = new FormData();
	$.each($(this).find("input[type='file']"), function(i, tag) {
		$.each($(tag)[0].files, function(i, file) {
//			console.log(tag.name);
			formData.append(tag.name, file);
		});
	});
	var params = this.serializeArray();
	$.each(params, function (i, val) {
//		console.log(val);
		formData.append(val.name, val.value);
	});
//	console.log(formData);
	return formData;
};
*/
//<input type="file" id="uploadImage" onchange="chooseImage(this, 1080)" multiple><br>
//<div id="preview"></div>
