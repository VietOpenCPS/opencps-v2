$(document).ready(function(){
	$( "body" ).delegate( 'a[data-toggle="dropdown"]', "click", function() {
		$(this).parent().toggleClass( "open" );
	});
	
	$( "body" ).delegate( 'button[data-toggle="dropdown"]', "click", function() {
		$(this).parent().toggleClass( "open" );
	});

	$( "body" ).on( 'hidden.bs.modal', ".modal", function() {
		$(this).removeData( "bs.modal" );
	});
	
	$(document).on('click', '.mWrapper a[data-toggle="collapse"]', function(event){
		$(this).toggleClass("open");
		$($(this).attr("href")).toggleClass("toggle-hide");
	});
	
	$(document).on('click', 'span[data-toggle="collapse"]', function(event){
		$(this).parent().next().toggleClass("toggle-hide");
	});
	
	$(document).on('click', 'div[data-toggle="collapse"]', function(event){
		$(this).next().toggleClass("toggle-hide");
	});
	
	$('.toggle-collapse').on('click', function() {
		$(this).toggleClass('fa-expand fa-compress');
		$('body #appManagerDossier').toggleClass('show-hide');
	});
});