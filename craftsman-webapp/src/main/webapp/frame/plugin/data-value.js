$(
		function(){
			$('select[data-value]').each(function(){
				$(this).val($(this).attr("data-value"));
			})
		}
);