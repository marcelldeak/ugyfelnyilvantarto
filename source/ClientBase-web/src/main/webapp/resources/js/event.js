function clearAndCloseAddEventDialog(delayed) {

    if (!delayed) {
        clearAddModal();
    } else {
        setTimeout(clearAddModal, 1000);
    }
}

function clearAndCloseEditEventDialog(delayed) {

    if (!delayed) {
        $('#customer_edit_dialog').modal('hide');
    } else {
        setTimeout(function () {
            $('#customer_edit_dialog').modal('hide');
        }, 1000);
    }

}

function clearAddModal() {
    $('#event_add_dialog').modal('hide');
    $('#event_add_form').trigger('reset');

}


