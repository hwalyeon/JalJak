var thisPageNum = 0;
$(function(){
	$("#topMenu a").removeClass("active").eq(thisPageNum).addClass("active");
	$('#topMenu a').click(function (e) {
		
		var index = $('#topMenu a').index(this);
		if(_IS_ACTIVE){
			location.href = _PAGE[index].url;
		}else{
			if(_PAGE[index].id != '#shopinfo' ){
				window.scrollTo(0, 0);
				AlertUtil.alertPropFailure("shopinfo.check.page");
			    return;
			}
		}
	});
	//spinner_div = $('#spinner').get(0);
	$(window).scroll(function(){
		var scrollTop = $(window).scrollTop();
		if(scrollTop == 0){
			$(".gotop").css("visibility", "hidden");
		}else{
			$(".gotop").css("visibility", "visible");
		}
	});
});

function gotop(){
	$("html, body").animate({ scrollTop: 0 }, "fast");
	return false;
}

function strStatus(obj){
	return obj.resultStatus.message == "success";
}

function marginTop(pixel){
	$("#sub_content").animate({'marginTop':pixel+"px"}, "fast");
}

function getResizeImage(src){
	var n = src.lastIndexOf("/");
	return src.substring(0, n+1)+imgPrefixCharS+src.substring(n+1);
}
