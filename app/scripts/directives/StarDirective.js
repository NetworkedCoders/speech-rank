(function () {
	'use strict';

	angular.module('speechRank').directive('starDirective', function() {
		return {
			restrict: 'A',
			link: function(scope, element) {
				$(element).on('mouseover', '.icon-star-empty', function() {
					var $star = $(this);
					console.log('star', $star);
				});
			}
		};
	});
})();