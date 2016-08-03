<!-- Modal -->
<div id="edit-document" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Share Document</h4>
            </div>
            <form id="edit-document-form" action="resource/editDocResource" method="POST" enctype="multipart/form-data">
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="row">
                            <h6 style="color: red">**In case you upload a document , previously saved document will be deleted.</h6>
                            <div class="col-md-4">
                                <label for="edit-document-path">Previous Doc Name</label>
                            </div>
                            <div class="col-md-4">
                                <p id="previous-document-path"> </p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <label for="edit-document-path">Document</label>
                            </div>
                            <div class="col-md-4">
                                <input type="file" id="edit-document-path" name="document" required>
                                <input id="resource-id-edit-document-page" type="hidden" name="resourceId"  />
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-4">
                                <label for="edit-document-description">Description</label>
                            </div>
                            <div class="col-md-4">
                                <textarea id= "edit-document-description" name="description" rows="5" cols="18" class="form-control" placeholder="Description" required></textarea>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-4">
                                <label for="edit-document-topic">Topic</label>
                            </div>
                            <div class="col-md-4">

                                <g:select id="edit-document-topic" class="form-control send-invitation-topic-dropdown"
                                          optionKey="id" optionValue="name" required="required"
                                          name="topicId" from="${topics}" noSelection="['':'-Select a topic-']" />

                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-default">Update</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                </div>
            </form>
        </div>

    </div>
</div>


</div>
</div>



