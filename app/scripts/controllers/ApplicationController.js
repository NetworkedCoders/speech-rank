(function () {
    'use strict';

    angular.module('speechRank').controller('ApplicationController', function( $scope, $location ) {
        console.log('ApplicationController loaded..');
	    $scope.user = {
		    username: 'Ola Kunysz',
		    userId: 123
	    };

	    $scope.isActive = function (viewLocation) {
			var active = (viewLocation === $location.path());
			return active;
		};
    });
})();
