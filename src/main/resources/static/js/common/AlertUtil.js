//$.toast.config.closeForStickyOnly = false;
var AlertUtil = (function() {
	var successOpt =  {duration:2000, stickey: true, type: "success"};
	var failureOpt =  {duration:2000, stickey: true, type: "danger"};
	var infoOpt =  {duration:2000, stickey: true, type: "info"};
	
	var alertCommon = function(msg, opt, top){
		alert(msg);
	}
	var alertSuccess = function(msg, top){
		alertCommon(msg , successOpt, top);
	}
	var alertFailure = function(msg, top){
		alertCommon(msg , failureOpt, top);
	}
	var alertInfo = function(msg, top){
		alertCommon(msg , infoOpt, top);
	}
	var alertPropSuccess = function(key, top){
		alertCommon($.t(key), successOpt, top);
	}
	var alertPropFailure = function(key, top){
		alertCommon($.t(key), failureOpt, top);
	}
	var alertPropInfo = function(key, top){
		alertCommon($.t(key), infoOpt, top);
	}
	return {
		alertSuccess : alertSuccess
		,alertFailure : alertFailure
		,alertInfo : alertInfo
		,alertPropSuccess : alertPropSuccess
		,alertPropFailure : alertPropFailure
		,alertPropInfo : alertPropInfo
		,alertCommon : alertCommon
	}
})();