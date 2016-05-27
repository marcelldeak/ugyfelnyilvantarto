function clearAndCloseAddEventDialog(delayed) {

    if (!delayed) {
        clearAddModal();
    } else {
        setTimeout(clearAddModal, 1000);
    }
}

function clearAddModal() {
    $('#event_add_dialog').modal('hide');
    $('#event_add_form').trigger('reset');

}

function closeDialog(dialog) {
    $('#' + dialog).modal('hide');
}
function clearAndCloseDeleteEventDialog(delayed) {
    if (!delayed) {
        $('#customer_edit_dialog').modal('hide');
    } else {
        setTimeout(function () {
            $('#event_delete_dialog').modal('hide');
        }, 1000);
    }
}
function clearAndCloseEditEventDialog(delayed) {

    if (!delayed) {
        $('#customer_edit_dialog').modal('hide');
    } else {
        setTimeout(function () {
            $('#event_edit_dialog').modal('hide');
        }, 1000);
    }

}



