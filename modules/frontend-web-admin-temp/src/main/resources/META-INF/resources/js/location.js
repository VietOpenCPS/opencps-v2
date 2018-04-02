
function initMap() {
	
	var locLatLng = {
			lat: parseFloat($("#_locationSub_code_lat").val()), 
			lng: parseFloat($("#_locationSub_code_lng").val())
		};
	
	var map = new google.maps.Map(document.getElementById('_locationSub_map'), {
	
		center: locLatLng,
		zoom: 13
		
	});
	
	var input = document.getElementById('_locationSub_name');
	
	var autocomplete = new google.maps.places.Autocomplete(input);
	
	autocomplete.bindTo('bounds', map);
	
	var marker = new google.maps.Marker({
		map: map,
		position: locLatLng,
		anchorPoint: new google.maps.Point(0, -29)
	});
	
	autocomplete.addListener('place_changed', function() {
	
		marker.setVisible(false);
		var place = autocomplete.getPlace();
		
		if (!place.geometry) {
			
			window.alert("No details available for input: '" + place.name + "'");
			return;
			
		}
	
		if (place.geometry.viewport) {
			
			map.fitBounds(place.geometry.viewport);
			
		} else {
			
			map.setCenter(place.geometry.location);
			map.setZoom(17);
			
		}
		
		var latitude = place.geometry.location.lat();
		
		var lontiude =place.geometry.location.lng();  
		
		console.log("lat: "+latitude + ", lng: " + lontiude);
		
		$("#_locationSub_code").val(latitude + "," + lontiude);
		
		marker.setPosition(place.geometry.location);
		marker.setVisible(true);
	
	});

}

