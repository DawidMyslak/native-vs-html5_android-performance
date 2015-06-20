Ext.define('CurrencyRates.controller.MainController', {
    extend: 'Ext.app.Controller',
	xtype: 'maincontroller',
	
	requires: [
		'Ext.util.JSONP'
    ],
	
    config: {
        refs: {
			main: {
				xtype: 'main',
				selector: 'main',
				autoCreate: true
			},
            currencyList: {
				xtype: 'currencylist',
				selector: 'currencylist',
				autoCreate: true
			},
			currencyDetails: {
				xtype: 'currencydetails',
				selector: 'currencydetails',
				autoCreate: true
			},
			backButton: 'currencydetails button#backButton',
			sourceField: 'currencydetails textfield#sourceField',
			dateField: 'currencydetails textfield#dateField',
			nameField: 'currencydetails textfield#nameField',
			codeField: 'currencydetails textfield#codeField',
			rateField: 'currencydetails textfield#rateField'
        },
        control: {
            backButton: {
				tap: 'backToList'
			}
        }
    },
    
    launch: function(app) {
		Ext.Viewport.setMasked({
			xtype: 'loadmask',
			message: 'Trwa ładowanie...'
		});
		
		var startDate = new Date();
		
		Ext.util.JSONP.request({
			url: 'http://dejvo.com/currency',
			callbackKey: 'callback',
			params: {
				key: 'c9398e6d05fbaee28322ab8a01c810f8'
			},
			success: function(result) {
				var endDate = new Date();
				
				Ext.getStore('currencyStore').setData(result);
				Ext.Viewport.setMasked(false);
				
				Ext.Msg.alert('Alert', endDate - startDate);
			}
		});
    },
	
	showDetails: function(id) {
		var currencyDetails = Ext.getStore('currencyStore').getAt(id);
		
		this.getSourceField().setValue('Narodowy Bank Polski');
		this.getDateField().setValue(currencyDetails.data.date);
		this.getNameField().setValue(currencyDetails.data.name);
		this.getCodeField().setValue(currencyDetails.data.code);
		this.getRateField().setValue(currencyDetails.data.rate);
	
		this.getMain().animateActiveItem(this.getCurrencyDetails(), { type: 'slide', direction: 'left' });
	},
	
	backToList: function() {
		this.getMain().animateActiveItem(this.getCurrencyList(), { type: 'slide', direction: 'right' });
	}
});
