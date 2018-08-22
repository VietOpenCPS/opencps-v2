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
	
	$(document).on('click', 'div[data-toggle="collapse"]', function(event){
		$(this).next().toggleClass("toggle-hide");
	});
	
	$(document).on('click', '.slide-toggle', function(event){
		$(this).next('.collapse').slideToggle();
	});
	
	$(document).on('click', '.slide-toggle-lv2', function(event){
		$(this).parent().next('.collapse').slideToggle();
	});
	
	$('.toggle-collapse').on('click', function() {
		$(this).toggleClass('fa-expand fa-compress');
		$('body #appManagerDossier').toggleClass('show-hide');
	});
	
	$('#column-1 > div > div').each(function(){
		if($(this).hasClass('portlet-boundary_FrontendWebPortal_LoginPortlet_')) {
			$('body').addClass('login-page');
		} 
	});

});