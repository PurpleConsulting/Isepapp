var alert_re = new RegExp(/\^alert-/i);


$(document).ready(function(){
	block = $("div.alert_input");
	input = $("input").first();
	btn = $('button').first();
	btn.attr('disabled', 'true');
	
	
	input.blur(function(){
		$.post("/Isepapp/Signin", {Ajaxpseudo :$(this).val()}, function(data, status){
			var result = (data.result.find == "true") ? true : false; 
			if (result){
				input.parent().addClass('has-success');
				input.parent().removeClass('has-error');
				block.alterClass('alert-*', 'alert-success');
				block.html('<p><strong>Bienvenue</strong>, ' + input.val() + '. </p><span style="position:absolute;top:10px;right:10px;" class="fa fa-check-square fa-1x"></span>')
				block.css('display', 'block');
				btn.removeAttr('disabled');
			} else { 
				btn.attr('disabled', 'true');
				input.parent().addClass('has-error');
				input.parent().removeClass('has-success');
				block.alterClass('alert-*', 'alert-danger');
				block.html('<p>Votre identifiant est introuvable.</p>')
				block.css('display', 'inline-block');
				block.css('margin-bottom', '0px');
			}
			
		})
	});
});


/*** Fonction utils ***/
(function ( $ ) {
	$.fn.alterClass = function ( removals, additions ) {
		var self = this;
		if ( removals.indexOf( '*' ) === -1 ) {
			self.removeClass( removals );
			return !additions ? self : self.addClass( additions );
		}
		var patt = new RegExp( '\\s' + 
				removals.
					replace( /\*/g, '[A-Za-z0-9-_]+' ).
					split( ' ' ).
					join( '\\s|\\s' ) + 
				'\\s', 'g' );
		self.each( function ( i, it ) {
			var cn = ' ' + it.className + ' ';
			while ( patt.test( cn ) ) {
				cn = cn.replace( patt, ' ' );
			}
			it.className = $.trim( cn );
		});
		return !additions ? self : self.addClass( additions );
	};
	})( jQuery );