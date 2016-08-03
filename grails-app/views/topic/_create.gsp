        <!-- Modal -->
        <div id="create-topic" class="modal fade" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Create Topic</h4>
                    </div>
                    <form id="create-topic-form" action="topic/save">
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-4">
                                    <label for="create-topic-name">Name</label>
                                </div>
                                <div class="col-md-4">
                                    <input id="create-topic-name" type="text" class="form-control" placeholder="Name" name="topicName" required>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-md-4">
                                    <label for="create-topic-visibility">Visibility</label>
                                </div>
                                <div class="col-md-4">

                                    <select id="create-topic-visibility" name= "visibility"class="form-control create-topic-visibility-dropdown">
                                        <option value="Public">Public</option>
                                        <option value="Private">Private</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button id="topic-save" type="submit" class="btn btn-default">Save</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    </div>
                    </form>
                </div>

            </div>
        </div>


    </div>
</div>