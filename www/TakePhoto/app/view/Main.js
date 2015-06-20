Ext.define('PhotoShare.view.Main', {
	extend: 'Ext.Container',
	xtype: 'main',

	requires: [
		'Ext.TitleBar',
		'Ext.Img'
	],

	config: {
		items: [
			{
				xtype: 'titlebar',
				docked: 'top',
				title: 'Zrób zdjęcie'
			},
			{
				itemId: 'photoButton',
				xtype: 'button',
				text: 'Uruchom kamerę',
				margin: '10',
				iconMask: true,
				ui: 'confirm'
			},
			{
				itemId: 'photoContainer',
				xtype: 'container',
				layout: 'fit',
				margin: '10',
				items: [
					{
						itemId: 'photo',
						xtype: 'image',
						height: 160
					}
				]
			}
		]
	}
});
