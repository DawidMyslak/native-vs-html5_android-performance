Ext.define('CurrencyRates.model.CurrencyModel', {
    extend: 'Ext.data.Model',
	xtype: 'currencymodel',
	
    config: {
        fields: [
            { name: 'name', type: 'string' },
            { name: 'code', type: 'string' },
            { name: 'rate', type: 'string' },
            { name: 'date', type: 'string' }
        ]
    }
});