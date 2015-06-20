Ext.define('CurrencyRates.view.CurrencyList', {
    extend: 'Ext.Container',
    xtype: 'currencylist',
	
	requires: [
		'CurrencyRates.store.CurrencyStore',
        'Ext.dataview.List'
    ],

    config: {
		layout: 'fit',
		
		items: [
			{
				docked: 'top',
				xtype: 'titlebar',
				title: 'Kursy walut'
			},
			{
				xtype: 'list',
				itemTpl: '{name}',
				store: 'currencyStore',
				listeners: {
					itemtap: function(list, index, target, record, event) {
						CurrencyRates.app.getController('MainController').showDetails(index);
					}
				}
			}
		]
    }
});
