Ext.define('PhotoShare.controller.MainController', {
	extend: 'Ext.app.Controller',

	config: {
		refs: {
			photoButton: 'button#photoButton',
			photo: 'image#photo',
			photoContainer: 'container#photoContainer'
		},
		
		control: {
			photoButton: {
				tap: 'capturePhoto'
			}
		}
	},

	capturePhoto: function() {
		var self = this,
			photo = self.getPhoto();

		navigator.camera.getPicture(
			onPhotoDataSuccess,
			onFail, {
				quality: 70,
				destinationType: navigator.camera.DestinationType.DATA_URL
			}
		);

		function onPhotoDataSuccess(imageData) {
			var imageSrc = 'data:image/jpeg;base64,' . concat(imageData);
			photo.setSrc(imageSrc);
		}

		function onFail(message) {
			alert(message);
		}
	}
});
