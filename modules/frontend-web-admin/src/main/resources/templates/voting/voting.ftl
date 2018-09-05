<#include "init.ftl">
<div class="row box" >
	<div class="col-sm-12 col-xs-12 text-xs-center MT20">
		<input id="workingUnitVoting" name="workingUnitVoting" />
	</div>
	<div class="col-sm-12 col-xs-12">
		<div class="MB20 MT10">
			<span class="votingTitle" style="font-size: 14px; font-weight: bold; text-transform: uppercase;">Phiếu khảo sát</span>
			<i style="cursor: pointer; margin-right: 20px; font-size: 18px;" onclick="addVoting()" class="fa fa-plus-circle pull-right" aria-hidden="true"></i>
		</div>
		<ul class="ul-with-border ul-default" id="votingListView"></ul>
		<script id="voting_template" type="text/x-kendo-template">
			<li>
				<div class="MB10 col-sm-12 P0" style="width: 100%;">
					<span style="font-size: 15px;">#=subject#</span>
					<i class="fa fa-pencil pull-right ML10" style="font-size: 18px;" onclick="onChangeVoting('#=id#')" aria-hidden="true"></i>
					<i class="fa fa-times pull-right ML10 k-delete-button" style="font-size: 18px;" aria-hidden="true"></i>
					<small style="padding-top: 3px;" class="text-gray pull-right"> Tạo vào #=moment(String(createDate)).utc().format('DD/MM/YYYY HH:mm:ss')#</small>
				</div>
				#
					var checked = '';
					for(var i = 0; i < choices.length; i++) {
					if (typeof selected != 'undefined') {
						if (i === (selected - 1)) {
							checked = 'checked="checked"'
						}
					}
				#
				<div class="col-sm-11">
					<input type="radio" name="choices#=id#" id="choices#=id##=i#" class="" #=checked#>
					<label style="position: relative; bottom: 4px;" class="" for="choices#=id##=i#">#=choices[i]#</label>
				</div>
				<div class="col-sm-1 text-center">
					<span>#=i + 1# / #=answers[i]#</span>
				</div>
				#
					}
				#

				#
					if (commentable) {
				#
					<textarea class="form-control MT10 MB10" tabindex="0" placeholder="Nhập đáp án khác ..." rows="2"></textarea>
				#
					}
				#
			</li>
		</script>
	</div>
	<div class="modal fade" id="dialogVoting" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Thêm câu hỏi</h4>
				</div>
				<div class="modal-body">
					<form id="formVoting" name="formVoting">
						<div class="form-group">
							<label for="subject">Nội dung ý kiến:</label>
							<textarea id="subject" class="form-control" name="subject" tabindex="0" placeholder="Nhập nội dung xin ý kiến" rows="3"></textarea>
						</div>
						<div id="listChoises">
						</div>
						<script type="text/x-kendo-template" id="templateChoiseVoting">
							<div class="form-group item-choise" data-index="">
								<label>Câu trả lời</label>
								<input class="form-control" name="choice" value="" />
								<i style="cursor: pointer;" onclick="deleteChoise(i)" class="fa fa-plus-circle pull-right" aria-hidden="true"></i>
							</div>	
						</script>
						<div class="MT5">
							<input type="checkbox" id="commentable" class="k-checkbox">
							<label class="k-checkbox-label" for="commentable">Sử dụng câu trả lời khác</label>
							<i style="cursor: pointer; font-size: 17px;" onclick="addChoise()" class="fa fa-plus-circle pull-right" aria-hidden="true"></i>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-active" onclick="submitVoting()"><i class="fa fa-floppy-o" aria-hidden="true"></i> Lưu lại</button>
					<button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-arrow-left" aria-hidden="true"></i> Quay lại</button>
				</div>
			</div>

		</div>
	</div>
</div>
<input type="hidden" id="_voting_hidden_new_id" name="_voting_hidden_new_id" />

<script>
	var listChoises = [];
	function clearInputVot () {
		$(".input-choise-voting").val("");
		$("#subject").val("");
	}
	function onChangeVoting(id) {
		console.log('id------', id)
		var item = $("#votingListView").getKendoListView().dataSource.get(id);
		if (item) {
			console.log(item)
			$("#_voting_hidden_new_id").val(id)
			$("#subject").val(item.subject);
			$("#commentable").prop('checked', item.commentable);
			listChoises = item.choices;
			genChoises(listChoises);
			$("#dialogVoting").modal('show');
		}
	}
	function addChoise () {
		console.log('cccc')
		listChoises.push(" ");
		genChoises(listChoises)
	}
	function addVoting () {
		clearInputVot();
		$("#_voting_hidden_new_id").val("");
		listChoises = ["", ""];
		console.log(listChoises);
		genChoises(listChoises);
		$("#dialogVoting").modal('show');
	}
	function deleteChoise (i) {
		if (i !== null && i !== undefined) {
			if (listChoises.length <= 2) {
				return
			}
			listChoises.splice(i, 1);
			genChoises(listChoises);
		}
	}
	function genChoises (arr) {
		$("#listChoises").html("");
		if (arr.length > 0) {
			for (var i = 0; i < arr.length; i++) {
				$("#listChoises").append('<div class="form-group item-choise" data-index="' + i + '"><label>Câu trả lời</label><input class="form-control input-choise-voting" style="width: 95%;" name="choice" value="' + arr[i] + '" /><i style="cursor: pointer;top: -22px;position:  relative; font-size: 15px;" onclick="deleteChoise(' + i + ')" class="fa fa-window-close pull-right" aria-hidden="true"></i></div>')
			}
		} else {
			$("#listChoises").html("")
		}
	}
	function submitVoting () {
		var x = document.forms['formVoting']
		var choicesItems = ''
		for(var i=0; i< x.length; i++){
			var field = x[i];

			if(field.name.indexOf('choice'||'none') >=0) {
				if(field.value == '') {
					notification.show({
						message: "Bạn phải điền câu trả lời!"
					}, "error");
					return
				} else {
					choicesItems += field.value + '\r\n'
				}
			}
		}
		var type = "POST";
		var url = "";
		if ($("#_voting_hidden_new_id").val()) {
			type = "PUT";
			url = "/o/rest/v2/pk5/votings/" + $("#_voting_hidden_new_id").val()
		} else {
			type = "POST";
			url = "/o/rest/v2/pk5/votings"
		}

		$.ajax({

			url: url,
			dataType: "json",
			type: type,
			headers: {
				"groupId": ${groupId}
			},
			data: {
				className: "question_opencps",
				classPK: "0",
				subject: $("#subject").val(),
				choices: choicesItems,
				commentable: $("#commentable").prop("checked")
			},
			success: function(result) {
				result["data"] = result.total == 0 ? []: result["data"];
				$("#votingListView").getKendoListView().dataSource.pushUpdate(result);
				if (type === "POST") {
					clearInputVot();
				} else {
					$("#dialogVoting").modal('hide');
				}
			},
			error: function(xhr, textStatus, errorThrown) {
				showMessageByAPICode(xhr.status);
			}

		});
	}
	var _voting_dataSource = new kendo.data.DataSource({

			transport: {
	
				read: function(options) {
					var working = options.data.working ? options.data.working : 0;
					$.ajax({
					
						url: '/o/rest/v2/pk5/votings/question_opencps/' + working,
						dataType: "json",
						type: 'GET',
						headers: {
							"groupId": ${groupId}
						},
						data: {
						},
						success: function(result) {
							result["data"] = result.total==0 ? []: result["data"];
							options.success(result);
							
						},
						error: function(xhr, textStatus, errorThrown) {
							options.error(xhr);
							showMessageByAPICode(xhr.status);
						
						}
					
					});
				},
				destroy: function(options) {
					var cf = confirm('Bạn có chắc muốn xóa bản ghi này?');
					
					if(cf){
						
						$.ajax({
							url: '/o/rest/v2/pk5/votings/' + options.data.votingId,
							headers: {
								"groupId": ${groupId}
							},
							type: 'DELETE',
							success: function(result) {
								
								$("#_voting_hidden_new_id").val("");
								options.success();
								showMessageToastr("success", 'Yêu cầu của bạn được xử lý thành công!');
								
							},
							error: function(xhr, textStatus, errorThrown) {
								
								$("#votingListView").getKendoListView().dataSource.error();
								showMessageByAPICode(xhr.status);
								
							}
							
						});
						
					} else{
						
						options.error();
					}
					
				},
				parameterMap: function(options, operation) {
					
					if (operation !== "read" && options.models) {
						return {
							models: kendo.stringify(options.models)
						};
					}
				}
			},
			schema: {
			
				data: "data",
				total: "total",
				model: {
					id:"votingId"
				}
			},
			error: function(e) {
				
				this.cancelChanges();
				
			}

		});
	
	$("#votingListView").kendoListView({
		
		remove: function(e) {
			
		},

		dataSource: _voting_dataSource,

		dataBound: function (){

		},

		change: function () {

		},

		template: kendo.template($("#voting_template").html()),

		// filterable: {
		// 	field: "holidayDate", operator: "contains", 	value: $("#_holiday_keySearch").val().trim() 
		// }
		
	});

	$("#workingUnitVoting").kendoComboBox({
		dataTextField : "itemName",
		dataValueField: "itemCode",
		dataSource: {
			transport : {
				read : function(options){
					$.ajax({
						url : "/o/rest/v2/dictcollections/GOVERNMENT_AGENCY/dictitems?sort=sibling",
						dataType : "json",
						type : "GET",
						headers: {"groupId": ${groupId}},
						success : function(result){
							if(result.data){
								options.success(result);
							}else {
								options.success({
									"total" : 0,
									"data" : []
								});
							}

						},
						error : function(xhr){
							options.error(xhr);
						}
					});
					
				}
			},
			schema : {
				total : "total",
				data : "data",
				model : {
					fields : {
						itemName : {
							type : "string"
						},
						itemCode : {
							type : "string"
						}
					}
				}
			}
		},
		change: function (e) {
			var value = this.value() ? this.value() : 0;
			$("#votingListView").getKendoListView().dataSource.read({
				working: this.value()
			})
		},
		filter: "contains",
		placeholder: "Chọn cơ quan",
		noDataTemplate: 'Không có dữ liệu'
	});
</script>