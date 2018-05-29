<#if (Request)??>
	<#include "init.ftl">
</#if>

<script type="text/javascript">
	var layout = new kendo.Layout("layoutTemplate");
	var serviceInfoRouter = new kendo.Router({
        init: function() {
            console.log("init-------------");
            $("#serviceinfo-right-content").load("${ajax.serviceinfomain_list}",function(result){

            });
        }
    });

    serviceInfoRouter.route("/(:id)", function(id) {
    	console.log(id);
    	$("#serviceinfo-right-content").load("${ajax.serviceinfo_detail}",function(result){
    		console.log(id);
    		pullDataDetail(id);
    	});
    });

    serviceInfoRouter.route("/", function() {
        console.log("/////////////////");
        $("#serviceinfo-right-content").load("${ajax.serviceinfomain_list}",function(result){

        });
    });
</script>

<script type="text/javascript">
	$(function(){
    	// Run Routing
    	serviceInfoRouter.start();
    });
</script>