$.extend($.fn.validatebox.defaults.rules, {
    minLength: {
		validator: function(value, param){
			return value.length >= param[0];
		},
		message: 'Please enter at least {0} characters.'
    }
});