function exibeUrl (data) {
	if($.isArray(data)){
		$("#retorno").empty();
		$.each(data, function(index, value){
			$("#retorno").prepend("Id: " + value.id + "<br>Pagina: " + value.url+ "<br>Url Encurtada: " + value.urlCurta + 
					"<br>Url Encurtada>"+ value.alias + "<br><br>");
		});
	}else{
		$("#retorno").html("Id: " + data.id + "<br>Pagina: " + data.url+ "<br>Url Encurtada: " + data.urlCurta + 
				"<br>Url Encurtada>"+ data.alias + "<br><br>");
	}
	$("#loading").empty();
}

function aguardarUrl () {
	$("#loading").html("<img style='width: 55px; height: 55px;' src='image/giphy.gif' alt='loading'></img>");
}

function exibeErroUrl () {
	$("#retorno").html("Ops, Algo acontecendo de errado, vamos ver com o estágiario.");
	$("#loading").empty();
}


function getUrlEncurtada() {
	event.preventDefault();
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: "http://localhost:8680/encurtador/url/add",
        dataType: "json",
        data: formToJSON(),
        success: function(data, textStatus, jqXHR){
        	alert('url adicionado com sucesso');
 	        $('#id_url_resultado').val(data.urlCurta);
        },
        error: function(jqXHR, textStatus, errorThrown){
        	console.log("Aqui" + errorThrown);
        	console.log(textStatus);
        	console.log(error);
        }
    });
}

function formToJSON() {
    return JSON.stringify({
        "url": $('#id_url').val()
        });
}


$(document).ready(function() {
	$("#enviar").on('click', getUrlEncurtada);
});
