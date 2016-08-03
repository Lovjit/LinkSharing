<!-- Modal -->
<div id="share-document" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Share Document</h4>
            </div>
            <form id="share-document-form" action="resource/saveDocResource" method="POST" enctype="multipart/form-data">
                <div class="modal-body">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-4">
                            <label for="share-document-url">Document</label>
                        </div>
                        <div class="col-md-4">
                            <input type="file" id="share-document-url" name="document" required>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-4">
                            <label for="share-document-description">Description</label>
                        </div>
                        <div class="col-md-4">
                            <textarea id= "share-document-description" name="description" rows="5" cols="18" class="form-control" placeholder="Description" required></textarea>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-4">
                            <label for="share-document-topic">Topic</label>
                        </div>
                        <div class="col-md-4">

                            <g:select id="share-document-topic" class="form-control send-invitation-topic-dropdown"
                                      optionKey="id" optionValue="name" required="required"
                                      name="topicId" from="${topics}" noSelection="['':'-Select a topic-']" />

                            %{--<select id="share-document-topic" class="form-control send-invitation-topic-dropdown">
                                <option value="Grails">Topic 1</option>
                                <option value="Java">Topic 2</option>
                            </select>--}%
                        </div>
                    </div>
                </div>
            </div>
                <div class="modal-footer">
                <button type="submit" class="btn btn-default">Share</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            </div>
            </form>
        </div>

    </div>
</div>


</div>
</div>



