<#if (Request)??>
<#include "init.ftl">
</#if>
<div class="ul-style-triangle">
  <ul id="govAgencyList">

  </ul>
</div>
<script type="text/x-kendo-template" id="govTemplate">
    <li><a href="/group/cong-tiep-nhan/quan-ly-ho-so\#/taohosomoi/#=itemCode#">#=itemName#</a></li>
</script>
<script type="text/javascript">
    var govDataSource = new kendo.data.DataSource({
            transport : {
                read: function(options)
                {
                    $.ajax({
                        url: '${(api)!}/dictcollections/GOVERNMENT_AGENCY/dictitems?sort=sibling',
                        type: 'GET',
                        dataType: 'json',
                        headers: {groupId: 55217},
                        success: function(result)
                        {
                            console.log(result);
                            options.success(result.data);
                        }
                    })             
                },
            }
        });

    $("#govAgencyList").kendoListView({
        dataSource: govDataSource,
        template: kendo.template($("#govTemplate").html())
    });

</script>