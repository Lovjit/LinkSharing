<!-- Modal -->
<div id="delete-link" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Delete Link</h4>
            </div>

            <form id="delete-link-form" action="resource/deleteLinkRes">
                <div class="modal-body">
                    <h3>Are you sure that you want to delete this resource?</h3>

                    <p><b>Note : This operation cannot be undone</b></p>
                    <input type="hidden" name="resourceId" id="resource-id-delete-link-page">
                </div>

                <div class="modal-footer">
                    <button id="delete-link-save" type="submit" class="btn btn-default">Delete</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>