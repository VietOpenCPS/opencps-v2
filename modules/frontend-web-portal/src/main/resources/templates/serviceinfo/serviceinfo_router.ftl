<#if (Request)??>
	<#include "init.ftl">
</#if>

<script type="text/javascript">
	var layout = new kendo.Layout("layoutTemplate");
	var serviceInfoRouter = new kendo.Router();
    serviceInfoRouter.route("/", function(id) {
        console.log(id);
        $("#serviceinfo-right-content").load("${ajax.serviceinfomain_list}",function(result){
            if(!"${domain}"){
                $("#administration > li:first-child").addClass("active");
                $("#administrationCodeSearch").data("kendoComboBox").value($("#administration > li:first-child").attr("dataPk"));
                $("#administrationCodeSearch").data("kendoComboBox")._isSelect = false;
                $("#service_info_list_view").getKendoListView().dataSource.read({
                  "administration": $("#administration > li:first-child").attr("dataPk")
              });
            }
        });
    });
    serviceInfoRouter.route("/(:id)", function(id) {
    	$("#serviceinfo-right-content").load("${ajax.serviceinfo_detail}",function(result){
            console.log(id);
    		pullDataDetail(id);
    	});
    });



</script>

<script type="text/javascript">
	$(function(){
    	// Run Routing
    	serviceInfoRouter.start();
    });
</script>