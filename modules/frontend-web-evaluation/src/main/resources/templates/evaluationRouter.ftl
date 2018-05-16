<#if (Request)??>
<#include "init.ftl">
</#if>

<script type="text/javascript">
	var evaluationRouter = new kendo.Router();
    evaluationRouter.route("/", function(id) {
        $("#listView").load("${ajax.evaluationMainList}",function(result){

        });
    });
    evaluationRouter.route("/(:id)", function(id) {
        $("#listView").load("${ajax.evaluationMainList}",function(result){
            var listView = $("#listView").data("kendoListView");

            listView.dataSource.transport.read = function(options){
                $.ajax({
                    url: "http://localhost:8080/o/rest/v2/employees?workingunit="+id+"",
                    dataType:"json",
                    headers : {"groupId": 55301},
                    type:"GET",
                    data: options.data,
                    success:function(result){
                        if(result.data){
                            options.success(result);
                        }else{
                            options.success({
                                "total":0,
                                "data":[]
                            })
                        }
                    },
                    error:function(result){
                        options.error(result);
                    }
                });
            }
            listView.dataSource.read();
        });

        
    });
</script>

<script type="text/javascript">
	$(function(){
    	// Run Routing
    	evaluationRouter.start();
    });
</script>