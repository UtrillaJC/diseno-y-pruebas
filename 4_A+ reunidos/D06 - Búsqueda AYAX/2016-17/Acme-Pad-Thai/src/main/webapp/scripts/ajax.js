$(document).ready(function() {

	$('#searchForm').submit(function(event) {
		event.preventDefault();
		search();
	});

	$('#keyword').keyup(function() {
		search();
	});

});

function search(event) {
	var keyword;
	var formAction;

	keyword = $('#keyword').val();
	formAction = $('#searchForm').attr('action');

	searchRecipeByKeyword(keyword, formAction);
}

function searchRecipeByKeyword(recipeKeyword, formAction) {
	jQuery.ajax({
		url : formAction,
		data : {
			keyword : recipeKeyword
		},
		success : function(response) {
			$("#tableContainer").html(response);
			console.log(response);
		}
	});
}