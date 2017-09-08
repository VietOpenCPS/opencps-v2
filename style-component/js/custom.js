//Toggle Guide section
$('#guide-toggle').click(function() {
	event.preventDefault();

    $(this).parent().prev().toggleClass('more');

    var t = $(this);
    var originaltext= t.text();
    $('#guide-toggle').text('Xem thêm >>');  
    t.text(originaltext);
    t.html(t.text() == 'Xem thêm >>' ? 'Rút gọn <<' : 'Xem thêm >>');
});


$(function () {
	$('[data-toggle="tooltip"]').tooltip()
})