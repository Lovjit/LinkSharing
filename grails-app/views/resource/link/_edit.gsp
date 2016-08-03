<!-- Modal -->
<div id="edit-link" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Edit Link</h4>
            </div>
            <form id="edit-link-form" action="resource/editLinkRes">
                <div class="modal-body">

                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-4">
                                <label for="edit-link-url">Link</label>
                            </div>
                            <div class="col-md-4">
                                <input id="edit-link-url" type="url" class="form-control" placeholder="Link" name="url" required>
                                <input id="resource-id-edit-link-page" type="hidden" name="resourceId"  />
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-4">
                                <label for="edit-link-description">Description</label>
                            </div>
                            <div class="col-md-4">
                                <textarea id= "edit-link-description" rows="5" cols="18" class="form-control" placeholder="Description" name="description" required></textarea>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-4">
                                <label for="edit-link-topic">Topic</label>
                            </div>
                            <div class="col-md-4">
                                <g:select id="edit-link-topic" class="form-control send-invitation-topic-dropdown"
                                          optionKey="id" optionValue="name" required="required"
                                          name="topicId" from="${topics}" noSelection="['':'-Select a topic-']" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button id="edit-link-save" type="submit" class="btn btn-default">Share</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                </div>
            </form>
        </div>

    </div>
</div>
