<#if (Request)??>
	<#include "init.ftl">
</#if>

<script type="text/javascript">
	var resultRouter = new kendo.Router();
    resultRouter.route("/", function(id) {
        $("#result_right_content").load("${ajax.result_mainlist}",function(result){
            $(".itemStatus").css("pointer-events","auto");
            $("#profileStatus li").removeClass("active");
            $("#profileStatus li > i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
        });
    });
    resultRouter.route("/(:id)", function(id) {
    	$("#result_right_content").load("${ajax.result_mainlist}",function(result){
            var listView = $("#listView").data("kendoListView");
            listView.dataSource.read({
                "state": id
            });
            $(".itemStatus").css("pointer-events","auto");
            $("#profileStatus li").removeClass("active");
            $("#profileStatus li > i").removeClass("fa fa-folder-open").addClass("fa fa-folder");
            $('#profileStatus li[dataPk='+id+']').children("i").removeClass("fa fa-folder").addClass("fa fa-folder-open");
            $('#profileStatus li[dataPk='+id+']').css("pointer-events","none");
            $('#profileStatus li[dataPk='+id+']').addClass('active');
    	});
    });
</script>

<script type="text/javascript">
	$(function(){
    	// Run Routing
    	resultRouter.start();
    });
</script>