<#if (Request)??>
	<#include "init.ftl">
</#if>
	<div class="row">
		<div class="col-sm-3">
			<#include "customer_menu.ftl">
		</div>
		<div class="col-sm-9" id="dossier_list">
			<div class="row">
				<div class="col-sm-9">
					<div class="row panel">
						<div class="col-sm-12" id="customer_dossierlist">
;
						</div>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="row">
						<div class="col-sm-12" id="customer_additional_requirements">

						</div>
						<div class="col-sm-12" id="customer_payment_request">

						</div>
						<div class="col-sm-12" id="customer_result_request">

						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-9 P0" style="display: none;" id="dossier_detail">

		</div>
		<div class="col-sm-9 P0" style="display: none;" id="left_container">

		</div>
	</div>

	<!-- popup notification -->
	<!-- container -->
	<span id="notification" style="display:none;"></span>

	<!-- templates -->
	<script id="successTemplate" type="text/x-kendo-template">
	  <div class="popup-error-notification">
	    <p>#= message #</p>
	  </div>
	</script>

	<script id="errorTemplate" type="text/x-kendo-template">
	  <div class="popup-success-notification">
	    <p>#= message #</p>
	  </div>
	</script>

	<!-- script -->
	<script type="text/javascript">
	  var notification;
	  $(document).ready(function() {
	    notification = $("#notification").kendoNotification({
	        position: {
	            pinned: true,
	            top: 30,
	            right: 30
	        },
	        autoHideAfter: 2000,
	        stacking: "down",
	        templates: [
	          {
	            type: "success",
	            template: $("#successTemplate").html()
	          },
	          {
	            type: "error",
	            template: $("#errorTemplate").html()
	          }
	        ]
	    }).data("kendoNotification");
	  });

	</script>
