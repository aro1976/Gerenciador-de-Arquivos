(function($) {
	$("#btnAdicionaProcesso").button().click(
			function(event) {
				event.preventDefault();
				$.ajax({
					url : $("#btnAdicionaProcesso").attr("adicionaUrl"),
					data : {
						id : $("#txtProcesso").val()
					},
					type : "POST",
					dataType : "json",
					success : function(processo, xmlHttp) {
						$("#divArquivoProcessos").append(
								"<div id='divArquivoProcesso"+processo+"'>" + processo + "</div>");
						$("#txtProcesso").val("");
					},
					error : function(xmlHttp) {
						alert("Error " + xmlHttp.status);
					}
				});
			});
})(jQuery);