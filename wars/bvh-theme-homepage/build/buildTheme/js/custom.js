$(document).ready(function(){
	$( "body" ).delegate( 'a[data-toggle="dropdown"]', "click", function() {
		$(this).parent().toggleClass( "open" );
	});
	
	$( "body" ).delegate( 'button[data-toggle="dropdown"]', "click", function() {
		$(this).parent().toggleClass( "open" );
	});

	////Slide
	$('.img-gallery .slider').slick({
		autoplay: false,
		slidesToShow: 1,
		slidesToScroll: 1,
		arrows: true
	});
});