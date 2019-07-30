/*$.fn.extend({
	hlSelect: function(data) {
		var _self = {};
		_self.el = $(this);
		_self.data = data || [];
		_self.setList = function() {
			var htmlStr = '<ul class="mui-scroll">';
			$.each(_self.data, function(index, item) {
				htmlStr += '<li class="mui-scroll">' + item + '</li>';
			});
			htmlStr += '</ul>';
			_self.el.append(htmlStr);
		};
		_self.setList();

		_self.ListEl = $(this).children("ul");
		_self.el.click(function() {
			if(_self.ListEl.is(':hidden')) {
				_self.ListEl.show();
			} else {
				_self.ListEl.hide();
			}
		});
		_self.ListEl.on("click", ">li", function() {
			_self.el.children("div").html($(this).html());
		});
	}
});*/

$(function() {
	$("input[name='checkboxName']:checkbox").each(function() {
		$(this).click(function() {
			if($(this).prop('checked')) {
				$('input[name="checkboxName"]').prop('checked', false);
				$(this).prop('checked', true);
			}
		});
	});
});
$(function() {
	$("input[name='checkboxName_1']:checkbox").each(function() {
		$(this).click(function() {
			if($(this).prop('checked')) {
				$('input[name="checkboxName_1"]').prop('checked', false);
				$(this).prop('checked', true);
			}
		});
	});
});