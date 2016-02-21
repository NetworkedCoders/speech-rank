(function () {
    'use strict';

    angular.module('speechRank').controller('ConferenceController', function( $scope, $stateParams, ConferenceService ) {
        console.log('ConferenceController loaded..');

        var conferenceId = $stateParams.id;
	    $scope.form = {
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

	    $scope.addComment = function(presentationId, userId, comment) {
		    if(comment !== '') {
			    $scope.pushCommentToModel(presentationId, userId, comment, $scope.user.username);
			    ConferenceService.addComment(presentationId, userId, comment, $scope.user.username)
				    .then(function(response) {
					    console.log('response', response);
				    }).catch(function(error) {
					    console.error('error', error);
				    });
		    } else {
			    console.log('no comment provided!');
		    }
	    };

	    $scope.pushCommentToModel = function(presentationId, userId, comment) {
		    var presentations = $scope.conference.presentations;
		    for(var i = 0, len = presentations.length; i < len; i++) {
			    if(presentationId === presentations[i].id) {
				    $scope.conference.presentations[i].comments.unshift({
					    presentationId: presentationId,
					    userId: userId,
					    username: $scope.user.username,
					    comment: comment
				    });
				    $scope.conference.presentations[i].comment = '';
			    }
		    }
	    };

	    $scope.addRating = function(presentationId) {
		    if($scope.form.rating !== null) {
			    ConferenceService.addRating(presentationId, $scope.form.rating)
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