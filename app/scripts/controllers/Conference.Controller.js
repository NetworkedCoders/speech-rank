(function () {
    'use strict';

    angular.module('speechRank').controller('ConferenceController', function( $scope, $stateParams, ConferenceService ) {
        console.log('ConferenceController loaded..');

        var conferenceId = $stateParams.id;
	    $scope.form = {
		    comment: '',
		    rating: null
	    };

        $scope.getConference = function(conferenceId) {
            ConferenceService.getConference(conferenceId)
                .then(function(response) {
                    console.log('response', response);
                    $scope.conference = response.data;
                }).catch(function(error) {
                    console.error('error', error);
                });
        };

	    $scope.addComment = function(presentationId, userId) {
		    console.log('scope.comment', $scope.form.comment);
		    if($scope.form.comment !== '') {
			    ConferenceService.addComment($scope.form.comment, presentationId, userId)
				    .then(function(response) {
					    console.log('response', response);
				    }).catch(function(error) {
					    console.error('error', error);
				    });
		    } else {
			    console.log('no comment provided!');
		    }

	    };

	    $scope.addRating = function(presentationId) {
		    if($scope.form.rating !== null) {
			    ConferenceService.addRating($scope.form.rating, presentationId)
				    .then(function(response) {
					    console.log('response', response);
				    }).catch(function(error) {
					    console.error('error', error);
				    });
		    } else {
			    console.log('no rating provided!');
		    }
	    };

        $scope.getConference(conferenceId);
    });
})();
