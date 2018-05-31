<script type="text/javascript">
	var dossierObject = {};

	var addDossierRouter = new kendo.Router();
	//Step 3 route
	addDossierRouter.route("/buoc4", function() {
		$.ajax({
			url: "${(ajax.add_dossier_step_4)!}",
			success: function(data)
			{
				$("#step4, #step2, #step3").addClass("done");
				$("#contentMain").html(data);
			}
		})
	});
	//Step 3 route
	addDossierRouter.route("/buoc3", function() {
		$.ajax({
			url: "${(ajax.add_dossier_step_3)!}",
			success: function(data)
			{
				$("#step4").removeClass("done");
				$("#step3, #step2").addClass("done");
				$("#contentMain").html(data);
			}
		})
	});
	//Step 2 route
	addDossierRouter.route("/buoc2", function() {
		$.ajax({
			url: "${(ajax.add_dossier_step_2)!}",
			success: function(data)
			{
				$("#step3, #step4").removeClass("done");
				$("#step2").addClass("done");
				$("#contentMain").html(data);
			}
		})
	});
	//Step 1 route
	addDossierRouter.route("/buoc1", function() {
		$.ajax({
			url: "${(ajax.add_dossier_step_1)!}",
			success: function(data)
			{
				$("#step2, #step3, #step4").removeClass("done");
				$("#contentMain").html(data);
			}
		})
	});
	//add new dosssier
	addDossierRouter.route("/tao-ho-so-moi", function() {
		$.ajax({
			url: "${(ajax.add_dossier)!}",
			success: function(data)
			{
				$("#step2, #step3, #step4").removeClass("done");
				$("#mainType1").html(data);
			}
		})
	});

	//new dosssiers
	addDossierRouter.route("/new", function() {
		$.ajax({
			url: "${(ajax.newDossiers)!}",
			success: function(data)
			{
				$("#mainType1").html(data);
			}
		})
	});
	//new dosssiers
	addDossierRouter.route("/processing", function() {
		$.ajax({
			url: "${(ajax.processingDossiers)!}",
			success: function(data)
			{
				$("#mainType1").html(data);
			}
		})
	});
	//new dosssiers
	addDossierRouter.route("/release", function() {
		$.ajax({
			url: "${(ajax.releaseDossiers)!}",
			success: function(data)
			{
				$("#mainType1").html(data);
			}
		})
	});
	//new dosssiers
	addDossierRouter.route("/done", function() {
		$.ajax({
			url: "${(ajax.doneDossiers)!}",
			success: function(data)
			{
				$("#mainType1").html(data);
			}
		})
	});

	$(function() {
		addDossierRouter.start();
	});
</script>
