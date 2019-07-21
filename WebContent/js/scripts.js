$(function(){
	$("#submitBtnId").on('click', function(event){
		event.preventDefault();
		var message = $("#messageTextId").val();
		
		if(message.trim().length == 0){
			alert("Please input a message")
			return;
		}
		
		$("#formId").submit();
	});
	
	
});