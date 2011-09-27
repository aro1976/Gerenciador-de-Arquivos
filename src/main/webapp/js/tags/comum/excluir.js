var comum_cria_btn_excluir = function(id) {
	var _btn_excluir = $(id).button();
		
	var _dlg_acao_excuir = document.createElement('div');
	_dlg_acao_excuir.title=_btn_excluir.attr("dialogoTitulo");
	_dlg_acao_excuir.innerHTML=_btn_excluir.attr("dialogoMensagem");
	
	var btn_confirma = function(){
		$(_dlg_acao_excuir).dialog("close");
		
		$.ajax({
			  url: _btn_excluir.attr("excluirUrl"),
			  type: "DELETE",
			  context: document.body,
			  success: function(){
			    alert(_btn_excluir.attr("sucessoMensagem"));
			    $(location).attr('href',_btn_excluir.attr("sucessoUrl"));							    
			  },
		  	  error: function(){
			    alert(_btn_excluir.attr("erroMensagem"));						    
			  }
			});
	};
	
	var btn_cancela = function(){
		$(_dlg_acao_excuir).dialog("close");
	};
	
	var dialogOpts = {
		autoOpen: false,
		modal: true,
		width: 450,
		minHeight: 250,	
		resizable: false,
		buttons: [
			{
	          text: _btn_excluir.attr("dialogoBotaoConfirmar"),
	          click: btn_confirma
	      	},{
	      	  text: _btn_excluir.attr("dialogoBotaoCancelar"),
	          click: btn_cancela	
	      	}]
	};
	
	$(_dlg_acao_excuir).dialog(dialogOpts);
	
	$(id).button().click(function() {
		$(_dlg_acao_excuir).dialog("open");
	});
};