<!-- TODO detailTemplate page -->
<div v-if="detailPage" style="width: 100%;">
	<input type="hidden" id="dossierId__hidden" :value="detailModel.dossierId" />
	<div class="row-header">
		<div class="background-triangle-big"> Tên thủ tục </div>
		<div class="layout row wrap header_tools row-blue">
	
			<div class="flex xs7 sm9 solo pl-4 text-ellipsis">
		
				{{detailModel.dossierNo}} {{detailModel.serviceName}}
		
			</div>
			<div class="flex xs5 sm3 text-right" style="margin-left: auto;">
		
				<v-btn flat class=" my-0 py-0 btn-border-left" color="grey darken-1" v-on:click.native.prevent.stop="undoDetailPage()">
					<v-icon class="mr-2">undo</v-icon>
				Quay lại
				</v-btn>
                <v-btn v-if="navigationFilterview" v-on:click.native.prevent.stop="navigationFilterview = !navigationFilterview" flat icon class="mr-3 my-0 hidden-sm-and-down"><v-icon>fullscreen</v-icon></v-btn>

                <v-btn v-if="!navigationFilterview" v-on:click.native.prevent.stop="navigationFilterview = !navigationFilterview" flat icon class="mr-3 my-0 hidden-sm-and-down"><v-icon>fullscreen_exit</v-icon></v-btn>

			</div>
	
		</div>
	
	</div>
	
	<v-expansion-panel expand class="my-0 opencps-dossier-info">
		<v-expansion-panel-content v-bind:value="true">
		<div slot="header" class="text-bold primary--text">Thông tin chung hồ sơ</div>
		<v-layout wrap class="px-4 pb-2">
			<v-flex xs12 sm6>
			<div class="pb-1">
				<span class="text-bold">
				Tên doanh nghiệp: 
				</span>
				{{ detailModel.applicantName }} 
			</div>
			<div class="pb-1">
				<span class="text-bold">
				Mã hồ sơ: 
				</span>
				{{ detailModel.dossierNo }}
			</div>
			<div class="pb-1">
				<span class="text-bold">
				Số hồ sơ: 
				</span>
				{{ detailModel.dossierIdCTN }}
			</div>
			<div class="pb-1">
				<span class="text-bold">
				Chuyển bởi: 
				</span>
				{{detailModel.lastActionUser}}
			</div>
            <div>
				{{detailModel.lastActionName}}
			</div>
			<div v-html="detailModel.lastActionNote">

			</div>
			</v-flex>
			<v-flex xs12 sm6>
				<div class="pb-1">
					<span class="text-bold">
					Thời gian gửi: 
					</span>
					{{ detailModel.submitDate}}
				</div>
				<div class="pb-1">
					<span class="text-bold">
					Thời gian tiếp nhận: 
					</span>
					{{ detailModel.receiveDate}}
				</div>
				<div class="pb-1">
					<span class="text-bold">
					Thời hạn xử lý: 
					</span>
					{{ detailModel.dueDate}}
				</div>
				<div class="pb-1">
					<span class="text-bold">
					Trạng thái: 
					</span>
					{{detailModel.dossierStatusText}}
				</div>
				<div>
					<a href="javascript:;" @click.prevent.stop="showContactDetail = !showContactDetail">
					Thông tin liên hệ: <v-icon color="primary" v-if="!showContactDetail">keyboard_arrow_down</v-icon>
					<v-icon color="primary" v-if="showContactDetail">keyboard_arrow_up</v-icon>
					</a>
					
					<v-slide-y-transition>
						<div v-if="showContactDetail">
							<div class="pb-1">
								<span class="text-bold">
								Tên doanh nghiệp: 
								</span> 
								{{detailModel.contactName}}
							</div>
							<div class="pb-1">
								<span class="text-bold">
								Địa chỉ email: 
								</span>
								{{detailModel.contactEmail}}
							</div>
							<div class="pb-1">
								<span class="text-bold">
								Số điện thoại: 
								</span>
								{{detailModel.contactTelNo}}
							</div>
							<div class="pb-1">
								<span class="text-bold">
								Địa chỉ: 
								</span>
								{{detailModel.wardName}}/{{detailModel.districtName}}/{{detailModel.cityName}}
							</div>
							
						</div>
					</v-slide-y-transition>
						
				</div>
				
			</v-flex>
		</v-layout>
	
		<#-- <div class="text-bold primary--text expansion-panel__header">Thông tin sản phẩm</div>
		<v-layout wrap class="px-4 pb-2">
			<v-flex xs12 sm6>
			<div class="pb-1" v-html="detailModel.briefNote"></div>
			</v-flex>
		</v-layout> -->
		</v-expansion-panel-content>
	</v-expansion-panel>

	<v-tabs :scrollable="false">
		<v-tabs-bar class="grey-opencps-panel" dark>
			<v-tabs-item :href="'#tab-dossier-detail-1'">
			THÀNH PHẦN HỒ SƠ
			</v-tabs-item>
			<v-tabs-item :href="'#tab-dossier-detail-2'" @click.prevent.stop="_initchangeProcessStep();">
			THỤ LÝ HỒ SƠ
			</v-tabs-item>
			<v-tabs-item :href="'#tab-dossier-detail-3'" @click.prevent.stop="selectDossierActionTab(detailModel)">
			TIẾN TRÌNH XỬ LÝ
			</v-tabs-item>
			<v-tabs-item :href="'#tab-dossier-detail-4'" onclick="getContacts(1)">
			TRAO ĐỔI THÔNG TIN
			</v-tabs-item>
			<v-tabs-slider color="primary"></v-tabs-slider>
		</v-tabs-bar>
		<v-tabs-items>
			<v-tabs-content :id="'tab-dossier-detail-1'" reverse-transition="slide-y-transition" transition="slide-y-transition">
			<v-expansion-panel expand class="my-0">
				<v-expansion-panel-content v-bind:value="true">
				<div slot="header" class="text-bold"> <span>I. </span>Tài liệu nộp</div>
				<small slot="header" class="text-gray text-right mr-4"> Những thành phần hồ sơ có dấu ( <span style="color: red;"> * </span> ) là thành phần bắt buộc</small>
				<div class="opencps_list_border_style" jx-bind="listDocumentIn"></div>
				</v-expansion-panel-content>
			</v-expansion-panel>
			<v-expansion-panel expand class="my-0">
				<v-expansion-panel-content v-bind:value="true">
				<div slot="header" class="text-bold"> <span>II. </span>Kết quả</div>
				<div class="opencps_list_border_style" jx-bind="listDocumentOut"></div> 
				</v-expansion-panel-content>
			</v-expansion-panel>
			</v-tabs-content>
			<v-tabs-content :id="'tab-dossier-detail-2'" reverse-transition="slide-y-transition" transition="slide-y-transition">
				<v-tabs :scrollable="false" v-if="processSteps">
					<v-tabs-bar dark class="grey-opencps-panel grey-opencps-panel-group-button">
					<v-tabs-item
						v-for="step in processSteps"
						:key="step.processActionId"
						:href="'#tab-temp-' + step.processActionId"
						@click.prevent.stop="changeProcessStep(step)"
					>
                        <v-btn
                            :loading="true"
                            :disabled="true"
                            v-if="stepLoading"
                        >
                            <span slot="loader">Loading...</span>
                        </v-btn>
                        <v-btn
                            color="primary"
                            class="mx-0 my-0"
                            v-else
                        >
                            {{ step.actionName }}
                        </v-btn>
						
					</v-tabs-item>
					<v-menu>
					</v-menu>
					</v-tabs-bar>
				</v-tabs>
				<v-card v-if="stepModel != null">
					<div v-if="stepModel.pending">
						Hồ sơ chờ đồng bộ ...
					</div>
					<div v-else-if="stepModel.plugin">
						<div class="flex xs12 sm12 text-center">
							<object id="dossierPDFViewPlugin" data="" width="100%" height="100%">
									<iframe :src="stepModel.url" width="100%" height="100%"> </iframe>
							</object>
							<div id="dossierPDFViewNotFound" class="text-center">{{ stepModel.no_pdf }}</div>
						</div>
					</div>
                    <div v-else>
                    	
                    	<v-card-title primary-title class="mx-2 pb-0" v-if="stepModel.createFiles">
                            <v-layout wrap> 
                                <v-flex xs12>
                                    Thành phần kết quả:
                                </v-flex>
                            </v-layout>
                        </v-card-title>
                        
                    	<v-expansion-panel class="my-0 expansion__list_style">
                            <v-expansion-panel-content v-for="(item,i) in stepModel.createFiles" v-if="item" :key="item.dossierPartId">
                            <div slot="header" @click.prevent="showAlpacaJSFORM(item)">{{i + 1}}. {{item.partName}} <small v-if="item.eform">( Form trực tuyến )</small> </div>
                            <div slot="header" class="text-right">
                                <v-btn flat icon light class="small-btn-x mx-0 my-0" v-on:click.native.prevent="singleFileUpload(item)">
                                    <v-icon>file_upload</v-icon>
                                </v-btn>
                                <v-btn color="primary" fab small dark class="small-btn-x mx-0 my-0" v-on:click.native.prevent="viewDossierFileResult(item, i)">
                                    {{item.counter}}
                                </v-btn>
                                
                                <v-btn flat icon light class="small-btn-x mx-0 my-0" v-on:click.native.prevent="deleteDossierFileVersion(item)">
                                    <v-icon>delete</v-icon>
                                </v-btn>
                            </div>

                            <input type="file" :id="'inputfile_'+item.dossierPartId" style="display:none" v-on:change="singleFileUploadInput($event, item, i)"/>
                            <div class="alert alert--outline success--text mx-4 hidden" :id="'message_success_'+item.referenceUid" >
                            	<i aria-hidden="true" class="material-icons icon alert__icon">check_circle</i>
	                            <div>
							    	Ghi lại {{item.partName}} Thành công!.
							  	</div>
						  	</div>
						  	<div class="alert alert--outline error--text mx-4 hidden" :id="'message_error_'+item.referenceUid" >
                            	<i aria-hidden="true" class="material-icons icon alert__icon">warning</i>
	                            <div>
							    	Ghi lại {{item.partName}} Thất bại!.
							  	</div>
						  	</div>
                            <div class="text-right pr-4" v-if="item.eform">
                                <v-btn color="primary" :id="'btn-save-formalpaca'+item.partNo"
                                	:referenceUid="item.referenceUid"
                                	:dossierId="detailModel.dossierId"
                                	class="saveForm"
                                    :loading="loadingAlpacajsForm"
                                    :disabled="loadingAlpacajsForm"
                                    v-on:click.native.prevent.stop="submitAlpacajsForm(item)"> Ghi lại </v-btn>
                            </div>
                            <div :id="'alpacajs_form_'+item.partNo" class="expansion-panel__header"></div>
							<input type="hidden" :id="'dossierFileId' + item.partNo" :value="item.dossierFileId" />

							<input v-if="item.counter > 0" type="hidden" class="validCreatePart" :actionCode="stepModel.actionCode" :id="'validCreateFile' + item.partNo" value="1" />
							
							<input v-else type="hidden" class="validCreatePart" :actionCode="stepModel.actionCode" :id="'validCreateFile' + item.partNo" value="0" />

							
                            </v-expansion-panel-content>
                        </v-expansion-panel>
                        
                        <v-card-title primary-title class="mx-2 py-0">
                            <v-layout wrap> 
                            	<v-flex xs12 class="mb-3" v-if="stepModel.allowAssignUser">
                                    <div jx-bind="processAssignUserId"></div>
                                </v-flex>
                                <v-flex xs12>
                                    Nhập ý kiến {{stepModel.actionName}}:
                                    <div jx-bind="processActionNote"></div>
                                </v-flex>
                            </v-layout>
                        </v-card-title>
                        
                        <v-card-actions>
                            <v-btn flat color="primary" class="px-0" :loading="actionsSubmitLoading" :disabled="actionsSubmitLoading"
                            @click.prevent.stop="postNextActions(stepModel)">Xác nhận</v-btn>
                        </v-card-actions>
                    </div>
				</v-card>
                
			</v-tabs-content>
			<v-tabs-content :id="'tab-dossier-detail-3'" reverse-transition="slide-y-transition" transition="slide-y-transition">
			<div class="opencps_list_border_style" jx-bind="listHistoryProcessing"></div>
			</v-tabs-content>
			<v-tabs-content :id="'tab-dossier-detail-4'" reverse-transition="slide-y-transition" transition="slide-y-transition">

				<div id="comments-container" class="pt-3 px-4" style="background: white;"></div>
				<div id="comments-container-pk" style="display: none;">{{detailModel.dossierId}}</div>

			</v-tabs-content>
		</v-tabs-items>
	</v-tabs>
</div>
			
<script type="text/javascript">
		
		function plugin0() {
            return document.getElementById('plugin0');
        }
        var plugin = plugin0;
            
		var fnSaveForm = function(id, value){
			var current = $("#btn-save-formalpaca"+id);
			var referentUid = current.attr("referenceUid");
			var dossierId = current.attr("dossierId");
			console.log('dossierId: '+dossierId);
			if(referentUid){
				$.ajax({
					url : "/o/rest/v2/dossiers/"+dossierId+"/files/"+referentUid+"/formdata",
					dataType : "json",
					type : "PUT",
					headers: {
						"groupId": themeDisplay.getScopeGroupId(),
						Accept : "application/json"
					},
					data : {
						formdata: JSON.stringify(value)
					},
					success : function(result){
						console.log(result);
					
						$('#message_success_'+referentUid).removeClass('hidden');
						setTimeout(
							function(){ 
								$('#message_success_'+referentUid).addClass('hidden');
							}, 
						10000);

						console.log("id----",id);
						console.log("validCreateFile----",$("#validCreateFile"+id));

						$("#validCreateFile"+id).val("1");
					},
					error : function(result){
						$('#message_error_'+referentUid).removeClass('hidden');
						setTimeout(
							function(){ 
								$('#message_error_'+referentUid).addClass('hidden');
							}, 
						10000);
					}
				});
			}
		};
		
	     var users = [];
	     
	     var getContacts = function(){
	     var dossierIdComment = $('#comments-container-pk').text().trim();
			 $.ajax({
	            url: '/o/rest/v2/dossiers/'+dossierIdComment+'/contacts',
	            type: 'GET',
	            headers: {
	                "groupId": themeDisplay.getScopeGroupId()
	            },
	            async: false,
	            dataType: 'json',
	            data: {
	                userMapping: true
	            },
	            
	            success: function(result) {
	                if(result != null && result.hasOwnProperty('data')){
	                    var contacts = result.data;
	                    
	                    $.each(contacts, function(index, contact){
	                        var user = {};
	                        user.id = contact.userId;
	                        user.fullname = contact.userName;
	                        user.email = contact.email;
	                        user.profilePictureURL = contact.profileUrl
	                        users.push(user);
	                    });
	                  }else{
	                    users = [];
	                }
	                
	                renderComment(users);
	            },
	            error: function(){
	                users = [];
	                renderComment(users);
	            }
	        });

		};
		
		function renderComment(users){

			var dossierId = $('#comments-container-pk').text().trim();
			var groupId = themeDisplay.getScopeGroupId();

            $('#comments-container').comments({
                profilePictureURL: 'https://viima-app.s3.amazonaws.com/media/user_profiles/user-icon.png',
                textareaRows: 1,
                enableAttachments: true,
                enableHashtags: true,
                enablePinging: true,
                postCommentOnEnter: false,
                forceResponsive: false,
                readOnly: false,
                newestText: "Mới nhất",
                oldestText: "Cũ nhất",
                popularText: "Phổ biến",
                attachmentsText: "Đính kèm",
                sendText: "Gửi",
                replyText: "Trả lời",
                editText: "Sửa",
                editedText: "Đã sửa",
                youText: "Bạn",
                saveText: "Ghi lại",
                deleteText: "Xóa",
                viewAllRepliesText: "Xem tất cả câu trả lời",
                hideRepliesText: "Ẩn câu trả lời",
                noCommentsText: "Không có bình luận nào",
                noAttachmentsText: "Không có tệp đính kèm",
                attachmentDropText: "Kéo thả tệp đính kèm",
                fieldMappings: {
                    id: 'commentId',
                    parent: 'parent',
                    userId: 'userId',
                    created: 'createdDate',
                    modified: 'modifiedDate',
                    content: 'content',
                    fileURL: 'fileUrl',
                    fileMimeType: 'fileType',
                    fileName: 'fileName',
                    pings: 'pings',
                    creator: 'userId',
                    fullname: 'fullname',
                    profileURL: 'profileUrl',
                    profilePictureURL: 'pictureUrl',
                    isNew: 'isNew',
                    createdByAdmin: 'isAdmin',
                    createdByCurrentUser: 'createdByCurrentUser',
                    upvoteCount: 'upvoteCount',
                    userHasUpvoted: 'userHasUpvoted',
                    email: 'email',
                    className: 'className',
                    classPK: 'classPK',
                },
                
                timeFormatter: function(time) {
                    
                    if(time != null){
                        
                        var dt  = time.split(/\ |\s/);
                        if(dt.length == 2){
                            var d = dt[0].split(/\-|\s/);
                        
                            return (d.slice(0,3).reverse().join('/')) + ' ' + dt[1];
                        }else{
                            return time;
                        }
                    }
                    
                    return '';
                },
                
                getUsers: function(onSuccess, onError) {
                   onSuccess(users);
                   onError();
                },
                
                getComments: function(onSuccess, onError) {
                    $.ajax({
                        url: '/o/rest/v2/comments/org.opencps.dossiermgt.model.Dossier/'+ dossierId,
                        type: 'GET',
                        headers: {
                            "groupId": groupId
                        },
                        async: false,
                        dataType: 'json',
                        data: {
                            
                        },
                        success: function(comments) {
                         
                            var data = [];

                            if (comments.hasOwnProperty('data')) {
                                $.each(comments.data, function(index, comment){
                                    data.push(formatComment(comment, users));
                                });
                                onSuccess(data);
                            }
                            
                        },
                        error: function(xhr){
                            onSuccess([]);
                            onError();
                        }
                    });
                },
                postComment: function(data, onSuccess, onError) {
                    var strPings = data.pings.join();
                    $.ajax({
                        url: '/o/rest/v2/comments',
                        type: 'POST',
                        headers: {
                            "groupId": groupId
                        },
                        async: false,
                        dataType: 'json',
                        data: {
                            className: 'org.opencps.dossiermgt.model.Dossier',
                            classPK: dossierId,
                            parent: data.parent != null ? data.parent : 0,
                            pings: strPings,
                            content: data.content,
                            upvoteCount: data.upvoteCount
                        },
                        success: function(comment) {
                            if(comment != null){
                                onSuccess(formatComment(comment, users));
                            }else{
                                onSuccess([]);
                            }
                            
                        },
                        error: function(xhr){
                            onError();
                        }
                    });
                },
                putComment: function(data, onSuccess, onError) {
                    
                    $.ajax({
                        url: '/o/rest/v2/comments/' + data.commentId,
                        type: 'PUT',
                        headers: {
                            "groupId": groupId
                        },
                        async: false,
                        dataType: 'json',
                        data: {
                            className: 'org.opencps.dossiermgt.model.Dossier',
                            classPK: dossierId,
                            parent: data.parent != null ? data.parent : 0,
                            pings: data.pings.join(),
                            content: data.content,
                            upvoteCount: data.upvoteCount
                        },
                        success: function(comment) {
                            onSuccess(formatComment(comment, users));
                        },
                        error: function(xhr){
                            onError();
                        }
                    });
                },
                deleteComment: function(data, onSuccess, onError) {
                   
                   	$.ajax({
                        url: '/o/rest/v2/comments/' + data.commentId,
                        type: 'DELETE',
                        data: data,
                        async: false,
                        dataType: 'json',
                        success: function(comment) {
                            onSuccess();
                        },
                        error: function(xhr){
                            onError();
                        }
                    });
                },
                upvoteComment: function(data, onSuccess, onError) {
                    
                    if(data.userHasUpvoted){
                        $.ajax({
                            url: '/o/rest/v2/comments/' + data.commentId + '/upvotes',
                            type: 'PUT',
                            headers: {
                                "groupId": groupId
                            },
                            async: false,
                            dataType: 'json',
                            data: {
                                commentId: data.commentId,
                                className: data.className,
                                classPK: data.classPK,
                                upvoteCount: data.upvoteCount
                            },
                            success: function(comment) {
                                onSuccess(formatComment(comment, users));
                            },
                            error: function(xhr){
                                onError();
                            }
                        });
                    }else{
                        $.ajax({
                            url: '/o/rest/v2/comments/' + data.commentId + '/upvotes',
                            type: 'DELETE',
                            data: {
                                commentId: data.commentId,
                                className: data.className,
                                classPK: data.classPK,
                                upvoteCount: data.upvoteCount
                            },
                            async: false,
                            dataType: 'json',
                            success: function(comment) {
                                onSuccess(formatComment(comment, users));
                                
                            },
                            error: function(xhr){
                                onError();
                            }
                        });
                    }
    
                },
                uploadAttachments: function(comments, onSuccess, onError) {
                    var responses = 0;
                    var successfulUploads = [];
    
                    var serverResponded = function() {
                        responses++;
    
                        if(responses == comments.length) {
                            
                            if(successfulUploads.length == 0) {
                                onError();
    
                            } else {
                                onSuccess(successfulUploads)
                            }
                        }
                    }
    
                    $(comments).each(function(index, comment) {
                        var formData = new FormData();
                        
                        $(Object.keys(comment)).each(function(index, key) {
                            var value = comment[key];
                            if(value) formData.append(key, value);
                        });
                        
                        formData.append('className', 'org.opencps.dossiermgt.model.Dossier');
                        
                        formData.append('classPK', dossierId);
                        
                        formData.append('parent', comment.parent != null ? comment.parent : 0);
                        
                        formData.append('fileName', comment.file.name);
                        
                        formData.append('fileType', comment.file.type);
                        
                        formData.append('fileSize', comment.file.size);
                        
                        formData.append('pings', comment.pings.join());
                        
                        formData.append('email', themeDisplay.getUserId());
                        
                        formData.append('fullname', themeDisplay.getUserName());
    
                        $.ajax({
                            url: '/o/rest/v2/comments/uploads',
                            dataType: 'json',
                            type: 'POST',
                            headers: {
                                "groupId": groupId
                            },
                            data: formData,
                            cache: false,
                            contentType: false,
                            processData: false,
                            success: function(comment) {
                                comment = formatComment(comment, users);
                                successfulUploads.push(comment);
                                serverResponded();                      
                            },
                            error: function(xhr, data) {
                                serverResponded();
                            }
                        });
                    });
                }
            });
        }
		
		function formatComment(comment, users){
			
			if(comment.parent == 0){
				comment.parent = null;
			}
			
			if(comment.fileName == ""){
				comment.fileName = null;
			}
			
			if(comment.fileType == ""){
				comment.fileType = null;
			}
			
			if(comment.fileUrl == ""){
				comment.fileUrl = null;
			}
			
			if(comment.pictureUrl ==""){
			    comment.pictureUrl = null;
			}
			
			if(comment.profileUrl ==""){
                comment.profileUrl = null;
            }
            
            if(comment.pings ==""){
                comment.pings = null;
            }else{
                var pings = comment.pings.toString();
                var arrPings = pings.split(",");
                
               
                $(arrPings).each(function(index, id) {
                    
                    $(users).each(function(i, user) {
                       
                         if(id == user.id){
                             comment.content = comment.content.replace('@' + id, '@' + users[i].fullname);
                             return false; 
                        }
                        
                    });
                    
                });
                
            }
			
			var createdDate = new Date(comment.createDate);
			
			var createdDateText = "2017-12-12 12:12";
			
			var modifiedDate = new Date(comment.modifiedDate);
			
			var modifiedDateText = "2017-12-12 12:12";
			
			comment.createdDate = createdDateText;
			
			comment.modifiedDate = modifiedDateText;
			
			return comment;
		}

</script>
