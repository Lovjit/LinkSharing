<!-- Modal -->
<div id="send-invitation" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Send Invitation</h4>
            </div>
            <form id="send-invite-form" action="user/sendEmailInvite">
                <div class="modal-body">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-4">
                            <label for="send-invitation-email-id">Email</label>
                        </div>
                        <div class="col-md-4">
                            <input id="send-invitation-email-id" type="email" class="form-control" placeholder="Email" name="emailId" required>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-4">
                            <label for="send-invitation-topic">Topic</label>
                        </div>
                        <div class="col-md-4">


                            <g:select id="send-invitation-topic" class="form-control send-invitation-topic-dropdown"
                                      optionKey="id" optionValue="name" required="required" name="selectedTopicId"
                                      from="${topics}" noSelection="['':'-Select a topic-']" />

                            <input id="send-invitation-topic-hidden" type="hidden" name="topicId"  />
                        </div>
                    </div>
                </div>
            </div>
                <div class="modal-footer">
                <button id="invite-via-mail-submit" type="submit" class="btn btn-default">Invite</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                </div>
            </form>
        </div>


    </div>
</div>


</div>
</div>