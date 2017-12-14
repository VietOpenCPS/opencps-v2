document.addEventListener('DOMContentLoaded', function (event) {
	var users = [];

	const config = {
		headers: {
			'groupId': themeDisplay.getScopeGroupId()
		},
		data: {
			userMapping: true
		}
	};

	var url = "/o/frontendwebpayment/json/payment_files.json";

	axios.get(url, config).then(function (result) {
		if (result != null && result.hasOwnProperty('data')) {
			var contacts = result.data;

			$.each(contacts, function (index, contact) {
				var user = {};
				user.id = contact.userMappingId;
				user.fullname = contact.fullName;
				user.email = contact.email;
				user.profilePictureURL = contact.profileUrl
				users.push(user);
			});

		} else {
			users = [
				{
				  "contactId": 0,
				  "userId": 0,
				  "userName": "",
				  "createDate": "2017-09-08T08:29:10.332Z",
				  "modifiedDate": "2017-09-08T08:29:10.332Z",
				  "contactType": 0,
				  "myContact": false,
				  "fullname": "",
				  "companyName": "",
				  "telNo": "",
				  "email": "",
				  "shared": false,
				  "userMappingId": 0,
				  "groupList": [
					{
					  "contactId": 0,
					  "fullname": "",
					  "companyName": "",
					  "telNo": "",
					  "email": "",
					  "userMappingId": 0
					}
				  ]
				}
			  ];
		}
		console.log(users);
		renderComment(users);

	})
		.catch(function (error) {
			console.log(error);
			users = [];
			renderComment(users);
		});

	function renderComment(users) {
		$('#docfile-detail-comment-container').comments({
			
		});
	}

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