Ext.define('CurrencyRates.store.CurrencyStore', {
    extend: 'Ext.data.Store',
	xtype: 'currencystore',
	
	requires: [
		'CurrencyRates.model.CurrencyModel'
    ],
	
    config: {
        storeId: 'currencyStore',
        model: 'CurrencyRates.model.CurrencyModel'
    }
});
