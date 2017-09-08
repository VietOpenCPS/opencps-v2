$(document).ready(function() {
    $("#menu-toggle-left").click(function(e) {
        e.preventDefault();
        $("#main-content").toggleClass("toggled-left");
        $('#menu ul').hide();
    });

    $("#menu-toggle-right").click(function(e) {
        e.preventDefault();
        $("#main-content").toggleClass("toggled-right");
    });
});

function initMenu() {
    $('#menu ul').hide();
    $('#menu ul').children('.current').parent().show();
    //$('#menu ul:first').show();
    $('#menu li a').click(
        function() {
            var checkElement = $(this).next();
            if ((checkElement.is('ul')) && (checkElement.is(':visible'))) {
                return false;
            }
            if ((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
                $('#menu ul:visible').slideUp('normal');
                checkElement.slideDown('normal');
                return false;
            }
        }
    );
}

$(document).ready(function() {
    initMenu();
});