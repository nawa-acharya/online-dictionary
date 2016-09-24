$(function() {
	$('#btnSearch').click(function() {
		$('#result').html('');
		$('#loader').show();
		var searchword=$('#txtSearch').val();
		if(searchword==""){alert("please insert text");return false;}
		if ($("#select option:selected").val() == "local") {
			loadFromLocalServer(searchword);
		} else {
			loadMerriamDictionary(searchword);
		}

	});
	$("#txtSearch").keypress(function(e) {
		if (e.which == 13) {
			loadFromLocalServer();
		}
	});
	/* Autocomplete */
	$('#txtSearch').autocomplete({
		width : 300,
		max : 10,
		delay : 100,
		minLength : 1,
		autoFocus : true,
		cacheLength : 1,
		scroll : true,
		highlight : false,
		source : function(request, response) {
			$.ajax({
				url : "dictionary",
				dataType : "json",
				data : {
					searchkey : request.term
				},
				success : function(data) {
					if (data.length > 0) {
						response($.map(data, function(value, key) {
							return {
								label : value.word,
								value : key.word
							}
						}));
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					// console.log( textStatus);
				}
			});
		}

	});
});

function loadMerriamDictionary(searchText) {
	var url = "MerriamServlet";

	$.ajax(url, {
		type : 'GET',
		dataType : 'JSON',
		data : {
			'searchkey' : searchText
		},
		success : bindMerriamDictionary,
		fail : error
	});
}

function loadFromLocalServer(searchText) {
	var url = "dictionary";
	$.ajax(url, {
		type : 'GET',
		dataType : 'JSON',
		data : {
			"searchkey" : searchText
		},
		success : bindSearchContent,
		fail : error
	});
}
function bindMerriamDictionary(data) {
	var listItem = data.entry_list.entry;
	var html = '';
	if(listItem!=null){
	html += '<h2>Search Result for <span>' + $('#txtSearch').val()
			+ '</span> found <em>[' + listItem.length + ']</em></h2>';
		html += '<table id="gradient-style" class=""><tr><th>Word</th><th class="th2">Data Type</th><th>Definition</th></tr>';
		$.each(listItem,function(index, item) {
			html += '<tr>';
			html += '<td><span>';
			html += item.ew+'</span>';
			if(typeof item.sound!="undefined"){
				var dir = item.sound.wav.substring(0, 1);
				html+='<p><audio class="play-pron converted" controls style="width:35px;">';
				html+='<source type="audio/wav" src="http://media.merriam-webster.com/soundc11/'+dir+'/'+item.sound.wav+'">';
				html+='</audio></p>';
			
			}
			html += '</td>';
			html += '<td>';
			html += item.fl;
			html += '</td>';
			html += '<td>';
			if (item.def.dt == "undefined") {
				html += item.def.dt.content;
			}
			if (item.def.dt.content == "undefined") {
				html += item.def.dt.content[0];
			} else {
				html += item.def.dt;
			}
			html += '</td>';
			html += '</tr>';
		});
	} else {
		html += '<h2 class="nodata">No result found for <span>'
				+ $('#txtSearch').val() + '</span></h2>';
	}
	$('#result').html('').html(html);
	$('#loader').hide();
}

function bindSearchContent(data) {
	var html = '';
	html += '<h2>Search Result for <span>' + $('#txtSearch').val()
			+ '</span> found <em>[' + data.length + ']</em></h2>';
	if (data.length > 0) {

		html += '<table id="gradient-style" class=""><tr><th>Word</th><th class="th2">Data Type</th><th>Definition</th></tr>';
		$.each(data, function(index, item) {
			html += '<tr>';
			html += '<td>';
			html += item.word;
			html += '</td>';
			html += '<td>';
			html += item.wordtype;
			html += '</td>';
			html += '<td>';
			html += item.definition;
			html += '</td>';
			html += '</tr>';
		});
	} else {
		html += '<h2 class="nodata">No result found for <span>'
				+ $('#txtSearch').val() + '</span></h2>';
	}
	$('#result').html('').html(html);
	$('#loader').hide();
}
function error(xhr, status, exception) {
	alert('error occur');
}