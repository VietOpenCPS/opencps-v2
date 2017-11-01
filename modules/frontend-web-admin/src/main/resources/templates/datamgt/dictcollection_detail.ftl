<#if (Request)??>
<#include "init.ftl">
</#if>

<div class="box-s2">
		
	<div id="_collectionDetail_tabstrip" class="col-xs-12 col-sm-12 tabstrip-hoz">

		<ul>

			<li class="k-state-active">
				Thông tin chi tiết
			</li>
			<li>
				Danh sách dữ liệu danh mục
			</li>
			<li>
				Mẫu form
			</li>

		</ul>

		<div id="_collectionDetail_info_link">
			
			<#include "dictcollection_detail_info.ftl">

		</div>

		<div id="_collectionDetail_dictItem_link"></div>

		<div id="_collectionDetail_formTemplate_link">
			
			<#include "dictcollection_detail_formtemplate.ftl">

		</div>

	</div>

</div>

<script>
$(document).ready(function() {

    $("#_collectionDetail_tabstrip").kendoTabStrip({


		select: function(e){
			
			if($(e.item).index() == 1){

				$("#_collectionDetail_dictItem_link").load('${url.adminDataMgtPortlet.dictcollection_detail_dictitem}&${portletNamespace}type=${constants.type_dictCollection}&${portletNamespace}collectionCode=${(dictCollection_dictCollection.collectionCode)!}');

			}

		}

    });

});
</script>
