$(document).ready(function(){
	     var users = [];
	     
		 $.ajax({
            url: '${api.endpoint}/contacts',
            type: 'GET',
            headers: {
                "groupId": '${groupId}'
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
                        user.id = contact.userMappingId;
                        user.fullname = contact.fullName;
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
		
		function renderComment(users){
            $('#docfile-detail-comment-container').comments({
                profilePictureURL: '/o/frontend-web-document/images/user_01.png',
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
                        url: '${api.endpoint}/comments/${constants.docFile_className}/${docFile.docFileId}',
                        type: 'GET',
                        headers: {
                            "groupId": '${groupId}'
                        },
                        async: false,
                        dataType: 'json',
                        data: {
                            
                        },
                        success: function(comments) {
                         
                            var data = [];
                            
                            $.each(comments.data, function(index, comment){
                                data.push(formatComment(comment, users));
                            });
    
                            onSuccess(data);
                        },
                        error: function(xhr){
                            onSuccess([]);
                            onError();
                            showMessageByAPICode(xhr.status);
                        }
                    });
                },
                postComment: function(data, onSuccess, onError) {
                    var strPings = data.pings.join();
                    $.ajax({
                        url: '${api.endpoint}/comments',
                        type: 'POST',
                        headers: {
                            "groupId": '${groupId}'
                        },
                        async: false,
                        dataType: 'json',
                        data: {
                            className: '${constants.docFile_className}',
                            classPK: '${docFile.docFileId}',
                            parent: data.parent != null ? data.parent : 0,
                            pings: strPings,
                            content: data.content,
                            upvoteCount: data.upvoteCount
                        },
                        success: function(comment) {
                            if(comment != null){
                                onSuccess(formatComment(comment, users));
                                showMessageToastr("success", messages.notifications.${language_code}.default.success1);
                            }else{
                                onSuccess([]);
                            }
                            
                        },
                        error: function(xhr){
                            onError();
                            showMessageByAPICode(xhr.status);
                        }
                    });
                },
                putComment: function(data, onSuccess, onError) {
                    
                    $.ajax({
                        url: '${api.endpoint}/comments/' + data.commentId,
                        type: 'PUT',
                        headers: {
                            "groupId": '${groupId}'
                        },
                        async: false,
                        dataType: 'json',
                        data: {
                            className: '${constants.docFile_className}',
                            classPK: '${docFile.docFileId}',
                            parent: data.parent != null ? data.parent : 0,
                            pings: data.pings.join(),
                            content: data.content,
                            upvoteCount: data.upvoteCount
                        },
                        success: function(comment) {
                            onSuccess(formatComment(comment, users));
                            showMessageToastr("success", messages.notifications.${language_code}.default.success1);
                        },
                        error: function(xhr){
                            onError();
                            showMessageByAPICode(xhr.status);
                        }
                    });
                },
                deleteComment: function(data, onSuccess, onError) {
                    $.when(showWindowConfirm('#template-confirm','Cảnh báo','Bạn muốn xóa thảo luận ?', $('#docfile-detail-comment-container'))).then(function(confirmed){
                        if(confirmed){
                           $.ajax({
                                url: '${api.endpoint}/comments/' + data.commentId,
                                type: 'DELETE',
                                data: data,
                                async: false,
                                dataType: 'json',
                                success: function(comment) {
                                    onSuccess();
                                    showMessageToastr("success", messages.notifications.${language_code}.default.success1);
                                },
                                error: function(xhr){
                                    onError();
                                    showMessageByAPICode(xhr.status);
                                }
                            });
                        }
                        else{
                            onError();
                        }
                    });
                },
                upvoteComment: function(data, onSuccess, onError) {
                    
                    if(data.userHasUpvoted){
                        $.ajax({
                            url: '${api.endpoint}/comments/' + data.commentId + '/upvotes',
                            type: 'PUT',
                            headers: {
                                "groupId": '${groupId}'
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
                                showMessageToastr("success", messages.notifications.${language_code}.default.success1);
                            },
                            error: function(xhr){
                                onError();
                                showMessageByAPICode(xhr.status);
                            }
                        });
                    }else{
                        $.ajax({
                            url: '${api.endpoint}/comments/' + data.commentId + '/upvotes',
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
                                showMessageToastr("success", messages.notifications.${language_code}.default.success1);
                                
                            },
                            error: function(xhr){
                                onError();
                                showMessageByAPICode(xhr.status);
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
                        
                        formData.append('className', '${constants.docFile_className}');
                        
                        formData.append('classPK', '${docFile.docFileId}');
                        
                        formData.append('parent', comment.parent != null ? comment.parent : 0);
                        
                        formData.append('fileName', comment.file.name);
                        
                        formData.append('fileType', comment.file.type);
                        
                        formData.append('fileSize', comment.file.size);
                        
                        formData.append('pings', comment.pings.join());
                        
                        formData.append('email', '${(userLogged.email)!}');
                        
                        formData.append('fullName', '${(userLogged.fullName)!}');
    
                        $.ajax({
                            url: '${api.endpoint}/comments/uploads',
                            dataType: 'json',
                            type: 'POST',
                            headers: {
                                "groupId": '${groupId}'
                            },
                            data: formData,
                            cache: false,
                            contentType: false,
                            processData: false,
                            success: function(comment) {
                                comment = formatComment(comment, users);
                                successfulUploads.push(comment);
                                serverResponded();                      
                                showMessageToastr("success", messages.notifications.${language_code}.default.success1);
                            },
                            error: function(xhr, data) {
                                serverResponded();
                                showMessageByAPICode(xhr.status);
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
			
			var createdDateText = formatDate(createdDate, 'yyyy-MM-dd HH:mm');
			
			var modifiedDate = new Date(comment.modifiedDate);
			
			var modifiedDateText = formatDate(modifiedDate, 'yyyy-MM-dd HH:mm');
			
			comment.createdDate = createdDateText;
			
			comment.modifiedDate = modifiedDateText;
			
			return comment;
		}
	});