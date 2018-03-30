<#include "init.ftl">

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

		</div>

		<div id="_collectionDetail_dictItem_link"></div>

		<div id="_collectionDetail_formTemplate_link">

		</div>

	</div>

</div>

<script>
$(document).ready(function() {

    var collectionTabs = $("#_collectionDetail_tabstrip").kendoTabStrip({


		select: function(e){
			
			if($(e.item).index() == 0){

				$("#_collectionDetail_info_link").load('${url.adminDataMgtPortlet.dictcollection_detail_info}&${portletNamespace}type=${constant.type_dictCollection}&${portletNamespace}collectionCode=${(dictCollection_dictCollection.collectionCode)!}');

			} else if($(e.item).index() == 1){

				$("#_collectionDetail_dictItem_link").load('${url.adminDataMgtPortlet.dictcollection_detail_dictitem}&${portletNamespace}type=${constant.type_dictCollection}&${portletNamespace}collectionCode=${(dictCollection_dictCollection.collectionCode)!}');

			} else if($(e.item).index() == 2){

				$("#_collectionDetail_formTemplate_link").load('${url.adminDataMgtPortlet.dictcollection_detail_formtemplate}&${portletNamespace}type=${constant.type_dictCollection}&${portletNamespace}collectionCode=${(dictCollection_dictCollection.collectionCode)!}');

			}

		}

    });
    
    $("#_collectionDetail_tabstrip").data("kendoTabStrip").select(1);

});
</script>
