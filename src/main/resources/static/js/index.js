$( document ).ready(function() {
    
    $("#collectBtn").click(function(){
    	httpRequest("/collect", "get", null, function(data, result){
    		$(".mainFrame").html(data);
    	});
    });
    
    $("#analyseBtn").click(function(){
    	httpRequest("/analyse", "get", null, function(data, result){
    		$(".mainFrame").html(data);
    	});
    });
    
    
    function httpRequest(requestUrl, method, requestData, callback){
    	$.ajax({
    		url: requestUrl,
    		type: method,
    		data: requestData,
    		success : callback
    	});
    }
});