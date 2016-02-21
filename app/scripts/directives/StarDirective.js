(function () {
	'use strict';

	angular.module('speechRank').directive('starDirective', function() {
		return {
			restrict: 'A',
			link: function(scope, element) {
				$(element).on('mouseover', '.icon-star-empty', function() {
					var $star = $(this);
					$star.prevAll().andSelf().removeClass('icon-star-empty').addClass('icon-star');
				}).on('mouseout', function() {
					var $wrapper = $(this);
					$wrapper.find('.icon-star').removeClass('icon-star').addClass('icon-star-empty');
				});
			}
		};
	});
})();
