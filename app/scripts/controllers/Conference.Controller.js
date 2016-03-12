(function () {
    'use strict';

    angular.module('speechRank').controller('ConferenceController', function( $scope, $stateParams, ConferenceService, toastr ) {
        console.log('ConferenceController loaded..');

        var conferenceId = $stateParams.id;

        $scope.getConference = function(conferenceId) {
            ConferenceService.getConference(conferenceId)
                .then(function(response) {
                    console.log('response', response);
                    $scope.conference = response.data;
                }).catch(function(error) {
                    toastr.error(error.data, 'Server Error!');
                });
        };

	    $scope.addComment = function(presentationId, comment) {
		    if(comment !== '') {
			    $scope.pushCommentToModel(presentationId, comment);
			    ConferenceService.addComment(presentationId, $scope.user.userId, comment, $scope.user.username)
				    .then(function(response) {
					    console.log('response', response);
				    }).catch(function(error) {
					   toastr.error(error.data, 'Server Error!');
				    });
		    } else {
			    console.log('no comment provided!');
		    }
	    };

	    $scope.pushCommentToModel = function(presentationId, comment) {
		    var presentations = $scope.conference.presentations;
		    for(var i = 0, len = presentations.length; i < len; i++) {
			    if(presentationId === presentations[i].id) {
				    $scope.conference.presentations[i].comments.unshift({
					    presentationId: presentationId,
					    userId: $scope.user.userId,
					    username: $scope.user.username,
					    comment: comment
				    });
				    $scope.conference.presentations[i].comment = '';
			    }
		    }
	    };

	    $scope.addRating = function(presentationId, rating) {
		    ConferenceService.addRating(presentationId, $scope.user.userId, rating)
			    .then(function(response) {
				    console.log('response', response);
			    }).catch(function(error) {
				    toastr.error(error.data, 'Server Error!');
			    });
	    };

        $scope.getConference(conferenceId);
    });
})();
