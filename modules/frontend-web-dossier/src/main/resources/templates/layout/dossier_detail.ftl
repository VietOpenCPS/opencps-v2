<!-- TODO detailTemplate page -->
<div v-if="detailPage" style="width: 100%;">
	<div class="row-header">
		<div class="background-triangle-big"> Tên thủ tục </div>
		<div class="layout row wrap header_tools row-blue">
	
			<div class="flex xs9 solo pl-4">
		
				{{detailModel.dossierNo}}
		
			</div>
			<div class="flex xs3 text-right">
		
				<v-btn flat class=" my-0 py-0 btn-border-left" color="grey darken-1" v-on:click.native="detailPage = !detailPage">
					<v-icon class="mr-2">undo</v-icon>
				Quay lại
				</v-btn>
		
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
				{{ detailModel.govAgencyName }} 
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
				{{ detailModel.dossierId }}
			</div>
			<div class="pb-1">
				<span class="text-bold">
				Chuyển bởi: 
				</span>
				{{detailModel.lastActionName}}
			</div>
			<div>
				<v-icon color="red darken-2">feedback</v-icon> {{detailModel.lastActionNote}}
			</div>
			</v-flex>
			<v-flex xs12 sm6>
				<div class="pb-1">
					<span class="text-bold">
					Thời gian gửi: 
					</span>
					{{ detailModel.submitDate | date }}
				</div>
				<div class="pb-1">
					<span class="text-bold">
					Thời gian tiếp nhận: 
					</span>
					{{ detailModel.receiveDate | date }}
				</div>
				<div class="pb-1">
					<span class="text-bold">
					Thời hạn xử lý: 
					</span>
					{{ detailModel.dueDate | date }}
				</div>
				<div class="pb-1">
					<span class="text-bold">
					Trạng thái: 
					</span>
					{{detailModel.dossierStatusText}}
				</div>
				<div>
					<a href="javascript:;" @click="showContactDetail = !showContactDetail">
					Thông tin liên hệ: <v-icon color="primary">chevron_right</v-icon>
					</a>
					
					<v-slide-y-transition>
						<div v-if="showContactDetail">
							<div class="pb-1">
								<span class="text-bold">
								contactName: 
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
	
		<div class="text-bold primary--text expansion-panel__header">Thông tin sản phẩm</div>
		<v-layout wrap class="px-4 pb-2">
			<v-flex xs12 sm6>
			<div class="pb-1">
				{{detailModel.briefNote}}
			</div>
			</v-flex>
		</v-layout>
		</v-expansion-panel-content>
	</v-expansion-panel>

	<v-tabs :scrollable="false">
		<v-tabs-bar class="grey-opencps-panel" dark>
			<v-tabs-item :href="'#tab-dossier-detail-1'">
			THÀNH PHẦN HỒ SƠ
			</v-tabs-item>
			<v-tabs-item :href="'#tab-dossier-detail-2'" @click.prevent.stop="_initchangeProcessStep()">
			THỤ LÝ HỒ SƠ
			</v-tabs-item>
			<v-tabs-item :href="'#tab-dossier-detail-3'" @click.prevent.stop="selectDossierActionTab(detailModel)">
			TIẾN TRÌNH XỬ LÝ
			</v-tabs-item>
			<v-tabs-item :href="'#tab-dossier-detail-4'">
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
				<v-tabs :scrollable="false">
					<v-tabs-bar dark class="grey-opencps-panel">
					<v-tabs-slider color="primary"></v-tabs-slider>
					<v-tabs-item
						v-for="step in processSteps"
						:key="step.processActionId"
						:href="'#tab-temp-' + step.processActionId"
						@click.prevent.stop="changeProcessStep(step)"
					>
						{{ step.actionName }}
					</v-tabs-item>
					<v-menu>
					</v-menu>
					</v-tabs-bar>
				</v-tabs>
				<v-card v-if="stepModel.actionName != null">
					<v-card-title primary-title class="mx-2">
					<v-layout wrap> 
					<v-flex xs12>
						Nhập ý kiến {{stepModel.actionName}}:
						<div jx-bind="processActionNote"></div>
					</v-flex>
					<v-flex xs12 v-if="stepModel.allowAssignUser">
						<div jx-bind="processAssignUserId"></div>
					</v-flex>
					</v-layout>
					
					
					
					</v-card-title>
					<v-card-actions>
					<v-btn flat color="primary" class="px-0">Xác nhận</v-btn>
					</v-card-actions>
				</v-card>
			 
			</v-tabs-content>
			<v-tabs-content :id="'tab-dossier-detail-3'" reverse-transition="slide-y-transition" transition="slide-y-transition">
			<div class="opencps_list_border_style" jx-bind="listHistoryProcessing"></div>
			</v-tabs-content>
			<v-tabs-content :id="'tab-dossier-detail-4'" reverse-transition="slide-y-transition" transition="slide-y-transition">
			<div id="comments-container" class="pt-3 px-4" style="background: white;"></div>
			<script type="text/javascript">
				
				$(function() {
	
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
					fullname: 'fullName',
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
	
					getUsers: function(success, error) {
					var usersArray = [
					{
						id: 1,
						fullname: "Current User",
						email: "current.user@viima.com",
						profile_picture_url: "https://app.viima.com/static/media/user_profiles/user-icon.png"
					},
					{
						id: 2,
						fullname: "Jack Hemsworth",
						email: "jack.hemsworth@viima.com",
						profile_picture_url: "https://app.viima.com/static/media/user_profiles/user-icon.png"
					},
					{
						id: 3,
						fullname: "Hank Smith",
						email: "hank.smith@viima.com",
						profile_picture_url: "https://app.viima.com/static/media/user_profiles/user-icon.png"
					},
					{
						id: 4,
						fullname: "Todd Brown",
						email: "todd.brown@viima.com",
						profile_picture_url: "https://app.viima.com/static/media/user_profiles/user-icon.png"
					},
					{
						id: 5,
						fullname: "Administrator",
						email: "administrator@viima.com",
						profile_picture_url: "https://app.viima.com/static/media/user_profiles/user-icon.png"
					},
					{
						id: 6,
						fullname: "Simon Powell",
						email: "simon.powell@viima.com",
						profile_picture_url: "https://app.viima.com/static/media/user_profiles/user-icon.png"
					},
					{
						id: 7,
						fullname: "Bryan Connery",
						email: "bryan.connery@viima.com",
						profile_picture_url: "https://app.viima.com/static/media/user_profiles/user-icon.png"
					}
					]
					success(usersArray);
					},
					getComments: function(success, error) {
					var commentsArray = [{
						id: 1,
						"created": "2015-01-10",
						"modified": "2015-01-10",
						"content": "Quisque ligula eros ullamcorper quis, lacinia quis facilisis sed sapien. Mauris varius diam vitae arcu. Sed arcu lectus auctor vitae, consectetuer et venenatis eget velit.",
						"pings": [],
						"creator": 1,
						"fullname": "You",
						"profile_picture_url": "https://app.viima.com/static/media/user_profiles/user-icon.png",
						"created_by_admin": false,
						"created_by_current_user": true,
						"upvote_count": 0,
						"user_has_upvoted": false
					},
					{
						id: 2,
						"created": "2015-01-10",
						"modified": "2015-01-10",
						"content": "Quisque ligula eros ullamcorper quis, lacinia quis facilisis sed sapien. Mauris varius diam vitae arcu. Sed arcu lectus auctor vitae, consectetuer et venenatis eget velit.",
						"pings": [],
						"creator": 2,
						"fullname": "You",
						"profile_picture_url": "https://app.viima.com/static/media/user_profiles/user-icon.png",
						"created_by_admin": false,
						"created_by_current_user": true,
						"upvote_count": 0,
						"user_has_upvoted": false
					}];
					success(commentsArray);
					 
					},
					postComment: function(data, success, error) {
					// 
					},
					putComment: function(data, success, error) {
					
					},
					deleteComment: function(data, success, error) {
					setTimeout(function() {
						success();
					}, 500);
					},
					upvoteComment: function(data, success, error) {
					setTimeout(function() {
						success(data);
					}, 500);
					},
					uploadAttachments: function(dataArray, success, error) {
					setTimeout(function() {
						success(dataArray);
					}, 500);
					},
				});
	
				function formatComment(comment, users) {
					if (comment.parent == 0) {
					comment.parent = null;
					}
	
					if (comment.fileName == "") {
					comment.fileName = null;
					}
	
					if (comment.fileType == "") {
					comment.fileType = null;
					}
	
					if (comment.fileUrl == "") {
					comment.fileUrl = null;
					}
	
					if (comment.pictureUrl == "") {
					comment.pictureUrl = null;
					}
	
					if (comment.profileUrl == "") {
					comment.profileUrl = null;
					}
	
					if (comment.pings == "") {
					comment.pings = null;
					} else {
					var pings = comment.pings.toString();
					var arrPings = pings.split(",");
	
	
					$(arrPings).each(function (index, id) {
	
						$(users).each(function (i, user) {
	
						if (id == user.id) {
							comment.content = comment.content.replace('@' + id, '@' + users[i].fullname);
							return false;
						}
						});
					});
					}
	
					var createdDate = new Date(comment.createDate);
	
					var createdDateText = formatDate(createdDate, 'yyyy-MM-dd HH:mm');
	
					var modifiedDate = new Date(comment.modifiedDate);
	
					var modifiedDateText = formatDate(modifiedDate, 'yyyy-MM-dd HH:mm');
	
					comment.createdDate = createdDateText;
	
					comment.modifiedDate = modifiedDateText;
	
					return comment;
				}
				
				});
		
			</script>
			</v-tabs-content>
		</v-tabs-items>
	</v-tabs>
</div>