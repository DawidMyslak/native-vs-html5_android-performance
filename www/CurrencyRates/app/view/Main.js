Ext.define('CurrencyRates.view.Main', {
    extend: 'Ext.Container',
    xtype: 'main',

    config: {
		fullscreen: true,
		layout: 'card',
		
		items: [
			{
				xtype: 'currencylist'
			},
			{
				xtype: 'currencydetails'
			}
		]
    }
});
