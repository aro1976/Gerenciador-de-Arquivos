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
								"<div style='display: none;'id='divArquivoProcesso"+processo.id+"'>" + processo.numero + "</div>");
						$("#divArquivoProcesso"+processo.id).fadeIn("slow");
					},
					error : function(xmlHttp) {
						alert("Error " + xmlHttp.status);
					}
				});
			});
})(jQuery);