//= require notify

$(document).ready(function(){
    $(".fa-pencil-square-o").click(function(){
        var topicNameNonEditableElement = $(this).parents('.row').find('.display-box');
        var topicNameEditableElements = $(this).parents('.row').find('.edit-box');
        topicNameNonEditableElement.removeClass('display-box');
        topicNameEditableElements.removeClass('edit-box');
        topicNameNonEditableElement.addClass('edit-box');
        topicNameEditableElements.addClass('display-box')
    });

    $('.save-topic').click(function(){
        var thisElement = $(this);
        var modifiedTopicName = $(this).parents('.row').find('.input-topic').val();
        if(!modifiedTopicName){
            alert('Please enter a valid topic name')
        }
        var topicId = $(this).parents('.row').find('.input-topic').attr('data-topic-id');

        var successCallback = function (response) {
                thisElement.parents('.row').find('.topic-name').html (modifiedTopicName);
                var editableElements = thisElement.parents('.row').find('.edit-box');
                var nonEditableElement = thisElement.parents('.row').find('.display-box');
                editableElements.removeClass('edit-box');
                nonEditableElement.removeClass('display-box');
                editableElements.addClass('display-box');
                nonEditableElement.addClass('edit-box');

            setTimeout(function(){
                location.reload(true)
            }, 2000);
                //$.notify(response,"info")

        };

        var errorCallBack = function (jqXHR, exception) {
            if(jqXHR.status == 500){
                $.notify("Invalid topic name or topic name already exist")
            }
        };

        $.ajax({
            url: "topic/update",
            data: {'topicId' : topicId,'topicName' : modifiedTopicName},
            success: successCallback,
            error : errorCallBack
        });
    });

    $('.cancel-topic').click(function(){
        var editableElements = $(this).parents('.row').find('.edit-box');
        var nonEditableElement = $(this).parents('.row').find('.display-box');
        editableElements.removeClass('edit-box');
        nonEditableElement.removeClass('display-box');
        editableElements.addClass('display-box');
        nonEditableElement.addClass('edit-box')
    });

    $('body').on('click','.send-invite-envelope',function(e){
        e.preventDefault();
        debugger;
        var topicNameElement = $(this).parents('.row').find('.topic-name');
        if(topicNameElement && topicNameElement.attr('value')){
            var topicNameVal = topicNameElement.attr('value');
            // Remove previously selected option
            $('#send-invitation-topic > option[selected=selected]').prop('selected',false);
            //$('#send-invitation-topic > option:eq('+topicNameVal+')').html(topicNameElement.html())
            $('#send-invitation-topic > option[value="'+topicNameVal+'"]').prop('selected', true);
            $('#send-invitation-topic').attr('disabled',true);
            $('#send-invitation-topic-hidden').val($(' #send-invitation-topic > option:eq('+topicNameVal+')').val());
            //$('#send-invitation-topic value="' + topicNameElement.text()+ '"]').attr('selected', true)
        }else{
            $('#send-invitation-topic').removeAttr('disabled')
        }
    });

    $('.fa-trash-o').click(function(e){
        e.preventDefault();
        debugger;
        var topicNameElement = $(this).parents('.row').find('.topic-name');

        var successCallback = function (response) {
            if(response){
                $.notify("Hold on.Deleting topic...!")
                setTimeout(function(){
                    //location.reload()
                    location.replace("user/index");
                }, 4000);
            }else{
                $.notify("Error while deleting topic.Please try after some time!")
            }
        }

        if(topicNameElement && topicNameElement.attr('value')){
            var topicNameVal = topicNameElement.attr('value');
            debugger;
            $.ajax({
                url: "topic/deleteTopic",
                data: {'topicId' : topicNameVal},
                success: successCallback
            })
        }
    });

    $('body').on('click','.unsubscribed',function(e){
        debugger;
        var unsubscribedElement = $(this);
        var topicElement = $(this).parents('.row').find('.topic-name');
        if(topicElement && topicElement.attr('value')) {
            var topicId = topicElement.attr('value');
            var topicName = topicElement.html();
            // ajax request to subscribe to above mentioned topic id
            var successCallback = function(response){
                debugger;
                unsubscribedElement.removeClass('unsubscribed');
                unsubscribedElement.addClass('subscribed');
                unsubscribedElement.children('span').html("Subscribe");
                var seriousnessList = unsubscribedElement.parents('.row').find('.seriousness-list');
                //seriousnessList.addClass('edit-box')
                seriousnessList.css('display','none');
                //seriousnessList.removeClass('display-box')
                $.notify("Unsubscribed to " + topicName,"success")
                setTimeout(function(){
                    location.reload()
                }, 2000);

            };
            $.ajax({
                url: "subscription/updateSubscription",
                data: {'topicId' : topicId,'isSubscription' : false},
                success: successCallback
            });
        }
        e.preventDefault()
    });

    $('body').on('click','.subscribed',function(e){
        debugger;
        var subscribedElement = $(this);
        var topicElement = $(this).parents('.row').find('.topic-name');
        if(topicElement && topicElement.attr('value')) {
            var topicId = topicElement.attr('value');
            var topicName = topicElement.html();
            // ajax request to subscribe to above mentioned topic id
            var successCallback = function(response){
                debugger;
                subscribedElement.removeClass('subscribed');
                subscribedElement.addClass('unsubscribed');
                subscribedElement.children('span').html("Unsubscribe");
                var seriousnessList = subscribedElement.parents('.row').find('.seriousness-list');
                seriousnessList.css('display','block');
                /*seriousnessList.addClass('display-box')
                seriousnessList.removeClass('edit-box')*/
                $.notify("Subscribed to " + topicName,"success")
                setTimeout(function(){
                    location.reload(true)
                }, 2000);

            };
            $.ajax({
                url: "subscription/updateSubscription",
                data: {'topicId' : topicId,'isSubscription' : true},
                success: successCallback
            });
        }
        e.preventDefault()
    });

    $('body').on('change','.seriousness-list',function(){
        debugger;
        var seriousnessSelectElement = $(this);
        var changeSeriousnessVal = seriousnessSelectElement.val();
        var topicElement = $(this).parents('.row').find('.topic-name');
        if(topicElement && topicElement.attr('value')) {
            var topicId = topicElement.attr('value');
            // ajax request to subscribe to above mentioned topic id
            var successCallback = function(response){
                location.reload(true)
            };
            $.ajax({
                url: "subscription/updateSubscriptionSeriousness",
                data: {'topicId' : topicId,'seriousnessVal' : changeSeriousnessVal},
                success: successCallback
            });
        }
    });

    $('body').on('change','.visibility-list',function(){
        debugger;
        var visibilitySelectElement = $(this);
        var changeVisibilityVal = visibilitySelectElement.val();
        var topicElement = $(this).parents('.row').find('.topic-name');
        if(topicElement && topicElement.attr('value')) {
            var topicId = topicElement.attr('value');
            // ajax request to subscribe to above mentioned topic id
            var successCallback = function(response){
                debugger;
                location.reload(true)
            };

            var errorCallBack = function (jqXHR, exception) {
                debugger;
                if(jqXHR.status == 500){
                    $.notify("Error while updating visibility. Please try after some time!!")
                }
            };

            $.ajax({
                url: "topic/updateVisibility",
                data: {'topicId' : topicId,'visibility' : changeVisibilityVal},
                success: successCallback,
                error : errorCallBack
            });
        }
    });


    $("#share-document-data").submit(function() {
        debugger;
        var formData = new FormData($(this)[0]);


        $.ajax({
            url: "resource/saveDocRes",
            type : 'POST',
            data: formData,
            success: successCallback
        });

        return false;
    });

    var loginForm = $("#login-form");
    loginForm.validate({
        errorElement: "div",
        submitHandler: function(form) {
            debugger;
            $('#submit').attr('disabled','disabled');
            $(form).ajaxSubmit(function (response) {
                debugger;
                if(response){
                    location.href = "user/index"
                }else{
                    var loginError = $('#login-error')
                    loginError.html("** Invalid combination of username/password combination")
                    loginError.removeClass('edit-box')
                    loginError.removeClass('display-box')

                }
            });
        }
    });


    var inviteForm = $("#send-invite-form");
    inviteForm.validate({
        errorElement: "div",
        submitHandler: function(form) {
            debugger;
            $('#submit').attr('disabled','disabled');
            $('#send-invitation').modal('toggle');
            $(form).ajaxSubmit(function (response) {
                debugger;
                if(response){
                    $.notify("Invite sent successfully","success")
                }else{
                    $.notify("Error while sending invite. Try after sometime !!","error")
                }
            });
        }
    });

    var registerForm = $('#registerForm')
    registerForm.validate({
        rules:{
            firstName:{
            required:true
             },
            lastName:{
                required:true
            },
            email:{
                required:true,
                email:true,
                remote:{
                    url: "login/validateEmail",
                    type : 'post'
                }
            },
            username:{
                required:true,
                remote:{
                    url: "login/validateUsername",
                    type:'post'
                   /* data: {username : function() {

                        return $( "#user-name" ).val();
                    }}*/
                }
            },
            password:{
                required:true,
                minLength : 5
            },
            confirmPassword:{
                required:true,
                equalTo : "#password-register"
            }},
        messages:{
           // username : 'User name already taken.Please try some other!'
            username :{
                remote : 'Please type in a unique username'
            },
            email:{
                remote : 'Email already registered'
            },
            password : {
                minLength : 'Please enter atleast five characters'
            },
            confirmPassword : {
                equalTo : 'Password and confirm password do not match'
            }
        }
    });

    var userUpdateForm = $('#user-update-form')
    userUpdateForm.validate({
        rules:{
            username:{
                required:true,
                remote:{
                    url: "user/validateUpdatedUserName",
                    type:'post'
                }
            },
            firstName : {
                required:true
            },
            lastName : {
                required : true
            }
        },
        messages:{
            username :{
                remote : 'User name already taken.Try some other combination!'
            },
            firstName : {
                required : 'Please enter first Name'
            },
            lastName : {
                required : 'Please enter last Name'
            }
        },
        submitHandler: function(form) {
                debugger;
                $('#submit').attr('disabled','disabled');
                $(form).ajaxSubmit(function (response) {
                    if(response){
                        $.notify("Profile updated successfully","success")
                        setTimeout(function(){
                            location.reload(true)
                        }, 2000);
                    }else{
                        $.notify("Error while updating profile. Try after sometime !!","error")
                    }
                });
            }
    }
    );

    var userPasswordUpdateForm = $('#user-password-update-form')
    userPasswordUpdateForm.validate({
            rules:{
                password : {
                    required:true
                },
                confirmPassword : {
                    required : true,
                    equalTo : '#user-update-password'
                }
            },
            messages:{
                confirmPassword :{
                    equalTo : 'Confirm password does not match with password'
                }
            },
            submitHandler: function(form) {
                debugger;
                $('#submit').attr('disabled','disabled');
                $(form).ajaxSubmit(function (response) {
                    if(response){
                        $.notify("Password updated successfully","success")
                    }else{
                        $.notify("Error while updating password. Try after sometime !!","error")
                    }
                });
            }
        }
    );

    var createTopicForm = $("#create-topic-form");
    createTopicForm.validate({
        errorElement: "div",
        submitHandler: function(form) {
            $('#submit').attr('disabled','disabled');
            $(form).ajaxSubmit(function (response) {
                if(response){
                    $('#create-topic').modal('toggle');
                    $.notify("Topic created successfully","success")
                }else{
                    $.notify("This topic name has already been created by you.Please give an unique name","error")
                }
            });
        }
    });

    var shareLinkForm = $("#share-link-form");
    shareLinkForm.validate({
        errorElement: "div",
        submitHandler: function(form) {
            debugger;
            $('#submit').attr('disabled','disabled');
            // add hidden field
            $(form).ajaxSubmit(function (response) {
                $('#share-link').modal('toggle');
                $('#share-link-topic > option[selected=selected]').prop('selected',false);
                $('#share-link-url').val('');
                $('#share-link-description').val('');
                if(response){
                    $.notify("Link created successfully","success")
                }else{
                    $.notify("Error while creating Link. Try after sometime !!","error")
                }
            });
        }
    });

    var editLinkForm = $("#edit-link-form");
    editLinkForm.validate({
        errorElement: "div",
        submitHandler: function(form) {
            debugger;
            $('#submit').attr('disabled','disabled');
            $(form).ajaxSubmit(function (response) {
                $('#edit-link').modal('toggle');
                if(response){
                    $.notify("Link updated successfully","success")
                    setTimeout(function(){
                        location.reload(true)
                    }, 2000);
                }else{
                    $.notify("Error while creating Link. Try after sometime !!","error")
                }
            });
        }
    });

    var shareDocumentForm = $("#share-document-form");
    shareDocumentForm.validate({
        errorElement: "div",
        submitHandler: function(form) {
            $('#submit').attr('disabled','disabled');
            $(form).ajaxSubmit(function (response) {
                $('#share-document').modal('toggle');
                $('#share-document-topic > option[selected=selected]').prop('selected',false);
                $('#share-document-description').val('');
                if(response){
                    $.notify("Document shared successfully","success")
                }else{
                    $.notify("Error while sharing document. Try after sometime !!","error")
                }
            });
        }
    });

    var editDocumentForm = $("#edit-document-form");
    editDocumentForm.validate({
        errorElement: "div",
        submitHandler: function(form) {
            debugger;
            $('#submit').attr('disabled','disabled');
            $(form).ajaxSubmit(function (response) {
                debugger;
                $('#edit-document').modal('toggle');
                if(response){
                    setTimeout(function(){
                        location.reload()
                    }, 2000);
                    $.notify("Document updated successfully","success")
                }else{
                    $.notify("Error while creating Document. Try after sometime !!","error")
                }
            });
        }
    });


    $('body').on('click','#resource-link-edit',function(){
        debugger;
        // populate data into the modal
        var url = $("#resource-link-view-site").attr('href');
        var description = $("#resource-description-view-page").html();
        var topicId = $("#resource-view-topic-id").val();
        var resourceId = $("#resource-id-index-page").val();
        $('#edit-link-topic > option[selected=selected]').prop('selected',false);
        $('#edit-link-topic > option[value="'+topicId+'"]').prop('selected', true);
        $('#edit-link-url').val(url);
        $('#edit-link-description').val(description);
        $('#resource-id-edit-link-page').val(resourceId)
    });

    $('body').on('click','#resource-link-delete',function(){
        debugger;
        // populate data into the modal
        var resourceId = $("#resource-id-index-page").val();
        $('#resource-id-delete-link-page').val(resourceId)
    });

    $('body').on('click','#resource-document-edit',function(){
        debugger;
        // populate data into the modal
        var previousDocPath = $("#document-download").attr('data-path')
        var splitArray = previousDocPath.split('/')
        var fileName = splitArray[splitArray.length-1]
        var description = $("#resource-description-view-page").html();
        var topicId = $("#resource-view-topic-id").val();
        var resourceId = $("#resource-id-index-page").val();
        $('#edit-document-topic > option[selected=selected]').prop('selected',false);
        $('#edit-document-topic > option[value="'+topicId+'"]').prop('selected', true);
        $('#previous-document-path').html(fileName);
        $('#edit-document-description').val(description);
        $('#resource-id-edit-document-page').val(resourceId)
    });


    $('#mark-resource-as-read').click(function(e){

        debugger;
        var resourceId = $(this).attr('data-resource-id')
        var successCallback = function (response) {
            if(response){
                debugger;
                $.notify("Successfully marked as read","success")
                setTimeout(function(){
                    location.reload()
                }, 2000);
            }else{
                $.notify("Error while marking as read.Please try after sometime")
            }
        }
        $.ajax({
            url: "resource/markAsRead",
            data: {'resourceId' : resourceId},
            success: successCallback
        });
        e.preventDefault()

    });
});
