$(document).ready(function(){
	$( "body" ).delegate( 'a[data-toggle="dropdown"]', "click", function() {
		$(this).parent().toggleClass( "open" );
	});
	
	$( "body" ).delegate( 'button[data-toggle="dropdown"]', "click", function() {
		$(this).parent().toggleClass( "open" );
	});
});