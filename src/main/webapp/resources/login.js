function GET(variable) {
	var query = window.location.search.substring(1);

	var vars = query.split("&");

	for (var i = 0; i < vars.length; i++) {
		var pair = vars[i].split("=");
		if (pair[0] == variable) {
			return pair[1];
		}
	}

	return (false);
}

function processQuery() {
	if (GET("msg") == "1") {
		document.getElementById('msg1').style.display = "block";
	} else {
		document.getElementById('msg1').style.display = "none";
	}

	if (GET("msg") == "2") {
		document.getElementById('msg2').style.display = "block";
	} else {
		document.getElementById('msg2').style.display = "none";
	}
}
