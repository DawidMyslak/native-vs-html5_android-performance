Ext.define('CurrencyRates.view.CurrencyDetails', {
    extend: 'Ext.Container',
    xtype: 'currencydetails',
	
	requires: [
        'Ext.form.FieldSet'
    ],

    config: {
		scrollable: true,
	
		items: [
			{
				docked: 'top',
				xtype: 'titlebar',
				title: 'Szczegóły',
				items: [
					{
						itemId: 'backButton',
						xtype: 'button',
						ui: 'back',
						text: 'Powrót'
					}
				]
			},
			{
				xtype: 'fieldset',
				items: [
					{
						itemId: 'nameField',
						xtype: 'textfield',
						label: 'Pełna nazwa:',
						labelAlign: 'top',
						cls: 'detailText',
						readOnly: true
					},
					{
						itemId: 'codeField',
						xtype: 'textfield',
						label: 'Kod:',
						labelAlign: 'top',
						cls: 'detailText',
						readOnly: true
					},
					{
						itemId: 'rateField',
						xtype: 'textfield',
						label: 'Kurs:',
						labelAlign: 'top',
						cls: 'detailText',
						readOnly: true
					},
					{
						itemId: 'sourceField',
						xtype: 'textfield',
						label: 'Źródło danych:',
						labelAlign: 'top',
						cls: 'detailText',
						readOnly: true
                
					},
					{
						itemId: 'dateField',
						xtype: 'textfield',
						label: 'Data aktualizacji:',
						labelAlign: 'top',
						cls: 'detailText',
						readOnly: true
					}
				]
			}
		]
    }
});
