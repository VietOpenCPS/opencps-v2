function namespace(length) {
	 var chars = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_';
    var result = '';
    for (var i = length; i > 0; --i) result += chars[Math.floor(Math.random() * chars.length)];
    return result;
}

function loadDatasource(url, type) {
	var dataSource = new kendo.data.DataSource({
			transport: {
         	read: {
          		url: url,
               dataType: type
            }
    		},
    });

	dataSource.read();
	
	return dataSource;
}

